package pete;

import net.magenta.engine.manager.MapManager;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/*
 * NOTE: anything that is not labeled as needed should be handled 
 * via message passing from different entities and components
 */


public class MyGdxGame extends ApplicationAdapter
{
	//NEEDED IN ACTUAL GAME CORE CREATE AND LOOP FOR PETER'S SHIT TO WORK///
	int currSecondsElapsed;
	long currSecondsElapsedL;
	long startTimeMilli;
	
	final static int tileSize = 32;
	final static int stageWidth = 25;
	final static int stageHeight = 19;
	
	TextureAtlas spriteAtlas;
	/*
	 * this is the main sprite sheet where all sprite frames
	 * will be held, it must be somewhere where all SpriteAnimators
	 * can access it via reference.
	 */
	/////////////////////////////////////////////////////////////////////////

	//This will be probs be handled by some map or game management system////
	MapManager mH;
	/////////////////////////////////////////////////////////////////////////
	
	//These will be held by each respective entity///////////////////////////
	SpriteAnimator animatorEnemy;
	SpriteAnimator animatorEnemy2;
	SpriteAnimator animatorPlayer;
	SpriteAnimator animatorPlayer2;//obviously no player2 exists
	/////////////////////////////////////////////////////////////////////////

	@Override
	public void create()
	{
		spriteAtlas = new TextureAtlas(Gdx.files.internal("FinalSpriteAtlas/FinalSpriteSheet.txt"));;
		
		
		//definitely will be moved to map managers or entities//////////////////////////////////////////////
		mH = new MapManager("map1", stageWidth, stageHeight, tileSize);

		
		int[] rotationOffsets_Enemy =
		{ 16, 24, 2, 18, 24, 12 };
		int[] rotationOffsets_Player =
		{ 16, 20, 0, 18, 22, 14 };
		AnimationInfo playerAnimInfo = new AnimationInfo(19, "Player/Walk/Player_Walk_New", rotationOffsets_Player);
		AnimationInfo enemyAnimInfo = new AnimationInfo(19, "Enemy/Walk/Enemy_Walk", rotationOffsets_Enemy);

		animatorEnemy = new SpriteAnimator(spriteAtlas, playerAnimInfo, 8, (tileSize * 3), (tileSize * 6), 0, 1);
		animatorEnemy2 = new SpriteAnimator(spriteAtlas, playerAnimInfo, 6, (tileSize * 4), (tileSize * 6), 1, 0);
		animatorPlayer = new SpriteAnimator(spriteAtlas, enemyAnimInfo, 2, (tileSize * 20), (tileSize * 15), 0, -1);
		animatorPlayer2 = new SpriteAnimator(spriteAtlas, enemyAnimInfo, 4, (tileSize * 20), (tileSize * 16), -1, 0);
		///////////////////////////////////////////////////////////////////////
	}

	@Override
	public void render()
	{
		//NEEDED FOR DISPLAY TO WORK CORRECTLY (PROBABLY CAN BE MOVED SOMEWHERE ELSE THOUGH)
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//////////////////////////////////////////////////////////////////

		//Should be handled somewhere else//////////////
		mH.update();

		animatorEnemy.drawSprite();
		animatorEnemy2.drawSprite();
		animatorPlayer.drawSprite();
		animatorPlayer2.drawSprite();
		////////////////////////////////////////////////

	}

}
