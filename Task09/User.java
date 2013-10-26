import java.io.Serializable;

public class User implements Serializable {
	String login;
	String password;
	Long lastlogintime;
	Gender gender;
	public User(String login,String password, long lastlogintime,Gender gender) {
		this.login = login;
		this.password = password;
		this.lastlogintime = lastlogintime; 
		this.gender = gender;
	}
	public String toString() {
		return login + " " + password + " " + lastlogintime;
	}
}
