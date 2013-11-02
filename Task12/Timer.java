import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Timer {
	private  JLabel lblShowTime0;
	private  JLabel lblShowTime1;
	private  JLabel lblShowTime2;
	private  JLabel lblShowTime3;
	private  JLabel lblT;
	private JLabel lblMessage;
	private JButton btnStopPomodoro;
	private JButton btnBreak;
	private JButton btnSkipBreak;
	private JButton btnStopBreak;
	private JButton btnStartPomodoro;
	
	public Timer(JLabel lblshowTime0, JLabel lblMessage,JButton btnStopPomodoro,JButton btnBreak,
			JButton btnStopBreak,JButton btnStartPomodoro,JLabel lblshowTime1,JLabel lblshowTime2,JLabel lblshowTime3,JLabel lblT,JButton btnSkipBreak) {
		
		this.lblShowTime0 = lblshowTime0;
		this.lblMessage = lblMessage;
		this.btnStopPomodoro = btnStopPomodoro;
		this.btnBreak = btnBreak;
		this.btnStopBreak = btnStopBreak;
		this.btnStartPomodoro = btnStartPomodoro;
		this.lblShowTime1 = lblshowTime1;
		this.lblShowTime2 = lblshowTime2;
		this.lblShowTime3 = lblshowTime3;
		this.lblT = lblT;
		this.btnSkipBreak = btnSkipBreak;
		
	}
	public void  startPomodoro(int max) throws InterruptedException {
		byte time [] = new byte[4];
		for(int i = 0; i < 4; i++) {
			time[i] = 0;
		}
		int i = 0;
			while(i < max) {
				if(Flag.getStopFlag()) {
					break;
				}
				Thread.sleep(1000);
				plusPlus(time);
				show(time);
				i++;
			}
			File file1 = new File("src/image/digit/0.jpg");
			lblShowTime0.setIcon(new ImageIcon(file1.getAbsolutePath().replace("\\","\\\\")));
			
			File file2 = new File("src/image/digit/0.jpg");
			lblShowTime1.setIcon(new ImageIcon(file2.getAbsolutePath().replace("\\","\\\\")));
			
			File file3 = new File("src/image/digit/0.jpg");
			lblShowTime2.setIcon(new ImageIcon(file3.getAbsolutePath().replace("\\","\\\\")));
			
			
			File file4 = new File("src/image/digit/0.jpg");
			lblShowTime3.setIcon(new ImageIcon(file4.getAbsolutePath().replace("\\","\\\\")));
	}
	public void plusPlus(byte array []) {
		array[0]++;
		for(int i = 0; i < 3; i++) {
			switch(i) {
			case 0 :
				array[i+1] += (byte) (array[i] / 10);
				array[i] %= 10;
				break;
			case 1:
				array[i+1] += (byte) (array[i] / 6);
				array[i] %= 6;
				break;
			case 2:
				array[i+1] += (byte) (array[i] / 10);
				array[i] %= 10;
				break;
			}
		}
	}
	private void show(byte array []) {
		
		File file1 = new File("src/image/digit/"+array[3]+".jpg");
		lblShowTime0.setIcon(new ImageIcon(file1.getAbsolutePath().replace("\\","\\\\")));
		
		File file2 = new File("src/image/digit/"+array[2]+".jpg");
		lblShowTime1.setIcon(new ImageIcon(file2.getAbsolutePath().replace("\\","\\\\")));
		
		File file3 = new File("src/image/digit/"+array[1]+".jpg");
		lblShowTime2.setIcon(new ImageIcon(file3.getAbsolutePath().replace("\\","\\\\")));
		
		File file4 = new File("src/image/digit/"+array[0]+".jpg");
		lblShowTime3.setIcon(new ImageIcon(file4.getAbsolutePath().replace("\\","\\\\")));
		
	}
	public void printMessage(String message) {
		lblMessage.setText(message);
	}
	public void setVisibleShowTime(boolean flag) {
		lblShowTime0.setVisible(flag);
		lblShowTime1.setVisible(flag);
		lblShowTime2.setVisible(flag);
		lblShowTime3.setVisible(flag);
		lblT.setVisible(flag);
		
	}
	public void setVisibleStopPomodoro(boolean flag) {
		btnStopPomodoro.setVisible(flag);
	}
	public void setVisibleBreak(boolean flag) {
		btnBreak.setVisible(flag);
	}
	public void setVisibleStopBreak(boolean flag) {
		btnStopBreak.setVisible(flag);
	}
	public void setVisibleSkipBreak(boolean flag) {
		btnSkipBreak.setVisible(flag);
	}
	public void setVisibleStartPomodoro(boolean flag) {
		btnStartPomodoro.setVisible(flag);
	}
	public void pausePomodoro() throws InterruptedException {
		startPomodoro(5*60);
	}
}
