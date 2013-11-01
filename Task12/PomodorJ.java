import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class PomodorJ extends JFrame {
	private JButton btnStartPomodoro;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PomodorJ frame = new PomodorJ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PomodorJ() {
		getContentPane().setFont(new Font("Maiandra GD", Font.BOLD, 32));
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 730);
		getContentPane().setLayout(null);
		final JLabel lbl_Pomodor = new JLabel("");
		File file = new File("src/image/pomodoro_logo_main.png");
		lbl_Pomodor.setIcon(new ImageIcon(file.getAbsolutePath().replace("\\","\\\\")));
		lbl_Pomodor.setBounds(155, 0, 1151, 276);
		getContentPane().add(lbl_Pomodor);		
		
		final JLabel lblShowTime0 = new JLabel("");
		File file1 = new File("src/image/digit/0.jpg");
		lblShowTime0.setIcon(new ImageIcon(file1.getAbsolutePath().replace("\\","\\\\")));
		lblShowTime0.setVisible(false);
		lblShowTime0.setBounds(570, 265, 58, 136);
		getContentPane().add(lblShowTime0);
		
		final JLabel lblShowTime1 = new JLabel("");
		File file2 = new File("src/image/digit/0.jpg");
		lblShowTime1.setIcon(new ImageIcon(file2.getAbsolutePath().replace("\\","\\\\")));
		lblShowTime1.setVisible(false);
		lblShowTime1.setBounds(638, 265, 64, 136);
		getContentPane().add(lblShowTime1);
		
		final JLabel lblShowTime2 = new JLabel("");
		File file3 = new File("src/image/digit/0.jpg");
		lblShowTime2.setIcon(new ImageIcon(file3.getAbsolutePath().replace("\\","\\\\")));
		lblShowTime2.setVisible(false);
		lblShowTime2.setBounds(740, 265, 64, 136);
		getContentPane().add(lblShowTime2);
		
		final JLabel lblShowTime3 = new JLabel("");
		File file4 = new File("src/image/digit/0.jpg");
		lblShowTime3.setIcon(new ImageIcon(file4.getAbsolutePath().replace("\\","\\\\")));
		lblShowTime3.setVisible(false);
		lblShowTime3.setBounds(800, 265, 64, 136);
		getContentPane().add(lblShowTime3);
		
	
		btnStartPomodoro = new JButton("Start pomodoro");
		btnStartPomodoro.setFont(new Font("Showcard Gothic", Font.BOLD, 77));
		btnStartPomodoro.setFocusable(false);
		btnStartPomodoro.setAutoscrolls(true);
		btnStartPomodoro.setForeground(Color.RED);
		btnStartPomodoro.setBackground(Color.WHITE);
		btnStartPomodoro.setIcon(null);
		btnStartPomodoro.setBounds(271, 427, 830, 97);
		getContentPane().add(btnStartPomodoro);
		
		final JButton btnBreak = new JButton("Break");
		btnBreak.setVisible(false);
		btnBreak.setFocusable(false);
		btnBreak.setForeground(Color.RED);
		btnBreak.setBackground(Color.WHITE);
		btnBreak.setFont(new Font("Showcard Gothic", Font.BOLD, 77));
		btnBreak.setBounds(467, 427, 434, 97);
		getContentPane().add(btnBreak);
		final JLabel lblMessage = new JLabel("");
		lblMessage.setForeground(Color.GRAY);
		lblMessage.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 40));
		lblMessage.setBounds(186, 265, 1154, 151);
		getContentPane().add(lblMessage);
		
		final JButton btnStopPomodoro = new JButton("Stop pomodoro");
		btnStopPomodoro.setVisible(false);
		btnStopPomodoro.setBackground(Color.WHITE);
		btnStopPomodoro.setFocusable(false);
		btnStopPomodoro.setForeground(Color.RED);
		btnStopPomodoro.setFont(new Font("Showcard Gothic", Font.BOLD, 77));
		btnStopPomodoro.setBounds(307, 427, 788, 97);
		getContentPane().add(btnStopPomodoro);
		
		final JButton btnStopBreak = new JButton("Stop break");
		btnStopBreak.setBackground(Color.WHITE);
		btnStopBreak.setVisible(false);
		btnStopBreak.setFont(new Font("Showcard Gothic", Font.BOLD, 77));
		btnStopBreak.setForeground(Color.RED);
		btnStopBreak.setBounds(450, 427, 554, 97);
		getContentPane().add(btnStopBreak);
		
		final JLabel lblT = new JLabel("");
		lblT.setVisible(false);
		File file5 = new File("src/image/digit/t.jpg");
		lblT.setIcon(new ImageIcon(file5.getAbsolutePath().replace("\\","\\\\")));
		lblT.setBounds(693, 287, 46, 109);
		getContentPane().add(lblT);
		

		final JButton btnSkipBreak = new JButton("Skip the break");
		btnSkipBreak.setVisible(false);
		btnSkipBreak.setBackground(Color.WHITE);
		btnSkipBreak.setForeground(Color.RED);
		btnSkipBreak.setFont(new Font("Showcard Gothic", Font.BOLD, 77));
		btnSkipBreak.setBounds(344, 535, 755, 97);
		getContentPane().add(btnSkipBreak);
		
		final Timer timer = new Timer(lblShowTime0,lblMessage,btnStopPomodoro,btnBreak,btnStopBreak,btnStartPomodoro,lblShowTime1,
										lblShowTime2,lblShowTime3,lblT,btnSkipBreak);

		
		final ThreadPomodoro threadPomodoro = new ThreadPomodoro(timer);
		
		btnStartPomodoro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Flag.setStopFlag(false);
				lblMessage.setText("");
				lblShowTime0.setVisible(true);
				lblShowTime1.setVisible(true);
				lblShowTime2.setVisible(true);
				lblShowTime3.setVisible(true);
				lblT.setVisible(true);
				threadPomodoro.setFlagRun(true);
				btnStopPomodoro.setVisible(true);
				btnStartPomodoro.setVisible(false);
			}
		});
		btnStopPomodoro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Flag.setStopFlag(true);
				lblShowTime0.setVisible(false);
				lblShowTime1.setVisible(false);
				lblShowTime2.setVisible(false);
				lblShowTime3.setVisible(false);
				lblT.setVisible(false);
				btnStopPomodoro.setVisible(false);
				btnStartPomodoro.setVisible(true);
			}
		});
		btnBreak.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Flag.setStopFlag(false);
				lblMessage.setText("");
				lblShowTime0.setVisible(true);
				lblShowTime1.setVisible(true);
				lblShowTime2.setVisible(true);
				lblShowTime3.setVisible(true);
				lblT.setVisible(true);
				threadPomodoro.setFlagPause(true);
				btnStopBreak.setVisible(true);
				btnSkipBreak.setVisible(true);
				btnBreak.setVisible(false);
			}
		});
		btnStopBreak.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblMessage.setText("");
				Flag.setStopFlag(true);
				lblShowTime0.setVisible(false);
				lblShowTime1.setVisible(false);
				lblShowTime2.setVisible(false);
				lblShowTime3.setVisible(false);
				lblT.setVisible(false);
				btnStopBreak.setVisible(false);
				btnBreak.setVisible(true);
				
			}
		});
		btnSkipBreak.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblMessage.setText("");
				Flag.setStopFlag(true);
				lblShowTime0.setVisible(false);
				lblShowTime1.setVisible(false);
				lblShowTime2.setVisible(false);
				lblShowTime3.setVisible(false);
				lblT.setVisible(false);
				btnStopBreak.setVisible(false);
				btnStartPomodoro.setVisible(true);
				btnSkipBreak.setVisible(false);
			}
		});
	}
}
