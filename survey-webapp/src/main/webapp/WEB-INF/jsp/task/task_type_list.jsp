<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>


<div class="row">
    <div class="col-xs-12">
        <div class="page-header">
            <h1>调查任务管理
                <small><i class="ace-icon fa fa-angle-double-right"></i> &nbsp;调查任务类型列表</small>
            </h1>
        </div>
        <div class="table-header" style="background-color: #438eb9">
            列表
        </div>
        <div id="sample-table-2_wrapper" class="dataTables_wrapper form-inline no-footer">
            <div class="row">
                <div class="col-xs-6">
                    <div class="dataTables_length" id="sample-table-2_length">
                        <label>Display <select name="sample-table-2_length" aria-controls="sample-table-2"
                                               class="form-control input-sm">
                            <option value="10">10</option>
                            <option value="25">25</option>
                            <option value="50">50</option>
                            <option value="100">100</option>
                        </select> records</label></div>
                </div>
                <div class="col-xs-6">
                    <div id="sample-table-2_filter" class="dataTables_filter"><label>Search:<input type="search"
                                                                                                   class="form-control input-sm"
                                                                                                   aria-controls="sample-table-2">
                    </label></div>
                </div>
            </div>
            <div class="row">
                <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable no-footer"
                       role="grid" aria-describedby="sample-table-2_info">
                    <thead>
                    <tr role="row">

                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Domain: activate to sort column ascending">类型名
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Price: activate to sort column ascending">照片类型
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Price: activate to sort column ascending">调查模板
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Price: activate to sort column ascending">描述
                        </th>


                        <th class="sorting_disabled" rowspan="1" colspan="1" aria-label="">操作</th>
                    </tr>
                    </thead>

                    <tbody>


                    <c:forEach var="item" varStatus="i" items="${data.content}" begin="0" end="10">
                        <c:choose>
                            <c:when test="${i.count%2==0}">
                                <tr role="row" class="odd">
                            </c:when>
                            <c:otherwise>
                                <tr role="row" class="even">
                            </c:otherwise>
                        </c:choose>
                        <td class="hidden-480">${item.name}</td>
                        <td class="hidden-480">门面,车照</td>
                        <td class="hidden-480">客户个人资料,客户家庭资料</td>
                        <td class="hidden-480">房贷调查需填写这些表格</td>
                        <td>
                            <div class="hidden-sm hidden-xs action-buttons">

                                <a class="dialogMessage" href="#modal-form" href="#" name="${i.count}" role="button">
                                    <i class="ace-icon fa fa-eye bigger-130"></i>
                                </a>

                                <a class="green" href="#">
                                    <i class="ace-icon fa fa-pencil bigger-130"></i>
                                </a>

                                <a class="red" href="#">
                                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                </a>
                                    <%--需要显示的类容--%>
                                <div id="dialog-message${i.count}" class="hide">
                                    <div class="tabbable">
                                        <ul class="nav nav-tabs padding-18">
                                            <li class="active">
                                                <a data-toggle="tab" href="#pictures1">
                                                    <i class="green"></i>
                                                    客户个人资料
                                                </a>
                                            </li>

                                            <li>
                                                <a data-toggle="tab" href="#pictures2">
                                                    <i class="orange"></i>
                                                    客户家庭资料
                                                </a>
                                            </li>

                                        </ul>

                                        <div class="tab-content no-border padding-24">
                                            <div id="pictures1" class="tab-pane in active">
                                                <table class="table table-bordered">
                                                    <tr>
                                                        <td class="col-md-2">7.1</td>
                                                        <td class="col-md-2">企业实际经营地址与注册地址是否一致</td>
                                                        <td class="col-md-3"/>
                                                        <td class="col-md-2">7.7</td>
                                                        <td class="col-md-2">仓库、存货</td>
                                                        <td class="col-md-3"/>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2">7.2</td>
                                                        <td class="col-md-2">相关证照</td>
                                                        <td class="col-md-3"/>
                                                        <td class="col-md-2">7.8</td>
                                                        <td class="col-md-2">办公环境</td>
                                                        <td class="col-md-3"/>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2">7.3</td>
                                                        <td class="col-md-2">租赁合同</td>
                                                        <td class="col-md-3"/>
                                                        <td class="col-md-2">7.9</td>
                                                        <td class="col-md-2">生产环境</td>
                                                        <td class="col-md-3"/>
                                                    </tr>

                                                    <tr>
                                                        <td class="col-md-2">7.4</td>
                                                        <td class="col-md-2">购销合同</td>
                                                        <td class="col-md-3"/>
                                                        <td class="col-md-2">7.10</td>
                                                        <td class="col-md-2">员工作业情况</td>
                                                        <td class="col-md-3"/>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2">7.5</td>
                                                        <td class="col-md-2">进出货单</td>
                                                        <td class="col-md-3"/>
                                                        <td class="col-md-2">7.11</td>
                                                        <td class="col-md-2">周边情况</td>
                                                        <td class="col-md-3"/>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2">7.6</td>
                                                        <td class="col-md-2">近半年计薪情况</td>
                                                        <td class="col-md-3"/>
                                                        <td class="col-md-2">7.12</td>
                                                        <td class="col-md-2">现场员工人数</td>
                                                        <td class="col-md-3"/>
                                                    </tr>
                                                </table>
                                            </div>
                                            <!-- /#home -->

                                            <div id="pictures2" class="tab-pane">
                                                <table class="table table-bordered">
                                                    <tr>
                                                        <td class="col-md-2">7.1</td>
                                                        <td class="col-md-2">企业实际经营地址与注册地址是否一致</td>
                                                        <td class="col-md-3"/>
                                                        <td class="col-md-2">7.7</td>
                                                        <td class="col-md-2">仓库、存货</td>
                                                        <td class="col-md-3"/>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2">7.2</td>
                                                        <td class="col-md-2">相关证照</td>
                                                        <td class="col-md-3"/>
                                                        <td class="col-md-2">7.8</td>
                                                        <td class="col-md-2">办公环境</td>
                                                        <td class="col-md-3"/>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2">7.3</td>
                                                        <td class="col-md-2">租赁合同</td>
                                                        <td class="col-md-3"/>
                                                        <td class="col-md-2">7.9</td>
                                                        <td class="col-md-2">生产环境</td>
                                                        <td class="col-md-3"/>
                                                    </tr>

                                                    <tr>
                                                        <td class="col-md-2">7.4</td>
                                                        <td class="col-md-2">购销合同</td>
                                                        <td class="col-md-3"/>
                                                        <td class="col-md-2">7.10</td>
                                                        <td class="col-md-2">员工作业情况</td>
                                                        <td class="col-md-3"/>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2">7.5</td>
                                                        <td class="col-md-2">进出货单</td>
                                                        <td class="col-md-3"/>
                                                        <td class="col-md-2">7.11</td>
                                                        <td class="col-md-2">周边情况</td>
                                                        <td class="col-md-3"/>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2">7.6</td>
                                                        <td class="col-md-2">近半年计薪情况</td>
                                                        <td class="col-md-3"/>
                                                        <td class="col-md-2">7.12</td>
                                                        <td class="col-md-2">现场员工人数</td>
                                                        <td class="col-md-3"/>
                                                    </tr>
                                                </table>
                                            </div>

                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="hidden-md hidden-lg">
                                <div class="inline position-relative">

                                    <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">

                                        <li>
                                            <a class="tooltip-success" data-rel="tooltip" title=""
                                               data-original-title="View">
                                            <span class="black">
                                                <i class="ace-icon fa fa-eye bigger-120"></i>
                                            </span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#" class="tooltip-success" data-rel="tooltip" title=""
                                               data-original-title="Edit">
                                            <span class="green">
                                                <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                            </span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#" class="tooltip-error" data-rel="tooltip" title=""
                                               data-original-title="Delete">
                                            <span class="red">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </td>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </div>
            <div class="row">
                <div class="col-xs-6">
                    <div class="dataTables_info" id="sample-table-2_info" role="status" aria-live="polite">
                        总条数:${data.totalElements}
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="dataTables_paginate paging_simple_numbers" id="sample-table-2_paginate">
                        <ul class="pagination">
                            <li class="paginate_button previous <c:if test="${data.firstPage}">disabled</c:if>"
                                aria-controls="sample-table-2" tabindex="0"
                                id="sample-table-2_previous"><a
                                    href="javascript:link('/survey/task?page=${data.number-1}')">上一页</a></li>

                            <c:forEach var="i" begin="1" end="${data.totalPages}">
                                <li class="paginate_button <c:if test="${i-1==data.number}">active</c:if>"
                                    aria-controls="sample-table-2" tabindex="0"><a
                                        href="javascript:link('/survey/task?page=${i-1}')">${i}</a></li>
                            </c:forEach>

                            <li class="paginate_button next <c:if test="${data.lastPage}">disabled</c:if>"
                                aria-controls="sample-table-2" tabindex="0"
                                id="sample-table-2_next"><a
                                    href="javascript:link('/survey/task?page=${data.number+1}')">下一页</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(function ($) {

        //override dialog's title function to allow for HTML titles
        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function (title) {
                var $title = this.options.title || '&nbsp;'
                if (("title_html" in this.options) && this.options.title_html == true)
                    title.html($title);
                else title.text($title);
            }
        }));
        //查看相信信息js
        $(".dialogMessage").on('click', function (e) {
            e.preventDefault();
            var name = this.name;
            var dialog = $("#dialog-message" + name).removeClass('hide').dialog({
                width:800,
                modal: true,
                title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-check'></i>模板预览</h4></div>",
                title_html: true,
                buttons: [
//                    {
//                        text: "取消",
//                        "class" : "btn btn-xs",
//                        click: function() {
//                            $( this ).dialog( "close" );
//                        }
//                    },
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