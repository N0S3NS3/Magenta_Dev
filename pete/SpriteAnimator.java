package pete;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

public class SpriteAnimator 
{
	/*
	 * USING THIS CLASS
	 * Each entity which uses sprites will have 
	 * their own instance of SpriteAnimator to
	 * handle displaying the sprite on screen
	 * 
	 * 
	 * 
	 * Sprite orientations are based on the num pad 
	 * (8 = north 2 = south 4 = west 6 = east)
	 */
	
	TextureAtlas spriteAtlas;
	SpriteBatch batch;
	Texture img;
	Sprite[] spriteFrames;
	
	int currSecondsElapsed;
	long currSecondsElapsedL;
	long startTimeMilli;
	
	//Animation currAnim;
	
	
	String animTitle;
	int animFrameCount;
	int orientation;
	
	int xPos,yPos;
	int[] rotOffset;
	float xSpeed,ySpeed;
	
	int i;


	public SpriteAnimator(TextureAtlas p_spriteAtlas,AnimationInfo p_currAnimInfo,int p_orientation,int p_xPos,int p_yPos, float p_xSpeed, float p_ySpeed)// int p_rotationOffsetX, int p_rotationOffsetY)
	{
		spriteAtlas = p_spriteAtlas;
		animTitle = p_currAnimInfo.getAnimTitle();
		animFrameCount = p_currAnimInfo.getFrameCount();
		rotOffset = p_currAnimInfo.getRotationOffsets();
		orientation = p_orientation;
		xPos = p_xPos;
		yPos = p_yPos;
		xSpeed = p_xSpeed;
		ySpeed = p_ySpeed;
		i = 0;
		batch = new SpriteBatch();
		startTimeMilli = System.currentTimeMillis();
		spriteFrames = new Sprite[animFrameCount];
		
		Sprite sprite = new Sprite();
		
		for(int i = 0; i < animFrameCount; i++)
		{
			sprite = spriteAtlas.createSprite(animTitle+String.format("%04d", i));
			spriteFrames[i] = sprite;
		}
	}
    
    public Sprite getCurrentSpriteFrame()
    {
    	/*This is called to select the particular 
    	 * frame that the sprite should be displaying
    	 * at a given time since it's creation.
    	 * 
    	 *
    	 */
    	currSecondsElapsedL = ((System.currentTimeMillis() - startTimeMilli));
		currSecondsElapsed = (int)currSecondsElapsedL/75; //lower number here = faster animations
		
		//System.out.println(((currSecondsElapsed+1)%(animFrameCount-1))+1);
		Sprite currentSpriteFrame = spriteFrames[(int)((currSecondsElapsed+1)%(animFrameCount-1))+1];

		//make each frame face the correct direction
		currentSpriteFrame = orientSprite(orientation, currentSpriteFrame);
		//sprites were drawn at 50x50px, shrink them to 32x32px with setScale
		currentSpriteFrame.setScale((float)0.64); 
		currentSpriteFrame.setPosition(xPos, yPos);
		
    	return currentSpriteFrame;
    }

    private Sprite orientSprite(int orientation, Sprite currentSpriteFrame) 
    {
    	switch(orientation)
		{
			case 8:
				currentSpriteFrame.setOrigin(0, 0);
				break;
			case 6:	
				currentSpriteFrame.setOrigin(rotOffset[0], rotOffset[1]);
				currentSpriteFrame.setRotation(270);
				break;
			case 2:
				currentSpriteFrame.setOrigin(rotOffset[2], rotOffset[3]);
				currentSpriteFrame.setRotation(180);
				break;
			case 4:
				currentSpriteFrame.setOrigin(rotOffset[4], rotOffset[5]);
				currentSpriteFrame.setRotation(90);
				break;
			default:
				//throw error
				break;
		}
    	return currentSpriteFrame;
	}

	public void drawSprite()
    {
		currSecondsElapsedL = ((System.currentTimeMillis() - startTimeMilli));
		currSecondsElapsed = (int)currSecondsElapsedL/75; //lower number here = faster animations
		
		//System.out.println(((currSecondsElapsed+1)%(animFrameCount-1))+1);
		Sprite currentSpriteFrame = spriteFrames[(int)((currSecondsElapsed+1)%(animFrameCount-1))+1];

		//make each frame face the correct direction
		currentSpriteFrame = orientSprite(orientation, currentSpriteFrame);
		//sprites were drawn at 50x50px, shrink them to 32x32px with setScale
		currentSpriteFrame.setScale((float)0.64); 
		currentSpriteFrame.setPosition(xPos, yPos);

    	batch.begin();
    	i++;//used to determine sprite position for moving sprites
    	currentSpriteFrame.setPosition(currentSpriteFrame.getX()+(xSpeed*i/3),currentSpriteFrame.getY()+(ySpeed*i/3) );
    	currentSpriteFrame.draw(batch);
    	batch.end();	
    }
}