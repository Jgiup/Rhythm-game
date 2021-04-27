package dynamic_beat_18;


public class Main {

	
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 3;
	// 노트가 떨어지는 시간
	public static final int SLEEP_TIME = 10;
	// 노트가 떨어지는 간격의 시간.
	public static final int REACH_TIME = 1;
	
	
	public static void main(String[] args) {
 
		new DynamicBeat();
	}

}
