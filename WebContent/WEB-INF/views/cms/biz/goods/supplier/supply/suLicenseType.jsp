<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../../../inc/injs.jsp" />
<title>管控目录</title>

<script type="text/javascript">
	$(function() {
		//数据表部分初始化
		$('#dg').datagrid({
			url : WEB_ROOT+'/cms/biz/supply/gspLicenseType/gridData',
			columns : [[ 
							{ field : 'licensetypeid', title : 'ID', align : 'center' }, 
							{ field : 'licensetypename', title : '证照类型名称', align : 'center' }, 
							{ field : 'customflag', title : '客户标志', align : 'center' }, 
							{ field : 'goodsflag', title : '货品标志', align : 'center' }, 
							{ field : 'supplierflag', title : '供应商标志', align : 'center' }, 
							{ field : 'memo', title : '备注', align : 'center' }, 
							{ field : 'earlywarndays', title : '预警天数', align : 'center' }, 
							{ field : 'superdays', title : '可超期天数', align : 'center' }, 
							{ field : 'userid', title : 'userid', align : 'center' ,hidden:true}, 
							{ field : 'credate', title : 'credate', align : 'center',hidden:true },
					]],
			singleSelect : true,
			striped : true,
			fit : true,
			border : false,
			rownumbers : true,
			toolbar : "#toolbar",
			pagination : true,
			pageSize : 100,
			pageList : [ 100, 200, 300 ],
			pageNumber : 1,
			rownumbers : true,
			onClickRow : function(index, row) {
			},
			onDblClickRow : function(index, row) {
			}
		});
	});
	
</script>

</head>
<body>
	<!-- 数据列表 -->
	<table id="dg"></table>

	<div id="toolbar">
		<form id="fm" method="post" style="border-top: 1px #DDDDDD solid">
			<div>
				<table cellpadding="0" cellspacing="0">	
					<tr>	
						<td><div class="btn-separator"></div></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon_zoom" plain="true"onclick="query()">查询</a></td>
						<td><div class="btn-separator"></div></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon_arrow_refresh" plain="true"onclick="$('#fm').form('clear')">重置</a></td>
						<td><div class='btn-separator'></div></td>
					</tr> 
				</table>
			</div>
			<!-- <div class="div-toolbar">
				用户名<input id="realname" name="realname" class="easyui-textbox"
					style="width: 80px">&nbsp;&nbsp; 
				登录名<input id="username"
					name="username" class="easyui-textbox" style="width: 80px">&nbsp;&nbsp;
			</div> -->
		</form>
	</div>
</body>
</html>