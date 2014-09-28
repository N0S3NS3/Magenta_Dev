package net.magenta.engine.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RenderComponent {
	private Sprite sprite;
	private Texture texture;
	private int x;
	private int y;
	
	protected RenderComponent(){
		
	}
	
	public static RenderComponent getComponent(){
		return new RenderComponent();
	}
	
	public RenderComponent(Texture texture, Sprite sprite, int x, int y){
		this.texture = texture;
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	
	public void dispose(){
		this.texture.dispose();
		//this.sprite.
	}
	
	
}
