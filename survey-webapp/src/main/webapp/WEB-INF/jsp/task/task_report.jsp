<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>


<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <div class="space-6"></div>

        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <!-- #section:pages/invoice -->
                <div class="widget-box transparent">
                    <div class="widget-header widget-header-large">
                        <h2 class="widget-title grey lighter">
                            <i class="ace-icon fa fa-list-alt"></i> 汇融易实地调研报告
                        </h2>

                        <div class="widget-toolbar">
                            <div class="widget-toolbar no-border invoice-info">
                                <span class="invoice-info-label">调查人员:</span> <span
                                    class="red">${surveyor.account} </span> <br/> <span
                                    class="invoice-info-label">调研日期:</span> <span
                                    class="blue">2018</span>
                            </div>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main padding-24">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="row">
                                        <div class="col-xs-11 label label-lg label-info arrowed-in arrowed-right">
                                            <b>实时定位地址</b>
                                        </div>
                                    </div>

                                    <%--<div class="col-sm-6">--%>
                                    <%--<img height="250"--%>
                                    <%--src="http://api.map.baidu.com/staticimage?center=${sign.actualLongitude},${sign.actualLatitude}&width=400&height=300&zoom=11&markers=${sign.actualLongitude},${sign.actualLatitude}"/>--%>
                                    <%--</div>--%>
                                </div>
                                <!-- /.col -->

                                <div class="col-sm-6">
                                    <div class="row">
                                        <div class="col-xs-11 label label-lg label-success arrowed-in arrowed-right">
                                            <b>调研人员设置地址</b>
                                        </div>
                                    </div>

                                    <%--<div class="col-sm-6">--%>
                                    <%--<img height="250"--%>
                                    <%--src="http://api.map.baidu.com/staticimage?center=${sign.signLongitude},${sign.signLatitude}&width=400&height=300&zoom=11&markers=${sign.signLongitude},${sign.signLatitude}"/>--%>
                                    <%--</div>--%>
                                </div>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.col -->


                    <div class="row">
                        <%--<div class="col-sm-1"></div>--%>


                        <div>
                            <h3 class="widget-title grey lighter">调研报告及结论</h3>
                        </div>
                        <div class="hr hr8  hr-dotted"></div>

                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name">客户名</div>

                                <div class="profile-info-value">
                                    <span class="editable" id="username">${td.customer.name}</span>
                                </div>
                            </div>


                            <div class="profile-info-row">
                                <div class="profile-info-name">调查类型</div>

                                <div class="profile-info-value">
                                    <span class="editable" id="age">${td.type}</span>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-------------------------------------------------------------------------------------->
                    <div class="row">
                        <div>
                            <h3 class="widget-title grey lighter">调研照片</h3>
                        </div>
                        <div class="hr hr8  hr-dotted"></div>
                        <div id="user-profile-2" class="user-profile">
                            <div class="tabbable">
                                <ul class="nav nav-tabs padding-18 ">
                                    <c:forEach items="${td.photos}" var="ps1" varStatus="i">
                                        <li class="<c:if test="${i.first}">active</c:if>"><a data-toggle="tab"
                                                                                             href="#pictures${i.count}">
                                            <i
                                                    class="orange ace-icon fa fa-picture-o bigger-120"></i>${ps1.key}
                                        </a>
                                        </li>
                                    </c:forEach>
                                </ul>

                                <div class="tab-content no-border padding-24">
                                    <c:forEach items="${td.photos}" var="ps2" varStatus="i">
                                        <div id="pictures${i.count}"
                                             class="tab-pane in <c:if test="${i.first}">active</c:if>">
                                            <div>
                                                <ul class="ace-thumbnails clearfix">
                                                    <c:forEach items="${ps2.value.contents}" var="pic">
                                                        <li><a href="/survey/photo/jpg/${pic.id}.${pic.extension}"
                                                               title="Photo Title"
                                                               data-rel="colorbox"> <img width="150" height="150"
                                                                                         alt="150x150"
                                                                                         src="/survey/photo/${pic.id}?width=150&height=150"/>

                                                            <div class="text">
                                                                <div class="inner">点击查看</div>
                                                            </div>
                                                        </a></li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!---------------------------------------------------------------------------------------------------------------------------->

                    <div class="row">
                        <div>
                            <h3 class="widget-title grey lighter">调研表格</h3>
                        </div>
                        <div class="hr hr8  hr-dotted"></div>
                        <div id="user-profile-3" class="user-profile">
                            <div>
                                <ul>
                                    <c:forEach items="${td.reports}" var="report" varStatus="i">
                                        <li><a class="dialogMessage" href="#modal-form" href="#" name="${i.count}"
                                               role="button"> <i
                                                class="orange ace-icon fa bigger-120"></i> ${report.key}
                                        </a>
                                        </li>
                                        <div id="dialog-message${i.count}" class="hide" name="${report.key}">
                                                <p>sadasdasdasdasdsadas</p>
                                        </div>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div>
                            <h3 class="widget-title grey lighter">调研录音</h3>
                        </div>
                        <div class="hr hr8  hr-dotted"></div>
                        <div class="profile-users clearfix">

                            <c:forEach items="${td.audios}" var="audio">

                                <div class="itemdiv memberdiv">
                                    <div class="inline pos-rel">
                                        <div class="user">
                                            <a href="/survey/audio/${audio.id}"> <img src="../assets/avatars/sound.png"
                                                                                      alt="Bob Doe's avatar"/>

                                            </a>
                                        </div>

                                        <div class="body">
                                            <div class="name">
                                                <a href="#"> <span
                                                        class="user-status status-online"></span> ${audio.name}
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </c:forEach>
                            <audio id="audio" src="/survey/audio/extension/28.amr"
                                   preload="auto" controls muted loop autoplay>
                            </audio>

                        </div>

                    </div>
                </div>
                <div class="row">
                    <div>
                        <h3 class="widget-title grey lighter">评价</h3>
                    </div>
                    <div class="hr hr8  hr-dotted"></div>
                    <div>
                        <textarea class="autosize-transition form-control"></textarea>
                    </div>
                    <br/>

                    <div class="col-md-offset-3 col-md-9">
                        <button class="btn btn-info" type="button">
                            <i class="ace-icon fa fa-thumbs-o-up bigger-110"></i> 通过
                        </button>

                        &nbsp; &nbsp; &nbsp;
                        <button class="btn" type="reset">
                            <i class="ace-icon fa fa-thumbs-o-down bigger-110"></i> 拒绝
                        </button>
                    </div>
                </div>
                <div class="row"></div>
            </div>

            <!-- /section:pages/invoice -->
        </div>
    </div>

    <!-- PAGE CONTENT ENDS -->

</div>
<!-- /.row -->


<%--查看图片js--%>
<script type="text/javascript">
    jQuery(function ($) {
        var $overflow = '';
        var colorbox_params = {
            rel: 'colorbox',
            reposition: true,
            scalePhotos: true,
            scrolling: false,
            previous: '<i class="ace-icon fa fa-arrow-left"></i>',
            next: '<i class="ace-icon fa fa-arrow-right"></i>',
            close: '&times;',
            current: '{current} of {total}',
            maxWidth: '100%',
            maxHeight: '100%',
            onOpen: function () {
                $overflow = document.body.style.overflow;
                document.body.style.overflow = 'hidden';
            },
            onClosed: function () {
                document.body.style.overflow = $overflow;
            },
            onComplete: function () {
                $.colorbox.resize();
            }
        };
        $('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
        $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange'></i>");//let's add a custom loading icon
    });

    $(function ($) {
        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function (title) {
                var $title = this.options.title || '&nbsp;';
                if (("title_html" in this.options) && this.options.title_html == true)
                    title.html($title);
                else title.text($title);
            }
        }));
        $(".dialogMessage").on('click', function (e) {
            e.preventDefault();
            var name = this.name;
            var dialog = $("#dialog-message" + name).removeClass('hide').dialog({
                width: 800,
                modal: true,
                title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-check'>"+name+"</i></h4></div>",
                title_html: true,
                buttons: [
                    {
                        text: "Ok",
                        "class": "btn btn-primary btn-xs",
                        click: function () {
                            $(this).dialog("close");
                        }
                    }
                ]
            });

        });

    });
</script>