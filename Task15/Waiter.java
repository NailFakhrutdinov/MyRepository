package programming_HW_N_15;

import javax.swing.JLabel;

public class Waiter implements Runnable {
	Thread thisThread;
	Thread t;
	JLabel label;
	Waiter(JLabel label, Thread t) {
		this.label = label;
		this.t = t;
		thisThread = new Thread(this);
		thisThread.start();
	}
	@Override
	public void run() {
		label.setText("Идет перевод...");
		while(t.isAlive()) {
			
		}
		label.setText("");
	}

}
