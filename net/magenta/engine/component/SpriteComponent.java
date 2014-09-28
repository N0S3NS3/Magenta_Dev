package net.magenta.engine.component;

import net.magenta.engine.message.Message;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class SpriteComponent implements Component{
	private TextureAtlas textureAtlas;
	private SpriteBatch spriteBatch;
	private Texture texture;
	private Sprite[] spriteFrames;
	
	private long secondsDelta;
	private long milliDelta;
	
	private String animationTitle;
	private int animationFrameCount;
	private int spriteOrientation;
	
	private int spriteXCoordinate;
	private int spriteYCoordinate;
	
	private float spriteXSpeed;
	private float spriteYSpeed;
	
	protected SpriteComponent(TextureAtlas textureAtlas){ 
		//this.textureAtlas = textureAtlas
	}
	
	@Override public void update(){
		
	}
	@Override public void dispose(){
		
	}
	@Override public void handleMessage(Message message){
		
	}
	
}
