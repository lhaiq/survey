<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!-- #section:settings.box -->
<!-- 界面设置控件-->
<div class="ace-settings-container" id="ace-settings-container">
    <!-- 界面控件图标-->
    <div class="btn btn-app btn-xs btn-warning ace-settings-btn"
         id="ace-settings-btn">
        <i class="ace-icon fa fa-cog bigger-150"></i>
    </div>
    <div class="ace-settings-box clearfix" id="ace-settings-box">
        <div class="pull-left">
            <!-- #section:settings.skins -->
            <!-- 界面颜色调整-->
            <div class="ace-settings-item">
                <div class="pull-left">
                    <select id="skin-colorpicker" class="hide">
                        <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                        <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                        <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                        <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                    </select>
                </div>
                <span>&nbsp; 皮肤</span>
            </div>
            <!-- #section:settings.container -->
            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2"
                       id="ace-settings-add-container"/> <label class="lbl"
                                                                for="ace-settings-add-container"> 界面调整 </label>
            </div>
            <!-- #section:basics/sidebar.options -->
            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2"
                       id="ace-settings-hover"/> <label class="lbl"
                                                        for="ace-settings-hover">子菜单悬浮</label>
            </div>
            <!-- /section:settings.container -->

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2"
                       id="ace-settings-compact"/> <label class="lbl"
                                                          for="ace-settings-compact">迷你导航</label>
            </div>

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2"
                       id="ace-settings-highlight"/> <label class="lbl"
                                                            for="ace-settings-highlight">导航边框样式</label>
            </div>

            <!-- /section:basics/sidebar.options -->
        </div>
        <!-- /.pull-left -->
    </div>
    <!-- /.ace-settings-box -->
</div>
