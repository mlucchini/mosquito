package com.mosquito.games.view.actor.anim;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mosquito.games.model.event.MoveStartedEvent.Direction;
import com.mosquito.games.shared.BoardPosition;
import com.mosquito.games.view.action.AddParticleAction;

public class ItemAnim {
	public static final float FPS = 30f;
	
	public static final float SHIFT_TO_DURATION = 0.25f;
	
	static final float APPEAR_DURATION = 0.4f;
	static final float DISAPPEAR_DURATION = 0.5f;
	static final float FREEZE_DURATION = 0.3f;
	static final float UNFREEZE_DURATION = 0.4f;
	static final float SPAWN_DURATION = 0.4f;
	static final float EXPLODE_DURATION = 0.4f;
	static final float COLLECT_DURATION = 1f;
	
	static final float BUMP_UP_SCALE = 1.15f;
	static final float SPAWN_SCALE = 1.1f;
	static final float SQUISH_SCALE = 0.8f;
	static final float COLLECT_SCALE = 1.5f;

	static final float SHIFT_TO_BOUNCE_FACTOR = 10f;
	
	public static Action getBumpUp() {
		return sequence(scaleTo(BUMP_UP_SCALE, BUMP_UP_SCALE, ItemAnim.APPEAR_DURATION / 2f, Interpolation.pow2Out),
						scaleTo(1f, 1f, ItemAnim.APPEAR_DURATION / 2f, Interpolation.pow2In));
	}
	
	public static Action getSpawned() {
		return sequence(scaleTo(0f, 0f, 0f),
						scaleTo(SPAWN_SCALE, SPAWN_SCALE, ItemAnim.SPAWN_DURATION / 2f, Interpolation.pow2Out),
						scaleTo(1f, 1f, ItemAnim.SPAWN_DURATION / 2f, Interpolation.pow2In));
	}
	
	public static Action getMerge(Group actor) {
		return parallel(new AddParticleAction(actor, "merge"),
						ItemAnim.getBumpUp());
	}

	public static Action getVerticalSquish() {
		return sequence(scaleTo(1f, SQUISH_SCALE, SHIFT_TO_DURATION / 2f, Interpolation.pow2Out),
						scaleTo(1f, 1f, SHIFT_TO_DURATION / 2f, Interpolation.pow2Out));
	}
	
	public static Action getHorizontalSquish() {
		return sequence(scaleTo(SQUISH_SCALE, 1f, SHIFT_TO_DURATION / 2f, Interpolation.pow2Out),
						scaleTo(1f, 1f, SHIFT_TO_DURATION / 2f, Interpolation.pow2Out));
	}
	
	public static Action getFreeze() {
		return rotateTo(90f, FREEZE_DURATION, Interpolation.pow2Out);
	}

	public static Action getUnfreeze() {
		return rotateTo(0f, UNFREEZE_DURATION, Interpolation.pow2Out);
	}
	
	public static Action getCollect(float deltaY) {
		return sequence(scaleTo(COLLECT_SCALE, COLLECT_SCALE, ItemAnim.COLLECT_DURATION / 2f, Interpolation.pow2In),
						parallel(scaleTo(0f, 0f, ItemAnim.COLLECT_DURATION / 2f, Interpolation.pow2In),
						moveBy(0f, deltaY, ItemAnim.COLLECT_DURATION / 2f, Interpolation.pow2In)));
	}
	
	public static Action getExplode(Actor actor) {
		return scaleTo(0f, 0f, ItemAnim.EXPLODE_DURATION, Interpolation.pow2Out);
	}
	
	public static Action getShiftTo(Direction direction, Vector2 destination) {
		boolean vertival = direction == Direction.UP || direction == Direction.DOWN;
		return parallel(moveTo(destination.x, destination.y, ItemAnim.SHIFT_TO_DURATION, Interpolation.pow2Out),
						vertival ? ItemAnim.getVerticalSquish() : ItemAnim.getHorizontalSquish());
	}

	public static Action getShiftToBounce(BoardPosition boardPosition, Direction direction) {
		float x = SHIFT_TO_BOUNCE_FACTOR * ((direction == Direction.LEFT) ? -boardPosition.x : (direction == Direction.RIGHT) ? BoardPosition.LAST_COLUMN - boardPosition.x : 0f);
		float y = SHIFT_TO_BOUNCE_FACTOR * ((direction == Direction.UP) ? boardPosition.y : (direction == Direction.DOWN) ? -BoardPosition.LAST_ROW + boardPosition.y : 0f); 
		return sequence(
    		Actions.moveBy(x, y, ItemAnim.SHIFT_TO_DURATION / 2f, Interpolation.pow2Out),
    		Actions.moveBy(-x, -y, ItemAnim.SHIFT_TO_DURATION / 2f, Interpolation.pow2Out));
	}
}
