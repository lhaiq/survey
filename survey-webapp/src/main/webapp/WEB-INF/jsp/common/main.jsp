<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	//每5分钟动态刷新实时信息

//	setInterval(function(){
//		var newTime = $("#newTime").val();
//		$.ajax({
//			dataType : "json",
//			url : "rest/menu/getChartsAsyn?time="+newTime,
//			async: false,
//			success : function(data){
//				for(var i = 0 ;i < data.length ;i++){
//					var strOne = "<div class='itemdiv dialogdiv'>" +
//							"<div class='user'>"+
//							"<img alt='' src='assets/avatars/"+data[i].checkType+".png'/></div>"+
//							"<div class='body'>"+
//							"<div class='time'>"+
//							"<i class='ace-icon fa fa-clock-o'></i> <span class='green'>"+data[i].errorTime+"</span>"+
//							"</div>"+
//							"<div class='name'>"+
//							"<a href='#'>"+data[i].dataName+"</a>"+
//							"</div>";
//					var strWds = "<div class='text'>水情数据："+data[i].warnMesage+"</div>";
//					var strTmr = "<div class='text'>电量数据："+data[i].warnMesage+"</div>";
//					var strTwo = "</div></div>";
//					if(data[i].dataType == 1)
//					{
//						$(strOne+strWds+strTwo).insertBefore(".scroll-content div:first");
//					}else{
//						$(strOne+strTmr+strTwo).insertBefore(".scroll-content div:first");
//					}
//					//查询出最新数据得到最新时间放入到newTime里
//					if(i == (data.length -1))
//					{
//						$("#newTime").val(data[i].errorTime)
//					}
//				}
//			}
//		});
//	},20000);

</script>
<!-- /section:settings.box 页面主要信息开始啦-->
<div class="page-content-area">
	<div class="page-header">
		<h1>
			控制面板 <small> <i class="ace-icon fa fa-angle-double-right"></i>
			概述 &amp; 统计
		</small>
		</h1>
	</div>
	<!-- /.page-header -->

	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS 页面内容开始-->

			<div class="row">
				<div class="space-6"></div>
				<div class="col-sm-5">
					<div class="widget-box">
						<div
								class="widget-header widget-header-flat widget-header-small">
							<h5 class="widget-title">
								<i class="ace-icon fa fa-signal"></i> 数据错误率
							</h5>

							<div class="widget-toolbar no-border">
								<div class="inline dropdown-hover">
									<button class="btn btn-minier btn-primary"> <span id='time'>今年</span> <i class="ace-icon fa fa-angle-down icon-on-right bigger-110"></i>
									</button>

									<ul id="bCount" class="dropdown-menu dropdown-menu-right dropdown-125 dropdown-lighter dropdown-close dropdown-caret">
										<li><a onclick="changeDataCharts(2016)"  class="blue"> <i class="ace-icon fa fa-caret-right bigger-110">&nbsp;</i>
											今年
										</a></li>

										<li><a onclick="changeDataCharts(2015)"   class="blue"> <i class="ace-icon fa fa-caret-right bigger-110 invisible">&nbsp;</i>
											去年
										</a></li>
									</ul>
								</div>
							</div>
						</div>

						<div class="widget-body">
							<div class="widget-main">
								<!-- #section:plugins/charts.flotchart -->
								<div id="piechart-placeholder"></div>

								<!-- /section:plugins/charts.flotchart -->
								<div class="hr hr8 hr-double"></div>

								<div class="clearfix">
									<!-- #section:custom/extra.grid -->
									<div class="grid3">
												<span class="grey"> <i class="ace-icon fa fa-exclamation-triangle fa-2x"></i> &nbsp; 警告
												</span>
										<h4 id="warning" class="bigger pull-right">${chartsMain.warning}</h4>
									</div>

									<div class="grid3">
												<span class="grey"> <i class="ace-icon fa fa-bullhorn fa-2x purple"></i> &nbsp; 一般严重
												</span>
										<h4 id="normal" class="bigger pull-right">${chartsMain.normal}</h4>
									</div>

									<div class="grid3">
												<span class="grey"> <i class="ace-icon fa fa-bolt fa-2x red"></i> &nbsp; 严重
												</span>
										<h4 id="error" class="bigger pull-right">${chartsMain.error}</h4>
									</div>

									<!-- /section:custom/extra.grid -->
								</div>
							</div>
							<!-- /.widget-main -->
						</div>
						<!-- /.widget-body -->
					</div>
					<!-- /.widget-box -->
				</div>


				<%--柱状图--%>
				<div class="col-sm-7">
					<div class="widget-box transparent">
						<div class="widget-header widget-header-flat">
							<h4 class="widget-title lighter">
								<i class="ace-icon fa fa-signal"></i> 水情数据
							</h4>

							<div class="widget-toolbar">
								<a href="#" data-action="collapse"> <i
										class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>

							<div class="dropdown widget-toolbar no-border">
								<%--	<button class="btn btn-xs btn-light bigger">
                                        <i class="ace-icon fa fa-arrow-left"></i>
                                        切换
                                    </button>
--%>
								<button  class="btn btn-xs bigger btn-yellow dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<span id='wds'>选择</span>
									<i class="ace-icon fa fa-chevron-down icon-on-right"></i>
								</button>

								<ul id="bWds" class="dropdown-menu dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
									<%--<li><a href="rest/menu/changeWdsData?wdsType=SW&tmrType=DAY">水位</a></li>
									<li><a href="rest/menu/changeWdsData?wdsType=RK&tmrType=DAY">入库流量</a></li>
									<li><a href="rest/menu/changeWdsData?wdsType=CK&tmrType=DAY">出库流量</a></li>
									<li><a href="rest/menu/changeWdsData?wdsType=FD&tmrType=DAY">发电流量</a></li>--%>
									<li><a onclick="changeWdsCharts('SW')">水位</a></li>
									<li><a onclick="changeWdsCharts('RK')">入库流量</a></li>
									<li><a onclick="changeWdsCharts('CK')">出库流量</a></li>
									<li><a onclick="changeWdsCharts('FD')">发电流量</a></li>
								</ul>
							</div>
						</div>

						<div class="widget-body">
							<div class="widget-main padding-4">
								<div id="sq-axis" style="height: 225px;width: 100%"></div>
							</div>
							<!-- /.widget-main -->
						</div>
						<!-- /.widget-body -->
					</div>
					<!-- /.widget-box -->
				</div>

				<!-- /.col -->
			</div>
			<!-- /.row -->

			<!-- #section:custom/extra.hr -->
			<div class="hr hr32 hr-dotted"></div>

			<!-- /section:custom/extra.hr -->
			<div class="row">
				<div class="col-sm-7">
					<div class="widget-box transparent">
						<div class="widget-header widget-header-flat">
							<h4 class="widget-title lighter">
								<i class="ace-icon fa fa-signal"></i> 电量数据
							</h4>

							<div class="widget-toolbar">
								<a href="#" data-action="collapse"> <i
										class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>

							<div class="widget-toolbar no-border">
								<%--<button class="btn btn-xs btn-light bigger">
                                    <i class="ace-icon fa fa-arrow-left"></i>
                                    切换
                                </button>--%>

									<button  class="btn btn-xs bigger btn-yellow dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										<span id='tmr'>选择</span>
										<i class="ace-icon fa fa-chevron-down icon-on-right"></i>
									</button>

									<ul id="bTmr" class="dropdown-menu dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
									<%--<li><a href="rest/menu/changeWdsData?tmrType=DAY&wdsType=SW">日发电量</a></li>--%>
									<%--<li><a href="rest/menu/changeWdsData?tmrType=MONTH&wdsType=SW">月发电量</a></li>--%>
									<%--<li><a href="rest/menu/changeWdsData?tmrType=YEAR&wdsType=SW">年发电量</a></li>--%>
									<%--<li><a href="rest/menu/changeWdsData?tmrType=LIUYU&wdsType=SW">流域发电量</a></li>--%>

									<li><a onclick="changeTmrCharts('DAY')">日发电量</a></li>
									<li><a onclick="changeTmrCharts('MONTH')">月发电量</a></li>
									<li><a onclick="changeTmrCharts('YEAR')">年发电量</a></li>
									<%--<li><a onclick="changeTmrCharts('LIUYU')">流域发电量</a></li>--%>

								</ul>
							</div>
						</div>

						<div class="widget-body">
							<div class="widget-main padding-4">
								<div id="dl-axis" style="height: 255px;width: 100%"></div>
							</div>
							<!-- /.widget-main -->
						</div>
						<!-- /.widget-body -->
					</div>
					<!-- /.widget-box -->
				</div>
				<div class="col-sm-5">
					<div class="widget-box transparent">
						<div class="widget-header widget-header-flat">
							<h4 class="widget-title lighter">
								<i class="ace-icon fa fa-star orange"></i> 监控状态
							</h4>

							<div class="widget-toolbar">
								<a href="#" data-action="collapse"> <i
										class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>

						<div class="widget-body">
							<div class="widget-main no-padding">
								<table class="table table-bordered table-striped">
									<thead class="thin-border-bottom">
									<tr>
										<th ><i class="ace-icon fa fa-caret-right blue"></i>名称 </th>
										<th><i class="ace-icon fa fa-caret-right blue"></i>数量 </th>
										<th class="hidden-480"><i class="ace-icon fa fa-caret-right blue"></i>监控状态</th>
									</tr>
									</thead>

									<tbody>
									<tr>
										<td>电量监控数据</td>
										<td><b class="green">${chartsState.tmrNormal}</b></td>
										<td class="hidden-480"><span class="label">默认</span></td>
									</tr>
									<tr>
										<td>电量警告数据</td>
										<c:if test="${chartsState.tmrWarning == 0}">
											<td><b class="green">0</b></td>
										</c:if>
										<c:if test="${chartsState.tmrWarning != 0}">
											<td><b class="green"><a href="rest/menu/getDetailInfo?dataType=2&leveType=1"> ${chartsState.tmrWarning}</a> </b></td>
										</c:if>
										<td class="hidden-480"><span class="label label-warning"><i class="ace-icon fa fa-exclamation-triangle bigger-120"></i>警告</span></td>
									</tr>
									<tr>
										<td>电量错误数据</td>
										<c:if test="${chartsState.tmrError == 0}">
											<td><b class="green">0</b></td>
										</c:if>
										<c:if test="${chartsState.tmrError != 0}">
											<td><b class="green"><a href="rest/menu/getDetailInfo?dataType=2&leveType=2"> ${chartsState.tmrError}</a> </b></td>
										</c:if>
										<td class="hidden-480"><span class="label label-danger arrowed-in">严重</span></td>
									</tr>
									<tr>
										<td>水情监控数据</td>
										<td><b class="green">${chartsState.wdsNormal}</b></td>
										<td class="hidden-480"><span class="label">默认</span></td>
									</tr>
									<tr>
										<td>水情警告数据</td>
										<c:if test="${chartsState.wdsWarning == 0}">
											<td><b class="green">0</b></td>
										</c:if>
										<c:if test="${chartsState.wdsWarning != 0}">
											<td><b class="green"><a href="rest/menu/getDetailInfo?dataType=1&leveType=1"> ${chartsState.wdsWarning}</a> </b></td>
										</c:if>
										<td class="hidden-480"><span class="label label-warning"><i class="ace-icon fa fa-exclamation-triangle bigger-120"></i>警告</span></td>
									</tr>
									<tr>
										<td>水情错误数据</td>
										<c:if test="${chartsState.wdsError == 0}">
											<td><b class="green">0</b></td>
										</c:if>
										<c:if test="${chartsState.wdsError != 0}">
											<td><b class="green"><a href="rest/menu/getDetailInfo?dataType=1&leveType=2"> ${chartsState.wdsError}</a> </b></td>
										</c:if>
										<td class="hidden-480"><span class="label label-danger arrowed-in">严重</span></td>
									</tr>
									</tbody>
								</table>
							</div>
							<!-- /.widget-main -->
						</div>
						<!-- /.widget-body -->
					</div>
					<!-- /.widget-box -->
				</div>
				<!-- /.col -->


				<!-- /.col -->
			</div>
			<!-- /.row -->

			<div class="hr hr32 hr-dotted"></div>

			<div class="row">
				<div class="col-sm-6">
					<div class="widget-box transparent" id="recent-box">
						<div class="widget-header">
							<h4 class="widget-title lighter smaller">
								<i class="ace-icon fa fa-rss orange"></i>监控类型错误率
							</h4>

						</div>

						<div class="widget-body">
							<div class="widget-main padding-4">
								<div class="tab-content padding-8">
									<div id="task-tab" class="tab-pane active">
										<h5 class="smaller lighter green">
											<i class="ace-icon fa fa-list"></i>检查类型
										</h5>

										<!-- #section:pages/dashboard.tasks -->
										<ul id="tasks" class="item-list">

											<li class="item-orange clearfix ">
												<label class="ace-icon fa fa-angle-right bigger-110"><span class="lbl">&nbsp; 和上小时数据比较</span></label>
												<div class="pull-right easy-pie-chart percentage" data-size="50" data-color="#ECCB51" data-percent="${chartsType.checkHour}">
													<span class="percent">${chartsType.checkHour}%</span>
												</div>
											</li>
											<li class="item-red clearfix">
												<label class="ace-icon fa fa-angle-right bigger-110"><span class="lbl">&nbsp; 和上一天数据比较</span></label>
												<div class="pull-right easy-pie-chart percentage" data-size="50" data-color="#ECCB71" data-percent="${chartsType.checkDay}">
													<span class="percent">${chartsType.checkDay}%</span>
												</div>
											</li>
											<li class="item-default clearfix">
												<label class="ace-icon fa fa-angle-right bigger-110"><span class="lbl">&nbsp; 大于/小于/等于</span></label>
												<div class="pull-right easy-pie-chart percentage" data-size="50" data-color="#ECCB71" data-percent="${chartsType.checkGt}">
													<span class="percent">${chartsType.checkGt}%</span>
												</div>
											</li>
											<li class="item-blue clearfix">
												<label class="ace-icon fa fa-angle-right bigger-110"><span class="lbl">&nbsp; 超出某个范围</span></label>
												<div class="pull-right easy-pie-chart percentage" data-size="50" data-color="#ECCB71" data-percent="${chartsType.checkDataScop}">
													<span class="percent">${chartsType.checkDataScop}%</span>
												</div>
											</li>
											<li class="item-pink clearfix">
												<label class="ace-icon fa fa-angle-right bigger-110"><span class="lbl">&nbsp; 数据范围变幅大小</span></label>
												<div class="pull-right easy-pie-chart percentage" data-size="50" data-color="#ECCB71" data-percent="${chartsType.checkDataScopRange}">
													<span class="percent">${chartsType.checkDataScopRange}%</span>
												</div>
											</li>
											<li class="item-purple clearfix">
												<label class="ace-icon fa fa-angle-right bigger-110"><span class="lbl">&nbsp; 根据其他数据比较</span></label>
												<div class="pull-right easy-pie-chart percentage" data-size="50" data-color="#ECCB71" data-percent="${chartsType.checkOtherData}">
													<span class="percent">${chartsType.checkOtherData}%</span>
												</div>
											</li>
											<li class="item-black  clearfix">
												<label class="ace-icon fa fa-angle-right bigger-110"><span class="lbl">&nbsp; 零点与二十四点数据比较</span></label>
												<div class="pull-right easy-pie-chart percentage" data-size="50" data-color="#ECCB71" data-percent="${chartsType.checkTimeOut}">
													<span class="percent">${chartsType.checkTimeOut}%</span>
												</div>
											</li>
											<li class="item-brownamber  clearfix">
												<label class="ace-icon fa fa-angle-right bigger-110"><span class="lbl">&nbsp; 数据缺失</span></label>
												<div class="pull-right easy-pie-chart percentage" data-size="50" data-color="#ECCB71" data-percent="${chartsType.checkOther}">
													<span class="percent">${chartsType.checkOther}%</span>
												</div>
											</li>
										</ul>

										<!-- /section:pages/dashboard.tasks -->
									</div>


									<!-- /.#member-tab -->


								</div>
							</div>
							<!-- /.widget-main -->
						</div>
						<!-- /.widget-body -->
					</div>
					<!-- /.widget-box -->
				</div>
				<!-- /.col -->

				<div class="col-sm-6">
					<div class="widget-box">
						<div class="widget-header">
							<h4 class="widget-title lighter smaller">
								<i class="ace-icon fa fa-comment blue"></i> 实时信息
							</h4>
						</div>

						<div class="widget-body">

							<div class="widget-main no-padding">
								<!-- #section:pages/dashboard.conversations -->
								<div class="dialogs" id="allInfo">
									<c:forEach var="chartsAsyn" items="${chartsAsynList}" varStatus="status">
										<div class="itemdiv dialogdiv">
											<div class="user">
												<img alt="" src="assets/avatars/${chartsAsyn.checkType}.png"/></div>
											<div class="body">
												<div class="time">
													<i class="ace-icon fa fa-clock-o"></i> <span class="green"><fmt:formatDate value="${chartsAsyn.errorTime}" pattern="yyyy-MM-dd  HH:mm:ss.SSS" /></span>
												</div>
												<div class="name">
													<a href="rest/menu/getDetailInfo?id=${chartsAsyn.id}">${chartsAsyn.dataName}</a>
												</div>
												<c:if test="${chartsAsyn.dataType == 1}">
													<div class="text">水情数据：${chartsAsyn.warnMesage}</div>
												</c:if>
												<c:if test="${chartsAsyn.dataType == 2}">
													<div class="text">电量数据：${chartsAsyn.warnMesage}</div>
												</c:if>
											</div>
										</div>
										<c:if test="${status.first == true}"><input id="newTime" type="hidden" value="${chartsAsyn.errorTime}"/></c:if>
										<%--<c:if test="${status.first == true}"><input id="newTime" type="hidden" value="2016-04-27  15.32.24.541"/></c:if>--%>
									</c:forEach>
								</div>
							</div>
							<!-- /.widget-main -->
						</div>
						<!-- /.widget-body -->
					</div>
					<!-- /.widget-box -->
				</div>
				<!-- /.col -->
			</div>

			<!-- /.row -->

			<!-- PAGE CONTENT ENDS -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</div>
<!-- /.page-content-area -->
<%--调用公共JS上--%>
<jsp:include page="../js/base/common_top.jsp" />

<script type="text/javascript">




//	function getData(){
//		setInterval(function() {
//			var data=ajaxByurl('rest/main/getRTAlarmMessage');
//			exec(data);
//		}, 60000);
//
//	}
//	function exec(data){
//		if(data!=null){
//			var num = 0;
//			var i = setInterval(function() {
//				if (num >= data.length) {
//					num=0;
//					clearInterval(i);
//				}else{
//					showalert(data[num]);
//				}
//				num++;
//			}, 1000);
//		}
//	}
//
//	getData();
//
//	function showalert(mess)
//	{
////		alert(mess.toString());
//		var text='点号名称：'+mess.DATANAME+'</br> 错误时间：'+mess.ERRORTIME+'</br> 详细信息：'+mess.DATANAME+mess.COLUMNNAME+mess.WARNMESAGE;
//		var imageUrl = 'assets/avatars/' + mess.CHECKTYPE+'.png';
//		$.gritter.add({
//			title: mess.NAME,
//			text: text,
//			image:imageUrl,
//			sticky: false,
//			time: '',
//			class_name: ( '')
//		});
//
//	}
//
//




	//水情
	var wdsLegendData = ${wdsChartsData.nameList};  //水情数据
	var xAxisData = ${wdsChartsData.timeList}; //时间轴
	var etSeriesDataWds = ${wdsChartsData.etSeriesData};  //二滩数据
	var gdSeriesDataWds = ${wdsChartsData.gdSeriesData};  //官地数据
	var jdSeriesDataWds = ${wdsChartsData.jdSeriesData};  //锦东数据
	var jxSeriesDataWds = ${wdsChartsData.jxSeriesData};  //锦西数据
	var rsSeriesDataWds = ${wdsChartsData.rsSeriesData};  //若水数据
	//电量
	var tmrLegendData = ${tmrChartsData.nameList};
	var planData = ${tmrChartsData.planSeriesData}; //计划发电
	var dayData = ${tmrChartsData.daySeriesData}; //实际发电


	var tmrOption = {
		toolbox: {
			show : true,
			feature : {
				mark : {show: true},
				dataView : {show: true, readOnly: false},
				magicType: {show: true, type: ['line', 'bar']}
			}
			//padding:-5
		},
		tooltip : {
			trigger: 'axis'
		},
		legend: {
			data:tmrLegendData,
			left:'right',
			top:60,
			orient: 'vertical'
		},
		grid: {
			left: '1%',
			right: '0%',
			bottom: '2%',
			top:'1%',
			containLabel: true
		},
		xAxis : [
			{

				type : 'value'
				/*            axisLabel : {
				 formatter: '{value} °C'
				 }*/
			}
		],
		yAxis : [
			{
				type : 'category',
				boundaryGap : true,
				data : ['二滩','官地','锦西','锦东','若水']
			}

		],

		color :  [ '#ff7f50', '#87cefa', '#da70d6', '#32cd32', '#6495ed', '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', '#68BC31','#2091CF','#AF4E96','#F3A43B','#60C0DD', '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'],
		series : [
			{
				name:tmrLegendData[0],
				type:'bar',
				data:dayData
			}
			,
			{
				name:tmrLegendData[1],
				type:'bar',
				data:planData
			}
		]
	};

	var wdsOption = {
		tooltip : {
			trigger: 'axis'
		},
		legend: {
			data: wdsLegendData,
			left:'right',
			top:60,
			orient: 'vertical'
		},
		toolbox: {
			show : true,
			feature : {
				dataView: {readOnly: false},
				magicType: {type: ['line', 'bar']}
			}

		},grid: {
			left: '0%',
			right: '1%',
			bottom: '2%',
			top:'3%',
			containLabel: true
		},
		xAxis : [
			{
				type : 'category',
				boundaryGap : true,
				data : xAxisData
			}
		],
		yAxis : [
			{
				type : 'value'
				/*            axisLabel : {
				 formatter: '{value} °C'
				 }*/
			}
		],
		series : [
			{
				name: wdsLegendData[0],
				type:'line',
				symbolSize: 8,
				smooth:true,
				data:etSeriesDataWds
			},
			{
				name: wdsLegendData[1],
				type:'line',
				symbolSize: 8,
				smooth:true,
				data:gdSeriesDataWds
			}
			,
			{
				name: wdsLegendData[2],
				type:'line',
				symbolSize: 8,
				smooth:true,
				data:jdSeriesDataWds
			}
			,
			{
				name: wdsLegendData[3],
				type:'line',
				symbolSize: 8,
				smooth:true,
				data:jxSeriesDataWds
			}
			,
			{
				name: wdsLegendData[4],
				type:'line',
				symbolSize: 8,
				smooth:true,
				data:rsSeriesDataWds
			}
		]
	} ;

	//页面加载时调用饼形图绘制函数
	$(document).ready(function()
	{
		changeDataCharts(2016);
	});
//点击按钮，文字进行相应切换
	$('#bCount').on('click', function(e) {
		var $target = $(e.target);
		$('#time').text($target.text());
	})

	var placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'150px'});
	var data ;
//ajax加载数据错误率图形
	function changeDataCharts(time) {
		$.ajax({
			type : "post",
			async : false, //同步执行
			url : "rest/menu/changeDataTime?year=" + time ,
			dataType : "json", //返回数据形式为json
			success : function(result) {
				if (result) {
					data = [
						{ label: "水情警告",  data:result['wdsWarning'] , color: "#68BC31"},
						{ label: "电量警告",  data: result['tmrWarning'], color: "#2091CF"},
						{ label: "水情一般严重",  data: result['wdsNormal'], color: "#AF4E96"},
						{ label: "电量一般严重",  data:  result['tmrNormal'], color: "#DA5430"},
						{ label: "水情严重",  data:  result['wdsError'], color: "#FEE074"},
						{ label: "电量严重",  data:  result['tmrError'], color: "#FEF089"}
					];
					$("#warning").text(result['warning']);
					$("#normal").text(result['normal']);
					$("#error").text(result['error']);
				}
			},
			error : function(errorMsg) {
			}
		});

		function drawPieChart(placeholder, data, position) {
			$.plot(placeholder, data, {
				series: {
					pie: {
						show: true,
						tilt:0.8,
						highlight: {
							opacity: 0.25
						},
						stroke: {
							color: '#fff',
							width: 2
						},
						startAngle: 2
					}
				},
				legend: {
					show: true,
					position: position || "ne",
					labelBoxBorderColor: null,
					margin:[-30,15]
				}
				,
				grid: {
					hoverable: true,
					clickable: true
				}
			})
		}

		drawPieChart(placeholder,data);
	}

	//点击button，button值进行相应切换
	$('#bWds').on('click', function(e) {
		var $target = $(e.target);
		$('#wds').text($target.text());
	})
	//ajax加载水情图形
	function changeWdsCharts(type)
	{
		//var ccc = echarts.init(document.getElementById('dl-axis'));
		var myChart = echarts.init(document.getElementById('sq-axis'));

		//获得图表的options对象
		//通过Ajax获取数据
		$.ajax({
			type : "post",
			async : false, //同步执行
			url : "rest/menu/changeWdsData?wdsType=" + type,
			dataType : "json", //返回数据形式为json
			success : function(result) {
				if (result) {
					var wdsLegendData = result.ajaxNameList;  //水情数据
					wdsOption.series[0].name = wdsLegendData[0];
					wdsOption.series[0].data = result.etAjaxSeriesData;
					wdsOption.series[1].name = wdsLegendData[1];
					wdsOption.series[1].data = result.gdAjaxSeriesData;
					wdsOption.series[2].name = wdsLegendData[2];
					wdsOption.series[2].data = result.jdAjaxSeriesData;
					wdsOption.series[3].name = wdsLegendData[3];
					wdsOption.series[3].data = result.jxAjaxSeriesData;
					wdsOption.series[4].name = wdsLegendData[4];
					wdsOption.series[4].data = result.rsAjaxSeriesData;
					wdsOption.xAxis[0].data = result.ajaxTimeList;
					wdsOption.legend.data = result.ajaxNameList;
					myChart.hideLoading();
					myChart.setOption(wdsOption);
				}
			},
			error : function(errorMsg) {
				myChart.hideLoading();
			}
		});
	}

	$('#bTmr').on('click', function(e) {
		var $target = $(e.target);
		$('#tmr').text($target.text());
	})
	//ajax加载电量图形
	function changeTmrCharts(type)
	{
		var myChart = echarts.init(document.getElementById('dl-axis'));
		//获得图表的options对象
		//通过Ajax获取数据
		$.ajax({
			type : "post",
			async : false, //同步执行
			url : "rest/menu/changeTmrCharts?tmrType=" + type,
			dataType : "json", //返回数据形式为json
			success : function(result) {
				if (result) {
					var tmrLegendData = result.ajaxNameList;  //水情数据
					tmrOption.series[0].name = tmrLegendData[0];
					tmrOption.series[0].data = result.ajaxDaySeriesData;
					tmrOption.series[1].name = tmrLegendData[1];
					tmrOption.series[1].data = result.ajaxPlanSeriesData;
					tmrOption.legend.data = result.ajaxNameList;
					myChart.hideLoading();
					myChart.setOption(tmrOption);
				}
			},
			error : function(errorMsg) {
				myChart.hideLoading();
			}
		});
	}

	jQuery(function($) {

		//电量数据
		var tmrChart =echarts.init(document.getElementById('dl-axis'));

		tmrChart.setOption(tmrOption); //先把可选项注入myChart中
		tmrChart.hideLoading();

		//水情数据图形
		var wdsChart =echarts.init(document.getElementById('sq-axis'));

		wdsChart.setOption(wdsOption); //先把可选项注入myChart中
		wdsChart.hideLoading();

		//图形自适应网页
		window.onresize= function () {
			wdsChart.resize();
			tmrChart.resize();
		};


		$('.easy-pie-chart.percentage').each(function(){
			var $box = $(this).closest('.infobox');
			var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
			var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
			var size = parseInt($(this).data('size')) || 50;
			$(this).easyPieChart({
				barColor: barColor,
				trackColor: trackColor,
				scaleColor: false,
				lineCap: 'butt',
				lineWidth: parseInt(size/10),
				animate: /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,
				size: size
			});
		})

		$('.sparkline').each(function(){
			var $box = $(this).closest('.infobox');
			var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
			$(this).sparkline('html',
					{
						tagValuesAttribute:'data-values',
						type: 'bar',
						barColor: barColor ,
						chartRangeMin:$(this).data('min') || 0
					});
		});


		//flot chart resize plugin, somehow manipulates default browser resize event to optimize it!
		//but sometimes it brings up errors with normal resize event handlers
		$.resize.throttleWindow = false;




			/**
			 we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
			 so that's not needed actually.
			 */
			<%--placeholder.data('chart',data );
			placeholder.data('draw', drawPieChart);--%>




		//pie chart tooltip example
		<%--var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
		var previousPoint = null;

		placeholder.on('plothover', function (event, pos, item) {
			if(item) {
				if (previousPoint != item.seriesIndex) {
					previousPoint = item.seriesIndex;
					var tip = item.series['label'] + " : " + item.series['percent']+'%';
					$tooltip.show().children(0).text(tip);
				}
				$tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
			} else {
				$tooltip.hide();
				previousPoint = null;
			}

		});--%>

		var d1 = [];
		for (var i = 0; i < Math.PI * 2; i += 0.5) {
			d1.push([i, Math.sin(i)]);
		}

		var d2 = [];
		for (var i = 0; i < Math.PI * 2; i += 0.6) {
			d2.push([i, Math.cos(i)]);
		}


		$('#recent-box [data-rel="tooltip"]').tooltip({placement: tooltip_placement});
		function tooltip_placement(context, source) {
			var $source = $(source);
			var $parent = $source.closest('.tab-content')
			var off1 = $parent.offset();
			var w1 = $parent.width();

			var off2 = $source.offset();
			//var w2 = $source.width();

			if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
			return 'left';
		}


		$('.dialogs,.comments').ace_scroll({
			size: 580
		});


		//Android's default browser somehow is confused when tapping on label which will lead to dragging the task
		//so disable dragging when clicking on label
		var agent = navigator.userAgent.toLowerCase();
		if("ontouchstart" in document && /applewebkit/.test(agent) && /android/.test(agent))
			$('#tasks').on('touchstart', function(e){
				var li = $(e.target).closest('#tasks li');
				if(li.length == 0)return;
				var label = li.find('label.inline').get(0);
				if(label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation() ;
			});

		$('#tasks').sortable({
					opacity:0.8,
					revert:true,
					forceHelperSize:true,
					placeholder: 'draggable-placeholder',
					forcePlaceholderSize:true,
					tolerance:'pointer',
					stop: function( event, ui ) {
						//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
						$(ui.item).css('z-index', 'auto');
					}
				}
		);
		$('#tasks').disableSelection();
		$('#tasks input:checkbox').removeAttr('checked').on('click', function(){
			if(this.checked) $(this).closest('li').addClass('selected');
			else $(this).closest('li').removeClass('selected');
		});


		//show the dropdowns on top or bottom depending on window height and menu position
		$('#task-tab .dropdown-hover').on('mouseenter', function(e) {
			var offset = $(this).offset();

			var $w = $(window)
			if (offset.top > $w.scrollTop() + $w.innerHeight() - 100)
				$(this).addClass('dropup');
			else $(this).removeClass('dropup');
		});



	})



</script>