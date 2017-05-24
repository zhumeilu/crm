package com.xmg.crm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmg.crm.domain.TreeNode;

public class ParseJson {

	/**
	 * 格式转换
	 * [{id:1,parent.id:null},{id:2,parent.id:1}]
	 * [id:1,parent.id:null,children:[{id:2,parent.id:1,children:[]}]]
	 * @param rows
	 * @return
	 */
	public static List parse(List rows) {
		List<TreeNode> list=new ArrayList<>();
		Map<String,TreeNode> bigMap=new HashMap<>();
		//遍历所有rows的map,将其封装到TreeNode对象中
		for (Object object : rows) {
			Map<String,String> map=(Map<String,String>)object;
			TreeNode node=new TreeNode();
			node.setId(String.valueOf(map.get("id")));
			node.setText(String.valueOf(map.get("name")));
			//将TreeNode存储到map中,id,node
			bigMap.put(String.valueOf(map.get("id")), node);
		}
		//遍历rows,判断其parent.id是否为空,如果为空,则将其对应的node设置到list中,如果不为空,那么找到其父分类,将其设置到父类中的children中
		for (Object object : rows) {
			Map<String,Object> map=(Map<String,Object>)object;
			if(map.get("parent.id")==null){
				//父部门为null,则为根部门
				list.add(bigMap.get(String.valueOf(map.get("id"))));
			}else{
				bigMap.get(String.valueOf(map.get("parent.id"))).getChildren().add(bigMap.get(String.valueOf(map.get("id"))));
			}
		}
		return list;
	}

}
