
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.concurrent.Semaphore;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class HomeWorkN14 {
	public static void main(String[] args) throws IOException, XPatherException, InterruptedException {
		Semaphore sem = new Semaphore(3);
		HtmlCleaner cleaner = new HtmlCleaner();
		URL url = new URL("http://ru.wikipedia.org/wiki/Список_городов_России_с_населением_более_100_тысяч_жителей");
		TagNode html = cleaner.clean(url.openStream());
		for(Object ob : html.evaluateXPath("//tr//td[3]//a")) {
			TagNode x = (TagNode)ob;
		 new ParseThread(sem,x.getAttributeByName("href"),x.getText().toString()).t.join();;
		}
		Collections.sort(Years.getList());
		Years.getTenCities();
	}
}
class ParseThread implements Runnable {
	Thread t;
	Semaphore sem;
	String url;
	String name;
	ParseThread(Semaphore sem,String url,String name){
		this.sem = sem;
		this.url = url;
		this.name  = name;
		t = new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		URL content;
		try {
			sem.acquire();
			content = new URL("http://ru.wikipedia.org" + url);
			HtmlCleaner cleaner1 = new HtmlCleaner();
			TagNode html1 = cleaner1.clean(content.openStream());
			Object array [] = html1.evaluateXPath("//tr[td =\"Первое упоминание\"]//a/text()");
			if(array.length == 0) {
				try {
					String buf = html1.evaluateXPath("//tr[td =\"Основан\"]//a/text()")[0].toString();
					Tools.syso(buf,name);
				}catch(ArrayIndexOutOfBoundsException e) {
				}
			}
			else {
				String buf = array[0].toString();
				if(!Tools.syso(buf,name)) {
					try {
						buf = html1.evaluateXPath("//tr[td =\"Основан\"]//a/text()")[0].toString();
						Tools.syso(buf,name);
					}catch(ArrayIndexOutOfBoundsException e) {
					}
				}
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		finally {
			sem.release();
		}
	}
}
