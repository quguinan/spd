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
    <title>test</title>
	<jsp:include page="inc/injs.jsp"/> 
	<script type="text/javascript"> 
	$(function () {
		//jquery icon初始化
        $('#myselect').fontIconPicker({
            theme: 'fip-darkgrey',//四种主题风格：fip-grey, fip-darkgrey, fip-bootstrap, fip-inverted
            source: ["icon-music", "icon-search", "icon-mail", "icon-mail-alt", "icon-heart", "icon-heart-empty", "icon-star", "icon-star-empty", "icon-star-half", "icon-star-half-alt", "icon-user", "icon-users", "icon-male", "icon-female", "icon-video", "icon-videocam", "icon-picture", "icon-camera", "icon-camera-alt", "icon-th-large", "icon-th", "icon-th-list", "icon-ok", "icon-ok-circled", "icon-ok-circled2", "icon-ok-squared", "icon-cancel", "icon-cancel-circled", "icon-cancel-circled2", "icon-plus", "icon-plus-circled", "icon-plus-squared", "icon-plus-squared-small", "icon-minus", "icon-minus-circled", "icon-minus-squared", "icon-minus-squared-alt", "icon-minus-squared-small", "icon-help", "icon-help-circled", "icon-info-circled", "icon-info", "icon-home", "icon-link", "icon-unlink", "icon-link-ext", "icon-link-ext-alt", "icon-attach", "icon-lock", "icon-lock-open", "icon-lock-open-alt", "icon-pin", "icon-eye", "icon-eye-off", "icon-tag", "icon-tags", "icon-bookmark", "icon-bookmark-empty", "icon-flag", "icon-flag-empty", "icon-flag-checkered", "icon-thumbs-up", "icon-thumbs-down", "icon-thumbs-up-alt", "icon-thumbs-down-alt", "icon-download", "icon-upload", "icon-download-cloud", "icon-upload-cloud", "icon-reply", "icon-reply-all", "icon-forward", "icon-quote-left", "icon-quote-right", "icon-code", "icon-export", "icon-export-alt", "icon-pencil", "icon-pencil-squared", "icon-edit", "icon-print", "icon-retweet", "icon-keyboard", "icon-gamepad", "icon-comment", "icon-chat", "icon-chat-empty", "icon-bell", "icon-bell-alt", "ion-android-alert", "ion-android-apps"],
            emptyIcon: true,//是否显示空
            emptyIconValue: "none",//空值
            iconsPerPage: 50, //每页显示图标的个数，默认20
            hasSearch: true,//是否显示试试框，默认true
        }); 
	  });
	</script>
  </head>
   
  <body>
	 <input id="myselect" name="myselect" />
  </body>
</html>
