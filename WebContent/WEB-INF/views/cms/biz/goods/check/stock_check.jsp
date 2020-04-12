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
    <title>库存盘点</title>
	<jsp:include page="../../../../inc/injs.jsp"/> 
	<script type="text/javascript"> 
	$(function(){
		$('#dg_sum').datagrid({
			url:'',   
		    columns:[[
		        {field:'STOREID',title:'',align:'center',hidden:true},
				{field:'storename',title:'',align:'center',hidden:true},
				{field:'GOODSID',title:'',align:'center',hidden:true},
				{field:'customname',title:'',align:'center',hidden:true},
				{field:'classid',title:'',align:'center',hidden:true},
				{field:'goodsstatus',title:'',align:'center',hidden:true},
				{field:'factoryid',title:'',align:'center',hidden:true},
				{field:'prodareaid',title:'',align:'center',hidden:true},
				{field:'GOODSDTLID',title:'',align:'center',hidden:true},
				{field:'unitid',title:'',align:'center',hidden:true},
				{field:'ratio',title:'',align:'center',hidden:true},
				{field:'goodsname',title:'货品名称',align:'center'}, 
				{field:'spec',title:'规格',align:'center'}, 
				{field:'qty',title:'库存数量',align:'center'}, 
				{field:'realqty',title:'实际数量',align:'center',editor:'numberbox',
					editor:{
		        		type:'numberbox',
		        		options:{
                        	onChange : 
                        		function (newValue, oldValue) {
	                            	var row = $('#dg_sum').datagrid('getSelected');
	                            	var diffval=parseFloat(newValue)-parseFloat(row.qty);
	                            	var rindex = $('#dg_sum').datagrid('getRowIndex', row);
	                            	var ed = $('#dg_sum').datagrid('getEditor', {
	  				                                        index : rindex,
	  				                                        field : 'diffqty'
	  				                                    });
			                        $(ed.target).numberbox('setValue', diffval);
									//触发保存
									//saveCheck();
                        		}
		        		}
		        	}
	        	}, 
				{field:'diffqty',title:'盈亏数量',align:'center', 
	        		editor:{ 
	        			type:'numberbox', 
	        			options:{
	        				editable: false
	        			} 
	        		} ,
	        		formatter: function(value,row,index){
	    				if (value<0){
	    					return "<span style='color:red'>"+value+"</span>";
	    				} else if(value==0){
	    					return '';
	    				} else {
	    					return value;
	    				}
	    			}
	        	}, 
				{field:'unitname',title:'单位',align:'center'}, 
				{field:'classname',title:'类别',align:'center',sortable:true}, 
				{field:'spell',title:'拼音',align:'center'}, 
				{field:'factoryname',title:'厂家',align:'center'}, 
				{field:'prodareaname',title:'产地',align:'center'}, 
				{field:'posid',title:'货位',align:'center'}, 
				{field:'status',title:'状态',align:'center'}   
		    ]],
		    singleSelect:true,
		    striped:true,
		    fit:true,
		    loadMsg:false,
		    border:false,
		    toolbar: "#toolbar1",
		    onClickRow : function(index, row){
		    	endEdit($("#dg_sum"));
		    	var rows=$('#dg_dtl').datagrid("getRows");
		    	if(rows.length>0){
		    		$('#dg_dtl').datagrid("loadData",{total:0,rows:[]});
		    	}
		    },
		    onDblClickRow : function(index, row){
		    	edit($("#dg_sum"));
		    	$('#dg_dtl').datagrid("load",WEB_ROOT+'/cms/goods/check/stock_check/gridDataStockCheckDtl?'
											    			+'goodsid='+row.goodsid
											    			+'&goodsdtlid='+row.goodsdtlid
											    			+'&docid='+row.docid); 
		    },
		    onLoadSuccess:function(){
		    	 // 上下方向键移动
		        // moveRow($("#dg_sum") );  
		    }
		});
		$('#dg_dtl').datagrid({
			url:'',   
		    columns:[[
		        {field:'STOREID',title:'',align:'center',hidden:true},
				{field:'storename',title:'',align:'center',hidden:true},
				{field:'GOODSID',title:'',align:'center',hidden:true},
				{field:'customname',title:'',align:'center',hidden:true},
				{field:'classid',title:'',align:'center',hidden:true},
				{field:'goodsstatus',title:'',align:'center',hidden:true},
				{field:'factoryid',title:'',align:'center',hidden:true},
				{field:'prodareaid',title:'',align:'center',hidden:true},
				{field:'GOODSDTLID',title:'',align:'center',hidden:true},
				{field:'unitid',title:'',align:'center',hidden:true},
				{field:'ratio',title:'',align:'center',hidden:true},
				{field:'classname',title:'类别',align:'center',hidden:true}, 
				{field:'spell',title:'拼音',align:'center',hidden:true}, 
				{field:'factoryname',title:'厂家',align:'center',hidden:true}, 
				{field:'prodareaname',title:'产地',align:'center',hidden:true}, 
				{field:'status',title:'状态',align:'center',hidden:true} ,  
				{field:'posid',title:'货位',align:'center',hidden:true}, 
				{field:'lotid',title:'批次号',align:'center'}, 
				{field:'goodsname',title:'货品名称',align:'center',hidden:true}, 
				{field:'spec',title:'规格',align:'center',hidden:true}, 
				{field:'qty',title:'库存数量',align:'center'}, 
				{field:'realqty',title:'实际数量',align:'center',editor:'textbox'}, 
				{field:'unitname',title:'单位',align:'center'},
				{field:'batchno',title:'批号',align:'center'},
				{field:'prodate',title:'生产日期',align:'center'},
				{field:'expiredate',title:'有效期',align:'center'},
		    ]],
		    singleSelect:true,
		    loadMsg:false,
		    striped:true,
		    fit:true,
		    border:false,
		    //toolbar: "#toolbar2",
		    onClickRow : function(index, row){
		    }
		});
		//初始化一些控件
		//$("#docid").next().hide();
	});

	/*开始盘点 - 打开库房选择*/
	function startCheck(){
		$("#winStoreSelect").window("open");
	};
	/*开始盘点 - 根据当前库房盘点情况,决定是新增盘点还是调用盘点*/
	function realStartCheck(storeid,storename){
		$("#storeid").val(storeid); 
		$("#storename").textbox("setValue",storename);
		$.post(WEB_ROOT+'/cms/goods/check/stock_check/startCheck', { storeid:storeid, storename:storename}, 
				function(result) {
					if(result.success){ 
						$("#docid").textbox("setValue",result.docid);
						$("#docno").textbox("setValue",result.docno);
						$("#status").textbox("setValue",result.status);
						$('#dg_sum').datagrid('load',WEB_ROOT+'/cms/goods/check/stock_check/gridDataStockCheckSum?docid='+result.docid);
						$.messager.show({title: '提示',msg: result.msg,showType:'show'});
					}else{
						$.messager.show({title: '提示',msg: result.msg,showType:'show'});
					}
			}, "JSON"); 
	};
	/* 增加项目 */
	function addGoods(goodsid,goodsdtlid){
		alert(goodsid);
		alert(goodsdtlid);
	};
	/* 删除项目 */
	function deleteGoods(){
		var row=$('#dg_sum').datagrid('getSelected');
		if(row){
			if(row.datasource==1){//数据来源 1：库存表 2：用户新增录入
				$.messager.show({title: '提示',msg: "原库存记录不能删除！",showType:'show'});
			}else{
				var index = $('#dg_sum').datagrid('getRowIndex', row);
				$('#dg_sum').datagrid('deleteRow', index);
			}
		}
	};
	/*保存盘点*/
	function saveCheck(){
		var dataSum="";
		var dataDtl="";
		if ($("#dg_sum").datagrid('getChanges').length) {
			var changedRowsSum = new Object();
			changedRowsSum["inserted"] = $("#dg_sum").datagrid('getChanges', "inserted");
			changedRowsSum["deleted"] = $("#dg_sum").datagrid('getChanges', "deleted");
			changedRowsSum["updated"] = $("#dg_sum").datagrid('getChanges', "updated");
			dataSum=JSON.stringify(changedRowsSum)
		}
		
		if ($("#dg_dtl").datagrid('getChanges').length) {
			var changedRowsDtl = new Object();
			changedRowsDtl["inserted"] = $("#dg_dtl").datagrid('getChanges', "inserted");
			changedRowsDtl["deleted"] = $("#dg_dtl").datagrid('getChanges', "deleted");
			changedRowsDtl["updated"] = $("#dg_dtl").datagrid('getChanges', "updated");
			dataDtl=JSON.stringify(changedRowsDtl)
		}
		$.post(WEB_ROOT+'/cms/goods/check/stock_check/saveCheck', { dataSum:dataSum ,dataDtl:dataDtl}, 
				function(result) {
					if(result.success){ 
						$('#dg_sum').datagrid('reload');
						$.messager.show({title: '提示',msg: result.msg,showType:'show'});
					}else{
						$.messager.show({title: '提示',msg: result.msg,showType:'show'});
					}
			}, "JSON");
		
	};
	/*结束盘点*/
	function finishCheck(){
		alert('结束盘点');
	};
	/* 根据拼音定位货品 */
	function selectRow(spell){
		var rows=$('#dg_sum').datagrid('getRows');
		if(rows){
			for (var i = 0; i < rows.length; i++) {
				if(rows[i].spell.indexOf(spell.toUpperCase()) != -1 ){
					//alert(i)
					$('#dg_sum').datagrid('selectRow',i);
					$('#dg_sum').datagrid('scrollTo',i);
			    	endEdit($("#dg_sum"));
			    	edit($("#dg_sum"));
			    	$('#dg_dtl').datagrid("load",WEB_ROOT+'/cms/goods/check/stock_check/gridDataStockCheckDtl?goodsid='+rows[i].goodsid+'&goodsdtlid='+rows[i].goodsdtlid); 
			    	return
				}
			}
			$('#dg_sum').datagrid('selectRow',0);
			$('#dg_sum').datagrid('scrollTo',0);
	    	endEdit($("#dg_sum"));
	    	$('#dg_dtl').datagrid("loadData",{total:0,rows:[]});
		}
		
	};
	/* 打印盘点 */
	function printCheck (){
		alert('打印盘点');
	}
	/* 审核盘点 */
	function reviewCheck (){
		var docid=$("#docid").textbox("getValue");
		$.post(WEB_ROOT+'/cms/goods/check/stock_check/reviewCheck', { docid:docid}, 
				function(result) {
					if(result.success){ 
						$("#status").textbox("setValue",result.status);
						$.messager.show({title: '提示',msg: result.msg,showType:'show'});
					}else{
						$.messager.show({title: '提示',msg: result.msg,showType:'show'});
					}
			}, "JSON");
	}
	/* 取消盘点 */
	function cancelCheck (){
		var docid=$("#docid").textbox("getValue");
		$.post(WEB_ROOT+'/cms/goods/check/stock_check/cancelCheck', { docid:docid}, 
				function(result) {
					if(result.success){ 
						$("#fm_store").form("clear");
						$('#dg_sum').datagrid("loadData",{total:0,rows:[]});
						$('#dg_dtl').datagrid("loadData",{total:0,rows:[]});
						$.messager.show({title: '提示',msg: result.msg,showType:'show'});
					}else{
						$.messager.show({title: '提示',msg: result.msg,showType:'show'});
					}
			}, "JSON");
	}
	</script>
</head>
<body class="easyui-layout" >
	<div data-options="region:'west',collapsible:false,split:true" style="width:75%" >
		<div id="dg_sum"></div>
	</div>  
	<div data-options="region:'center'"  >
		<div id="dg_dtl"> </div>
	</div>  
	<div id="toolbar1">
		<table cellpadding='1' cellspacing='1'>
			<tr>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_wand' plain='true' onclick='startCheck()'>开始盘点</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_add' plain='true' onclick='$("#winGoodsSelect").window("open")'>增加项目</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_delete' plain='true' onclick='deleteGoods()'>删除项目</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_disk' plain='true' onclick='saveCheck()'>保存盘点</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_lightning' plain='true' onclick='reviewCheck()'>盘点审核</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_tick' plain='true' onclick='finishCheck()'>完成盘点</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_printer' plain='true' onclick='printCheck()'>打印</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_cross' plain='true' onclick='cancelCheck()'>取消盘点</a></td>
				<td><div class='btn-separator'></div></td>
			</tr>
		</table>
		<form:form id="fm_store" modelAttribute="form"  method="post">
			<div class="div-toolbar" style="border-top: 1px #DDDDDD solid">
				<form:input id="storeid" path="storeid" type="text" style="display:none" />
				<input id="docid" name="docid" type="text" class="easyui-textbox" style="width:100px"/>
				库房名称:&nbsp;&nbsp;<form:input id="storename" path="storename" type="text" class="easyui-textbox"  data-options="required:false,disabled:true ,width:80"  />
				盘点单号:&nbsp;&nbsp;<input id="docno" name="docno" type="text" class="easyui-textbox"  data-options="required:false,disabled:true ,width:160"  />
				单据状态:&nbsp;&nbsp;<input id="status" name="status" type="text" class="easyui-combobox" data-options="
																												valueField: 'label',
																												textField: 'value',
																												data: [
																													{label: '临时',value: 'N'},
																													{label: '取消',value: 'F'},
																													{label: '已审',value: 'C'},
																													{label: '完成',value: 'D'}
																												],
																												required:false,disabled:true ,width:80"/>
				拼音:&nbsp;&nbsp;<input id="goodsSpell" name="goodsSpell" type="text" class="easyui-textbox" 
							data-options="required:false ,width:80,onChange: function(value) {selectRow(value)}">
				货品名称:&nbsp;&nbsp;<input id="goodsName" name="goodsName" type="text" class="easyui-textbox" data-options="required:false,width:80">
			</div>
			
		</form:form>
		
	</div>
	
	
	
	
	<!-- 库房  begin -->
	//窗口名winStoreSelect固定，不要改
	<div id="winStoreSelect" class="easyui-window" title="选择库房" style="width:250px;height:220px"   
        data-options="iconCls:'icon_attach',modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false,
        			href:'${pageContext.request.contextPath}/cms/common/storeSelect'"></div>
	<script type="text/javascript">
		function getStore(storeid,storename){
			realStartCheck(storeid,storename);
		}
	</script>
	<!-- 库房  end -->
	
	<!-- 货品  begin -->
	//窗口名winGoodsSelect固定，不要改
	<div id="winGoodsSelect" class="easyui-window" title="选择货品" style="width:600px;height:430px"   
        data-options="iconCls:'icon_attach',modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false,
        			href:'${pageContext.request.contextPath}/cms/common/goodsSelect'"></div>
	<script type="text/javascript">
		//方法名getGoodsid固定，不要改
		//选择完货品后，根据goodsid,goodsdtlid处理
		function getGoodsid(goodsid,goodsdtlid){
			addGoods(goodsid,goodsdtlid);
		}
	</script>
	<!-- 货品  end -->
</body>
</html>