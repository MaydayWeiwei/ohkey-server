<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" session="true"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=no">
<title>Yuanzi Star</title>
<link rel="icon" type="image/png"
	href="http://yuanzi.fr/img/favicon.png">
<link
	href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/plugins/circliful/css/jquery.circliful.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/plugins/magic/magic.css"/>"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">

<spring:url value="/" var="baseURL" />
<script src="<c:url value="/resources/plugins/jquery-1.11.0.js"/>"></script>
<script src="<c:url value="/resources/plugins/jquery.stringify.js"/>"></script>
<script
	src="<c:url value="/resources/plugins/bootstrap/js/bootstrap.js"/>"></script>
<script
	src="<c:url value="/resources/plugins/circliful/js/jquery.circliful.min.js"/>"></script>
<script
	src="<c:url value="/resources/plugins/jquery.anyslider.min.js"/>"></script>
<script src="<c:url value="/resources/js/application.js"/>"
	type="text/javascript" charset="utf-8"></script>
<script>
	(function(i, s, o, g, r, a, m) {
		i['GoogleAnalyticsObject'] = r;
		i[r] = i[r] || function() {
			(i[r].q = i[r].q || []).push(arguments)
		}, i[r].l = 1 * new Date();
		a = s.createElement(o), m = s.getElementsByTagName(o)[0];
		a.async = 1;
		a.src = g;
		m.parentNode.insertBefore(a, m)
	})(window, document, 'script', '//www.google-analytics.com/analytics.js',
			'ga');

	ga('create', 'UA-51084723-1', 'auto');
	ga('send', 'pageview');
</script>

</head>
<body>
	<img class="bg"
		src="<c:url value="${model_image_url_base}${model.mainPicture}"/>"
		alt="">
	<div class="header-sidebar container-fluid">
		<div class="row" style="height: 20px;"></div>
		<div class="row">
			<img class="logoImg"
				src="<c:url value="/resources/images/logoImg.png"/>" />
		</div>
		<div class="row" style="height: 5px;"></div>
		<div class="row">
			<span class="siteName">缘滋网</span>
		</div>
		<div class="row">
			<hr />
		</div>

		<div class="row">
			<div id="subscribe" class="menu-item">
				<div class="icon">
					<span class="glyphicon glyphicon-plus"></span>
				</div>
				<div class="text">
					<span class="description">订阅</span>
				</div>
				<div class="email">
					<form class="form-inline">
						<div class="form-group">
							<input type="email"
								class="form-control" id="email" style="width:230px;margin-top:23px"
								placeholder="输入您的邮箱地址">
						</div>
					</form>
				</div>
				<div class="validate">
					<span class="button" style="line-height:70px;">确认</span>
				</div>
			</div>
		</div>
		<div class="row">
			<div id="similarity" class="menu-item">
				<div class="icon">
					<span class="glyphicon glyphicon-tasks"></span>
				</div>
				<div class="text">
					<span class="description">匹配度</span>
				</div>
				<div class="simiMax">
					<div class="simiPercentage" data-dimension="80"
						data-text="${maxMatch}" data-info="最大" data-width="5"
						data-fontsize="12" data-percent="${model.max_match*100}"
						data-fgcolor="#61a9dc" data-bgcolor="#eee" data-fill="#ddd"></div>
				</div>
				<div class="simiMin">
					<div class="simiPercentage" data-dimension="80"
						data-text="${minMatch}" data-info="最小" data-width="5"
						data-fontsize="12" data-percent="${model.min_match*100}"
						data-fgcolor="#61a9dc" data-bgcolor="#eee" data-fill="#ddd"></div>
				</div>
				<div class="simiMean">
					<div class="simiPercentage" data-dimension="80"
						data-text="${meanMatch}" data-info="平均" data-width="5"
						data-fontsize="12" data-percent="${model.mean_match*100}"
						data-fgcolor="#61a9dc" data-bgcolor="#eee" data-fill="#ddd"></div>
				</div>
				<c:if test="${match==0}">
					<div class="simiMy">
						<div class="simiPercentage" data-dimension="80" data-text="点击计算"
							data-info="我的匹配度" data-width="5" data-fontsize="14"
							data-percent="0" data-fgcolor="#fff" data-bgcolor="#fff"
							data-fill="#fff"></div>
					</div>
				</c:if>
				<c:if test="${match!=0}">
					<div class="simiMyResult">
						<div class="simiPercentage" data-dimension="80"
							data-text="${matchPercentage}" data-info="我的匹配度" data-width="5"
							data-fontsize="14" data-percent="${match*100}"
							data-fgcolor="#61a9dc" data-bgcolor="#eee" data-fill="#ddd"></div>
					</div>
				</c:if>

			</div>
		</div>
		<div class="row">
			<div id="like" class="menu-item active">
				<div class="icon">
					<span class="glyphicon glyphicon-heart-empty"></span>
				</div>
				<div class="icon">
					<span class="glyphicon glyphicon-heart"></span>
				</div>

				<div class="text">
					<span class="description"><span id="likeCount"> ${likeCount}</span>个赞</span>
				</div>
				<div class="addLike">
					<span class="button" style="line-height:70px;">+赞</span>
				</div>
				
			</div>
		</div>
		<div class="row">
			<div id="profil" class="menu-item ${model.gender}">
				<div class="icon">
					<img
						src="${model_image_url_base}${model.profilePicture}"
						alt="" class="img-circle profil">
				</div>
				<div class="text">
					<img
						src="${model_image_url_base}${model.profilePicture}"
						alt="" class="img-circle profil">
				</div>
				<div class="infoBox boy">
					<div class="arrow"></div>
					<div class="info container-fluid">
						<div class="row">

							<div class="col-sm-4 right">
								<img
									src="${model_image_url_base}${model.wechatPicture}"
									width="100px" alt="加入我的群聊" />
							</div>
							<c:forEach var="info" items="${model.listModelInfos}">
								<div class="col-sm-4 right">${info.description}</div>
							</c:forEach>
							<div class="col-sm-8 right">邮箱：${model.email}</div>
							<div class="clearfix visible-sm-block"></div>
							<div class="col-sm-12 espace"></div>
							<div class="col-sm-3 right">自我介绍：</div>
							<div class="col-sm-9 right">${model.selfIntroduction}</div>
							<div class="col-sm-12 espace"></div>
							<div class="col-sm-3 right">图片心情：</div>
							<div class="col-sm-9 right">${model.pictureIntroduction}</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<div id="footer">
			<a href="/aboutus.html" target="_blank"><div class="link aboutUs"
					style="background-color: rgba(219, 85, 101, 1);"></div></a> <a
				href="/suggestions.html" target="_blank"><div
					class="link suggestions"
					style="background-color: rgba(81, 193, 135, 1);"></div></a> <a
				href="/terms.html" target="_blank"><div class="link terms"
					style="background-color: rgba(11, 172, 211, 1);"></div></a> <a
				href="/links.html" target="_blank"><div class="link links"
					style="background-color: #da883d;"></div></a>
		</div>
		<div id="copyRight">2014 © YUANZI All Rights Reserved</div>
	</div>
	<div id="tomorrow" class="${nextModel.gender}">
		<div class="image">
			<img
				src="${model_image_url_base}${nextModel.profilePicture}"
				alt="See it tomorrow" class="img-circle">
		</div>
		<div class="arrow">
			<span class="glyphicon glyphicon-chevron-right"></span>
		</div>
		<input type="hidden" id="tomorrowTitle"
			value="TA的信息将于<fmt:formatDate value="${nextModel.onlineDate}" pattern="MM"/>月<fmt:formatDate value="${nextModel.onlineDate}" pattern="dd"/>日00:00准时发布" />
		<input type="hidden" id="tomorrowGender" value="${nextModel.gender}" />
	</div>
	<div id="survey" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content container-fluid">
				<div class="row">
					<div class="logo">
						<span class="icon glyphicon glyphicon-tasks"></span>

					</div>
				</div>
				<div class="row">
					<div id="surveySlider">
						<div class="survey-item">
							<div class="question">请简单回答几个问题，我们帮你计算和TA的匹配度</div>
							<div class="answer">
								<span class="start next">开始<span
									class="glyphicon glyphicon-chevron-right"></span></span>
							</div>
						</div>
						<c:forEach items="${questions}" var="question" varStatus="status">
							<div class="survey-item">
								<div class="question">${question.question.description}</div>
								<div class="answer questionAnswer">
									<div class="row">
										<c:forEach items="${question.optionList}" var="option">
											<div class="col-md-6 col-lg-6 col-sm-6">
												<label class="next <c:if test="${status.last}">last</c:if>">
													<input type="radio" name="question${question.id}"
													value="${option.id}"> ${option.description}
												</label>
											</div>
										</c:forEach>
										<input type="hidden" class="questionId" value="${question.id}">
									</div>
								</div>
							</div>
						</c:forEach>
						<div class="survey-item">
							<div class="question">你们的匹配度是</div>
							<div class="answer">
								<div class="simiResult" data-dimension="80" data-width="5"
									data-fontsize="
									12" data-fgcolor="#61a9dc"
									data-bgcolor="#eee" data-fill="#ddd"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="likeModal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content container-fluid">
				<div class="row">
					<div class="logo">
						<span class="icon glyphicon glyphicon-heart"></span>
					</div>
				</div>
				<div class="row">
					<div class="description notLogged"></div>
				</div>
			</div>
		</div>
	</div>
	<div id="subscribeModal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content container-fluid">
				<div class="row">
					<div class="logo">
						<span class="icon glyphicon glyphicon-plus"></span>
					</div>
				</div>
				<div class="row">
					<div class="description">
						订阅成功，多谢支持。
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<input type="hidden" id="baseURL" value="${baseURL}">
	<input type="hidden" id="yuanziModelId" value="${model.id}">
</body>
</html>
