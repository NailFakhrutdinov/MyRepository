import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task05 {
	public static void main(String args []) throws Exception {
		System.out.print("Enter login: ");
		Scanner sc = new Scanner(System.in);
		String login = sc.next();
		Pattern patter = Pattern.compile("[a-zA-Z0-9]{2,10}");
		Matcher m = patter.matcher(login);
		if(m.matches()) {
			File file = new File("src/system/"+login+".txt");
			if(!file.exists()) {
				System.out.print("Enter password: ");
				String password = sc.next();
				Pattern pattern = Pattern.compile("[a-zA-Z0-9]{3,40}");
				Matcher match = pattern.matcher(password);
				if(match.matches()) {
					file.createNewFile();
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(new User(login,password,System.currentTimeMillis()));
				}
				else {
					System.out.println("Wrong password.");
				}
			}
			else {
				System.out.println("Enter password");
				String password = sc.next();
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				User user =(User)ois.readObject();
				if(!user.password.equals(password)) {
					System.out.println("Wrong password.");
				}
				else {
					System.out.println(new Date(user.lastlogintime));
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(new User(login,password,System.currentTimeMillis()));
				}
			}
		}
		else {
			System.out.println("Wrong login.");
		}
	}
}
