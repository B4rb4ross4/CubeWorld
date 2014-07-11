package com.cubeworld.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Array;

public class CubeWorldGame extends ApplicationAdapter {
	private PerspectiveCamera cam;
	private Model model;
	private ModelBatch modelBatch;
	private CameraInputController camController;
	private Array<ModelInstance> instances = new Array<ModelInstance>();
	
	
	@Override
	public void create () {
		modelBatch = new ModelBatch();
		
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(12f, 12f, 12f);
		cam.lookAt(0, 0, 0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();
		
		ModelBuilder modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(5f, 5f, 5f, new Material(ColorAttribute.createDiffuse(Color.NAVY)), Usage.Position | Usage.Normal);
		
		for(float x = -10f ; x <= 10f ; x += 5f) {
			for(float z = -10f ; z <= 10f ; z += 5f) {
				ModelInstance boxInstance = new ModelInstance(model);
				boxInstance.transform.setToTranslation(x, 0, z);
				instances.add(boxInstance);

			}
		}
		
		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);
		
	}

	@Override
	public void render () {
		camController.update();
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		modelBatch.begin(cam);
		modelBatch.render(instances);
		modelBatch.end();
		
	}
	
	public void dispose() {
		model.dispose();
		instances.clear();
	}
}
