<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="../../../inc/injs.jsp"/> 
	<title>自定义报表展示</title>

	<script type="text/javascript">
		var docid="${docid}";
		$(function(){
			$.post(WEB_ROOT+'/cms/biz/rpt/createGridData', { docid:docid }, 
					function(result) {
						if(result.success){ 
							/**********************************************************
							初始化$('#dg')
							**********************************************************/
							//alert(JSON.stringify(result.properties));
							$('#dg').datagrid(result.properties);//动态获取所有属性
							/*********************************************************
							初始化toolbar
							**********************************************************/
							var content = 
								"<form  id='fm' method='post' style='padding:5 0 0 0;height:auto'>"+
									"姓名<input name='name' type='text' class='easyui-textbox' style='width:130px' editable='false'  />&nbsp;&nbsp;"+
									"日期<input name='dt1' type='text' class='easyui-datebox' style='width:130px' editable='false'  />至"+
									"    <input name='dt2' type='text' class='easyui-datebox' style='width:130px' editable='false'  />"+
								"</form>";
							$('#toolbar').append(content);
							//重新渲染组件
							$.parser.parse($('#fm'));
						}
				}, "JSON"); 
			//query();
		});	
		
		function query(){
			$.post(WEB_ROOT+'/cms/biz/rpt/displayGridData', { docid:docid }, 
					function(joData) {
						//alert(JSON.stringify(joData));
						$('#dg').datagrid("loadData",joData);
					}, "JSON"); 
		};
	</script>
</head>
<body>
	<table id='dg'></table>
	<div id="toolbar" >
		<table cellpadding="0" cellspacing="0">	
			<tr>	
				<td><div class="btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon_zoom" plain="true"onclick="query()">查询</a></td>
				<td><div class="btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon_arrow_refresh" plain="true"onclick="$('#fm').form('clear')">重置</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon_arrow_refresh" plain="true"onclick="alert(导出)">导出</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon_printer" plain="true"onclick="alert(打印)">打印</a></td>
				<td><div class='btn-separator'></div></td>
			</tr> 
		</table>
	</div>
</body>
</html>