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
    <title>二级库申领</title>
	<jsp:include page="../../../../inc/injs.jsp"/> 
	<script type="text/javascript">  
		var userDeptid="${user.deptid}"
		var userDeptname="${user.deptname}"
		$(function(){
			$("#dg_dtl").datagrid({
				url:'',   
			    columns:[[
							{field:'dtlid', title:'细单id',align:'center',hidden:true  }, 
							{field:'docid', title:'总单id',align:'center' ,hidden:true},
							{field:'rowno', title:'顺序号',align:'center',hidden:true }, 
							{field:'goodsid', title:'货品主档ID',align:'center',hidden:true },
							{field:'goodsdtlid', title:'货品明细ID',align:'center' ,hidden:true},
							{field:'spell', title:'拼音',width:120,align:'center' ,hidden:true }, 
							{field:'factoryname', title:'厂家',align:'center'  ,hidden:true},
							{field:'prodareaname', title:'产地',align:'center',hidden:true  }, 
							{field:'goodsname', title:'货品名称',width:120,align:'center' },
							{field:'spec', title:'规格',width:120,align:'center' },
							{field:'qty', title:'申请数量',width:100,align:'center',editor : 'numberbox'},
							{field:'realqty', title:'入库数量',width:100,align:'center'}, 
							{field:'unitname', title:'单位',align:'center' },
							{field:'memo', title:'备注',align:'center' },
			              ]],
			    singleSelect:true,
			    striped:true,
			    rownumbers:true,
			    fit:true,
			    border:false,
			    onClickRow : function(index, row){
			    	endEdit($("#dg_dtl"));
			    },
			    onDblClickRow : function(index, row){
			    	endEdit($("#dg_dtl"));
			    	edit($("#dg_dtl"));
			    },
			});
			
			$('#dlg_history').dialog({  
			    title: '历史查询...',    
			    width: 900,    
			    height: 500,    
			    closed: true,    
			    cache: false,       
			    modal: true,
			    toolbar: "#tb_history"
			});  
			$('#dg_history_doc').datagrid({ 
				url:'',    
				border:0,
				fit:true,
				fitColumns:false,
				/* rownumbers:true, */
				singleSelect:true,
			    columns:[	 
				             [ 
				             {title:'',field:'docid',align:"center",hidden:true}, 
				             {title:'总单编号',field:'docno',align:"center"}, 
				             {title:'',field:'billtype',width:40,align:"center",hidden:true}, 
				             {title:'单据类型名称',field:'billname',align:"center"}, 
				             {title:'',field:'storeid',width:40,align:"center",hidden:true}, 
				             {title:'',field:'deptid',width:40,align:"center",hidden:true}, 
				             {title:'库房名称',field:'storename',align:"center"}, 
				             {title:'',field:'createrid',width:40,align:"center",hidden:true}, 
				             {title:'单据日期',field:'createdate',align:"center"}, 
				             {title:'',field:'checkerid',width:40,align:"center",hidden:true}, 
				             {title:'',field:'checkdate',width:40,align:"center",hidden:true}, 
				             {title:'单据状态',field:'status',align:"center"}, 
				             {title:'',field:'memo',width:40,align:"center",hidden:true}
				    		 ]
			             ] ,
		         	view: detailview,
		        	detailFormatter: function(rowIndex, rowData){
		        		return '<table id="table_history_doc">'+
			        				'<tr><td>总单ID: </td>' + '<td>'+rowData.docid +'</td></tr>'+
			        				'<tr><td>备注: </td>' + '<td>'+rowData.memo + '</td></tr>' +
		        				'</table>';
		        	},
			        pagination : false, 
					pageSize : 15,	
					pageList : [15,30,60] ,
					onClickRow: function (index, row) {
						$("#dg_history_dtl").datagrid({url:WEB_ROOT+'/cms/goods/stock/stock_out/gridDataDtl?docid='+row.docid});
					},
					onDblClickRow: function (index, row) {
						loadBill();
					},
		        	onLoadSuccess : function (data) { 
		        		
		        	}
			});
			$('#dg_history_dtl').datagrid({ 
				url:'',     
				border:0,
				fit:true,
				fitColumns:false,
			    remoteSort:false,  
				/* rownumbers:true, */
				singleSelect:true,
			    columns:[[
							{field:'dtlid', title:'细单id',align:'center',hidden:true  }, 
							{field:'docid', title:'总单id',align:'center' ,hidden:true},
							{field:'rowno', title:'顺序号',align:'center',hidden:true }, 
							{field:'goodsid', title:'货品主档ID',align:'center',hidden:true },
							{field:'goodsdtlid', title:'货品明细ID',align:'center' ,hidden:true},
							{field:'spell', title:'拼音',width:120,align:'center' ,hidden:true }, 
							{field:'factoryname', title:'厂家',align:'center'  ,hidden:true},
							{field:'prodareaname', title:'产地',align:'center',hidden:true  }, 
							{field:'goodsname', title:'货品名称',width:120,align:'center' },
							{field:'spec', title:'规格',width:120,align:'center' },
							{field:'qty', title:'申请数量',width:100,align:'center'},
							{field:'realqty', title:'出库数量',width:100,align:'center',editor : 'numberbox'}, 
							{field:'unitname', title:'单位',align:'center' },
							{field:'memo', title:'备注',align:'center' },
			              ]],
		        pagination : false, 
				pageSize : 40,	
				pageList : [40,50,60] ,
			    onLoadSuccess : function (data) { 
			    }
			});
		});
		
		
		//新增总单
		function addDoc(){
			$("#fm_doc").form("clear");
			$("#billtype").val("KSSQ");
			$("#billname").textbox('setValue',"科室申请");
			$("#deptid").val(userDeptid);
			$("#deptname").textbox('setValue',userDeptname);
			$("#status").textbox('setValue',"N");
			$("#dg_dtl").datagrid("loadData", { total: 0, rows: [] });
		};
		//新增细单
		//校验库房id有效,打开库存选择窗口
		function openStockSelect(){
			var storeid=$("#storeid").combobox("getValue");
			if(storeid==""){
				$("#winStockSelect").window("close");
				$.messager.show({title: '提示',msg: "请先选择仓库！",showType:'show'});
				return;
			}
			//动态加载storeid
			$('#winStockSelect').window({
				href: '${pageContext.request.contextPath}/cms/common/goodsStockSelectSum?storeid='+storeid,
			});
			$("#winStockSelect").window("open");
		};
		//新增细单
		function addDtl(storeid,goodsid,goodsdtlid){
			$.post(WEB_ROOT+'/cms/common/goodsSelect/getGoodsSelect', { goodsid:goodsid, goodsdtlid:goodsdtlid}, 
					function(result) {
						if(result.success){ 
							//alert(result.data.goodsname);
							$('#dg_dtl').datagrid('appendRow',result.data);
							var row = $('#dg_dtl').datagrid('getRows');
							$('#dg_dtl').datagrid('selectRow',row.length -1);// 关键在这里
							edit($('#dg_dtl'));
						}else{
							$.messager.show({title: '提示',msg: result.msg,showType:'show'});
						}
				}, "JSON");
		};
		
		//暂存
		function save(){
			var dataDtl="";
			if (!endEdit($('#dg_dtl'))){return;};
			if ($("#dg_dtl").datagrid('getChanges').length) {
				var changedRows = new Object();
				changedRows["inserted"] = $("#dg_dtl").datagrid('getChanges', "inserted");
				changedRows["deleted"] = $("#dg_dtl").datagrid('getChanges', "deleted");
				changedRows["updated"] = $("#dg_dtl").datagrid('getChanges', "updated");
				dataDtl=JSON.stringify(changedRows)
			}
			$('#fm_doc').form('submit', {    
			    url:WEB_ROOT+'/cms/goods/stock2/stock2_apply/save',    
			    onSubmit: function(param){
			    	param.p1 = dataDtl;    
			    	//表单验证信息
				 	return $(this).form('validate');
			    },    
			    success:function(data){   
			    	var result=JSON.parse(data);
			    	$.messager.show({title: '提示',msg:result.msg,showType:'show'});
			    	$('#fm_doc').form('load',result.docData);
			    	$('#dg_dtl').datagrid('reload',WEB_ROOT+'/cms/goods/stock2/stock2_apply/gridDataDtl?docid='+result.docData.docid);
			    }    
			});  
		}
		//设置状态  作废or审核
		function setStatus(value){
			var docid=$("#docid").val();
			var status=$("#status").val();
			$.post(WEB_ROOT+'/cms/goods/stock2/stock2_apply/setStatus', { docid : docid,status:status,value:value }, 
					function(result) {
						if(result.success){ 
							$('#fm_doc').form('load',result.docData);
							$('#dg_dtl').datagrid('reload',WEB_ROOT+'/cms/goods/stock2/stock2_apply/gridDataDtl?docid='+result.docData.docid);
							$.messager.show({title: '提示',msg: result.msg,showType:'show'});
						}else{
							$.messager.show({title: '提示',msg: result.msg,showType:'show'});
						}
				}, "JSON");
		};
		//查询
		function query(){
			addDoc();
			$('#dlg_history').dialog("open");
 			$('#credate_history').datebox('setValue', getNowFormatDate());	
 			$('#dg_history_doc').datagrid('load',WEB_ROOT+'/cms/goods/stock2/stock2_apply/gridDataDocBycredate?'+$('#fmHistory').serialize()); 
 			$('#dg_history_dtl').datagrid('loadData',{total:0,rows:[]});
		};
		//载入单据
		function loadBill(){
			var row = $('#dg_history_doc').datagrid("getSelected");
			if(row){
				$('#fm_doc').form('load',WEB_ROOT+'/cms/goods/stock2/stock2_apply/gridDataDocByid?docid='+row.docid);
		    	$('#dg_dtl').datagrid('load',WEB_ROOT+'/cms/goods/stock2/stock2_apply/gridDataDtl?docid='+row.docid);
		    	$('#dlg_history').dialog('close');
			}          
		}
		//历史查询
		function history_query(){
			$('#dg_history_doc').datagrid('load',WEB_ROOT+'/cms/goods/stock2/stock2_apply/gridDataDocBycredate?'+$('#fmHistory').serialize()); 
 			$('#dg_history_dtl').datagrid('loadData',{total:0,rows:[]});
		}
	</script>
	
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:true" style="height:130px">
		<form:form id="fm_doc" modelAttribute="form"  method="post">
			<div id="toolbar_doc" class="datagrid-toolbar">
		        <table cellpadding='1' cellspacing='1'>
					<tr>
						<td><div class='btn-separator'></div></td>
						<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_add' plain='true' onclick='addDoc()'>新增总单</a></td>
						<td><div class='btn-separator'></div></td>
						<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_disk' plain='true' onclick='save()'>保存</a></td>
						<td><div class='btn-separator'></div></td>
						<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_printer' plain='true' onclick=''>打印</a></td>
						<td><div class='btn-separator'></div></td>
						<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_cross' plain='true' onclick='setStatus("F")'>作废</a></td>
						<td><div class='btn-separator'></div></td>
						<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_lightning' plain='true' onclick='setStatus("C")'>审核</a></td>
						<td><div class='btn-separator'></div></td>
						<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_tick' plain='true' onclick='setStatus("S")'>发送</a></td>
						<td><div class='btn-separator'></div></td>
						<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_magnifier' plain='true' onclick='query()'>查询</a></td>
						<td><div class='btn-separator'></div></td>
					</tr>
				</table>
		    </div> 
		    
		    
		    <form:input id="docid" path="docid" type="text" style="display:none" />
		    <form:input id="billtype" path="billtype" type="text" style="display:none" value="KSSQ"  />
		    <form:input id="createrid" path="createrid" type="text" style="display:none" />
		    <form:input id="createdate" path="createdate" type="text" style="display:none" />
		    <form:input id="deptid" path="deptid" type="text" style="display:none"  value="${user.deptid }"/>
		    <table style="font-size:12;padding:3 0 0 0">
		    	<tr>
		    		<td>单据号:</td><td><form:input id="docno" path="docno" type="text" class="easyui-textbox"  data-options="required:false,disabled:true" readOnly="true" /></td>
		    		<td>单据类型</td><td><form:input id="billname" path="billname" type="text" class="easyui-textbox"  data-options="required:false,disabled:true" readOnly="true" value="科室申请"/></td>
		    		<td>单据状态</td><td><form:input id="status" path="status" type="text" class="easyui-textbox"  data-options="required:false,disabled:true" readOnly="true" value="N"/></td>
		    	</tr>
		    	<tr>
		    		<td>审核人</td><td><form:input id="checkerid" path="checkerid" type="text" class="easyui-textbox"  data-options="required:false,disabled:true" readOnly="true" /></td>
		    		<td>审核时间</td><td><form:input id="checkdate" path="checkdate" type="text" class="easyui-textbox"  data-options="required:false,disabled:true" readOnly="true" /></td>
		    		<td>发送人</td><td><form:input id="senderid" path="senderid" type="text" class="easyui-textbox"  data-options="required:false,disabled:true" readOnly="true" /></td>
		    		<td>发送时间</td><td><form:input id="senddate" path="senddate" type="text" class="easyui-textbox"  data-options="required:false,disabled:true" readOnly="true" /></td>
		    	</tr>
		    	<tr>
		    		<td>申领科室</td><td><form:input id="deptname" path="deptname" type="text" class="easyui-textbox"   data-options="required:false,disabled:true" readOnly="true" value="${user.deptname }"  /></td>
		    		<td>仓库</td><td><form:input id="storeid" path="storeid" type="text" class="easyui-combobox"  
		    					data-options="required:true,valueField:'storeid',textField:'storename',url:'${pageContext.request.contextPath}/cms/common/storeSelect/getDictStore',panelHeight:'auto',editable:false" /></td>
		    		<td>备注</td><td colspan="3"><form:input id="memo" path="memo" type="text" class="easyui-textbox"  data-options="required:false,width:390"  /></td>
		    	</tr>
		    </table>
		</form:form>
	</div>
	<div data-options="region:'center',border:true">
		<div id="toolbar_dtl" class="datagrid-toolbar">
	        <table cellpadding='1' cellspacing='1'>
				<tr>
					<td><div class='btn-separator'></div></td>
					<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_add' plain='true' onclick='openStockSelect()'>新增明细</a></td>
					<td><div class='btn-separator'></div></td>
					<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_cancel' plain='true' onclick='del($("#dg_dtl"))'>删除明细</a></td>
					<td><div class='btn-separator'></div></td>
				</tr>
			</table>
	    </div>
		<table id="dg_dtl"></table>
	</div>
	
	<!-- 查询历史对话框 -->
	<div id="dlg_history" >
		<div  class="easyui-layout" style="border:0px red solid;width:100%;height:100%">
			<div data-options="region:'west',split:true" style="width:30%;">
		 		<table id="dg_history_doc"></table>
		 	</div>
		    <div data-options="region:'center'" style="padding:0px;">
		    	<table id="dg_history_dtl"></table>
		    </div> 
		</div>
	</div>
	<!-- 查询历史对话框工具栏 -->
	<div id="tb_history" style="padding:0px;height:auto">
		<div >
			<table cellpadding="0" cellspacing="0">	
				<tr>	
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon_arrow_refresh" plain="true"onclick="history_query()">刷新</a></td>
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon_tick" plain="true"onclick="loadBill()">调取</a></td>
					<td><div class='btn-separator'></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon_cross" plain="true"onclick="javascript:$('#dlg_history').dialog('close');" >关闭</a></td>
					<td><div class="btn-separator"></div></td>
				</tr> 
			</table>
		</div>
		<form  id="fmHistory" method="post" style="padding:5 0 0 0;height:auto">
			日期<input id="credate_history" name="credate_history" type="text" class="easyui-datebox" style="width:130px" editable="false"  />
		</form>
	</div>
	
	<!-- 通用库存货品选择  begin -->
	<!-- 窗口名winGoodsStockSelect固定，不要改 -->
	<div id="winStockSelect" class="easyui-window" title="选择库存货品" style="width:600px;height:430px"   
        data-options="iconCls:'icon_attach',modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false"></div>
	<script type="text/javascript">
		//方法名getGoodsid固定，不要改
		//选择完货品后，根据goodsid,goodsdtlid处理
		function getStockGoodsid(storeid,goodsid,goodsdtlid){
			addDtl(storeid,goodsid,goodsdtlid);
		}
	</script>
	<!-- 库存货品  end -->
	
	
	
</body>
</html>