<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>汇融易实地调查系统</title>

	<meta name="description" content="and Validation" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
	
	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" />
	<link rel="stylesheet" href="assets/css/jquery-ui.min.css" />
	<link rel="stylesheet" href="assets/css/chosen.css" />
	<link rel="stylesheet" href="assets/css/datepicker.css" />
	<link rel="stylesheet" href="assets/css/bootstrap-timepicker.css" />
	<link rel="stylesheet" href="assets/css/daterangepicker.css" />
	<link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" href="assets/css/colorpicker.css" />
	<link rel="stylesheet" href="assets/css/select2.css" />
	<link rel="stylesheet" href="assets/css/ui.jqgrid.css" />
	<link rel="stylesheet" href="assets/css/jquery.gritter.css" />
	<!-- text fonts -->
	<link rel="stylesheet" href="assets/css/ace-fonts.css" />

	<!-- ace styles -->
	<link rel="stylesheet" href="assets/css/ace.min.css" id="main-ace-style" />

	<!--[if lte IE 9]>
	<link rel="stylesheet" href="assets/css/ace-part2.min.css" />
	<![endif]-->
	<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
	<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

	<!--[if lte IE 9]>
	<link rel="stylesheet" href="assets/css/ace-ie.min.css" />
	<![endif]-->
	<!-- inline styles related to this page -->
	<%--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300">--%>
	<!-- ace settings handler -->
	<script src="assets/js/ace-extra.min.js"></script>

	<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

	<!--[if lte IE 8]>
	<script src="assets/js/html5shiv.min.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>
