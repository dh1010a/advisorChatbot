$(document).ready(function(){


// scroll
$(function() { 
	function  resizeWindow() { 
	var winH = $(window).height();
		$(".wrap__login").css({height:winH+"px"});
		$(".gnb").css({height:winH-81+"px"});
		$(".content").css({height:winH-81+"px"});
		$(".wrap__cms").css({height:winH+"px"});
		$(".chat--scroll").css({height:winH-120+"px"});

	} 
	$(window).resize(resizeWindow); 
	resizeWindow();	
});


// 달력
$(function() {
	$( ".j-cal" ).datepicker({
		showOn: "both", 
		buttonImage: "img/btn-calendar.png", 
		buttonImageOnly: true,
		dateFormat: 'yy-mm-dd',
		monthNames: ['.1월','.2월','.3월','.4월','.5월','.6월','.7월','.8월','.9월','.10월','.11월','.12월'],
		dayNamesMin: ['S','M','T','W','T','F','S'],
		changeMonth: false,
		changeYear: false,
		nextText: '다음 달',
		prevText: '이전 달',
		showMonthAfterYear: true
	});
});


// tab on/off
$('.tab--common_ns li a').click(function(){
	$('.common--tabview').not($('.cont__'+$(this).attr("id"))).hide();
	$('.cont__'+$(this).attr("id")).show();

	$('.tab--common_ns li a').not($(this)).removeClass('active');
	$(this).addClass('active');	

	return false;
});


// 레이어
$('.pop--open1-btn').click(function() {
	$('.pop--open1, .pop--overlay',top.document).fadeIn('');
	$('body').css('overflow','hidden')
});
$('.pop--close1-btn').click(function(){
	$('.pop--open1, .pop--overlay',top.document).fadeOut('');
	$('body').css('overflow','auto')
});


});