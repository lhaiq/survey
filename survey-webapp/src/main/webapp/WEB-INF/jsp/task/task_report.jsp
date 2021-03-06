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
                                    class="blue"><fmt:formatDate value="${td.startTime}"
                                                                 pattern="yyyy-MM-dd"/></span>
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

                                    <div class="col-sm-6" id="actualLocation">
                                    </div>
                                </div>
                                <!-- /.col -->

                                <div class="col-sm-6">
                                    <div class="row">
                                        <div class="col-xs-11 label label-lg label-success arrowed-in arrowed-right">
                                            <b>调研人员设置地址</b>
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <div id="signLocation"></div>
                                    </div>
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
                            <h3 class="widget-title grey lighter">客户信息及调查总结</h3>
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
                                    <span class="editable">${td.type}</span>
                                </div>
                            </div>

                            <div class="profile-info-row">
                                <div class="profile-info-name">客户手机</div>

                                <div class="profile-info-value">
                                    <span class="editable">${td.customer.mobileNumber}</span>
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name">客户座机</div>

                                <div class="profile-info-value">
                                    <span class="editable">${td.customer.telephoneNumber}</span>
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name">客户身份证</div>

                                <div class="profile-info-value">
                                    <span class="editable">${td.customer.idCard}</span>
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name">客户公司</div>

                                <div class="profile-info-value">
                                    <span class="editable">${td.customer.company}</span>
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name">客户地址</div>

                                <div class="profile-info-value">
                                    <span class="editable">${td.customer.address}</span>
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name">调查总结</div>

                                <div class="profile-info-value">
                                    <span class="editable">${td.summary}</span>
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
                                        <div id="dialog-message${i.count}" templateId="${report.value.templateId}"
                                             reportId="${report.value.content.id}" class="hide dialog-report"
                                             name="${report.key}">
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
                                <ul>
                                    <label>${audio.name}</label>
                                    <audio src="/survey/audio/extension/${audio.id}.${audio.extension}"
                                           controls="controls">
                                    </audio>
                                </ul>

                            </c:forEach>

                        </div>

                    </div>
                </div>
                <div class="row">
                    <div>
                        <h3 class="widget-title grey lighter">评价</h3>
                    </div>
                    <div class="hr hr8  hr-dotted"></div>
                        <textarea class="autosize-transition form-control"
                                  <c:if test="${td.status!=3}">disabled</c:if>
                                  id="textarea_comment">${td.comment}</textarea>
                </div>
                <br/>

                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info"
                            <c:if test="${td.status!=3}">disabled</c:if> type="button"
                            <c:if test="${td.status==3}">onclick="comment('pass');"</c:if> >
                        <i class="ace-icon fa fa-thumbs-o-up bigger-110"></i> 通过
                    </button>

                    &nbsp; &nbsp; &nbsp;
                    <button class="btn btn-warning"
                            <c:if test="${td.status!=3}">disabled</c:if> type="button"
                            <c:if test="${td.status==3}">onclick="comment('refuse');"</c:if>>
                        <i class="ace-icon fa fa-thumbs-o-down bigger-110"></i> 重新调查
                    </button>

                    &nbsp; &nbsp; &nbsp;
                    <button class="btn btn-danger"
                            <c:if test="${td.status!=3}">disabled</c:if> type="button"
                            <c:if test="${td.status==3}">onclick="comment('discard');"</c:if>>
                        <i class="ace-icon fa fa-times bigger-110"></i> 废弃
                    </button>

                    &nbsp; &nbsp; &nbsp;
                    <a class="btn  "  href="/suvery/report/download/${td.id}" >
                        <i class="ace-icon fa fa-download bigger-110"></i> 下载
                    </a>

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
<c:if test="${sign!=null}">

    <script type="text/javascript">


        // 百度地图API功能
        var actualMap = new BMap.Map("actualLocation");
        var signMap = new BMap.Map("signLocation");

        var actual_top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
        var actual_top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
        var actual_top_right_navigation = new BMap.NavigationControl({
            anchor: BMAP_ANCHOR_TOP_RIGHT,
            type: BMAP_NAVIGATION_CONTROL_SMALL
        });

        var sign_top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
        var sign_top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
        var sign_top_right_navigation = new BMap.NavigationControl({
            anchor: BMAP_ANCHOR_TOP_RIGHT,
            type: BMAP_NAVIGATION_CONTROL_SMALL
        });

        actualMap.addControl(actual_top_left_control);
        actualMap.addControl(actual_top_left_navigation);
        actualMap.addControl(actual_top_right_navigation);

        signMap.addControl(sign_top_left_control);
        signMap.addControl(sign_top_left_navigation);
        signMap.addControl(sign_top_right_navigation);

        var actualPoint = new BMap.Point(${sign.actualLongitude}, ${sign.actualLatitude});
        actualMap.centerAndZoom(actualPoint, 12);
        var actualMarker = new BMap.Marker(actualPoint);// 创建标注
        actualMap.addOverlay(actualMarker);             // 将标注添加到地图中
        actualMarker.enableDragging();

        var signPoint = new BMap.Point(${sign.signLongitude}, ${sign.signLatitude});
        signMap.centerAndZoom(signPoint, 12);
        var signMarker = new BMap.Marker(signPoint);// 创建标注
        signMap.addOverlay(signMarker);             // 将标注添加到地图中
        signMarker.enableDragging();


    </script>
</c:if>
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


    function comment(type) {
        var comment = $("#textarea_comment").val();
        $.ajax({
            type: "get",
            url: "/survey/comment/task/" +${td.id},
            data: {
                comment: comment,
                type: type
            },
            success: function (data) {
                if (data.status) {
                    link('/survey/core/task')
                }
            }
        });
    }

    function getTemplateAndReport(reportDiv) {
        var templateId = reportDiv.attr("templateId");
        $.ajax({
            type: "get",
            url: "/survey/conf/" + templateId,
            success: function (data) {
                if (data.status) {
                    var content = data.data.content;
                    content = content.replace('templateId', 'templateId' + templateId)
                    reportDiv.html(content);
                    fileReport(reportDiv)
                }
            }
        });
    }

    function fileReport(reportDiv) {
        var reportId = reportDiv.attr("reportId");
        if (reportId != '') {
            $.ajax({
                type: "get",
                url: "/survey/report/" + reportId,
                success: function (data) {
                    if (data.status) {
                        var content = JSON.parse(data.data.content);
                        console.log(content)
                        $.each(content, function (key, value) {
                            var element = $("#" + key);
                            if (element.is('input')) {
                                var type = element[0].type;
                                if (type == 'text') {
                                    element.attr('value', value);
                                } else if (type == 'radio' || type == 'checkbox') {
                                    if (value == 'true') {
                                        element.attr('checked', true);
                                    }
                                }
                            } else if (element.is('textarea')) {
                                element.val(value)
                            } else if (element.is('select')) {
                                element.val(value)
                            }

                            element.attr("disabled", true);

                        });
                    }
                }
            });
        }

    }


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
            $(".dialog-report").html("")
            getTemplateAndReport($("#dialog-message" + name));
            var dialog = $("#dialog-message" + name).removeClass('hide').dialog({
                width: 800,
                modal: true,
                title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-check'>" + name + "</i></h4></div>",
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