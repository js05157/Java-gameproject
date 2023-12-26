package dodging_game_2;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread
{
	private Player player;
	private boolean isLoop;//노래 무한반복
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	public Music(String name, boolean isLoop)
	{
		try
		{
			this.isLoop = isLoop;
			file = new File(IntroScreen.class.getResource("/music/" + name).toURI());//toURL함수로 음악파일의 위치를 가져와 해당이름의 파일을 실행
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);//파일을 버퍼에 담아서 읽어온다
			player = new Player(bis);//해당 파일을 담는다
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void close()//음악을 종료
	{
		isLoop = false;
		player.close();
		this.interrupt();//스레드를 중지
	}
	
	public void run()//오버라이딩
	{
		try
		{
			do
			{
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop);//isLoop가 참이면 반복
		} 
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}







