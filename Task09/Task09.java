import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Task09 extends JFrame {
	private String login;
	private String password;
	private final Pattern LOGIN_CHECK;
	private final Pattern PASSWORD_CHECK;
	private static Gender gender = Gender.NotSelected;

	public Task06() {
		super("authorization");
		login = "";
		password = "";
		LOGIN_CHECK = Pattern.compile("[a-zA-Z0-9]{2,10}");
		PASSWORD_CHECK = Pattern.compile("[a-zA-Z0-9]{3,40}");

		Container content = getContentPane();
		content.setLayout(null);
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(5, 5, 95, 21);
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(5, 30, 95, 21);
		final JTextField tfLogin = new JTextField(10);
		tfLogin.setBounds(100, 5, 120, 21);
		final JTextField tfPassword = new JTextField(40);
		tfPassword.setBounds(100, 30, 120, 21);
		final JLabel lblOutputOne = new JLabel("");
		lblOutputOne.setBounds(100, 100, 300, 21);
		final JLabel lblComBox = new JLabel("choose a gender");
		lblComBox.setBounds(230, 45, 100, 21);
		JButton btnSign = new JButton("Sign up");
		btnSign.setBounds(50, 67, 80, 21);
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = tfLogin.getText();
				if (LOGIN_CHECK.matcher(login).matches()) {
					File file = new File("src/users/" + login + ".txt");
					if (file.exists()) {
						lblOutputOne.setText("login already exists");
					} else {
						password = tfPassword.getText();
						if (PASSWORD_CHECK.matcher(password).matches()) {
							if (gender != Gender.NotSelected) {
								try {
									file.createNewFile();
									ObjectOutputStream oos = new ObjectOutputStream(
											new FileOutputStream(file));
									MessageDigest md = MessageDigest.getInstance("MD5");  
							        byte[] messageDigest = md.digest(password.getBytes());
							        BigInteger number = new BigInteger(1, messageDigest);
							        String hashtext = number.toString(16);
									oos.writeObject(new User(login, hashtext,
											System.currentTimeMillis(),gender));
									lblOutputOne
											.setText("the registration process was successful");
								} catch (IOException e1) {
									e1.printStackTrace();
								} catch (NoSuchAlgorithmException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							else {
								lblOutputOne
								.setText("gender wasn't select");
							}
						} else {
							lblOutputOne.setText("wrong password");
						}
					}
				} else {
					lblOutputOne.setText("wrong login");
				}
			}
		});
		Gender[] items = { Gender.NotSelected, Gender.Female, Gender.Male,
				Gender.Other };
		JComboBox comboBox = new JComboBox(items);
		comboBox.setBounds(230, 68, 100, 21);
		content.add(comboBox);
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox box = (JComboBox) e.getSource();
				Gender item = (Gender) box.getSelectedItem();
				gender = item;
			}
		};
		comboBox.addActionListener(actionListener);
		JButton btnLog = new JButton("Log in");
		btnLog.setBounds(130, 67, 80, 21);
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = tfLogin.getText();
				if (LOGIN_CHECK.matcher(s).matches()) {
					File file = new File("src/users/" + s + ".txt");
					if (file.exists()) {
						ObjectInputStream ois;
						try {
							ois = new ObjectInputStream(new FileInputStream(
									file));
							User user = (User) (ois.readObject());
							if (file.exists()) {
								s = tfPassword.getText();
								MessageDigest md = MessageDigest.getInstance("MD5");  
						        byte[] messageDigest = md.digest(s.getBytes());
						        BigInteger number = new BigInteger(1, messageDigest);
						        String hashtext = number.toString(16);
								if (user.password.equals(hashtext)) {
									if (PASSWORD_CHECK.matcher(s).matches()) {
										lblOutputOne.setText(new Date(
												user.lastlogintime).toString() + " gender : " + user.gender);
										ObjectOutputStream oos = new ObjectOutputStream(
												new FileOutputStream(file));
										oos.writeObject(new User(user.login,
												user.password, System
														.currentTimeMillis(),user.gender));
									} else {
										lblOutputOne.setText("Wrong password");
									}
								} else {
									lblOutputOne
											.setText("passwords do not match");
								}
							} else {
								lblOutputOne.setText("You are not logged in");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						lblOutputOne.setText("a login does not exist");
					}
				} else {
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
		content.add(comboBox);
		content.add(lblComBox);
		setPreferredSize(new Dimension(240, 130));
		pack();
		setLocationRelativeTo(null);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(content);
	}

	public static void main(String args[]) {
		Task06 abs = new Task06();
		abs.setVisible(true);

	}
}