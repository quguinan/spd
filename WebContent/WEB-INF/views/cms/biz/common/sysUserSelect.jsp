<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>选择用户</title>
		
</head>
	
<body >
	<!-- 数据列表 -->
	<table class="easyui-datagrid" style="width:400px;height:250px"  id="dg_sysUser" 
        data-options="
        		url:'${pageContext.request.contextPath}/cms/common/sysUserSelect/datagrid',
        		fitColumns:true,
        		singleSelect:true,
        		toolbar:'#tb',
        		fit:true,
        		pagination:true,
        		border:false,
        		onDblClickRow:function(index, row){
        				selectSysUser();
        			}
        		"
        >   
    <thead>   
        <tr>   
            <th data-options="field:'userid',width:20,hidden:true">编码</th> 
            <th data-options="field:'realname',width:20">用户名</th>     
            <th data-options="field:'username',width:20">登录名</th>   
        </tr>   
    </thead>   
</table>  
	<!-- 表格的工具栏 -->
	<div id="tb">
		<table cellpadding='1' cellspacing='1'>   
			<tr>  
				<td>
					<form id="fm_sysUserSelect" method="post" >
						<div style="padding-left:10px; padding-top:10px;">
							名称<input  name="sysUsernameselect" class="easyui-textbox" style="width:80px">&nbsp;&nbsp;
							拼音<input  name="sysRealnameselect" class="easyui-textbox" style="width:80px">&nbsp;&nbsp;
						</div> 
					</form>
				</td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_arrow_refresh' plain='true' onclick='querySysUser()'>查询</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_tick' plain='true' onclick='selectSysUser()'>选择</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_cross' plain='true' onclick='closeSelectSysUser()'>取消</a></td>
				<td><div class='btn-separator'></div></td>
			</tr> 
		</table>
		
	</div>	
	<script type="text/javascript">  
			function querySysUser(){
				var url='${pageContext.request.contextPath}/cms/common/sysUserSelect/datagrid?'+$('#fm_sysUserSelect').serialize();
				$("#dg_sysUser").datagrid("reload",url)
			};
			function selectSysUser(){
				var row=$("#dg_sysUser").datagrid("getSelected");
				if(row){
					getSysUser(row.userid,row.username,row.realname);
					closeSelectSysUser();//选择完关闭
				}
			};
			function closeSelectSysUser(){
				//$(this).parent().window("close");
				$("#winSysUserSelect").window("close");
			};
	</script> 
</body>
</html>