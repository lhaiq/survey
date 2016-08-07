<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="row">
    <div class="col-xs-12">

        <div class="page-header">
            <h1>调查主管管理
                <small><i class="ace-icon fa fa-angle-double-right"></i> &nbsp;编辑主管</small>
            </h1>
        </div>


        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" action="/survey/user/${data.id}" method="post" onsubmit="return false;">
            <input type="hidden" name="_method" value="put" />
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1">登录名<label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <input type="text" class="form-control" name="account" value="${data.account}" disabled/>
                        ${isSameUser }
                    </div>
                </div>
            </div>
            <input type="hidden" name="role" value="3"/>
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">登录密码 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <input type="password" name="password" id="password" class="form-control col-sm-5"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">密码确认 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <input type="password" name="confirmPass" class="form-control col-sm-5"/>
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
    function reset(){
        $('.form-horizontal')[0].reset()
    }

    function validate() {
        return $(".form-horizontal").validate({
            rules: {
                password: {
                    required: true,
                    minlength: 6
                },
                confirmPass: {
                    required: true,
                    minlength: 6,
                    equalTo: "#password"
                }
            },
            messages: {
                password: {
                    required: "必填字段",
                    minlength: "密码长度不够"
                },
                confirmPass: {
                    required: true,
                    minlength: "密码长度不够",
                    equalTo: "两次密码输入不一致"
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
                    javascript:link('/survey/manager')
                }
            }
        });
    }
</script>
