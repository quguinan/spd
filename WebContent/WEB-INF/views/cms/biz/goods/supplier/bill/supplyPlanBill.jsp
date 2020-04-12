<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商-计划反馈</title>
<jsp:include page="../../../../../inc/injs.jsp" />
<script type="text/javascript">
	$(function() {
		$("#dg_doc")
				.datagrid(
						{
							url : WEB_ROOT
									+ '/cms/biz/suplan/SupplyPlanBill/gridDataDoc',
							columns : [ [ {
								field : 'orgid',
								title : 'docid',
								align : 'center',
								hidden : true
							}, {
								field : 'supplyid',
								title : 'docid',
								align : 'center',
								hidden : true
							}, {
								field : 'plandocid',
								title : '计划总单ID',
								align : 'center'
							}, {
								field : 'credate',
								title : '计划日期',
								align : 'center',
								width : 120
							}, {
								field : 'entryname',
								title : '客户名称',
								align : 'center',
								width : 120
							},

							] ],
							singleSelect : true,
							title : "计划反馈",
							striped : true,
							//rownumbers:true,
							fit : true,
							pagination : true,
							pageSize : 20,
							pageList : [ 20, 50, 100 ],
							border : false,
							toolbar : "#tb_supply",
							onClickRow : function(index, row) {
								$("#dg_dtl")
										.datagrid(
												"reload",
												WEB_ROOT
														+ '/cms/biz/suplan/SupplyPlanBill/gridDataDtl?plandocid='
														+ row.plandocid);

							},
							onDblClickRow : function(index, row) {

							},
							onLoadSuccess : function(data) {

							}
						});

		$("#dg_dtl").datagrid({
			url : '',
			columns : [ [ {
				field : 'orgid',
				title : 'orgid',
				align : 'center',
				hidden : true
			}, {
				field : 'planid',
				title : '计划细单ID',
				align : 'center'
			}, {
				field : 'goodsname',
				title : '货品名称',
				align : 'center',
				width : 120
			}, {
				field : 'spell',
				title : '拼音',
				align : 'center',
				width : 120
			}, {
				field : 'goodstype',
				title : '规格',
				align : 'center'
			}, {
				field : 'factoryname',
				title : '生产厂家',
				align : 'center'
			}, {
				field : 'unitprice',
				title : '单价',
				align : 'center'
			}, {
				field : 'backprice',
				title : '反馈单价',
				align : 'center',
				editor : 'text'
			}, {
				field : 'goodsunit',
				title : '单位',
				align : 'center'
			}, {
				field : 'goodsqty',
				title : '数量',
				align : 'center'
			}, {
				field : 'backqty',
				title : '反馈数量',
				align : 'center',
				editor : 'text'
			}, ] ],
			singleSelect : true,
			striped : true,
			title : " 医院采购计划明细",
			//rownumbers:true,
			fit : true,
			border : true,
			toolbar : "#tb_org",
			pagination : true,
			pageSize : 20,
			pageList : [ 20, 50, 100 ],
			onClickRow : function(index, row) {
				endEdit($("#dg_dtl"));
			},
			onDblClickRow : function(index, row) {
				edit($("#dg_dtl"));
			},
			onBeforeEdit : function(rowIndex, rowData, changes) {

				if (rowData.backqty = 'undefined') {
					rowData.backqty = rowData.goodsqty;
					rowData.backprice = rowData.unitprice;
				}

			}
		});

		/* 	queryOrg();
			querySupply(); */
	});
	//平台货品查询
	function queryOrg() {

	}

	function backUp() {
		endEdit($("#dg_dtl"));
		var changedRows = new Object();
		changedRows["updated"] = $("#dg_dtl").datagrid('getChanges', "updated");

		$.post(WEB_ROOT + '/cms/biz/suplan/SupplyPlanBill/saveDataOrg', {
			json : JSON.stringify(changedRows)
		}, function(result) {
			if (result.success) {
				$('#dg_dtl').datagrid('reload');
			}
			$.messager.show({
				title : '提示',
				msg : result.msg,
				showType : 'show'
			});
		}, "JSON");
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',border:true" style="width: 28%">
		<table id="dg_doc"></table>
	</div>
	<div data-options="region:'center',border:false">
		<table id="dg_dtl"></table>
	</div>

	<div id="tb_supply" style="padding: 0px; height: auto">
		<form id="fmSupply" method="post"
			style="padding: 5 0 0 0; height: auto">
			开始日期：<input id="sugoodsid" name="sugoodsid" type="text"
				class="easyui-datebox" style="width: 100px" /> 结束日期<input
				id="sugoodsname" name="sugoodsname" type="text"
				class="easyui-datebox" style="width: 100px" /> <a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-search" plain="true" onclick="queryOrg()">查找</a>
		</form>
	</div>

	<div id="tb_org" style="padding: 0px; height: auto">
		<div>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="fa-cloud-upload" plain="true" onclick="backUp()">确定反馈</a></td>
				</tr>
			</table>
		</div>
		<form id="fmOrg" method="post" style="padding: 5 0 0 0; height: auto">
			货品类型<input id="classcode" name="classcode" type="text"
				class="easyui-combobox" style="width: 120px"
				data-options="required:false,valueField:'classcode',textField:'classname',url:'${pageContext.request.contextPath}/cms/biz/supply/orgGoods/getClasslist',panelHeight:'200',editable:false" />
			货品ID<input id="goodsid" name="goodsid" type="text"
				class="easyui-textbox" style="width: 100px" /> 货品名称<input
				id="goodsname" name="goodsname" type="text" class="easyui-textbox"
				style="width: 100px" /> 拼音<input id="spell" name="spell"
				type="text" class="easyui-textbox" style="width: 100px" /><a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-search" plain="true" onclick="queryOrg()">查找</a>
		</form>
	</div>
</body>
</html>