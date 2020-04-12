<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../inc/injs.jsp"/>
	<script>

	$(function(){ 
		$('body').layout({
			fit : true
		});
		$("#mytree2").tree({
			url : WEB_ROOT + "/cms/sysdict/dict/treeData",
			cascadeCheck : false,
		    onlyLeafCheck : true,
		    dnd : true,
		    lines : true,
		    animate : true,
		    iconCls: 'icon-add',
			onSelect : function(nodedata) {
				$('#grid').datagrid("load", WEB_ROOT + "/cms/sysdict/dict/gridData?pid="+nodedata.id);
			}
		});
							
		$('#grid').datagrid({	
			url:WEB_ROOT + "/cms/sysdict/dict/gridData",
			fit:true,
			singleSelect:true,
		    striped:true,
		    border:false,
			columns : 
			[[ 
			    { title : '编码', field : 'value', width : 80, align : 'left', editor : "textbox" }, 
				{ title : '名称', field : 'text', width : 100, align : 'left', editor : "textbox" }
			]] ,
			toolbar: "#toolbar"
		}); 
		//加载按钮()
      	getButtons("${menuid}",$('#toolbar'),'toolbar');
	});
	function add(){
		alert("add");
	}
	function del(){
		alert("del");
	}
	function save(){
		alert("save");
	}
	function query(){
		alert("query");
	}

	</script>
</head>
<body >
		<div id="west" data-options="region:'west',split:false,title:'系统字典',collapsible:false" style="width:150px;">
			<ul id="mytree2"></ul>
		</div>
		<div id="Center" data-options="region:'center'">
			<table id="grid"></table>
		</div>
		
		<div id="toolbar"> </div>
</body>
</html>