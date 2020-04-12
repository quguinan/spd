<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
   <base href="<%=basePath%>">
    <title>库存查询</title>
	<jsp:include page="../../../../inc/injs.jsp"/> 
	<script type="text/javascript"> 
		$(function(){ 
			$('#tt').tabs({    
			    border:false, 
			    fit:true,
			    border:true,
			    onSelect:function(title,index){    
			        if(index==0){
			        	
			        } else if(index==1){
			        	
			        } 
			    }    
			});
			
			
			$('#dg1').datagrid({
	       		url:WEB_ROOT+'/cms/goods/stock/stock_query/gridData1?'+$('#fm1').serialize(),
			    columns:[[
					//{field:'stockid',title:'',align:'center' ,hidden:true},    
					{field:'storeid',title:'',align:'center'  ,hidden:true},
					{field:'storename',title:'库房',align:'center' },
					{field:'goodsid',title:'',align:'center'  ,hidden:true},
					{field:'goodsdtlid',title:'',align:'center' ,hidden:true },
					{field:'goodsname',title:'名称',align:'center' },
					{field:'customname',title:'通用名',align:'center' },
					{field:'classid',title:'',align:'center'  ,hidden:true},
					{field:'classname',title:'类别',align:'center' },
					{field:'spell',title:'拼音',align:'center' },
					{field:'goodsstatus',title:'状态',align:'center' },
					{field:'factoryid',title:'',align:'center' ,hidden:true },
					{field:'factoryname',title:'厂家',align:'center' },
					{field:'prodareaid',title:'',align:'center'  ,hidden:true },
					{field:'prodareaname',title:'产地',align:'center' },
					{field:'unitid',title:'',align:'center'  ,hidden:true},
					{field:'unitname',title:'单位',align:'center'  },
					{field:'spec',title:'规格',align:'center' },
					{field:'ratio',title:'包装量',align:'center' },
			        //{field:'lotid',title:'批次号',align:'center' },
					{field:'postid',title:'货位',align:'center' },
					{field:'qty',title:'数量',align:'center' },
					{field:'status',title:'状态',align:'center' }
			    ]],
			    singleSelect:true,
			    striped:true,
			    fit:true,
			    border:false,
			    rownumbers:true,
			    toolbar: "#toolbar1",
				pagination : false, 
				pageSize : 10,	
				pageList : [10,20,30] ,
			    pageNumber:1,   
			    rownumbers:true,
			    onClickRow : function(index, row){
			    	
			    },
			    onDblClickRow : function(index, row){
			    	$('#dlg_io').dialog('open');
			    	$('#dg_io').datagrid("load",WEB_ROOT+'/cms/goods/stock/stock_query/gridDataIOByGoodsid?goodsid='+row.goodsid+"&goodsdtlid="+row.goodsdtlid+"&"+$('#fm1').serialize());
			    }
			});  
			
			$('#dg2').datagrid({
	       		url:WEB_ROOT+'/cms/goods/stock/stock_query/gridData2?'+$('#fm2').serialize(),
			    columns:[[
			        {field:'stockid',title:'',align:'center' ,hidden:true},    
			        {field:'storeid',title:'',align:'center'  ,hidden:true},
			        {field:'storename',title:'库房',align:'center' },
			        {field:'goodsid',title:'',align:'center'  ,hidden:true},
			        {field:'goodsdtlid',title:'',align:'center' ,hidden:true },
			        {field:'goodsname',title:'名称',align:'center' },
			        {field:'customname',title:'通用名',align:'center' },
			        {field:'classid',title:'',align:'center'  ,hidden:true},
			        {field:'classname',title:'类别',align:'center' },
			        {field:'spell',title:'拼音',align:'center' },
			        {field:'goodsstatus',title:'状态',align:'center' },
			        {field:'factoryid',title:'',align:'center' ,hidden:true },
			        {field:'factoryname',title:'厂家',align:'center' },
			        {field:'prodareaid',title:'',align:'center'  ,hidden:true },
			        {field:'prodareaname',title:'产地',align:'center' },
			        {field:'unitid',title:'',align:'center'  ,hidden:true},
			        {field:'unitname',title:'单位',align:'center'  },
			        {field:'spec',title:'规格',align:'center' },
			        {field:'ratio',title:'包装量',align:'center' },
			        {field:'lotid',title:'批次号',align:'center' },
			        {field:'postid',title:'货位',align:'center' },
			        {field:'qty',title:'数量',align:'center' },
			        {field:'status',title:'状态',align:'center' },
			        {field:'batchno',title:'生产批号',align:'center' },
			        {field:'prodate',title:'生产日期',align:'center' },
			        {field:'expiredate',title:'有效期',align:'center' },
			    ]],
			    singleSelect:true,
			    striped:true,
			    fit:true,
			    border:false,
			    rownumbers:true,
			    toolbar: "#toolbar2",
				pagination : false, 
				pageSize : 10,	
				pageList : [10,20,30] ,
			    pageNumber:1,   
			    rownumbers:true,
			    onClickRow : function(index, row){
			    },
			    onDblClickRow : function(index, row){
			    	$('#dlg_io').dialog('open');
			    	$('#dg_io').datagrid("load",WEB_ROOT+'/cms/goods/stock/stock_query/gridDataIOBylotid?lotid='+row.lotid+"&"+$('#fm2').serialize());
			    }
			});  
			
			/* IO流水 */
			$('#dg_io').datagrid({
	       		url:'',
			    columns:[[
							{field:'ioid',title:'',align:'center' ,hidden:true},  
							{field:'iodate',title:'发生日期',align:'center' },    
							{field:'billdtlid',title:'业务明细id',align:'center' },
							{field:'qty',title:'业务数量',align:'center'  },
							{field:'memo',title:'摘要',align:'center'  },
							{field:'stockid',title:'库存id',align:'center'  },
							{field:'storeid',title:'库房id',align:'center'  },
							{field:'lotid',title:'批次',align:'center'  },
							{field:'goodsid',title:'goodsid',align:'center'  },
							{field:'goodsdtlid',title:'goodsdtlid',align:'center'  },
							{field:'billtype',title:'业务类型',align:'center'  },
							{field:'billtable',title:'业务表',align:'center' },
			    ]],
			    singleSelect:true,
			    striped:true,
			    fit:true,
			    border:false,
			    rownumbers:true,
				pagination : false, 
				pageSize : 10,	
				pageList : [10,20,30] ,
			    pageNumber:1,   
			    rownumbers:true
			});  
			
			$('#dlg_io').dialog({  
				title: 'IO流水...',  
			    width: 900,    
			    height: 500,    
			    closed: true,    
			    cache: false,       
			    modal: true,
			}); 
		});  
		function query1(){
			$('#dg1').datagrid("load",WEB_ROOT+'/cms/goods/stock/stock_query/gridData1?'+$('#fm1').serialize()); 
		};
		function query2(){
			$('#dg2').datagrid("load",WEB_ROOT+'/cms/goods/stock/stock_query/gridData2?'+$('#fm2').serialize()); 
		};
	</script>
</head>
<body>
	<div id="tt" >   
	    <div title="库存查询-汇总" >   
	    	<table id="dg1"></table> 
	    </div>   
	    <div title="库存查询-明细" >   
	    	<table id="dg2"></table> 
	    </div>   
	</div>  
	
	<div id="toolbar1" style="padding:0px;height:auto">
		<div >
			<table cellpadding="0" cellspacing="0">	
				<tr>	
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon_magnifier" plain="true"onclick="query1()">查询汇总</a></td>
					<td><div class="btn-separator"></div></td>
				</tr> 
			</table>
		</div>
		<form  id="fm1" method="post" style="padding:5 0 0 0;height:auto;border-top: 1px #DDDDDD solid">
			<div class="div-toolbar">
			名称<input id="goodsname1" name="goodsname1" type="text" class="easyui-textbox" style="width:130px" editable="false"  />
			仓库<input id="storeid1" name="storeid1" type="text" class="easyui-combobox"  style="width:90px"
		    					data-options="required:true,valueField:'storeid',textField:'storename',url:'${pageContext.request.contextPath}/cms/common/storeSelect/getDictStore',panelHeight:'auto',editable:false" />
			</div>
		</form>
	</div>
	<div id="toolbar2" style="padding:0px;height:auto">
		<div >
			<table cellpadding="0" cellspacing="0">	
				<tr>	
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon_magnifier" plain="true"onclick="query2()">查询明细</a></td>
					<td><div class="btn-separator"></div></td>
				</tr> 
			</table>
		</div>
		<form  id="fm2" method="post" style="padding:5 0 0 0;height:auto;border-top: 1px #DDDDDD solid">
			<div class="div-toolbar">
			名称<input id="goodsname2" name="goodsname2" type="text" class="easyui-textbox" style="width:130px" editable="false"  />
			仓库<input id="storeid2" name="storeid2" type="text" class="easyui-combobox"  style="width:90px"
		    					data-options="required:true,valueField:'storeid',textField:'storename',url:'${pageContext.request.contextPath}/cms/common/storeSelect/getDictStore',panelHeight:'auto',editable:false" />
			</div>
		</form>
	</div>
	
	<!-- 查询历史对话框 -->
	<div id="dlg_io" >
		<table id="dg_io"></table>
	</div>
</body>
</html>