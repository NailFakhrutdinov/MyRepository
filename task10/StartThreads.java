import java.util.Scanner;


public class StartThreads {
	public static void workWithConsole() throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.println("write your hashcode: \n \t");
		String hashCode = sc.next();
		SelectionsCharacters sh = new SelectionsCharacters(hashCode);
		SelectionNumbers sn = new SelectionNumbers(hashCode);
		SelectionsCharactersAndNumbers scan = new SelectionsCharactersAndNumbers(hashCode);
		sh.t.join();
		sn.t.join();
		scan.t.join();
		System.out.println();
		if(sh.getFlag()) {
			System.out.print("decrypted : ");
			System.out.println(sh.getResult());
		}
		if(sn.getFlag()) {
			System.out.print("decrypted : ");
			System.out.println(sn.getResult());
		}
		if(scan.getFlag()) {
			System.out.print("decrypted : ");
			System.out.println(scan.getResult());
		}
		if(!Flag.flag) {
			System.out.println("not decrypted");
		}
	}
}
