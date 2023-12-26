package dodging_game_2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter 
{
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			DodgingGame.game.pressLeft();
		}
		else if(e.getKeyCode() == KeyEvent.VK_A)
		{
			DodgingGame.game.pressLeft();
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			DodgingGame.game.pressRight();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			DodgingGame.game.pressRight();
		}
		
	}
	
	public void keyRealesed(KeyEvent e)
	{
		
	}
	
}

