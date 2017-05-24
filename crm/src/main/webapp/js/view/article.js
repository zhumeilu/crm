$(function(){
	var articleDatagrid,articleDialog,articleDialogForm,articleShow;
	articleDatagrid = $("#article_datagrid");
	articleDialog = $("#article_dialog");
	articleDialogForm = $("#article_dialog_form");
	articleShow = $("#article_show");
	articleDatagrid.datagrid({
		url:'/article_list.do',
		fit:true,
		fitColumns:true,
		toolbar:'#article_bt',
		pagination:true,
		singleSelect:true,
		border:false,
		columns:[[
		          {field:'title',width:1,title:'标题',align:'center'},
		          {field:'author.nickName',width:1,title:'作者',align:'center'},
		          {field:'publishedTime',width:1,title:'发表日期',align:'center'}
		          ]],
		onDblClickRow:function(index,record){
			articleShow.dialog({
				title:record.title,
				content:record.content_highlight,
				modal:true,
				closed:false
			});
		}
	});
	articleDialog.dialog({
		width:300,
		height:250,
		buttons:'#article_dialog_bb',
		closed:true
	});
	
	articleShow.dialog({
		width:500,
		height:300,
		closed:true
	});
	var cmdObj = {
			add:function(){
				articleDialog.dialog("open");
				articleDialog.dialog("setTitle", "新增");
				articleDialogForm.form("clear");
			},
			edit:function(){
				var rowData = articleDatagrid.datagrid("getSelected");
				if (rowData) {
					articleDialog.dialog("open");
					articleDialog.dialog("setTitle", "编辑");
					articleDialogForm.form("clear");
					articleDialogForm.form("load", rowData);
				} else {
					$.messager.alert("温馨提示", "请选中需要编辑的数据", "info");
				}
			},
			del:function(){
				var rowData = articleDatagrid.datagrid("getSelected");
				if (rowData) {
					$.messager.confirm("温馨提示", "您确定需要删除该数据吗?", function(yes) {
						if (yes) {
							$.get("/article_delete.do?aid=" + rowData.id, function(data) {
								$.messager.alert("温馨提示", data.msg, "info", function() {
									articleDatagrid.datagrid("load");
								});
							}, "json");
						}
					});
				} else {
					$.messager.alert("温馨提示", "请选中需要删除的数据", "info");
				}
			},
			refresh:function(){
				articleDatagrid.datagrid("reload");
			},
			updateIndex:function(){},
			save:function(){
				var url = "";
				var id = $("[name='id']").val();
				if (id) {
					url = "/article_update.do";
				} else {
					url = "/article_save.do";
				}
				articleDialogForm.form("submit", {
					url : url,
					success : function(data) {
						data = $.parseJSON(data);
						if (data.success) {
							$.messager.alert("温馨提示", data.msg, "info", function() {
								articleDialog.dialog("close");
								articleDatagrid.datagrid("load");
							});
						} else {
							$.messager.alert("温馨提示", data.msg, "info");
						}
					}
				});
			},
			cancel:function(){
				articleDialog.dialog("close");
			},
			search:function(){
				articleDatagrid.datagrid("load",{keyContent:$("#keyContent").val()});
			}
	}
	
	$("a[data-cmd]").on("click",function(){
		var cmd = $(this).data("cmd");
		if(cmd){
			cmdObj[cmd]();
		}
	});
});