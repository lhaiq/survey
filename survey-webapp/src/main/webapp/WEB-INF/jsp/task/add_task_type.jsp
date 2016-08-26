<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <form class="form-horizontal" action="/survey/conf" method="post" onsubmit="return false;">
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

            <div class="form-group" id="photoContainer">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">照片类型 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-6 no-padding-left">
                        <select multiple="" class="chosen-select form-control"
                                id="form-field-select-5"
                                data-placeholder="选择一个照片类型..." name="photoTypes">
                            <c:forEach items="${photoTypes}" var="item">
                                <option value="${item.id}">${item.name}--${item.pixel}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group" id="templateContainer">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">模板 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-6 no-padding-left">
                        <select multiple="" class="chosen-select form-control"
                                id="form-field-select-4"
                                data-placeholder="选择一个模版..." name="templates">
                            <c:forEach items="${templates}" var="item">
                                <option value="${item.id}">${item.name}</option>
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
                        <button class="btn btn-xs btn-success" onclick="onSubmit()">
                            <i class="ace-icon fa fa-check bigger-110">提交</i>
                        </button>

                        <button class="btn btn-xs btn-success" onclick="rest()">
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

    $(document).ready(function () {
        validate();
    });

    function onSubmit() {

        if (!validate().form()) {
            return;
        }

        $('.form-horizontal')
        var data = $('.form-horizontal').serializeObject()
        console.log(JSON.stringify(data))
        $.ajax({
            type: "post",
            url: "/survey/conf",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (data) {
                if (data.status) {
                    link('/survey/task/taskType')
                }
            }
        });
    }

    function reset() {
        $('.form-horizontal')[0].reset()
    }

    function validate() {
        return $(".form-horizontal").validate({
            rules: {
                name: {
                    required: true,
                    remote: "/survey/conf/validate/0"
                },
                templates: {
                    required: true
                },
                photoTypes: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "必填字段",
                    remote: "该名字已存在"
                },
                photoTypes: {
                    required: "必填字段"
                },

                templates: {
                    required: "必填字段"
                }
            }
        });
    }


</script>
