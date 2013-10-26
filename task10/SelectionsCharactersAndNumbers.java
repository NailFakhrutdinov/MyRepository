import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SelectionsCharactersAndNumbers implements Runnable {
	private boolean flag;
	Thread t;
	private final int countOfSelectionsCharactersAndNumbers = (int)Math.pow(36, 4);
	private static char [] alphabet;
	private String password;
	private String result = null;
	private final byte[] arrayOfCharactersAndNumbers = {0,0,0};
	static {
		alphabet = new char [36];
		for (int i = 0; i < 10; i++) {
				alphabet[i] = (char)(i + 48);
		}
		for(int i = 10; i < 36; i++) {
			alphabet[i] = (char)(i + 55);
		}
	}
	public SelectionsCharactersAndNumbers(String password) {
		this.password = password;
		t = new Thread(this);
		t.run();
	}
	private String md5(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(password.getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		return number.toString(16);
	}

	private String fromCharToString() {
		String s = "";
		for (int i = 0; i < 3; i++) {
			s += alphabet[arrayOfCharactersAndNumbers[i]];
		}
		return s;
	}
	public boolean getFlag() {
		return flag;
	}
	private String select() throws NoSuchAlgorithmException {
		if (password.equals(md5(fromCharToString()))) {
			return fromCharToString();
		}
		for (int i = 0; i < countOfSelectionsCharactersAndNumbers; i++) {
			if(Flag.flag) {
				return null;
			}
			plusPlus();
			if (password.equals(md5(fromCharToString()))) {
				flag = true;
				Flag.flag = true;
				return fromCharToString();
			}
		}
		return null;
	}

	private void plusPlus() {
		arrayOfCharactersAndNumbers[0]++;
		for (int i = 0; i < 2; i++) {
			arrayOfCharactersAndNumbers[i + 1] += (arrayOfCharactersAndNumbers[i] / 36);
			arrayOfCharactersAndNumbers[i] %= 36;
		}
	}

	public String getResult() {
		return result;
	}

	public void run() {
		try {
			result = select();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
