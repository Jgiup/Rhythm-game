package dynamic_beat_18;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Timer;

import javazoom.jl.player.Player;

public class Music extends Thread {

	private Player player;
	// Player함수는 JLayer때문에 생긴 라이브러리 함수.
	private boolean isloop;
	// isloop변수는 현재 곡이 무한반복인지 한번만 실행되고 꺼지는건지 알려주는 변수
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isloop) {
		// 곡이름 , 무한반복인지 여부
		try {
			this.isloop = isloop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			// music이라는 파일안에있는 해당이름의 파일을 실행. toURI로 해당파일위치가져옴.
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			// 해당파일을 버퍼에 담아서 읽어올수있또록 해줌(?)
			player = new Player(bis);
			// 해당파일을 담을수 있또록 해줌.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public int getTime() {
		// getTime은 현재 실행되고 있는 음악이 어떤위치에서 실행되는지 알려줌.
		// 추후 리듬게임에서 노트를 떨어트릴때 0.001초까지 분석하여 맞춰줌!
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() {
		// close함수는 언제 실행되고 있건 간에 종료될수있도록 해주는 함수.
		isloop = false;
		player.close();
		this.interrupt();
		// interrupt는 해당 쓰레드를 중지상태로 만들어주는 역할.
	}

	
	
	@Override
	public void run() {
		// run 함수는 쓰레드를 상속받으면 무조건 선언해야됨
		try {
			do {
				player.play();
				// 곡실행
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isloop);
			// isloop의 값이 true라며ㅓㄴ 무한반복
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
