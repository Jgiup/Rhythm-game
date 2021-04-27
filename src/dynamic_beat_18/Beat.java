package dynamic_beat_18;

public class Beat {
// 박자타이밍과 노트종류에 대한 데이터를 담을 수 있는 클래스.
	private int time;
	private String noteName;
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	
	public Beat(int time, String noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
	}
}
