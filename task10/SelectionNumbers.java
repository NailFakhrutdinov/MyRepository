import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SelectionNumbers implements Runnable {
	private boolean flag = false;
	private final int countOfSelectionsNumbers = (int) Math.pow(10, 4);
	private final String password;
	private String result = null;
	private final byte[] arrayOfDigits = { 0, 0, 0, 0 };
	Thread t;
	public SelectionNumbers(String password) {
		this.password = password;
		 t = new Thread(this);
		t.start();
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
			s += arrayOfDigits[i];
		}
		return s;
	}

	private String select() throws NoSuchAlgorithmException {
		if (password.equals(md5(fromByteToString()))) {
			return fromByteToString();
		}
		for (int i = 0; i < countOfSelectionsNumbers; i++) {
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
		arrayOfDigits[0]++;
		for (int i = 0; i < 3; i++) {
			arrayOfDigits[i + 1] += (arrayOfDigits[i] / 10);
			arrayOfDigits[i] %= 10;
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
