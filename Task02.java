import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// курс $ = 32,32
// курс И = 43,14

/*
 * —рок сдачи: 12-00 24/09/2013
1.  Ќаписать метод, который конвертирует валюту (доллары, евро) в рубли. ћетод 
должен принимать один аргумент: сумму денег в валюте с ведущим значком 
валюты.
Ќапример:
$58.23
И434,2
”слови€:
- «начок валюты об€зателен ($ или И)
- –азделителем валюты должен быть Ђ.ї или Ђ,ї
- ƒробного значени€ валюты (центов) может не быть или максимум 2 цифры 
после  зап€той
–езультат:
- ‘ункци€ должна вернуть сумму в рубл€х с точностью до второго знака 
после зап€той (курс вз€ть текущий на день решени€ задачи)
- ≈сли вход€щее значение не соответствует шаблону, то функци€ должна 
возвращать 0 
 * 
 */
public class Task02 {
	
	private static final float DOLLAR = 32.3257f;
	private static final float EURO = 43.1457f;
	
	public static float converter(String s) {
		Pattern p = Pattern.compile("(\\$|И)(([1-9]([0-9]+)?)|0)((\\.|,)([0-9]{0,2}))?$");
		Matcher m = p.matcher(s);
		if(!m.matches()) {
			return 0;
		}
		String str = s.substring(1,s.length());
		if(str.contains(",")) {
			str = str.replace(",",".");
		}
		float sum = Float.valueOf(str);
		switch(s.charAt(0)) {
			case '$' :
				sum *= DOLLAR;
				break;
			case 'И' :
				sum *= EURO;
				break;
		}
		sum = new BigDecimal(sum).setScale(2,RoundingMode.UP).floatValue();
		return  sum;
	}
	public static void main(String[] args) {
	//tests
		System.out.println(converter("И1.1"));
		System.out.println(converter("$1.10"));
		System.out.println(converter("$0.1"));
		System.out.println(converter("$00.1"));
		System.out.println(converter("$.1"));
		System.out.println(converter("И109,10"));
		System.out.println(converter("$10000"));
		System.out.println(converter("1"));
		System.out.println(converter("$19-12"));
		System.out.println(converter("$1,213"));
	}
}