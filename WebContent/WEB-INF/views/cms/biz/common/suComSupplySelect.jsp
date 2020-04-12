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
    <title>选择供应商平台接口供应商</title>
		
</head>
	
<body >
	<!-- 数据列表 -->
	<table class="easyui-datagrid" style="width:400px;height:250px"  id="dg_supply" 
        data-options="
        		url:'${pageContext.request.contextPath}/cms/common/suComSupplySelect/datagrid',
        		fitColumns:true,
        		singleSelect:true,
        		toolbar:'#tb',
        		fit:true,
        		pagination:true,
        		border:false,
        		onDblClickRow:function(index, row){
        				selectComSupply();
        			}
        		"
        >   
    <thead>   
        <tr>   
            <th data-options="field:'supplyid',width:20,hidden:true">id</th>   
            <th data-options="field:'supplyname',width:20">名称</th>   
            <th data-options="field:'supplycode',width:20">编码</th>   
            <th data-options="field:'spell',width:20">拼音</th>    
        </tr>   
    </thead>   
</table>  
	<!-- 表格的工具栏 -->
	<div id="tb">
		<table cellpadding='1' cellspacing='1'>   
			<tr>  
				<td>
					<form id="fm_supplySelect" method="post" >
						<div style="padding-left:10px; padding-top:10px;">
							名称<input  name="supplynameselect" class="easyui-textbox" style="width:80px">&nbsp;&nbsp;
							拼音<input  name="supplyspellselect" class="easyui-textbox" style="width:80px">&nbsp;&nbsp;
						</div> 
					</form>
				</td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_arrow_refresh' plain='true' onclick='queryComSupply()'>查询</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_tick' plain='true' onclick='selectComSupply()'>选择</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_cross' plain='true' onclick='closeSelectComSupply()'>取消</a></td>
				<td><div class='btn-separator'></div></td>
			</tr> 
		</table>
	</div>	
	<script type="text/javascript">  
			function queryComSupply(){
				var url='${pageContext.request.contextPath}/cms/common/suComSupplySelect/datagrid?'+$('#fm_supplySelect').serialize();
				$("#dg_supply").datagrid("reload",url)
			};
			function selectComSupply(){
				var row=$("#dg_supply").datagrid("getSelected");
				if(row){
					getComSupply(row.supplyid,row.supplyname);
					closeSelectComSupply();//选择完关闭
				}
			};
			function closeSelectComSupply(){
				//$(this).parent().window("close");
				$("#winComSupplySelect").window("close");
			};
	</script> 
</body>
</html>