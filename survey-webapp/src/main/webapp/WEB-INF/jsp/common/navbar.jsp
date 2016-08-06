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
            <a href="" class="navbar-brand">
                <small> <!-- i标签为公司图标-->
                    <img src="assets/logo.png"> 汇融易实地调查系统
                </small>
            </a>

            <!-- /section:basics/navbar.layout.brand -->

            <!-- #section:basics/navbar.toggle -->

            <!-- /section:basics/navbar.toggle -->

        </div>

        <!-- #section:basics/navbar.dropdown 导航条提示信息及下来菜单和用户信息-->

        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">





                <!-- #section:basics/navbar.user_menu 用户信息-->
                <li class="light-blue"><a data-toggle="dropdown" href="#"
                                          class="dropdown-toggle"> <img class="nav-user-photo"
                                                                        src="<%= basePath%>assets/avatars/user.jpg"
                                                                        alt="Jason's Photo"/> <span
                        class="user-info"> <small>Welcome,</small> ${sessionScope.user.account}
					</span> <i class="ace-icon fa fa-caret-down"></i>
                </a>

                    <ul
                            class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

                        <li><a href="javascript:link('/survey/updatePasswordUI')"> <i
                                class="ace-icon fa fa-user"></i> 资料
                        </a></li>

                        <li class="divider"></li>

                        <li><a href="/survey/core/user/logout"> <i class="ace-icon fa fa-power-off"></i>
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
