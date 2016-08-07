<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="page-header">
    <h1>照片类型
        <small><i class="ace-icon fa fa-angle-double-right"></i> &nbsp;添加照片类型</small>
    </h1>
</div>
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" action="/survey/photoType" method="post"
              onsubmit="return false">
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
            <input type="hidden" class="form-control" name="type" value="1"/>

            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1">像素<label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-6 no-padding-left">
                        <select name="pixel"
                                class="form-control col-sm-5"
                                data-placeholder="选择一个像素...">
                            <option value="1920*1680">1920*1680</option>
                            <option value="1920*1080">1920*1080</option>
                            <option value="1366*768">1366*768</option>
                            <option value="1440*900">1440*900</option>
                            <option value="1600*900">1600*900</option>
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
                        <button class="btn btn-xs btn-success" onclick="reset()">
                            <i class="ace-icon fa fa-undo bigger-110">重置</i>
                        </button>
                    </div>

                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">

    $(document).ready(function () {
        validate();
    });


    function reset() {
        $('.form-horizontal')[0].reset()
    }


    function validate() {
        return $(".form-horizontal").validate({
            rules: {
                name: {
                    required: true,
                    remote: "/survey/conf/validate/1"
                },
                file: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "必填字段",
                    remote: "该名字已存在"
                },
                file: {
                    required: "必填字段"
                }

            }
        });
    }


    function onSubmit() {

        if (!validate().form()) {
            return;
        }
        $('.form-horizontal').ajaxSubmit({
            success: function (data) {
                if (data.status) {
                    link('/survey/photoType')
                }
            }
        });
    }

</script>
