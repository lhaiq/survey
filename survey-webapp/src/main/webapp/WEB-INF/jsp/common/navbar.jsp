<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!-- #section:basics/navbar.layout 导航布局-->

<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <!-- #section:basics/sidebar.mobile.toggle 导航栏开关-->
        <button type="button" class="navbar-toggle menu-toggler pull-left"
                id="menu-toggler">
            <span class="sr-only"></span>
            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <!-- /section:basics/sidebar.mobile.toggle 设置公司图标和项目名称-->
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="#" class="navbar-brand">
                <small> <!-- i标签为公司图标-->
                    <i class="fa fa-leaf"></i> 数据监控预警平台
                </small>
            </a>

            <!-- /section:basics/navbar.layout.brand -->

            <!-- #section:basics/navbar.toggle -->

            <!-- /section:basics/navbar.toggle -->

        </div>

        <!-- #section:basics/navbar.dropdown 导航条提示信息及下来菜单和用户信息-->

        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">

                <li class="grey">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-tachometer" style="font-size: 18px;"></i>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-bullhorn"></i> 3 监控级别
                        </li>

                        <li>
                            <a href="rest/addMonitor/doSearch?levelType=1">
                                <div class="clearfix">
                                    <span class="pull-left">告警</span>
                                    <span class="pull-right">${topType.warning}%</span>
                                </div>
                                <div class="progress progress-mini">
                                    <div style="width:${topType.warning}%" class="progress-bar"></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="rest/addMonitor/doSearch?levelType=2">
                                <div class="clearfix">
                                    <span class="pull-left">一般严重</span>
                                    <span class="pull-right">${topType.normal}%</span>
                                </div>
                                <div class="progress progress-mini">
                                    <div style="width:${topType.normal}%"
                                         class="progress-bar progress-bar-warning"></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="rest/addMonitor/doSearch?levelType=3">
                                <div class="clearfix">
                                    <span class="pull-left">严重</span>
                                    <span class="pull-right">${topType.error}%</span>
                                </div>
                                <div class="progress progress-mini">
                                    <div style="width:${topType.error}%" class="progress-bar progress-bar-danger"></div>
                                </div>
                            </a>
                        </li>


                        <li class="dropdown-footer">
                            <a href="rest/addMonitor/doSearch">查看所有的详情 <i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="purple">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-bell icon-animated-bell"></i>
						<span class="badge badge-important" id="monitorCount">
							<c:forEach items="${chartsDetailList}" var="list">
                                <c:if test="${list.checkType == 0}">
                                    ${list.count}
                                </c:if>
                            </c:forEach>
						</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-exclamation-triangle"></i>
							<span id="monitorTypeCount"><c:forEach items="${chartsDetailList}" var="list">
                                <c:if test="${list.checkType == 0}">
                                    ${list.count}
                                </c:if>
                            </c:forEach> 条 通知</span>
                        </li>
                        <li id="monitorTypeData">
                            <c:forEach items="${chartsDetailList}" var="list">
                                <c:if test="${null != list.checkType && list.checkType != 0}">
                                    <a href="rest/menu/getMonitorLog?type=${list.checkType}&flag=0">
                                        <div class="clearfix">
													<span class="pull-left">
														  <img style="width: 25px;height: 25px;"
                                                               src="assets/avatars/${list.checkType}.png"/>
														${list.name}
													</span>
                                            <span class="pull-right badge badge-info">+${list.count}</span>
                                        </div>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </li>

                        <li class="dropdown-footer">
                            <a href="rest/menu/getMonitorLog?flag=1"> 查看全部信息 <i class="ace-icon fa fa-arrow-right"></i></a>
                        </li>

                    </ul>
                </li>

                <li class="green">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
						<span class="badge badge-success" id="smsCount">
							<c:forEach items="${smsDataList}" var="list">
                                <c:if test="${list.smsData == null}">
                                    ${list.count}
                                </c:if>
                            </c:forEach>
						</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-envelope-o"></i>
							<span id="smsDataCount"><c:forEach items="${smsDataList}" var="list">
                                <c:if test="${list.smsData == null}">
                                    ${list.count}
                                </c:if>
                            </c:forEach> 条短信</span>
                        </li>

                        <li class="dropdown-content">
                            <ul class="dropdown-menu dropdown-navbar" id="smsData">
                                <c:forEach items="${smsDataList}" var="list">
                                    <c:if test="${list.smsData != null}">
                                        <li>
                                            <a href="rest/menu/setReadFlag?type=sms">
                                                <img src="assets/avatars/avatar2.png" class="msg-photo"/>
													<span class="msg-body">
														<span class="msg-title">
															<span class="blue">${list.userName}：</span>
																${list.smsData}
														</span>
														<span class="msg-time">
															<i class="ace-icon fa fa-clock-o"></i>
															<span>${list.smsTime}</span>
														</span>
													</span>
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>

                        <li class="dropdown-footer">
                            <a href="rest/sms/searchAlert"> 查看全部短信 <i class="ace-icon fa fa-arrow-right"></i> </a>
                        </li>
                    </ul>
                </li>

                <!-- #section:basics/navbar.user_menu 用户信息-->
                <li class="light-blue"><a data-toggle="dropdown" href="#"
                                          class="dropdown-toggle"> <img class="nav-user-photo"
                                                                        src="<%= basePath%>assets/avatars/user.jpg"
                                                                        alt="Jason's Photo"/> <span
                        class="user-info"> <small>Welcome,</small> 川江
					</span> <i class="ace-icon fa fa-caret-down"></i>
                </a>

                    <ul
                            class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li><a href="#"> <i class="ace-icon fa fa-cog"></i> 设置
                        </a></li>

                        <li><a href="profile.html"> <i
                                class="ace-icon fa fa-user"></i> 资料
                        </a></li>

                        <li class="divider"></li>

                        <li><a href="rest/user/logout"> <i class="ace-icon fa fa-power-off"></i>
                            注销
                        </a></li>
                    </ul>
                </li>

                <!-- /section:basics/navbar.user_menu -->
            </ul>
        </div>

        <!-- /section:basics/navbar.dropdown -->
    </div>
    <!-- /.navbar-container -->
</div>
