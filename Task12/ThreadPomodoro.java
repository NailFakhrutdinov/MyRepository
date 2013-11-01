public class ThreadPomodoro implements Runnable {
	Thread thread;
	private volatile boolean flagRun = false;
	private volatile boolean flagPause = false;
	volatile Timer timer;

	ThreadPomodoro(Timer timer) {
		thread = new Thread(this);
		this.timer = timer;
		thread.start();
	}

	public void setFlagRun(boolean flagRun) {
		this.flagRun = flagRun;
	}

	public void setFlagPause(boolean flagPause) {
		this.flagPause = flagPause;
	}

	public void run() {
		while (true) {
			if (flagRun) {
				try {
					timer.startPomodoro(25 * 60);
					if (!Flag.getStopFlag()) {
						timer.printMessage("Your Pomodoro is over. It's time to take a break!");
						timer.setVisibleStopPomodoro(false);
						timer.setVisibleBreak(true);
						timer.setVisibleShowTime(false);
					}
					flagRun = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (flagPause) {
				try {
					timer.pausePomodoro();
					if (!Flag.getStopFlag()) {
						timer.printMessage("                    The break is over. Time to work!");
						timer.setVisibleStopBreak(false);
						timer.setVisibleShowTime(false);
						timer.setVisibleSkipBreak(false);
						timer.setVisibleStartPomodoro(true);
					}
					flagPause = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
