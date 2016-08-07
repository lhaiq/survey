<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript">
    try {
        ace.settings.check('main-container', 'fixed')
    } catch (e) {
    }
</script>
<!-- #section:basics/sidebar 侧边导航-->
<div id="sidebar" class="sidebar                  responsive">
    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'fixed')
        } catch (e) {
        }
    </script>
    <!--侧边导航上方四个按钮-->
    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="ace-icon fa fa-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="ace-icon fa fa-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="ace-icon fa fa-users"></i>
            </button>

            <button class="btn btn-danger">
                <i class="ace-icon fa fa-cogs"></i>
            </button>

        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div>
    <!-- /.sidebar-shortcuts -->
    <!--下拉导航开始-->
    <ul class="nav nav-list">
        <c:if test="${sessionScope.user.role==2}">
            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-user"></i>
                    <span class="menu-text">评审员管理</span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="javascript:link('/survey/addSyndicUI')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            添加评审员
                        </a>
                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="javascript:link('/survey/syndic')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            评审员列表
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>

            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-user"></i>
                    <span class="menu-text">调查员管理</span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="javascript:link('/survey/addSurveyorUI')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            添加调查员
                        </a>
                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="javascript:link('/survey/surveyor')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            调查员列表
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>

            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-user"></i>
                    <span class="menu-text">调查主管管理</span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="javascript:link('/survey/addManagerUI')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            添加主管
                        </a>
                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="javascript:link('/survey/manager')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            主管列表
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>

        </c:if>
        <c:if test="${sessionScope.user.role==1 or sessionScope.user.role==3}">
            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-user"></i>
                    <span class="menu-text">客户管理</span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <c:if test="${sessionScope.user.role==1}">
                        <li class="">
                            <a href="javascript:link('/survey/addCustomerUI')">
                                <i class="menu-icon fa fa-user-plus"></i>
                                添加客户
                            </a>
                            <b class="arrow"></b>
                        </li>
                    </c:if>

                    <li class="">
                        <a href="javascript:link('/survey/customer')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            客户列表
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>
        </c:if>

        <li class="">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-paper-plane-o"></i>
                <span class="menu-text">调查任务</span>

                <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul class="submenu">
                <c:if test="${sessionScope.user.role==2}">
                    <li class="">
                        <a href="javascript:link('/survey/task/addTypeUI')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            添加任务类型
                        </a>

                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="javascript:link('/survey/task/taskType')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            任务类型列表
                        </a>

                        <b class="arrow"></b>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user.role ==1 or sessionScope.user.role ==3}">
                    <li class="">
                        <a href="javascript:link('/survey/core/task')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            任务列表
                        </a>

                        <b class="arrow"></b>
                    </li>
                </c:if>
            </ul>
        </li>

        <c:if test="${sessionScope.user.role==2}">
            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-paper-plane-o"></i>
                    <span class="menu-text">模板管理</span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">

                    <li class="">
                        <a href="javascript:link('/survey/template/addUI')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            添加模板
                        </a>

                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="javascript:link('/survey/template')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            模板列表
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>

            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-paper-plane-o"></i>
                    <span class="menu-text">照片类型管理</span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">

                    <li class="">
                        <a href="javascript:link('/survey/photoType/addUI')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            添加照片类型
                        </a>

                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="javascript:link('/survey/photoType')">
                            <i class="menu-icon fa fa-caret-right"></i>
                            照片类型列表
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>
        </c:if>


    </ul>


    <!-- #section:basics/sidebar.layout.minimize 设置导航最小化-->
    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
           data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>

    <!-- /section:basics/sidebar.layout.minimize -->
    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'collapsed')
        } catch (e) {
        }


    </script>

    <script type="text/javascript">



        function link(url) {
            $.ajax({
                url: url,
                async: true,
                success: function (data, status, xhr) {
                    $(".page-content-area").html(data)
                },
                error: function (data) {
                    console.log(data)
                }
            });
        }

    </script>


</div>



