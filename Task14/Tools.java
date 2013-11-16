
import java.util.LinkedList;
import java.util.List;

public class Tools {
	public static int checkYear(String year) {
		if(year.length() <= 4) {
			return -1;
		}
		else {
			for(int i = 0; i < (year.length() - 3); i++) {
				if(Character.isDigit(year.charAt(i)) && Character.isDigit(year.charAt(i + 1)) &&
						Character.isDigit(year.charAt(i + 2))) {
					return i;
				}
			}
		}
		return -2;
	}
	
	public static boolean  syso(String year,String name) {
		byte index = (byte)checkYear(year);
		if(index == -1) {
			Years.add(year,name);
			return true;
		}
		else {
			if(index != -2) {
				Years.add(year.substring(index,index+4),name);
				return true;
			}
		}
		return false;
	}
}
class Years {
	private static List<City> list = new LinkedList<>();
	public synchronized static void add(String year,String name) {
		list.add(new City(year,name));
	}
	public static List<City> getList() {
		return list;
	}
	public static void getTenCities() {
		for(int i = 0; i < 10; i++) {
			System.out.println((i + 1) +". " +  list.get(i));
		}
	}
}
class City implements Comparable<City> {
	String year;
	String name;
	public City(String year,String name) {
		this.year = year;
		this.name = name;
	}
	public int compareTo(City o) {
		return new Integer(this.year.trim()).compareTo(new Integer(o.year.trim()));
	}
	public String toString() {
		return name +" (" + year.trim() + ")";
	}
}
