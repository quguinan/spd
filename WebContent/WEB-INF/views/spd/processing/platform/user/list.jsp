<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>用户信息维护</title>


<jsp:include page="../../../../inc/injs.jsp" />
<script type="text/javascript">
	$(function() {
		//数据表部分初始化
		var dg = $('#dg')
				.datagrid(
						{
							url : WEB_ROOT + '/cms/user/gridData',
							columns : [ [
									{
										field : 'userid',
										title : '用户编码',
										width : 80,
										align : 'center'
									},
									{
										field : 'realname',
										title : '真实名',
										width : 100,
										align : 'center',
										editor : {
											type : "textbox",
											options : {
												required : true
											}
										}
									},
									{
										field : 'username',
										title : '登录名',
										width : 100,
										align : 'center',
										editor : {
											type : "textbox",
											options : {
												required : true
											}
										}
									},
									{
										field : 'password',
										title : '用户密码',
										width : 100,
										align : 'center',
										editor : {
											type : "textbox",
											options : {
												required : true
											}
										},
										formatter : function(value, row, index) {
											return "******";
										}
									},
									{
										field : 'roleid',
										title : '用户权限',
										width : 82,
										align : 'center',
										required : 'true',
										editor : {
											type : 'combotree',
											options : {
												url : WEB_ROOT
														+ '/cms/user/getRoleTreedata',
												editable : false,
												required : true,
												panelHeight : 'auto'
											}
										},
										formatter : function(value, row) {
											return row.rolename;
										}
									},
									{
										field : 'gspcatoryid',
										title : '类别',
										align : 'center',
										width : 80,
										editor : {
											type : 'combobox',
											options : {
												url : WEB_ROOT
														+ '/cms/biz/supply/supply/findByCategroyjson',
												valueField : "docid",
												textField : "name",
												editable : false,
												required : false,
												panelHeight : 180
											}
										},
										formatter : function(value, row) {
											return row.gspcatoryname;
										}
									}, {
										field : 'supplyid',
										title : '供应商ID',
										width : 50,
										align : 'center',
										editor : {
											type : "textbox"
										}
									}, {
										field : 'supplyname',
										title : '供应商',
										width : 320,
										align : 'center',
										editor : {
											type : "textbox"
										}
									}

							] ],
							singleSelect : true,
							striped : true,
							fit : true,
							border : false,
							title : '用户信息',
							//title:'用户列表',
							rownumbers : true,
							toolbar : "#toolbar",
							pageNumber : 1,
							rownumbers : true,
							onClickRow : function(index, row) {
								endEdit();
							},
							onDblClickRow : function(index, row) {
								endEdit();
								editUser();
							}
						});

		var dtl = $("#dtl").datagrid({
			rownumbers : true,
			onDblClickRow : function(index, row) {
				selectComSupply(index, row);
			}
		});
		dg.datagrid('enableFilter');
		dtl.datagrid('enableFilter');
		//加载按钮()
		getButtons("${menuid}", $('#toolbar'), 'toolbar');
	});

	//查询
	function refreshUser() {
		$('#dg').datagrid('reload');
	};
	//新增
	function newUser() {

		if (endEdit()) {
			$("#dg").datagrid('appendRow', {});
			var rows = $("#dg").datagrid('getRows');
			$("#dg").datagrid('beginEdit', rows.length - 1);
			//alert(rows.length-1);
			$("#dg").datagrid("selectRow", rows.length - 1);
		}
	};
	//编辑
	function editUser() {
		if (endEdit()) {
			var row = $("#dg").datagrid('getSelected');
			if (row) {
				var rowIndex = $("#dg").datagrid('getRowIndex', row);
				$("#dg").datagrid('beginEdit', rowIndex);
			}
		}
	};
	//删除
	function deleteUser() {
		endEdit();
		var row = $("#dg").datagrid('getSelected');
		if (row) {
			var rowIndex = $("#dg").datagrid('getRowIndex', row);
			$("#dg").datagrid('deleteRow', rowIndex);
		}
	}
	//保存
	function saveUser() {
		if (endEdit()) {

			if ($("#dg").datagrid('getChanges').length) {
				var changedRows = new Object();
				changedRows["inserted"] = $("#dg").datagrid('getChanges',
						"inserted");
				changedRows["deleted"] = $("#dg").datagrid('getChanges',
						"deleted");
				changedRows["updated"] = $("#dg").datagrid('getChanges',
						"updated");
				$.post(WEB_ROOT + '/cms/user/gridSave', {
					json : JSON.stringify(changedRows)
				}, function(result) {
					if (result.success) {
						$('#dg').datagrid('reload');
					}
					$.messager.show({
						title : '提示',
						msg : result.msg,
						showType : 'show'
					});

					$('#dg').datagrid('acceptChanges');

				}, "JSON");
			}
		}
	}
	//结束编辑
	function endEdit() {
		var rows = $("#dg").datagrid('getRows');
		for (var i = 0; i < rows.length; i++) {
			//表单验证
			if (!$('#dg').datagrid('validateRow', i)) {
				$.messager.show({
					title : '提示',
					msg : "必填项不能空!",
					showType : 'show'
				});
				$('#dg').datagrid('selectRow', i);// 关键在这里
				return false;
			}
			//结束编辑
			$("#dg").datagrid('endEdit', i);
		}
		return true;
	}
	function selectComSupply(index, row) {
		//判断是否选中用户
		var dgselected = $("#dg").datagrid('getSelected');
		if (dgselected == null) {
			alert("请选择一个用户");
			return;
		}

		//得到选中的供应商ID和供应商名称
		var supplyid = row.supplyid;
		var supplyname = row.supplyname;
		var sucomtype = row.sucomtype;

		//得到总单选中的索引
		var rowIndex = $('#dg').datagrid('getRowIndex',
				$('#dg').datagrid('getSelected'));
		//将总单设置为编辑状态
		$('#dg').datagrid('beginEdit', rowIndex);
		//获得到需要编辑行的值
		var editors = $('#dg').datagrid('getEditors', rowIndex);
		//需要赋值的列
		var newSupplyid = editors[5];
		var newSupplyname = editors[6];
		var row = $('#dtl').datagrid('getSelected');
		var roleid = dgselected.roleid;
		if (roleid == '004') {
			$.messager.alert('提示信息', 'SU管理员不需要对应！', 'info');
			endEdit();
			return;
		}
		if (sucomtype == '机构') {
			if (roleid !== '002') {
				$.messager.alert('提示信息', '机构不能对应非SU医院端！', 'info');
				endEdit();
				return;
			}
		}
		if (sucomtype == '供应商') {
			if (roleid !== '003') {
				$.messager.alert('提示信息', '供应商不能对应非SU供应商端！', 'info');
				endEdit();
				return;
			}
		}
		//赋值
		newSupplyid.target.textbox('setValue', supplyid);
		newSupplyname.target.textbox('setValue', supplyname);
		//结束编辑
		endEdit();
		//alert(JSON.stringify(editors));
	}
</script>
</head>
<body class="easyui-layout">
	<!-- 数据列表 -->
	<div data-options="region:'west',border:true" style="width: 48%">
		<table id="dg"></table>
	</div>
	<div data-options="region:'center',border:true">
		<!-- 数据列表 -->
		<table class="easyui-datagrid" id="dtl"
			data-options="
        		url:'${pageContext.request.contextPath}/cms/common/suComSupplySelect/datagrid',
        		fitColumns:true,
        		singleSelect:true,
        		toolbar:'#tb',title:'供应商及机构',
        		fit:true,
        		border:false
        		">
			<thead>
				<tr>
					<th data-options="field:'supplyid',width:5">ID</th>
					<th data-options="field:'supplyname',width:20">供应商名称</th>

					<th data-options="field:'spell',width:20">拼音</th>
					<th data-options="field:'sucomtype',width:20">类型</th>

				</tr>
			</thead>
		</table>
	</div>
	<div id="toolbar">
		<%-- <form id="fmQuery" method="post" style="border-top: 1px #DDDDDD solid">
			<div class="div-toolbar">
				用户名<input id="realname" name="realname" class="easyui-textbox"
					style="width: 80px">&nbsp;&nbsp; 登录名<input id="username"
					name="username" class="easyui-textbox" style="width: 80px">&nbsp;&nbsp;
			</div>
		</form> --%>
	</div>
</body>
</html>
