<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机构-订单(查看,审核,作废)</title>
<jsp:include page="../../../../inc/injs.jsp" />
<script type="text/javascript">
	var oldorderqty;
	$(function() {
		$("#dg_doc")
				.datagrid(
						{
							url : '',
							columns : [ [
									//单据状态: -1作废,0新订单,1已审核,2已接收
									{
										field : '_icon',
										title : '',
										align : 'center',
										width : 40,
										formatter : function(value, row, index) {
											if (row.status == 0) {
												return '<a href="javascript:void(0);" class="new" onclick="" />';
											} else if (row.status == 1) {
												return '<a href="javascript:void(0);" class="checked" onclick="" />';
											} else if (row.status == 2) {
												return '<a href="javascript:void(0);" class="recieved" onclick="" />';
											} else if (row.status == -1) {
												return '<a href="javascript:void(0);" class="canceled" onclick="" />';
											}
										}
									},
									{
										title : 'docid',
										field : 'docid',
										align : "center",
										hidden : true
									},
									{
										title : '订单编号',
										field : 'docno',
										align : "center",
										formatter : function(value, row, index) {
											return '<span title="'+value+'"  class="easyui-tooltip">'
													+ value + '</span>';
										}
									}, {
										title : '客户id',
										field : 'entryid',
										align : "center",
										hidden : true
									}, {
										title : '客户名称',
										field : 'entryname',
										align : "center"
									}, {
										title : '供应商id',
										field : 'supplyid',
										align : "center",
										hidden : true
									}, {
										title : '签订日期',
										field : 'signdate',
										align : "center"
									}, {
										title : '签订人',
										field : 'signman',
										align : "center"
									}, {
										title : '有效期开始',
										field : 'validbegdate',
										align : "center"
									}, {
										title : '有效期结束',
										field : 'validenddate',
										align : "center"
									}, {
										title : '备注',
										field : 'memo',
										align : "center"
									}, {
										title : '创建日期',
										field : 'credate',
										align : "center"
									}, {
										title : '平台订单ID',
										field : 'sourceid',
										align : "center"
									}, ] ],
							border : 0,
							fit : true,
							/* rownumbers:true, */
							singleSelect : true,
							toolbar : "#toolbar",
							pagination : true,
							pageSize : 20,
							pageList : [ 20, 50, 100 ],
							onClickRow : function(index, row) {
								$("#dg_dtl")
										.datagrid(
												"reload",
												WEB_ROOT
														+ '/cms/biz/supply/orgOrderBill/gridDataDtl?docid='
														+ row.docid);
							},
							onDblClickRow : function(index, row) {
							},
							onLoadSuccess : function(data) {
								//默认选中第一行
								$('#dg_doc').datagrid("selectRow", 0);
								//刷新细单
								$("#dg_dtl")
										.datagrid(
												"reload",
												WEB_ROOT
														+ '/cms/biz/supply/orgOrderBill/gridDataDtl?docid='
														+ +$('#dg_doc')
																.datagrid(
																		"getSelected").docid);

								$('.new').linkbutton({
									text : '',
									plain : true,
									iconCls : 'fa-file-text-o'
								});
								$('.checked').linkbutton({
									text : '',
									plain : true,
									iconCls : 'fa-check'
								});
								$('.recieved').linkbutton({
									text : '',
									plain : true,
									iconCls : 'fa-exchange'
								});
								$('.canceled').linkbutton({
									text : '',
									plain : true,
									iconCls : 'fa-remove'
								});
								/* icon_tick   icon_delete  icon_error icon_stop*/
							},
						})
		$("#dg_dtl").datagrid({
			url : '',
			toolbar : '#tb_org',
			columns : [ [ {
				title : 'dtlid',
				field : 'dtlid',
				align : "center"
			}, {
				title : 'docid',
				field : 'docid',
				align : "center",
				hidden : true
			}, {
				title : '平台细单ID',
				field : 'sortno',
				align : "center"
			}, {
				title : '平台字典ID',
				field : 'goodsid',
				align : "center"
			}, {
				title : '平台货品名称',
				field : 'goodsname',
				align : "center"
			}, {
				title : '平台货品厂家',
				field : 'factoryname',
				align : "center"
			}, {
				title : '我的货品名称',
				field : 'supgoodsname',
				align : "center"
			}, {
				title : '我的货品规格',
				field : 'supgoodstype',
				align : "center"
			}, {
				title : '我的货品单位',
				field : 'supgoodsunit',
				align : "center"
			}, {
				title : '我的货品厂家',
				field : 'supfactory',
				align : "center"
			}, {
				title : '订单数量',
				field : 'orderqty',
				align : "center",
				editor : {
					type : "textbox",
					onChange : function(newValue, oldValue) {
						alert(1)
					}
				}
			}, {
				title : '采购单位',
				field : 'goodsunit',
				align : "center"
			}, {
				title : '采购单价',
				field : 'unitprice',
				align : "center"
			}, {
				title : '采购金额',
				field : 'total',
				align : "center"
			}, {
				title : '批号',
				field : 'lotno',
				align : "center",
				width : '100',
				editor : {
					type : "textbox"
				}
			}, {
				title : '生产日期',
				field : 'prodate',
				align : "center",
				width : '100',
				editor : {
					type : "datebox"
				}
			}, {
				title : '有效期至',
				field : 'invaliddate',
				align : "center",
				width : '100',
				editor : {
					type : "datebox"
				}
			}, {
				title : '备注',
				field : 'memo',
				align : "center"
			}, {
				title : '平台订单ID',
				field : 'orgid',
				align : "center"
			}, {
				title : 'supplyid',
				field : 'supplyid',
				align : "center",
				hidden : true
			} ] ],
			border : 0,
			singleSelect : false,
			striped : true,
			fit : true,
			border : false,
			pagination : false,
			pageSize : 20,
			pageList : [ 20, 50, 100 ],
			onClickRow : function(index, row) {
				endEdit("#dg_dtl");
			},
			onDblClickRow : function(index, row) {
				beginedit("#dg_dtl", index);
			},
			onAfterEdit : function(index, row, changes) {
				setTotal(index, row)
			},
			onBeginEdit : function(index, row) {
			}
		});
		$("#orgid").combobox({
			url : WEB_ROOT + '/cms/common/sutype',
			required : false,
			valueField : 'supplyid',
			textField : 'supplyname',
			limitToList : true,
			panelHeight : 'auto',
			editable : true,
			width : 200,
			onLoadSuccess : function(data) {
				/* //默认选中第一个
					            var array=$(this).combobox("getData");
					            for(var item in array[0]){
					                if(item=="supplyid"){
					                   $(this).combobox('select',array[0][item]);
					                 }
					            } */
			},
			onSelect : function(data) {

			}
		});

		$('#credate1').datebox('setValue', getNowFormatDate());
		$('#credate2').datebox('setValue', getNowFormatDate());
		//query();
	});

	function setTotal(index, row) {
		//alert(JSON.stringify(row))
		$('#dg_dtl').datagrid('updateRow', {
			index : index,
			row : {
				'total' : row.orderqty * row.unitprice
			}
		});
	}
	function query() {
		$("#dg_doc").datagrid(
				"reload",
				WEB_ROOT + '/cms/biz/supply/orgOrderBill/getOrderDoc?'
						+ $("#fm").serialize());
	};
	function setStatus(status) {
		var row = $('#dg_doc').datagrid('getSelected');
		var index = $('#dg_doc').datagrid('getRowIndex', row);
		//alert(JSON.stringify(row));
		if (row) {
			$.post(WEB_ROOT + ' ', {
				status : status,
				docid : row.docid
			}, function(result) {
				if (result.success) {
					query();
					$('#dg_doc').datagrid('selectRow', index);
					$.messager.show({
						title : '提示',
						msg : result.msg,
						showType : 'show'
					});
				} else {
					$.messager.show({
						title : '提示',
						msg : result.msg,
						showType : 'show'
					});
				}
			}, 'json');

		}
	};
	function reload() {
		$("#dg_dtl").datagrid(
				"reload",
				WEB_ROOT + '/cms/biz/supply/orgOrderBill/gridDataDtl?docid='
						+ +$('#dg_doc').datagrid("getSelected").docid);

	}

	function confirmorder() {
		var rows = $("#dg_dtl").datagrid("getSelected");
		var index = $("#dg_dtl").datagrid("getRowIndex");

		rows.orderqty = r;
		rows.dtlid = '';

		$('#dg_dtl').datagrid('appendRow', rows);
	}
	//拆单

	function deorder() {

		var row = $("#dg_dtl").datagrid("getSelected");
		var rows = $('#dg_dtl').datagrid('getSelections');//多行

		var rowindex = $("#dg_dtl").datagrid('getRowIndex', row);
		var dtlrows = $("#dg_dtl").datagrid("getRows");
		alert(JSON.stringify(row))
		if (row == null) {
			mmessager("center", "请选中需要拆单的单据行，在进行拆单操作！");
			return;
		}
		if (rows.length > 1) {
			mmessager("center", "此功能不允许多选操作！");
			return;
		}
		$.messager
				.prompt(
						'拆单',
						'请输入需要拆分的数量',
						function(r) {
							if (r) {
								if (row) {
									$('#dg_dtl')
											.datagrid('beginEdit', rowindex);

									var oldqty = row.orderqty;
									var newqty = oldqty - r
									if (eval(oldqty) == eval(r)
											|| eval(oldqty) < eval(r)
											|| eval(r) <= 0) {
										mmessager("center",
												"拆单数量不能等于,大于原数量，并且不能小于等于0！");
										endEdit("#dg_dtl");
										return;
									}
									rows.orderqty = r;
									//console.log(JSON.stringify(row))
									//rows.dtlid = '';
									$('#dg_dtl').datagrid('insertRow', {
										index : rowindex + 1,
										row : {
											"docid" : rows.docid,
											"sortno" : rows.sortno,
											"goodsid" : rows.goodsid,
											"orderqty" : r,
											"unitprice" : rows.unitprice,
											"total" : rows.unitprice * r,
											"orderdtlid" : rows.orderdtlid,
											"supgoodsid" : rows.supgoodsid,
											"supgoodsname" : rows.supgoodsname,
											"supgoodstype" : rows.supgoodstype,
											"supfactory" : rows.supfactory,
											"supgoodsunit" : rows.supgoodsunit,
											"goodsname" : rows.goodsname,
											"goodstype" : rows.goodstype,
											"factoryname" : rows.factoryname,
											"goodsunit" : rows.goodsunit,
											"orgid" : rows.orgid,
											"supplyid" : rows.supplyid,
											"sendqty" : "",
											"lotno" : "",
											"prodate" : "",
											"invaliddate" : ""
										}
									});

									var editors = $('#dg_dtl').datagrid(
											'getEditors', rowindex);
									var orderqty = editors[0];
									//赋值
									orderqty.target.textbox('setValue', newqty);
									$('#dg_dtl').datagrid("endEdit", rowindex);
								}
							}
						});

	}

	function center(message) {
		$.messager.show({
			title : '提示',
			msg : message,
			showType : 'fade',
			style : {
				right : '',
				bottom : ''
			}
		});
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:true,split:true"
		style="height: 40%">
		<table id="dg_doc"></table>
	</div>
	<div data-options="region:'center',border:true">
		<table id="dg_dtl"></table>
	</div>
	<div id="toolbar">
		<div>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="fa-search" plain="true" onclick="query()">订单查询</a></td>
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="fa-check" plain="true" onclick="setStatus('1')">审核</a></td>
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="fa-undo" plain="true" onclick="setStatus('0')">取消审核</a></td>
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="fa-remove" plain="true" onclick="setStatus('-1')">作废</a></td>
					<td><div class="btn-separator"></div></td>
				</tr>
			</table>
		</div>
		<form id="fm" method="post" style="padding: 5 0 0 0; height: auto">
			开始日期<input id="credate1" name="credate1" type="text"
				class="easyui-datebox" style="width: 130px" editable="false" />
			结束日期<input id="credate2" name="credate2" type="text"
				class="easyui-datebox" style="width: 130px" editable="false" />
			客户名称<input id="orgid" name="orgid" />
		</form>
	</div>
	<div id="tb_org" style="padding: 0px; height: auto">
		<form id="fmOrg" method="post" style="padding: 5 0 0 0; height: auto">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-repeat" plain="true" onclick="reload()">刷新</a> <a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-plus-circle" plain="true" onclick="deorder()">拆单</a><a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-cloud-upload" plain="true" onclick="backUp()">确定</a> <a
				href='javascript:void(0);' class='easyui-linkbutton'
				iconCls='icon-redo' plain='true' onclick='exportExl()'>导出</a> <a
				href='javascript:void(0);' class='easyui-linkbutton'
				iconCls='icon-back' plain='true' onclick='importExl()'>导入</a>
		</form>
	</div>

	<div id="w2" class="easyui-window" title="请输入拆单信息"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width: 700px; height: 200px; padding: 10px;">
		<div>
			数量1： <input class="easyui-numberbox" style="width: 50px" id="qty1"
				name="qty1"> 批号1： <input class="easyui-textbox"
				style="width: 100px" id="lot1" name="lot1"> 生产日期1： <input
				class="easyui-datebox" style="width: 100px" id="prodate1"
				name="prodate1"> 有效期至1： <input class="easyui-datebox"
				style="width: 100px" id="validate1" name="validate1">
		</div>
		<div style="padding-top: 10px">
			数量2： <input class="easyui-numberbox" style="width: 50px" id="qty2"
				name="qty2"> 批号2： <input class="easyui-textbox"
				style="width: 100px" id="lot2" name="lot2"> 生产日期2： <input
				class="easyui-datebox" style="width: 100px" id="prodate2"
				name="prodate2"> 有效期至2： <input class="easyui-datebox"
				style="width: 100px" id="validate2" name="validate2">
		</div>
		<div style="padding-top: 20px; padding-right: 50px;" align="right">
			<a href="#" class="easyui-linkbutton" style="width: 100px;"
				onclick="confirmorder()">确定</a>
		</div>
	</div>
</body>
</html>