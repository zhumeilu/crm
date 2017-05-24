package com.xmg.crm.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TreeNode{

	private String id;
	private String text;
	private List<TreeNode> children=new ArrayList<>();
}
