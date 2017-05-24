package com.xmg.crm.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xmg.crm.util.NoPermission;
import com.xmg.crm.util.PermissionUtil;
import com.xmg.crm.util.UserContext;
import com.xmg.crm.util.WebContext;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//将session设置到当前线程中
		WebContext.setSession(request.getSession());
		//如果session中为空,并且布置login请求,就将其重定向到login.jsp
		if(!request.getRequestURI().equals("/login.do")&&request.getSession().getAttribute(UserContext.EMPLOYEE_IN_SESSION)==null){
			response.sendRedirect("/login.jsp");
			return false;
		}
		//进行权限控制
		HandlerMethod hm=(HandlerMethod) handler;
		//注解判断
		if(hm.getMethod().isAnnotationPresent(NoPermission.class)){
			return true;
		}
		//表达式判断
		String expression = hm.getBean().getClass().getName()+":"+hm.getMethod().getName();
		System.out.println(expression);
		if(!PermissionUtil.hasPermission(expression)){
			//根据用户的请求方式,返回json还是页面
			if(hm.getMethod().isAnnotationPresent(ResponseBody.class)){
				//返回json数据
				response.sendRedirect("/noPermission.json");
			}else{
				//返回页面
				response.sendRedirect("/noPermission.jsp");
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
