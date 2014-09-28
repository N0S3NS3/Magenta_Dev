package net.magenta.engine.manager;

import java.util.Arrays;

import net.magenta.engine.message.Message;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

/*
 * NOTE: USING THIS CLASS
 * 	Maps can be loaded by calling the loadNewMap function and passing the
 *  name of the map and by providing a mapName to the MapHandler constructor.
 * 
 * TODO: Triggers
 * 	triggers(certain tiles, when stepped on will cause reactions such as
 *  text pop ups), this can probably be added to logicLayer when we figure out
 *  what kind of trigger events we need.
 *  
 *NOTE: 
 *	Currently, logic tiles are stored in single tiles, 
 *  their width and height is not accounted for.
 *  This can easily be changed if there is a reason to do so. 
 */

public class MapManager extends Manager
{
	private int mapHeight;
	private int mapWidth;
	private int tileSize;

	String mapFilePath;

	TiledMap currentMap;
	String currentMapTitle;
	TiledMapTileSets currentTileSet;

	final String visualLayerTitle = "visual";
	final String obstacleLayerTitle = "obstacles";
	final String logicLayerTitle = "logic";

	final String pSpawnName = "playerSpawn";
	final char pSpawnChar = 'p';
	final String eSpawnName = "enemySpawn";
	final char eSpawnChar = 'e';
	final String breadName = "foodBread";
	final char breadChar = 'b';
	final String exitName = "exit";
	final char exitChar = 'z';

	MapLayer visualLayer;
	MapLayer logicLayer;
	MapLayer collisionLayer;

	int[][] collideGrid;
	char[][] logicGrid; // used to store spawn points, food, and shadows

	OrthographicCamera camera;

	public MapManager(String mapTitle, int mapWidth, int mapHeight, int tileSize)
	{
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.tileSize = tileSize;
		//This should not be handled in constructor
		this.mapFilePath = "Maps/" + mapTitle + ".tmx";
		
//		stageWidth = MyGdxGame.stageWidth;// map widths = 25 tiles
//		stageHeight = MyGdxGame.stageHeight;// map heights = 19 tiles
//		tileSize = MyGdxGame.tileSize;

		loadNewMap(currentMapTitle);

		Gdx.graphics.setDisplayMode(800, 608, false);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
		camera.setToOrtho(false, w, h);
		camera.update();

	}

	@Override public boolean update(){
		float unitScale = 1f;
		OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(currentMap, unitScale);
		renderer.setView(camera);
		camera.update();
		renderer.render();
		return true;
	}

	public void destroyMap()
	{
		// unloads a current map, clears all data

	}

	public void loadNewMap(String mapTitle)
	{
		currentMap = new TmxMapLoader().load(mapFilePath);

		// COLLISION DATA LOADING////
		collisionLayer = currentMap.getLayers().get("collision");
		MapObjects collisionObjects = collisionLayer.getObjects();
		collideGrid = new int[mapWidth][mapHeight];// 0 = no collider 1 =
														// collider
		collideGrid = buildCollideGrid(collideGrid, collisionObjects);
		printMapData(collideGrid);

		// LOGIC DATA LOADING/////
		logicLayer = currentMap.getLayers().get("logic");
		MapObjects logicObjects = logicLayer.getObjects();
		logicGrid = new char[mapWidth][mapHeight];
		logicGrid = buildLogicGrid(logicGrid, logicObjects);
		printMapData(logicGrid);
	}

	private int[][] buildCollideGrid(int[][] p_cGrid, MapObjects p_cObjects)
	{
		for (int[] row : p_cGrid)
			Arrays.fill(row, 0);
		for (MapObject object : p_cObjects)
		{
			RectangleMapObject rectObj = (RectangleMapObject) object;
			Rectangle rect = rectObj.getRectangle();

			int rectX, rectY, rectWidth, rectHeight;
			rectX = (int) rect.x / tileSize;
			rectY = (int) rect.y / tileSize;
			rectHeight = (int) rect.height / tileSize;
			rectWidth = (int) rect.width / tileSize;

			for (int i = rectX; i < rectX + rectWidth; i++)
				for (int j = rectY; j < rectY + rectHeight; j++)
				{
					p_cGrid[i][j] = 1;
				}
			// System.out.println("x: " +rectX + ", y: " + rectY+ ", H:" +
			// rectHeight + ", W:" + rectWidth );
		}
		return p_cGrid;
	}

	private char[][] buildLogicGrid(char[][] p_lGrid, MapObjects p_lObjects)
	{
		{
			for (char[] row : p_lGrid)
				Arrays.fill(row, '0');

			for (MapObject object : p_lObjects)
			{
				RectangleMapObject rectObj = (RectangleMapObject) object;
				Rectangle rect = rectObj.getRectangle();

				int rectX, rectY, rectHeight, rectWidth; // for now logic tiles
															// are only 1x1
				rectX = (int) rect.x / tileSize;
				rectY = (int) rect.y / tileSize;
				rectHeight = (int) rect.height / tileSize;
				rectWidth = (int) rect.width / tileSize;

				if (object.getName().matches(breadName))
				{

					System.out.println("BREAD LOCATED x: " + rectX + " | y: " + rectY);
					for (int i = rectX; i < rectX + rectWidth; i++)
						for (int j = rectY; j < rectY + rectHeight; j++)
						{
							p_lGrid[i][j] = breadChar;
						}
				}
				else if (object.getName().matches(eSpawnName))
				{
					System.out.println("ENEMY SPAWN LOCATED x: " + rectX + " | y: " + rectY);
					/*
					 * TODO: SEND MESSAGE TO CREATE ENEMY AT THIS LOCATION AND
					 * ORIENTATION There is no point in putting this tile in a
					 * char based grid as the orientation info would be lost, if
					 * necessary we can add an additional 2d array that holds a
					 * specialized class w/ orientation info included
					 */
				}
				else if (object.getName().matches(pSpawnName))
				{
					int orientation = Integer.parseInt(object.getProperties().get("orientation").toString());
					System.out.println("PLAYER SPAWN LOCATED x: " + rectX + " | y: " + rectY + " Orientation = " + orientation);
					/*
					 * TODO: SEND MESSAGE TO CREATE PLAYER AT THIS LOCATION AND
					 * ORIENTATION There is no point in putting this tile in a
					 * char based grid as the orientation info would be lost, if
					 * necessary we can add an additional 2d array that holds a
					 * specialized class w/ orientation info included
					 */

				}
				else if (object.getName().matches(exitName))
				{
					System.out.println("EXIT LOCATED: x: " + rectX + " | y: " + rectY);
					// ADD TO logicGrid
					for (int i = rectX; i < rectX + rectWidth; i++)
						for (int j = rectY; j < rectY + rectHeight; j++)
						{
							p_lGrid[i][j] = exitChar;
						}
				}
				else
				{
					System.out.println("UNKNOWN LOGIC TILE FOUND, NAME: " + object.getName());
				}
			}
			return p_lGrid;
		}

	}

	public void printMapData(int[][] grid)
	{
		// for testing to verify that grids are being recorded properly
		System.out.println("COLLISION MAP");
		for (int i = mapHeight - 1; i >= 0; i--)
		{
			for (int j = 0; j < mapWidth; j++)
			{
				System.out.print(grid[j][i]);
			}
			System.out.println();
		}

	}

	private void printMapData(char[][] p_logicGrid)
	{
		// for testing to verify that grids are being recorded properly
		System.out.println("LOGIC MAP");
		for (int i = mapHeight - 1; i >= 0; i--)
		{
			for (int j = 0; j < mapWidth; j++)
			{
				System.out.print(p_logicGrid[j][i]);
			}
			System.out.println();
		}
	}


	@Override public boolean dispose(){
		return true;
	}
	@Override public void handleMessage(Message message){
		
	}
	
	// FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF/////////////////////////////

}
