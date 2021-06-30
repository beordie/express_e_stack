// 自定义alert弹出框
function rzAlert(msgTitle,msgTxt){
	var url = window.location.href;
	//https://x.xlhtszgh.cn/kd/static/wx/wximages/userInfoIcon.png
	var urlarr = url.split("/");
	var rootUrl = urlarr[0]+"//"+urlarr[2]+"/kd/static/wx/wximages/";
	var $msgCover = $("<div></div>");
	$msgCover.css({
		"position":"absolute",
		"top":"0%",
		"left":"0%",
		"width":"100%",
		"height":"100%",
		"background-color":"rgba(0,0,0,0.5)",
		"z-index":"1002"
	});

	var $coverMsgCont = $("<div></div>");
	$coverMsgCont.css({
		"width":"56%",
		"padding":"20px 2%",
		"background":"#fff",
		"position":"relative",
		"left":"20%",
		"top":"calc(50% - 123px)",
		"border-radius":"10px",
		"overflow":"hidden"
	});
	$msgCover.append($coverMsgCont);

	var $closeimg = $("<img src='"+rootUrl+"close.gif' alt=''>");
	$closeimg.css({
		"display":"block",
		"float":"right",
		"width":"18px",
		"position":"absolute",
		"top":"8px",
		"right":"8px"
	});
	$closeimg.click(function() {
		/* Act on the event */
		$msgCover.fadeOut("fast",function(){
			$msgCover.remove();
		});
	});
	$coverMsgCont.append($closeimg);

	var $noticeimg = $("<img src='"+rootUrl+"noticeIcon.png' alt=''>");
	$noticeimg.css({
		"display":"block",
		"width":"90px",
		"margin":"0 auto"
	});
	$coverMsgCont.append($noticeimg);

	var $msgTitle = $("<p>"+msgTitle+"</p>");
	$msgTitle.css({
		"margin":"20px 0px 0px",
		"padding":"0px 8px",
		"line-height":"36px",
		"text-align":"center",
		"position":"relative",
		"font-size":"16px",
		"font-weight":"bold",
		"border-bottom":"1px solid #f1f1f1"
	});
	$coverMsgCont.append($msgTitle);

	var $msgTxt = $("<p>"+msgTxt+"</p>");
	$msgTxt.css({
		"margin":"0px",
		"padding":"0px",
		"height":"60px",
		"line-height":"30px",
		"text-align":"center",
		"color":"#00a6fd",
		"font-weight":"bold",
		"font-size":"14px"
	});
	$coverMsgCont.append($msgTxt);

	$("body").append($msgCover);

	setTimeout(function(){
		$msgCover.fadeOut("fast",function(){
			$msgCover.remove();
		});
	},2000)
}

