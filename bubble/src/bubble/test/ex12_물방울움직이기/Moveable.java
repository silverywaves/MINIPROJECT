package bubble.test.ex12_물방울움직이기;


public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	default public void down() {};	
}
