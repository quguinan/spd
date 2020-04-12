<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
<META HTTP-EQUIV="Expires" CONTENT="0" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<script>
	var WEB_ROOT = "${pageContext.request.contextPath}";
	var LANG = "${pageContext.request.locale}";
</script>


<!--JQuey库 (必须)-->
<script src="https://code.jquery.com/jquery-1.8.3.min.js"></script>

<!-- easyui -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.7.0/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.7.0/datagrid-filter.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.7.0/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.7.0/themes/color.css">
<%-- <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.7.0/jquery.edatagrid.js"> --%>
<!-- easyui主题 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.7.0/themes/bootstrap/easyui.css">

<!-- 引用insdep主题 -->
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/InsdepUI-for-EasyUI-1.1.0/jquery.easyui.min.js">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/InsdepUI-for-EasyUI-1.1.0/insdep.extend.min.js"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/InsdepUI-for-EasyUI-1.1.0/icon.css"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/InsdepUI-for-EasyUI-1.1.0/insdep.easyui.min.css"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/InsdepUI-for-EasyUI-1.1.0/insdep.theme_default.css">
 --%>

<!-- easyui扩展 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.7.0/jquery-easyui-datagridview/datagrid-detailview.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.7.0/jquery-easyui-datagridview/datagrid-groupview.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.7.0/easyui-datagrid-moverow.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.7.0/btn-separator.css" />


<!-- 其他  -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.serialize-object.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/crypto/core-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/crypto/enc-base64-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/my.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/pinyin.js" charset="GBK"></script>

<!-- font-awesome -->
<link
	href="${pageContext.request.contextPath}/js/font-awesome-4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<!-- fa重写easyui的图标 -->
<link href="${pageContext.request.contextPath}/css/icon_fa_overrideEasyui.css" rel="stylesheet">

<!-- 为了实现下拉图标选择,引入fontIconPicker -->
<script
	src="${pageContext.request.contextPath}/js/fontIconPicker-2.0.0/jquery.fonticonpicker.js"></script>
<%-- <link href="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet" />  --%>
<link
	href="${pageContext.request.contextPath}/js/fontIconPicker-2.0.0/css/jquery.fonticonpicker.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/js/fontIconPicker-2.0.0/themes/grey-theme/jquery.fonticonpicker.grey.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/js/fontIconPicker-2.0.0/themes/dark-grey-theme/jquery.fonticonpicker.darkgrey.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/js/fontIconPicker-2.0.0/themes/bootstrap-theme/jquery.fonticonpicker.bootstrap.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/js/fontIconPicker-2.0.0/themes/inverted-theme/jquery.fonticonpicker.inverted.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/js/fontIconPicker-2.0.0/demo/fontello-7275ca86/css/fontello.css"
	rel="stylesheet" />

<!-- 二维码 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.qrcode.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/qrcode.min.js"></script>

<!-- 条形码 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/JsBarcode.all.min.js"></script>


<!-- 二维码字符串压缩 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/pako/pako.js"></script>

<!-- 打印控件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>

<!-- 按钮控制 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/buttons.js"></script>
<!-- 过滤 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.7.0/datagrid-filter.js"></script>

<!-- 公共资源 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/pub.js"></script>

<!-- 导出excle -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/datagrid-export.js"></script>
<!-- JS读取EXCEL -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/xlsx.full.min.js"></script>
<!-- 自定义页面滚动条 -->
<%-- 
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/malihu-custom-scrollbar-plugin-3.1.5/jquery.mCustomScrollbar.css" />
<script src="${pageContext.request.contextPath}/js/malihu-custom-scrollbar-plugin-3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${pageContext.request.contextPath}/js/malihu-custom-scrollbar-plugin-3.1.5/jquery.mCustomScrollbar.js"></script>
 --%>


