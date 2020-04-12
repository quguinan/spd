<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				field : 'goodsid',
				title : '货品ID',
				align : 'center'
			}, {
				field : 'goodsname',
				title : '货品名称',
				align : 'left',
				width : 400
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
				WEB_ROOT + '/cms/biz/supply/goods/list?'
						+ $("#fmOrg").serialize());
	}
</script>
</head>
<body>
	<table id="dg_org"></table>
	<div id="tb_org" style="padding: 0px; height: auto">
		<form id="fmOrg" method="post" style="padding: 5 0 0 0; height: auto">
			<!-- 	机构<input id="orgid" name="orgid" /> -->
			<%-- 货品类型<input id="classcode" name="classcode" type="text" class="easyui-combobox" style="width:120px"   
					data-options="required:false,valueField:'classcode',textField:'classname',url:'${pageContext.request.contextPath}/cms/biz/supply/orgGoods/getClasslist',panelHeight:'200',editable:false" /> --%>
			货品名称<input id="goodsname" name="goodsname" type="text"
				class="easyui-textbox" style="width: 100px" /> 拼音<input id="spell"
				name="spell" type="text" class="easyui-textbox" style="width: 100px" /><a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="fa-search" plain="true" onclick="queryOrg()">查找</a>
		</form>
	</div>
</body>
</html>