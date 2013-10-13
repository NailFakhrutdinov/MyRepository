import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Task06 extends JFrame {
	private String login;
	private String password;
	private final Pattern LOGIN_CHECK;
	private final Pattern PASSWORD_CHECK;
    public Task06(){
        super("authorization");
        login = "";
        password = "";
        LOGIN_CHECK = Pattern.compile("[a-zA-Z0-9]{2,10}");
        PASSWORD_CHECK = Pattern.compile("[a-zA-Z0-9]{3,40}");
        JPanel content = new JPanel();
        content.setLayout(null);
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setBounds(5,5,95,21); 
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(5,30,95,21);
        final JTextField tfLogin = new JTextField(10);
        tfLogin.setBounds(100,5,120,21);
        final JTextField tfPassword = new JTextField(40);
        tfPassword.setBounds(100,30,120,21);
        final JLabel lblOutputOne = new JLabel("");
        lblOutputOne.setBounds(100,100,300,21);
        JButton btnSign = new JButton("Sign up");
        btnSign.setBounds(50,67,80,21);
        btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = tfLogin.getText();
				if(LOGIN_CHECK.matcher(login).matches()) {
					File file = new File("src/users/"+login+".txt");
					if(file.exists()) {
						lblOutputOne.setText("login already exists");
					}
					else {
						password = tfPassword.getText();
						if(PASSWORD_CHECK.matcher(password).matches()) {
							try {
								file.createNewFile();
								ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
								oos.writeObject(new User(login,password,System.currentTimeMillis()));
								lblOutputOne.setText("the registration process was successful");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						else {
							lblOutputOne.setText("wrong password");
						}
					}
				}
				else {
					lblOutputOne.setText("wrong login");
				}
			}
		});
        JButton btnLog = new JButton("Log in");
        btnLog.setBounds(130,67,80,21);
        btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = tfLogin.getText();
				if(LOGIN_CHECK.matcher(s).matches()) {
					File file = new File("src/users/"+s+".txt");
					ObjectInputStream ois;
					try {
						ois = new ObjectInputStream(new FileInputStream(file));
						User user = (User)(ois.readObject());
						if(file.exists()) {
							s = tfPassword.getText();
							if(user.password.equals(s)) {
								if(PASSWORD_CHECK.matcher(s).matches()) {
										lblOutputOne.setText(new Date(user.lastlogintime).toString());
										ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
										oos.writeObject(new User(user.login,user.password,System.currentTimeMillis()));
								}
								else {
									lblOutputOne.setText("Wrong password");
								}
							}
							else {
								lblOutputOne.setText("passwords do not match");
							}
						}
						else {
							lblOutputOne.setText("You are not logged in");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else {
					lblOutputOne.setText("wrong login");
				}	
			}
		});
        content.add(lblLogin);
        content.add(lblPassword);
        content.add(tfLogin);
        content.add(tfPassword);
        content.add(btnSign);
        content.add(btnLog);
        content.add(lblOutputOne);
        setSize(400,400); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(content);
    }
    public static void main(String args []) {
    	Task06 abs = new Task06();
    	abs.setVisible(true);
    	
    }
}