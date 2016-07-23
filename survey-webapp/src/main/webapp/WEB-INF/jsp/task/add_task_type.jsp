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
        <form class="form-horizontal" action="rest/user/creat" method="post" onsubmit="return false;">
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1">类型名<label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-9">
                    <div class="col-sm-5 no-padding-left">
                        <input type="text" class="form-control" name="username" value=""/>
                        ${isSameUser }
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1">照片类型<label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-9">
                    <div class="col-sm-5 no-padding-left">
                        <input type="text" class="form-control" name="username" value=""/>
                    </div>
                    <div class="col-sm-3">

                        <a href="#" class="tooltip-success" data-rel="tooltip" title=""
                           data-original-title="Add">
                            <span class="black">
                                <i class="ace-icon fa fa-plus-circle bigger-120"></i>
                            </span>
                        </a>
                    </div>
                </div>

            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1-2">模板 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-9">
                    <div class="col-sm-5 no-padding-left">
                        <input type="text" class="form-control" name="username" value=""/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1-2">描述 <label
                        style="color: red;">&nbsp;*</label> </label>

                <div class="col-sm-9">
                    <div class="col-sm-5 no-padding-left">
                        <input type="text" name="chinaName" class="form-control col-sm-5"/>
                    </div>

                </div>
            </div>

            <div class="row" style="height:30px"></div>
            <div align="center">
                <div>
                    <button class="btn btn-sm btn-success" onclick="onSubmit()">
                        <i class="ace-icon fa fa-check bigger-110">提交</i>
                    </button>

                    &nbsp; &nbsp; &nbsp;
                    <button class="btn btn-sm btn-success" onclick="doUndoAll()">
                        <i class="ace-icon fa fa-undo bigger-110">重置</i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
