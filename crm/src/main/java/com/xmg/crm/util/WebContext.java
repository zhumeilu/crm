package com.xmg.crm.util;

import javax.servlet.http.HttpSession;

public class WebContext {

	private static ThreadLocal<HttpSession> tl=new ThreadLocal<>();
	
	public static void setSession(HttpSession session){
		tl.set(session);
	}
	public static HttpSession getSession(){
		return tl.get();
	}
	
}
