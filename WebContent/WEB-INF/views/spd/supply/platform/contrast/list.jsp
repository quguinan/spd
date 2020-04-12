<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典对照管理</title>

<jsp:include page="../../../../inc/injs.jsp" />
<script type="text/javascript">
	/* 页面全局变量 */
	var v_orgid = '';
	var v_orgname = '';
	var v_supplyid = '';

	$(function() {
		$("#dg_supply")
				.datagrid(
						{
							url : '',
							columns : [ [ {
								field : 'orgid',
								title : '平台ID',
								align : 'center'
							}, {
								field : 'supgoodsid',
								title : '货品ID',
								align : 'left',
								width : 80
							}, {
								field : 'supgoodsname',
								title : '货品名称',
								align : 'left',
								width : 300
							}, {
								field : 'supgoodstype',
								title : '货品规格',
								align : 'left',
								width : 150
							}, {
								field : 'orggoodsid',
								title : '平台字典ID',
								align : 'center',
								editor : {
									type : "textbox"
								}
							}, {
								field : 'supgoodsunit',
								title : '单位',
								align : 'center'
							}, {
								field : 'supfactory',
								title : '厂家/产地',
								align : 'left'
							}, {
								field : 'supapprovedocno',
								title : '批准文号',
								align : 'left'
							}, {
								field : 'supregistdocno',
								title : '注册证号',
								align : 'left'
							}, {
								field : 'gspcategory',
								title : '类型',
								align : 'center'
							} ] ],
							singleSelect : true,
							title : "我的货品字典",
							striped : true,
							//rownumbers:true,
							fit : true,
							pagination : true,
							pageSize : 33,
							pageList : [ 33, 66, 93 ],
							border : false,
							toolbar : "#tb_supply",
							onClickRow : function(index, row) {

								$("#dg_org")
										.datagrid(
												"reload",
												WEB_ROOT
														+ '/cms/biz/supply/goods/list?goodsname='
														+ row.supgoodsname);
							},
							onDblClickRow : function(index, row) {
								undoMap(index, row);
							},
							onLoadSuccess : function(data) {

							}
						});

		$("#dg_org").datagrid({
			url : "",
			columns : [ [ {
				field : 'goodsid',
				title : '货品ID',
				align : 'center'
			}, {
				field : 'goodsname',
				title : '货品名称',
				align : 'left',
				width : 300
			}, {
				field : 'goodstype',
				title : '货品分类',
				align : 'left',
				width : 200
			}, {
				field : 'goodsunit',
				title : '单位',
				align : 'center'
			}, {
				field : 'factoryname',
				title : '厂家/产地',
				align : 'left',
				width : 400
			}, {
				field : 'approvedocno',
				title : '批准文号',
				align : 'left',
				width : 200
			}, {
				field : 'registdocno',
				title : '注册证号',
				align : 'left',
				width : 200
			}, {
				field : 'category',
				title : '类型',
				align : 'center'
			}, {
				field : 'goodspinyin',
				title : '拼音',
				align : 'left',
				width : 200
			} ] ],
			singleSelect : true,
			striped : true,
			title : "平台货品字典",

			fit : true,
			border : true,
			pagination : true,
			pageSize : 33,
			pageList : [ 33, 66, 128 ],
			toolbar : "#tb_org",
			onClickRow : function(index, row) {
			},
			onDblClickRow : function(index, row) {
				//对照
				doMap(index, row);
				//对照后自动保存
				saveContrast();
			},
		});

		$('#cb').combobox(
				{
					onChange : function(newValue, oldValue) {
						$("#dg_org").datagrid(
								"reload",
								WEB_ROOT + '/cms/biz/supply/goods/list?fenlei='
										+ newValue);
					}
				});

	});

	//设置回车查询快捷键
	document.onkeydown = function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 13) {
			querySupply(1);
			//queryOrg();
		}
	}
	//平台货品查询
	function queryOrg() {
		$("#dg_org").datagrid(
				"reload",
				WEB_ROOT + '/cms/biz/supply/goods/list?'
						+ $("#fmOrg").serialize());
	}
	//供应商货品查询
	function querySupply(flag) {
		if (flag == 1) {
			$("#dg_supply").datagrid(
					"reload",
					WEB_ROOT + '/cms/biz/supgoods/supgoodslist?'
							+ $("#fmSupply").serialize());
		} else {

		}

	}
	//供应商货品对照
	function doMap(index, row) {
		var rowSupply = $("#dg_supply").datagrid("getSelected");
		var indexSupply = $("#dg_supply").datagrid("getRowIndex", rowSupply);
		var rowOrg = $("#dg_org").datagrid("getSelected");
		if (rowSupply == null) {
			alert("请选择(供应商货品)");
		} else if (rowOrg == null) {
			alert("请选择(平台货品)");
		} else {
			var goodsid = row.goodsid;
			//得到供应商货品字典索引
			var rowIndex = $('#dg_supply').datagrid('getRowIndex',
					$('#dg_supply').datagrid('getSelected'));
			//将总单设置为编辑状态
			$('#dg_supply').datagrid('beginEdit', rowIndex);
			//获得到需要编辑行的值
			var editors = $('#dg_supply').datagrid('getEditors', rowIndex);
			var orggoodsid = editors[0];
			//赋值
			orggoodsid.target.textbox('setValue', goodsid);
			$("#dg_supply").datagrid('endEdit', rowIndex);

		}
	}

	//结束编辑
	function endEdit() {
		var rows = $("#dg_supply").datagrid('getRows');
		for (var i = 0; i < rows.length; i++) {
			//表单验证
			if (!$('#dg_supply').datagrid('validateRow', i)) {
				$.messager.show({
					title : '提示',
					msg : "必填项不能空!",
					showType : 'show'
				});
				$('#dg_supply').datagrid('selectRow', i);// 关键在这里
				return false;
			}
			//结束编辑
			$("#dg").datagrid('endEdit', i);
		}
		return true;
	}

	//供应商货品取消对照
	function undoMap(index, row) {
		var rowSupply = $("#dg_supply").datagrid("getSelected");
		var indexSupply = $("#dg_supply").datagrid("getRowIndex", rowSupply);

		if (row.orggoodsid == undefined) {
			return;
		}
		if (rowSupply == null) {
			alert("请选择(供应商货品)");
		} else {
			$.messager.confirm('确认对话框', '确定取消对照信息？',
					function(r) {
						if (r) {
							//alert("确定之后")
							//将总单设置为编辑状态
							$('#dg_supply').datagrid('beginEdit', index);
							//获得到需要编辑行的值
							var editors = $('#dg_supply').datagrid(
									'getEditors', index);
							var oldgoodsid = editors[0];
							//赋值
							oldgoodsid.target.textbox('setValue', '');
							$("#dg_supply").datagrid('endEdit', index);

							saveContrast();
						}
					});

		}

	}
	//调用接口,导入供应商信息
	function importData() {

	}

	//保存
	function saveContrast() {
		//alert("调用保存")
		if ($("#dg_supply").datagrid('getChanges').length) {
			var changedRows = new Object();
			changedRows["inserted"] = $("#dg_supply").datagrid('getChanges',
					"inserted");
			changedRows["deleted"] = $("#dg_supply").datagrid('getChanges',
					"deleted");
			changedRows["updated"] = $("#dg_supply").datagrid('getChanges',
					"updated");

			$.post(WEB_ROOT + '/cms/biz/supgoods/supgoodssave', {
				json : JSON.stringify(changedRows)
			}, function(result) {
				if (result.success) {
					$('#dg_supply').datagrid('reload');
				}
				$.messager.show({
					title : '提示',
					msg : result.msg,
					timeout : 1000,
					showType : 'show'
				/* ,
													style : {
														right : '',
														bottom : ''
													} */
				});
				$('#dg_supply').datagrid('acceptChanges');
			}, "JSON");
		}
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',border:true" style="width: 50%">
		<table id="dg_supply"></table>
	</div>
	<div data-options="region:'center',border:false">
		<table id="dg_org"></table>
	</div>

	<div id="tb_supply" style="padding: 0px; height: auto">
		<form id="fmSupply" method="post"
			style="padding: 5 0 0 0; height: auto">
			货品ID<input id="goodsid" name="goodsid" type="text"
				class="easyui-textbox" style="width: 100px" /> 货品名称<input
				id="goodsname" name="goodsname" type="text" class="easyui-textbox"
				style="width: 100px" /> 拼音<input id="spell" name="spell"
				type="text" class="easyui-textbox" style="width: 100px" /> <a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-search" plain="true" onclick="querySupply(1)">查找</a>
		</form>
	</div>

	<div id="tb_org" style="padding: 0px; height: auto">
		<form id="fmOrg" method="post" style="padding: 5 0 0 0; height: auto">
			<%-- 货品类型<input id="classcode" name="classcode" type="text" class="easyui-combobox" style="width:120px"   
					data-options="required:false,valueField:'classcode',textField:'classname',
					url:'${pageContext.request.contextPath}/cms/biz/supply/orgGoods/getClasslist',panelHeight:'200',editable:false" /> --%>
			货品ID<input id="goodsid" name="goodsid" type="text"
				class="easyui-textbox" style="width: 100px" /> 货品名称<input
				id="goodsname" name="goodsname" type="text" class="easyui-textbox"
				style="width: 100px" /> 拼音<input id="spell" name="spell"
				type="text" class="easyui-textbox" style="width: 100px" /> 分类 <select
				id="cb" class="easyui-combobox" name="dept" style="width: 100px;">
				<option>全部</option>
				<option>药品</option>
				<option>耗材</option>
			</select> <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-search" plain="true" onclick="queryOrg()">查找</a>
		</form>
	</div>

	<div id="dlg_selectOrgid" class="easyui-dialog" title="选择一个机构"
		style="width: 200px; height: 200px;"
		data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,closable:false">
		<table id="dg_org_select"></table>
	</div>
</body>
</html>