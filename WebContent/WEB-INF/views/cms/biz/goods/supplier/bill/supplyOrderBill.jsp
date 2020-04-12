<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商-订单(查看,允许重传)</title>
<jsp:include page="../../../../../inc/injs.jsp" />
<script type="text/javascript">  
	$(function(){
		//检查当前用户参数:1机构 2供应商
		checkSuUser(2);
	
		$("#dg_doc").datagrid({
            url:'',    
			columns:[	 
		             [ 
						//单据状态: 1已审核,2已接收
						{field:'_icon', title:'',align:'center',width:40,
							formatter:function(value,row,index){
								if(row.status==1){
									return '<a href="javascript:void(0);" class="checked" onclick="" />';
								}else if(row.status==2){
									return '<a href="javascript:void(0);" class="recieved" onclick="" />';
								}
							} 
						},
						{title:'订单编号',field:'docno',align:"center",
							formatter: function (value, row, index) { 
			          			return '<span title="'+value+'"  class="easyui-tooltip">'+value+'</span>';
			          		}
		             	}, 
						{title:'机构id',field:'orgid',align:"center",hidden:true}, 
						{title:'机构名称',field:'orgname',align:"center"}, 
						{title:'供应商id',field:'supplyid',align:"center",hidden:true}, 
						{title:'供应商名称',field:'supplyname',align:"center"}, 
						{title:'ordtype',field:'ordtype',align:"center",hidden:true}, 
						{title:'intype',field:'intype',align:"center",hidden:true}, 
						{title:'审核人id',field:'checkuserid',align:"center",hidden:true}, 
						{title:'审核人',field:'checkusername',align:"center"}, 
						{title:'审核日期',field:'checkdate',align:"center"}, 
						{title:'signdate',field:'signdate',align:"center",hidden:true}, 
						{title:'signaddress',field:'signaddress',align:"center",hidden:true}, 
						{title:'signman',field:'signman',align:"center",hidden:true}, 
						{title:'有效期开始',field:'validbegdate',align:"center"}, 
						{title:'有效期结束',field:'validenddate',align:"center"}, 
						{title:'结算方式',field:'settletype',align:"center"}, 
						{title:'预付款',field:'prepay',align:"center"}, 
						{title:'金额',field:'total',align:"center"}, 
						{title:'备注',field:'memo',align:"center"}, 
						{title:'状态',field:'status',align:"center"}, 
						{title:'创建日期',field:'credate',align:"center"}, 
						{title:'userid',field:'userid',align:"center",hidden:true}, 
						{title:'storerid',field:'storerid',align:"center",hidden:true}, 
						{title:'sourceid',field:'sourceid',align:"center"},
		             	{title:'docid',field:'docid',align:"center"}, 
		    		 ]
	             ] ,
			border:0,
			fit:true,
			/* rownumbers:true, */
			singleSelect:true,
		    toolbar: "#toolbar",
		    pagination:false,
		    pageSize: 20,
	        pageList: [20, 50, 100],
	        onClickRow : function(index, row){
	        	$("#dg_dtl").datagrid("reload",WEB_ROOT+'/cms/biz/supply/supplyOrderBill/gridDataDtl?docid='+row.docid);
		    },
		    onDblClickRow : function(index, row){
		    },
		    onLoadSuccess: function (data) {
	    		$('.new').linkbutton({ text: '', plain: true, iconCls: 'fa-file-text-o' });
	    		$('.checked').linkbutton({ text: '', plain: true, iconCls: 'fa-check' });
	    		$('.recieved').linkbutton({ text: '', plain: true ,iconCls: 'fa-exchange'});
	    		$('.canceled').linkbutton({ text: '', plain: true ,iconCls: 'fa-remove'});
		        /* icon_tick   icon_delete  icon_error icon_stop*/
		    },
		})
		$("#dg_dtl").datagrid({
			url:'',    
			columns:[	 
		             [ 
						{title:'dtlid',field:'dtlid',align:"center",hidden:true}, 
						{title:'docid',field:'docid',align:"center",hidden:true}, 
						{title:'序号',field:'sortno',align:"center"}, 
						{title:'goodsid',field:'goodsid',align:"center"}, 
						{title:'平台货品名称',field:'goodsname',align:"center"}, 
						{title:'sugoodsid',field:'sugoodsid',align:"center"}, 
						{title:'供应商货品名称',field:'sugoodsname',align:"center"}, 
						{title:'订单数量',field:'orderqty',align:"center"}, 
						{title:'单位',field:'unit',align:"center"}, 
						{title:'单价',field:'unitprice',align:"center"}, 
						{title:'金额',field:'total',align:"center"}, 
						{title:'备注',field:'memo',align:"center"}, 
						{title:'orgid',field:'orgid',align:"center"}, 
						{title:'supplyid',field:'supplyid',align:"center"}
		    		 ]
	             ] ,
			border:0,
			singleSelect:true,
		    striped:true,
		    fit:true,
		    border:false,
		    pagination:false,
		    pageSize: 20,
	        pageList: [20, 50, 100],
			onClickRow : function(index, row){
		    },
		    onDblClickRow : function(index, row){
		    },
		})
		
		$('#credate1').datebox('setValue', getNowFormatDate());
		$('#credate2').datebox('setValue', getNowFormatDate());
		query();
	});
	function query(){
		$("#dg_doc").datagrid("reload",WEB_ROOT+'/cms/biz/supply/supplyOrderBill/gridDataDoc?'+$("#fm").serialize());
	};
	function setStatus(status){
		var row = $('#dg_doc').datagrid('getSelected');
		//alert(JSON.stringify(row));
		if (row){
			$.post(WEB_ROOT+'/cms/biz/supply/supplyOrderBill/setStatus',{status:status,docid:row.docid},
					function(result){
						if (result.success){
							query();
							$.messager.show({title: '提示',msg: result.msg,showType:'show'});
						}else{
							$.messager.show({title: '提示',msg: result.msg,showType:'show'});
						}
					},'json');
		}
	};
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:true,split:true" style="height:40%">
		<table id="dg_doc"></table>
	</div>
	<div data-options="region:'center',border:true">
		<table id="dg_dtl"></table>
	</div>
	<div id="toolbar">
		<table cellpadding="0" cellspacing="0">	
			<tr>
				<td>
					<form  id="fm" method="post" style="padding:5 0 0 0;height:auto">
						开始日期<input id="credate1" name="credate1" type="text" class="easyui-datebox" style="width:120px" editable="false"  />
						结束日期<input id="credate2" name="credate2" type="text" class="easyui-datebox" style="width:120px" editable="false"  />
					</form>
				</td>	
				<td><div class="btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-search" plain="true"onclick="query()">订单查询</a></td>
				<td><div class="btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-reply" plain="true"onclick="setStatus(1)">允许重传</a></td>
				<td><div class="btn-separator"></div></td>
			</tr> 
		</table>
	</div>
</body>
</html>