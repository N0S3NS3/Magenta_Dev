package pete;

public class AnimationInfo 
{
	/*
	 * This class functions as a simple data structure holding 
	 * 1.) An animation's String title (used to find it in the SpriteAtlas(main huge sprite sheet))
	 * 2.) That animation's frame count (used to pull the proper number of frames from SpriteAtlas)
	 * 3.) An animation's 6 rotationOffsets (hopefully you will never have to know about these, for more info ask Peter)
	 * ...
	 * Each animated entity will have an array/list of all of their possible animations
	 * stored as AnimationInfo 
	*/

	
	private int frameCount;
	private String animTitle;
	private int[] rotationOffsets;
	
	public AnimationInfo(int p_frameCount, String p_animTitle)
	{
		frameCount = p_frameCount;
		animTitle = p_animTitle;
		rotationOffsets = new int[] {0,0,0,0,0,0};
	}
	public AnimationInfo(int p_frameCount, String p_animTitle, int[] p_rotationOffsets)
	{
		frameCount = p_frameCount;
		animTitle = p_animTitle;
		rotationOffsets = p_rotationOffsets;
	}
	
	public int getFrameCount()
	{
		return frameCount;
	}
	
	public String getAnimTitle()
	{
		return animTitle;
	}

	public int[] getRotationOffsets()
	{
		return rotationOffsets;
	}
	
}
