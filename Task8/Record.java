
public class Record implements Comparable<Record> {
	float rating;
	String nameAndDate;
	Record(String nameAndDate,float rating) {
		this.rating = rating;
		this.nameAndDate = nameAndDate;
	}
	@Override
	public int compareTo(Record o) {
		if(this.rating > o.rating) {
			return -1;
		}
		else {
			if(this.rating < o.rating) {
				return 1;
			}
		}
		return 0;
	}
	public String toString() {
		return nameAndDate + " - " + rating;
	}
}
