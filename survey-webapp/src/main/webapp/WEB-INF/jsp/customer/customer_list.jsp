<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<div class="row">
    <div class="col-xs-12">
        <div class="page-header">
            <h1>客户管理
                <small><i class="ace-icon fa fa-angle-double-right"></i> &nbsp;客户列表</small>
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
                            aria-label="Domain: activate to sort column ascending">姓名
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Domain: activate to sort column ascending">手机号码
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Domain: activate to sort column ascending">座机
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Price: activate to sort column ascending">身份证号
                        </th>

                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Price: activate to sort column ascending">公司
                        </th>

                        <th class="sorting" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1"
                            aria-label="Price: activate to sort column ascending">地址
                        </th>

                        <th class="sorting_disabled" rowspan="1" colspan="1" aria-label="">操作</th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:forEach var="item" varStatus="i" items="${data.content}" begin="0" end="10">
                        <td class="hidden-480">${item.name}</td>
                        <td class="hidden-480">${item.mobileNumber}</td>
                        <td class="hidden-480">${item.telephoneNumber}</td>
                        <td class="hidden-480">${item.idCard}</td>
                        <td class="hidden-480">${item.company}</td>
                        <td class="hidden-480">${item.address}</td>
                        <td>
                            <div class="hidden-sm hidden-xs action-buttons">
                                <a class="blue" href="javascript:link('/survey/surveyCustomerUI/${item.id}')">
                                    <i class="ace-icon fa fa-search-plus bigger-130" title="调查该客户"></i>
                                </a>

                                <a class="green" href="javascript:link('/survey/editCustomerUI/${item.id}')">
                                    <i class="ace-icon fa fa-pencil bigger-130" title="编辑"></i>
                                </a>

                                <a class="red" href="javascript:deleteById(${item.id})">
                                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
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
                                    href="javascript:link('/survey/customer?page=${data.number-1}')">上一页</a></li>

                            <c:forEach var="i" begin="1" end="${data.totalPages}">
                                <li class="paginate_button <c:if test="${i-1==data.number}">active</c:if>"
                                    aria-controls="sample-table-2" tabindex="0"><a
                                        href="javascript:link('/survey/customer?page=${i-1}')">${i}</a></li>
                            </c:forEach>

                            <li class="paginate_button next <c:if test="${data.lastPage}">disabled</c:if>"
                                aria-controls="sample-table-2" tabindex="0"
                                id="sample-table-2_next"><a
                                    href="javascript:link('/survey/customer?page=${data.number+1}')">下一页</a></li>
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
            url: "/survey/customer/" + id,
            success: function (data) {
                if (data.status) {
                    javascript:link('/survey/customer?page=${data.number}&name=${name}')
                }
            }
        });
    }
</script>