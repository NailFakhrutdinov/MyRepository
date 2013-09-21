import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// ���� $ = 32,32
// ���� � = 43,14

/*
 * ���� �����: 12-00 24/09/2013
1.  �������� �����, ������� ������������ ������ (�������, ����) � �����. ����� 
������ ��������� ���� ��������: ����� ����� � ������ � ������� ������� 
������.
��������:
$58.23
�434,2
�������:
- ������ ������ ���������� ($ ��� �)
- ������������ ������ ������ ���� �.� ��� �,�
- �������� �������� ������ (������) ����� �� ���� ��� �������� 2 ����� 
�����  �������
���������:
- ������� ������ ������� ����� � ������ � ��������� �� ������� ����� 
����� ������� (���� ����� ������� �� ���� ������� ������)
- ���� �������� �������� �� ������������� �������, �� ������� ������ 
���������� 0 
 * 
 */
public class Task02 {
	
	private static final float DOLLAR = 32.3257f;
	private static final float EURO = 43.1457f;
	
	public static float converter(String s) {
		Pattern p = Pattern.compile("(\\$|�)(([1-9]([0-9]+)?)|0)((\\.|,)([0-9]{0,2}))?$");
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
			case '�' :
				sum *= EURO;
				break;
		}
		sum = new BigDecimal(sum).setScale(2,RoundingMode.UP).floatValue();
		return  sum;
	}
	public static void main(String[] args) {
	//tests
		System.out.println(converter("�1.1"));
		System.out.println(converter("$1.10"));
		System.out.println(converter("$0.1"));
		System.out.println(converter("$00.1"));
		System.out.println(converter("$.1"));
		System.out.println(converter("�109,10"));
		System.out.println(converter("$10000"));
		System.out.println(converter("1"));
		System.out.println(converter("$19-12"));
		System.out.println(converter("$1,213"));
	}
}