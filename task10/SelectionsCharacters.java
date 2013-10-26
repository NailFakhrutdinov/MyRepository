import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SelectionsCharacters implements Runnable {
	private boolean flag;
	Thread t;
	private final int countOfSelectionsCharacters = (int)Math.pow(26,4);
	private final byte ADDITION = 97;
	private String password;
	private String result = null;
	private final byte[] arrayOfCharacters = {0,0,0,0};
	public SelectionsCharacters(String password) {
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

	private String fromByteToString() {
		String s = "";
		for (int i = 0; i < 4; i++) {
			s += (char)(arrayOfCharacters[i] + 97);
		}
		return s;
	}

	private String select() throws NoSuchAlgorithmException {
		if (password.equals(md5(fromByteToString()))) {
			return fromByteToString();
		}
		for (int i = 0; i < countOfSelectionsCharacters; i++) {
			if(Flag.flag) {
				return null;
			}
			plusPlus();
			if (password.equals(md5(fromByteToString()))) {
				flag = true;
				Flag.flag = true;
				return fromByteToString();
			}
		}
		return null;
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	private void plusPlus() {
		arrayOfCharacters[0]++;
		for (int i = 0; i < 3; i++) {
			arrayOfCharacters[i + 1] += (arrayOfCharacters[i] / 26);
			arrayOfCharacters[i] %= 26;
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
