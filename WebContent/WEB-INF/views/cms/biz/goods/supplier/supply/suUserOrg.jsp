<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平台机构用户维护</title>
<jsp:include page="../../../../../inc/injs.jsp" />
<script type="text/javascript">
	$(function() {
		$("#dg").datagrid({
			url:WEB_ROOT+'/cms/biz/supply/userOrg/gridDataUser',   
		    columns:[[
						{field:'userid', title:'用户id',align:'center' }, 
						{field:'realname', title:'用户名',align:'center' }, 
						{field:'username', title:'登录名',align:'center' }, 
						{field:'usertype', title:'用户类型',align:'center',
							formatter : function(value, row) {
								if(value==1){
									return '机构用户';
								}else if(value==2){
									return '供应商用户';
								}
							}
						}, 
						{field:'groupid', title:'机构分组',align:'center',width:200 ,
							editor : {
								type : 'combobox',
								options : {
									url : WEB_ROOT+'/cms/biz/supply/orgGroup/findAlljson',
									valueField: "groupid",
		                            textField: "groupname",
									editable : false,
									required : true,
									panelHeight : 'auto'
								}
							},
							formatter : function(value, row) {
								return row.groupname;
							}
						}, 
						{field:'groupname', title:'机构分组名称',align:'center',width:200,hidden:true}, 
						{field:'stopped', title:'停用标志',align:'center' }, 
		              ]],
		    singleSelect:true,
		    striped:true,
		    rownumbers:true,
		    fit:true,
		    border:false,
		    toolbar: "#tb_user",
		    onClickRow : function(index, row){
		    	endEdit($("#dg"));
		    },
		    onDblClickRow : function(index, row){
		    	endEdit($("#dg"));
		    	edit($("#dg"));
		    },
		});
	});
	
	//查询
	function refresh(){
		$('#dg').datagrid('reload',WEB_ROOT+'/cms/biz/supply/userOrg/gridDataUser');
	};
	//保存
	function saveUser() {
		endEdit($("#dg"));
		if ($("#dg").datagrid('getChanges').length) {
			var changedRows = new Object();
			changedRows["inserted"] = $("#dg").datagrid('getChanges',"inserted");
			changedRows["deleted"] = $("#dg").datagrid('getChanges', "deleted");
			changedRows["updated"] = $("#dg").datagrid('getChanges', "updated");
			$.post(WEB_ROOT+'/cms/biz/supply/userOrg/saveUser', {
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
	//删除
	function deleteUser() {
		endEdit($("#dg"));
		var row = $("#dg").datagrid('getSelected');
		if (row) {
			var rowIndex = $("#dg").datagrid('getRowIndex', row);
			$("#dg").datagrid('deleteRow', rowIndex);
		}
	}
	</script>
</head>
<body>
	<table id="dg"></table>
	<div id="tb_user">
			<div>
				<table cellpadding="0" cellspacing="0">	
					<tr>	
						<td><div class="btn-separator"></div></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-user-plus" plain="true"onclick='$("#winSysUserSelect").window("open")'>增加</a></td>
						<td><div class="btn-separator"></div></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-user-circle-o" plain="true"onclick="saveUser()">保存</a></td>
						<td><div class='btn-separator'></div></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-user-times" plain="true"onclick="deleteUser()">删除</a></td>
						<td><div class='btn-separator'></div></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-refresh" plain="true"onclick="refresh()">刷新</a></td>
						<td><div class='btn-separator'></div></td>
					</tr> 
				</table>
			</div>
	</div>
	
	<!-- 用户列表  begin -->
	//窗口名winSysUserSelect固定，不要改
	<div id="winSysUserSelect" class="easyui-window" title="选择系统用户" style="width:600px;height:430px"   
        data-options="iconCls:'icon_attach',modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false,
        			href:'${pageContext.request.contextPath}/cms/common/sysUserSelect'"></div>
	<script type="text/javascript">
		//方法名getSysUser固定，不要改
		//选择完货品后，根据userid,username处理
		function getSysUser(userid,username,realname){
			var row=$('#dg').datagrid('appendRow',{ userid: userid, realname:realname,username:username,usertype:'1' });//机构用户
			saveUser();
			/* endEdit($("#dg"));
	    	edit($("#dg")); */
		}
	</script>
	<!-- 用户列表  end -->
</body>
</html>