<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<style>
/* html {
  background: url(${pageContext.request.contextPath}/img/login.jpg) no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
} */
/* html {
	background-color: #007465;
}
 */
input[type=submit], input[type=button] {
	display: inline-block;
	vertical-align: middle;
	padding: 12px 24px;
	margin: 0px;
	font-size: 19px;
	line-height: 24px;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	cursor: pointer;
	color: #ffffff;
	background-color: #27A9E3;
	border-radius: 3px;
	border: none;
	-webkit-appearance: none;
	outline: none;
	width: 100%;
}

body {
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='test.jpg',
		sizingMethod='scale');
	background-repeat: no-repeat;
	background-positon: 100%, 100%;
}
</style>

<HEAD>
<TITLE>登录</TITLE>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<jsp:include page="../inc/injs.jsp" />
<script type="text/javascript">
	$(function() {
		$('#w_login').window({
			width : 400,
			height : 300,
			modal : true,
			title : "供应商系统平台登录 ",
			closed : false,
			collapsible : false,
			iconCls : 'icon-ok',
			closable : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			border : false,
			draggable : false,
			width : '500px',
			height : '400px'
		});
		$('#username').textbox({
			prompt : '用户名',
			iconCls : 'icon-man',
			iconWidth : 38
		});
		/* $('#username').textbox('textbox').keydown(function (e) {
		    if (e.keyCode == 13) {
		    	if($('#username').textbox('getText')==''){return;}
		    	$('#password').next('span').find('input').focus();
		    }
		}); */
		$('#password').passwordbox({
			prompt : '密码',
			iconWidth : 38,
			showEye : true
		});
		$('#password').textbox('textbox').keydown(function(e) {
			if (e.keyCode == 13) {
				dologin();
			}
		});
	});

	function dologin() {
		$("#login").form('submit');
	};
</script>
</HEAD>
<BODY
	style="background-image: url('${pageContext.request.contextPath}/img/timg.jpg'); background-size: 100%;">
	<div id="w_login"
		style="padding: 20px 70px 20px 70px;background-image: ${pageContext.request.contextPath}/img/4858.jpg">
		<div style="margin-bottom: 30px;">
			<h1 style="text-align: center;">供应链管理一体化系统(SPD)</h1>
		</div>
		<form id="login" method="post"
			action="${pageContext.request.contextPath}/dologin">
			<div style="margin-bottom: 30px;">
				<input id="username" name="username"
					style="width: 100%; height: 50px; padding: 12px; font-size: 15px" />
			</div>
			<div style="margin-bottom: 20px">
				<input type="password" id="password" name="password"
					style="width: 100%; height: 50px; padding: 12px" />
			</div>
			<div style="margin-bottom: 20px">
				<!-- <a id="btn_dologin" onclick=dologin() style="padding:5px 0px;width:100%;" >登录</a> -->
				<!-- 	<INPUT id=btn_dologin onclick=this.blur() value=登录 type=submit> -->
				<input id=btn_dologin value="登 录"
					style="width: 100%; height: 40px; background-color: #007465"
					type="submit">
			</div>
			<div id="msg"
				style="color: red; font-size: 14px; text-align: center;">${msg }</div>
		</form>
	</div>
</BODY>
</HTML>
