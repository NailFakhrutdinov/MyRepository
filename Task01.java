import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 1.  Ќаписать метод, использу€ регул€рные выражени€, реализующий проверку номера
телефона на соответствие шаблону:
+код_страны (код_города) основной_номер
код_страны Ц от 1 до 3 цифр
код_города Ц от 2 до 8 цифр
основной_номер Ц 3 блока цифр разделенных знаками Ђ-ї, от 1 до 6 цифр в каждом 
блоке
ѕример корректных номеров:
+ 3 (475) 69-664-65, +7 (353) 49-65-656


2.  Ќаписать метод, использу€ регул€рные выражени€ (replaceAll), привод€щий телефонный 
номер из задани€ 1 в международный формат:
+код_страныкод_городаосновной_номер
Ќеобходимо убрать все лишние символы, кроме цифр и Ђ+ї. ѕриводить необходимо
только телефонные номера прошедшие проверку методом из задани€ (1).  
 */

public class Task01 {
	public static void main(String args []) {
		//Tests toCheckNumbers()
		System.out.println(toCheckNumbers("+ 7 (843) 213-324-21421"));
		System.out.println(toCheckNumbers(" 7 (843) 213-324-21421"));
		System.out.println(toCheckNumbers("+ 7 (843) 213-324-21421"));
		System.out.println(toCheckNumbers("+ 7 (84) 13-24-1"));
		System.out.println(toCheckNumbers("+ 777 (55555555) 213123-324123-214211"));
		System.out.println(toCheckNumbers("+7(843)0324-21421"));
		System.out.println(toCheckNumbers("+ 7 (843) 213-324-"));
		//Tests converter
		try {
			System.out.println(converter("+ 7 (843) 213-324-21421"));
			System.out.println(converter("+ 7 (843) 213-324-21421"));
			System.out.println(converter("+ 7 (84) 13-24-1"));
			System.out.println(converter("+ 777 (55555555) 213123-324123-214211"));
			System.out.println(converter(" 7 (843) 213-324-21421"));
			System.out.println(converter("+7(843)0324-21421"));
			System.out.println(converter("+ 7 (843) 213-324-"));
			
		}catch(IllegalArgumentException e) {
			//....
		}
	}
	public static boolean toCheckNumbers(String number) {
		Pattern p = Pattern.compile("^ *\\+ *[0-9]{1,3} *\\([0-9]{2,8}\\) *[0-9]{1,6}-[0-9]{1,6}-[0-9]{1,6} *$");
		Matcher m = p.matcher(number);
		return m.matches();
	}
	public static String converter(String number) throws IllegalArgumentException {
		if(!toCheckNumbers(number)) {
			throw new IllegalArgumentException("Wrong number");
		}
		Pattern p = Pattern.compile(("(-)|(\\()|(\\))|( *)"));
		Matcher m = p.matcher(number);
		return m.replaceAll("");
	}
}