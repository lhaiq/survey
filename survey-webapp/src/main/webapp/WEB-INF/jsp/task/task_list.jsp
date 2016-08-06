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
        <div class="table-header" style="background-color: #438eb9">
            列表
        </div>
        <div id="sample-table-2_wrapper" class="dataTables_wrapper form-inline no-footer">
            <div class="row">
                <div class="col-xs-6">
                    <div class="dataTables_length" id="sample-table-2_length">
                        <label>Display
                            <select id="page_size" name="sample-table-2_length" aria-controls="sample-table-2"
                                    class="form-control input-sm">
                                <option value="10" <c:if test="${data.size==10}">selected</c:if>>10</option>
                                <option value="20" <c:if test="${data.size==20}">selected</c:if>>20</option>
                                <option value="50" <c:if test="${data.size==50}">selected</c:if>>50</option>
                                <option value="100" <c:if test="${data.size==100}">selected</c:if>>100</option>
                            </select> records</label>
                    </div>
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

                    <c:forEach var="item" items="${data.content}">
                        <td class="hidden-480">${item.customerName}</td>
                        <td class="hidden-480">${item.account}</td>
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
                            <c:if test="${item.status==5}">重新调查</c:if>
                            <c:if test="${item.status==6}">废弃</c:if>
                        </td>
                        <td class="hidden-480">${item.point}</td>
                        <td>
                            <div class="hidden-sm hidden-xs action-buttons">
                                <%--<c:if test="${sessionScope.user.role==1}">--%>
                                    <a class="black" href="javascript:link('/survey/task/report/${item.id}')">
                                        <i class="ace-icon fa fa-eye bigger-130" title="查看报告"></i>
                                    </a>

                                <%--</c:if>--%>
                                <c:if test="${sessionScope.user.role==3}">
                                    <a class="blue" href="javascript:deleteById(${item.id})">
                                        <i class="ace-icon glyphicon glyphicon-repeat bigger-130" title="重新分配"></i>
                                    </a>
                                    <a class="green"
                                       <c:if test="${item.status==0}">href="javascript:link('/survey/editTaskUI/${item.id}')"</c:if>>
                                        <i class="ace-icon fa fa-pencil bigger-130" title="编辑"></i>
                                    </a>

                                    <a class="red"
                                       <c:if test="${item.status==0 or item.status==6}">href=href="javascript:deleteById(${item.id})"</c:if>>
                                        <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                    </a>
                                </c:if>

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
                                    href="javascript:link('/survey/core/task?page=${data.number-1}&size=${data.size}')">上一页</a></li>

                            <c:forEach var="i" begin="1" end="${data.totalPages}">
                                <li class="paginate_button <c:if test="${i-1==data.number}">active</c:if>"
                                    aria-controls="sample-table-2" tabindex="0"><a
                                        href="javascript:link('/survey/core/task?page=${i-1}&size=${data.size}')">${i}</a></li>
                            </c:forEach>

                            <li class="paginate_button next <c:if test="${data.lastPage}">disabled</c:if>"
                                aria-controls="sample-table-2" tabindex="0"
                                id="sample-table-2_next"><a
                                    href="javascript:link('/survey/core/task?page=${data.number+1}&size=${data.size}')">下一页</a></li>
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
                    link('/survey/core/task?page=${data.number}')
                }
            }
        });
    }

    $(document).ready(function () {
        $('#page_size').change(function () {
            var size = $(this).children('option:selected').val();//这就是selected的值
            link('/survey/core/task?page=${data.number}&size=' + size)
        })
    })

</script>