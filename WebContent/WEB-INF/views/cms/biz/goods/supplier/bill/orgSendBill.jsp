<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机构-送货单(查看,审核,作废)</title>
<jsp:include page="../../../../../inc/injs.jsp" />
<script type="text/javascript">  
	$(function(){
		//检查当前用户参数:1机构 2供应商
		checkSuUser(1);
	
		$("#dg_doc").datagrid({
            url:'',    
			columns:[	 
		             [ 
						//单据状态: -1作废,0新订单,1已审核,2已接收
						{field:'_icon', title:'',align:'center',width:40,
							formatter:function(value,row,index){
								if(row.status==0){
									return '<a href="javascript:void(0);" class="new" onclick="" />';
								}else if(row.status==1){
									return '<a href="javascript:void(0);" class="checked" onclick="" />';
								}else if(row.status==2){
									return '<a href="javascript:void(0);" class="recieved" onclick="" />';
								}else if(row.status==-1){
									return '<a href="javascript:void(0);" class="canceled" onclick="" />';
								}
							} 
						},
						{title:'',field:'docid',align:"center",hidden:true}, 
						{title:'送货单号',field:'docno',align:"center"}, 
						{title:'',field:'orgid',align:"center",hidden:true}, 
						{title:'机构名称',field:'orgname',align:"center"}, 
						{title:'',field:'supplyid',align:"center",hidden:true}, 
						{title:'供应商名称',field:'supplyname',align:"center"}, 
						{title:'送货单类型',field:'sendtype',align:"center"}, 
						{title:'',field:'checkuserid',align:"center",hidden:true}, 
						{title:'审核人',field:'checkusername',align:"center"}, 
						{title:'审核时间',field:'checkdate',align:"center"}, 
						{title:'送货地点',field:'sendaddress',align:"center"}, 
						{title:'送货时间',field:'senddatetime',align:"center"}, 
						{title:'',field:'memo',align:"center",hidden:true}, 
						{title:'状态',field:'status',align:"center"}, 
						{title:'',field:'credate',align:"center",hidden:true}, 
						{title:'',field:'userid',align:"center",hidden:true}, 
						{title:'',field:'storerid',align:"center",hidden:true}, 
						{title:'',field:'orderid',align:"center",hidden:true}, 
						{title:'',field:'sourceid',align:"center"}, 
						{title:'',field:'intype',align:"center",hidden:true}
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
		        	$("#dg_dtl").datagrid("reload",WEB_ROOT+'/cms/biz/supply/orgSendBill/gridDataDtl?docid='+row.docid);
			    },
			    onDblClickRow : function(index, row){
			    },
			    onLoadSuccess: function (data) {
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
						{title:'sugoodsid',field:'sugoodsid',align:"center"}, 
						{title:'供应商货品名称',field:'sugoodsname',align:"center"}, 
						{title:'goodsid',field:'goodsid',align:"center"}, 
						{title:'平台货品名称',field:'goodsname',align:"center"},  
						{title:'送货数量',field:'sendqty',align:"center"}, 
						{title:'收货数量',field:'recieveqty',align:"center",
							editor:"numberbox",
							formatter: function (value,row,index) {
								//alert(value.toString())
								//alert(row.sendqty)
		                        if (value.toString() != row.sendqty) {
		                            return "<span style='color:red;'>"+value+"</span>";
		                        } else {
		                            return "<span style='color:#000;'>"+value+"</span>";
		                        }
		                    }
						}, 
						{title:'单位',field:'unit',align:"center"}, 
						{title:'单价',field:'unitprice',align:"center"}, 
						{title:'金额',field:'total',align:"center"}, 
						{title:'备注',field:'memo',align:"center"},  
						{title:'',field:'batchno',align:"center"}, 
						{title:'',field:'expirydate',align:"center"}, 
						{title:'',field:'dtlstatus',align:"center"}, 
						{title:'orgid',field:'orgid',align:"center"}, 
						{title:'supplyid',field:'supplyid',align:"center"}
		    		 ]
	             ] ,
			border:0,
			singleSelect:true,
		    striped:true,
		    toolbar: "#toolbar_dtl",
		    fit:true,
		    border:false,
		    pagination:false,
		    pageSize: 20,
	        pageList: [20, 50, 100],
			onClickRow : function(index, row){
				endEdit($("#dg_dtl"));
		    },
		    onDblClickRow : function(index, row){
		    	endEdit($("#dg_dtl"));
		    	edit($("#dg_dtl"));
		    },
		});
		$("#supplyid").combobox({
			url:'${pageContext.request.contextPath}/cms/biz/supply/orgOrderBill/getSupplyByOrgid',
			required:false,
            valueField:'supplyid',
            textField:'supplyname',
            limitToList:true,
			panelHeight:'auto',
			editable:true,
			width:200,
            onLoadSuccess:function(data){
	            /* //默认选中第一个
	            var array=$(this).combobox("getData");
	            for(var item in array[0]){
	                if(item=="supplyid"){
	                   $(this).combobox('select',array[0][item]);
	                 }
	            } */
            }
        });
		$('#credate1').datebox('setValue', getNowFormatDate());
		$('#credate2').datebox('setValue', getNowFormatDate());
		query();
	});
	
	function query(){
		$("#dg_doc").datagrid("reload",WEB_ROOT+'/cms/biz/supply/orgSendBill/gridDataDoc?'+$("#fm").serialize());
	};
	function setStatus(status){
		var row = $('#dg_doc').datagrid('getSelected');
		var index = $('#dg_doc').datagrid('getRowIndex', row);
		//alert(JSON.stringify(row));
		if (row){
			$.post(WEB_ROOT+'/cms/biz/supply/orgSendBill/setStatus',{status:status,docid:row.docid},
					function(result){
						if (result.success){
							query();
							$('#dg_doc').datagrid('selectRow',index);
							$.messager.show({title: '提示',msg: result.msg,showType:'show'});
						}else{
							$.messager.show({title: '提示',msg: result.msg,showType:'show'});
						}
					},'json');

		}
	};
	/* dtl如果需要修改则保留以下代码 */
	function save(){
		endEdit($("#dg_dtl"));
		if ($("#dg_dtl").datagrid('getChanges').length) {
			var changedRows = new Object();
			changedRows["inserted"] = $("#dg_dtl").datagrid('getChanges',"inserted");
			changedRows["deleted"] = $("#dg_dtl").datagrid('getChanges', "deleted");
			changedRows["updated"] = $("#dg_dtl").datagrid('getChanges', "updated");
			$.post(WEB_ROOT + '/cms/biz/supply/orgSendBill/saveDataDtl', {json : JSON.stringify(changedRows)}, 
				function(result) {
					if (result.success) {
						$('#dg_dtl').datagrid('reload');
					}
					$.messager.show({ title : '提示', msg : result.msg, showType : 'show' });
				}, "JSON");
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
						开始日期<input id="credate1" name="credate1" type="text" class="easyui-datebox" style="width:130px" editable="false"  />
						结束日期<input id="credate2" name="credate2" type="text" class="easyui-datebox" style="width:130px" editable="false"  />
						供应商<input id="supplyid" name="supplyid"   />
					</form>
				</td>
				<td><div class="btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-search" plain="true"onclick="query()">送货单查询</a></td>
				<td><div class="btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-reply" plain="true"onclick="setStatus(1)">允许重传</a></td>
				<td><div class="btn-separator"></div></td>
			</tr> 
		</table>
		
	</div>
	<div id="toolbar_dtl">
		<div >
			<table cellpadding="0" cellspacing="0">	
				<tr>	
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-save" plain="true"onclick="save()">保存细单</a></td>
					<td><div class="btn-separator"></div></td>
				</tr> 
			</table>
		</div>
	</div>
</body>
</html>