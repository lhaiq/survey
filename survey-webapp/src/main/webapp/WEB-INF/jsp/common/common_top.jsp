<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='assets/js/jquery.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>


<script src="assets/js/bootstrap.min.js"></script>
<!--[if lte IE 8]>
<script src="assets/js/excanvas.min.js"></script>
<![endif]-->
<!-- page specific plugin scripts -->
<script src="assets/js/fuelux/fuelux.wizard.min.js"></script>
<script src="assets/js/jquery.validate.min.js"></script>
<script src="assets/js/additional-methods.min.js"></script>
<script src="assets/js/bootbox.min.js"></script>
<script src="assets/js/jquery.maskedinput.min.js"></script>
<script src="assets/js/select2.min.js"></script>
<script src="assets/js/jquery-ui.min.js"></script>
<script src="assets/js/jquery-ui.custom.min.js"></script>
<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="assets/js/chosen.jquery.min.js"></script>
<script src="assets/js/fuelux/fuelux.spinner.min.js"></script>
<script src="assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="assets/js/date-time/bootstrap-timepicker.min.js"></script>
<script src="assets/js/date-time/moment.min.js"></script>
<script src="assets/js/date-time/daterangepicker.min.js"></script>
<script src="assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
<script src="assets/js/bootstrap-colorpicker.min.js"></script>
<script src="assets/js/jquery.knob.min.js"></script>
<script src="assets/js/jquery.autosize.min.js"></script>
<script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
<script src="assets/js/jquery-form.js"></script>
<script src="assets/js/bootstrap-tag.min.js"></script>
<script src="assets/js/typeahead.jquery.min.js"></script>
<script src="assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="assets/js/jqGrid/i18n/grid.locale-en.js"></script>

<script src="assets/js/jquery.easypiechart.min.js"></script>
<script src="assets/js/jquery.sparkline.min.js"></script>
<script src="assets/js/flot/jquery.flot.min.js"></script>
<script src="assets/js/flot/jquery.flot.pie.min.js"></script>
<script src="assets/js/flot/jquery.flot.resize.min.js"></script>
<script src="assets/js/jquery.gritter.min.js"></script>
<script src="assets/js/echarts.js"></script>
<!-- ace scripts -->
<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>
<script src="assets/js/jquery.colorbox-min.js"></script>




<script type="text/javascript">
	/**
	 * 通过url返回json数据
	 * @param url
	 * @returns
	 */
	function  ajaxByurl(url){
		var json;
		$.ajax({
			dataType : "json",
			url : url,
			//data : params,
			cache:false,
			async: false,
			success : function(data){
				json=data;
			}
		});
		return json;
	}
	/**
	 * String 转bool
	 * @param bool
	 * @returns {boolean}
	 */
    function boolConvert(bool){
		if(bool=="true")
			return true;
		else
			return false;
	}
	/**
	 * bool反转
	 * @param bool
	 * @returns {boolean}
	 */
	function boolReve(bool){
		if(bool)
			return false;
		else
			return true;
	}

	jQuery(function($) {
		//改变皮肤录入事件
		$("#skin-colorpicker").on("change",function(){
			ajaxByurl("rest/menu/setUIstyle?skin="+this.selectedIndex);
		});
		//界面调整录入事件
		$("#ace-settings-add-container").on("click",function(){
			ajaxByurl("rest/menu/setUIstyle?uiStyle="+boolReve(this.checked));
		});
		//子菜单悬浮录入事件
		$("#ace-settings-hover").on("click",function(){
			ajaxByurl("rest/menu/setUIstyle?submenu="+boolReve(this.checked));
		});
		//迷你导航录入事件
		$("#ace-settings-compact").on("click",function(){
			ajaxByurl("rest/menu/setUIstyle?miniNav="+boolReve(this.checked));
		});
		//导航边框切换录入事件
		$("#ace-settings-highlight").on("click",function(){
			ajaxByurl("rest/menu/setUIstyle?navStyle="+boolReve(this.checked));
		});



		// chosen 多选控件
		$('.chosen-select').chosen({
			allow_single_deselect : true
		});
		$(window).off('resize.chosen').on('resize.chosen', function() {
			$('.chosen-select').each(function() {
				var $this = $(this);
				$this.next().css({
					'width' : $this.parent().width()
				});
			})
		}).trigger('resize.chosen');
       //浏览导航 例：主页/系统管理/用户管理
		var breadvalues=$('#breadvalue112').val().split('/');
		$('#breadcrumbli').append('	<li class="active"></li>');
		for(var v in breadvalues){
			$('#breadcrumbli').append('	<li class="active">'+breadvalues[v]+'</li>');
		}

	})
</script>
