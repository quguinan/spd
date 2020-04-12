<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商耗材目录</title>

<jsp:include page="../../../../../inc/injs.jsp" />
<script type="text/javascript">  
    /* 页面全局变量 */
	var v_orgid='';
	var v_orgname='';
	var v_supplyid='';
	
	$(function(){
		//检查当前用户参数:1机构 2供应商
		checkSuUser(2);
	
		$("#dg_supply").datagrid({
			url:'',   
		    columns:[[
						{field:'orgid', title:'docid',align:'center',hidden:true }, 
						{field:'supplyid', title:'docid',align:'center',hidden:true },  
						{field:'sugoodsid', title:'供应商货品ID',align:'center' }, 
						{field:'sugoodsname', title:'供应商货品名称',align:'center',width:120 }, 
						{field:'goodsid', title:'平台货品ID',align:'center' }, 
						{field:'goodsname', title:'平台货品名称',align:'center',width:120 }, 
						{field:'spell', title:'拼音',align:'center' }, 
						{field:'spec', title:'规格',align:'center'}, 
						{field:'unit', title:'单位',align:'center' },  
						{field:'classcode', title:'分类编码',align:'center' }, 
						{field:'classname', title:'分类名称',align:'center' }, 
						{field:'factory', title:'厂家',align:'center' }, 
						{field:'prodarea', title:'产地',align:'center' }, 
		              ]],
		    singleSelect:true,
		    title:"([${suUser.supplyid}]${suUser.supplyname})供应商货品字典",
		    striped:true,
		    //rownumbers:true,
		    fit:true,
		    pagination:true,
		    pageSize: 20,
	        pageList: [20, 50, 100],
		    border:false,
		    toolbar: "#tb_supply",
		    onClickRow : function(index, row){
		    },
		    onDblClickRow : function(index, row){
		    },
		    onLoadSuccess : function(data){
		        
		    }
		});
		
		
		$("#dg_org").datagrid({
			url:'',   
		    columns:[[
						{field:'orgid', title:'orgid',align:'center',hidden:true }, 
						{field:'goodsid', title:'货品ID',align:'center' }, 
						{field:'goodsname', title:'货品名称',align:'center',width:120 }, 
						{field:'spell', title:'拼音',align:'center' ,width:120}, 
						{field:'classcode', title:'类型编码',align:'center' },
						{field:'classname', title:'类型名称',align:'center' },
						{field:'spec', title:'规格',align:'center' },
						{field:'unit', title:'单位',align:'center' },
						{field:'factory', title:'厂家',align:'center' },
						{field:'prodarea', title:'产地',align:'center' },
						{field:'orgname', title:'机构名称',align:'center' ,hidden:true },
						{field:'goodstype', title:'货品分类',align:'center' }, 
		              ]],
		    singleSelect:true,
		    striped:true,
		    title:"平台货品字典",
		    //title:"([{suUser.orgid}]{suUser.orgname})平台货品字典",
		    //rownumbers:true,
		    fit:true,
		    border:true,
		    toolbar: "#tb_org",
		    pagination:true,
		    pageSize: 20,
	        pageList: [20, 50, 100],
		    onClickRow : function(index, row){
		    },
		    onDblClickRow : function(index, row){
		    	doMap();
		    },
		});
		
		$("#dg_org_select").datagrid({
			url:'',   
		    columns:[[
						{field:'orgid', title:'机构ID',align:'center' }, 
						{field:'orgname', title:'机构名称',align:'center' }, 
		              ]],
		    singleSelect:true,
		    striped:true,
		    //title:"([{suUser.orgid}]{suUser.orgname})平台货品字典",
		    //rownumbers:true,
		    fit:true,
		    border:true,
		    pagination:false,
		    pageSize: 20,
	        pageList: [20, 50, 100],
		    onClickRow : function(index, row){
		    },
		    onDblClickRow : function(index, row){
		    	v_orgid=row.orgid;
		    	v_orgname=row.orgname;
				v_supplyid=row.supplyid;
				queryOrg();
				querySupply(); 
				
		    	$('#dlg_selectOrgid').dialog('close');
		    },
		});
		//选择orgid
		SelectOrgid();
	}); 
	//平台货品查询
	function queryOrg(){
		$.post(WEB_ROOT+'/cms/common/checkSuUserOrgSupply',{orgid:v_orgid,supplyid:v_supplyid},
				function(result){
					if (result.success){
						$('#dg_org').datagrid({title:'(['+v_orgid+']'+v_orgname+')平台货品字典'})
						$("#dg_org").datagrid("reload",WEB_ROOT+'/cms/biz/supply/orgGoods/gridDataOrgByClassidNameGoodsidSpell?orgid='+v_orgid+'&'+$("#fmOrg").serialize());
					}else{
						$.messager.show({title: '提示',msg: result.msg,showType:'show'});
					}
				},'json');
	}
	//供应商货品查询
	function querySupply(){
		url=WEB_ROOT+'/cms/biz/supply/supplyGoods/gridDataSupplyByNameSpell?orgid='+v_orgid+'&'+$("#fmSupply").serialize();
		$("#dg_supply").datagrid("reload",url)
	}
	//供应商货品对照
	function doMap(){
		var rowSupply=$("#dg_supply").datagrid("getSelected");
		var indexSupply=$("#dg_supply").datagrid("getRowIndex",rowSupply);
		var rowOrg=$("#dg_org").datagrid("getSelected");
		if (rowSupply==null){
			alert("请选择(供应商货品)");
		}else if(rowOrg==null){
			alert("请选择(平台货品)");
		}else{
			//alert(rowSupply.goodsid);
			if(rowSupply.goodsid!=undefined){
				$.messager.confirm('确认对话框', '确定覆盖已对照信息？', function(r){
					if (r){
						$.post(WEB_ROOT+'/cms/biz/supply/supplyGoods/doMap',
								{orgid:rowSupply.orgid,
							 	supplyid:rowSupply.supplyid,
							 	sugoodsid:rowSupply.sugoodsid,
							 	goodsid:rowOrg.goodsid},
								function(result){
									if (result.success){
										//$('#dg_supply').datagrid('selectRow',indexSupply);
							    		$('#dg_supply').datagrid("reload"); 
									}else{
										$.messager.show({title: '提示',msg: result.msg,showType:'show'});
									}
								},'json'); 
					}
				});
			}else{
				$.post(WEB_ROOT+'/cms/biz/supply/supplyGoods/doMap',
						{orgid:rowSupply.orgid,
					 	supplyid:rowSupply.supplyid,
					 	sugoodsid:rowSupply.sugoodsid,
					 	goodsid:rowOrg.goodsid},
						function(result){
							if (result.success){
								//$('#dg_supply').datagrid('selectRow',indexSupply);
					    		$('#dg_supply').datagrid("reload");  
							}else{
								$.messager.show({title: '提示',msg: result.msg,showType:'show'});
							}
						},'json'); 
			}
		}
		//$('#dg_supply').datagrid("selectRow",indexSupply);
	}
	//供应商货品取消对照
	function undoMap(){
		var rowSupply=$("#dg_supply").datagrid("getSelected");
		var indexSupply=$("#dg_supply").datagrid("getRowIndex",rowSupply);
		if (rowSupply==null){
			alert("请选择(供应商货品)");
		}else{
			$.messager.confirm('确认对话框', '确定取消对照信息？', function(r){
				if (r){
					$.post(WEB_ROOT+'/cms/biz/supply/supplyGoods/undoMap',
							{orgid:rowSupply.orgid,
							 supplyid:rowSupply.supplyid,
							 sugoodsid:rowSupply.sugoodsid},
							function(result){
								if (result.success){
						    		$('#dg_supply').datagrid("reload");   
								}
							},'json'); 
				}
			})
		}
	}
	//调用接口,导入供应商信息
	function importData(){
		
	}
	//重选机构
	function SelectOrgid(){
		/*判断此供应商(当前用户)是否有多个机构与之对应，
		有多个： 则 选择   
		仅一个： 则 直接打开
		0  : 提示
		*/
		$.post(WEB_ROOT+'/cms/biz/supply/supplyOrg/findSupplyOrgBySupplyid',{},
				function(result){
					if (result.orgidCount==0){
						$.messager.show({title: '提示',msg: "没有机构选择此供应商,请联系管理员进行维护!",showType:'show'});
						$('#reSelectOrgid').linkbutton('disable');
					}else if(result.orgidCount==1){
						v_orgid=result.orgs[0].orgid;
						v_orgname=result.orgs[0].orgname;
						v_supplyid=result.orgs[0].supplyid;
						queryOrg();
						querySupply(); 
						$('#reSelectOrgid').linkbutton('disable');
					}else if(result.orgidCount>1){
						$("#dg_org_select").datagrid("loadData",result.orgs);
						$('#dlg_selectOrgid').dialog('open');
						$('#reSelectOrgid').linkbutton('enable');
					}
				},'json');
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',border:true" style="width:50%">
		<table id="dg_supply"></table>
	</div>
	<div data-options="region:'center',border:false">
    	<table id="dg_org"></table>
    </div>
    
    <div id="tb_supply" style="padding:0px;height:auto">
		<div >
			<table cellpadding="0" cellspacing="0">	
				<tr>	
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-download" plain="true"onclick="importData()">下载</a></td>
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-search" plain="true"onclick="querySupply()">查找</a></td>
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-check" plain="true"onclick="doMap()" >对照</a></td>
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-close" plain="true"onclick="undoMap()" >取消对照</a></td>
					<td><div class="btn-separator"></div></td>
				</tr> 
			</table>
		</div>
		<form  id="fmSupply" method="post" style="padding:5 0 0 0;height:auto">
			货品ID<input id="sugoodsid" name="sugoodsid" type="text" class="easyui-textbox" style="width:100px"   />
			货品名称<input id="sugoodsname" name="sugoodsname" type="text" class="easyui-textbox" style="width:100px"   />
			拼音<input id="suspell" name="suspell" type="text" class="easyui-textbox" style="width:100px"   />
		</form>
	</div>
	
	<div id="tb_org" style="padding:0px;height:auto">
		<div >
			<table cellpadding="0" cellspacing="0">	
				<tr>	
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-search" plain="true"onclick="queryOrg()">查找</a></td>
					<td><div class="btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-refresh" plain="true"onclick="SelectOrgid()" id="reSelectOrgid">重选机构</a></td>
					<td><div class="btn-separator"></div></td>
				</tr> 
			</table>
		</div>
		<form  id="fmOrg" method="post" style="padding:5 0 0 0;height:auto">
			<%-- 货品类型<input id="classcode" name="classcode" type="text" class="easyui-combobox" style="width:120px"   
					data-options="required:false,valueField:'classcode',textField:'classname',
					url:'${pageContext.request.contextPath}/cms/biz/supply/orgGoods/getClasslist',panelHeight:'200',editable:false" /> --%>
			货品ID<input id="goodsid" name="goodsid" type="text" class="easyui-textbox" style="width:100px"   />
			货品名称<input id="goodsname" name="goodsname" type="text" class="easyui-textbox" style="width:100px"   />
			拼音<input id="spell" name="spell" type="text" class="easyui-textbox" style="width:100px"   />
		</form>
	</div>
	
	<div id="dlg_selectOrgid" class="easyui-dialog"title="选择一个机构" style="width:200px;height:200px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,closable:false">   
	    <table id="dg_org_select"></table>
	</div>
</body>
</html>