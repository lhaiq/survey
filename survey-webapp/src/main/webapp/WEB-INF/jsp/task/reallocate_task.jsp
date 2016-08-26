<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
    <h1>调查任务管理
        <small><i class="ace-icon fa fa-angle-double-right"></i> &nbsp;重新分配调查任务</small>
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
        <form class="form-horizontal" action="/survey/task" method="post" onsubmit="return false;">
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1">客户姓名<label
                        style="color: red;">&nbsp;*</label> </label>

                <input type="hidden" name="customerId" value="${customer.id}"/>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <input type="text" class="form-control" name="customerName" value="${customer.name}" disabled/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1">分配调查员<label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <select
                                class="form-control col-sm-5"
                                data-placeholder="选择一个调查员..." name="surveyorId">
                            <c:forEach items="${surveyors}" var="item">
                                <option value="${item.id}">${item.account}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">调查类型 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <select
                                class="form-control col-sm-5"
                                data-placeholder="选择一个调查类型" name="type">
                            <c:forEach items="${types}" var="item">
                                <option value="${item.name}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2">调查要点 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <textarea name="point" class="form-control"/>
                    </div>
                </div>
            </div>

            <div class="row" style="height:30px"></div>
            <div class="form-group">
                <label class="col-sm-4 control-label no-padding-right" for="form-field-1-2"> </label>

                <div class="col-sm-8">
                    <div class="col-sm-5 no-padding-left">
                        <button class="btn btn-xs btn-success" onclick="onSubmit()" >
                            <i class="ace-icon fa fa-check bigger-110">提交</i>
                        </button>

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

    function reset() {
        $('.form-horizontal')[0].reset()
    }
    function onSubmit() {

        $('.form-horizontal').ajaxSubmit({
            success: function (data) {
                if (data.status) {
                    javascript:link('/survey/core/task')
                }
            }
        });
    }
</script>