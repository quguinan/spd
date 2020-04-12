<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="../../../inc/injs.jsp"/> 
	<title>自定义报表设置</title>

	<script type="text/javascript">
		$(function(){
			$("#dg_doc").datagrid({
				url:WEB_ROOT+'/cms/biz/rpt/gridDataDoc',   
			    columns:[[
							{field:'docid', title:'报表编号', align:'center' },
							{field:'rptname', title:'报表名称', align:'left' ,halign:'center',width:200},
							{field:'sqltext', title:'SQL语句', align:'left' ,halign:'center',width:500},
							{field:'_operate',title:'操作',align:'center',width:160,
						    	formatter:function (val,row,index) {
						    		return '<a href="javascript:void(0);" target="_blank" onClick="rptTest('+index+')">预览</a>&nbsp;&nbsp;&nbsp;'+
						    			   '<a href="javascript:void(0);" target="_blank" onClick="editRpt('+index+')">编辑</a>&nbsp;&nbsp;&nbsp;'+
						    			   '<a href="javascript:void(0);" target="_blank" onClick="deleteRpt('+index+')">删除</a>&nbsp;&nbsp;&nbsp;';
						    		//return '<a href="'+WEB_ROOT+'/cms/biz/rpt/gridDataProperties'+'" target="_blank">预览</a>';
						    	}
						    }, 
			              ]],
			    singleSelect:true,
			    striped:true,
			    fit:true,
			    border:false,
			    rownumbers:true,
			    toolbar: [
							{
								text:'新增',
								iconCls: 'icon-add',
								handler: function(){
									$('#dlg').dialog('open').dialog('setTitle','新增报表');
									$('#fmDoc').form('clear');
									$('#dg_column').datagrid('loadData',{total:0,rows:[]});
									$('#dg_filter').datagrid('loadData',{total:0,rows:[]});
									$('#dg_properties').datagrid('load',WEB_ROOT+'/cms/biz/rpt/gridDataProperties');
									$('#dg_events').datagrid('load',WEB_ROOT+'/cms/biz/rpt/gridDataEvents');
								}
							}
							],
			});
			
			$("#dg_column").datagrid({
				url:'',   
			    columns:[[
							{field:'docid', title:'docid',align:'center' ,hidden:true},
							{field:'dtlid',title:'dtlid',align:'center',hidden:true },
							{field:'field', title:'字段名',align:'left',halign:'center' ,width:100},
							{field:'title', title:'标题名',align:'left',halign:'center'  ,width:100,editor : "textbox"},
							{field:'width', title:'列宽',align:'left' ,halign:'center' ,width:100,
								editor:
									{
										type:'numberbox',
										options:{
											precision:0
										}
									}
							},
							{field:'align', title:'水平对齐',align:'left'  ,halign:'center',width:100,
								editor:
									{
										type:'combobox',
										options:{
											valueField:'value',
											textField:'text',
											panelHeight:'auto',
											editable:false,
											data: [ {value: 'left',text: 'left'}, {value: 'center',text: 'center'}, {value: 'right',text: 'right'} ]
											}
									}
							},
							{field:'halign', title:'标题对齐',align:'left'  ,halign:'center',width:100,
								editor:
								{
									type:'combobox',
									options:{
										valueField:'value',
										textField:'text',
										panelHeight:'auto',
										editable:false,
										data: [ {value: 'left',text: 'left'}, {value: 'center',text: 'center'}, {value: 'right',text: 'right'} ]
										}
								}},
							{field:'sortable', title:'列排序',align:'left'  ,halign:'center',width:100,
								editor:
									{
										type:'combobox',
										options:{
											valueField:'value',
											textField:'text',
											panelHeight:'auto',
											editable:false,
											data: [ {value: '',text: ' '},{value: 'true',text: 'true'}, {value: 'false',text: 'false'} ]
											}
									}},
							{field:'order', title:'排序数序',align:'left'  ,halign:'center',width:100,
										editor:
										{
											type:'combobox',
											options:{
												valueField:'value',
												textField:'text',
												panelHeight:'auto',
												editable:false,
												data: [ {value: '',text: ' '},{value: 'asc',text: 'asc'}, {value: 'desc',text: 'desc'} ]
												}
										}},
							{field:'hidden', title:'列隐藏',align:'left' ,halign:'center' ,width:100,
											editor:
											{
												type:'combobox',
												options:{
													valueField:'value',
													textField:'text',
													panelHeight:'auto',
													editable:false,
													data: [ {value: '',text: ' '},{value: 'true',text: 'true'}, {value: 'false',text: 'false'} ]
													}
											}},
			              ]],
			    singleSelect:true,
			    striped:true,
			    fit:true,
			    border:false,
			    toolbar: [
				{
					text:'新增字段',
					iconCls: 'icon_add',
					handler: function(){
						alert('新增')
					}
				},{
			    	text:'删除字段',
					iconCls: 'icon-remove',
					handler: function(){
						alert('删除')
					}
				}],
				onClickRow : function(index, row) {
					endEditColumn($(this));
					beginEditColumn($(this));
				},
				onDblClickRow : function(index, row) {
					endEditColumn($(this));
					beginEditColumn($(this));
				}
			});
			
			$("#dg_properties").datagrid({
				url:'',   
			    columns:[[
							{field:'dtlid', title:'docid',hidden:true },
							{field:'docid', title:'docid',hidden:true},
							{field:'proname', title:'属性名', align:'left' ,halign:'center',width:120},
							{field:'provalue', title:'属性值', align:'left' ,halign:'center',width:150,editor : "textbox"},
			              ]],
			    singleSelect:true,
			    striped:true,
			    fit:true,
			    border:false,
			    rownumbers:false,
				onClickRow : function(index, row) {
					endEditColumn($(this));
					beginEditColumn($(this));
				},
				onDblClickRow : function(index, row) {
					endEditColumn($(this));
					beginEditColumn($(this));
				}
			});
			
			$("#dg_events").datagrid({
				url:'',   
			    columns:[[
							{field:'dtlid', title:'dtlid',hidden:true },
							{field:'docid', title:'docid',hidden:true},
							{field:'eventname', title:'事件', align:'left' ,halign:'center',width:100},
							{field:'eventparam', title:'参数', align:'left' ,halign:'center',width:100,editor : "textbox"},
							{field:'eventvalue', title:'内容', align:'left' ,halign:'center',width:100,editor : "textbox"},
			              ]],
			    singleSelect:true,
			    striped:true,
			    fit:true,
			    border:false,
			    rownumbers:false,
				onClickRow : function(index, row) {
					endEditColumn($(this));
					beginEditColumn($(this));
				},
				onDblClickRow : function(index, row) {
					endEditColumn($(this));
					beginEditColumn($(this));
				}
			});
			        
			$("#dg_filter").datagrid({
				url:'',   
			    columns:[[
							{field:'docid', title:'docid',align:'center' ,hidden:true},
							{field:'dtlid',title:'dtlid',align:'center',hidden:true },
			              ]],
			    singleSelect:true,
			    striped:true,
			    fit:true,
			    border:false,
			    toolbar: [
				{
					text:'新增条件',
					iconCls: 'icon_add',
					handler: function(){
						alert('新增')
					}
				},{
			    	text:'删除条件',
					iconCls: 'icon-remove',
					handler: function(){
						alert('删除')
					}
				}]
			});
			//dlg初始化
	      	  $('#dlg').dialog({    
			    /* width:400,
			    height:500, */
			    fit:true,
			    closed: true,    
			    cache: false,
			    modal: true   ,
			    inline:true,
			    border: 'thin',
			    toolbar: [
							{
						    	text:'保存报表',
								iconCls: 'icon-save',
								handler: function(){
									save();
								}
							},{
						    	text:'取消',
								iconCls: 'icon_cross',
								handler: function(){
									$('#dg_doc').datagrid('load',WEB_ROOT+'/cms/biz/rpt/gridDataDoc');
									$('#dlg').dialog('close'); // close the dialog
								}
							}],
			});  
		}); 
		
		//报表测试
		function rptTest(index){
			//alert(index);
			var rows = $('#dg_doc').datagrid('getRows');
			var row = rows[index];    
			var text=row.rptname;
			var jq = top.jQuery;//top.jQuery代替$  即可找到父控件对应的方法
			if (jq('#tt').tabs('exists', text)){    
				jq('#tt').tabs('select', text);    
		    } else {    
		        var content = '<iframe scrolling="auto" frameborder="0"  src="'+WEB_ROOT+'/cms/biz/rpt/rptDisplay?docid='+row.docid+'" style="width:100%;height:100%;"></iframe>';    
		        jq('#tt').tabs('add',{    
		            title: text,    
		            iconCls : '',
		            content: content,    
		            closable:true 
		        });    
		    }  
		};
		
		//主界面编辑行
		function editRpt(index){
			var rows = $('#dg_doc').datagrid('getRows');
			var row = rows[index];
			//alert(JSON.stringify(row))
			if (row){
				 $('#dlg').dialog('open').dialog('setTitle','编辑报表');
				 //doc
				 $('#fmDoc').form('load',row);
				 //其他datagrid....未完待续
			}
		};
		//主界面删除行
		function deleteRpt(index){
			alert(index);
		};
		
		//sql语句解析
		function sqlAnalyse(){
			var sql=$("#sqltext").textbox('getValue');
			$.post(WEB_ROOT+'/cms/biz/rpt/sqlAnalyse', { sql:sql }, 
					function(result) {
						if(result.success){ 
							//alert(JSON.stringify(result.data));
							//字段
							$('#dg_column').datagrid('loadData',{total:0,rows:[]});
							for (var i = 0; i < result.data_column.length; i++) {  
							        $('#dg_column').datagrid('appendRow',result.data_column[i]);
							}  
							//条件
							$('#dg_filter').datagrid('loadData',{total:0,rows:[]});
							for (var j = 0; j < result.data_filter.length; j++) {  
							        $('#dg_filter').datagrid('appendRow',result.data_filter[j]);
							}  
						}else{
							$.messager.show({title: '提示',msg: result.msg,showType:'show'});
						}
				}, "JSON");
		};
		
		
		//保存报表
		function save(){
			endEditColumn($("#dg_column"));
			endEditColumn($("#dg_properties"));
			if ($("#dg_column").datagrid('getChanges').length) {
				var rowsColumns =$("#dg_column").datagrid('getRows');
				var rowsProperties = $("#dg_properties").datagrid('getRows');
				
				$.post(WEB_ROOT + '/cms/biz/rpt/save', {
					docid : $('#docid').textbox('getValue'),
					jsonDoc : JSON.stringify($('#fmDoc').serializeArray()),
					jsonColumns : JSON.stringify(rowsColumns),
					jsonProperties : JSON.stringify(rowsProperties)
				}, function(result) {
					if (result.success) {
						$('#dg_column').datagrid('reload');
						//$('#dlg').dialog('close'); // close the dialog
					}
					$.messager.show({
						title : '提示',
						msg : result.msg,
						showType : 'show'
					});
				}, "JSON"); 
			}
		};
		
		function endEditColumn(dg){
			//endEdit();
			var rows = dg.datagrid('getRows');
			for (var i = 0; i < rows.length; i++) {
				dg.datagrid('endEdit', i);
			}
		};
		
		function beginEditColumn(dg){
			//beginEdit();
			var row = dg.datagrid('getSelected');
			if (row) {
				var rowIndex = dg.datagrid('getRowIndex', row);
				dg.datagrid('beginEdit', rowIndex);
			}
		};
	</script>

</head>
<body>
	<table id='dg_doc'></table>
	
	<div id='dlg' class="easyui-layout" data-options="fit:true">
	
		<div data-options="region:'west',collapsible:false,border:false" style="width:300px">
			<div class="easyui-tabs" data-options="fit:true">
				<div title="属性">
					<table id='dg_properties'></table>
				</div>
				<div title="事件">
					<table id='dg_events'></table>
				</div>
			</div>
		</div> 
	
		<div data-options="region:'center',collapsible:false,split:true,border:false" >
			<div class="easyui-layout" data-options="fit:true">   
				<div data-options="region:'north',collapsible:false" style="height:150px" >
					<form id="fmDoc">
						<input class="easyui-textbox" name="docid" id="docid"  /> 
						报表名称:<input class="easyui-textbox" name="rptname" id="rptname" data-options="required:true" /><br>
						SQL语句:
						<br><input class="easyui-textbox" name="sqltext" id="sqltext" data-options="multiline:true,required:true" 
										style="width:700px;height:90px;"/>
						<a id="btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="sqlAnalyse()">SQL解析</a>  
					</form>
				</div>
				<div data-options="region:'center',collapsible:false" style="height:150px">
					<div class="easyui-tabs" data-options="fit:true">
						<div title="报表字段">
							<table id='dg_column'></table>
						</div>
						<div title="查询条件">
							<table id='dg_filter'></table>
						</div>
					</div>
				</div>
			</div>
		</div>
		 
		
	</div>
</body>
</html>