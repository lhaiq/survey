<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
    <div class="col-xs-12">
        <div class="page-header">
            <h1>客户管理
                <small><i class="ace-icon fa fa-angle-double-right"></i> &nbsp;编辑客户</small>
            </h1>
        </div>

        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" action="/survey/customer/${data.id}" method="post" onsubmit="return false;">
            <input type="hidden" name="_method" value="put" />
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1">姓名<label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <input type="text" class="form-control" name="name" value="${data.name}"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1">手机号码<label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <input type="text" class="form-control" name="mobileNumber" value="${data.mobileNumber}"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1">座机<label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <input type="text" class="form-control" name="telephoneNumber" value="${data.telephoneNumber}"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">身份证号码 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <input type="text" name="idCard" class="form-control col-sm-5" value="${data.idCard}"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">公司 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <input type="text" name="company" class="form-control col-sm-5" value="${data.company}"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">地址 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <input type="text" name="address" class="form-control col-sm-5" value="${data.address}"/>
                    </div>
                </div>
            </div>

            <div class="row" style="height:30px"></div>
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2"> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <button class="btn btn-xs btn-success" onclick="onSubmit()" style="width:81px;margin-left: 34px">
                            <i class="ace-icon fa fa-check bigger-110">提交</i>
                        </button>

                        &nbsp; &nbsp; &nbsp;
                        <button class="btn btn-xs btn-success" onclick="doUndoAll()" style="width:81px">
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
        ('.form-horizontal')[0].reset()
    }

    function validate() {
        return $(".form-horizontal").validate({
            rules: {
                name: {
                    required: true,
                },
                mobileNumber: {
                    required: true,
                    number: true,
                    maxlength: 11,
                    minlength: 11
                },
                telephoneNumber: {
                    required: true,
                    number: true
                },
                idCard: {
                    required: true,
                    minlength: 15,
                    remote: "/survey/customer/validate"
                },
                company: "required",
                address: "required"
            },
            messages: {
                name: {
                    required: "必填字段"
                },
                mobileNumber: {
                    required: "请输入手机号",
                    minlength: "手机号长度非法",
                    number: "手机号必须由数字组成"
                },
                telephoneNumber: {
                    required: "请输入手机号",
                    number: "手机号必须由数字组成"
                },
                idCard: {
                    required: "必填字段",
                    minlength: "格式错误",
                    remote: "该身份证已添加"
                },
                password: {
                    required: "请输入密码",
                    minlength: "密码长度不能小于 5 个字母"
                },
                company: "必填字段",
                address: "必填字段"
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
                    javascript:link('/survey/customer')
                }
            }
        });
    }
</script>

