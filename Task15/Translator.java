package programming_HW_N_15;

import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Translator extends JFrame {
	private final String KEY = "";
	static String value_from = null;
	static String value_into = null;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Translator frame = new Translator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Translator() throws ParseException, IOException {
		 final Map<String,LinkedList<String>> languages = new HashMap<String,LinkedList<String>>();
		 final Map<String,String> compliance = new HashMap<String,String>();
		 final JSONParser parser = new JSONParser();
		  final ContainerFactory containerFactory = new ContainerFactory(){
		    public List creatArrayContainer() {
		      return new LinkedList();
		    }

		    public Map createObjectContainer() {
		      return new LinkedHashMap();
		    }
		                        
		  };
		  String jsonText = "https://translate.yandex.net/api/v1.5/tr.json/getLangs?key="+KEY+"&ui=ru";
		  URL url = new URL(jsonText);
		  Reader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
		  Map json = (Map)parser.parse(reader,containerFactory);
		  for(Object x : json.entrySet()) {
			  Map.Entry es = (Map.Entry)x;
			  if(es.getKey().equals("dirs")) {
				  LinkedList<String> ll = (LinkedList)es.getValue();
				  String language = getFirstPart((String)ll.get(0));
				  LinkedList<String> values = new LinkedList<String>();
				 for(Object y : ll) {
					 if(!language.equals(getFirstPart((String)y))) {
						 languages.put(language.trim(),values);
						 values = new LinkedList<String>();
						 language = getFirstPart((String)y);
					 }
					 values.add(getLastPart(((String)y).trim()));
				 }
			  }
			  if(es.getKey().equals("langs")) {
				  Map matches = (Map)es.getValue();
				  for(Object z : matches.entrySet()) {
					  Map.Entry element = (Map.Entry)z;
					  compliance.put((String)element.getKey(),(String)element.getValue());
				  }
			  }
		  }
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1219, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JLabel message = new JLabel("");
		message.setBounds(231, 266, 314, 34);
		
		JLabel from = new JLabel("С");
		from.setBounds(76, 10, 17, 14);
		contentPane.add(from);
		
		JLabel into = new JLabel("На");
		into.setBounds(762, 11, 17, 14);
		contentPane.add(into);
		
		final Choice language_into = new Choice();
		language_into.setBounds(785, 10, 186, 20);
		contentPane.add(language_into);
		language_into.add("");
		language_into.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				message.setText("");
				for(String x :compliance.keySet()) {
					if(compliance.get(x).equals(e.getItem())) {
						value_into = x;
						break;
					}
				}
			}
		});
		
		final Choice language_from = new Choice();
		language_from.setBounds(112, 10, 174, 20);
		contentPane.add(language_from);
		language_from.add("");
		for(String x : compliance.values()) {
			language_from.add(x);
		}
		language_from.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				message.setText("");
				value_from = (String)e.getItem();
				if(!value_from.equals("")) {
					language_into.removeAll();
					language_into.add("");
					for(String x :compliance.keySet()) {
						if(compliance.get(x).equals(e.getItem())) {
							value_from = x;
							break;
						}
					}
					for(String x : languages.get(value_from)) {
						language_into.add(compliance.get(x));
					}
				}
			}	
		});
		
		JButton translate = new JButton("Перевести");
		translate.setBounds(509, 234, 151, 23);
		contentPane.add(translate);
		
		contentPane.add(message);
		
		final JTextArea in_text = new JTextArea();
		in_text.setBounds(10, 48, 552, 20);
		contentPane.add(in_text);
		
		final JTextArea out_text = new JTextArea();
		out_text.setBounds(586, 48, 607, 23);
		contentPane.add(out_text);
		translate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				message.setText("");
				if((value_from == null || value_into == null) || ((value_from.equals("") || value_into.equals("")))) {
					message.setText("Выберите языки");
				}
				else {
					if(in_text.getText().equals("")) {
						message.setText("Введите текст");
					}
					else {
						String text = null;
						try {
							text = new String(in_text.getText().getBytes(),"UTF-8");
						} catch (UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
						String link = "https://translate.yandex.net/api/v1.5/tr.json/translate?key="+KEY+"&text="+text+"&lang="+value_from+"-"+value_into;
						Downloader d = new Downloader(link, message,containerFactory, parser, out_text);
						new Waiter(message,d.t);
					}
				}
			}
		});
		
	}
	private String getFirstPart(String total) {
		return total.substring(0,2);
	}
	private String getLastPart(String total) {
		return total.substring(3,total.length());
	}
}
