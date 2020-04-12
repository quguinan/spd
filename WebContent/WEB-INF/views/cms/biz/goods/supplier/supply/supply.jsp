<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商目录(总的)</title>
<jsp:include page="../../../../../inc/injs.jsp" />
</head>
<script type="text/javascript">
	$(function() {
		$("#dg").datagrid({
			url:WEB_ROOT+'/cms/biz/supply/supply/gridDataSupply',   
		    columns:[[
						{field:'supplyid', title:'供应商ID',align:'center' }, 
						{field:'supplycode', title:'供应商编码',align:'center' }, 
						{field:'supplyname', title:'供应商名称',align:'center' }, 
						{field:'spell', title:'拼音',align:'center' }, 
						{field:'categoryid', title:'证照管控',align:'center',width:180,
							editor : {
								type : 'combobox',
								options : {
									url : WEB_ROOT+'/cms/biz/supply/supply/findAllCategroyjson',
									valueField: "docid",
		                            textField: "name",
									editable : false,
									required : false,
									panelHeight : 180
								}
							},
							formatter : function(value, row) {
								return row.categoryname;
							}
						}, 
						{field:'categoryname', title:'证照管控名称',align:'center',width:200,hidden:true}, 
						{field:'status', title:'状态',align:'center',
							formatter : function(value, row) {
								if (value=='1'){
									return '已审核';
								}else{
									return '未审核';
								}
								
							}
						}, 
		              ]],
		    singleSelect:true,
		    striped:true,
		    rownumbers:true,
		    fit:true,
		    border:false,
		    toolbar: "#tb_su",
		    onClickRow : function(index, row){
		    	endEdit($("#dg"));
		    },
		    onDblClickRow : function(index, row){
		    	endEdit($("#dg"));
		    	edit($("#dg"));
		    },
		});
	});
	//保存
	function save() {
		endEdit($("#dg"));
		if ($("#dg").datagrid('getChanges').length) {
			var changedRows = new Object();
			changedRows["inserted"] = $("#dg").datagrid('getChanges',"inserted");
			changedRows["deleted"] = $("#dg").datagrid('getChanges', "deleted");
			changedRows["updated"] = $("#dg").datagrid('getChanges', "updated");
			//alert(JSON.stringify(changedRows))
			$.post(WEB_ROOT+'/cms/biz/supply/supply/save', {
				json : JSON.stringify(changedRows)
			}, function(result) {
				$.messager.show({ title : '提示', msg : result.msg, showType : 'show' });
				refresh();
			}, "JSON");
		}
	};
	//刷新
	function refresh() {
		$('#dg').datagrid('reload');
	};
</script>
<body >
	<table id="dg"></table>
	<div id="tb_su">
		<table cellpadding="0" cellspacing="0">	
			<tr>	
				<td><div class="btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-plus" plain="true"onclick='$("#winComSupplySelect").window("open")'>增加</a></td>
				<td><div class="btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-refresh" plain="true"onclick="refresh()">刷新</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-save" plain="true"onclick="save()">保存</a></td>
				<td><div class='btn-separator'></div></td>
			</tr> 
		</table>
	</div>
	
	
	
	<!-- com供应商  begin -->
	//窗口名winComSupplySelect固定，不要改
	<div id="winComSupplySelect" class="easyui-window" title="供应商平台-选择供应商" style="width:600px;height:430px"   
        data-options="iconCls:'icon_attach',modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false,
        			href:'${pageContext.request.contextPath}/cms/common/suComSupplySelect'"></div>
	<script type="text/javascript">
		//方法名getSupplyid固定，不要改
		//选择完后，根据goodsid,goodsdtlid处理
		function getComSupply(supplyid,supplyname){
			 $.post(WEB_ROOT+'/cms/biz/supply/supply/selectComSupply',{supplyid:supplyid},
					function(result){
						if (result.success){
							$.messager.show({title: '提示',msg: result.msg,showType:'show'});
							refresh();
						}
					},'json'); 
					//save();
		}
	</script>
	<!-- com供应商  end -->
</body>
</html>