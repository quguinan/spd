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


<jsp:include page="../../../inc/injs.jsp" />
<script type="text/javascript">
	$(function() {
		//数据表部分初始化
		$('#dg').datagrid({
			url : WEB_ROOT + '/cms/user/gridData',
			columns : [[ { field : 'userid', title : '用户编码',width:120, align : 'center' }, 
			              { field : 'realname', title : '真实名',width:120, align : 'center', editor : {type:"textbox",options : {required:true}}}, 
			              { field : 'username', title : '登录名', width:120,align : 'center', editor : {type:"textbox",options : {required:true}}}, 
			              { field : 'password', title : '用户密码', width:120,align : 'center', editor : {type:"textbox",options : {required:true}}, 
								formatter : function(value, row, index) {
									return "******";
								}
						  }, 
						  { field : 'roleid', title : '用户权限',width:200, align : 'center',required:'true',
								editor : {
									type : 'combotree',
									options : {
										url : WEB_ROOT + '/cms/user/getRoleTreedata',
										editable : false,
										required : true,
										panelHeight : 'auto'
									}
								},
								formatter : function(value, row) {
									return row.rolename;
								}
						  } , 
						  { field : 'deptid', title : '所属科室',width:150, align : 'center',
								editor : {
									type : 'combotree',
									options : {
										url : WEB_ROOT + '/cms/user/getDeptTreedata',
										editable : false,
										required : false,
										panelHeight : 'auto'
									}
								},
								formatter : function(value, row) {
									return row.deptname;
								}
						  } ,
						 /*  { field : 'orgid', title : '所属机构', width:150,align : 'center', enabled:false,
							  editor : {
									type : 'combotree',
									options : {
										url : WEB_ROOT + '/cms/user/getOrgTreedata',
										editable : false,
										required : true,
										panelHeight : 'auto'
									}
								},
								formatter : function(value, row) {
									return row.orgname;
								}
						  },  */
						  /* { field : 'orgname', title : '所属机构', width:120,align : 'center'},  */
					]],
			singleSelect : true,
			striped : true,
			fit : true,
			border : false,
			//title:'用户列表',
			rownumbers : true,
			toolbar : "#toolbar",
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30 ],
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
		//加载按钮()
		getButtons("${menuid}", $('#toolbar'), 'toolbar');
	});

	//查询
	function refreshUser() {
		$('#dg').datagrid('reload');
	};
	//新增
	function newUser() {
		endEdit();
		$("#dg").datagrid('appendRow', {});
		var rows = $("#dg").datagrid('getRows');
		$("#dg").datagrid('beginEdit', rows.length - 1);
	};
	//编辑
	function editUser() {
		endEdit();
		var row = $("#dg").datagrid('getSelected');
		if (row) {
			var rowIndex = $("#dg").datagrid('getRowIndex', row);
			$("#dg").datagrid('beginEdit', rowIndex);
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
		endEdit();
		if ($("#dg").datagrid('getChanges').length) {
			var changedRows = new Object();
			changedRows["inserted"] = $("#dg").datagrid('getChanges',"inserted");
			changedRows["deleted"] = $("#dg").datagrid('getChanges', "deleted");
			changedRows["updated"] = $("#dg").datagrid('getChanges', "updated");
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
			}, "JSON");
		}
	}
	//结束编辑
	function endEdit() {
		var rows = $("#dg").datagrid('getRows');
		for (var i = 0; i < rows.length; i++) {
			$("#dg").datagrid('endEdit', i);
		}
	}
</script>
</head>

<body>
	<!-- 数据列表 -->
	<table id="dg"></table>

	<div id="toolbar">
		<form id="fmQuery" method="post" style="border-top: 1px #DDDDDD solid">
			<div class="div-toolbar">
				用户名<input id="realname" name="realname" class="easyui-textbox"
					style="width: 80px">&nbsp;&nbsp; 登录名<input id="username"
					name="username" class="easyui-textbox" style="width: 80px">&nbsp;&nbsp;
			</div>
		</form>
	</div>
</body>
</html>
