<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平台耗材目录</title>

<jsp:include page="../../../../../inc/injs.jsp" />
<script type="text/javascript">  
	$(function(){
		//检查当前用户参数:1机构 2供应商
		checkSuUser(1);
	
		$("#dg_org").datagrid({
			url:"",   
		    columns:[[
						{field:'orgid', title:'orgid',align:'center'}, 
						{field:'goodsid', title:'货品ID',align:'center' }, 
						{field:'goodsname', title:'货品名称',align:'center' }, 
						{field:'goodstype', title:'货品分类',align:'center' }, 
						{field:'spell', title:'拼音',align:'center' }, 
						{field:'classcode', title:'类型编码',align:'center' },
						{field:'classname', title:'类型名称',align:'center' },
						{field:'spec', title:'规格',align:'center' },
						{field:'unit', title:'单位',align:'center' },
						{field:'factory', title:'厂家',align:'center' },
						{field:'prodarea', title:'产地',align:'center' },
						{field:'orgname', title:'机构名称',align:'center' ,hidden:true },
		              ]],
		    singleSelect:true,
		    striped:true,
		    title:"平台货品字典",
		    rownumbers:true,
		    fit:true,
		    border:true,
		    toolbar: "#tb_org",
		    pagination:true,
		    pageSize: 20,
	        pageList: [20, 50, 100],
		    onClickRow : function(index, row){
		    },
		    onDblClickRow : function(index, row){
		    },
		});
		
		
		$("#orgid").combobox({
			url:'${pageContext.request.contextPath}/cms/common/suSelectOrg',
			required:false,
            valueField:'orgid',
            textField:'orgname',
            limitToList:true,
			panelHeight:'auto',
			editable:false,
            onLoadSuccess:function(data){
	            //默认选中第一个
	            var array=$(this).combobox("getData");
	            for(var item in array[0]){
	                if(item=="orgid"){
	                   $(this).combobox('select',array[0][item]);
	                 }
	            }
            }
        });
		
		queryOrg();
		
		
	}); 
	//平台货品查询
	function queryOrg(){
		/* $.post(WEB_ROOT+'/cms/common/checkSuUserOrg',{},
				function(result){
					if (result.success){
						$("#dg_org").datagrid("reload",WEB_ROOT+'/cms/biz/supply/orgGoods/gridDataOrgByClassidNameGoodsidSpell?'+$("#fmOrg").serialize());
					}else{
						$.messager.show({title: '提示',msg: result.msg,showType:'show'});
					}
				},'json'); */
		$("#dg_org").datagrid("reload",WEB_ROOT+'/cms/biz/supply/orgGoods/gridDataOrgByClassidNameGoodsidSpell?'+$("#fmOrg").serialize());
	}
	
	function importData(){
		EasyUILoad(); //显示效果
		$.post(WEB_ROOT+'/cms/biz/supply/orgGoods/importData',{},
				function(result){
					dispalyEasyUILoad(); //隐藏效果
					if (result.success){
						queryOrg();
						$.messager.show({title: '提示',msg: result.msg,showType:'show'});
					}
				},'json');
	}
	function EasyUILoad() {
        $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: "auto !important" }).appendTo("body");
        $("<div class=\"datagrid-mask-msg\"></div>").html("<img  class ='img1' /> 正在运行，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
    }
 
    function dispalyEasyUILoad() {
        $(".datagrid-mask").remove();
        $(".datagrid-mask-msg").remove();
    }
</script>
</head>
<body>
	<table id="dg_org"></table>
	<div id="tb_org" style="padding:0px;height:auto">
		<div >
			<table cellpadding="0" cellspacing="0">	
				<tr>	
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-download" plain="true"onclick="importData()" data-options=" disabled:true " >同步</a></td>
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-search" plain="true"onclick="queryOrg()">查找</a></td>
					<td><div class="btn-separator"></div></td>
				</tr> 
			</table>
		</div>
		<form  id="fmOrg" method="post" style="padding:5 0 0 0;height:auto">
			机构<input id="orgid" name="orgid" />
			<%-- 货品类型<input id="classcode" name="classcode" type="text" class="easyui-combobox" style="width:120px"   
					data-options="required:false,valueField:'classcode',textField:'classname',url:'${pageContext.request.contextPath}/cms/biz/supply/orgGoods/getClasslist',panelHeight:'200',editable:false" /> --%>
			货品名称<input id="goodsname" name="goodsname" type="text" class="easyui-textbox" style="width:100px"   />
			拼音<input id="spell" name="spell" type="text" class="easyui-textbox" style="width:100px"   />
		</form>
	</div>
</body>
</html>