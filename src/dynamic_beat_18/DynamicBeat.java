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
	// �ʱ�ȭ���ѵ�... �̹����� �ʱ�ȭ�ε�...?;
	// background�� ���� �����̸� . �ٸ� �ڿ� �̹��� ������ �� �ִ��Ŀ� ���� �ʱ�ȭ�Ǵ°� �ٸ�.
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	// menuBar ��� ��ü�ȿ� png������ menuBar��� �̹����� �ڸ���Ե�. (�ʱ�ȭ��.)

	private JButton exitButton = new JButton(exitButtonBasicImage);
	// �⺻ ������ �̹����� Basic�̹����� �� ����.
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	private int mouseX, mouseY;
	// mouse�� x��ǥ�� y��ǥ�� �ʱ�ȭ.

	private boolean isMainScreen = false;
	// ó������ ����ȭ���� �ƴ� ����ȭ���̱� ������ false���� ��. ����ȭ������ ���� True������ ����� ����.
	private boolean isGameScreen = false;

	ArrayList<Track> trackList = new ArrayList<Track>();
	// ArrayList�� ����(Ʈ��)���� ������ �ִ� �ϳ��� ������� �迭.
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
		// ���������� �⺻������ �����ϰ� �ֶ� �޴��ٸ� �Ⱥ��̰� ����.
		setTitle("Dynamic Beat");
		// �����̸��� Dynamic Beat�ΰ� �ǹ�
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// Main�Լ����� ������ ������ �ػ󵵰� �ȴٴ� �ǹ�
		setResizable(false);
		// ������� ����â�� ����ڰ� ���Ƿ� ���ٲ�.
		setLocationRelativeTo(null);
		// ������� ����â�� ��Ȯ�� ���߾ӿ� �߰Բ� ����.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ����â�� ���������� ���α׷��� ���������ϰ�����.
		// �̰� �ȸ���� ����â �����ص� ���α׷��� ���ư�...
		setVisible(true);
		// �츮�� ���� ����â�� ���������� ȭ�鿡 ����ϰ� ����.
		setBackground(new Color(0, 0, 0, 0));
		// paintComponents�Լ��� ����ɴ� ������ ����� ������� �ٲ������.
		setLayout(null);
		// ��ư�̳� JLabel�� ��ġ�� ����� �ΰ� ����.

		addKeyListener(new KeyListener());

		introMusic.start();


		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		// ��3���� �Լ����� �����������ϴ� ���ø��� �����ϰ� ����� ���� ��ư�� ����ϱ����� ����ϴ� �Լ���.
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				// ��ư�� �������� Entered�̹����� �ٲ��شٴ� �ǹ�.
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺 Ŀ���� ��ư�� Ŭ���Ǿ����� �ո������ �ٲ�ٴ� �ǹ�.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				// ��ư���� ���콺�� ������ �� Basic�̹����� �ٲ�.
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// �������·� ��
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// ������ Ŭ���ϸ� �ý���(���α׷�_����) ����.
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
					// �Ҹ��� ������ 1���̵� ����.
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);
		// JFrame�� exitButton �߰�.

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
				// ���ʹ�ư�̺�Ʈ
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
				// �����ʹ�ư�̺�Ʈ
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
				// ���̵������̺�Ʈ
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
				// ���̵�������̺�Ʈ
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
		// setBounds�� ��ġ�� ũ�⸦ ������.
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Pressed�̺�Ʈ�� �߻����� ��, �� ���콺�� �������� �Ͼ �����.
				mouseX = e.getX();
				mouseY = e.getY();
				// ���콺�� �÷����� ���콺�� x��ǥ�� y��ǥ�� ���´ٴ� �ǹ�.
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// Dragged�̺�Ʈ�� �߻����� ��, �� ���콺�� �巡�������� �Ͼ �����.
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				// ���� ��ũ���� x , y ��ǥ�� ������.
				setLocation(x - mouseX, y - mouseY);
				// ���� ������ x , y��ǥ�� ����â�� �ٲ���.
			};
		});
		add(menuBar);
		// JFrame�� menuBar�� �߰���ٴ� �ǹ�.

	}

	public void paint(Graphics g) {
		// paint �Լ��� JFrame�� ��ӹ��� GUI(�׷��ȱ��)���ӿ��� ���� ù��°�� ȭ���� �׷��ִ� �Լ�.
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// ���� ������ �ػ� ũ�⸸ŭ�� �̹����� ����� �װ� screenImage�� �־���.
		screenGraphic = screenImage.getGraphics();
		// screenImage�� �̿��ؼ� �׷��� ��ü�� ����(?)
		screenDraw((Graphics2D) screenGraphic);
		// screenDraw��� �Լ��� �̿��� ��� �׸��� �׷��� (?)
		g.drawImage(screenImage, 0, 0, null);
		// ����â�� screenImage�� �׷����ܰ���. ( 0,0�� ��ġ��Ų�ٴ� �� )
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		// background�̹����� ���(screenImage)�� �׷��ְڴٴ� �ǹ�.
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 150, null);
			g.drawImage(titleImage, 340, -80, null);
		}
		if (isGameScreen) {
			game.screenDraw(g);
		}
		paintComponents(g);
		// paintComponents �Լ��� �޴��ٴ� �׻� �����Ǿ��ְ� �׻� �����Ҽ��ۿ� ���� ���̹Ƿ� paintsComponents�̿�.
		try {
			Thread.sleep(5);
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.repaint();
		// �̹����� ��� �����Ѵٴ� ��.
	}

	public void selectedTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		// titleImage������ ���õ� ������ ����κ��̹����� �����ͼ� �ʱ�ȭ���ִ� ��.
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
		// ���Լ��� ��Ŀ���� �ȸ��������� �ִ� ���� ����.
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
