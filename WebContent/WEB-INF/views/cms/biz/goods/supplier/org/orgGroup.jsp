<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机构分组</title>
<jsp:include page="../../../../../inc/injs.jsp" />
<script type="text/javascript">  
		$(function(){
			
			$("#dg_doc").datagrid({
				url:WEB_ROOT+'/cms/biz/supply/orgGroup/gridDataOrgGroupDoc',   
			    columns:[[
							{field:'groupid', title:'groupid',align:'center'}, 
							{field:'groupname', title:'groupname',align:'center'}, 
			              ]],
			    singleSelect:true,
			    striped:true,
			    //rownumbers:true,
			    fit:true,
			    border:false,
			    toolbar: [	'-',{
			    				text:'查询',
								iconCls: 'fa-search',
								handler: function(){
									$('#dg_doc').datagrid('reload',WEB_ROOT+'/cms/biz/supply/orgGroup/gridDataOrgGroupDoc');
									$('#dg_dtl').datagrid('loadData', {total:0,rows:[]});  
								}
							},'-'],
			    onClickRow : function(index, row){
			    	$('#dg_dtl').datagrid('reload',WEB_ROOT+'/cms/biz/supply/orgGroup/gridDataOrgGroupDtl?groupid='+row.groupid);
			    },
			    onDblClickRow : function(index, row){
			    },
			});
			$("#dg_dtl").datagrid({
				url:'',   
			    columns:[[
							{field:'groupid', title:'groupid',align:'center' }, 
							{field:'groupname', title:'groupname',align:'center' }, 
							{field:'orgid', title:'orgid',align:'center' }, 
							{field:'orgname', title:'orgname',align:'center' }, 
							{field:'spell', title:'spell',align:'center' },  
							{field:'logname', title:'logname',align:'center' },  
							{field:'password', title:'password',align:'center' }, 
			              ]],
			    singleSelect:true,
			    striped:true,
			    //rownumbers:true,
			    fit:true,
			    border:false,
			    onClickRow : function(index, row){
			    },
			    onDblClickRow : function(index, row){
			    },
			});

		});
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',border:true" style="width:180px">
		<table id="dg_doc"></table>
	</div>
	<div data-options="region:'center',border:true">
		<table id="dg_dtl"></table>
	</div>
</body>
</html>