package dynamic_beat_18;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image judgementImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image Plare;
	private Image judgeImage;
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	private Image keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	private Image keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}

// 하나의 게임이 하나의 단위로써 동작할거기때문에 쓰레드 선언.
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementImage, 0, 580, null);
		for(int i = 0; i< noteList.size(); i++)
		{
			Note note = noteList.get(i);
			if(note.getY() > 620 ) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
			}
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}
			else {
				note.screenDraw(g);
			}
		}
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// 내가 설정한 폰트떄문에 글자 화질이 나가는 상황. 글자 화질 안나가게 해주는 라이브러리.
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawImage(keyPadSImage, 228, 580, null);
		g.drawImage(keyPadDImage, 332, 580, null);
		g.drawImage(keyPadFImage, 436, 580, null);
		g.drawImage(keyPadSpace1Image, 540, 580, null);
		g.drawImage(keyPadSpace2Image, 640, 580, null);
		g.drawImage(keyPadJImage, 744, 580, null);
		g.drawImage(keyPadKImage, 848, 580, null);
		g.drawImage(keyPadLImage, 952, 580, null);
		g.drawString("A", 270, 609);
		g.drawString("S", 374, 609);
		g.drawString("D", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("000000", 565, 702);
		g.drawImage(Plare, 510, 400, null);
		g.drawImage(judgeImage, 460, 400, null);
		
	}

	public void pressA() {
		judge("A");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPressedImage.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void pressS() {
		judge("S");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPressedImage.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void pressD() {
		judge("D");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPressedImage.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPressedImage.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPressedImage.png")).getImage();
		new Music("drumBig1.mp3", false).start();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPressedImage.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPressedImage.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPressedImage.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseA() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	}

	public void releaseS() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	}

	public void releaseD() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	}

	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyBasicImage.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes(this.titleName);

	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	public void dropNotes(String titleName) {
		Beat[] beats = null;
		if(titleName.equals("Osaka - Elo") && difficulty.equals("Easy")) {
		// Elo의 Osaka 재생.
			int startTime = 3200 - Main.REACH_TIME * 1000;
			int gap = 200;
			beats = new Beat[] {
					new Beat(startTime, "J"),
					new Beat(startTime + gap * 2,"K"),
					new Beat(startTime + gap * 3 ,"L"),
					new Beat(startTime + gap * 10 ,"A"),
					new Beat(startTime + gap * 12 ,"S"),
					new Beat(startTime + gap * 12 +150,"J"),
			};
		} else if(titleName.equals("Osaka - Elo") && difficulty.equals("Hard")) {
		// Elo의 Osaka 재생.
			int startTime = 14600 - Main.REACH_TIME * 1000;
			int gap = 200;
			beats = new Beat[] {
					new Beat(startTime, "J"),
					new Beat(startTime + gap * 2,"K"),
					new Beat(startTime + gap * 3 ,"L"),
					new Beat(startTime + gap * 10 ,"A"),
					new Beat(startTime + gap * 12 ,"S"),
					new Beat(startTime + gap * 12 +150,"J"),
			};
		}
		else if(titleName.equals("Tyoko - Giriboy") && difficulty.equals("Easy")) {
			int startTime = 3200 - Main.REACH_TIME * 1000;
			int gap = 300;
			int newgap = 265;
			int halfgap = 150;
			int smallgap = 75;
			beats = new Beat[] {
					new Beat(startTime , "J"),
					new Beat(startTime + gap ,"K"),
					new Beat(startTime + gap * 2  ,"L"),
					new Beat(startTime + gap * 4 + halfgap  ,"L"),
					new Beat(startTime + gap * 6 + smallgap  ,"K"),
					new Beat(startTime + gap * 7 + smallgap ,"J"),
					new Beat(startTime + gap * 8 + smallgap ,"D"),
					new Beat(startTime + gap * 10 ,"J"),
					new Beat(startTime + gap * 12 - halfgap , "D"),
					new Beat(startTime + gap * 14 ,"L"),
					new Beat(startTime + gap * 15 ,"K"),
					new Beat(startTime + gap * 16 ,"J"),
					new Beat(startTime + gap * 18 ,"L"),
					new Beat(startTime + gap * 19 ,"J"),
					new Beat(startTime + gap * 21 - halfgap ,"K"),
					new Beat(startTime + gap * 22 + smallgap ,"D"),
					new Beat(startTime + gap * 26 - halfgap,"Space"),
					new Beat(startTime + gap * 28 + smallgap , "J"),
					new Beat(startTime + gap * 29 + smallgap ,"K"),
					new Beat(startTime + gap * 30 + smallgap * 2 ,"L"),
					new Beat(startTime + gap * 32 + smallgap * 3  ,"L"),
					new Beat(startTime + gap * 34 + smallgap  ,"K"),
					new Beat(startTime + gap * 35 + smallgap ,"J"),
					new Beat(startTime + gap * 36 + smallgap ,"D"),
					new Beat(startTime + gap * 38 ,"J"),
					new Beat(startTime + gap * 40, "D"),
					new Beat(startTime + gap * 42 + smallgap, "J"),
					new Beat(startTime + gap * 43 + smallgap, "K"),
					new Beat(startTime + gap * 44 + smallgap, "J"),
					new Beat(startTime + gap * 46, "D"),
					new Beat(startTime + gap * 47 + smallgap , "J"),
					new Beat(startTime + gap * 49 - smallgap * 2, "K"),
					new Beat(startTime + gap * 51 - smallgap * 2 , "J"),
					new Beat(startTime + gap * 56 + halfgap , "Space"),
					new Beat(startTime + gap * 57 , "Space"),
					new Beat(startTime + gap * 58 + newgap - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 3 - smallgap * 2, "Space"), 
					new Beat(startTime + gap * 58 + newgap * 4, "J"),
					new Beat(startTime + gap * 58 + newgap * 5 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 5 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 7 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 9 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 11 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 12 , "J"),
					new Beat(startTime + gap * 58 + newgap * 13 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 13 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 15 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 17 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 19 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 20 , "J"),
					new Beat(startTime + gap * 58 + newgap * 21 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 21 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 23 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 25 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 27 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 28, "J"),
					new Beat(startTime + gap * 58 + newgap * 29 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 29 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 31 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 33 - smallgap * 2, "Space"), 
					new Beat(startTime + gap * 58 + newgap * 35 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 36 , "J"),
					new Beat(startTime + gap * 58 + newgap * 37 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 37 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 39 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 41 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 43 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 44 , "J"),
					new Beat(startTime + gap * 58 + newgap * 45 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 45 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 47 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 49 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 51 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 52 , "J"),
					new Beat(startTime + gap * 58 + newgap * 53 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 53 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 55 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 57 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 59 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 60 , "J"),
					new Beat(startTime + gap * 58 + newgap * 61 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 61 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 63 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 65 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 67 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 68 , "J"),
					new Beat(startTime + gap * 58 + newgap * 69 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 69 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 71 - smallgap, "S"),	
					new Beat(startTime + gap * 58 + newgap * 73 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 75 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 76 , "J"),
					new Beat(startTime + gap * 58 + newgap * 77 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 77 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 79 - smallgap, "S"),	
					new Beat(startTime + gap * 58 + newgap * 81 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 83 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 84 , "J"),
					new Beat(startTime + gap * 58 + newgap * 85 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 85 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 87 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 89 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 91 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 92 , "J"),
					new Beat(startTime + gap * 58 + newgap * 93 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 93 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 95 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 97 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 99 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 100 ,"J"),
					new Beat(startTime + gap * 58 + newgap * 101 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 101 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 103 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 105 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 107 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 108, "J"),
					new Beat(startTime + gap * 58 + newgap * 109 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 109 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 111 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 113 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 115 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 116, "J"),
					new Beat(startTime + gap * 58 + newgap * 117 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 117 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 119 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 121 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 123 - smallgap * 2, "Space"),
					new Beat(startTime + gap * 58 + newgap * 124 , "J"),
					new Beat(startTime + gap * 58 + newgap * 125 + smallgap, "K"),
					new Beat(startTime + gap * 58 + newgap * 125 + halfgap, "L"),
					new Beat(startTime + gap * 58 + newgap * 127 - smallgap, "S"),
					new Beat(startTime + gap * 58 + newgap * 129, "Space"),
					new Beat(startTime + gap * 59 + newgap * 129+ smallgap*2, "Space"),
					new Beat(startTime + gap * 61 + newgap * 129 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 130 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 131 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 132 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 133 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 134 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 135 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 136 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 137 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 138 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 139 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 140 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 141 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 142 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 143 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 144 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 145 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 146 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 147 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 148 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 149 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 150 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 151 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 152 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 153 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 154 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 155 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 156 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 157 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 157 + smallgap *2, "Space"),
					new Beat(startTime + gap * 61 + newgap * 158 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 159 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 160 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 161 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 162 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 163 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 164 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 165 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 165 + smallgap *2, "Space"),
					new Beat(startTime + gap * 61 + newgap * 166 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 167 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 168 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 169 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 170 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 171 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 172 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 173 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 173 + smallgap *2, "Space"),
					new Beat(startTime + gap * 61 + newgap * 174 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 175 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 176 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 177 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 177 + smallgap *2, "Space"),
					new Beat(startTime + gap * 61 + newgap * 178 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 179 + smallgap *2, "K"), 	
					new Beat(startTime + gap * 61 + newgap * 180 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 181 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 181 + smallgap *2, "Space"),
					new Beat(startTime + gap * 61 + newgap * 182 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 183 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 184 + smallgap *2, "L"),
					new Beat(startTime + gap * 61 + newgap * 185 + smallgap *2, "Space"),					
					new Beat(startTime + gap * 61 + newgap * 187 + smallgap *2, "D"),
					new Beat(startTime + gap * 61 + newgap * 187 + smallgap *2, "Space"),
					new Beat(startTime + gap * 61 + newgap * 187 + smallgap *2, "J"),
					new Beat(startTime + gap * 61 + newgap * 189 + smallgap *2, "A"),
					new Beat(startTime + gap * 61 + newgap * 189 + smallgap *2, "S"),
					new Beat(startTime + gap * 61 + newgap * 189 + smallgap *2, "K"),
					new Beat(startTime + gap * 61 + newgap * 189 + smallgap *2, "L"),
					new Beat(startTime + gap * 71 + newgap * 191 + smallgap *2, "A"),
					new Beat(startTime + gap * 71 + newgap * 192 + smallgap *2, "A"),
					new Beat(startTime + gap * 71 + newgap * 193 + smallgap *2, "D"),
					new Beat(startTime + gap * 74 + newgap * 191 + smallgap *3, "S"),
					new Beat(startTime + gap * 74 + newgap * 192 + smallgap *3, "S"),
					new Beat(startTime + gap * 74 + newgap * 193 + smallgap *3, "J"),
					new Beat(startTime + gap * 77 + newgap * 191 + smallgap *4, "D"),
					new Beat(startTime + gap * 77 + newgap * 192 + smallgap *4, "D"),
					new Beat(startTime + gap * 77 + newgap * 193 + smallgap *4, "K"),
					new Beat(startTime + gap * 79 + newgap * 194 + smallgap *2, "Space"),
					new Beat(startTime + gap * 80 + newgap * 195 + smallgap *2, "Space"),
					new Beat(startTime + gap * 87 + newgap * 189 + smallgap *3, "D"),
					new Beat(startTime + gap * 87 + newgap * 189 + smallgap *3, "Space"),
					new Beat(startTime + gap * 87 + newgap * 189 + smallgap *3, "J"),
					new Beat(startTime + gap * 87 + newgap * 191 + smallgap *2, "A"),
					new Beat(startTime + gap * 87 + newgap * 191 + smallgap *2, "S"),
					new Beat(startTime + gap * 87 + newgap * 191 + smallgap *2, "K"),
					new Beat(startTime + gap * 87 + newgap * 191 + smallgap *2, "L"),
					new Beat(startTime + gap * 99 + newgap * 191 + smallgap *3, "A"),
					new Beat(startTime + gap * 99 + newgap * 192 + smallgap *3, "A"),
					new Beat(startTime + gap * 99 + newgap * 193 + smallgap *3, "D"),
					new Beat(startTime + gap * 102 + newgap * 191 + smallgap *4, "S"),
					new Beat(startTime + gap * 102 + newgap * 192 + smallgap *4, "S"),
					new Beat(startTime + gap * 102 + newgap * 193 + smallgap *4, "J"),
					new Beat(startTime + gap * 105 + newgap * 191 + smallgap *5, "D"),
					new Beat(startTime + gap * 105 + newgap * 192 + smallgap *5, "D"),
					new Beat(startTime + gap * 105 + newgap * 193 + smallgap *5, "K"),
					new Beat(startTime + gap * 107 + newgap * 195 , "Space"),
					new Beat(startTime + gap * 108 + newgap * 196 , "Space"),
					new Beat(startTime + gap * 115 + newgap * 190 + smallgap *2, "D"),
					new Beat(startTime + gap * 115 + newgap * 190 + smallgap *2, "Space"),
					new Beat(startTime + gap * 115 + newgap * 190 + smallgap *2, "J"),
					new Beat(startTime + gap * 115 + newgap * 192 + smallgap , "A"),
					new Beat(startTime + gap * 115 + newgap * 192 + smallgap , "S"),
					new Beat(startTime + gap * 115 + newgap * 192 + smallgap , "K"),
					new Beat(startTime + gap * 115 + newgap * 192 + smallgap , "L"),
					
					//여기부터 이상.
					new Beat(startTime + gap * 127 + newgap * 192 + smallgap , "D"),
					new Beat(startTime + gap * 128 + newgap * 192 , "S"),
					new Beat(startTime + gap * 129 + newgap * 192 - smallgap , "A"),
					new Beat(startTime + gap * 145 + newgap * 190 + smallgap * 2, "D"),
					new Beat(startTime + gap * 145 + newgap * 190 + smallgap * 2, "Space"),
					new Beat(startTime + gap * 145 + newgap * 190 + smallgap * 2, "J"),
					new Beat(startTime + gap * 145 + newgap * 191 + smallgap , "A"),
					new Beat(startTime + gap * 145 + newgap * 191 + smallgap , "S"),
					new Beat(startTime + gap * 145 + newgap * 191 + smallgap , "K"),
					new Beat(startTime + gap * 145 + newgap * 191 + smallgap , "L"),
					
					
					
					
			};
		} else if (titleName.equals("Tyoko - Giriboy") && difficulty.equals("Hard")) {
			int startTime = 3200 - Main.REACH_TIME * 1000;
			int gap = 300;
			int newgap = 265;
			int halfgap = 150;
			int smallgap = 75;
			beats = new Beat[] {
					
			};
		}
		int i= 0;
		gameMusic.start();
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped){
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void judge(String input) {
		for(int i =0; i< noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
			
		}	
	}

	public void judgeEvent(String judge) {
		if(!judge.equals("None")) {
			Plare = new ImageIcon(Main.class.getResource("../images/Plare.png")).getImage();
		}
		
		if(judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
		}
		else if(judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
		}
		else if(judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
		}
		else if(judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
		}
		else if(judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
		}
		else if(judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
		}
	}
	
}
