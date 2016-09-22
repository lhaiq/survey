<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>


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
                            aria-label="Price: activate to sort column ascending">评审员
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
                        <tr class="
                                <c:if test="${item.status==0}"></c:if>
                                <c:if test="${item.status==1}">active</c:if>
                                <c:if test="${item.status==2}">info</c:if>
                                <c:if test="${item.status==3}">warning</c:if>
                                <c:if test="${item.status==4}">success</c:if>
                                <c:if test="${item.status==5}">danger</c:if>
                        ">
                            <td class="hidden-480">${item.customerName}</td>
                            <td class="hidden-480">${item.account}</td>
                            <td class="hidden-480">${item.syndicName}</td>
                            <td class="hidden-480">${item.type}</td>
                            <td class="hidden-480"><fmt:formatDate value="${item.startTime}"
                                                                   pattern="yyyy-MM-dd HH:mm:SS"/></td>
                            <td class="hidden-480"><fmt:formatDate value="${item.endTime}"
                                                                   pattern="yyyy-MM-dd HH:mm:SS"/></td>
                            <td class="hidden-480">
                                <c:if test="${item.status==8}">待分配</c:if>
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
                                <div class="action-buttons">
                                    <c:if test="${sessionScope.user.role==1}">
                                        <a class="black" href="javascript:link('/survey/task/report/${item.id}')">
                                            <i class="ace-icon fa fa-eye bigger-130" title="查看报告"></i>
                                        </a>

                                    </c:if>
                                    <c:if test="${sessionScope.user.role==3}">
                                        <a class="blue" href="javascript:void(0)"
                                           onclick="reallocate(${item.customerId})">
                                            <i class="ace-icon glyphicon glyphicon-repeat bigger-130" title="重新分配"></i>
                                        </a>
                                        <a class="green"
                                           <c:if test="${item.status==0 or item.status==8}">href="javascript:link('/survey/editTaskUI/${item.id}')"</c:if>
                                           <c:if test="${item.status!=0 and item.status!=8}">style="opacity: 0.2"</c:if>
                                                >
                                            <i class="ace-icon fa fa-pencil bigger-130" title="编辑"></i>
                                        </a>

                                        <a
                                                <c:if test="${item.status==0 or item.status==6}">href=href="javascript:deleteById(${item.id})"</c:if>
                                                <c:if test="${item.status!=0 && item.status!=6}">style="opacity: 0.2"</c:if>

                                                >
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
                                    href="javascript:link('/survey/core/task?page=${data.number-1}&size=${data.size}&customerName=${taskVO.customerName}&status=${taskVO.status}')">上一页</a>
                            </li>

                            <c:forEach var="i" begin="1" end="${data.totalPages}">
                                <li class="paginate_button <c:if test="${i-1==data.number}">active</c:if>"
                                    aria-controls="sample-table-2" tabindex="0"><a
                                        href="javascript:link('/survey/core/task?page=${i-1}&size=${data.size}&customerName=${taskVO.customerName}&status=${taskVO.status}')">${i}</a>
                                </li>
                            </c:forEach>

                            <li class="paginate_button next <c:if test="${data.lastPage}">disabled</c:if>"
                                aria-controls="sample-table-2" tabindex="0"
                                id="sample-table-2_next"><a
                                    href="javascript:link('/survey/core/task?page=${data.number+1}&size=${data.size}&customerName=${taskVO.customerName}&status=${taskVO.status}')">下一页</a>
                            </li>
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

    function reallocate(customerId) {
        bootbox.confirm("确定重新分配?", function (result) {
            if (result) {
                link('/survey/reallocateTaskUI/' + customerId)
            }
        });
    }

    function search() {
        var size = $("#page_size").children('option:selected').val();//这就是selected的值
        var name = $("#input_search").val();
        var status = $("#task_status").children('option:selected').val();
        console.log(name + "----" + status)
        link('/survey/core/task?page=${data.number}&size=' + size + "&customerName=" + name + "&status=" + status)
    }

    $(document).ready(function () {
        $('#page_size').change(function () {
            search();
        });

        $('#task_status').change(function () {
            search();
        });

    })

</script>