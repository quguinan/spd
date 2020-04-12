<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<jsp:include page="../../../../inc/injs.jsp"></jsp:include>

</head>
<script type="text/javascript">
	$(function() {
		$("#dg")
				.datagrid(
						{
							url : "${pageContext.request.contextPath}/cms/common/suComSupplySelect/datagrid?supplytype=机构",
							fitColumns : true,
							singleSelect : true,
							title : '供应商及机构',
							fit : true,
							border : false,
							rownumbers : true,
							columns : [ [ {
								field : 'supplyid',
								title : '机构ID	',
								width : 20,
								align : 'left'
							}, {
								field : 'supplyname',
								title : '机构名称',
								width : 80,
								align : 'left'
							}, {
								field : 'spell',
								title : '拼音',
								width : 80,
								align : 'left'
							}, {
								field : 'sucomtype',
								title : '类型',
								width : 80,
								align : 'left'
							} ] ]

						})

	});
</script>
<body class="easyui-layout">
	<div data-options="region:'west',border:true" style="width: 100%">
		<table id="dg"></table>
	</div>
</body>
</html>