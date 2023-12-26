package dodging_game_2;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DodgingGame extends JFrame
{ 
	private Image screenImage;
	private Graphics screenGraphic;
	
	private JLabel menuBar = new JLabel(new ImageIcon(IntroScreen.class.getResource("/images/menuBar3.png")));
	
	private Image rankBoardImage = new ImageIcon(IntroScreen.class.getResource("/images/rankBoardImage.png")).getImage();
	private Image scoreImage = new ImageIcon(IntroScreen.class.getResource("/images/scoreImage.png")).getImage();
	private Image gameOverImage = new ImageIcon(IntroScreen.class.getResource("/images/gameOverImage.png")).getImage();
	private Image Background = new ImageIcon(IntroScreen.class.getResource("/images/ingameBackground.jpg")).getImage();
	private Image titleImage = new ImageIcon(IntroScreen.class.getResource("/images/titleImage.png")).getImage();
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(IntroScreen.class.getResource("/images/exitButtonEnterdImage.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(IntroScreen.class.getResource("/images/exitButtonBasicImage.png"));
	private ImageIcon soundButtonEnteredImage = new ImageIcon(IntroScreen.class.getResource("/images/soundButtonEnteredImage.png"));
	private ImageIcon soundButtonBasicImage = new ImageIcon(IntroScreen.class.getResource("/images/soundButtonBasicImage.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(IntroScreen.class.getResource("/images/startButtonBasicImage.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(IntroScreen.class.getResource("/images/startButtonEnteredImage.png"));
	private ImageIcon RestartButtonBasicImage = new ImageIcon(IntroScreen.class.getResource("/images/RestartButtonBasicImage.png"));
	private ImageIcon RestartButtonEnteredImage = new ImageIcon(IntroScreen.class.getResource("/images/RestartButtonEnteredImage.png"));
	private ImageIcon mainButtonBasicImage = new ImageIcon(IntroScreen.class.getResource("/images/mainButtonBasicImage.png"));
	private ImageIcon mainButtonEnteredImage = new ImageIcon(IntroScreen.class.getResource("/images/mainButtonEnteredImage.png"));
	private ImageIcon mainButtonBasicImage2 = new ImageIcon(IntroScreen.class.getResource("/images/mainButtonBasicImage2.png"));
	private ImageIcon mainButtonEnteredImage2 = new ImageIcon(IntroScreen.class.getResource("/images/mainButtonEnteredImage2.png"));
	private ImageIcon godModeButtonPressedImage = new ImageIcon(IntroScreen.class.getResource("/images/godModePressedImage.png"));
	private ImageIcon godModeButtonBasicImage = new ImageIcon(IntroScreen.class.getResource("/images/godModeImage.png"));
	private ImageIcon rankButtonBasicImage = new ImageIcon(IntroScreen.class.getResource("/images/rankButtonBasicImage.png"));
	private ImageIcon rankButtonEnteredImage = new ImageIcon(IntroScreen.class.getResource("/images/rankButtonEnteredImage.png"));
	private ImageIcon IntroButtonBasicImage = new ImageIcon(IntroScreen.class.getResource("/images/IntroButtonBasicImage.png"));
	
	private JButton soundButton = new JButton(soundButtonBasicImage);
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton RestartButton = new JButton(RestartButtonBasicImage);
	private JButton mainButton = new JButton(mainButtonBasicImage);
	private JButton mainButton2 = new JButton(mainButtonBasicImage2);
	private JButton godmodeButton = new JButton(godModeButtonBasicImage);
	private JButton rankButton = new JButton(rankButtonBasicImage);
	private JButton IntroButton = new JButton(IntroButtonBasicImage);
	
	private ArrayList<User> userList;
	private String name;
	private User user;
	
	public static Game game;
	
	
	private Music gameMusic;
	private boolean isplaymusic = true;
	private boolean isbuttonpressed = false;
	private int mouseX, mouseY;

	private boolean isMainScreen = true;
	private boolean isGameScreen = false;
	private boolean isGameOverScreen = false;
	private boolean isRankBoardScreen = false;
	
	private int scoreList [] = new int[IntroScreen.MAX_GAME_COUNT];
	
	private int gamecnt = -1;

	public DodgingGame(String name) {
		this.name = name;
		userList = new ArrayList<>();
		user = new User(name);
		userList.add(user);
		
		setFocusable(true);
		setUndecorated(true);
		setTitle("Dodging Game");
		setSize(IntroScreen.SCREEN_WIDTH, IntroScreen.SCREEN_HEIGHT);
		setResizable(false);// 사용자가 창크기를 조절할 수 없음
		setLocationRelativeTo(null);// 프로그램 실행 시 화면 중앙에 실행
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 창 종료시 프로그램 종료
		setVisible(true);// 프로그램 창을 출력
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		gameMusic = new Music("mainMusic.mp3", true);
		gameMusic.start();
		addKeyListener(new KeyListener());
		
		exitButton.setBounds(1240, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)// 마우스 올라가면 색바 뀌
			{
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 커서모양 바꾸기
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)// 왼쪽마우스만
				{
					System.exit(0);
				}
			}
		});
		add(exitButton);
	
		soundButton.setFocusable(false);//포커스방지
		soundButton.setBounds(1200, 0, 30, 30);
		soundButton.setBorderPainted(false);
		soundButton.setContentAreaFilled(false);
		soundButton.setFocusPainted(false);
		soundButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)// 마우스 올라가면 색바 뀌
			{
				soundButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 커서모양 바꾸기
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				soundButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e)
			{

				if (e.getButton() == MouseEvent.BUTTON1)// 왼쪽마우스
				{
					MusicController();
				}

			}
		});
		add(soundButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
		
		startButton.setBounds(490, 550, 300, 153);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)// 마우스 올라가면 색바 뀌
			{
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 커서모양 바꾸기
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();

			}

			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)// 왼쪽마우스만
				{
					Music startButtonPressedMusic = new Music("startButtonPressedMusic.mp3", false);
					startButtonPressedMusic.start();
					gameStart();
					
				}
			}
		});
		add(startButton);
		
		mainButton.setBounds(20, 50, 80, 41);
		mainButton.setBorderPainted(false);
		mainButton.setContentAreaFilled(false);
		mainButton.setFocusPainted(false);
		mainButton.setVisible(false);
		mainButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)// 마우스 올라가면 색바 뀌
			{
				mainButton.setIcon(mainButtonEnteredImage);
				mainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 커서모양 바꾸기
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();

			}

			public void mouseExited(MouseEvent e) {
				mainButton.setIcon(mainButtonBasicImage);
				mainButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) 
			   {
				if (e.getButton() == MouseEvent.BUTTON1)// 왼쪽마우스만
				{
					Music mainButtonPressedMusic = new Music("mainButtonPressedMusic.mp3", false);
					mainButtonPressedMusic.start();
					backMain();
				}
			}
		});
		add(mainButton);
		
		mainButton2.setBounds(320, 550, 300, 153);
		mainButton2.setBorderPainted(false);
		mainButton2.setContentAreaFilled(false);
		mainButton2.setFocusPainted(false);
		mainButton2.setVisible(false);
		mainButton2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)// 마우스 올라가면 색바 뀌
			{
				mainButton2.setIcon(mainButtonEnteredImage2);
				mainButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));// 커서모양 바꾸기
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();

			}

			public void mouseExited(MouseEvent e) {
				mainButton2.setIcon(mainButtonBasicImage2);
				mainButton2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) 
			   {
				if (e.getButton() == MouseEvent.BUTTON1)// 왼쪽마우스만
				{
					Music mainButtonPressedMusic = new Music("mainButtonPressedMusic.mp3", false);
					mainButtonPressedMusic.start();
					backMain();
				}
			}
		});
		add(mainButton2);
		
		RestartButton.setBounds(670, 550, 300, 153);
		RestartButton.setBorderPainted(false);
		RestartButton.setContentAreaFilled(false);
		RestartButton.setFocusPainted(false);
		RestartButton.setVisible(false);
		RestartButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)// 마우스 올라가면 색바뀌
			{
				RestartButton.setIcon(RestartButtonEnteredImage);
				RestartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 커서모양 바꾸기
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				RestartButton.setIcon(RestartButtonBasicImage);
				RestartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)// 왼쪽마우스만
				{
					Music startButtonPressedMusic = new Music("startButtonPressedMusic.mp3", false);
					startButtonPressedMusic.start();
					gameStart();
					
				}
			}
		});
		add(RestartButton);
		
		godmodeButton.setFocusable(false);
		godmodeButton.setBounds(367, 534, 35, 50);
		godmodeButton.setBorderPainted(false);
		godmodeButton.setContentAreaFilled(false);
		godmodeButton.setFocusPainted(false);
		godmodeButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				godmodeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 커서모양 바

			}

			public void mouseExited(MouseEvent e) {
				godmodeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) 
			   {
				if (e.getButton() == MouseEvent.BUTTON1)// 왼쪽마우스만
				{
					if(game.godmode)
					{
						godmodeButton.setIcon(godModeButtonBasicImage);
						game.godmode = false;
					}
					else
					{
						godmodeButton.setIcon(godModeButtonPressedImage);
						game.godmode = true;
					}
					Music godmodeButtonPressedMusic = new Music("CatgodmodeSound.mp3", false);
					godmodeButtonPressedMusic.start();
				}
			}
		});
        add(godmodeButton);
        
        rankButton.setFocusable(false);
        rankButton.setBounds(1150, 600, 50, 48);
        rankButton.setBorderPainted(false);
        rankButton.setContentAreaFilled(false);
        rankButton.setFocusPainted(false);
        rankButton.setVisible(true);
        rankButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)// 마우스 올라가면 색바 뀌
			{
				rankButton.setIcon(rankButtonEnteredImage);
				rankButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 커서모양 바꾸기
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				if(!isbuttonpressed)
				{
				rankButton.setIcon(rankButtonBasicImage);
				}
				rankButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)// 왼쪽마우스만
				{	
					Music rankButtonPressedMusic = new Music("startButtonPressedMusic.mp3", false);
					rankButtonPressedMusic.start();	
					
					if(!isbuttonpressed)
					{
						isbuttonpressed = true;
						rankButton.setIcon(rankButtonEnteredImage);	
						startButton.setVisible(false);
						rankBoard();
					}
					else
					{
						isbuttonpressed = false;
						rankButton.setIcon(rankButtonBasicImage);
						isMainScreen = true;
						isRankBoardScreen = false;
						startButton.setVisible(true);
					}
					
				}
			}
		});
		add(rankButton);
		
	}

	public void paint(Graphics g) {
		screenImage = createImage(IntroScreen.SCREEN_WIDTH, IntroScreen.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);

	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(Background, 0, 0, null);// 배경화면 출력
		if(isMainScreen) {
			g.drawImage(titleImage, 180, 30, null);
		}
		else if(isGameScreen)
		{
			game.screenDraw(g);
		}
		else if(isGameOverScreen)
		{
			g.drawImage(gameOverImage,380, 100, null);
			g.drawImage(scoreImage,380, 350, null);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.PLAIN, 100));
			g.drawString("" + user.getScore(), 860, 430);
		}
		else if(isRankBoardScreen)
		{
			g.drawImage(rankBoardImage,400, -50 , null);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.drawString("1.", 450, 220);
			g.drawString("2.", 450, 250);
			g.drawString("3.", 450, 280);
			g.drawString("4.", 450, 310);
			g.drawString("5.", 450, 340);
			g.drawString("6.", 450, 370);
			g.drawString("8.", 450, 400);
			g.drawString("9.", 450, 430);
			g.drawString("10.", 450, 460);
			g.drawString("11.", 450, 490);
			g.drawString("12.", 450, 520);
			g.drawString("13.", 450, 550);
			g.drawString("14.", 450, 580);
			g.drawString("15.", 450, 610);
			if(gamecnt >= 0)
			{
				g.drawString("" + user.getName(), 480, 220);
				g.drawString("" + user.gethighScore(), 750, 220);
			}
			
			
			
		}
		paintComponents(g);// 라벨, 버튼출력
		this.repaint();
	}
	
	public int getScoreList(int num)
	{
		
		return scoreList [num];
	}

	public void gameStart()
	{
		gamecnt++;
		isGameScreen = true;
		isMainScreen = false;
		isGameOverScreen = false;
		Background = new ImageIcon(IntroScreen.class.getResource("/images/ingameBackground.jpg")).getImage();
		startButton.setVisible(false);
		RestartButton.setVisible(false);
		rankButton.setVisible(false);
		IntroButton.setVisible(false);
		mainButton.setVisible(true);
		mainButton2.setVisible(false);
		godmodeButton.setVisible(true);
		setFocusable(true);
		gameMusic.close();
		gameMusic = new Music("ingameMusic.mp3", true);
		
		if(isplaymusic) gameMusic.start();
		game = new Game(this);
	}
	
	public void backMain()
	{
		isMainScreen = true;
		isGameScreen = false;
		isGameOverScreen = false;
		Background = new ImageIcon(IntroScreen.class.getResource("/images/ingameBackground.jpg")).getImage();
		startButton.setVisible(true);
		rankButton.setVisible(true);
		IntroButton.setVisible(true);
		RestartButton.setVisible(false);
		mainButton.setVisible(false);
		mainButton2.setVisible(false);
		godmodeButton.setVisible(false);
		godmodeButton.setIcon(godModeButtonBasicImage);
		game.godmode = false;
		gameMusic.close();
		gameMusic = new Music("mainMusic.mp3", true);
		try
		{
			Thread.sleep(200);
		}catch(InterruptedException ex)
		{
			ex.printStackTrace();
		}
		if(isplaymusic) gameMusic.start();
		game.close();
	}
	
	public void gameOver()
	{
		isMainScreen = false;
		isGameScreen = false;
		isGameOverScreen = true;
		mainButton.setVisible(false);
		RestartButton.setVisible(true);
		mainButton2.setVisible(true);
		godmodeButton.setVisible(false);
		scoreList[gamecnt] = game.getscore();
		user.setScore(game.getscore());
	}
	
	public void MusicController()
	{
		if ((!isplaymusic))// 노래가 off 상태래면
		{
			soundButton.setIcon(soundButtonBasicImage);
			if (isGameScreen)
				gameMusic = new Music("ingameMusic.mp3", true);
			else
				gameMusic = new Music("mainMusic.mp3", true);
			gameMusic.start();
			isplaymusic = true;
		} else if (isplaymusic)// 노래 on 상태라면
		{
			soundButton.setIcon(soundButtonEnteredImage);
			gameMusic.close();// 노래 중지
			isplaymusic = false;
		}
	}

	public void rankBoard()
	{
		isRankBoardScreen = true;
		isMainScreen = false;
		isGameScreen = false;
		isGameOverScreen = false;
		
		startButton.setVisible(false);
		
		
	}
	
	public String getName()
	{
		return name;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
