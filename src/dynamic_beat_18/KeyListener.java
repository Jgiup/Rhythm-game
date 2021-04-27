package dynamic_beat_18;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter{
	
	@Override
	public void keyPressed(KeyEvent e) {
	//키를 눌렀을 때 이벤트.
		if(DynamicBeat.game == null) {
			return;
		}
		//만약 게임이 null값이라면, 진행중이지 않다면 밑에 이벤트들 무시.
		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			DynamicBeat.game.pressA();
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.pressS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.pressD();
		}	
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.pressSpace();
		}	
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.pressJ();
		}	
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.pressK();
		}	
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.pressL();
		}
	
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	//키를 뗐을 때 이벤트.
		if(DynamicBeat.game == null) {
			return;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			DynamicBeat.game.releaseA();
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.releaseS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.releaseD();
		}	
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.releaseSpace();
		}	
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.releaseJ();
		}	
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.releaseK();
		}	
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.releaseL();
		}
	}

}
