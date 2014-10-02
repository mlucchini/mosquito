package com.mosquito.games.view.actor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.after;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mosquito.games.app.system.util.SequenceAnimationController;
import com.mosquito.games.app.system.util.TextureFactory;
import com.mosquito.games.model.event.MoveStartedEvent.Direction;
import com.mosquito.games.shared.BoardPosition;
import com.mosquito.games.shared.Color;
import com.mosquito.games.shared.ItemType;
import com.mosquito.games.view.action.DispatchViewEventAction;
import com.mosquito.games.view.action.HideItemActorAction;
import com.mosquito.games.view.action.PlaySequenceAnimationAction;
import com.mosquito.games.view.action.ShowItemAction;
import com.mosquito.games.view.actor.anim.ItemAnim;
import com.mosquito.games.view.actor.helper.ActorDrawer;
import com.mosquito.games.view.actor.input.ItemActorInputListener;
import com.mosquito.games.view.event.ViewCollectFinishedEvent;
import com.mosquito.games.view.event.ViewCollectStartedEvent;
import com.mosquito.games.view.event.ViewItemGrownEvent;

public class ItemActor extends Group {
	Vector2 tileSize;
	int z;
	TextureAtlas textureAtlas;
	AtlasRegion texture;
	SequenceAnimationController animationController;

    public ItemActor(BoardPosition boardPosition, ItemType type, Vector2 tileSize) {
    	this.tileSize = tileSize;
    	this.z = type.level;

    	setTexture(type);
    	setTransform(boardPosition);
    	setAnimationControllers(type);
    	setListeners();

        setVisible(false);
    }

    private void setTexture(ItemType type) {
    	textureAtlas = TextureFactory.makeAtlas(type);
    	texture = textureAtlas.findRegion("state0" + type.level);
    	texture.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    }

    private void setTransform(BoardPosition boardPosition) {
    	Vector2 position = getViewPosition(boardPosition);

    	setScale(tileSize.x / texture.getRegionWidth(), tileSize.y / texture.getRegionHeight());
        setBounds(position.x, position.y, texture.getRegionWidth() * getScaleX(), texture.getRegionHeight() * getScaleY());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
    }
    
    private void setAnimationControllers(ItemType type) {
    	animationController = new SequenceAnimationController(type, textureAtlas);
    }
    
    private void setListeners() {
    	addListener(new ItemActorInputListener(this));
    }
    
    public int getZ() {
    	return z;
    }
    
    public void freeze() {
    	queue(ItemAnim.getFreeze());
    }
    
    public void unfreeze() {
    	queue(ItemAnim.getUnfreeze());
    }

	public void play(String animationName) {
		queue(new PlaySequenceAnimationAction(animationController, animationName));
	}
    
    public void show(ItemType type) {
		queue(new ShowItemAction(type, this));
    }
	
	public void explode() {
    	queue(ItemAnim.getExplode(this));
	}
    
    public void shiftTo(final BoardPosition boardPosition, Direction direction, boolean moves) {
    	if (moves) {
    		addAction(ItemAnim.getShiftTo(direction, getViewPosition(boardPosition)));
    	} else {
    		addAction(bounceToSelf(boardPosition, direction));
    	}
    }

	private Action bounceToSelf(BoardPosition boardPosition, Direction direction) {
		return ItemAnim.getShiftToBounce(boardPosition, direction);
	}

	public void collect(Color color, boolean upCorner) {
		float deltaY = upCorner ? getHeight() : - getHeight();
    	queue(sequence(
    			new DispatchViewEventAction(new ViewCollectStartedEvent(color)),
    			ItemAnim.getCollect(deltaY),
    			new DispatchViewEventAction(new ViewCollectFinishedEvent(color)),
    			new HideItemActorAction(this)));
	}

	public void growInto(ItemType type, ItemActor grownItemActor) {
		queue(sequence(
				new ShowItemAction(type, grownItemActor),
				new DispatchViewEventAction(new ViewItemGrownEvent())));
	}

	public void hide() {
		queue(new HideItemActorAction(this));
	}

    private Vector2 getViewPosition(BoardPosition position) {
    	return new Vector2(position.x * tileSize.x, (BoardPosition.LAST_ROW - position.y) * tileSize.y);
    }

    @Override
    public void act(float delta) {
    	super.act(delta);
    	animationController.update(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	super.draw(batch, parentAlpha);

    	TextureRegion region = texture;
    	if (animationController.isAnimating()) {
    		region = animationController.getCurrentTexture();
    	}
    	ActorDrawer.draw(this, batch, parentAlpha, region);
    }

	public void dispose() {
		textureAtlas.dispose();
	}
	
	private void queue(Action action) {
		addAction(after(action));
	}
}
