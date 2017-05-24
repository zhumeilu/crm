package com.xmg.crm.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

public class CommUtil {

	/**
	 * 把指定的复杂对象属性，按照指定的内容，封装到新的map中
	 * @param source 目标对象
	 * @param ps     需要封装到map中的属性
	 * @return
	 */
	public static List<Map<String,Object>> obj2List(List<Object> sources, String[] ps, String[] key){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for (Object source : sources) {
			list.add(obj2map(source,ps,key));
		}
		return list;
	}
	public static Map<String, Object> obj2map(Object source, String[] ps,String[] key) {
		Map<String, Object> map = new HashMap<>();
		if (source == null)
			return null;
		if (ps == null || ps.length < 1) {
			return null;
		}
		Object temp;
		String p;
		for (int i=0;i<ps.length;i++) {
			if(i>=key.length){
				break;
			}
			p = ps[i];
			temp = source;
			PropertyDescriptor sourcePd = null;
			if(p.indexOf(".")>0){
				PropertyDescriptor param = BeanUtils.getPropertyDescriptor(
						temp.getClass(), p.substring(0,p.indexOf(".")));
				if(param!=null && param.getReadMethod() != null){
					try {
						Method readMethod = param.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass()
								.getModifiers())) {
							readMethod.setAccessible(true);
						}
						temp = readMethod.invoke(temp, new Object[0]);
					} catch (Exception ex) {
						throw new RuntimeException(
								"Could not copy properties from source to target",
								ex);
					}
					if(temp==null){
						map.put(key[i], null);
						continue;
					}
					sourcePd = BeanUtils.getPropertyDescriptor(
							temp.getClass(), p.substring(p.indexOf(".")+1));
				}else{
					continue;
				}
			}else{
				sourcePd = BeanUtils.getPropertyDescriptor(
						temp.getClass(), p);
			}
			if (sourcePd != null && sourcePd.getReadMethod() != null) {
				try {
					Method readMethod = sourcePd.getReadMethod();
					if (!Modifier.isPublic(readMethod.getDeclaringClass()
							.getModifiers())) {
						readMethod.setAccessible(true);
					}
					Object value = readMethod.invoke(temp, new Object[0]);
					map.put(key[i], value);
				} catch (Exception ex) {
					throw new RuntimeException(
							"Could not copy properties from source to target",
							ex);
				}
			}
		}
		return map;
	
	}
	public static Map<String, Object> obj2map(Object source, String[] ps) {
		return obj2map(source,ps,ps);
	}

	
}
