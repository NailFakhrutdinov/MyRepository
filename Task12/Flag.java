
public class Flag {
	private volatile static boolean stopFlag = false;
	public static synchronized void setStopFlag(boolean flag) {
		stopFlag = flag;
	}
	public static boolean getStopFlag() {
		return stopFlag;
	}
}
