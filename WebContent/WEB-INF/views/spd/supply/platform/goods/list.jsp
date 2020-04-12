<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平台耗材目录</title>

<jsp:include page="../../../../inc/injs.jsp" />
<script type="text/javascript">
	$(function() {

		//检查当前用户参数:1机构 2供应商
		//checkSuUser(1);

		$("#dg_org").datagrid({
			url : "",
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
				width : 400
			}, {
				field : 'supgoodstype',
				title : '货品规格',
				align : 'left',
				width : 200
			}, {
				field : 'supgoodsunit',
				title : '单位',
				align : 'center'
			}, {
				field : 'supfactory',
				title : '厂家/产地',
				align : 'left',
				width : 400
			}, {
				field : 'supapprovedocno',
				title : '批准文号',
				align : 'left',
				width : 200
			}, {
				field : 'supregistdocno',
				title : '注册证号',
				align : 'left',
				width : 200
			}, {
				field : 'gspcategory',
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
			rownumbers : true,
			fit : true,
			border : true,
			pagination : true,
			pageSize : 30,
			pageList : [ 32, 64, 128 ],
			toolbar : "#tb_org",

			onClickRow : function(index, row) {
			},
			onDblClickRow : function(index, row) {
			},
		});

		$("#orgid").combobox({
			url : '${pageContext.request.contextPath}/cms/common/suSelectOrg',
			required : false,
			valueField : 'orgid',
			textField : 'orgname',
			limitToList : true,
			panelHeight : 'auto',
			editable : false,
			onLoadSuccess : function(data) {
				//默认选中第一个
				var array = $(this).combobox("getData");
				for ( var item in array[0]) {
					if (item == "orgid") {
						$(this).combobox('select', array[0][item]);
					}
				}
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
		//queryOrg();
		//设置回车查询快捷键
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				queryOrg();
			}
		}

	});
	//平台货品查询
	function queryOrg() {
		$("#dg_org").datagrid(
				"reload",
				WEB_ROOT + '/cms/biz/supgoods/supgoodslist?'
						+ $("#fmOrg").serialize());
	}

	function importExl() {

		$('#dlg_excel').dialog('open').dialog('setTitle', "导入excel");
		$('#file_upload').val("");
	};
	//下载模板
	function downloadExcel() {
		var url = "/temp/货品导入模板.xls";
		window.location.href = url + fileName;
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
	/************************************************************************************
	上传EXCEL的方法
	 *************************************************************************************/
	function saveExcel() {
		var fileName = $('#file_upload').filebox('getValue');
		//对文件格式进行校验  
		var d1 = /\.[^\.]+$/.exec(fileName);
		if (d1 != ".xls") {
			$.messager.alert('提示', '请选择xls格式文件！', 'info');
			return false;
		}

		load();
		$('#fmDlgExcel').form('submit', {
			url : WEB_ROOT + '/cms/biz/supgoods/importExl',
			onSubmit : function() {
				//表单验证信息
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					disLoad();
					$('#dlg_excel').dialog('close'); // close the dialog
					$("#dg_org").datagrid("reload");
				}
				$.messager.show({
					title : '提示',
					msg : result.msg
				});
			}
		});
	}
</script>
</head>
<body>
	<table id="dg_org"></table>
	<div id="tb_org" style="padding: 0px; height: auto">
		<form id="fmOrg" method="post" style="padding: 5 0 0 0; height: auto">
			货品名称<input id="goodsname" name="goodsname" type="text"
				class="easyui-textbox" style="width: 100px" /> 拼音<input id="spell"
				name="spell" type="text" class="easyui-textbox" style="width: 100px" /><a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-search" plain="true" onclick="queryOrg()">查找</a> <a
				href='javascript:void(0);' class='easyui-linkbutton'
				iconCls='icon-back' plain='true' onclick='importExl()'>导入</a>
		</form>
	</div>
	<!-- 上传excel -->
	<div id="dlg_excel">
		<form:form id="fmDlgExcel" method="post" enctype="multipart/form-data"
			style="padding:20px">
			<!-- 		<input id="file_upload" name="file_upload" type="file"
				accept="application/vnd.ms-excel" /> -->
				选择文件：　<input id="file_upload" name="file_upload"
				class="easyui-filebox" style="width: 270px"
				data-options="prompt:'请选择文件...'" buttonText="点我选文件">
		</form:form>
	</div>
	<div id="dlg-excel-buttons">
		<a target="_blank" data-icon="arrow-down" class="btn btn-green"
			href="/spd/temp/货品导入模板.xls">下载EXCEL模板</a> <a
			href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveExcel()">保存EXCEL</a> <a
			href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dlg_excel').dialog('close')">取消</a>
	</div>
</body>
</html>