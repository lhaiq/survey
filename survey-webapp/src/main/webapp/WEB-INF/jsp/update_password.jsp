<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
    <div class="col-xs-12">
        <div class="page-header">
            <h1>个人资料
                <small><i class="ace-icon fa fa-angle-double-right"></i> &nbsp;修改密码</small>
            </h1>
        </div>
        <div class="alert alert-block alert-success hide" id="alert-success">
            <button type="button" class="close" data-dismiss="alert"><i class="ace-icon fa fa-times"></i>
            </button>
            <i class="ace-icon fa fa-check green"></i>
            <strong class="green">修改成功</strong>
        </div>
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" action="/survey/user/updatePass/${sessionScope.user.id}" method="post"
              onsubmit="return false;">
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1">旧密码<label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <input type="password" class="form-control" name="oldPassword" id="oldPassword"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">新密码 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <input type="password" name="newPassword" id="newPassword" class="form-control col-sm-5"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">新密码确认 <label
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
                                style="width:81px;margin-left: 34px">
                            <i class="ace-icon fa fa-check bigger-110">提交</i>
                        </button>

                        &nbsp; &nbsp; &nbsp;
                        <button class="btn btn-xs btn-success" onclick="reset();" style="width:81px">
                            <i class="ace-icon fa fa-undo bigger-110">重置</i>
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    function reset() {
        $('.form-horizontal')[0].reset();
    }

    function validate() {
        return $(".form-horizontal").validate({
            rules: {
                oldPassword: {
                    required: true,
                    minlength: 6,
                    remote: {
                        type: "get",
                        url: "/survey/core/user/validate/oldPass/${sessionScope.user.id}",
                        data: {
                            oldPassword: function () {
                                return $("#oldPassword").val();
                            }
                        }
                    }
                },
                newPassword: {
                    required: true,
                    minlength: 6
                },
                confirmPass: {
                    required: true,
                    minlength: 6,
                    equalTo: "#newPassword"
                }
            },
            messages: {
                oldPassword: {
                    required: true,
                    minlength: 6,
                    remote: "旧密码错误"
                },
                newPassword: {
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
                    $("#alert-success").removeClass("hide");
                    reset();
                }
            }
        });
    }
</script>
