package dynamic_beat_18;

public class Track {
	// �ϳ��� � ���� ������ ��� Ŭ����.
	private String titleImage; // ����κ��̹���
	private String startImage; // ���� ���� â ǥ���̹���
	private String gameImage; // �ش� ���� �������� �� ǥ�� �̹���
	private String startMusic; // ���� ���� â ����
	private String GameMusic; // �ش���� ���������� ����
	private String titleName; // �� ����

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public String getStartImage() {
		return startImage;
	}

	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}

	public String getGameImage() {
		return gameImage;
	}

	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}

	public String getStartMusic() {
		return startMusic;
	}

	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}

	public String getGameMusic() {
		return GameMusic;
	}

	public void setGameMusic(String gameMusic) {
		GameMusic = gameMusic;
	}

	public String gettitleName() {
		return titleName;
	}

	public void settitleName(String titleName) {
		titleName = titleName;
	}

	public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic,
			String titleName) {
		super();
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.GameMusic = gameMusic;
		this.titleName = titleName;
	}

}
