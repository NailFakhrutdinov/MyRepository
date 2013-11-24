package programming_HW_N_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Downloader implements Runnable {
	String link;
	Thread t;
	JLabel message;
	JSONParser parser;
	ContainerFactory cf;
	JTextArea out_text;
	public Downloader(String link,JLabel message,ContainerFactory cf,JSONParser parser,JTextArea out_text) {
		this.link = link;
		this.message = message;
		this.cf = cf;
		this.parser = parser;
		this.out_text = out_text;
		t = new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		URL url;
		 try {
				url = new URL(link);
			 BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
			 Map map = (Map)parser.parse(reader,cf);
			 for(Object x : map.entrySet()) {
				 Map.Entry element = (Map.Entry)x;
				 out_text.setText(element.getKey() + " " + element.getClass());
				 if(element.getKey().equals("code")) {
					 if(!(element.getValue() + "").equals("200")) {
						 message.setText("Не удалось подключиться к интернету");
						 break;
					 }
				 }
				 if(element.getKey().equals("text")) {
					 String text = element.getValue().toString();
					 text = text.substring(1,text.length()-1);
					 byte array [] = text.getBytes();
					 text = new String(array);
					 out_text.setText(text);
				 }
			 }
		 }catch (MalformedURLException e) {
			 	message.setText("Не удалось подключиться к интернету");
				e.printStackTrace();
		} catch (IOException e) {
				message.setText("Не удалось подключиться к интернету");
				e.printStackTrace();
		} catch (ParseException e) {
				message.setText("Не удалось подключиться к интернету");
				e.printStackTrace();
			}
	}
}
