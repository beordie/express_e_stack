package com.beordie.wx.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class TicketUtil {

	private static String ticket;
	private static long oldTime = 0;

	public static String getTicket() {
		long newTime = System.currentTimeMillis();
		if (newTime - oldTime >= 7100000) {
			oldTime = newTime;
			try {
				setTicket();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return ticket;
	}


	private static void setTicket() throws Exception {
		URL url = new URL("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+TokenUtil.getToken()+"&type=jsapi");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String text = null;
		while((text = br.readLine())!=null) {
			sb.append(text);
		}
		br.close();
		System.out.println(sb.toString());
		JSONObject obj = new JSONObject(sb.toString());
		ticket = obj.getString("ticket");
	}
}
