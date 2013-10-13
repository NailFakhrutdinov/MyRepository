import java.io.Serializable;

public class User implements Serializable {
	String login;
	String password;
	Long lastlogintime;
	public User(String login,String password, long lastlogintime) {
		this.login = login;
		this.password = password;
		this.lastlogintime = lastlogintime; 
	}
	public String toString() {
		return login + " " + password + " " + lastlogintime;
	}
}
