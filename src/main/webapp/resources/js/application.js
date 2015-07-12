var simiGenerated = false;
var resultGenerated = false;
var likeAdded = false;
var matchResponseList = new Array();
var context = $("#baseURL").val();

$(document).ready(function() {

	context = $("#baseURL").val();
	// test control
	

	$("#tomorrow").tooltip({
		placement : "left",
		title : $("#tomorrowTitle").val(),
		template : '<div class="tooltip '+$("#tomorrowGender").val()+'" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
	});
	
	$("#footer .link.aboutUs").tooltip({
		placement : "top",
		title : "关于我们",
		template : '<div class="tooltip aboutUs" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'	
	});
	$("#footer .link.suggestions").tooltip({
		placement : "top",
		title : "意见反馈",
		template : '<div class="tooltip suggestions" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'	
	});
	$("#footer .link.terms").tooltip({
		placement : "top",
		title : "法律帮助",
		template : '<div class="tooltip terms" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'	
	});
	$("#footer .link.links").tooltip({
		placement : "top",
		title : "友情链接",
		template : '<div class="tooltip links" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'	
	});

	$("#similarity").mouseenter(function() {
		if (simiGenerated == false) {
			setTimeout(function() {
				$(".simiPercentage").circliful();
			}, 100);
			simiGenerated = true;
		}
	});
	
	$("#subscribe .validate").click(function (){
		$.ajax({
			type : 'POST',
			url : context + "star/subscribe",
			data : $.stringify($("#email").val()),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if(data=='success'){
					$('#subscribeModal .modal-content .description').text("您已成功订阅我们的信息");
					$("#email").val("");
					$('#subscribeModal').modal('show');
				}
				if(data=='exist'){
					$('#subscribeModal .modal-content .description').text("您已订阅过我们的信息");
					$('#subscribeModal').modal('show');
				}
				if(data=='invalid'){
					$('#subscribeModal .modal-content .description').text("您输入的邮箱不合法");
					$('#subscribeModal').modal('show');
				}
				
			},
			error : function(data) {
				$('#subscribeModal .modal-content .description').text("因为网络问题,未能订阅成功,请稍后再试");
				$('#subscribeModal').modal('show');
			}
		});
	});
	
	
	$("#like.active .addLike").click(function (){
		if(!likeAdded){
			$.ajax({
				type : 'POST',
				url : context + "star/like/",
				data : $("#yuanziModelId").val(),
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					$('#likeModal .modal-content .description').text("点赞成功，代小主人谢谢您");
					$('#likeCount').text(parseInt($('#likeCount').text())+1);
					$('#likeCount').addClass("puffln");
					$('#like').removeClass("active");
					$('#like').addClass("inactive");
					likeAdded = true;
				},
				error : function(data) {
					$('#likeModal .modal-content .description').text("因为网络问题,未能为其点赞,请稍后再试");
					$('#likeModal').modal('show');
				}
			});
		}
		
	});

	$("#similarity .simiMy").click(function() {
		ga('send', 'event', 'similarity', 'start');
		$('#survey').modal('show');
	});
	
	

	$('#survey').on('hidden.bs.modal', function(e) {
		if(simiGenerated){
			location.reload(true);
		}
	});

	
	$("#survey").modal({
		show : false
	});
	var surveySlider = $("#surveySlider").anyslider({
		showBullets : false,
		showControls : false,
		interval : 0
	});
	var surveySliderControl = surveySlider.data('anyslider');

	$("#surveySlider .next").click(function() {
		surveySliderControl.next();

	});
	$("#surveySlider .last input:radio").change(function() {
		$(".questionAnswer").each(function() {
			var response = new Object();
			response.questionId = $(this).find(".questionId").val();
			response.optionId = $("input[name='question" + response.questionId + "']:checked").val();
			matchResponseList.push(response);
		});

		$.ajax({
			type : 'POST',
			url : context + "star/match/" + $("#yuanziModelId").val(),
			data : $.stringify(matchResponseList),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				var matchValue = (data*100).toFixed(1);
				if (!resultGenerated) {
					$(".simiResult").attr("data-percent", matchValue );
					$(".simiResult").attr("data-text", matchValue + "%");
					$(".simiResult").circliful();
					resultGenerated = true;
				}
			},
			error : function(data) {
				alert("Demander de valider ton table");
			}
		});
	});
	$("#favoriteGallery .photo .delete").click(function() {
		var favoriteId = $(this).next().val();
		var r = confirm("确定将其移出收藏夹？");
		var _parentSelf = $(this);
		if (r == true) {
			$.ajax({
				type : 'POST',
				url : context + "star/favorite/delete/" +favoriteId ,
				success : function(data) {
					if(data==1){
						_parentSelf.prev().addClass("magictime puffOut");
						_parentSelf.addClass("magictime puffOut");
						var ele = _parentSelf;
						var html = '<span class="icon glyphicon glyphicon-plus magictime puffIn"></span>';
						setTimeout(function() {
							ele.parent().html(html);
						}, 500);
					}else{
						alert("error internet");
					}
				},
				error : function(data) {
					alert("Demander de valider ton table");
				}
			});
			
		} else {

		}

	});	
});