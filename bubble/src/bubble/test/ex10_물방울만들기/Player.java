package bubble.test.ex10_물방울만들기;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Player extends JLabel implements Moveable {
	// 위치상태
	private int x; 
	private int y;

	// 움직임 상태
	private boolean left;	 
	private boolean right; 
	private boolean up;  
	private boolean down; 	 
	
	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	// 플레이어 속도 상태
	private final int SPEED = 4;	
	private final int JUMPSPEED = 2;	// up, down
	
	private ImageIcon playerR, playerL; 

	public Player() {
		initObject();
		initSetting();
		intBackgroundPlayerService();	
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png"); 
		playerL = new ImageIcon("image/playerL.png"); 
	}

	private void initSetting() {
		x = 80; 		
		y = 535; 
		
		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;
		

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y); 

	}
	
	private void intBackgroundPlayerService() {		
		new Thread(new BackgroundPlayerService(this)).start();
	}


	@Override
	public void left() {
		left = true;		
		new Thread(()->{		
			while(left) {		
				setIcon(playerL);	
				x -= SPEED;	
				setLocation(x,y);
				try {
					Thread.sleep(10);		
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		}).start(); 
	}

	@Override
	public void right() {

		right = true;
		new Thread(()->{		
			while(right) {
				setIcon(playerR);
				x += SPEED;		
				setLocation(x,y);	
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();  
		
	}

	@Override
	public void up() { 
//		System.out.println("up");
		up = true;		
		new Thread(()->{
			for(int i=0; i<130/JUMPSPEED; i++) {	
				y -= JUMPSPEED;		
				setLocation(x,y);	
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			up = false;		
			down();			
		}).start();
	}

	@Override
	public void down() {
//		System.out.println("down");
		down = true;
		new Thread(()->{
			while(down) {						
				y += JUMPSPEED;						
				setLocation(x,y);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	}
}
