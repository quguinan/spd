<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户权限维护</title>
    	<!-- 
    	
    	
    		由于tree控制比较方便灵活，此页面使用easyui框架
    		
    		
    	 -->
	<jsp:include page="../../../inc/injs.jsp"/>
	<script type="text/javascript">

	$(function(){
		 //dlg初始化
      	  $('#dlg').dialog({    
		    width: 400,    
		    height: 200,    
		    closed: true,    
		    cache: false,
		    buttons:'#dlg-buttons',    
		    modal: true   ,
		    inline:true,
		    border: 'thin'
		});   
		
		//dg初始化
		$('#tdg').treegrid({
			//url:WEB_ROOT+'/cms/rolemenu/findRoles', 
			url:WEB_ROOT+'/cms/rolemenu/treegridData',   
		    columns:[[     
				{field:'rolename',title:'权限名称',align:'left'}  ,
		        {field:'roleid',title:'权限ID',align:'left'},  
		        {field:'rolepid',title:'权限PID',align:'left'},  
		        {field:'orgid',title:'orgid',align:'left',hidden:true},  
		        /* {field:'orgname',title:'所属机构',align:'left'},    */
		    ]],
		    idField : "roleid",
			treeField : "rolename",
		    singleSelect:true,
		    striped:true,
		    fit:true,
		    border:false,
		    /* toolbar:'#toolbar', */
		    toolbar: "#toolbar",
			onClickRow : function(row){
							refreshTree(row)
						},
		});
		//dg初始化
		$('#dg_user').datagrid({
			url: '',   
		    columns:[[     
					{ field : 'userid', title : '用户编码',width:120, align : 'center' }, 
					{ field : 'realname', title : '真实名',width:120, align : 'center', editor : "textbox"}, 
					{ field : 'username', title : '登录名', width:120,align : 'center', editor : "textbox"}, 
		    ]],
		    singleSelect:true,
		    striped:true,
		    fit:true,
		    border:false
		});
		//上级组件
      	$('#rolepid').combotree({
			url : WEB_ROOT+'/cms/rolemenu/treegridDataPID',
			//parentField : 'rolepid',
			valueField: 'roleid',
            textField: 'rolename',
            editable: false,
			lines : true,
			panelHeight : 'auto',
			onLoadSuccess: function () {
				//$("#rolepid").combotree('tree').tree("collapseAll");//默认加载完成后 全部折叠
		    }
		});
		//加载按钮()
      	getButtons("${menuid}",$('#toolbar'),'toolbar');
      	getButtons("${menuid}",$('#dlg-buttons'),'dlg-buttons');
	});
	//刷新全部
	function refreshRoleMenu(){
		alert('查询')
	}
	//更新权限tree
	function updateRoleMenu(){
		//var nodes = $('#dt').tree('getChecked');
		var nodes = $('#dt').tree('getChecked', ['checked', 'indeterminate']);
		var menuids = '';
		var row = $('#tdg').treegrid('getSelected');
		if (row){
			//alert(nodes.length);
			for(var i=0; i<nodes.length; i++){
		 		if (menuids != '') menuids += ',';
		 		//alert(JSON.stringify(nodes[i]));
				menuids += nodes[i].menuid;
			}
			 $.post(WEB_ROOT+'/cms/rolemenu/updateRoleMenu',{roleid:row.roleid,menuids:menuids},function(result){
				if (result.success){
					$.messager.show({title: '提示',msg: '更新成功!',showType:'show'});
				} else {
					$.messager.show({title: '提示',msg: result.errorMsg,showType:'show'});
				} 
			},'json'); 
		}

	}
	//选择dg,刷新tree
	 function refreshTree(row){
		/* alert(row.roleid); */
		$('#dt').tree({
	  		url : WEB_ROOT+'/cms/rolemenu/getRoleMenu?roleid='+row.roleid,
	  		checkbox : true,
	  		//loadmsg: "正在加载...",
	  		onlyLeafCheck : false,  //是否只选叶子节点
	  		lines : true,
	  		loadFilter: function(data){     
	            return data;        
	        }      ,
	       // data : WEB_ROOT+'/js/jquery-easyui-1.5.1/tree_data2.json'
		    cascadeCheck: false,//取消勾选属性//选中子节点时,父节点是否被选中.
		    onLoadSuccess :function (){
		    	$('#dt').tree("collapseAll");//折叠所有节点
				//由根开始遍历一级一级的展开，展开到二级
			  	var roots=$('#dt').tree('getRoots'); 
			  	for(i=0;i<roots.length;i++){
			  		$('#dt').tree('expand', roots[i].target);//'target'参数表示节点的DOM对象
			  	}
		    }
	    });
		$('#dg_user').datagrid('reload',WEB_ROOT + '/cms/user/gridData?roleid='+row.roleid);
	} 
	//新增
	function newRole(){
		$('#dlg').dialog('open').dialog('setTitle','新增权限');
		$('#rolepid').combotree("reload");
		$('#dg_user').datagrid('loadData',{ "total": 0, "rows": [] });
		$('#fm').form('clear');
	};
	//编辑
	function editRole(){
		var row = $('#tdg').treegrid('getSelected');
		//alert(JSON.stringify(row));
		if (row){
			 $('#dlg').dialog('open').dialog('setTitle','编辑权限');
			 $('#rolepid').combotree("reload");
			 $('#dg_user').datagrid('reload',WEB_ROOT + '/cms/user/gridDataBy?roleid='+row.roleid);
			 $('#fm').form('load',row);
			 
		}
	};
	//新增和编辑的保存
	function saveRole(){
		$('#fm').form('submit',{
			 url: WEB_ROOT+'/cms/rolemenu/saveRole',
			 onSubmit: function(){
			 	//表单验证信息
			 	//return $(this).form('validate');
			 },
			 success: function(result){
			 	 var result = eval('('+result+')');
				 if (result.success){
				 	 $('#dlg').dialog('close'); // close the dialog
					 $('#tdg').treegrid('reload'); // reload the user data
					 $.messager.show({title: 'Success',msg: result.msg,showType:'show'});
				 } else {
					 $.messager.show({title: 'Error',msg: result.msg,showType:'show'});
				 }
			 }
		 });
	};
	//删除
	function deleteRole(){
		var row = $('#tdg').treegrid('getSelected');
			if (row){
				$.messager.confirm('Confirm','是删除这个权限?',function(r){
					if (r){
						 $.post(WEB_ROOT+'/cms/rolemenu/delete',{roleid:row.roleid},function(result){
							if (result.success){
								$('#tdg').treegrid('reload');	// reload the user data
								$.messager.show({title: '提示',msg: result.msg,showType:'show'});
							} else {
								$.messager.show({title: '提示',msg: result.msg,showType:'show'});
							} 
						},'json'); 
					}
				});
			}
	};
	//取消
	function cancel(){
		javascript:$('#dlg').dialog('close');
	}

  </script>
  </head>
  
  <body >
	<div id="lo" class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',title:'权限列表',collapsible:false,split:true" style="width:30%" >
			<table id="tdg"></table>
		</div>  
		<!-- tree -->
		<div data-options="region:'center',title:'菜单选择'"  >
			<form:form modelAttribute="formRoleMenu" id="fmTree" method="post">
				<div id="dt"> </div>
			</form:form>  
		</div>  
		<div data-options="region:'east',title:'对应权限的人员列表'" style="width:45%" >
			<table id="dg_user"></table>
		</div>
	</div>
	
	<div id="toolbar"></div>
	
	<!-- 表单对话框 -->
 	<div id="dlg"> 
		<form:form modelAttribute="form" id="fm" method="post">
			<form:hidden path="roleid"/> 
			<table cellpadding="5">
				<tr>
					<td>上级菜单CODE:</td>
					<td>
						<form:input id="rolepid" path="rolepid" type="text" class="easyui-textbox" required="true" data-options="required:false" style="width:130 ! important;"/>
						<a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#rolepid').combotree('clear');" >清空</a>
					</td>
					
				</tr>
				<tr>
					<td>权限名称:</td>
					<td><form:input path="rolename" type="text" class="easyui-textbox" required="true" data-options="required:true"/></td>
				</tr>
				<%-- <tr>
					<td>所属机构:</td>
					<td><form:input path="orgid" type="text" class="easyui-textbox" required="true" data-options="required:true"/></td>
				</tr> --%>
			</table>
		</form:form> 
	</div>
	<div id="dlg-buttons">
		<!-- <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRole()">保存</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()">取消</a> -->
	</div> 
	
  </body>
</html>
