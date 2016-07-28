<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>


<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <div class="space-6"></div>

        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <!-- #section:pages/invoice -->
                <div class="widget-box transparent">
                    <div class="widget-header widget-header-large">
                        <h2 class="widget-title grey lighter">
                            <i class="ace-icon fa fa-list-alt"></i>
                            汇融易实地调研报告
                        </h2>

                        <div class="widget-toolbar">
                            <div class="widget-toolbar no-border invoice-info">
                                <span class="invoice-info-label">调查人员:</span>
                                <span class="red">小平</span>

                                <br/>
                                <span class="invoice-info-label">调研日期:</span>
                                <span class="blue">2016-08-08</span>
                            </div>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main padding-24">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="row">
                                        <div class="col-xs-11 label label-lg label-info arrowed-in arrowed-right">
                                            <b>实时定位地址</b>
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <img height="250"
                                             src="http://api.map.baidu.com/staticimage?center=116.403874,39.914889&width=400&height=300&zoom=11&markers=116.288891,40.00426"/>
                                    </div>
                                </div>
                                <!-- /.col -->

                                <div class="col-sm-6">
                                    <div class="row">
                                        <div class="col-xs-11 label label-lg label-success arrowed-in arrowed-right">
                                            <b>调研人员设置地址</b>
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <img height="250"
                                             src="http://api.map.baidu.com/staticimage?center=116.403874,39.914889&width=400&height=300&zoom=11&markers=116.288891,40.00426"/>
                                    </div>
                                </div>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.col -->


                    <div class="row">
                        <%--<div class="col-sm-1"></div>--%>


                        <div><h3 class="widget-title grey lighter">调研报告及结论</h3></div>
                        <div class="hr hr8  hr-dotted"></div>

                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> 客户名</div>

                                <div class="profile-info-value">
                                    <span class="editable" id="username">alexdoe</span>
                                </div>
                            </div>


                            <div class="profile-info-row">
                                <div class="profile-info-name"> 调查类型</div>

                                <div class="profile-info-value">
                                    <span class="editable" id="age">房贷</span>
                                </div>
                            </div>

                            <div class="profile-info-row">
                                <div class="profile-info-name"> Joined</div>

                                <div class="profile-info-value">
                                    <span class="editable" id="signup">2010/06/20</span>
                                </div>
                            </div>

                            <div class="profile-info-row">
                                <div class="profile-info-name"> Last Online</div>

                                <div class="profile-info-value">
                                    <span class="editable" id="login">3 hours ago</span>
                                </div>
                            </div>

                        </div>
                    </div>


                    <div class="row">
                        <div><h3 class="widget-title grey lighter">调研照片</h3></div>
                        <div class="hr hr8  hr-dotted"></div>
                        <div id="user-profile-2" class="user-profile">
                            <div class="tabbable">
                                <ul class="nav nav-tabs padding-18">
                                    <li class="active">
                                        <a data-toggle="tab" href="#pictures1">
                                            <i class="green ace-icon fa fa-picture-o bigger-120"></i>
                                            现场情况
                                        </a>
                                    </li>

                                    <li>
                                        <a data-toggle="tab" href="#pictures2">
                                            <i class="orange ace-icon fa fa-picture-o bigger-120"></i>
                                            门面
                                        </a>
                                    </li>

                                </ul>

                                <div class="tab-content no-border padding-24">
                                    <div id="pictures1" class="tab-pane in active">
                                        <div>
                                            <ul class="ace-thumbnails clearfix">
                                                <!-- #section:pages/gallery -->
                                                <li>
                                                    <a href="../assets/images/gallery/image-1.jpg" title="Photo Title"
                                                       data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-1.jpg"/>

                                                        <div class="text">
                                                            <div class="inner">点击查看</div>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="../assets/images/gallery/image-2.jpg" data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-2.jpg"/>

                                                        <div class="text">
                                                            <div class="inner">Sample Caption on Hover</div>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="../assets/images/gallery/image-3.jpg" data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-3.jpg"/>

                                                        <div class="text">
                                                            <div class="inner">Sample Caption on Hover</div>
                                                        </div>
                                                    </a>
                                                </li>


                                                <li>
                                                    <a href="../assets/images/gallery/image-6.jpg" data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-6.jpg"/>
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="../assets/images/gallery/image-1.jpg" data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-1.jpg"/>
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="../assets/images/gallery/image-2.jpg" data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-2.jpg"/>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- /#home -->

                                    <div id="pictures2" class="tab-pane">
                                        <div>
                                            <ul class="ace-thumbnails clearfix">
                                                <!-- #section:pages/gallery -->
                                                <li>
                                                    <a href="../assets/images/gallery/image-1.jpg" title="Photo Title"
                                                       data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-1.jpg"/>

                                                        <div class="text">
                                                            <div class="inner">点击查看</div>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="../assets/images/gallery/image-2.jpg" data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-2.jpg"/>

                                                        <div class="text">
                                                            <div class="inner">Sample Caption on Hover</div>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="../assets/images/gallery/image-3.jpg" data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-3.jpg"/>

                                                        <div class="text">
                                                            <div class="inner">Sample Caption on Hover</div>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="../assets/images/gallery/image-4.jpg" data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-4.jpg"/>
                                                    </a>
                                                </li>

                                                <li>
                                                    <div>
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-5.jpg"/>
                                                    </div>
                                                </li>

                                                <li>
                                                    <a href="../assets/images/gallery/image-6.jpg" data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-6.jpg"/>
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="../assets/images/gallery/image-1.jpg" data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-1.jpg"/>
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="../assets/images/gallery/image-2.jpg" data-rel="colorbox">
                                                        <img width="150" height="150" alt="150x150"
                                                             src="../assets/images/gallery/thumb-2.jpg"/>
                                                    </a>
                                                </li>

                                            </ul>
                                        </div>
                                    </div>
                                    <!-- /#feed -->

                                    <div id="pictures3" class="tab-pane">

                                    </div>
                                    <!-- /#friends -->

                                    <div id="pictures4" class="tab-pane">

                                    </div>
                                    <!-- /#pictures -->
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div><h3 class="widget-title grey lighter">调研表格</h3></div>
                        <div class="hr hr8  hr-dotted"></div>
                        <div id="user-profile-3" class="user-profile">
                            <div class="tabbable">
                                <ul class="nav nav-tabs padding-18">
                                    <li class="active">
                                        <a data-toggle="tab" href="#template1">
                                            <i class="green ace-icon fa bigger-120"></i>
                                            客户个人资料
                                        </a>
                                    </li>

                                    <li>
                                        <a data-toggle="tab" href="#template2">
                                            <i class="orange ace-icon fa bigger-120"></i>
                                            客户公司资料
                                        </a>
                                    </li>

                                </ul>

                                <div class="tab-content no-border padding-24">
                                    <div id="template1" class="tab-pane in active">
                                        <table class="table table-bordered">
                                            <tr>
                                                <td class="col-md-2">7.1</td>
                                                <td class="col-md-2">企业实际经营地址与注册地址是否一致</td>
                                                <td class="col-md-3">是</td>
                                                <td class="col-md-2">7.7</td>
                                                <td class="col-md-2">仓库、存货</td>
                                                <td class="col-md-3">1000</td>
                                            </tr>
                                            <tr>
                                                <td class="col-md-2">7.2</td>
                                                <td class="col-md-2">相关证照</td>
                                                <td class="col-md-3"/>
                                                <td class="col-md-2">7.8</td>
                                                <td class="col-md-2">办公环境</td>
                                                <td class="col-md-3">好的</td>
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
                                                <td class="col-md-3">很好</td>
                                                <td class="col-md-2">7.12</td>
                                                <td class="col-md-2">现场员工人数</td>
                                                <td class="col-md-3">100人</td>
                                            </tr>
                                        </table>
                                    </div>
                                    <!-- /#home -->

                                    <div id="template2" class="tab-pane">
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


                    <div class="row">
                        <div><h3 class="widget-title grey lighter">调研录音</h3></div>
                        <div class="hr hr8  hr-dotted"></div>
                        <div class="profile-users clearfix">
                            <div class="itemdiv memberdiv">
                                <div class="inline pos-rel">
                                    <div class="user">
                                        <a href="#">
                                            <img src="../assets/avatars/sound.png" alt="Bob Doe's avatar"/>
                                        </a>
                                    </div>

                                    <div class="body">
                                        <div class="name">
                                            <a href="#">
                                                <span class="user-status status-online"></span>
                                                录音1
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="profile-users clearfix">
                                <div class="itemdiv memberdiv">
                                    <div class="inline pos-rel">
                                        <div class="user">
                                            <a href="#">
                                                <img src="../assets/avatars/sound.png" alt="Bob Doe's avatar"/>
                                            </a>
                                        </div>

                                        <div class="body">
                                            <div class="name">
                                                <a href="#">
                                                    <span class="user-status status-online"></span>
                                                    录音2
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="profile-users clearfix">
                                    <div class="itemdiv memberdiv">
                                        <div class="inline pos-rel">
                                            <div class="user">
                                                <a href="#">
                                                    <img src="../assets/avatars/sound.png" alt="Bob Doe's avatar"/>
                                                </a>
                                            </div>

                                            <div class="body">
                                                <div class="name">
                                                    <a href="#">
                                                        <span class="user-status status-online"></span>
                                                        录音3
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </div>

                            </div>

                        </div>
                    </div>
                    <div class="row">
                        <div><h3 class="widget-title grey lighter">评价</h3></div>
                        <div class="hr hr8  hr-dotted"></div>
                        <div>
                            <textarea
                                    class="autosize-transition form-control"></textarea>
                        </div>
                        <br/>
                        <div class="col-md-offset-3 col-md-9">
                            <button class="btn btn-info" type="button">
                                <i class="ace-icon fa fa-thumbs-o-up bigger-110"></i> 通过
                            </button>

                            &nbsp; &nbsp; &nbsp;
                            <button class="btn" type="reset">
                                <i class="ace-icon fa fa-thumbs-o-down bigger-110"></i> 拒绝
                            </button>
                        </div>
                    </div>
                    <div class="row">

                    </div>
                </div>

                <!-- /section:pages/invoice -->
            </div>
        </div>

        <!-- PAGE CONTENT ENDS -->

    </div>
    <!-- /.row -->


    <%--查看图片js--%>
    <script type="text/javascript">
        jQuery(function ($) {
            var $overflow = '';
            var colorbox_params = {
                rel: 'colorbox',
                reposition: true,
                scalePhotos: true,
                scrolling: false,
                previous: '<i class="ace-icon fa fa-arrow-left"></i>',
                next: '<i class="ace-icon fa fa-arrow-right"></i>',
                close: '&times;',
                current: '{current} of {total}',
                maxWidth: '100%',
                maxHeight: '100%',
                onOpen: function () {
                    $overflow = document.body.style.overflow;
                    document.body.style.overflow = 'hidden';
                },
                onClosed: function () {
                    document.body.style.overflow = $overflow;
                },
                onComplete: function () {
                    $.colorbox.resize();
                }
            };
            $('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
            $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange'></i>");//let's add a custom loading icon
        })
    </script>