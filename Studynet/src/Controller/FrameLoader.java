package Controller;

import java.util.Stack;

import javax.swing.JFrame;

public abstract class FrameLoader {
	static Stack<Backable> frames;
	
	public static void back() {
		frames.pop().vanish();
		frames.peek().init();
	}
	
	public static void addFrame(Backable frame) {
		if(!frames.isEmpty())
			frames.peek().disappear();
		frames.push(frame);
	}
	
	public static void reset() {
		frames = new Stack<Backable>();
	}
	

}
