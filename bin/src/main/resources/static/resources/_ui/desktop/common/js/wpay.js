/*
 * 원클릭 결제
 * 2019-05-10 : CMB
 * 
 */

//간편결제 : 카드 정보
var WPAY_CARD_INFO = [];
WPAY_CARD_INFO['03'] = {'name' : '롯데카드', 'cls' : 'cd_kind_lotte'};
WPAY_CARD_INFO['04'] = {'name' : '현대카드', 'cls' : 'cd_kind_hyundai'};
WPAY_CARD_INFO['06'] = {'name' : '국민카드', 'cls' : 'cd_kind_kb'};
WPAY_CARD_INFO['11'] = {'name' : 'BC카드' , 'cls' : 'cd_kind_bc'};
WPAY_CARD_INFO['12'] = {'name' : '삼성카드', 'cls' : 'cd_kind_samsung'};
WPAY_CARD_INFO['14'] = {'name' : '신한카드', 'cls' : 'cd_kind_shinhan'};
WPAY_CARD_INFO['16'] = {'name' : 'NH카드' , 'cls' : 'cd_kind_nh'};
WPAY_CARD_INFO['01'] = {'name' : '하나카드', 'cls' : 'cd_kind_hana'};
WPAY_CARD_INFO['17'] = {'name' : '하나카드', 'cls' : 'cd_kind_hana'};

var WPAY = {
    FORMID : 'frmWpayHspay',
    IFRAMEID : 'ifrmWpayHspayLayer',
    loading : false,
    
    // WPAY.addForm 
    addForm: function () {
        if ($('#'+WPAY.FORMID).length > 0) {
            $('#'+WPAY.FORMID).remove();
        }
        return $('<form/>', { 
            id: WPAY.FORMID,
            enctype: 'application/x-www-form-urlencodeed'
            }).appendTo($('body'));
    },
    // WPAY.open 
    open: function (callback) {
        if($(".popwrap.one_click_pay1905").length > 0){
            $("#productLayer").remove();
            
            var wpayTag = '';
            var wpayStyleTag = "width: 100%; height: 650px; margin-top: 0; z-index: 100001;display: block !important; top: 0px; left: 0; background: #fff";
            wpayTag += '<div class="popwrap w_type_4 one_click_pay1905" id="" style="top: 100px; position: absolute; z-index: 101; margin-left:0;width:100%;left:0;padding:0; display: block;">';
            wpayTag += '    <div class="pop_cnt" style="margin-top:-50px">';
            wpayTag += '    <div style="height: 20px;"></div>';
            wpayTag += '        <iframe id="'+WPAY.IFRAMEID+'" name="' + WPAY.IFRAMEID + '" src="about:blank" width="100%" height="100%" style="'+wpayStyleTag+'" frameborder="0" border="0"></iframe>';
            wpayTag += '    </div>';
            wpayTag += '</div>';
            
            $("body").append(wpayTag);
        }else{
            $("#productLayer").show();
            var wpayTag = '';
            var wpayStyleTag = "width: 100%; height: 685px; margin-top: 0; z-index: 100001;display: block !important; top: 0px; left: 0; background: #fff";
            wpayTag += '<div class="popwrap w_type_4 one_click_pay1905" id="" style="top: 100px; position: absolute; z-index: 101; margin-left: -272px; display: block;">';
            wpayTag += '    <div class="pop_cnt">';
            wpayTag += '    <div style="height: 20px;"></div>';
            wpayTag += '        <iframe id="'+WPAY.IFRAMEID+'" name="' + WPAY.IFRAMEID + '" src="about:blank" height="100%" style="'+wpayStyleTag+'" frameborder="0" border="0"></iframe>';
            wpayTag += '    </div>';
            wpayTag += '    <a href="javascript:void(0);" id="onclickLayerClose" class="btn_close"><img src="/_ui/desktop/common/images/popup/ico_close.png" alt="닫기"></a>';
            wpayTag += '</div>';
        
            $("body").append(wpayTag);
            
            $(".one_click_pay1905").css("margin-top",$(document).scrollTop());
            
            //레이어 닫기    
            $("#onclickLayerClose").click(function(){
                $(".one_click_pay1905").remove();
                $("#productLayer").hide();
            }); 
        }
        
        callback();
    },
    // WPAY.close 
    close: function () {
        $(".one_click_pay1905").remove();
        $("#productLayer").hide();
        
        return false;
    },
    // WPAY.convertFormToJSON 
    convertFormToJSON: function convertFormToJSON(_form) {
        var obj = {};
        try {
            if (_form[0].tagName && _form[0].tagName.toUpperCase() == "FORM") {
                var arr = _form.serializeArray();
                if (arr != null) {
                    $.each(arr, function() {
                        obj[this.name] = this.value;
                    });
                }
            }
        } catch (e) {
        } finally {
        }
        return obj;
    },

    // WPAY.wpaylayerConfirm 
    wpaylayerConfirm: function (msg, confirmMsg, cancleMsg) {
        var layer = new layerConfirm(msg, confirmMsg, cancleMsg);
        return layer;
    }
};

// 간편결제 : 이용약관 이니시스화면UI
WPAY.accesstermsSubmit = function (data) {
    var $frmWpayHspay = WPAY.addForm();
    $frmWpayHspay.empty();
    addHidden($frmWpayHspay, 'mid'          , data.mid);            // 이니시스 가맹점 ID
    addHidden($frmWpayHspay, 'returnUrl'    , data.returnUrl);
    addHidden($frmWpayHspay, 'signature'    , data.signature);
    
    var nWidth = "450";
    var nHeight = "640";
      
    // 듀얼 모니터 고려한 윈도우 띄우기
    var curX = window.screenLeft;
    var curY = window.screenTop;
    var curWidth = document.body.clientWidth;
    var curHeight = screen.height;
      
    var nLeft = curX + (curWidth / 2) - (nWidth / 2);
    var nTop = curY + (curHeight / 2) - (nHeight / 2)
    //var nTop = 250;
    
    var strOption = "";
    strOption += "left=" + nLeft + "px,";
    strOption += "top=" + nTop + "px,";
    strOption += "width=" + nWidth + "px,";
    strOption += "height=" + nHeight + "px,";
    strOption += "toolbar=no,menubar=no,location=no,scrollorbars=no";
    strOption += "resizable=no,status=no,directories=no";
    
    // iframe -> 팝업 변경
    window.open("" ,"WPAYPOP",strOption);
    
    $frmWpayHspay.attr({
        'method': 'POST',
        'action': data.url,
        //'target': WPAY.IFRAMEID
        'target': "WPAYPOP"
    });
    $frmWpayHspay.submit();
}
//간편결제 : 신규카드등록 이니시스화면UI
WPAY.cardRegistSubmit = function (data) {
    var $frmWpayHspay = WPAY.addForm();
    $frmWpayHspay.empty();
    addHidden($frmWpayHspay, 'mid'          , data.mid);            // 이니시스 가맹점 ID
    addHidden($frmWpayHspay, 'wpayUserKey'  , data.wpayUserKey);    // [SEED] 이니시스에서 발행한 wpayUserKey
    addHidden($frmWpayHspay, 'ci'           , data.ci);             // [SEED] 고객의 CI
    addHidden($frmWpayHspay, 'payMethod'    , data.payMethod);      // 결제수단 코드 - 신용카드:"01"
    addHidden($frmWpayHspay, 'bankCardCode' , data.bankCardCode);   // 신용카드:6.2 카드사 코드 참조
    addHidden($frmWpayHspay, 'returnUrl'    , data.returnUrl);      // [URLENCODING] 등록결과 전달 URL
    addHidden($frmWpayHspay, 'signature'    , data.signature);
    
    var nWidth = "470";
    var nHeight = "660";
      
    // 듀얼 모니터 고려한 윈도우 띄우기
    var curX = window.screenLeft;
    var curY = window.screenTop;
    var curWidth = document.body.clientWidth;
    var curHeight = screen.height;
      
    var nLeft = curX + (curWidth / 2) - (nWidth / 2);
    var nTop = curY + (curHeight / 2) - (nHeight / 2)
    //var nTop = 250;
    
    var strOption = "";
    strOption += "left=" + nLeft + "px,";
    strOption += "top=" + nTop + "px,";
    strOption += "width=" + nWidth + "px,";
    strOption += "height=" + nHeight + "px,";
    strOption += "toolbar=no,menubar=no,location=no,scrollorbars=no";
    strOption += "resizable=no,status=no,directories=no";
    
    // iframe -> 팝업 변경
    window.open("" ,"WPAYPOP",strOption);
    
    $frmWpayHspay.attr({
        'method': 'POST',
        'action': data.url,
        //'target': WPAY.IFRAMEID
        'target': "WPAYPOP"
    });
    $frmWpayHspay.submit();
};

// 간편회원페이지 이니시스화면UI
WPAY.memberRegistSubmit = function (data) {
    var $frmWpayHspay = WPAY.addForm();
    $frmWpayHspay.empty();
    addHidden($frmWpayHspay, 'mid'          , data.mid);        // 이니시스 가맹점 ID
    addHidden($frmWpayHspay, 'userId'       , data.userId);     // [SEED] 가맹점 유저 ID
    addHidden($frmWpayHspay, 'ci'           , data.ci);         // [SEED] 고객의 CI
    /*
     * P : 결제시 회원가입 요청
     * N/null : 일반 회원가입 요청
     * 'N' 으로 고정
    */
    addHidden($frmWpayHspay, 'reqType'      , data.reqType);
    addHidden($frmWpayHspay, 'userNm'       , data.userNm);     // [URLENCODING] 고객 실명 (가맹점별Optional Required)
    addHidden($frmWpayHspay, 'hNum'         , data.hNum);       // [SEED] 고객 휴대폰번호
    /* 
     * 휴대폰통신사
     * 'SKR':SKT알뜰폰
     * 'LGR':LGT알뜰폰
     * 'KTR':KT알뜰폰
     */
    addHidden($frmWpayHspay, 'hCorp'        , data.hCorp);
    addHidden($frmWpayHspay, 'birthDay'     , data.birthDay);   // [SEED] 생년월일 (YYYYMMDD)
    addHidden($frmWpayHspay, 'socialNo2'    , data.socialNo2);  // 주민번호 뒤 첫자리
    addHidden($frmWpayHspay, 'frnrYn'       , data.frnrYn);     // returnUrl
    addHidden($frmWpayHspay, 'returnUrl'    , data.returnUrl);  // [URLENCODING] 회원가입 결과전달 URL
    addHidden($frmWpayHspay, 'payUrl'       , data.payUrl);     // 빈값으로 고정
    addHidden($frmWpayHspay, 'signature'    , data.signature);
    
    var nWidth = "450";
    var nHeight = "640";
      
    // 듀얼 모니터 고려한 윈도우 띄우기
    var curX = window.screenLeft;
    var curY = window.screenTop;
    var curWidth = document.body.clientWidth;
    var curHeight = screen.height;
      
    var nLeft = curX + (curWidth / 2) - (nWidth / 2);
    var nTop = curY + (curHeight / 2) - (nHeight / 2)
    //var nTop = 250;
    
    var strOption = "";
    strOption += "left=" + nLeft + "px,";
    strOption += "top=" + nTop + "px,";
    strOption += "width=" + nWidth + "px,";
    strOption += "height=" + nHeight + "px,";
    strOption += "toolbar=no,menubar=no,location=no,scrollorbars=no";
    strOption += "resizable=no,status=no,directories=no";
    
    // iframe -> 팝업 변경
    window.open("" ,"WPAYPOP",strOption);
    
    $frmWpayHspay.attr({
        'method': 'POST',
        'action': data.url,
        //'target': WPAY.IFRAMEID
        'target': "WPAYPOP"
    });
    $frmWpayHspay.submit();
}

WPAY.passwordChangeSubmit = function (data) {
    var $frmWpayHspay = WPAY.addForm();
    $frmWpayHspay.empty();
    addHidden($frmWpayHspay, 'mid'          , data.mid);            // 이니시스 가맹점 ID
    addHidden($frmWpayHspay, 'wpayUserKey'  , data.wpayUserKey);    // [SEED] 이니시스에서 발행한 wpayUserKey
    addHidden($frmWpayHspay, 'ci'           , data.ci);             // [SEED] 고객의 CI
    addHidden($frmWpayHspay, 'returnUrl'    , data.returnUrl);      // [URLENCODING] 등록결과 전달 URL
    addHidden($frmWpayHspay, 'signature'    , data.signature);
    
    var nWidth = "450";
    var nHeight = "640";
      
    // 듀얼 모니터 고려한 윈도우 띄우기
    var curX = window.screenLeft;
    var curY = window.screenTop;
    var curWidth = document.body.clientWidth;
    var curHeight = screen.height;
      
    var nLeft = curX + (curWidth / 2) - (nWidth / 2);
    var nTop = curY + (curHeight / 2) - (nHeight / 2)
    //var nTop = 250;
    
    var strOption = "";
    strOption += "left=" + nLeft + "px,";
    strOption += "top=" + nTop + "px,";
    strOption += "width=" + nWidth + "px,";
    strOption += "height=" + nHeight + "px,";
    strOption += "toolbar=no,menubar=no,location=no,scrollorbars=no";
    strOption += "resizable=no,status=no,directories=no";
    
    // iframe -> 팝업 변경
    window.open("" ,"WPAYPOP",strOption);
    
    $frmWpayHspay.attr({
        'method': 'POST',
        'action': data.url,
        //'target': WPAY.IFRAMEID
        'target': "WPAYPOP"
    });
    $frmWpayHspay.submit();
}

//간편결제 : 서비스 해지
//1. 비밀번호인증 이니시스 화면UI 호출
//2. 비밀번호인증 성공시 서비스 해지 api 호출
WPAY.memberUnRegistSubmit = function (data) {
    var $frmWpayHspay = WPAY.addForm();
    $frmWpayHspay.empty();
    addHidden($frmWpayHspay, 'mid'          , data.mid);            // 이니시스 가맹점 ID
    addHidden($frmWpayHspay, 'wpayUserKey'  , data.wpayUserKey);    // [SEED] 이니시스에서 발행한 wpayUserKey
    addHidden($frmWpayHspay, 'ci'           , data.ci);             // [SEED] 고객의 CI
    addHidden($frmWpayHspay, 'returnUrl'    , data.returnUrl);      // [URLENCODING] 비밀번호 인증 후 결과 전달 URL
    addHidden($frmWpayHspay, 'signature'    , data.signature);
    
    var nWidth = "450";
    var nHeight = "640";
      
    // 듀얼 모니터 고려한 윈도우 띄우기
    var curX = window.screenLeft;
    var curY = window.screenTop;
    var curWidth = document.body.clientWidth;
    var curHeight = screen.height;
      
    var nLeft = curX + (curWidth / 2) - (nWidth / 2);
    var nTop = curY + (curHeight / 2) - (nHeight / 2)
    //var nTop = 250;
    
    var strOption = "";
    strOption += "left=" + nLeft + "px,";
    strOption += "top=" + nTop + "px,";
    strOption += "width=" + nWidth + "px,";
    strOption += "height=" + nHeight + "px,";
    strOption += "toolbar=no,menubar=no,location=no,scrollorbars=no";
    strOption += "resizable=no,status=no,directories=no";
        
    // iframe -> 팝업 변경
    window.open("" ,"WPAYPOP",strOption);
    
    $frmWpayHspay.attr({
        'method': 'POST',
        'action': data.url,
        //'target': WPAY.IFRAMEID
        'target': "WPAYPOP"
        
    });
    $frmWpayHspay.submit();
}

/* 
간편결제 : 결제인증요청 이니시스화면UI
WPAY.paymentAuthSubmit
*/
WPAY.paymentAuthSubmit = function (data) {
	
	var $frmWpayHspay = WPAY.addForm();
	$frmWpayHspay.empty();

	addHidden($frmWpayHspay, 'mid'			, data.mid);			//이니시스 가맹점 ID
	addHidden($frmWpayHspay, 'wpayUserKey'	, data.wpayUserKey);	//[SEED] 이니시스에서 발행한 wpayUserKey
	addHidden($frmWpayHspay, 'wpayToken'	, data.wpayToken);		//[SEED] 이니시스에서 발행한 토큰
	addHidden($frmWpayHspay, 'ci'			, data.ci);				//[SEED] 고객의 CI
	addHidden($frmWpayHspay, 'payMethod'	, data.payMethod);		//결제수단 코드- 신용카드: 01
	addHidden($frmWpayHspay, 'bankCardCode'	, data.bankCardCode);	//신용카드: 6.2 카드사 코드 참조
	addHidden($frmWpayHspay, 'oid'			, data.oid);			//주문번호
	addHidden($frmWpayHspay, 'goodsName'	, data.goodsName);		//[URLENCODING] 상품명 (문자열 MAX Size 100byte 미만)
	addHidden($frmWpayHspay, 'goodsPrice'	, data.goodsPrice);		//결제금액
	addHidden($frmWpayHspay, 'buyerName'	, data.buyerName);		//[URLENCODING] 구매자명
	addHidden($frmWpayHspay, 'buyerTel'		, data.buyerTel);		//구매자연락처
	addHidden($frmWpayHspay, 'buyerEmail'	, data.buyerEmail);		//구매자이메일
	addHidden($frmWpayHspay, 'cardQuota'	, data.cardQuota);		//할부개월수(00:일시불)
	addHidden($frmWpayHspay, 'cardInterest'	, data.cardInterest);	//무이자여부(Y/N)
	addHidden($frmWpayHspay, 'couponCode'	, data.couponCode);		//선할인 쿠폰코드
	/*
	 * 핀인증 여부
	 * - Y/null : 핀인증 필수
	 * - N : 이니시스 판단
	 * ※ 1.5 FDS 수집 필수 항목의 FDS 인증 기본RULE 참조해서 핀인증 처리요망
	*/
	addHidden($frmWpayHspay, 'flagPin'		, data.flagPin);
	addHidden($frmWpayHspay, 'flagPinMsg'	, data.flagPinMsg);		//핀인증 창 안내 메시지 flagPin:Y/null 인 경우 [필수]
	addHidden($frmWpayHspay, 'returnUrl'	, data.returnUrl);
	addHidden($frmWpayHspay, 'flagCardPoint', data.flagCardPoint);	//카드포인트 사용여부 사용:Y, 그외 미사용 ※ Signature 생성 제외
	addHidden($frmWpayHspay, 'signature'	, data.signature);

	$frmWpayHspay.attr({
		'method': 'POST'
		, 'action': data.url
		// , 'target': WPAY.IFRAMEID
	});
	$frmWpayHspay.submit();
}

/* 
간편결제 : 결제인증요청 이니시스화면UI
WPAY.checkoutAuthSubmit 주문서 팝업 요청
*/
WPAY.checkoutAuthSubmit = function (data) {    
    var $frmWpayHspay = WPAY.addForm();
    $frmWpayHspay.empty();

    addHidden($frmWpayHspay, 'mid'          , data.mid);            //이니시스 가맹점 ID
    addHidden($frmWpayHspay, 'wpayUserKey'  , data.wpayUserKey);    //[SEED] 이니시스에서 발행한 wpayUserKey
    addHidden($frmWpayHspay, 'wpayToken'    , data.wpayToken);      //[SEED] 이니시스에서 발행한 토큰
    addHidden($frmWpayHspay, 'ci'           , data.ci);             //[SEED] 고객의 CI
    addHidden($frmWpayHspay, 'payMethod'    , data.payMethod);      //결제수단 코드- 신용카드: 01
    addHidden($frmWpayHspay, 'bankCardCode' , data.bankCardCode);   //신용카드: 6.2 카드사 코드 참조
    addHidden($frmWpayHspay, 'oid'          , data.oid);            //주문번호
    addHidden($frmWpayHspay, 'goodsName'    , data.goodsName);      //[URLENCODING] 상품명 (문자열 MAX Size 100byte 미만)
    addHidden($frmWpayHspay, 'goodsPrice'   , data.goodsPrice);     //결제금액
    addHidden($frmWpayHspay, 'buyerName'    , data.buyerName);      //[URLENCODING] 구매자명
    addHidden($frmWpayHspay, 'buyerTel'     , data.buyerTel);       //구매자연락처
    addHidden($frmWpayHspay, 'buyerEmail'   , data.buyerEmail);     //구매자이메일
    addHidden($frmWpayHspay, 'cardQuota'    , data.cardQuota);      //할부개월수(00:일시불)
    addHidden($frmWpayHspay, 'cardInterest' , data.cardInterest);   //무이자여부(Y/N)
    addHidden($frmWpayHspay, 'couponCode'   , data.couponCode);     //선할인 쿠폰코드
    /*
     * 핀인증 여부
     * - Y/null : 핀인증 필수
     * - N : 이니시스 판단
     * ※ 1.5 FDS 수집 필수 항목의 FDS 인증 기본RULE 참조해서 핀인증 처리요망
    */
    addHidden($frmWpayHspay, 'flagPin'      , data.flagPin);
    addHidden($frmWpayHspay, 'flagPinMsg'   , data.flagPinMsg);     //핀인증 창 안내 메시지 flagPin:Y/null 인 경우 [필수]
    addHidden($frmWpayHspay, 'returnUrl'    , data.returnUrl);
    addHidden($frmWpayHspay, 'flagCardPoint', data.flagCardPoint);  //카드포인트 사용여부 사용:Y, 그외 미사용 ※ Signature 생성 제외
    addHidden($frmWpayHspay, 'signature'    , data.signature);
    
    var nWidth = "450";
    var nHeight = "640";
      
    // 듀얼 모니터 고려한 윈도우 띄우기
    var curX = window.screenLeft;
    var curY = window.screenTop;
    var curWidth = document.body.clientWidth;
    var curHeight = screen.height;
      
    var nLeft = curX + (curWidth / 2) - (nWidth / 2);
    var nTop = curY + (curHeight / 2) - (nHeight / 2)
    //var nTop = 250;
    
    var strOption = "";
    strOption += "left=" + nLeft + "px,";
    strOption += "top=" + nTop + "px,";
    strOption += "width=" + nWidth + "px,";
    strOption += "height=" + nHeight + "px,";
    strOption += "toolbar=no,menubar=no,location=no,scrollorbars=no";
    strOption += "resizable=no,status=no,directories=no";
      
    popup = window.open("" ,"PAYFORM", strOption);
    if (popup == null) {
    	new layerAlert('팝업 차단을 해제해주세요.</br>팝업 허용 및 새로고침을 하신 후,</br>다시 결제를 진행해주세요.');
        return false;
    }
    
    $frmWpayHspay.attr({
        'method': 'POST',
        'action': data.url
    });
    var html = '';
    html += $frmWpayHspay[0].outerHTML;
    html += '<script> document.querySelector("#'+WPAY.FORMID+'").submit();</script>'
    popup.document.write(html);
    
    popupCheck = setInterval(function() {
        if(typeof(popup)=='undefined' || popup.closed) {
        	setInterval(function(){
            	if(typeof isSubmit !== 'undefined' && isSubmit == false){
                	history.back();
                    clearTimeout(popupCheck);	
            	}else{
                    clearTimeout(popupCheck);
            	}
        	},500);
        }
    },500);
}

// 카드목록 가져오기
var drawCardList = function drawCardList(callBackFunction) {
    var drawEasyPayTag = function (data) {
        var selectedCard = [];
        var isFirstDefault = false;
        var $card_easy_pay = $('#card_easy_pay'), easyPay = '';
        easyPay += '    <ul class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">';
        if (data.resultCode == "0000") {
            $card_easy_pay.data('nointlist', data.noIntCardList);
            $.each(data.cardList, function (index, item) {
                var card = WPAY_CARD_INFO[item.bankCardCode];
                easyPay += '<li class="swiper-slide swiper-slide-active" style="margin-right: 25px;">';
                easyPay += '    <div class="card_item '+card.cls+'">';
                easyPay += '        <div class="card_item_wrap">';
                if(item.discinfo.applYn == "Y"){
                    easyPay += '<div class="sale_now_box1910 type1">즉시할인</div>';
                }
                easyPay += '            <div class="radio_stl">';
                easyPay += '                    <input type="radio" id="card_choice_'+item.bankCardNo+'" name="card_choice" value="'+item.bankCardCode+'" data-token="' + item.wpayToken +'"';
                if (item.majYn == "Y") {
                    easyPay += ' checked >';
                    isFirstDefault = true;
                }else{
                    easyPay += '>';
                }
                easyPay += '                    <label for="card_choice_'+index+'"><div class="card_name">'+decodeURIComponent(item.cardName)+'</div></label>';
                easyPay += '                    <input type="hidden" class="discInfo_applYn" value="'+item.discinfo.applYn+'"/>';
                easyPay += '                    <input type="hidden" class="discInfo_couponCode" value="'+item.discinfo.couponCode+'"/>';
                easyPay += '                    <input type="hidden" class="discInfo_discAmt" value="'+item.discinfo.discAmt+'"/>';
                easyPay += '                    <input type="hidden" class="discInfo_minAmt" value="'+item.discinfo.minAmt+'"/>';
                easyPay += '                    <input type="hidden" class="discInfo_maxAmt" value="'+item.discinfo.maxAmt+'"/>';
                easyPay += '            </div>';
                easyPay += '            <div class="card_num">'+$.trim(item.bankCardNo).replace(/-/g, ' ')+'</div>';
                easyPay += '            <span class="sod_select card_pay_term" tabindex="0">';
                easyPay += '                <span class="sod_label">일시불</span>';
                easyPay += '                <span class="sod_list_wrapper">';
                easyPay += '                    <span class="sod_list">';
                easyPay += '                        <span class="sod_option selected" title="일시불">일시불</span>';
                easyPay += '                    </span>';
                easyPay += '                </span>';
                easyPay += '                <select name="card_pay_term_'+item.bankCardCode+'" id="card_pay_term_'+item.bankCardCode+'" class="sodsb" data-custom-class="card_pay_term">';
                easyPay += '                    <option value="00">일시불</option>';
                easyPay += '                </select>';
                easyPay += '            </span>';
                easyPay += '        </div>';
                easyPay += '    </div>';
                easyPay += '</li>';
                
                selectedCard.push(item.bankCardCode);
            });
        }

        easyPay += '        <li class="swiper-slide swiper-slide-next" style="margin-right: 25px;">';
        easyPay += '            <div class="card_item cd_kind_add">';
        easyPay += '                <a href="#none" class="card_add_btn" id="btnCardRegist">카드등록</a>';
        easyPay += '            </div>';
        easyPay += '        </li>';
        easyPay += '    </ul>';
        $(".swiper_card_easy_pay").html(easyPay);
        
        $("#btnCardRegist").click(function(){
            if (WPAY.loading == true) return;
            
            $.get("/wpay/cardRegist?fncallback=cardRegistCallback", function (data) {
                switch(data.resultCode) {
                case "0000":
                    // iframe -> 팝업 변경 WPAY.open 사용 안함
                    //WPAY.open(function () {
                    WPAY.cardRegistSubmit(data);
                    //});
                    break;
                case "NOT_WPAY_MEMBER":
                    // 등록되지 않은 회원인 경우 간편회원 가입페이지로 이동
                    wpayMemberRegistConfirm();
                    break;
                case "IS_ANONYMOUS":
                    location.href = "/member/login";
                    break;
                default:
                    new layerAlert(decodeURIComponent(data.resultMsg || "신규카드등록중 오류가 발생하였습니다."));
                }
            });     
        });
        
        if(!isFirstDefault){
            $("[id^=card_choice_]").eq(0).click();
        }
        
        if(selectedCard.length > 0){
            for(var i=0;i<selectedCard.length;i++){
                drawInstallment(selectedCard[i]);
            }
        }

        if(typeof fn_updateDiscInfo === "function"){
            fn_updateDiscInfo();
        }
        
        return callBackFunction();
    };
    var drawEasyPayTagFail = function (data) {
        console.log("fail");
    }
    // 카드리스트 && 무이자할부리스트 조회 
    return $.get('/wpay/cardListForPayment').done(drawEasyPayTag).fail(drawEasyPayTagFail);
}

// 주문서간편결제 : 카드 할부/무이자 생성
var drawInstallment = function drawInstallment(cardCode) {
    var noIntList = $('#card_easy_pay').data('nointlist');
    var paymentPrice = parseInt($('#total').val(), 0) || 0;
    var MAX_INSTALLMENT = 12,
        MAX_NOINTSTALLMENT_PRICE = 0,
        MAX_NOINTSTALLMENT_MONTH = ["0"];
    var $selectInstallment = $("select[id='card_pay_term_"+cardCode+"']"),
        selectTag = '';
    
    selectTag = '<option value="00">일시불</option>';
    
    if (paymentPrice >= 50000) {
        // 무이자 할부금액가져오기
        // 결제금액이 cardEvent.amount 보다 큰 할부를 적용합니다.
        // cardEventList.amount 가 가장 큰 금액의 month를 적용합니다.
        if (noIntList != null) {
            $.each(noIntList, function (idx, item) {
                if (item.cardCode != cardCode) return true;
                
                $.each(item.cardEventList, function (i, cardEvent) {
                    if (paymentPrice >= cardEvent.amount * 10000 && cardEvent.amount * 10000 > MAX_NOINTSTALLMENT_PRICE) {
                        MAX_NOINTSTALLMENT_PRICE = cardEvent.amount * 10000;
                        MAX_NOINTSTALLMENT_MONTH = cardEvent.month;
                    }
                });
            });
        }
        
        for (var i = 2; i <= MAX_INSTALLMENT; i++) {
            if (i < 10) {
                month = '0' + i;
            } else {
                month = i;
            }
            if ($.inArray(i.toString(), MAX_NOINTSTALLMENT_MONTH) > -1) {
                selectTag += '<option value="'+month+'" data-nointstallment="Y">'+ i.toString() + '개월 무이자</option>';
            } else {
                selectTag += '<option value="'+month+'">'+ i.toString() + '개월</option>';
            }
        }
    }
    $selectInstallment.html(selectTag);
}

// 간편결제 : 회원가입유도 처리 
var wpayMemberRegistConfirm = function () {
    var layer = WPAY.wpaylayerConfirm("간편결제 회원으로 등록되어 있지 않습니다.<br/>간편결제 가입 및 결제수단 등록을<br/>진행하시겠습니까?", "확인", "취소");
    layer.confirmAction = function () {
        // iframe -> 팝업 변경 WPAY.open 사용 안함
        //WPAY.open(function () {
        $.get("/wpay/memberRegist?fncallback=memberRegistCallback", function (memberData) {
            setTimeout(function () {
                if ($.trim(memberData.mid) == "") {
                    layerAlert(decodeURIComponent(memberData.resultMsg || "간편결제 회원 등록중 오류가 발생하였습니다."));
                    //WPAY.close();
                    return;
                }
                
                WPAY.memberRegistSubmit(memberData);
            }, 300);
        });
        //});
    }
    layer.cancleAction = function () {
        //WPAY.close();
    }
}

$(document).ready(function(){
	// 할부개월수 선택시 label에 선택된 개월수 표시
	$(document).on("change","select[id^=card_pay_term_]",function(){    
	    var idx = $("select[id^=card_pay_term_]").index($(this));
	    $(".sod_select:eq("+idx+") .sod_label").text($(this).find("option:selected").text());
	});
	
	// 카드등록
	$("#btnCardRegist").click(function(){
		if (WPAY.loading == true) return;
	    
	    $.get("/wpay/cardRegist?fncallback=cardRegistCallback", function (data) {
	        switch(data.resultCode) {
	        case "0000":
	            //iframe -> 팝업 변경 WPAY.open 사용 안함
	            //WPAY.open(function () {
	            WPAY.cardRegistSubmit(data);
	            //});
	            break;
	        case "NOT_WPAY_MEMBER":
	            // 등록되지 않은 회원인 경우 간편회원 가입페이지로 이동
	            wpayMemberRegistConfirm();
	            break;
	        case "IS_ANONYMOUS":
	            location.href = "/member/login";
	            break;
	        default:
	            new layerAlert(decodeURIComponent(data.resultMsg || "신규카드등록중 오류가 발생하였습니다."));
	        }
	    });		
	});
	
	//레이어 닫기	
	$("#onclickLayerClose").click(function(){
	    $(".one_click_pay1905").remove();
	    $("#productLayer").hide();
	});	
});

function addHidden(ptag, hname, hvl) {
    
    if($("#"+hname).length >0){
        $("#"+hname).remove();
    }
    return $('<input/>').attr('type'    , 'hidden')
                        .attr('id'      , hname)
                        .attr('name'    , hname)
                        .attr('value'   , hvl)
                        .appendTo(ptag);
}

function setOneClick(){
    $("#sel_rd0").trigger("click");    
    var swiperObj_c = new Swiper('.swiper_card_easy_pay', {
        loop: false,
        slidesPerView:'auto',
        freeMode: false,
        centeredSlides:false,
        speed: 500,
        spaceBetween:25,
        simulateTouch:false,
        slidesPerGroup: 1,
        navigation: {
            nextEl: '.next_oclk_slide_btn.oclks_btn1905',
            prevEl: '.prev_oclk_slide_btn.oclks_btn1905',
        },
    });
    
    swiperObj_c.on('sliderMove , transitionEnd', function() {
        $("input[name=discInfo]").val("");
        //할인받기 초기화
    });
    
    if($('.card_easy_pay_wrap.swiper-container .swiper-slide').length <= 1){
        $('.oclks_btn1905').hide();
    }
    
    $("label[for^=card_choice_]").on("click",function(){
        $(this).prev().prop("checked",true);

        if(typeof fn_updateDiscInfo === "function"){
            fn_updateDiscInfo();
        }
    });
}

function test(){
    window.parent.postMessage({loadingShow:'loadingShow'},'*');
}