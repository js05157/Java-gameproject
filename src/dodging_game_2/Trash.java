package dodging_game_2;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Trash extends Thread
{
	private Image trashImage = new ImageIcon(IntroScreen.class.getResource("/images/trashBasic.png")).getImage();
	private int x, y ;
	private String trashType;
	
	public Trash(int x, int y, String trashType)
	{
		this.x = x;
		this.y = y;
		this.trashType =trashType;
	}
	
	public void screenDraw(Graphics2D g)
	{
		if(trashType.equals("can"))
		{
			g.drawImage(trashImage, x, y, null);
		}
	}
	
	public void drop()
	{
		y += IntroScreen.TRASH_SPEED;

	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				drop();
				Thread.sleep(IntroScreen.SLEEP_TIME);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
	
}
