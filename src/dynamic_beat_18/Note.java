package dynamic_beat_18;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 280 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;
	// 현재 노트가 진행되는지의 여부에 대한 함수,
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
	// isProceeded는 사용자정의함수(사용자가설정한)아름 저채
		return proceeded;
	}
	
	public void close() {
	// 노크가 성공적으로 판정됬을때 사라지게끔 하는 함수라고 생각하면됨.	
		proceeded = false;
	}

	public Note(String noteType) {
		if(noteType.equals("A")) {
			x = 228;
		}
		else if(noteType.equals("S")) {
			x = 332;
		}
		else if(noteType.equals("D")) {
			x = 436;
		}
		else if(noteType.equals("Space")) {
			x = 540;
		}
		else if(noteType.equals("J")) {
			x = 744;
		}
		else if(noteType.equals("K")) {
			x = 848;
		}
		else if(noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
		
	}

	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space"))
		{
			g.drawImage(noteBasicImage, x, y, null);
			
		}
		else 
		{
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if (y > 620) {
			System.out.println("Miss");
			close();
		// 노트의 y좌표, 즉 y의 위치가 판정바 밑으로 가면 Miss를 출력한뒤 사라짐.	
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				// drop이란 함수를 이용해 y좌표 즉, 노트의 높이가 계속 떨어짐 무한반복으로.
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}else {
					interrupt();
					break;
				}
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}

	public String judge() {
		if (y>= 613) {
			System.out.println("Late");
			close();
			return "Late";
		}
		else if (y>= 600 ) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if (y>= 587) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if (y>= 573 ) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		}
		else if (y>= 565 ) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if (y>= 550 ) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if (y>= 535 ) {
			System.out.println("Early");
			close();
			return "Early";
		}
		return "None";
	}
	
	public int getY() {
		return y;
	}
}
