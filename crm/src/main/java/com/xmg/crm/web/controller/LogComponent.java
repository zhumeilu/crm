package com.xmg.crm.web.controller;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xmg.crm.domain.Employee;
import com.xmg.crm.domain.Log;
import com.xmg.crm.service.ILogService;
import com.xmg.crm.util.UserContext;
import com.xmg.crm.util.WebContext;

@Component
public class LogComponent {

	@Autowired
	private ILogService service;
	public void doLog(JoinPoint jp){
		if(jp.getTarget() instanceof ILogService){
			return ;
		}
		Log t=new Log();
		t.setDate(new Date());
		t.setOperation(jp.getTarget().getClass().getSimpleName()+":"+jp.getSignature().getName());
		t.setOperator((Employee)WebContext.getSession().getAttribute(UserContext.EMPLOYEE_IN_SESSION));
		service.save(t);
	}
}
