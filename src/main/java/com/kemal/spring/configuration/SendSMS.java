package com.kemal.spring.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
 
// Simple send SMS programm
public class SendSMS implements Runnable{
    
	private String destination;
	private String message;
	
	
	public SendSMS(String destination, String message) {
		this.destination = destination;
		this.message = message;
		
	}
		
	@Override
	public void run() {
		String url=null;
        StringBuilder inBuffer = new StringBuilder();
        try {
            /*url = "http://smsc.txtnation.com:8091/sms/send_sms.php" +
                "?src=" + src + "&dst=" + dst + "&type=" + type +
                "&dr=" + dr + "&user=" + user + "&password=" + password + "&msg=" + URLEncoder.encode(msg, "UTF-8") ;*/
            url="https://connectexpress.in/api/v3/index.php?method=sms&api_key=A0328dbebf911a50f3c50de0ad3bb7a60&to="+destination+"&sender=SHUBHM&message="+message+"&format=json&unicode=auto";
            System.out.println(url);
        }catch (Exception e) {
			// TODO: handle exception
		} /*catch (UnsupportedEncodingException e) {
            return null;
        }*/
        try {
            URL tmUrl = new URL(url);
            URLConnection tmConnection = tmUrl.openConnection();
            tmConnection.setDoInput(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(tmConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                inBuffer.append(inputLine);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //return inBuffer.toString()
		
	}
}

