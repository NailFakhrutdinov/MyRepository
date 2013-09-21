import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 1.  �������� �����, ��������� ���������� ���������, ����������� �������� ������
�������� �� ������������ �������:
+���_������ (���_������) ��������_�����
���_������ � �� 1 �� 3 ����
���_������ � �� 2 �� 8 ����
��������_����� � 3 ����� ���� ����������� ������� �-�, �� 1 �� 6 ���� � ������ 
�����
������ ���������� �������:
+ 3 (475) 69-664-65, +7 (353) 49-65-656


2.  �������� �����, ��������� ���������� ��������� (replaceAll), ���������� ���������� 
����� �� ������� 1 � ������������� ������:
+���_���������_��������������_�����
���������� ������ ��� ������ �������, ����� ���� � �+�. ��������� ����������
������ ���������� ������ ��������� �������� ������� �� ������� (1).  
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