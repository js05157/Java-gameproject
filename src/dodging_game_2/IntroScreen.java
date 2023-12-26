package dodging_game_2;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class IntroScreen extends JFrame {
	public static final int MAX_GAME_COUNT = 100;
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int TRASH_SPEED = 5;
	public static final int SLEEP_TIME = 10;
	
	private int mouseX, mouseY;

	private Image screenImage;
	private Graphics screenGraphic;

	private JTextField nameTextField;
	private JLabel menuBar = new JLabel(new ImageIcon(IntroScreen.class.getResource("/images/menuBar3.png")));
	private Image Background = new ImageIcon(IntroScreen.class.getResource("/images/introBackground.jpg")).getImage();
	private Image NameMessage = new ImageIcon(IntroScreen.class.getResource("/images/nameMessage.png")).getImage();
	private Image chooseMessage = new ImageIcon(IntroScreen.class.getResource("/images/chooseMessage.png")).getImage();
	private ImageIcon xbuttonImage = new ImageIcon(IntroScreen.class.getResource("/images/Xbutton.png"));
	private ImageIcon tetrisImageBasicImage = new ImageIcon(
			IntroScreen.class.getResource("/images/tetrisBasicImage.png"));
	private ImageIcon tetrisImageEnteredImage = new ImageIcon(
			IntroScreen.class.getResource("/images/tetrisBasicImage.png"));
	private ImageIcon dodgeImageBasicImage = new ImageIcon(
			IntroScreen.class.getResource("/images/dodgeBasicImage.png"));
	private ImageIcon dodgeImageEnteredImage = new ImageIcon(
			IntroScreen.class.getResource("/images/dodgeBasicImage.png"));

	private JButton xbutton = new JButton(xbuttonImage);
	private JButton tetrisbutton = new JButton(tetrisImageBasicImage);
	private JButton dodgebutton = new JButton(dodgeImageBasicImage);

	private Music gameMusic;

	private int Screen = 1;
	private String playerName;

	public IntroScreen() {

		setUndecorated(true);
		setTitle("Intro Screen");
		setSize(IntroScreen.SCREEN_WIDTH, IntroScreen.SCREEN_HEIGHT);
		setResizable(false);// 사용자가 창크기를 조절할 수 없음
		setLocationRelativeTo(null);// 프로그램 실행 시 화면 중앙에 실행
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 창 종료시 프로그램 종료
		setVisible(true);// 프로그램 창을 출력
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		gameMusic = new Music("introMusic.mp3", true);
		gameMusic.start();

		tetrisbutton.setFocusable(false);
		tetrisbutton.setBounds(455, 250, 150, 150);
		tetrisbutton.setBorderPainted(false);
		tetrisbutton.setContentAreaFilled(false);
		tetrisbutton.setFocusPainted(false);
		tetrisbutton.setVisible(false);
		tetrisbutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)// 마우스 올라가면 색바 뀌
			{
				tetrisbutton.setIcon(tetrisImageEnteredImage);
				tetrisbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 커서모양 바꾸기
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				tetrisbutton.setIcon(tetrisImageBasicImage);
				tetrisbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)// 왼쪽마우스만
				{
					System.exit(0);
				}
			}
		});
		add(tetrisbutton);

		dodgebutton.setFocusable(false);// 포커스방지
		dodgebutton.setBounds(675, 250, 150, 150);
		dodgebutton.setBorderPainted(false);
		dodgebutton.setContentAreaFilled(false);
		dodgebutton.setFocusPainted(false);
		dodgebutton.setVisible(false);
		dodgebutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				dodgebutton.setIcon(dodgeImageBasicImage);
				dodgebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 커서모양 바꾸기
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				dodgebutton.setIcon(dodgeImageEnteredImage);
				dodgebutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)// 왼쪽마우스만
				{
					setVisible(false);
					new DodgingGame(playerName);
					gameMusic.close();
				}
			}
		});
		add(dodgebutton);

		nameTextField = new JTextField();
		nameTextField.setBounds(540, 300, 200, 30);
		nameTextField.setFont(new Font("Arial", Font.PLAIN, 15));
		nameTextField.setHorizontalAlignment(JTextField.CENTER);
		nameTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				playerName = nameTextField.getText();
				
				if (Screen == 1) {
					nameTextField.setVisible(false);
					tetrisbutton.setVisible(true);
					dodgebutton.setVisible(true);
					
				}

				Screen = 2;
			}

		});
		add(nameTextField);

		xbutton.setBounds(1240, 0, 30, 30);
		xbutton.setBorderPainted(false);
		xbutton.setContentAreaFilled(false);
		xbutton.setFocusPainted(false);
		xbutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)// 마우스 올라가면 색바 뀌
			{
				xbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 커서모양 바꾸기
			}

			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)// 왼쪽마우스만
				{
					System.exit(0);
				}
			}
		});
		add(xbutton);

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

	}

	public void paint(Graphics g) {
		screenImage = createImage(IntroScreen.SCREEN_WIDTH, IntroScreen.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);

	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(Background, 0, 0, null);// 배경화면 출력
		if (Screen == 1) {
			g.drawImage(NameMessage, 420, 200, null);
		} else {
			g.drawImage(chooseMessage, 420, 200, null);
		}
		paintComponents(g);// 라벨, 버튼출력
		this.repaint();
	}
	
	
	public void playMusic() {
		gameMusic = new Music("introMusic.mp3", true);
		gameMusic.start();
    }

}
