<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(function () {
        $("#addPhotoType").on("click", function () {
            var $newPhotoType = '<div class="form-group" >'
                    + '<label class="col-sm-4 control-label no-padding-right" for="form-field-1"> </label>'
                    + '<div class="col-sm-8">'
                    + '<div class="col-sm-3 no-padding-left">'
                    + '<input type="text" class="form-control" name="photoTypes" value=""/>'
                    + '</div>'
                    + '<div class="col-sm-3 no-padding-left">'
                    + '   <select name="pixels"'
                    + '           class="form-control col-sm-5"'
                    + '           data-placeholder="选择一个调查员...">'
                    + '       <option value="1920*1680">1920*1680</option>'
                    + '       <option value="副总经理">李四</option>'
                    + '       <option value="主管">王麻子</option>'
                    + '       <option value="WY">吴旗</option>'
                    + '  </select>'
                    + '</div>      '
                    + '<div class="col-sm-2" style="margin-top: 8px">'
                    + '<a href="javascript:void(0)" class="tooltip-success" data-rel="tooltip" title=""'
                    + 'data-original-title="Add">'
                    + '<span class="black photoTypeCls">'
                    + '<i class="ace-icon fa fa-minus bigger-120"></i>'
                    + '</span>'
                    + ' </a>'
                    + '</div>'
                    + '</div>'
                    + '</div>';
            alert($newPhotoType)
            $("#photoTypeContainer").after($newPhotoType);

            $(".photoTypeCls").on("click", function () {
                $(this).parent().parent().parent().parent().remove();
            });
        });

    });
</script>
<div class="page-header">
    <h1>调查任务管理
        <small><i class="ace-icon fa fa-angle-double-right"></i> &nbsp;编辑任务类型</small>
    </h1>
</div>
<div class="row">
    <div class="col-xs-12">
        <c:if test="${addFlag == 'Y' }">
            <div class="alert alert-block alert-success">
                <button type="button" class="close" data-dismiss="alert"><i class="ace-icon fa fa-times"></i>
                </button>
                <i class="ace-icon fa fa-check green"></i>
                <strong class="green">添加成功。</strong>
            </div>
        </c:if>
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" action="/survey/conf" method="post" onsubmit="return false;">
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1">类型名<label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-6 no-padding-left">
                        <input type="text" class="form-control" name="name" value="${data.name}"/>
                    </div>
                </div>
            </div>

            <c:forEach items="${data.photos}" var="photo" begin="0" varStatus="i">

                <div class="form-group" id="photoTypeContainer">
                    <label class="col-sm-4 control-label no-padding-right" for="form-field-1">
                        <c:if test="${i.first}"
                                >照片类型</c:if>
                    </label>

                    <div class="col-sm-8">
                        <div class="col-sm-3 no-padding-left">
                            <input type="text" class="form-control" name="photoTypes" value="${photo.name}"/>
                        </div>
                        <div class="col-sm-3 no-padding-left">
                            <select name="pixels"
                                    class="form-control col-sm-5"
                                    data-placeholder="选择一个调查员...">
                                <option value="1920*1680" <c:if test="${photo.pixel=='1920*1680'}">selected</c:if>>1920*1680</option>
                                <option value="1920*1080" <c:if test="${photo.pixel=='1920*1080'}">selected</c:if>>1920*1080</option>
                                <option value="1366*768"  <c:if test="${photo.pixel=='1366*768'}">selected</c:if>>1366*768</option>
                                <option value="1440*900"  <c:if test="${photo.pixel=='1440*900'}">selected</c:if>>1440*900</option>
                                <option value="1600*900"  <c:if test="${photo.pixel=='1600*900'}">selected</c:if>>1600*900</option>
                            </select>
                        </div>
                        <div class="col-sm-2" style="margin-top: 8px">

                            <a href="javascript:void(0)" class="tooltip-success" data-rel="tooltip" title=""
                               data-original-title="Add">
                                <c:if test="${i.first}">
                                    <span class="black" id="addPhotoType">
                                <i class="ace-icon fa fa-plus-circle bigger-120"></i>
                            </span>
                                </c:if>

                                <c:if test="${!i.first}">
                                    <span class="black photoTypeCls">
                                    <i class="ace-icon fa fa-minus bigger-120"></i>
                                </span>
                                </c:if>

                            </a>
                        </div>
                    </div>

                </div>

            </c:forEach>


            <div class="form-group" id="templateContainer">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">模板 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-6 no-padding-left">
                        <select multiple="" class="chosen-select form-control"
                                id="form-field-select-4"
                                data-placeholder="选择一个模版..." name="templates">
                            <c:forEach items="${templates}" var="item">
                                <option value="${item.id}"
                                        <c:forEach items="${data.templates}" var="template">
                                            <c:if test="${template.id==item.id}">selected</c:if>
                                        </c:forEach>
                                        >${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>


            <div class="row" style="height:30px"></div>
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2"> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <button class="btn btn-xs btn-success" onclick="onSubmit()"
                                style="width: 81px;margin-left: 34px">
                            <i class="ace-icon fa fa-check bigger-110">提交</i>
                        </button>

                        &nbsp; &nbsp; &nbsp;
                        <button class="btn btn-xs btn-success" onclick="doUndoAll()">
                            <i class="ace-icon fa fa-undo bigger-110">重置</i>
                        </button>
                    </div>

                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">

    jQuery(function ($) {
        $('.chosen-select').chosen({
            allow_single_deselect: true
        });
        //resize the chosen on window resize

        $(window).off('resize.chosen').on('resize.chosen', function () {
            $('.chosen-select').each(function () {
                var $this = $(this);
                $this.next().css({
                    'width': $this.parent().width()
                });
            })
        }).trigger('resize.chosen');


    });

    //    function onSubmit() {
    //        $('.form-horizontal').ajaxSubmit({
    //            success: function (data) {
    //                if (data.status) {
    //                    alert(JSON.stringify(data))
    //                    link_template(routers.template_list, {page: 0})
    //                }
    //            }
    //        });
    //    }

    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = [this.value || ''];
            }
        });
        return o;
    };

    function onSubmit() {
        $('.form-horizontal')
        var data = $('.form-horizontal').serializeObject()
        console.log(JSON.stringify(data))
        $.ajax({
            type: "post",
            url: "/survey/conf",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (data) {
                if (!data.status) {
                    $("#account-label").html(data.error.message)
                } else {
                    console.log(data)
                }
            }
        });
    }


</script>
