<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机构目录</title>
<jsp:include page="../../../../../inc/injs.jsp" />
<script type="text/javascript">
	$(function() {
		$("#dg_org").datagrid({
			url:WEB_ROOT+'/cms/biz/supply/org/gridDataOrg',   
		    columns:[[
						{field:'orgid', title:'机构ID',align:'center' }, 
						{field:'orgname', title:'机构名称',align:'center' }, 
						{field:'spell', title:'拼音',align:'center' }, 
						{field:'logname', title:'机构接口用户名',align:'center' }, 
						{field:'password', title:'机构接口密码',align:'center' }, 
		              ]],
		    singleSelect:true,
		    striped:true,
		    rownumbers:true,
		    fit:true,
		    border:false,
		    toolbar: "#tb_org",
		    onClickRow : function(index, row){
		    	$("#dg_suVSorg").datagrid("reload",WEB_ROOT+'/cms/biz/supply/org/gridDataSupplyOrg?orgid='+row.orgid);
		    },
		    onDblClickRow : function(index, row){
		    },
		});
		$("#dg_suVSorg").datagrid({
			url:'',   
		    columns:[[
						{field:'orgid', title:'机构ID',align:'center',hidden:true }, 
						{field:'orgname', title:'机构名称',align:'center',hidden:true }, 
						{field:'supplyid', title:'供应商ID',align:'center' }, 
						{field:'supplyname', title:'供应商名称',align:'center' }, 
						{field:'logname', title:'供应商接口用户名',align:'center' }, 
						{field:'password', title:'供应商接口密码',align:'center' }, 
		              ]],
		    singleSelect:true,
		    striped:true,
		    rownumbers:true,
		    fit:true,
		    border:false,
		    toolbar: "#tb_suVSorg",
		    onClickRow : function(index, row){
		    },
		    onDblClickRow : function(index, row){
		    },
		});
	});
	function query(){
		var url=WEB_ROOT+'/cms/biz/supply/org/gridDataOrg';
		$("#dg_org").datagrid("reload",url);
	}
	function query2(){
		var row = $("#dg_org").datagrid('getSelected');
		if (row) {
			var url=WEB_ROOT+'/cms/biz/supply/org/gridDataSupplyOrg?orgid='+row.orgid;
			$("#dg_suVSorg").datagrid("reload",url);
		}
	}
	function add(){
		alert('增加')
	}
	function addSupply(){
		var row = $("#dg_org").datagrid('getSelected');
		if (row) {
			$("#winSupplySelect").window("open");
		}else{
			$.messager.show({title: '提示',msg: '请首先选择机构！',showType:'show'});
		}
	}
	function saveSuSupply(){
		alert('saveSuSupply')
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',border:true" style="width:450px">
		<table id="dg_org"></table>
		<div id="tb_org">
			<div>
				<table cellpadding="0" cellspacing="0">	
					<tr>	
						<td><div class="btn-separator"></div></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-plus" plain="true"onclick="add()">增加机构</a></td>
						<td><div class="btn-separator"></div></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-refresh" plain="true"onclick="query()">刷新</a></td>
						<td><div class='btn-separator'></div></td>
					</tr> 
				</table>
			</div>
		</div>
	</div>
	<div data-options="region:'center',border:true">
		<table id="dg_suVSorg"></table>
		<div id="tb_suVSorg">
			<div>
				<table cellpadding="0" cellspacing="0">	
					<tr>	
						<td><div class="btn-separator"></div></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-plus" plain="true"onclick='addSupply()'>增加供应商</a></td>
						<td><div class="btn-separator"></div></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-refresh" plain="true"onclick="query2()">刷新</a></td>
						<td><div class='btn-separator'></div></td>
					</tr> 
				</table>
			</div>
		</div>
	</div>
	
	<!-- 平台供应商  begin -->
	//窗口名winSupplySelect固定，不要改
	<div id="winSupplySelect" class="easyui-window" title="供应商平台-选择供应商" style="width:600px;height:430px"   
        data-options="iconCls:'icon_attach',modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false,
        			href:'${pageContext.request.contextPath}/cms/common/suSupplySelect'"></div>
	<script type="text/javascript">
		//方法名getSupplyid固定，不要改
		//选择完后，根据goodsid,goodsdtlid处理
		function getSupply(supplyid,supplyname){
			var row = $("#dg_org").datagrid('getSelected');
			if (row) {
			 $.post(WEB_ROOT+'/cms/biz/supply/supply/selectSupply',{orgid:row.orgid,supplyid:supplyid},
					function(result){
						if (result.success){
							$.messager.show({title: '提示',msg: result.msg,showType:'show'});
							query2();
						} 
					},'json'); 
			}
			//saveSuSupply(); 
		}
	</script>
	<!-- 平台供应商  end -->
</body>
</html>