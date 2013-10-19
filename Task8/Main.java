import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		System.out.println("Запрос места в рейтинге :");
		System.out.print("\t");
		int number = sc.nextInt();
		try {
			System.out.println("Ответ: ");
			System.out.println("\t"+BD_Utils.ratingCalculation(number));
			System.out.println("Комментарий: \n \t Был составлен Общий рейтинг из двух таблиц и найден фильм \n \t занимающий " + number + "ую позицию в Общем рейтинге");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
