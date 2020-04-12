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
    <title>科室维护</title>
	<jsp:include page="../../../inc/injs.jsp"/> 
	<script type="text/javascript"> 
		var recordNodes = new Array();//定义一个数组容器记录已被展开的节点ID
		$(function(){
            /* grid初始化 */
			$('#grid').treegrid({
						url : WEB_ROOT+'/cms/dept/treegridData',
						//title : '系统菜单设置',
						idField : "deptid",
						treeField : "deptname",
						/* striped: true, */
	                    animate: false,//关闭动画效果
	                    nowrap: true,
						method : 'post',
						lines : true,
						singleSelect : true,
					    border:false,
					    fit:true,  
					    loadMsg: "加载中......",
					    //rownumbers:true,
						columns : [[
									{title : '科室名称', field : 'deptname', width : 180, align : 'left', editor : "textbox"},
									{title : '科室ID', field : 'deptid', width : 100, align : 'left', editor : "textbox"},
									{title : '科室父ID', field : 'deptpid', width : 80, align : 'left', editor : "textbox"},
									{title : '拼音', field : 'spell', width : 160, align : 'left', editor : "textbox"},
									]],
						toolbar : "#toolbar",
					    onDblClickRow : function(index, row){
					    	edit();
					    }, 
					    onBeforeExpand: function (node) {/*  */
			                recordNodes.push(node.id.toString());
					    },
					    onBeforeCollapse: function (node) {
			                var i = recordNodes.indexOf(node.id.toString());
			                if (i >= 0) { recordNodes.splice(i, 1); }
			            },
					    onLoadSuccess: function () {
			                var list = [];
			                for (var j = 0; j < recordNodes.length; j++) {
			                    list.push(recordNodes[j])
			                }
			                $('#grid').treegrid("collapseAll");
			                for (var i = 0; i < list.length; i++) {
			                	$('#grid').treegrid('expand', list[i]);
			                }
			                
			               /*  $('#grid').treegrid("collapseAll");//折叠所有节点
			    			//由根开始遍历一级一级的展开，展开到二级
			    		  	var roots=$('#grid').treegrid('getRoots'); 
			    		  	for(i=0;i<roots.length;i++){
			    		  		//alert(JSON.stringify(roots[i].id))
			    		  		$('#grid').treegrid('expand', roots[i].id);
			    		  	}    */
					    }
					});
			  
			
		});
		
		
		//查询
		function query(){
			$('#grid').treegrid('reload');
		}
	</script>
  </head>
   
  <body>
	<table id="grid"></table>
	
	<div id="toolbar">
		<table cellpadding='1' cellspacing='1'>
			<tr>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_magnifier' plain='true' onclick='query()'>查询</a></td>
				<td><div class='btn-separator'></div></td>
			</tr>
		</table>
	</div>
	
  </body>
</html>
