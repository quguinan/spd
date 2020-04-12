<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商-计划反馈</title>
<jsp:include page="../../../../inc/injs.jsp" />
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
							}, ] ],
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

								$('#dg_doc').datagrid("selectRow", 0);

								$("#dg_dtl")
										.datagrid(
												"reload",
												WEB_ROOT
														+ '/cms/biz/suplan/SupplyPlanBill/gridDataDtl?plandocid='
														+ $('#dg_doc')
																.datagrid(
																		"getSelected").plandocid);
							},
						});
		$("#dg_dtl").datagrid(
				{
					url : '',
					columns : [ [ {
						title : '平台货品信息',
						colspan : 7,
						rowspan : 1
					}, {
						title : '我的货品信息',
						colspan : 5,
						rowspan : 1
					}, {
						title : '计划反馈信息',
						colspan : 5,
						rowspan : 1
					} ], [ {
						field : 'planid',
						title : '计划细单ID',
						align : 'center',
						rowspan : 1
					}, {
						field : 'plandocid',
						title : '总单ID',
						align : 'center',
						rowspan : 1
					}, {
						field : 'goodsid',
						title : '平台货品ID',
						align : 'center',
						rowspan : 1
					}, {
						field : 'goodsname',
						title : '货品名称',
						align : 'center',
						width : 120,
						rowspan : 1
					}, {
						field : 'opcode',
						title : '拼音',
						align : 'center',
						width : 120,
						rowspan : 1
					}, {
						field : 'goodstype',
						title : '规格',
						align : 'center',
						rowspan : 1
					}, {
						field : 'factoryname',
						title : '生产厂家',
						align : 'center',
						rowspan : 1
					}, {
						field : 'supgoodsid',
						title : '我的货品ID',
						align : 'center'
					}, {
						field : 'supgoodsname',
						title : '我的货品',
						align : 'center'
					}, {
						field : 'supgoodstype',
						title : '我的货品规格',
						align : 'center'
					}, {
						field : 'supgoodsunit',
						title : '货品单位',
						align : 'center'
					}, {
						field : 'supfactory',
						title : '我的生产厂家',
						align : 'center'
					}, {
						field : 'unitprice',
						title : '采购单价',
						align : 'center'
					}, {
						field : 'backprice',
						title : '反馈单价',
						align : 'center',
						editor : 'text'
					}, {
						field : 'goodsunit',
						title : '采购单位',
						align : 'center'
					}, {
						field : 'goodsqty',
						title : '采购数量',
						align : 'center'
					}, {
						field : 'backqty',
						title : '反馈数量',
						align : 'center',
						editor : 'text'
					} ] ],
					singleSelect : true,
					striped : true,
					title : " 医院采购计划明细",
					//rownumbers:true,
					fit : true,
					border : true,
					toolbar : "#tb_org",
					/* pagination : true,
					pageSize : 20,
					pageList : [ 20, 50, 100 ], */
					onClickRow : function(index, row) {
						endEdit($("#dg_dtl"));
					},
					onDblClickRow : function(index, row) {
						if (row.supgoodsid == undefined) {
							$.messager.alert('提示', '平台货品ID：' + row.goodsid
									+ ',名称：' + row.goodsname + ',规格：'
									+ row.goodstype + ',厂家：' + row.factoryname
									+ '， 没有对照,请完成对照后刷请页面再进行反馈！！！', 'warning');
							return;
						}
						edit($("#dg_dtl"));
					},
					onBeforeEdit : function(rowIndex, rowData, changes) {
					}
				});
		/* 初始化dlg_pic_edit */
		$('#dlg_excel').dialog({
			height : 150,
			width : 420,
			closed : true,
			cache : false,
			buttons : '#dlg-excel-buttons',
			modal : true,
			inline : true,
			border : 'thin'
		/* cls:'c5' */
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
		if ($("#dg_dtl").datagrid('getChanges').length) {
			$.post(WEB_ROOT + '/cms/biz/suplan/SupplyPlanBill/saveDataOrg', {
				json : JSON.stringify(changedRows)
			}, function(result) {
				if (result.success) {
					$("#dg_doc	").datagrid("reload");
					$('#dg_dtl').datagrid('reload');
				}
				$.messager.show({
					title : '提示',
					msg : result.msg,
					showType : 'show'
				});
			}, "JSON");
		}
	}
	function exportExl() {
		var rows = $('#dg_dtl').datagrid('getRows');
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row.supgoodsid == undefined) {
				alert('第' + (parseInt(i) + parseInt(1)) + '几行没有对照，请对照后再执行导出');
				return;
			}
		}
		//export with customized rows
		$('#dg_dtl').datagrid('toExcel', {
			filename : '医院计划采购明细.xls',
			rows : rows,
			worksheet : 'Worksheet'
		});
	}
	function importExl() {

		if ($("#dg_doc").datagrid("getRows").length <= 0) {
			alert("请选择一条总单后进行导入！！！");
			return;
		}
		$('#dlg_excel').dialog('open').dialog('setTitle', "导入excel");
		$('#file_upload').val("");
	}
	//弹出加载层
	function load() {
		$("<div class=\"datagrid-mask\"></div>").css({
			display : "block",
			width : "100%",
			height : $(window).height()
		}).appendTo("body");
		$("<div class=\"datagrid-mask-msg\"></div>")
				.html(
						"<span style='display:block;margin-left:-10px;margin-top:-10px;font-size: 9px;color: gray'>正在导入，请稍候。。。</span>")
				.appendTo("body").css({
					borderRadius : "23px ",
					display : "block",
					left : ($(document.body).outerWidth(true) - 190) / 2,
					top : ($(window).height() - 45) / 2
				});
		$('#dlg_excel').dialog('close');
	}
	//取消加载层
	function disLoad() {
		$(".datagrid-mask").remove();
		$(".datagrid-mask-msg").remove();
	}
	function saveExcel() {
		var fileName = $('#file_upload').filebox('getValue');
		//对文件格式进行校验  
		var d1 = /\.[^\.]+$/.exec(fileName);
		if (d1 != ".xls") {
			$.messager.alert('提示', '请选择xls格式文件！', 'info');
			return false;
		}
		load();
		var rows = $("#dg_dtl").datagrid("getRows");
		var ids = '';
		for (var i = 0; i < rows.length; i++) {
			ids += rows[i]['planid'] + ',';
			if (rows[i]['supgoodsid'] == undefined) {
				alert("细单中存在未对照的数据，请检查！！！");
				disLoad();
				return;
			}
		}
		var rows = $("#dg_dtl").datagrid("getRows");
		$('#fmDlgExcel')
				.form(
						'submit',
						{
							url : WEB_ROOT
									+ "/cms/biz/suplan/SupplyPlanBill/importExcel?dtlrows="
									+ ids + "&sheetrows=" + rows.length,
							onSubmit : function() {
								//表单验证信息
								return $(this).form('validate');
							},
							success : function(result) {
								disLoad();
								var result = eval('(' + result + ')');
								if (result.success) {
									$('#dlg_excel').dialog('close'); // close the dialog
									$("#dg_doc	").datagrid("reload");
									$("#dg_dtl").datagrid("reload");
								}
								$.messager.show({
									title : '提示',
									msg : result.msg
								});
							}
						});
	}
	function reload() {
		$("#dg_doc	").datagrid("reload");
		$("#dg_dtl").datagrid("reload");
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',border:true" style="width: 20%">
		<table id="dg_doc"></table>
	</div>
	<div data-options="region:'center',border:false">
		<table id="dg_dtl"></table>
	</div>

	<%-- <div id="tb_supply" style="padding: 0px; height: auto">
		<form id="fmSupply" method="post"
			style="padding: 5 0 0 0; height: auto">
			开始日期：<input id="sugoodsid" name="sugoodsid" type="text"
				class="easyui-datebox" style="width: 100px" /> 结束日期<input
				id="sugoodsname" name="sugoodsname" type="text"
				class="easyui-datebox" style="width: 100px" /> <a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-search" plain="true" onclick="queryOrg()">查找</a>
		</form>
	</div> --%>

	<div id="tb_org" style="padding: 0px; height: auto">
		<form id="fmOrg" method="post" style="padding: 5 0 0 0; height: auto">
			<%-- 	货品类型<input id="classcode" name="classcode" type="text"
				class="easyui-combobox" style="width: 120px"
				data-options="required:false,valueField:'classcode',textField:'classname',url:'${pageContext.request.contextPath}/cms/biz/supply/orgGoods/getClasslist',panelHeight:'200',editable:false" />
			货品ID<input id="goodsid" name="goodsid" type="text"
				class="easyui-textbox" style="width: 100px" /> 货品名称<input
				id="goodsname" name="goodsname" type="text" class="easyui-textbox"
				style="width: 100px" /> 拼音<input id="spell" name="spell"
				type="text" class="easyui-textbox" style="width: 100px" /><a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-search" plain="true" onclick="queryOrg()">查找</a> --%>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-repeat" plain="true" onclick="reload()">刷新</a> <a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-cloud-upload" plain="true" onclick="backUp()">确定反馈</a> <a
				href='javascript:void(0);' class='easyui-linkbutton'
				iconCls='icon-redo' plain='true' onclick='exportExl()'>导出</a> <a
				href='javascript:void(0);' class='easyui-linkbutton'
				iconCls='icon-back' plain='true' onclick='importExl()'>导入</a>
		</form>
	</div>
	<!-- 上传excel -->
	<div id="dlg_excel">
		<form:form id="fmDlgExcel" method="post" enctype="multipart/form-data"
			style="padding:20px">
				选择文件：　<input id="file_upload" name="file_upload"
				class="easyui-filebox" style="width: 270px"
				data-options="prompt:'请选择文件...'" buttonText="点我选文件">
		</form:form>
	</div>
	<div id="dlg-excel-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveExcel()">保存EXCEL</a> <a
			href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dlg_excel').dialog('close')">取消</a>
	</div>
</body>
</html>