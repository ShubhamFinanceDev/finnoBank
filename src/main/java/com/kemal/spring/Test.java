package com.kemal.spring;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.UUID;

import com.kemal.spring.configuration.SendSMS;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
		String surveySMSHindi="प्रिय+ग्राहक,+हमारे+मूल्यवान+ग्राहक+होने+के+लिए+धन्यवाद।+यहां,+हम+एक+ग्राहक+संतुष्टि+सर्वेक्षण+कर+रहे+हैं,+और+आपकी+प्रतिक्रिया+की+सराहना+की+जाएगी।+कृपया+अपनी+प्रतिक्रिया+भरने+के+लिए+लिंक+पर+क्लिक+करें+<link>+हम+आपकी+प्रतिक्रिया+का+उपयोग+हमारी+सेवा+को+और+बेहतर+बनाने+में+के+लिए+करेंगे।%0aधन्यवाद।%0aशुभम+हाउसिंग";
		String surverySMS="Dear+Customer,+thank+you+for+being+our+valuable+customer.+Here,+we+are+conducting+a+customer+satisfaction+survey,+and+your+response+would+be+appreciated.+Please+click+the+link+to+fill+your+feedback+<link>+We+will+use+your+feedback+to+further+improve+our+service.%0aThank+you.%0aShubham+Housing";
		SendSMS sms = new SendSMS("8882900755", surveySMSHindi.replace("<link>", "https://houseportal.shubham.co:8959/submit-survey/fae86a7183884b56852a6afb14183b5e"));
		//
		Thread th = new Thread(sms);
		//th.start();
		
		System.out.println("Default Locale:   " + Locale.getDefault());
		System.out.println("Default Charset:  " + Charset.defaultCharset());
		System.out.println("file.encoding;    " + System.getProperty("file.encoding"));
		System.out.println("sun.jnu.encoding: " + System.getProperty("sun.jnu.encoding"));
		System.out.println("Default Encoding: " + getEncoding());
	      
	}
	
	public static String getEncoding()
	   {
	      final byte [] bytes = {'D'};
	      final InputStream inputStream = new ByteArrayInputStream(bytes);
	      final InputStreamReader reader = new InputStreamReader(inputStream);
	      final String encoding = reader.getEncoding();
	      return encoding;
	   }

}
