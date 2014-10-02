package com.mosquito.games.view.action;

import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.mosquito.games.app.system.util.SequenceAnimationController;

public class PlaySequenceAnimationAction extends RunnableAction {
	SequenceAnimationController animationController;
	String animationName;

	public PlaySequenceAnimationAction(SequenceAnimationController animationController, String animationName) {
		this.animationController = animationController;
		this.animationName = animationName;
	}
	
	@Override
	public void run() {
		animationController.play(animationName);
	}
}
