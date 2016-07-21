<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<!-- /section:settings.box -->
	<div class="page-content-area">
		<div class="page-header">
			<h1>
				用户编辑 <small> <i class="ace-icon fa fa-angle-double-right"></i> 查看和编辑用户信息 </small>
			</h1>
		</div><!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="well well-sm">
					<button type="button" class="close line-height-0" data-dismiss="alert">
						<i class="ace-icon fa fa-times smaller-75"></i>
					</button>
					编号用于用户登录,不能重复   	
					<span class="label label-warning">
					<i class="ace-icon fa fa-exclamation-triangle bigger-100"></i> 提示 </span>
				</div>
				<div class="row">
					<div class="col-xs-12"> 
						<table id="grid-table"></table>
	
						<div id="grid-pager"></div>
					</div>
				</div>
				<!-- PAGE CONTENT ENDS -->
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div><!-- /.page-content-area -->

<%--调用公共JS上--%>
<jsp:include page="../../js/base/common_top.jsp" />
<%--本页面JS--%>
<script src="jsp/js/user/user_info_main.js"></script>

<%--调用公共JS下--%>
<jsp:include page="../../js/base/common_down.jsp" />