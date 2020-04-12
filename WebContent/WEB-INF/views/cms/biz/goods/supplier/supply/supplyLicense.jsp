<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>证照维护</title>
<jsp:include page="../../../../../inc/injs.jsp" />
<script type="text/javascript">
	$(function() {
		$("#dg").datagrid({
			url:"",   
		    columns:[[
						{field:'_icon', title:'',align:'center',width:40,
							formatter:function(value,row,index){
								if(row.iconflag==1){
									return '<a href="javascript:void(0);" class="icon_tick" onclick="" />';
								}else if(row.iconflag==0){
									return '<a href="javascript:void(0);" class="icon_warn" onclick="" />';
								}else if(row.iconflag==-1){
									return '<a href="javascript:void(0);" class="icon_err" onclick="" />';
								}
							} 
						},
						{field:'licensetypename', title:'证照类型名称',align:'center' }, 
						{field:'dtlid', title:'dtlid',align:'center' ,hidden:true},
						{field:'docid', title:'docid',align:'center' ,hidden:true},
						{field:'licensetypeid', title:'管控编号',align:'center' }, 
						{field:'customflag', title:'客户标志',align:'center' }, 
						{field:'goodsflag', title:'货品标志',align:'center'  }, 
						{field:'supplierflag', title:'供应商标志',align:'center'  }, 
						{field:'earlywarndays', title:'预警天数',align:'center' }, 
						{field:'superdays', title:'可超期天数',align:'center' }, 
						{field:'orgid', title:'orgid',align:'center',hidden:true }, 
						{field:'memo', title:'备注',align:'center' }, 
						{field:'supplyid', title:'supplyid',align:'center',hidden:true }, 
						{field:'licenseid', title:'licenseid',align:'center' ,hidden:true}, 
						{field:'iconflag', title:'iconflag',align:'center' ,hidden:true}, 
		              ]],
		    singleSelect:true,
		    striped:true,
		    //rownumbers:true,
		    fit:true,
		    border:false,
		    toolbar: "#toolbar",
		    title:"${suUser.supplyname}",
		    onClickRow : function(index, row){
		    	
		    },
		    onDblClickRow : function(index, row){
		    	editLicense();
		    },
		    onLoadSuccess: function (data) {
	    		$('.icon_tick').linkbutton({ text: '', plain: true, iconCls: 'icon_tick' });
	    		$('.icon_err').linkbutton({ text: '', plain: true ,iconCls: 'icon_delete'});
	    		$('.icon_warn').linkbutton({ text: '', plain: true ,iconCls: 'icon_error'});
		        /* icon_tick   icon_delete  icon_error icon_stop*/
		    },
		});
		//dlg初始化
    	  $('#dlg').dialog({    
		    width: 700,    
		    height: 550,    
		    closed: true,    
		    closable: false,
		    cache: false,
		    buttons:'#dlg-buttons',    
		    modal: true   ,
		    inline:true,
		    border: 'thin'
		}); 
		/*查询*/
		query();
		
		/* 图片文件选择完即显示 */
		$('#file_upload').live('change',function(){ 
			var $file = $(this); 
			var fileObj = $file[0];
			var windowURL = window.URL || window.webkitURL;
			var dataURL;
			var $img = $("#preview");
			if(fileObj && fileObj.files && fileObj.files[0]){
				dataURL = windowURL.createObjectURL(fileObj.files[0]);
				$("#preview").attr('src',dataURL);
				//$("#preview").attr('alt',dataURL);
			}else{
				dataURL = $file.val();
				var imgObj = document.getElementById("preview");
				imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
			} 
		});
		
	});
	
	function query(){
		$.post(WEB_ROOT+'/cms/common/checkSuUserOrgSupplyLicense',{},
				function(result){
					if (result.success){
						$("#dg").datagrid("reload",WEB_ROOT+'/cms/biz/supply/gspCategory/gridDataDtlBySupplyid');
					}else{
						$.messager.show({title: '提示',msg: result.msg,showType:'show'});
					}
				},'json');
	};
	
	function editLicense(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			$.post(WEB_ROOT+'/cms/biz/supply/supplyLicense/gridDataBySupplyidLicensetypeid', 
				  {orgid:row.orgid,supplyid:row.supplyid,licensetypeid:row.licensetypeid}, 
					function(result) {
					  $('#fm').form('load',result);
					  //alert(WEB_ROOT+$('#picurl').val())
					  $("#preview").attr('src',WEB_ROOT+$('#viewurl').val()); //设置预览小图
					  $("#preview_a").attr('href',WEB_ROOT+$('#picurl').val()); //设置预览连接打开
					  $('#dlg').dialog('open').dialog('setTitle','维护证照');
			}, "JSON");
		}
	};
	
	function save(){
		$('#fm').form('submit',{
			 url: WEB_ROOT+'/cms/biz/supply/supplyLicense/save',
			 onSubmit: function(){
			 	//表单验证信息
			 	return $(this).form('validate');
			 },
			 success: function(result){
			 	 var result = eval('('+result+')');
				 if (result.success){
					 $.messager.show({title: 'success',msg: result.msg,showType:'show'});
					 cancel();//关闭窗口
				 } else {
					 $.messager.show({title: 'success',msg: result.msg,showType:'show'});
				 }
			 }
		 });
	};

	function cancel(){
		javascript:$('#dlg').dialog('close');
		$('#fm').form('clear');
		$("#dg").datagrid("reload");
	};
</script>
</head>
<body >
	<table id="dg"></table>
	
	<!-- 表格工具栏 -->
	<div id="toolbar">
		<table cellpadding="0" cellspacing="0">	
			<tr>	
				<td><div class="btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-plus" plain="true" onclick='editLicense()'>维护证照</a></td>
				<td><div class="btn-separator"></div></td>
			</tr> 
		</table>
	</div>
	
	
	<!-- 编辑对话框 -->
	<div id="dlg">
		<table cellpadding="0" cellspacing="0">	
			<tr>	
				<td><div class='btn-separator'></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-save" plain="true"onclick="save()">保存</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="fa-remove" plain="true"onclick="cancel()">取消</a></td>
				<td><div class='btn-separator'></div></td>
			</tr> 
		</table>
		<form:form	action="saveGridDataPic" modelAttribute="suSupplyLicenseForm"	
					id="fm" method="post" enctype="multipart/form-data" style="padding:20px">
			<form:input id="licenseid" path="licenseid" hidden="true" /> <!--  hidden="true" -->
			
			<form:input id="supplyid" path="supplyid" hidden="true"  />
			<form:input id="licensetypeid" path="licensetypeid" hidden="true"  />
			
			<form:input id="status" path="status"  hidden="true" /><!-- 证照状态 0可用  -1 停用 -->
			<form:input id="userid" path="userid"  hidden="true" />
			<form:input id="credate" path="credate" hidden="true"  />
			<form:input id="picurl" path="picurl" hidden="true"  />
			<form:input id="viewurl" path="viewurl" hidden="true"  />
			<table >
				<tr>
					<td>*证照名称:</td><td><form:input id="licensename" path="licensename" class="easyui-textbox" data-options="required:true"/></td>
					<td>*证照编码:</td><td><form:input id="licensecode" path="licensecode" class="easyui-textbox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td>相关企业:</td><td><form:input id="relatecompany" path="relatecompany" class="easyui-textbox"/></td>
					<td>相关货品:</td><td><form:input id="relategoods" path="relategoods" class="easyui-textbox"/></td>
				</tr>
				<tr>
					<td>签发单位:</td><td><form:input id="signdeptment" path="signdeptment" class="easyui-textbox"/></td>
					<td>签发日期:</td><td><form:input id="signdate" path="signdate" class="easyui-datebox" data-options="editable:false"/></td>
				</tr>
				<tr>
					<td>期限开始日期:</td><td><form:input id="startdate" path="startdate" class="easyui-datebox" data-options="editable:false"/></td>
					<td>期限结束日期:</td><td><form:input id="enddate" path="enddate" class="easyui-datebox" data-options="editable:false"/></td>
				</tr>
				<tr>
					<td>经营范围:</td><td><form:input id="busiscopeids" path="busiscopeids" class="easyui-textbox"/></td>
					<td></td><td></td>
				</tr>
				<tr>
					<td>主要内容:</td>
					<td colspan="3">
						<form:input id="maincontent" path="maincontent" class="easyui-textbox" style="width:100%;height:100px;" data-options="multiline:true" />
					</td>
				</tr>
				
				<tr>
					<td>上传图片:</td>
					<td colspan="3">
						<input id="file_upload" name="file_upload" type="file" /> 
					</td>
				</tr>
				<tr>
					<td>图片预览:</td>
					<td colspan="3">
						<a id="preview_a" target="_blank" href="javascript:void(0);"><img id="preview" height="150" alt="点击预览"/></a> 
						<!-- <a id="preview_a" target="_blank" href="javascript:void(0);">点击预览</a> -->
					</td>
				</tr>
			</table>
		</form:form>
	</div>
	
	
</body>
</html>