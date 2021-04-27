package dynamic_beat_18;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.PNG"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));

	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground (1).jpg")).getImage();
	// 초기화긴한데... 이미지쪽 초기화인듯...?;
	// background는 그저 변수이름 . 다만 뒤에 이미지 파일을 뭘 넣느냐에 따라 초기화되는게 다름.
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	// menuBar 라는 객체안에 png형식의 menuBar라는 이미지가 자리잡게됨. (초기화됨.)

	private JButton exitButton = new JButton(exitButtonBasicImage);
	// 기본 나가기 이미지는 Basic이미지가 될 것임.
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	private int mouseX, mouseY;
	// mouse의 x좌표와 y좌표값 초기화.

	private boolean isMainScreen = false;
	// 처음에는 메인화면이 아닌 시작화면이기 때문에 false값을 줌. 메인화면으로 갈시 True값으로 변경될 예정.
	private boolean isGameScreen = false;

	ArrayList<Track> trackList = new ArrayList<Track>();
	// ArrayList는 변수(트랙)들을 담을수 있는 하나의 만들어진 배열.
	private Image selectedImage;
	private Image titleImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0;

	public static Game game;

	public DynamicBeat() {
		trackList.add(new Track("OsakaTitleImage.png", "OsakaStartImage.png", "OsakaGameImage.png", "OSAKAHilite.mp3",
				"OSAKA_Fix.mp3", "Osaka - Elo"));
		trackList.add(new Track("TyokoTitleImage.png", "TyokoStartImage.png", "TyokoGameImage.jpg", "TyokoHilite.mp3",
				"Tyoko_Fix.mp3", "Tyoko - Giriboy"));
		
		setUndecorated(true);
		// 실행했을때 기본적으로 존재하고 있떤 메뉴바를 안보이게 해줌.
		setTitle("Dynamic Beat");
		// 게임이름이 Dynamic Beat인걸 의미
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// Main함수에서 정의한 값들이 해상도가 된다는 의미
		setResizable(false);
		// 만들어진 게임창을 사용자가 임의로 못바꿈.
		setLocationRelativeTo(null);
		// 만들어진 게임창이 정확히 정중앙에 뜨게끔 해줌.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 게임창을 종료했을때 프로그램도 같이종료하게해줌.
		// 이거 안만들면 게임창 종료해도 프로그램은 돌아감...
		setVisible(true);
		// 우리가 만든 게임창이 정상적으로 화면에 출력하게 해줌.
		setBackground(new Color(0, 0, 0, 0));
		// paintComponents함수가 실행될대 기존의 배경이 흰색으로 바뀌게해줌.
		setLayout(null);
		// 버튼이나 JLabel의 위치를 제대로 두게 해줌.

		addKeyListener(new KeyListener());

		introMusic.start();


		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		// 위3가지 함수들은 기존의존재하던 템플릿을 제거하고 사용자 지정 버튼을 사옹하기위해 사용하는 함수들.
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				// 버튼을 눌렀을때 Entered이미지로 바꿔준다는 의미.
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스 커서가 버튼이 클릭되었을때 손모양으로 바뀐다는 의미.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				// 버튼에서 마우스가 나왔을 때 Basic이미지로 바뀜.
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 원래상태로 ㅋ
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 버턴을 클릭하면 시스템(프로그램_게임) 종료.
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
					// 소리가 나오고 1초이따 종료.
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);
		// JFrame에 exitButton 추가.

		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterMain();
			}
		});
		add(startButton);

		quitButton.setBounds(40, 330, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);

				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);

		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// 왼쪽버튼이벤트
				selectedLeft();
			}
		});
		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// 오른쪽버튼이벤트
				selectedRight();
			}
		});
		add(rightButton);

		easyButton.setVisible(false);
		easyButton.setBounds(375, 620, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// 난이도쉬움이벤트
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);

		hardButton.setVisible(false);
		hardButton.setBounds(655, 620, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// 난이도어려움이벤트
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);

		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				backMain();
			}
		});
		add(backButton);

		menuBar.setBounds(0, 0, 1280, 30);
		// setBounds는 위치와 크기를 정해줌.
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Pressed이벤트가 발생했을 때, 즉 마우스를 눌렀을때 일어날 내용들.
				mouseX = e.getX();
				mouseY = e.getY();
				// 마우스를 늘렀을때 마우스의 x좌표와 y좌표를 얻어온다는 의미.
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// Dragged이벤트가 발생했을 때, 즉 마우스를 드래그했을때 일어날 내용들.
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				// 현재 스크린에 x , y 좌표를 가져옴.
				setLocation(x - mouseX, y - mouseY);
				// 현재 가져온 x , y좌표로 게임창을 바꿔줌.
			};
		});
		add(menuBar);
		// JFrame에 menuBar가 추가됬다는 의미.

	}

	public void paint(Graphics g) {
		// paint 함수는 JFrame을 상속받은 GUI(그래픽기반)게임에서 가장 첫번째로 화면을 그려주는 함수.
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// 내가 지정한 해상도 크기만큼의 이미지를 만든뒤 그걸 screenImage에 넣어줌.
		screenGraphic = screenImage.getGraphics();
		// screenImage를 이용해서 그래픽 객체를 얻어옴(?)
		screenDraw((Graphics2D) screenGraphic);
		// screenDraw라는 함수를 이용해 어떠한 그림을 그려줌 (?)
		g.drawImage(screenImage, 0, 0, null);
		// 게임창에 screenImage가 그려진단거임. ( 0,0에 위치시킨다는 말 )
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		// background이미지를 어딘가(screenImage)에 그려주겠다는 의미.
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 150, null);
			g.drawImage(titleImage, 340, -80, null);
		}
		if (isGameScreen) {
			game.screenDraw(g);
		}
		paintComponents(g);
		// paintComponents 함수는 메뉴바는 항상 고정되어있고 항상 존재할수밖에 없는 것이므로 paintsComponents이용.
		try {
			Thread.sleep(5);
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.repaint();
		// 이미지를 계속 갱신한다는 뜼.
	}

	public void selectedTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		// titleImage변수에 선택된 음악의 제목부분이미지를 가져와서 초기화해주는 거.
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectedLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectedTrack(nowSelected);
	}

	public void selectedRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectedTrack(nowSelected);
	}

	public void gameStart(int nowselected, String difficulty) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		// 이함수는 포커스가 안맞춰질수도 있는 것을 방지.
		game = new Game(trackList.get(nowSelected).gettitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);
	}

	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		backButton.setVisible(false);
		selectedTrack(nowSelected);
		isGameScreen = false;
		game.close();
	}

	public void enterMain() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectedTrack(0);
	}

}
