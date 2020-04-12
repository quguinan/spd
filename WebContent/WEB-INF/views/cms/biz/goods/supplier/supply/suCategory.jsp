<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管控类型</title>
<jsp:include page="../../../../../inc/injs.jsp" />
<script type="text/javascript">  
		$(function(){
			$("#dg_doc").datagrid({
				url:WEB_ROOT+'/cms/biz/supply/gspCategory/gridDataDoc',   
			    columns:[[
							{field:'docid', title:'docid',align:'center',hidden:true }, 
							{field:'name', title:'管控名称',align:'center' }, 
							{field:'ctrlrange', title:'管控范围',align:'center' }, 
							{field:'ctrltime', title:'管控时机',align:'center' }, 
							{field:'memo', title:'备注',align:'center' }, 
							{field:'userid', title:'userid',width:120,align:'center',hidden:true }, 
							{field:'credate', title:'credate',width:120,align:'center' ,hidden:true}
			              ]],
			    singleSelect:true,
			    striped:true,
			    //rownumbers:true,
			    fit:true,
			    border:false,
			    toolbar: [	'-',{
			    				text:'查询',
								iconCls: 'fa-search',
								handler: function(){
									$('#dg_doc').datagrid('reload',WEB_ROOT+'/cms/biz/supply/gspCategory/gridDataDoc');
									$('#dg_dtl').datagrid('loadData', {total:0,rows:[]});  
								}
							},'-'],
			    onClickRow : function(index, row){
			    	$('#dg_dtl').datagrid('reload',WEB_ROOT+'/cms/biz/supply/gspCategory/gridDataDtl?docid='+row.docid);
			    	$('#dg_license').datagrid('loadData',{rows:0,data:[]});
			    },
			    onDblClickRow : function(index, row){
			    },
			});
			$("#dg_dtl").datagrid({
				url:'',   
			    columns:[[
							{field:'licensetypeid', title:'批次号',align:'center' ,hidden:true}, 
							{field:'licensetypename', title:'证照类型名称',align:'center' }, 
							{field:'customflag', title:'客户标志',align:'center' }, 
							{field:'goodsflag', title:'货品标志',align:'center' }, 
							{field:'supplierflag', title:'供应商',align:'center' }, 
							{field:'memo', title:'备注',align:'center' }, 
							{field:'earlywarndays', title:'预警天数',align:'center' }, 
							{field:'superdays', title:'可超期天数',align:'center' }, 
							{field:'userid', title:'',align:'center' ,hidden:true}, 
							{field:'credate', title:'',align:'center' ,hidden:true}
			              ]],
			    singleSelect:true,
			    striped:true,
			    //rownumbers:true,
			    fit:true,
			    border:false,
			    onClickRow : function(index, row){
			    	$('#dg_license').datagrid('reload',WEB_ROOT+'/cms/biz/supply/supplyLicense/gridData?licensetypeid='+row.licensetypeid);
			    },
			    onDblClickRow : function(index, row){
			    },
			});
			$("#dg_license").datagrid({
				url:'',   
			    columns:[[
							{field:'licenseid', title:'证照ID',align:'center'},  
							{field:'supplyid', title:'供应商ID',align:'center'}, 
							{field:'supplyname', title:'供应商名称',align:'center'},    
							{field:'licensetypeid', title:'管控类型ID',align:'center'},  
							{field:'licensetypename', title:'管控类型名称',align:'center'},   
							{field:'licensename', title:'证照名称',align:'center'},   
							{field:'licensecode', title:'证照编码',align:'center'},   
							{field:'relatecompany', title:'相关企业',align:'center'},   
							{field:'relategoods', title:'相关货品',align:'center'},   
							{field:'signdeptment', title:'签发单位',align:'center'},   
							{field:'signdate', title:'签发日期',align:'center'},   
							{field:'busiscopeids', title:'经营范围',align:'center'},   
							{field:'maincontent', title:'主要内容',align:'center'},   
							{field:'startdate', title:'有效期开始',align:'center'},   
							{field:'enddate', title:'有效期截止',align:'center'},   
							{field:'status', title:'证照状态',align:'center'},   
							{field:'userid', title:'userid',align:'center'},   
							{field:'credate', title:'credate',align:'center',hidden:true},  
			              ]],
			    singleSelect:true,
			    striped:true,
			    //rownumbers:true,
			    pagination:true,
			    fit:true,
			    border:false,
			    onClickRow : function(index, row){
			    },
			    onDblClickRow : function(index, row){
			    },
			});
			
		});
</script>

</head>
<body class="easyui-layout">
	<div data-options="region:'west',border:true" style="width:300px">
		<table id="dg_doc"></table>
	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',collapsed:false,border:false" style="height:300px">
            	<table id="dg_dtl"></table>
            </div>
            <div data-options="region:'center',border:false">
            	<table id="dg_license"></table>
            </div>
        </div>
		
	</div>
</body>
</html>