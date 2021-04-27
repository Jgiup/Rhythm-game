package dynamic_beat_18;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Timer;

import javazoom.jl.player.Player;

public class Music extends Thread {

	private Player player;
	// Player�Լ��� JLayer������ ���� ���̺귯�� �Լ�.
	private boolean isloop;
	// isloop������ ���� ���� ���ѹݺ����� �ѹ��� ����ǰ� �����°��� �˷��ִ� ����
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isloop) {
		// ���̸� , ���ѹݺ����� ����
		try {
			this.isloop = isloop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			// music�̶�� ���Ͼȿ��ִ� �ش��̸��� ������ ����. toURI�� �ش�������ġ������.
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			// �ش������� ���ۿ� ��Ƽ� �о�ü��ֶǷ� ����(?)
			player = new Player(bis);
			// �ش������� ������ �ֶǷ� ����.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public int getTime() {
		// getTime�� ���� ����ǰ� �ִ� ������ ���ġ���� ����Ǵ��� �˷���.
		// ���� ������ӿ��� ��Ʈ�� ����Ʈ���� 0.001�ʱ��� �м��Ͽ� ������!
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() {
		// close�Լ��� ���� ����ǰ� �ְ� ���� ����ɼ��ֵ��� ���ִ� �Լ�.
		isloop = false;
		player.close();
		this.interrupt();
		// interrupt�� �ش� �����带 �������·� ������ִ� ����.
	}

	
	
	@Override
	public void run() {
		// run �Լ��� �����带 ��ӹ����� ������ �����ؾߵ�
		try {
			do {
				player.play();
				// �����
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isloop);
			// isloop�� ���� true���ä� ���ѹݺ�
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
