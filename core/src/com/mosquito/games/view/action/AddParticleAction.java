package com.mosquito.games.view.action;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.mosquito.games.view.actor.ParticleActor;

public class AddParticleAction extends RunnableAction {
	Group actor;
	String effectName;

	public AddParticleAction(Group actor, String effectName) {
		this.actor = actor;
		this.effectName = effectName;
	}
	
	@Override
	public void run() {
		ParticleActor particleActor = new ParticleActor(effectName);
		actor.addActor(particleActor);
		particleActor.start();
	}
}
