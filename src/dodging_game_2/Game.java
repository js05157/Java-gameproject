package dodging_game_2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Game extends Thread {
	
	private Image cat = new ImageIcon(IntroScreen.class.getResource("/images/Lcat.png")).getImage();
	
	private Image catHpImage1 = new ImageIcon(IntroScreen.class.getResource("/images/catHpImage.png")).getImage();
	private Image catHpImage2 = new ImageIcon(IntroScreen.class.getResource("/images/catHpImage.png")).getImage();
	private Image catHpImage3 = new ImageIcon(IntroScreen.class.getResource("/images/catHpImage.png")).getImage();
	

	
	
	
	
	
	private int catX = 640;
	private Music catSound;
	private int ismeow = 0;
	private int level = 1;
	private int beforeLevel = 0;
	private int score = 0;
	private long lastScoreIncreaseTime = System.currentTimeMillis();
	private DodgingGame dodgingGame;
	public boolean godmode = false;
	private int catHP = 3;
	private boolean catInvincible = false;
	private long catInvincibleStartTime;
	private int catdir; //1: 왼쪽 2: 오른쪽
	
	public Game(DodgingGame dodgingGame) {
		this.dodgingGame = dodgingGame;//
		start();
	}

	
	ArrayList<Trash> trashList = new ArrayList<Trash>();
	
	

	public void screenDraw(Graphics2D g) {
		if(catHP != 0)g.drawImage(cat, catX, 630, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("SCORE : " + score, 580, 70);
		g.setColor(Color.WHITE);
		g.setColor((score >= 90) ? Color.RED : Color.WHITE);
		g.drawString("LEVEL : " + level, 1150, 70);
		switch(catHP)
		{
			case 3 :
				g.drawImage(catHpImage1, 1100, 100, null);
				g.drawImage(catHpImage2, 1150, 100, null);
				g.drawImage(catHpImage3, 1200, 100, null);
				break;
			case 2 :
				g.drawImage(catHpImage1, 1100, 100, null);
				g.drawImage(catHpImage2, 1150, 100, null);
				
				break;
			case 1 :
				g.drawImage(catHpImage1, 1100, 100, null);
				break;
			case 0 :
				gameOver();
				
				
				
		}

		for (int i = 0; i < trashList.size(); i++) {
			Trash trash = trashList.get(i);
			trash.screenDraw(g);
			if (isCrash(catX, trash.getX(), trash.getY()));
				
				
			
		}

	}
	
	
	
	public void pressRight() {
		
		catdir = 2;
		if (catX + 15 > 1220) {
			catX = 1220;
			if (ismeow == 0) {
				catSound = new Music("CatSadSound.mp3", false);
				catSound.start();
				ismeow = 1;
			}
		} else {
			catX += 15;
		}
		if (catX == 1205)
			ismeow = 0;
		if(catInvincible)
		{
			cat = new ImageIcon(IntroScreen.class.getResource("/images/invincibleRcat.png")).getImage();
		}
		else
		{
		cat = new ImageIcon(IntroScreen.class.getResource("/images/Rcat.png")).getImage();
		}
	}

	public void pressLeft() {
		catdir = 1;
		if (catX - 15 <= 0) {
			catX = 0;
			if (ismeow == 0) {
				catSound = new Music("CatSadSound.mp3", false);
				catSound.start();
				ismeow = 1;
			}
		} else {
			catX -= 15;
		}
		if (catX == 15)
			ismeow = 0;
		if(catInvincible)
		{
			cat = new ImageIcon(IntroScreen.class.getResource("/images/invincibleLcat.png")).getImage();
		}
		else
		{
		cat = new ImageIcon(IntroScreen.class.getResource("/images/Lcat.png")).getImage();
		}
	}

	public void dropTrash() {

		Random random = new Random();
		int x = random.nextInt(1280); 
		Trash trash = new Trash(x, 0, "can");
		trash.start();
		trashList.add(trash);
	}

	public void run() {
		int sleepTime = 1000;
		try {
			while (true) {
				if(godmode)
				{
					if(catdir == 1)
					{
						cat = new ImageIcon(IntroScreen.class.getResource("/images/invincibleLcat.png")).getImage();
					}
					else if(catdir == 2)
					{
						cat = new ImageIcon(IntroScreen.class.getResource("/images/invincibleRcat.png")).getImage();
					}
				}
				dropTrash();
				
				
				long currentTime = System.currentTimeMillis();// 1초가 지나면 currentTime은 1000이됨, 그러면 if 문으로 들어가서 스코어 올리고,
																// lastScoreIncreaseTime에 1000을 저장함
				if (currentTime - lastScoreIncreaseTime >= 1000) { // 그리고 2초가 지나면 currentTime이 2000이 되어 2000 - 1000으로
																	// 조건성립. 반복
					increaseScore_or_Level();
					lastScoreIncreaseTime = currentTime;
				}
				
				if (!godmode && catInvincible && (currentTime - catInvincibleStartTime >= 1500)) {
		               	catInvincible = false;
		               	if(catdir == 1)
						{
							cat = new ImageIcon(IntroScreen.class.getResource("/images/Lcat.png")).getImage();
						}
						else if(catdir == 2)
						{
							cat = new ImageIcon(IntroScreen.class.getResource("/images/Rcat.png")).getImage();
						}
		            }
				
				// sleep 중에 인터럽트가 발생하면 InterruptedException이 발생함
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					// InterruptedException 처리 후 스레드 종료
					Thread.currentThread().interrupt(); // 현재 스레드를 다시 인터럽트 상태로 설정
					break;
				}
				if (isLevelUP()) {
					sleepTime = calculateSleepTime();
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private int calculateSleepTime() {

		int baseSleepTime = 1050;
		int levelMultiplier = 100;
		return baseSleepTime - (level * levelMultiplier);
	}

	private boolean isLevelUP() {
		if (level == beforeLevel) {
			level = beforeLevel + 1;
			return true;
		}
		return false;
	}

	private void increaseScore_or_Level() {
		score++;
		if (score >= 90)
			score++;
		if (score == 10)
			beforeLevel = 1;
		if (score == 20)
			beforeLevel = 2;
		if (score == 30)
			beforeLevel = 3;
		if (score == 40)
			beforeLevel = 4;
		if (score == 50)
			beforeLevel = 5;
		if (score == 60)
			beforeLevel = 6;
		if (score == 70)
			beforeLevel = 7;
		if (score == 80)
			beforeLevel = 8;
		if (score == 90)
			beforeLevel = 9;
	}

	private boolean isCrash(int catx1, int trashx, int trashy) {
		if (!isgodMode()) {
			
			int trashX1 = trashx -15;
			int trashX2 = trashx +5;
			int trashY1 = trashy - 16;
			int trashY2 = trashy + 16;
			int catX1 = catx1 - 30;
			int catX2 = catx1 + 30;
			int catY1 = 605;
			int catY2 = 655;
			
			if ((!catInvincible && catX1 < trashX2 && catX2 > trashX1) && (catY1 < trashY2 && catY2 > trashY1)) {
				
				catHP--;
				catSound = new Music("CatSadSound.mp3", false);
				catSound.start();
				catInvincible = true;
				
				if(catdir == 1)
				{
					cat = new ImageIcon(IntroScreen.class.getResource("/images/invincibleLcat.png")).getImage();
				}
				else if(catdir == 2)
				{
					cat = new ImageIcon(IntroScreen.class.getResource("/images/invincibleRcat.png")).getImage();
				}
				
				catInvincibleStartTime = System.currentTimeMillis();
				return true;
			}
		}
		return false;
	}

	public boolean isgodMode() 
	{
		if(godmode)
		{
			catInvincible = true;
			return true;
		}
		else
		{
			return false;
		}
	}

	private void gameOver() 
	{
		dodgingGame.gameOver();
		this.close();
	}

	public void close() {
		this.interrupt();
		try {
			join(); // 스레드가 종료될 때까지 대기
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getscore()
	{
		return this.score;
	}
}
