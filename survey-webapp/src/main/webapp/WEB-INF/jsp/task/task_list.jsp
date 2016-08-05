<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>


<div class="row">
    <div class="col-xs-12">
        <div class="page-header">
            <h1>调查任务管理
                <small><i class="ace-icon fa fa-angle-double-right"></i> &nbsp;调查任务列表</small>
            </h1>
        </div>
        <div class="table-header">
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
                    <div id="sample-table-2_filter" class="dataTables_filter"><label>Search:<input type="search" class="form-control input-sm" aria-controls="sample-table-2">
                    </label></div>
                </div>
            </div>
            <div class="row">
                <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable no-footer"
                       role="grid" aria-describedby="sample-table-2_info">
                    <thead>
                    <tr role="row">

                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Domain: activate to sort column ascending">客户名
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Price: activate to sort column ascending">调查员
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Price: activate to sort column ascending">调查类型
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Price: activate to sort column ascending">开始时间
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Price: activate to sort column ascending">结束时间
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Price: activate to sort column ascending">状态
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Price: activate to sort column ascending">调查要点
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
                        <td class="hidden-480">${item.customerName}</td>
                        <td class="hidden-480">${item.nickName}</td>
                        <td class="hidden-480">${item.type}</td>
                        <td class="hidden-480"><fmt:formatDate value="${item.startTime}"
                                                               pattern="yyyy-MM-dd HH:mm:SS"/></td>
                        <td class="hidden-480"><fmt:formatDate value="${item.endTime}"
                                                               pattern="yyyy-MM-dd HH:mm:SS"/></td>
                        <td class="hidden-480">
                            <c:if test="${item.status==0}">待调查</c:if>
                            <c:if test="${item.status==1}">开始调查</c:if>
                            <c:if test="${item.status==2}">待审核</c:if>
                            <c:if test="${item.status==3}">审核中</c:if>
                            <c:if test="${item.status==4}">审核成功</c:if>
                            <c:if test="${item.status==5}">审核失败</c:if>
                        </td>
                        <td class="hidden-480">${item.point}</td>
                        <td>
                            <div class="hidden-sm hidden-xs action-buttons">

                                <a class="black" href="javascript:link('/survey/core/task/report/${item.id}')">
                                    <i class="ace-icon fa fa-eye bigger-130"></i>
                                </a>

                                <a class="green" href="javascript:link('/survey/task/${item.id}')">
                                    <i class="ace-icon fa fa-pencil bigger-130" title="编辑"></i>
                                </a>

                                <a class="red" href="javascript:deleteById(${item.id})">
                                    <i class="ace-icon glyphicon glyphicon-repeat bigger-130"></i>
                                </a>

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
                                    href="javascript:link('/survey/core/task?page=${data.number-1}')">上一页</a></li>

                            <c:forEach var="i" begin="1" end="${data.totalPages}">
                                <li class="paginate_button <c:if test="${i-1==data.number}">active</c:if>"
                                    aria-controls="sample-table-2" tabindex="0"><a
                                        href="javascript:link('/survey/core/task?page=${i-1}')">${i}</a></li>
                            </c:forEach>

                            <li class="paginate_button next <c:if test="${data.lastPage}">disabled</c:if>"
                                aria-controls="sample-table-2" tabindex="0"
                                id="sample-table-2_next"><a
                                    href="javascript:link('/survey/core/task?page=${data.number+1}')">下一页</a></li>
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
            url: "/survey/task/" + id,
            success: function (data) {
                if (data.status) {
                    javascript:link('/survey/task?page=${data.number}')
                }
            }
        });
    }
</script>