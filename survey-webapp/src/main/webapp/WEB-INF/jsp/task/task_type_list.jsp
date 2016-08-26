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


                        <th class="sorting_disabled" rowspan="1" colspan="1" aria-label="">操作</th>
                    </tr>
                    </thead>

                    <tbody>


                    <c:forEach var="item" varStatus="i" items="${data.content}">
                        <tr>
                            <td class="hidden-480">${item.name}</td>
                            <td class="hidden-480">
                                <c:forEach items="${item.photos}" var="photo" varStatus="j">
                                    ${photo.name}(${photo.pixel})
                                    <c:if test="${!j.last}">,</c:if>
                                </c:forEach>
                            </td>
                            <td class="hidden-480">
                                <c:forEach items="${item.templates}" var="template" varStatus="j">
                                    ${template.name}
                                    <c:if test="${!j.last}">,</c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <div class="action-buttons">


                                    <a class="green" href="javascript:link('/survey/task/editTypeUI/${item.id}')">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>

                                        <%--<a class="red" href="javascript:deleteById(${item.id})">--%>
                                        <%--<i class="ace-icon fa fa-trash-o bigger-130"></i>--%>
                                        <%--</a>--%>

                                </div>

                                <div class="hidden-md hidden-lg">
                                    <div class="inline position-relative">

                                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">

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
                                    href="javascript:link('/survey/task/taskType?page=${data.number-1}')">上一页</a></li>

                            <c:forEach var="i" begin="1" end="${data.totalPages}">
                                <li class="paginate_button <c:if test="${i-1==data.number}">active</c:if>"
                                    aria-controls="sample-table-2" tabindex="0"><a
                                        href="javascript:link('/survey/task/taskType?page=${i-1}')">${i}</a></li>
                            </c:forEach>

                            <li class="paginate_button next <c:if test="${data.lastPage}">disabled</c:if>"
                                aria-controls="sample-table-2" tabindex="0"
                                id="sample-table-2_next"><a
                                    href="javascript:link('/survey/task/taskType?page=${data.number+1}')">下一页</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    function deleteById(id) {
        $.ajax({
            type: "delete",
            url: "/survey/conf/" + id,
            success: function (data) {
                if (data.status) {
                    link("/survey/task/taskType?page=${data.number}")
                }
            }
        });
    }
</script>