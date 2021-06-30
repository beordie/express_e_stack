// 正则测试快递单号函数
function CheckExpNum(expNum) {
	//检测数字
	var reg = new RegExp(/^[1-9]\d*$/);
	if (reg.test(expNum)) {
		return true;
	} else {
		return false;
	}
 //   	var str = expNum;
	// if (str == null) {
 //    	return false;
	// }

	// var companyReg = new Array();
 //    companyReg[0] = /^[0-9]{12}$/;  //申通快递单号由12位数字组成,目前常见以268**、368**、58**等开头.
 //    companyReg[1] = /^[a-z-A-Z]{2}[0-9]{9}[a-z-A-Z]{2}$/;  //EMS单号由13位字母和数字组成,一般开头和结尾二位是字母,中间是数字
 //    companyReg[2] = /^[0-9]{12}$/; //顺丰速递单号由12位数字组成,目前常见以电话区号后三位开头
 //    companyReg[3] = /^[a-z-A-Z-0-9]{10}$/; //圆通快递单号由10位字母数字组成,目前常见以1*、2*、6*、8*、V*等开头.
 //    companyReg[4] = /^[0-9]{13}$/; //韵达快递单号由13位数字组成,目前常见以10*、12*、19*等开头.
 //    companyReg[5] = /^[0-9]{12}$/; //中通快递单号由12位数字组成,目前常见以2008**、6**、010等开头.
 //    companyReg[6] = /^[0-9]{14}$/; //天天快递单号由14位数字组成,目前常见以6**、5*、00*等开头.
 //    companyReg[7] = /^[0-9]{10}$/; //宅急送单号由10位数字组成,目前常见以7**、6**、5**等开头.

 //    for (var i = 0; i < companyReg.length; i++) {
 //    	var reg = new RegExp(companyReg[i]);
 //    	if (reg.test(expNum)) {
 //    		return true;
 //    	}
 //    }
    
 //    return false;

}

// 正则测试身份证号码函数
function CheckUserId(userId){
	if (userId == null || userId.length < 18) {
		return false;
	}
	var reg = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
	if (reg.test(userId)) {
		return true;
	} else {
		return false;
	}
}

// 正则测试手机号码函数
function CheckPhoneNum(phoneNum){
	var phoneNum = phoneNum;
	if (phoneNum == null || phoneNum.length != 11) {
		return false; 
	}

	var reg = new RegExp(/0?(13|14|15|17|18|19)[0-9]{9}/);
	if (reg.test(phoneNum)) {
		return true;
	} else {
		return false;
	}
}

// 检测名字不为空
function CheckUserName(userName){
	if (userName.length != 0) {
		return true;
	} else {
		return false;
	}
}

