package com.xmg.crm.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xmg.crm.domain.Permission;
import com.xmg.crm.service.IPermissionService;

/**
 * 权限工具类
 * @author zhumeilu
 *
 */
@Component
public class PermissionUtil {

	private static IPermissionService permissionService;
	@Autowired
	public void setPermissionService(IPermissionService permissionService) {
		PermissionUtil.permissionService = permissionService;
	}
	/**
	 * 判断是否有权限
	 * @param expression
	 * @return
	 */
	public static boolean hasPermission(String expression) {
		Permission permission=permissionService.queryByExpression(expression);
		// 如果数据库中没有该权限,则直接放行
		if(permission==null){
			return true;
		}
		//从session中获取用户的权限
		List<Permission> userPermissions = (List<Permission>) WebContext.getSession().getAttribute(UserContext.PERMISSION_IN_SESSION);
		System.out.println("用户权限______________"+userPermissions);
		if(userPermissions==null){
			return false;
		}
		//在用户权限中查看是否有这个权限
		for (Permission permission2 : userPermissions) {
			//,如果用户拥有这个权限,则放行
			if(permission2.getExpression().equals(expression)){
				return true;
			}
		}
		//将表达式转换成ALL
		expression=expression.split(":")[0]+"ALL";
		permission = permissionService.queryByExpression(expression);
		//如果数据库中有这个权限,则在用户权限中查询
		if(permission!=null){
			for (Permission permission2 : userPermissions) {
				//遍历用户的权限,如果用户拥有这个权限,则放行
				if(permission2.getExpression().equals(expression)){
					return true;
				}
			}
		}
		
		return false;
	}

}
