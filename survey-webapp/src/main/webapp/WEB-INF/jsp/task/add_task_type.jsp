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
            $("#photoTypeContainer").after($newPhotoType);

            $(".photoTypeCls").on("click", function () {
                $(this).parent().parent().parent().parent().remove();
            });
        });

        $("#addTemplate").on("click", function () {
            var $newTemplate = '<div class="form-group" id="templateContainer">'
                    + '<label class="col-sm-4 control-label no-padding-right" for="form-field-1-2"> </label>'
                    + '<div class="col-sm-8">'
                    + '<div class="col-sm-3 no-padding-left">'
                    + '<input type="text" class="form-control" name="templateNames" value=""/>'
                    + '</div>'
                    + '<div class="col-sm-3 no-padding-left">'
                    + '<input type="text" class="form-control" name="templateContents" value=""/>'
                    + '</div>'
                    + '<div class="col-sm-2" style="margin-top: 8px">'
                    + '<a href="javascript:void(0)" class="tooltip-success" data-rel="tooltip" title=""'
                    + 'data-original-title="Add">'
                    + '<span class="black templateCls">'
                    + '<i class="ace-icon fa fa-minus bigger-120"></i>'
                    + '</span>'
                    + ' </a>'
                    + '</div>'
                    + '</div>'
                    + '</div>';
            $("#templateContainer").after($newTemplate);

            $(".templateCls").on("click", function () {
                $(this).parent().parent().parent().parent().remove();
            });
        });

    });
</script>
<div class="page-header">
    <h1>调查任务管理
        <small><i class="ace-icon fa fa-angle-double-right"></i> &nbsp;添加任务类型</small>
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
        <form class="form-horizontal" action="rest/user/creat" method="post" onsubmit="return false;">
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1">类型名<label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-6 no-padding-left">
                        <input type="text" class="form-control" name="name" value=""/>
                    </div>
                </div>
            </div>

            <div class="form-group" id="photoTypeContainer">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1">照片类型<label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-3 no-padding-left">
                        <input type="text" class="form-control" name="photoTypes" value=""/>
                    </div>
                    <div class="col-sm-3 no-padding-left">
                        <select name="pixels"
                                class="form-control col-sm-5"
                                data-placeholder="选择一个调查员...">
                            <option value="1920*1680">1920*1680</option>
                            <option value="副总经理">李四</option>
                            <option value="主管">王麻子</option>
                            <option value="WY">吴旗</option>
                        </select>
                    </div>
                    <div class="col-sm-2" style="margin-top: 8px">

                        <a href="javascript:void(0)" class="tooltip-success" data-rel="tooltip" title=""
                           data-original-title="Add">
                            <span class="black" id="addPhotoType">
                                <i class="ace-icon fa fa-plus-circle bigger-120"></i>
                            </span>
                        </a>
                    </div>
                </div>

            </div>

            <div class="form-group" id="templateContainer">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">模板 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-3 no-padding-left">
                        <input type="text" class="form-control" name="templateNames" value=""/>
                    </div>
                    <div class="col-sm-3 no-padding-left">
                        <input type="text" class="form-control" name="templateContents" value=""/>
                    </div>
                    <div class="col-sm-3" style="margin-top: 8px">

                        <a href="javascript:void(0)" class="tooltip-success" data-rel="tooltip" title=""
                           data-original-title="Add">
                            <span class="black" id="addTemplate">
                                <i class="ace-icon fa fa-plus-circle bigger-120"></i>
                            </span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">描述 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-6 no-padding-left">
                        <textarea class="form-control col-sm-5" name="desc"></textarea>
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
        var data = $('.form-horizontal').serializeArray()
        console.log(JSON.stringify(data))
        $.ajax({
            type: "post",
            url: "/survey/core/conf",
            contentType:"application/json",
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
