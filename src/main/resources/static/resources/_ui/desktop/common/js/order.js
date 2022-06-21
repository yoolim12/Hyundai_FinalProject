var quickDeliveryCostAlert = true;     
$(document).ready(function ()
{
	setEcommerceData("Checkout1(바로주문)");
 	// GA URL 적용
 	/*
    ga_url = decodeURI(document.URL);
    if(ga_url.indexOf('?') > -1){
        ga_url = ga_url.substring(0,ga_url.indexOf('?'));
    }
    
    ga_page_name = "주문서";
    */
    
    /*주문서 - 수정 190816 - s*/
    $(document).on("mouseover",".tlt_ship190816", function(){
    	$(this).parent('.delch_wrap190816').find('.delch_box190816').css("display","block");
    });
    $(document).on("mouseout",".tlt_ship190816", function(){
    	$(this).parent('.delch_wrap190816').find('.delch_box190816').css("display","none");
    });
    /*주문서 - 수정 190816 - e*/
    $(window).scroll(function(){
    	var scrollTop = $(window).scrollTop();
    	var height = $(window).height();
    	var scrollHeight = scrollTop + height;
    	var documentHeight = parseInt($(document).height());
    	var float_left = $(".float_left").height();
    	var float_right = $(".float_right").height();
    	var gap = 140;
    	if (scrollTop == 0 || scrollTop < 20) {
    			if($(".fourpm_txt").length > 0) {
    					$(".float_right").css("top", 30 + "px");
    			}else {
    					$(".float_right").css("top", 0 + "px");
    			}
    	} else if (scrollTop > gap && scrollTop < gap+float_left - float_right) {
    			$(".float_right").css("top", scrollTop - gap + "px");
    	} else if(scrollTop >= gap+float_left - float_right){
    			$(".float_right").css("top", float_left - float_right + "px");
    	}
    });
    
    //H.Point ***********************************
    var endTime, walker;
    var hour=0, min=3, sec=0;  
    
 
    function goTimer(){  
        var now = new Date();  
        var existTime = endTime.getTime() - now.getTime();  
        if(existTime <= 0){  
            Timer_refresh(0);  
            clearInterval(walker);  
            hideShowLayer();
            $(".popwrap").hide();
            layerAlert("인증시간이 경과 하였습니다.");
        }else{ // 아닐 경우
            Timer_refresh(existTime);  
        }
    }

    function n2(num){  
        return num >= 10 ? num : "0" + num;  
    }

    function Timer_refresh(milisec){  
         T_min = parseInt(Math.floor(milisec/60000)%60);  
        T_sec =parseInt(Math.floor(milisec/1000)%60);  
        $('#counter').html(n2(T_min) + ":" + n2(T_sec)+"초");  
    }
    $(document).on('click', '#startTimer', function() {  
        endTime = new Date();  
        endTime.setSeconds(endTime.getSeconds()+hour);  
        endTime.setMinutes(endTime.getMinutes()+min); 
        endTime.setHours(endTime.getHours()+sec); 
        walker = setInterval(goTimer, 1000); 
    });
    $(document).on('click', '#stopTimer', function() {  
        if(walker != null){  
            Timer_refresh(0);  
            clearInterval(walker);  
            walker=null;  
        }
    });
    
    function doTimer() {
        endTime = new Date();  
        endTime.setSeconds(endTime.getSeconds()+hour);  
        endTime.setMinutes(endTime.getMinutes()+min); 
        endTime.setHours(endTime.getHours()+sec); 
        walker = setInterval(goTimer, 1000); 
    }
    //H.Point ***********************************
    
    
    $(window).scroll(function(){
        var scrollTop = $(window).scrollTop();
        var height = $(window).height();
        var scrollHeight = scrollTop + height;
        var documentHeight = parseInt($(document).height());
		var float_left = $(".float_left").height();
		var float_right = $(".float_right").height();
		var gap = 140;
		        
        if (scrollTop == 0 || scrollTop < 20) {
            if($(".fourpm_txt").length > 0) {
                $(".float_right").css("top", 30 + "px");
            }else {
                $(".float_right").css("top", 0 + "px");                
            }
            
        } else if (scrollTop > gap && scrollTop < gap+float_left - float_right) {
            $(".float_right").css("top", scrollTop - gap + "px");
        }else if(scrollTop >= gap+float_left - float_right){
            $(".float_right").css("top", float_left - float_right + "px");
		}
    });
    
    // VIP 배송사 선택 Radio 버튼 재선택시 해제 가능하도록 처리
    var post_checked =$("#deilvy_rdo01").prop("checked");
    var cj_checked =$("#deilvy_rdo02").prop("checked");
    $(":radio[name='deilvy_rdo']").click(function(){
        
        var _clickId = $(this).attr("id");
        if(("deilvy_rdo01" == _clickId && post_checked == true)
                ||("deilvy_rdo02" == _clickId && cj_checked == true)){
            $(this).prop("checked",false);
        }
        post_checked =$("#deilvy_rdo01").prop("checked");
        cj_checked =$("#deilvy_rdo02").prop("checked");  
    });    
    // VIP 배송사 선택 후 확인 버튼 클릭시 
    $("#confirm_btn_selectComp").click(function(){
    	var selectedCompany = "";
        var isPermanentCompany = false;
        var hasOeraEntry = 'false';
        var hasLiquidesPerfums = 'false';
        if($(":radio[id='deilvy_rdo01']").prop("checked")){
            selectedCompany = "POST_OFFICE";
            $("#selectedCompArea").show();
            $("#nonSelectedCompArea").hide();
            $(".select_message").text("우체국 택배로 배송됩니다.");
            if( hasOeraEntry == 'true' || hasLiquidesPerfums == 'true'){
                $(".oera_fkh_003-case4").html("※ THE STAR/STAR/MANIA 대상 선택사항입니다<br/>※ 우체국 택배는 토요일 출고 및 배송 불가<br/>※ 리퀴드 퍼퓸바/오에라는 택배사 선택 불가(각각 별도 배송)");
            }else{
                $(".oera_fkh_003-case4").html("※ THE STAR/STAR/MANIA 대상 선택사항입니다<br/>※ 우체국 택배는 토요일 출고 및 배송 불가<br/><b style='color: red'>※ 파업 등 배송업체 사정에 따라 택배사가 변경될 수 있습니다.</b>");
            }
        }else if($(":radio[id='deilvy_rdo02']").prop("checked")){
        	selectedCompany = "CJ_LOGISTICS";
        	$("#selectedCompArea").show();
            $("#nonSelectedCompArea").hide();
            $(".select_message").text("CJ대한통운으로 배송됩니다.");
            if( hasOeraEntry == 'true' || hasLiquidesPerfums == 'true'){
                $(".oera_fkh_003-case4").html("※ THE STAR/STAR/MANIA 대상 선택사항입니다<br/>※ 리퀴드 퍼퓸바/오에라는 택배사 선택 불가(각각 별도 배송)");
            }else{
                $(".oera_fkh_003-case4").html("※ THE STAR/STAR/MANIA 대상 선택사항입니다");
            }
        }else{
            $("#selectedCompArea").hide();
            $("#nonSelectedCompArea").show();
            $(":checkbox[id='next_chk']").prop("checked",false);
            isPermanentCompany = false;
        } 
        
        if($(":checkbox[id='next_chk']").prop("checked")){
            isPermanentCompany = true;
        }
        $("#selectedCompany").val(selectedCompany);
        $("#isPermanentCompany").val(isPermanentCompany);
        $("#productLayer").hide();
        $("div.popwrap.deliy_pop0227").hide();
        
    });
    
	checkRedVoucher();
       

	$("#ce_tab li:eq(0) a").click();
    
	//즉시할인 포커스
	var today = new Date();
  	var year = today.getFullYear();
  	var month = today.getMonth() + 1;
  	var day = today.getDate();
  	
    if(month < 10){
    	month = "0"+month;
    }
    if(day < 10){
    	day = "0"+day;
    }
    
    // 기본 활성화 값
	if(year+""+month+""+day < "20180301"){
		$("#nowSale1").show();
		$("#nowSale1Img").show();
		$("#nowSale2").hide();
  	}else{
  		$("#nowSale1").hide();
  		$("#nowSale1Img").hide();
  		$("#nowSale2").hide();
  	}
	
    //원클릭 결제
    drawCardList(setOneClick);
    
    window.addEventListener('message',function(e){
        if(e.data.loadingShow != null){
            var loadingHtml = "";
            loadingHtml += '<div class="layerLoading_bar" id="loadingBarDiv" style="width: 100%;height: 100%;top:0;">';
            loadingHtml += '    <img src="http://cdn.thehandsome.com/pc/order/loading_spinner_007s_190827.gif" alt="loading" style="margin-left: -40px;">';
            loadingHtml += '</div>';
        
            $("body").append(loadingHtml);
        }else if(e.data.wpayResult != null){
            if(e.data.wpayResult == "checkoutPaymentAuthCallback"){
                checkoutPaymentAuthCallback(e.data.resultCode, e.data.resultMsg, e.data.result);
            }
        }
    });
    
    
    
    var frm = '';
    
    
        frm = $('#customerAddress');
        

    addressSetting(frm);
    
    
    $('#addrSearchBtn').click(function(){   
        searchAddressLayer('line1', 'line2', 'adress');
    });
    
    emailTypeDomainSelectController("#emailDely","#emailDelySel");
    
    
    if(parseInt(2620) > 0){
        $('#pointpay').prop('readonly',false);
    }else{
        $('#pointpay').prop('readonly',true);
    }
    //H.Point ***********************************
    if(parseInt(2059) > 0){
        $('#hpointpay').prop('readonly',false);
        $('#btnUseHPoint').prop('disabled',false);
        $('#btnUseHPoint').prop('class','btn add_s min_auto');
    }else{
        $('#hpointpay').prop('readonly',true);
    }
    //H.Point ***********************************
    if(parseInt(0) > 0){
        $('#giftpay').prop('readonly',false);
    }else{
        $('#giftpay').prop('readonly',true);
    }
    //egift ***********************************
    if(parseInt(0) > 0){
        $('#egiftpay').prop('readonly',false);
    }else{
        $('#egiftpay').prop('readonly',true);
    }
    
     
     // H.Point Start  ##########################################################
     $("#pw").keypress(function(event){
         if(capsLock(event)){
             $("#pwMsg").text("<Caps Lock>이 켜져 있습니다.");
         }else{
             $("#pwMsg").text("");
         }
         
         if ( event.which == 13 ) { 
             $(this).blur();
             checkPassword();
             return false;
         }
      });
     // H.Point END  ##########################################################
     $('.deliveryMsg').click(function(){
        $('#orderr').keyup();
     });
     
     $('#point_useall').click(function(){
         if($('#point_useall').is(":checked") && parseInt(2620) > 0){
             
            var usepoint = $('#total').val() - $('#cartDeliveryCost').val();
            
            if( usepoint > 0 && checktHandsomepointUsableAmount == 0 && "0" != "1" && $("#voucherRateCheck").val() != "1" ){
            	$('#pointpay').val(0);
            	layerAlert("제품 당 실결제금액(할인 적용가)이 3만원 이하인 제품은 한섬 마일리지를 사용하실 수 없습니다."); 
            	return;
        	}
             
            var usablePoint = parseInt('2620')
            if( usablePoint > checktHandsomepointUsableAmount ){
                usablePoint = checktHandsomepointUsableAmount
            }
            
            if( usablePoint > usepoint ){
                $('#pointpay').val(usepoint);
                $('#pointpay').prop('readonly', true);
            }else{
                $('#pointpay').val(usablePoint);
                $('#pointpay').prop('readonly', true);
            }
         }else{
            $('#pointpay').val('');
            $('#pointpay').prop('readonly', false);
            $('#point_useall').prop("checked", false);
         }
     });
      
     $('#pointpay').blur(function(){
         if(parseInt(2620) > 0 && $('#pointpay').val() >= parseInt(2620)){
            $('#pointpay').val('2620');
            if($('#pointpay').val() > 0){
                $('#point_useall').prop("checked", true);
            }
         }
     });
     
     // H.Point Start  ##########################################################
     $('#hpoint_useall').click(function(){
         if($('#hpoint_useall').is(":checked") && parseInt(2059) > 0){
            var usehpoint = $('#total').val() - $('#cartDeliveryCost').val();
            if(parseInt(2059) > usehpoint ){
                $('#hpointpay').val(usehpoint);
                $('#hpointpay').prop('readonly', true);
            }else{
                $('#hpointpay').val('2059');
                $('#hpointpay').prop('readonly', true);
            }
         }else{
            $('#hpointpay').val('');
            $('#hpointpay').prop('readonly', false);
            $('#hpoint_useall').prop("checked", false);
         }
     });
     $('#hpointpay').blur(function(){
         if(parseInt(2059) > 0 && $('#hpointpay').val() >= parseInt(2059)){
            $('#hpointpay').val('2059');
            if($('#hpointpay').val() > 0){
                $('#hpoint_useall').prop("checked", true);
            }
         }
     });
     // H.Point End ##############################################################
     
     
     $('#voucherCode').on("input", function(){
    	this.value = this.value.toUpperCase();
     });
     
     $('#gc_useall').click(function(){
         if($('#gc_useall').is(":checked") && parseInt(0) > 0){
             var usegift = $('#total').val() - $('#cartDeliveryCost').val();
             if(parseInt(0) > usegift ){
                $('#giftpay').val(usegift);
                $('#giftpay').prop('readonly', true);
            }else{
                $('#giftpay').val('0');
                $('#giftpay').prop('readonly', true);
            }
         }else{
            $('#giftpay').val('');
            $('#giftpay').prop('readonly', false);
            $('#gc_useall').prop("checked", false);
         }
     });

     $('#gc_egift_useall').click(function(){
         if($('#gc_egift_useall').is(":checked") && parseInt(0) > 0){
             var usegift = $('#total').val() - $('#cartDeliveryCost').val();
             if(parseInt(0) > usegift ){
                $('#egiftpay').val(usegift);
                $('#egiftpay').prop('readonly', true);
            }else{
                $('#egiftpay').val('0');
                $('#egiftpay').prop('readonly', true);
            }
         }else{
            $('#egiftpay').val('');
            $('#egiftpay').prop('readonly', false);
            $('#gc_egift_useall').prop("checked", false);
         }
     });
     
     $('#giftpay').blur(function(){
         if(parseInt(0) > 0 && $('#giftpay').val() >= parseInt(0)){
            $('#giftpay').val('0');
            if($('#giftpay').val() > 0){
                $('#gc_useall').prop("checked", true);
            }
         }
     });
     
     $('#egiftpay').blur(function(){
         if(parseInt() > 0 && $('#giftpay').val() >= parseInt()){
            $('#egiftpay').val('');
            if($('#egiftpay').val() > 0){
                $('#gc_egift_useall').prop("checked", true);
            }
         }
     });
     
     $("#adress").change(function() {
         setDeliveryMode();
     });
     
     $('#agree').change(function() {
        var documentResult = "";
        
        	documentResult = "<a class=\"btn gray\" onclick=\"doOrder();\">결제하기</a>";
		
        $("#doOrderBtn").html(documentResult);
     });
     
     setDeliveryMode();
     
     
     $(".btn_close").on("click", function(){
        hideShowLayer();
        $(".popwrap").hide();
     });
     
     
     //쿠폰 및 혜택 선택
     $(document).on("click","#sel_type1 , #sel_type2",function(){
         if($(this).attr("id") == "sel_type1"){
             $(".coupon_select_wrap").show();
             $(".vvip_select_wrap").hide();
         }else{
             $(".coupon_select_wrap").hide();
             $(".vvip_select_wrap").show();
         }
     });
     
     //우수고객할인
     $(document).on("click","#vipDiscountCheck",function(){
         if($(this).is(":checked")){
             $("#vipDiscountAmountText").val($("#vipDiscountAmountValue").val()+"원");
         }else{
             if($("#vipDiscountAmount").val() == ""){
                 $("#vipDiscountAmountText").val("");                 
             }
         }
     });
     
     $(document).on("click","#btnRedeemVipDiscount",function(){
         redeemVipDiscount(this);
     });
});


//간편결제 : 카드 등록 후 콜백
var cardRegistCallback = function (resultCode, resultMsg, result) {
  drawCardList(setOneClick);
  
  switch(resultCode) {
  case "2406": 
      layerWpayCookieSettingView();
      WPAY.close();
      break;
  case "0000":
      new layerAlert("");

      $(".popwrap .pop_cnt .pop_tlt").css("padding","0px");
      $(".popwrap .pop_cnt .pop_tlt").html("등록 완료");
      
      var cardDetailTag = '';
      var card = WPAY_CARD_INFO[result.bankCardCode];
      cardDetailTag += '<div class="oclk_mp_list">';
      cardDetailTag += '<div class="oclk_card_list_wrap">';
      cardDetailTag += '  <div class="lyrpp_mid">';
      cardDetailTag += '      <div class="card_easy_pay_wrap" style="margin-left: 45px;width:200px;">';
      cardDetailTag += '          <div class="card_item '+card.cls+'" style="width:176px;height:99px;">';
      cardDetailTag += '              <div class="card_item_wrap">';
      cardDetailTag += '                   <div class="card_name">'+decodeURIComponent(card.name)+'</div>';
      cardDetailTag += '                   <div class="card_num">'+$.trim(result.bankCardNo).replace(/-/g, ' ')+'</div>';
      cardDetailTag += '              </div>';
      cardDetailTag += '          </div>';
      cardDetailTag += '          <h4 style="margin-left: 45px;margin-top: 15px">카드등록이 완료되었습니다.</h4>';
      cardDetailTag += '      </div>';
      cardDetailTag += '  </div>';
      cardDetailTag += '</div>';
      cardDetailTag += '</div>';
      
      $(".popwrap .pop_cnt .pop_tlt").after(cardDetailTag);
      
      $(".popwrap .btn").val("간편결제를 등록해 주셔서 감사합니다");
      WPAY.close();
      break;
  default:
      new layerAlert(resultMsg);
      WPAY.close();
      break;
  }
}

//간편결제 : 간편회원 가입페이지 이동 후 콜백 
var memberRegistCallback = function (resultCode, resultMsg) {
  switch(resultCode) {
  case "2406":
      WPAY.close();
      break;
  case "0000":
      new layerAlert(resultMsg);
      WPAY.close();
      break;
  default:
      new layerAlert(resultMsg);
      WPAY.close();
      break;
  }
}

function fn_updateDiscInfo(){
    var mode = $("[name='mode']:checked").val();
    var selectObj = $('input[name="card_choice"]:checked').parent();

    var applYn = selectObj.find(".discInfo_applYn").val();							
    var cardName = selectObj.find(".card_name").text();								
    var discAmt = selectObj.find(".discInfo_discAmt").val();	                    
    var minAmt = selectObj.find(".discInfo_minAmt").val();		                    

    var couponCode = "";									                        

    var totalPrice = $("#total").val();
    var totalAmountHtml = "";

    totalAmountHtml = "₩" + addComma(Number(totalPrice));

    $(".discinfo_point").remove();

    if(totalPrice != null && discAmt != null && minAmt != null){
        discAmt = discAmt != "" && discAmt != null ? discAmt.split(",").join("") : 0;
        minAmt = minAmt != "" && minAmt != null ? minAmt.split(",").join("") : 0;

        
        if(applYn != "Y" || minAmt == "" || Number(totalPrice) < Number(minAmt) || discAmt == ""){
            discAmt = 0;
        }

        if(discAmt > 0 && mode == "KO001-3"){
            couponCode = selectObj.find(".discInfo_couponCode").val();				

            var discInfoHtml = "";

            discInfoHtml += "<dt class='discinfo_point'>즉시할인</dt> \n";
            discInfoHtml += "<dd class='discinfo_point'>- ₩" + addComma(discAmt) + "</dd>";

            $("#subTotal").after(discInfoHtml);

            totalAmountHtml = "₩" + addComma(Number(totalPrice)- Number(discAmt));
        }
    }
    
    $(".discInfo_minAmt").each(function(){
        var cardMinAmt = $(this).val();
        var totalPriceOrg = $("#total").val();

        cardMinAmt = cardMinAmt != null && cardMinAmt != "" && cardMinAmt != undefined ? cardMinAmt : 0;

        if(totalPrice != null && cardMinAmt != null && Number(cardMinAmt) < Number(totalPriceOrg)){
            $(this).parents(".card_item").find(".sale_now_box1910").show();
        } else {
            $(this).parents(".card_item").find(".sale_now_box1910").hide();
        }
    });
    $("#totalPrice").html(totalAmountHtml);
    
    $("#discInfo_coupon").val(couponCode);
}

var authFailFn = function(msg){
    layerAlert(msg);
};

function setDeliveryMode(){
    var island = false;
    if($("#adress").val() >= 63000 && $("#adress").val() <= 63644){
        island = true;
    }
    
    var isQuick = false;
    
    
    $.ajax({
        type: "GET",
        url: "/ko/checkout/deliveryMode",
        dataType: "json",
        data: {"island": island, "isQuick":isQuick},
        success: function(data){
            //if(data.errorMsg != ''){
                //layerAlert('정확한 쿠폰 코드를 다시 입력해 주시기 바랍니다.\n' + data.errorMsg);
                //return;
            //}else{
                $('.sum_box').html(data.totalPricePage);
                checkRedVoucher();
                if(typeof fn_updateDiscInfo === "function"){
                    fn_updateDiscInfo();
                }
                if(Number($('#cartDeliveryCost').val()) > 0 && quickDeliveryCostAlert == true){
                    quickDeliveryCostAlert = false;
                    
                }
                //$('#voucherCode').prop('disabled',true);
                //$('#selectVoucher').prop('disabled',true);
                //$(self).prop('class','btn dis_s');
                //$(self).prop('disabled',true);
            //}
        }//,
        //error: function(xhr,  Status, error) {
            //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
        //}
    });
}

function setAuth(name, hp){
    $('#order_name').text(name);
    $('#ordererName').val(name);
    //$('#order_hp').text(hp);
    $('#authYn').val('Y');
}

function f_guestAddress(){
    $('input[name=lastName]').val($('#ordererName').val());
    
    //var hp_num = $('#order_hp').text().split("-");
    $("#hp").val($('#hp0').val()).prop("selected", "selected");
    $('input[name=hp_num2]').val($('#hp1').val());
    $('input[name=hp_num3]').val($('#hp2').val());
    
    $('input[name=mail]').val($('#order_email').val());
    if($('#emailDomainSel').val() == ''){
        $('input[name=emailDely]').val($('#emailDomain').val());
    }else{
        $("#emailDelySel").val($('#emailDomainSel').val()).prop("selected", "selected");
        $('input[name=emailDely]').hide();
    }
}

function f_customerAddress(){
    addressSetting($('#customerAddress'));
}

function resetAddress(){
    $('input[name=addressId]').val('');
    $('input[name=postcode]').val('');
    $('input[name=line1]').val('');
    $('input[name=line2]').val('');
    
    $('input[name=lastName]').val('');

    $("#basis_bk_flag").removeClass("basis_bk_flag");

    
    $("#hp").val('010').prop("selected", "selected");
    $('input[name=hp_num2]').val('');
    $('input[name=hp_num3]').val('');

    $("#ph").val('').prop("selected", "selected");
    $('input[name=ph_num2]').val('');
    $('input[name=ph_num3]').val('');

    $('input[name=deliveryRequestContents]').val('');

    $('input[name=mail]').val('');
    $('input[name=emailDely]').val('');
    $("#emailDelySel").val('').prop("selected", "selected");
    $('input[name=emailDely]').show();
    
}

function addressSetting(frm){
    var defaultAddressPk = "";
    var deliveryPk = frm.find('input[name=sel_addressId]').val();
    
    
    $('input[name=addressId]').val(frm.find('input[name=sel_addressId]').val());
    $('input[name=postcode]').val(frm.find('input[name=sel_postcode]').val());
    $('input[name=line1]').val(frm.find('input[name=sel_line1]').val());
    $('input[name=line2]').val(frm.find('input[name=sel_line2]').val());
    
    
    if(getByteLength(frm.find('input[name=sel_lastName]').val()) > 40){
        $('input[name=lastName]').val(subStringBytes(frm.find('input[name=sel_lastName]').val(),40,3));
    }else{
        $('input[name=lastName]').val(frm.find('input[name=sel_lastName]').val());
    }
    var hp_num = frm.find('input[name=sel_cellphone]').val().split("-");
    $("#hp").val(hp_num[0]).prop("selected", "selected");
    $('input[name=hp_num2]').val(hp_num[1]);
    $('input[name=hp_num3]').val(hp_num[2]);
    
    var ph_num = frm.find('input[name=sel_phone]').val().split("-");
    $("#ph").val(ph_num[0]).prop("selected", "selected");
    $('input[name=ph_num2]').val(ph_num[1]);
    $('input[name=ph_num3]').val(ph_num[2]);
    
    var email = frm.find('input[name=sel_email]').val().split("@");
    if(email.indexOf("undefined") > -1){
        email = "";
    }
    $('input[name=mail]').val(email[0]);
    $('input[name=emailDely]').val(email[1]);
    //$("#emailDelySel").val(email[1]).prop("selected", "selected");
    
    if(defaultAddressPk != "" && defaultAddressPk == deliveryPk){
        $("#basis_bk_flag").addClass("basis_bk_flag");
    } else {
        $("#basis_bk_flag").removeClass("basis_bk_flag");
    }
    
    $('#adress').change();
    
}

//vip 배송업체 선택 기능 추가
function openVipCompany(){
    $("#productLayer").show();
    $("div.popwrap.deliy_pop0227").show();
    var setIsPermanentCompany = $("#isPermanentCompany").val();
    var setSelectedCompany = $("#selectedCompany").val();

    if(setSelectedCompany == 'POST_OFFICE'){
        $(":radio[id='deilvy_rdo01']").prop("checked",true);
        $(":radio[id='deilvy_rdo02']").prop("checked",false);
    }else if(setSelectedCompany == 'CJ_LOGISTICS'){
        $(":radio[id='deilvy_rdo01']").prop("checked",false);
        $(":radio[id='deilvy_rdo02']").prop("checked",true);
    }else{
        $(":radio[id='deilvy_rdo01']").prop("checked",false);
        $(":radio[id='deilvy_rdo02']").prop("checked",false);
    }
    
    if(setIsPermanentCompany == 'true'){
        $(":checkbox[id='next_chk']").prop("checked",true);
    }else{
        $(":checkbox[id='next_chk']").prop("checked",false);
    }
}


function selectAddress(){
    var sel_rd = $('input:radio[name="sel_address"]:checked');
    if(sel_rd.length == 0){
        layerAlert('배송지 선택해주세요.');
        return;
    }
    
    var sel_addressId = sel_rd.val();
    var form = $('#address_' + sel_addressId);
    $('.btn_close').click();
    addressSetting(form);
}

function selectDeliveryAddress(){
    var sel_rd = $('input:radio[name="sel_deliveryAddress"]:checked');
    
    if(sel_rd.length == 0){
        layerAlert('배송지 선택해주세요.');
        return;
    }
    
    var sel_addressId = sel_rd.val();
    var form = $('#deliveryAddress_' + sel_addressId);
    $('.btn_close').click();
    addressSetting(form);
}

function redeemVoucher(self){
     
	if($('#voucherCode').val() != ''){
		var tmpCode = $('#voucherCode').val(); 
		$('#voucherCode').val(tmpCode.toUpperCase())
	}
	
    var code = ($("#selectVoucher option:selected").val() == '') ? $('#voucherCode').val() : $("#selectVoucher option:selected").val();
    
    if($(self).prop('class') == 'btn dis_s min_auto'){
        return;
    }

    if(code == ''){
        //$(self).attr('onclick', 'redeemVoucher(this)');
        layerAlert('정확한 쿠폰 코드를 다시 입력해 주시기 바랍니다.');
        return;
    }
    
    
    if($(self).prop('class') == 'btn dis_s min_auto' 
            || $('#pointpay').val() > 0 
            || $('#hpointpay').val() > 0
            || $(self).prop('class') == 'btn dis_s min_auto'
            || $('#giftpay').val() > 0
            || $('#egiftpay').val() > 0
            || $("#pvoucherCode").prop('disabled') == true){

    	var confirmMsg = "";
    	if("Y" == "Y") {
    		confirmMsg = "플러스포인트쿠폰, 한섬마일리지나 H.Point 입력 후 쿠폰을 선택하실 경우 플러스포인트쿠폰/한섬마일리지/H.Point를 재입력 해야 합니다.";
    	} else {
    		confirmMsg = "한섬마일리지 입력 후 쿠폰을 선택하실 경우 한섬마일리지를 재입력 해야 합니다."; 
    	}

        if(!confirm(confirmMsg)){
            //$(self).attr('onclick', 'redeemVoucher(this)');
            return;
        }
        
        $(self).parents(".point_wrap").find("input:first").prop("readonly",false);
    }
    
    
    if ($(self).hasClass('on')) {
        layerAlert('처리중입니다.');
        return;
    }
    
    $(self).addClass('on');
    
    $.ajax({
        type: "GET",
        url: "/ko/checkout/redeemVoucher",
        contentType:"application/json; charset=utf-8",
        dataType: "json",
        data: {"voucherCode": code},
        success: function(data){
            if(data == "NEED_LOGIN"){
                alert("need login");
                location.href = "/ko/member/login";
            }
            
            if(data.errorMsg != ''){
                if(data.errorMsg == "globalOnlyVoucher") {
                    $('#voucherCode').val('');
                    layerAlert('입력하신 쿠폰 코드는 글로벌 전용입니다.</br>영문/중문 한섬통합몰에서 사용가능합니다.');
                    return;
                } else if(data.errorMsg == "koreaOnlyVoucher") {
                	layerAlert('정확한 쿠폰 코드를 다시 입력해 주시기 바랍니다.');
                    return;
                } else if(data.errorMsg == "dateNotAvailable") {
                	layerAlert('해당 쿠폰 코드는 적용이 불가능합니다.');
                    return;
                } else if(data.errorMsg == "orderOver") {
                	layerAlert('선착순 200명 마감되었습니다.');
                    return;
                } else if(data.errorMsg == 'brandCoupon'){
                	// 2017.04,26, 이현승, 브랜드쿠폰제한 안내 메시지 변경
                	var positiveErrMsg = '<p style="text-align:left">? (브랜드/카테고리)에만 해당.<br />다른 브랜드/카테고리와 함께 결제 시 사용하실 수 없습니다.</p>';
                	var negativeErrMsg = '<p style="text-align:left">? (브랜드/카테고리)는(은) 제외.<br />해당 브랜드/카테고리와 함께 결제 시 사용하실 수 없습니다.</p>';
                	var brandCategoryName = data.brandCategoryName;
                	var positiveYn = data.positiveYn;
                	
                	var brandCouponErrorMessage = "";
                	if(positiveYn=='true'){
                		brandCouponErrorMessage = positiveErrMsg.replace("?",brandCategoryName);
                	}else {
                		brandCouponErrorMessage = negativeErrMsg.replace("?",brandCategoryName);
                	}
                	layerAlert(brandCouponErrorMessage);
                	return;
                } else if(data.errorMsg == "alreadyVoucherUsed") {
                    layerAlert('쿠폰을 사용하신 주문입니다.<br />주문 당 쿠폰은 1번만 사용가능합니다.');
                    return;
                } else {
                    layerAlert('정확한 쿠폰 코드를 다시 입력해 주시기 바랍니다.\n' + data.errorMsg);
                    return;
                }
            }else{
            	
            	checkoutCartView();
            	
            	
                $('.sum_box').html(data.totalPricePage);
                
                if(typeof fn_updateDiscInfo === "function"){
                    fn_updateDiscInfo();
                }
                /* 글로벌 사이트일경우 배송비 및 관세 업데이트 20191113 남일희 */
                
                checkRedVoucher();
                $('input[name=voucherCode]').val(code);
                $('#voucherCode').prop('disabled',true);
                $('#selectVoucher').prop('disabled',true);
                $(self).prop('class','btn dis_s min_auto');
                $('#accumulationPoint').val(data.accumulationPoint);
                $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
                
                //$('#pointpay').val('');
                //$('#pointpay').prop('readonly',false);
                $('#btnCancelUsePoint').prop('class','btn dis_s min_auto');
                $('#btnCancelUsePoint').prop('disabled',false);
                $('#btnUsePoint').prop('disabled',false);
                $('#btnUsePoint').prop('class','btn add_s min_auto');
                $('#point_useall').prop("checked", false);
                $('#point_useall').prop('disabled',false);
                
                if(self.id == "btnRedeemVoucher"){
                    $('#btnReleaseVoucher').show();
                    $('#btnRedeemVoucher').hide();
	                $('#btnReleaseVoucher').prop('class','btn add_s min_auto');
	                $('#btnReleaseVoucher').prop('disabled',false);
	                $('#btnRedeemVoucher').prop('disabled',true);
	                $('#btnRedeemVoucher').prop('class','btn dis_s min_auto');
                }
                
                if($("#pvoucherCode").prop('disabled') == true) {
                    $('#pvoucherCode').val('');
                    $('#pvoucherCode').prop('disabled',false);
                    $('#selectPVoucher').prop('disabled',false);
                    $('#selectPVoucher').val('').prop('selected', true);
                    
                	$('#btnReleasePVoucher').prop('class','btn dis_s min_auto');
                    $('#btnReleasePVoucher').prop('disabled',true);
                    $('#btnRedeemPVoucher').prop('disabled',false);
                    $('#btnRedeemPVoucher').prop('class','btn add_s min_auto');
                }

                // H.Point ********************************************************
                $('#hpointpay').val('');
                $('#hpointpay').prop('readonly',false);
                $('#btnCancelUseHPoint').prop('class','btn dis_s min_auto');
                $('#btnCancelUseHPoint').prop('disabled',false);
                $('#btnUseHPoint').prop('disabled',false);
                $('#btnUseHPoint').prop('class','btn add_s min_auto');
                $('#hpoint_useall').prop("checked", false);
                $('#hpoint_useall').prop('disabled',false);
                // H.Point ********************************************************
                $('#giftpay').val('');
                $('#giftpay').prop('readonly',false);
                $('#btnCancelUseGiftAmount').prop('class','btn dis_s min_auto');
                $('#btnCancelUseGiftAmount').prop('disabled',false);
                $('#btnUseGiftAmount').prop('disabled',false);
                $('#btnUseGiftAmount').prop('class','btn add_s min_auto');
                $('#gc_useall').prop("checked", false);
                $('#gc_useall').prop('disabled',false);
                // EGIFT ********************************************************
                $('#egiftpay').val('');
                $('#egiftpay').prop('readonly',false);
                $('#btnCancelUseEGiftAmount').prop('class','btn dis_s min_auto');
                $('#btnCancelUseEGiftAmount').prop('disabled',false);
                $('#btnUseEGiftAmount').prop('disabled',false);
                $('#btnUseEGiftAmount').prop('class','btn add_s min_auto');
                $('#gc_egift_useall').prop("checked", false);
                $('#gc_egift_useall').prop('disabled',false);
                
                if($('#btnRedeemVipDiscount').prop('disabled')){
                    //우수고객할인 초기화
                    $('#btnReleaseVipDiscount').prop('class','btn dis_s min_auto');
                    $('#btnReleaseVipDiscount').prop('disabled',true);
                    $('#btnRedeemVipDiscount').prop('disabled',false);
                    $('#btnRedeemVipDiscount').prop('class','btn add_s min_auto');
                    
                    $("#vipDiscountAmountText").val("");
                    $("#vipDiscountAmount").val("");
                    $("#vipDiscountCheck").prop("checked",false);
                    $("#btnRedeemVipDiscount").show();
                    $("#btnReleaseVipDiscount").hide();
                }
            }
        }//,
        //error: function(xhr,  Status, error) {
            //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
        //}
    }).always(function(){
        $(self).removeClass('on');
    });
}

function redeemPVoucher(self){
    
    var entrySize = 1;
    var entryQuantity = "1" == "" ?  0 : Number("1");
    
     
    if(entrySize > 1)
    {
        layerAlert("플러스포인트쿠폰은 1개의 상품만 구매할수 있습니다.");
        $("#selectPVoucher option:eq(0)").prop("selected", true);
        return;
    }
    
    if(entryQuantity > 1)
    {
        layerAlert("플러스포인트쿠폰은 1개의 상품 수량만 구매할수 있습니다.");
        $("#selectPVoucher option:eq(0)").prop("selected", true);
        return;
    }

    if($(self).prop('class') == 'btn dis_s min_auto'){
        return;
    }
    
    
    if($(self).prop('class') == 'btn dis_s min_auto' 
            || $('#pointpay').val() > 0
            || $('#hpointpay').val() > 0
            || $(self).prop('class') == 'btn dis_s min_auto'
            || $('#giftpay').val() > 0
            || $('#egiftpay').val() > 0){
        //if(!confirm("마일리지나 H.Point 또는 기프트카드 입력 후 쿠폰을 선택하실 경우 마일리지/H.Point/기프트카드를 재입력 해야 합니다.")){
            //$(self).attr('onclick', 'redeemVoucher(this)');
        //   return;
        //}
        layerAlert("플러스포인트 쿠폰을 먼저 적용하시고 한섬마일리지/H.Point 입력 해야 합니다.");
        return;
    }
    
    
    if($('#pvoucherCode').val() != ''){
        var tmpCode = $('#pvoucherCode').val(); 
        $('#pvoucherCode').val(tmpCode.toUpperCase())
    }
    
    var code = ($("#selectPVoucher option:selected").val() == '') ? $('#pvoucherCode').val() : $("#selectPVoucher option:selected").val();
    
    if($(self).prop('class') == 'btn dis_s min_auto'){
        return;
    }

    //$(self).attr("onclick","layerAlert('처리중입니다.')");
    
    if(code == ''){
        //$(self).attr('onclick', 'redeemVoucher(this)');
        layerAlert('정확한 쿠폰 코드를 다시 입력해 주시기 바랍니다.');
        return;
    }
    
    if ($(self).hasClass('on')) {
        layerAlert('처리중입니다.');
        return;
    }
    
    $(self).addClass('on');
    
    $.ajax({
        type: "GET",
        url: "/ko/checkout/redeemPVoucher",
        contentType:"application/json; charset=utf-8",
        dataType: "json",
        data: {"voucherCode": code},
        success: function(data){
            if(data == "NEED_LOGIN"){
                alert("need login");
                location.href = "/ko/member/login";
            }
           
            if(data.errorMsg != ''){
                layerAlert(data.errorMsg);
                $('#pvoucherCode').prop('disabled',false);
                $('#selectPVoucher').prop('disabled',false);
                $('#selectPVoucher').val('').prop('selected', true);
                return;
            }else{
            	checkoutCartView();
            	
                $('.sum_box').html(data.totalPricePage);
                
                if(typeof fn_updateDiscInfo === "function"){
                    fn_updateDiscInfo();
                }
                checkRedVoucher();
                $('input[name=pvoucherCode]').val(code);
                $('#pvoucherCode').prop('disabled',true);
                $('#selectPVoucher').prop('disabled',true);
                $(self).prop('class','btn dis_s min_auto');
                $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
                
                //$('#pointpay').val('');
                //$('#pointpay').prop('readonly',false);
                $('#btnCancelUsePoint').prop('class','btn dis_s min_auto');
                $('#btnCancelUsePoint').prop('disabled',false);
                $('#btnUsePoint').prop('disabled',false);
                $('#btnUsePoint').prop('class','btn add_s min_auto');
                $('#point_useall').prop("checked", false);
                $('#point_useall').prop('disabled',false);
                
                if(self.id == "btnRedeemPVoucher"){
	                $('#btnReleasePVoucher').prop('class','btn add_s min_auto');
	                $('#btnReleasePVoucher').prop('disabled',false);
	                $('#btnRedeemPVoucher').prop('disabled',true);
	                $('#btnRedeemPVoucher').prop('class','btn dis_s min_auto');
                }
                
                // H.Point ********************************************************
                $('#hpointpay').val('');
                $('#hpointpay').prop('readonly',false);
                $('#btnCancelUseHPoint').prop('class','btn dis_s min_auto');
                $('#btnCancelUseHPoint').prop('disabled',false);
                $('#btnUseHPoint').prop('disabled',false);
                $('#btnUseHPoint').prop('class','btn add_s min_auto');
                $('#hpoint_useall').prop("checked", false);
                $('#hpoint_useall').prop('disabled',false);
                // H.Point ********************************************************
                
                $('#giftpay').val('');
                $('#giftpay').prop('readonly',false);
                $('#btnCancelUseGiftAmount').prop('class','btn dis_s min_auto');
                $('#btnCancelUseGiftAmount').prop('disabled',false);
                $('#btnUseGiftAmount').prop('disabled',false);
                $('#btnUseGiftAmount').prop('class','btn add_s min_auto');
                $('#gc_useall').prop("checked", false);
                $('#gc_useall').prop('disabled',false);

                $('#egiftpay').val('');
                $('#egiftpay').prop('readonly',false);
                $('#btnCancelUseEGiftAmount').prop('class','btn dis_s min_auto');
                $('#btnCancelUseEGiftAmount').prop('disabled',false);
                $('#btnUseEGiftAmount').prop('disabled',false);
                $('#btnUseEGiftAmount').prop('class','btn add_s min_auto');
                $('#gc_egift_useall').prop("checked", false);
                $('#gc_egift_useall').prop('disabled',false);
            }
        }//,
        //error: function(xhr,  Status, error) {
            //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
        //}
    }).always(function(){
        $(self).removeClass('on');
    });
}

function redeemVipDiscount(self){
    

    if('' == ""){
        layerAlert("한섬 VVIP만 사용하실 수 있습니다.");
        return;
    }else if( 'false' == 'false' && ('' == 'V4' || '' == 'V5' || '' == 'V6') ){
        layerAlert("한섬 VVIP 혜택을 사용하실 수 없습니다.");
        return;
    }
    
    if($('#vipDiscountAmountText').val() == ""){
        layerAlert("한섬 VVIP 혜택사용을 체크 하셔야 합니다.");
        return;
    }
    
    if($('#btnRedeemVoucher').prop('disabled')){
        layerAlert("쿠폰과 동시에 사용하실 수 없습니다."); 
       return;
    }
    
    if($(self).prop('class') == 'btn dis_s min_auto' 
            || $('#pointpay').val() > 0 
            || $('#hpointpay').val() > 0
            || $(self).prop('class') == 'btn dis_s min_auto'
            || $('#giftpay').val() > 0
            || $('#egiftpay').val() > 0
            || $("#pvoucherCode").prop('disabled') == true){

        var confirmMsg = "";
        if("Y" == "Y") {
            confirmMsg = "플러스포인트쿠폰, 한섬마일리지나 H.Point 입력 후 한섬 VVIP 혜택을 선택하실 경우 플러스포인트쿠폰/한섬마일리지/H.Point를 재입력 해야 합니다.";
        } else {
            confirmMsg = "한섬마일리지 입력 후 한섬 VVIP 혜택을 선택하실 경우 한섬마일리지를 재입력 해야 합니다."; 
        }

        if(!confirm(confirmMsg)){
            return;
        }
        
        $(self).parents(".point_wrap").find("input:first").prop("readonly",false);
    }
    
    $("#vipDiscountAmount").val($("#vipDiscountAmountValue").val().replace(/,/g,""));

    $.ajax({
        type: "GET",
        url: "/ko/checkout/redeemVipDiscount",
        contentType:"application/json; charset=utf-8",
        dataType: "json",
        data: {"vipDiscountAmount": $("#vipDiscountAmount").val(),"vipCustNo":$("#vipCustNoTemp").val()},
        success: function(data){
            if(data == "NEED_LOGIN"){
                alert("need login");
                location.href = "/ko/member/login";
            }
           
            if(data.errorMsg != ''){
                layerAlert(data.errorMsg);
                $("#vipDiscountAmount").val("");
                return;
            }else{
                checkoutCartView();
                $('.sum_box').html(data.totalPricePage);
                //7.4 khg
                setDeliveryMode();
                
                if(typeof fn_updateDiscInfo === "function"){
                    fn_updateDiscInfo();
                }
                checkRedVoucher();
                $(self).prop('class','btn dis_s min_auto');
                $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
                
                $('#btnCancelUsePoint').prop('class','btn dis_s min_auto');
                $('#btnCancelUsePoint').prop('disabled',false);
                $('#btnUsePoint').prop('disabled',false);
                $('#btnUsePoint').prop('class','btn add_s min_auto');
                $('#point_useall').prop("checked", false);
                $('#point_useall').prop('disabled',false);
                
                if(self.id == "btnRedeemVipDiscount"){
                    $('#btnReleaseVipDiscount').prop('class','btn add_s min_auto');
                    $('#btnReleaseVipDiscount').prop('disabled',false);
                    $('#btnRedeemVipDiscount').prop('disabled',true);
                    $('#btnRedeemVipDiscount').prop('class','btn dis_s min_auto');
                }
                
                // H.Point ********************************************************
                $('#hpointpay').val('');
                $('#hpointpay').prop('readonly',false);
                $('#btnCancelUseHPoint').prop('class','btn dis_s min_auto');
                $('#btnCancelUseHPoint').prop('disabled',false);
                $('#btnUseHPoint').prop('disabled',false);
                $('#btnUseHPoint').prop('class','btn add_s min_auto');
                $('#hpoint_useall').prop("checked", false);
                $('#hpoint_useall').prop('disabled',false);
                // H.Point ********************************************************
                
                $('#giftpay').val('');
                $('#giftpay').prop('readonly',false);
                $('#btnCancelUseGiftAmount').prop('class','btn dis_s min_auto');
                $('#btnCancelUseGiftAmount').prop('disabled',false);
                $('#btnUseGiftAmount').prop('disabled',false);
                $('#btnUseGiftAmount').prop('class','btn add_s min_auto');
                $('#gc_useall').prop("checked", false);
                $('#gc_useall').prop('disabled',false);

                $('#egiftpay').val('');
                $('#egiftpay').prop('readonly',false);
                $('#btnCancelUseEGiftAmount').prop('class','btn dis_s min_auto');
                $('#btnCancelUseEGiftAmount').prop('disabled',false);
                $('#btnUseEGiftAmount').prop('disabled',false);
                $('#btnUseEGiftAmount').prop('class','btn add_s min_auto');
                $('#gc_egift_useall').prop("checked", false);
                $('#gc_egift_useall').prop('disabled',false);
                
                $("#btnRedeemVipDiscount").hide();
                $("#btnReleaseVipDiscount").show();
            }
        }//,
        //error: function(xhr,  Status, error) {
            //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
        //}
    }).always(function(){
        $(self).removeClass('on');
    });
    
}
 
function releaseVipDiscount(self){
    
    if($(self).prop('class') == 'btn dis_s min_auto'){
        return;
    }
    
    if ($(self).hasClass('on')) {
        layerAlert('처리중입니다.');
        return;
    }
    
    $(self).addClass('on');
    
    $.ajax({
        type: "GET",
        url: "/ko/checkout/releaseVipDiscount",
        dataType: "json",
        contentType:"application/json; charset=utf-8",
        data: {"vipDiscountAmount": $("#vipDiscountAmount").val()},
        success: function(data){
            if(data == "NEED_LOGIN"){
                alert("need login");
                location.href = "/ko/member/login";
            }
            
            if(data.errorMsg != ''){
                layerAlert('정확한 쿠폰 코드를 다시 입력해 주시기 바랍니다.');
                return;
            }else{
                checkoutCartView();
                $('.sum_box').html(data.totalPricePage);
                //7.4 khg
                setDeliveryMode();
                
                if(typeof fn_updateDiscInfo === "function"){
                    fn_updateDiscInfo();
                }
                checkRedVoucher();
                $('#accumulationPoint').val(data.accumulationPoint);
                $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
                
                if(self.id == "btnReleaseVipDiscount"){
                    $('#btnReleaseVipDiscount').prop('class','btn dis_s min_auto');
                    $('#btnReleaseVipDiscount').prop('disabled',true);
                    $('#btnRedeemVipDiscount').prop('disabled',false);
                    $('#btnRedeemVipDiscount').prop('class','btn add_s min_auto');
                    
                    $("#vipDiscountAmountText").val("");
                    $("#vipDiscountAmount").val("");
                    $("#vipDiscountCheck").prop("checked",false);
                    $("#btnRedeemVipDiscount").show();
                    $("#btnReleaseVipDiscount").hide();
                }
                
                $('#point_useall').prop("checked", false);
                $('#hpoint_useall').prop("checked", false);
                $('#gc_egift_useall').prop("checked", false);
            }
        }//,
        //error: function(xhr,  Status, error) {
            //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
        //}
    }).always(function(){
        $(self).removeClass('on');
    });
}

function releaseVoucher(self){
    var code = ($("#selectVoucher option:selected").val() == '') ? $('#voucherCode').val() : $("#selectVoucher option:selected").val();
    
    if($('#voucherCode').val() != ''){
		var tmpCode = $('#voucherCode').val(); 
		$('#voucherCode').val(tmpCode.toUpperCase())
	}
    
    if($(self).prop('class') == 'btn dis_s min_auto'){
        return;
    }
    
    if(code == ''){
        layerAlert('정확한 쿠폰 코드를 다시 입력해 주시기 바랍니다.');
        return;
    }
    
    if ($(self).hasClass('on')) {
        layerAlert('처리중입니다.');
        return;
    }
    
    $(self).addClass('on');
    
    $.ajax({
        type: "GET",
        url: "/ko/checkout/releaseVoucher",
        dataType: "json",
        contentType:"application/json; charset=utf-8",
        data: {"voucherCode": code},
        success: function(data){
            if(data == "NEED_LOGIN"){
                alert("need login");
                location.href = "/ko/member/login";
            }
            
            if(data.errorMsg != ''){
                layerAlert('정확한 쿠폰 코드를 다시 입력해 주시기 바랍니다.');
                return;
            }else{
            	checkoutCartView();
            	
                $('#voucherCode').val('');
                $('#select_voucher').html(data.selectVoucher);
                $('.sum_box').html(data.totalPricePage);
                
                if(typeof fn_updateDiscInfo === "function"){
                    fn_updateDiscInfo();
                }
                /* 글로벌 사이트일경우 배송비 및 관세 업데이트 20191113 남일희 */
                
                checkRedVoucher();
                $('#voucherCode').prop('disabled',false);
                $('#selectVoucher').prop('disabled',false);
                $('#accumulationPoint').val(data.accumulationPoint);
                $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
                
                if(self.id == "btnReleaseVoucher"){
                    $('#btnReleaseVoucher').hide();
                    $('#btnRedeemVoucher').show();
                	$('#btnReleaseVoucher').prop('class','btn dis_s min_auto');
	                $('#btnReleaseVoucher').prop('disabled',true);
	                $('#btnRedeemVoucher').prop('disabled',false);
	                $('#btnRedeemVoucher').prop('class','btn add_s min_auto');
                }
                
                $('#point_useall').prop("checked", false);
                $('#hpoint_useall').prop("checked", false);
                $('#gc_egift_useall').prop("checked", false);
            }
        }//,
        //error: function(xhr,  Status, error) {
            //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
        //}
    }).always(function(){
        $(self).removeClass('on');
    });
}

function releasePVoucher(self){
    var code = ($("#selectPVoucher option:selected").val() == '') ? $('#pvoucherCode').val() : $("#selectPVoucher option:selected").val();
    
    if($('#pvoucherCode').val() != ''){
        var tmpCode = $('#pvoucherCode').val(); 
        $('#pvoucherCode').val(tmpCode.toUpperCase())
    }
    
    if($(self).prop('class') == 'btn dis_s min_auto'){
        return;
    }
    
    if(code == ''){
        layerAlert('정확한 쿠폰 코드를 다시 입력해 주시기 바랍니다.');
        return;
    }
    
    if ($(self).hasClass('on')) {
        layerAlert('처리중입니다.');
        return;
    }
    
    $(self).addClass('on');
    
    $.ajax({
        type: "GET",
        url: "/ko/checkout/releasePVoucher",
        dataType: "json",
        contentType:"application/json; charset=utf-8",
        data: {"voucherCode": code},
        success: function(data){
            if(data == "NEED_LOGIN"){
                alert("need login");
                location.href = "/ko/member/login";
            }
            
            if(data.errorMsg != ''){
                layerAlert('정확한 쿠폰 코드를 다시 입력해 주시기 바랍니다.');
                return;
            }else{
            	
            	checkoutCartView();
            	
                $('#pvoucherCode').val('');
                $('#select_pvoucher').html(data.selectPVoucher);
                $('.sum_box').html(data.totalPricePage);
                
                if(typeof fn_updateDiscInfo === "function"){
                    fn_updateDiscInfo();
                }
                checkRedVoucher();
                $('#pvoucherCode').prop('disabled',false);
                $('#selectPVoucher').prop('disabled',false);
                $('#selectPVoucher').val('').prop('selected', true);
                $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
                
                if(self.id == "btnReleasePVoucher"){
                	$('#btnReleasePVoucher').prop('class','btn dis_s min_auto');
                    $('#btnReleasePVoucher').prop('disabled',true);
                    $('#btnRedeemPVoucher').prop('disabled',false);
                    $('#btnRedeemPVoucher').prop('class','btn add_s min_auto');
                }
                
                $('#point_useall').prop("checked", false);
                $('#hpoint_useall').prop("checked", false);
                $('#gc_egift_useall').prop("checked", false);
            }
        }//,
        //error: function(xhr,  Status, error) {
            //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
        //}
    }).always(function(){
        $(self).removeClass('on');
    });
}


function removeAddress(addressId){
    if(confirm('해당 배송지를 최근 배송지 목록에서 삭제 하시겠습니까?')){
        $.ajax({
            type: "GET",
            url: "/ko/checkout/removeAddress",
            dataType: "json",
            data: {"addressId": addressId},
            success: function(data){
                if(data == "NEED_LOGIN"){
                    alert("need login");
                    location.href = "/ko/member/login";
                }
                
                $('#shippingDeliveryAddressList').html(data.shippingAddress);
            }//,
            //error: function(xhr,  Status, error) {
                //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
            //}
        });
    }
}

function checkRedVoucher() {
    
    var total = $.trim($("#total").val());
    if(total == "") {
    	total = 0;
    }

    /* 2020.05.20 레드 바우처 결제 금액이 안되더라도 레드 바우처 결제수단 선택 계속 노출
    if(parseInt(total) >= parseInt('300000')) {
    	$("#paymentRedVoucher").show();
    } else {
    	var mode = $("[name='mode']:checked").val();
    	if(mode == "KO001-4") {
    		$("#sel_rd1").click();
    	}
    	$("#paymentRedVoucher").hide();
    }
    */
    
}


function chkword(targetID,lengthID,maxLength){
    var strValue = $('#'+targetID).val();
    var strLen = strValue.length;
    var maxByte = parseInt(maxLength);
    var str2 = "";

    if(strLen > maxByte){
        layerAlert(maxByte + "자를 초과 입력 할 수 없습니다.");
        str2 = strValue.substr(0, maxByte);
        $('#'+targetID).val(str2);
        strLen = str2.length;
    }
    
    $('#'+lengthID).text(strLen);
}
// 10만 H.Point 이상 사용시 휴대폰 점유 인증 : 통멤서버로 부터 인증번호 받아서 저장 
function hpointAuthHandphone() {
    $.ajax({
         type: 'GET',
         url: "/ko/checkout/hpointAuthHandphone",
         async: false,
         data : null,          
         dataType : "json",
         success: function(data) {
             if (data.respCd == '0000') {
                  $('#hpointForm').find('input[name=certNo]').val(data.certNo); 

              } else {
                  $('.btn_close').click();
                  layerAlert(data.notfMsgCntn1);
              }
             
         },
         error: function(xhr, status, err) {
             var console = window.console || {log:function(){}};
             console.log("status : " + status + "\n error : " + err);
         }
    });

}
// 10만 H.Point 이상 사용시 휴대폰 점유 인증 - 사용자 입력 
function checkHPassword(){
    var codeInput = "";
    var certNo = "";
    
    codeInput = $('#codeInput').val();
    certNo = $('#hpointForm').find('input[name=certNo]').val();
    if(codeInput == '') {
        layerAlert("인증번호를 입력해 주세요.");
        return;
    }
    if(codeInput.length != 6) {
        layerAlert("인증번호 6자리를 입력해 주세요.");
        return;
    }
    
    if(certNo != codeInput )
    {
        $('.btn_close').click();
        layerAlert("인증번호가 일치하지 않습니다.");
    }
    else
    {
        $.ajax({
            type: 'GET',
            url: "/ko/checkout/hpointAuthCheck",
            async: false,
            data : {"certNo":certNo},          
            dataType : "json",
            success: function(data) {
                if (data == true) {
                    $('.btn_close').click();
                    var pnt = $('#userInfoForm').find('input[name=pnt]').val();
                    setUseHPoint(pnt);

                 } else {
                     $('.btn_close').click();  
                }
                
            },
            error: function(xhr, status, err) {
                var console = window.console || {log:function(){}};
                console.log("status : " + status + "\n error : " + err);
            }
       });
          
    }   
    
}

function checkPassword(){
    var pw = $.trim($('#pw').val());
    if($.trim(pw) == '') {
        layerAlert("비밀번호를 입력해 주세요.", false, true);
        return;
    }
    
    var pw = $("#pw").val();
	    pw = pw.replace(/#/g,"[({})]");
	    pw = pw.replace(/>/g,"{[()]}");
	    pw = pw.replace(/</g,"({[]})");
	    

    $.ajax({
        type:"POST",
        url:"/ko/checkout/getSubPaymentPwAuth",
        data:{"uid":$("#uid").val(), "pw":pw, "CSRFToken":"fdcc9003-3927-481c-9d6d-70be0aba5887"},
        success: function(data){
            if(data == true){
                //placeorder();
                //$('#firstAuth').val('Y');
                $(".btn_close").click();
                
                var gubun = $('#userInfoForm').find('input[name=gubun]').val();
                var pnt = $('#userInfoForm').find('input[name=pnt]').val();

                if(gubun == 'use_point'){
                    setUsePoint(pnt);
                }
                else if(gubun == 'use_hpoint'){
                    setUseHPoint(pnt);
                }else if(gubun == 'use_egift_amount'){
                    setUseEGift(pnt);
                }
                else{
                    setUseGiftAmount(pnt);
                }
            }else{
                $("#pwMsg").text('입력하신 아이디 혹은 비밀번호가 일치하지 않습니다.');
                return;
            }
        }//,
        //error: function(xhr,  Status, error) {
            //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
        //}
    });
}


function doOrder(){
	var fourpmStockProcess = false;
	var fourpmTimeProcess = false;
	
    
    
	    if($("[name='mode']:checked").val() == "KO001-3"){

            var agent = navigator.userAgent.toLocaleLowerCase();

            if(agent.indexOf("chrome") > -1 && agent.indexOf("/80") > -1 ){
                /*
                var msg = "크롬 브라우저 80 버전 업데이트 이슈에 따라 원클릭결제 서비스 이용이 불가 할 수<br>있습니다.(쿠키 정책 변경)<br><br>" +
                    "원활한 원클릭결제를 위해<br>크롬 외 다른 브라우저를 통해 이용해주세요.<br><br>" +
                    "빠른 시일 내에 정상화 될 수 있도록<br>노력하겠습니다.";

                new layerAlert(msg);
                return  false;
                */
            }

	        if(parseInt($('#total').val()) > 0){
		    	var id = $('input[name="card_choice"]:checked').attr('id');
		    	if (typeof id == "undefined") {
		    		var lc = new layerAlert("결제를 진행 하실 카드를 선택해 주세요.");
			        lc.confirmAction = function(){
			        	
			        };
			    	
		    		return;
		    	}
	        }
	    }
	    
	    // 2020.05.20 레드 바우처 선택 시 결제 금액 비교 하여 결제 여부 판단
	    if($("[name='mode']:checked").val() == "KO001-4"){
	        if(parseInt($('#total').val()) > 0){ // PlaceOrder 확인
	            if(parseInt($('#total').val()) < parseInt('300000')){
		            var lc = new layerAlert("최종 결제 금액 30만원 이상인 경우 현대카드 레드 쇼핑바우처 사용 가능합니다.<br><br> 결제금액을 다시 확인해 주세요.");
			        lc.confirmAction = function(){
			        	
			        };
			    	
		    		return;	            
		        }
	        }
	    }
	    
	    
	
	//퀵배송 최종 검증
	
    	
    if($("#chk_giftAmount").val().length>0 && $("#chk_pointAmount").val().length>0) {
    	
    	layerAlert("한섬마일리지와 기프트카드는 함께 사용할 수 없습니다.");
    	return;
    }
    
    if($('#adress').val() == ''){
        layerAlert("배송지 주소 우편번호를 입력해 주세요.");
        return;
    }

    if($('#line1').val() == ''){
        layerAlert("배송지 주소 첫번째 주소를 입력해 주세요.");
        return;
    }
    
    
	    if($.trim($('#line2').val()) == ''){
	        layerAlert("나머지 주소를 입력해 주세요.");
	        return;
	    }
    
    
	    if($.trim($('#rcpt_name').val()) == ''){
	        layerAlert("수령인을 입력해 주세요.");
	        return;
	    }
	
	    
    if($("#rcpt_name").length > 0) {
	    if($("#rcpt_name").val().length > 10) {
	        layerAlert("수령인은 10자리 이하로 입력해주세요.");
	        return;
	    }
    }
    
    if($('#hp').val() == '' || $('#hp_num2').val() == '' || $('#hp_num3').val() == ''){
        layerAlert("배송지 주소 휴대폰 번호를 입력해 주세요.");
        return;
    }

    if(!$('#agree').is(":checked")){
        layerAlert("구매자 동의 항목을 체크하여 주세요.");
        return;
    }
    
 	// 2017.01.31 - 이현승, 휴대폰번호 데이터 검증 (숫자만입력가능.) >>>> START
 	
	var inputNumberchk = /^[0-9]+$/;
 	
 	var hp = $("#hp").val();
	var hpNum2 = $("#hp_num2").val();
	var hpNum3 = $("#hp_num3").val();
	
	// 휴대폰 앞자리 체크
	if(hp==null || hp==""){
		var la = new layerAlert(" <b>*</b> 표시는 필수항목입니다.");
		la.confirmAction = function(){
			$("#agree").attr('checked', false);
			$("#hp").focus();
		};
		return;
	} else {
		for(i=0; i<hp.length; i++){
			var str = hp.substr(i,1);
			if (!inputNumberchk.test(str)){
				var la = new layerAlert("전화번호는 숫자만 입력가능 합니다.");
				la.confirmAction = function(){
					$("#agree").attr('checked', false);
					$("#hp").focus();
				};
				return;
			}
		}
	}
	
	for(i=0; i<hpNum2.length; i++){
		var str = hpNum2.substr(i,1);
		if (!inputNumberchk.test(str)){
			var la = new layerAlert("전화번호는 숫자만 입력가능 합니다.");
			la.confirmAction = function(){
				$("#agree").attr('checked', false);
				$("#hp_num2").focus();
			};
			return;
		}
	}
	for(i=0; i<hpNum3.length; i++){
		var str = hpNum3.substr(i,1);
		if (!inputNumberchk.test(str)){
			var la = new layerAlert("전화번호는 숫자만 입력가능 합니다.");
			la.confirmAction = function(){
				$("#agree").attr('checked', false);
				$("#hp_num3").focus();
			};
			return;
		}
	}
	
	if($("#ph_num2").val() != null && $("#ph_num2").val().length > 0){
		var phNum2 = $("#ph_num2").val();
		for(i=0; i<phNum2.length; i++){
			var str = phNum2.substr(i,1);
			if (!inputNumberchk.test(str)){
				var la = new layerAlert("전화번호는 숫자만 입력가능 합니다.");
				la.confirmAction = function(){
					$("#agree").attr('checked', false);
					$("#ph_num2").focus();
				};
				return;
			}
		}
	}
	
	if($("#ph_num3").val() != null && $("#ph_num3").val().length > 0){
		var phNum3 = $("#ph_num3").val();
		for(i=0; i<phNum3.length; i++){
			var str = phNum3.substr(i,1);
			if (!inputNumberchk.test(str)){
				var la = new layerAlert("전화번호는 숫자만 입력가능 합니다.");
				la.confirmAction = function(){
					$("#agree").attr('checked', false);
					$("#ph_num3").focus();
				};
				return;
			}
		}
	}
	
	// 2017.01.31 - 이현승, 휴대폰번호 데이터 검증 (숫자만입력가능.) >>>> END
	
	// 매장수령, 간편회원일때, 휴대폰번호 데이터 검증 (숫자만입력가능.) >>>> START
    
    // 매장수령, 간편회원일때, 휴대폰번호 데이터 검증 (숫자만입력가능.) >>>> END
    
    
    
    //공동현관출입 hyunbae
    
    var enterDoor = $('#enter_door').val();
    if($.trim($('#orderr').val()) === '부재 시 문 앞에 놓아주세요'){
    	if(enterDoor === '공동현관 출입방법을 선택해주세요'){
    		layerAlert('공동현관 출입방법을 선택해주세요.');
			return;
    	}else if(enterDoor === '자유 출입 가능'){
    		$('#frontDoorMessage').val('자유출입가능');
    	}else{
    		if($.trim($('#frontDoorMessage').val()) == ''){
    			layerAlert('공동현관 출입방법을 입력해 주세요');
    			return;
    		}
    	}
    }else if($.trim($('#orderr').val()) !== '부재 시 문 앞에 놓아주세요' && $.trim($('#orderr').val()) !== '직접입력'){
    	if(enterDoor === '자유 출입 가능'){
    		$('#frontDoorMessage').val('자유출입가능');
    	}
    }
    
    if($('#frontDoorMessage').val()){
    	$('#frontDoorMessage').val($('#frontDoorMessage').val().replace(/#/gi,'＃'));
    }
    
    //delivery address email
    if($.trim($('#mail').val()) != '' && $.trim($('#emailDely').val()) != ''){
        $('#email').val($.trim($('#mail').val()) + '@' + $('#emailDely').val());
    }
    
    if($('input:radio[name="mode"]:checked').length == 0){
        layerAlert("결제수단을 선택해 주세요.");
        return;
    }
    
    if($('#pointpay').val() != '' && $('#pointpay').val() != '0' && $('#pointpay').val() > parseInt(2620)){
        layerAlert("한섬마일리지 사용액은 결제할 금액보다 같거나 작아야 합니다.");
        return;
    }
    if($('#hpointpay').val() != '' && $('#hpointpay').val() != '0' && $('#hpointpay').val() > parseInt(2059)){
        layerAlert("H.Point 사용액은 결제할 금액보다 같거나 작아야 합니다.");
        return;
    }
    if($('#giftpay').val() != '' && $('#giftpay').val() != '0' && $('#giftpay').val() > parseInt(0)){
        layerAlert("기프트카드 사용액은 결제할 금액보다 같거나 작아야 합니다.");
        return;
    }
    if($('#egiftpay').val() != '' && $('#egiftpay').val() != '0' && $('#egiftpay').val() > parseInt(0)){
        layerAlert("e-money 사용액은 결제할 금액보다 같거나 작아야 합니다.");
        return;
    }
    
    if(parseInt($('#total').val()) == 0){
		var documentResult = "";
		
        	documentResult = "<a class=\"btn gray\" onclick=\"layerAlert('처리중입니다.');\">결제하기</a>";
				
        $("#doOrderBtn").html(documentResult);
    }
    
    if($('#pointpay').val() == '') $('#pointpay').val('0');
    if($('#hpointpay').val() == '') $('#hpointpay').val('0');
    if($('#giftpay').val() == '') $('#giftpay').val('0');
    if($('#egiftpay').val() == '') $('#egiftpay').val('0');
    
    
    if($("[name='mode']:checked").val() == 'KO001-3') {
        var $wpayCardCode = $("input[name=card_choice]:checked");
        var $wpayInstallment = $wpayCardCode.parent().parent().find("#card_pay_term_" + $wpayCardCode.val());
        //var $wpayInstallment = $("#card_pay_term_" + $wpayCardCode.val());
        
        $("input[name=wpayToken]").val($wpayCardCode.data('token'));        
        $("input[name=cardQuota]").val($wpayInstallment.val()); 
        $("input[name=cardInterest]").val($wpayInstallment.find('option:selected').data('nointstallment')||'N');    
    }
    
    $.ajax({
        type: "GET",
        url: "/ko/checkout/checkOnlineStock",
        dataType: "json",
        success: function(data){
            if(data == '')
            {
			
	                if(parseInt($('#total').val()) == 0){
	                    $('#doOrder').prop('disabled',true);
	                    $('#orderForm').prop('action', '/ko/checkout/placepoint'); 
	                    setEcommerceData("Checkout2(바로주문)");
	                    $('#orderForm').submit();
	                }else{
	                    placeorder();
	                }
				
            }
            else
            {
                var documentResult = "";
                
                	documentResult = "<a class=\"btn gray\" onclick=\"doOrder();\">결제하기</a>";
        		
                
                $("#doOrderBtn").html(documentResult);
                //alert(data);
                
                var lc = "";
                if(data == "entrySite") {
                	lc = new layerAlert("일시적인 오류가 발생하였습니다. <br/> 쇼핑백으로 이동합니다. ");
                } else if(data == "mileage_shortage") {
                	lc = new layerAlert("한섬마일리지가 부족합니다.");
                } else if(data == "egiftcard_shortage") {
                	lc = new layerAlert("e-money가 부족합니다.");                	
                } else if(data == "giftcard_shortage") {
                	lc = new layerAlert("기프트카드가 부족합니다.");
                } else if(data == "hpoint_shortage") {
                	lc = new layerAlert("H.Point가 부족합니다.");
                } else if(data == "storePickupOnlineStock") {
                	lc = new layerAlert("수량 미달로 인해 일반배송으로 변경됩니다.");
                } else if(data == "cartError") {
                    lc = new layerAlert("일시적인 오류가 발생하였습니다. <br/> 브라우저를 닫고 다시 주문해 주시기 바라며, 계속 주문이 안되신다면 다른 컴퓨터 또는 다른 모바일기기에서 주문을 시도해 주시기 바랍니다.");
                } else {
	                lc = new layerAlert(data + " 해당 상품의 재고 수량이 충분하지 않습니다. ");
                }
                
                lc.confirmAction = function(){
		    		location.href = "/ko/shoppingbag";
		    	};
            }
        }//,
        //error: function(xhr,  Status, error) {
        //  alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
        //}
    });
}

function loadingOff(){
    //로딩바제거
    if($("#loadingBarDiv").length > 0){
        //$(".layerLoading_bar").remove();
    }
    
	if($(".layerArea:last").length > 0){
	    //$(".layerArea:last").remove();
    }
}

function placeorder()
{
    setEcommerceData("Checkout2(바로주문)");
    
    var referUrlTag = "<input type=hidden name='wpayReferUrl' id='wpayReferUrl' value='https'>";
    $('#orderForm').append(referUrlTag);
    
	$.ajax({
		type: "POST",
		url: "/ko/checkout/placeorder",
		dataType: "json",
		data: $('#orderForm').serialize(),
		success: function(data){
			if(data.errorMsg != null && data.errorMsg.length > 0)
			{
				var error = data.errorMsg; 
				if (error == "VoucherUseError" || error == "PaymentAmountCheckError")
				{
                	var message = "";
			    	if (error == "VoucherUseError"){
			    		message = "일시적인 오류가 발생하였습니다. <br/> 쇼핑백으로 이동합니다. ";
			    		var lc = new layerAlert(message);
				    	lc.confirmAction = function(){
				    		location.href = "/ko/shoppingbag";
				    	};
			    	} else {
			    		location.href = "/ko/shoppingbag";
			    	}
				} else if (error == "CellPhoneIsEmptyError"){
					var lc = new layerAlert("배송지 주소/수령인/휴대폰번호를 입력하여 주십시오");
			    	lc.confirmAction = function(){
			    		$('#hp_num2').focus();
			    	};
			    	return;
				} else if (error == "CellPhoneIsNumericError_KO" || error == "CellPhoneIsNumericError"){
					var alertMsg = (error.indexOf("_KO") > 0) ? "전화번호는 숫자만 입력가능 합니다." : "checkout.shipto.phone.msg";
					var lc = new layerAlert(alertMsg);
			    	lc.confirmAction = function(){
			    		$('#hp_num2').focus();
			    	};
			    	return;
				} else if (error == "PhoneIsNumericError"){
					var lc = new layerAlert("전화번호는 숫자만 입력가능 합니다.");
			    	lc.confirmAction = function(){
			    		$('#ph_num2').focus();
			    	};
			    	return;
				} else if (error == "OrderMobilePhoneIsNumericError"){
					var lc = new layerAlert("전화번호는 숫자만 입력가능 합니다.");
			    	lc.confirmAction = function(){
			    		$('#hp1').focus();
			    	};
			    	return;
				} else if (error == "VirtualAccountStopCustomer"){
				    var lc = new layerAlert("고객님은 [가상계좌 주문] 이용불가 대상입니다<br>다른 결제수단 이용 부탁드립니다. 감사합니다.");
			    	lc.confirmAction = function(){
			    	    
			    	};
			    	return;
				} else {
					$('#agree').prop("checked", false);
					$('#payinfo').html(data.errorMsg);
					layerAlert($("일시적인 오류가 발생하였습니다. <br/> 브라우저를 닫고 다시 주문해 주시기 바라며, 계속 주문이 안되신다면 다른 컴퓨터 또는 다른 모바일기기에서 주문을 시도해 주시기 바랍니다.").val());
					return;
				}
			}
			else
			{
				
						switch($('#paytype').val()){
						case "LGCNS":
							$('#PAY_FORM').html(data.smilePayinfo);
							getTxnId();
							break;
						default:
		                    $('#paytype').val('Inicis');
		                    $('#PAY_FORM').html(data.inicisPayinfo);
							
							if ($("[name='mode']:checked").val() == "KO001-3") {
		                    	
		                    	var wpayData = WPAY.convertFormToJSON( $('#PAY_FORM'));
	                    	    //즉시할인
	                    	    if($("input[name=discInfo]").val() != ""){
                                	wpayData.couponCode = $("input[name=discInfo]").val();
	                    	    }
	                    	    
	                    	    //$("body").append('<div class="layerArea" id="productLayer"><div class="layerBg"></div></div>');
	            	            
	            	            var loadingHtml = "";
	                            loadingHtml += '<div class="layerLoading_bar" id="loadingBarDiv" style="width: 100%;height: 100%;top:0;">';
	                            loadingHtml += '    <img src="http://cdn.thehandsome.com/pc/order/loading_spinner_007s_190827.gif" alt="loading">';
	                            loadingHtml += '</div>';
	                        
	                            //$("body").append(loadingHtml);
	                    	    
		                    	WPAY.checkoutAuthSubmit(wpayData);
		                    } else {
                                disabledHistoryBack();
			                    launchIniPay();
								break;
		                    }
						}
						
					
			}
		},
		error: function(xhr,  Status, error) {
			$('#agree').prop("checked", false);
		    layerAlert("일시적인 오류가 발생하였습니다. <br/> 브라우저를 닫고 다시 주문해 주시기 바라며, 계속 주문이 안되신다면 다른 컴퓨터 또는 다른 모바일기기에서 주문을 시도해 주시기 바랍니다.");
			return;
        }
	});
} 

var launchIniPayAlipay = function () {
	var $this = this;
	
	/**
	 * popup 닫기 이벤트 체크
	 */
	 $this.popupCloseCheck = function (winobj) {
		if (winobj != null) {
			if (winobj.closed) {
				$('.hsome_inicis_modal').hide();
			} else {
				setTimeout(function () {
					$this.popupCloseCheck(winobj);
				}, 1000);
			}
		}
	};

	/**
	 * Modal 생성
	 */
	$('.hsome_inicis_modal').remove();
	$('<div/>').addClass('hsome_inicis_modal').css({
	    'position': 'fixed',
	    'top': '0',
	    'right': '0',
	    'bottom': '0',
	    'left': '0',
	    'z-index': '10400',
		'opacity': '0.3',
	    'background-color': '#000000',
	    'display': 'none'
	}).appendTo($('body'));
	
	/**
	 * window open
	 */
	var options = [];
	options.push("width=1030");
	options.push("height=850");
	options.push("top="+((window.screen.height / 2) - (850 / 2)));
	options.push("left="+((window.screen.width / 2) - (1030 / 2)));
	options.push("scrollbars=1");
	options.push("resizable=1");
	
	var alipayWin = window.open("about:blank", "alipayWin", options.join(','));
	
	if (alipayWin == null || alipayWin.screenLeft == 0) {
		
		return;
	}
	$('.hsome_inicis_modal').show();
	$this.popupCloseCheck(alipayWin);
	
	/**
	 * form submit
	 */
	var $inipayForm = $("#PAY_FORM");
	$inipayForm.attr({
		'action': $inipayForm.find('[id="pgpaymenturl"]').val(),
		'target': "alipayWin"
	});
	$inipayForm.submit();
}


function doPwcheck(self, gubun, subUseAmt){
	
	
    var pw = $("#pw").val();
    pw = pw.replace(/#/g,"[({})]");
    pw = pw.replace(/>/g,"{[()]}");
    pw = pw.replace(/</g,"({[]})");
    
	$.ajax({
        type: "POST",
        url: "/ko/checkout/getSubPaymentPwAuth",
        dataType: "json",
        async: false,
   		data:{"uid":$("#uid").val(), "pw":pw, "CSRFToken":"fdcc9003-3927-481c-9d6d-70be0aba5887"},
        success: function(data){
        	if(data == true) {
        		if(gubun == "1") {
	        		setUsePoint(subUseAmt);
        		} else if(gubun == "2") {
        			setUseGiftAmount(subUseAmt);
        		} else if(gubun == "3") {
        			setUseHPoint(subUseAmt);
        		} else if(gubun == "4") {
                    setUseEGift(subUseAmt);
                }
        	} else {
        		if(gubun == "1") {
        			showlayer(self, '#popwrap2', 'use_point', subUseAmt);
        		} else if(gubun == "2") {
        			showlayer(self, '#popwrap2', 'use_gift_amount', subUseAmt);
        		} else if(gubun == "3") {
        			showlayer(self, '#popwrap2', 'use_hpoint', subUseAmt);
        		} else if(gubun == "4") {
                    showlayer(self, '#popwrap2', 'use_egift_amount', subUseAmt);
                }
				
				$("#pw").focus();
        	}
        },
        error: function(xhr,  Status, error) {
        	alert('getSubPaymentPwAuth sendRequest error : ' + "[gubun : " + gubun + "]" + xhr.status + " ( " + error + " ) " );
    	}
	});
}

function doUsePoint(self){
    var use_point = $('#pointpay').val();
    var use_gift_amount = $('#giftpay').val(); 
    var deliveryCost = $('#cartDeliveryCost').val();
    var saleCheck = "0";
    var voucherRateCheck = $("#voucherRateCheck").val();

    if($(self).prop('class') == 'btn dis_s min_auto'){
        return;
    }
    
    if(use_point == '' || use_point == '0'){
        layerAlert("사용하실 포인트를 입력해주세요.");
        return;
    }
    
    if($("#btnUsePoint").attr("class") == "btn add_s min_auto" && $("#btnUseGiftAmount").attr("class") == "btn add_s min_auto") { //둘다 적용 전
        $('#giftpay').val("");
    }else {
        if(use_gift_amount > 0){
            layerAlert("한섬마일리지/기프트카드 중복사용 할 수 없습니다.");
            return;
        }  
    }
    
    if(parseInt(2620) < 0){
        layerAlert("사용 가능한 한섬마일리지가 없습니다.");
        $('#pointpay').val("");
        return;
    }
    
    if(saleCheck == "1"){
        layerAlert("10% 할인율을 초과하는 세일상품 구매시 한섬마일리지를 사용하실 수 없습니다.");
        return;
    }
    
    if(voucherRateCheck == "1"){
        layerAlert("할인율이 10%를 초과하여 한섬마일리지를 사용하실 수 없습니다.");
        return;
    }

    if(use_point > parseInt($('#total').val())){
        layerAlert("한섬마일리지 사용액은 결제할 금액보다 같거나 작아야 합니다.");
        return;
    }
    
    if(parseInt(deliveryCost) > 0){
        var total = $('#total').val();
        if(parseInt(deliveryCost) > 0){
            if((parseInt(total) <= parseInt(deliveryCost) && parseInt(use_point) >= parseInt(total)) 
                    || (parseInt(total) >= parseInt(deliveryCost) && parseInt(use_point) > (parseInt(total) - parseInt(deliveryCost))))
            {
                layerAlert("배송비는 한섬마일리지/기프트카드로 결제하실 수 없습니다.");
                $('#pointpay').val(0);
                return;
            }
        }
    }
    
    if( checktHandsomepointUsableAmount == 0 ){
        $('#pointpay').val(0);
        layerAlert("제품 당 실결제금액(할인 적용가)이 3만원 이하인 제품은 한섬 마일리지를 사용하실 수 없습니다."); 
        return;
    }
    
    if( use_point > checktHandsomepointUsableAmount ){
        layerAlert("제품 당 실결제금액(할인 적용가)이 3만원 이하인 제품은 한섬 마일리지를 사용하실 수 없습니다."); 
        $('#pointpay').val('');
        if( $('#point_useall').prop("checked") ) {
            $('#point_useall').prop("checked", false);
            $('#pointpay').val(checktHandsomepointUsableAmount);
        }else {
            $('#pointpay').val('')
        }
        return;
    }
    
    doPwcheck(self, '1', use_point);
}

function doUseHPoint(self){
    var use_point = $('#hpointpay').val();
    var deliveryCost = $('#cartDeliveryCost').val();
    var saleCheck = "0";
    var voucherRateCheck = $("#voucherRateCheck").val();

    
    
    if(use_point == '' || use_point == '0' ){
        layerAlert("사용하실 포인트를 입력해주세요.");
        return;
    }

    // 2020.03.13 hmk
    // h point 최저 사용금액 100원으로 변경
    if(use_point < 100 ){
        layerAlert("100 포인트 이상부터 사용하실 수 있습니다.");
        return;
    }
    
    if(parseInt(2059) < 0){
        $('#hpointpay').val("");
        return;
    }
    

    
    //if(saleCheck == "1"){
    //    layerAlert("10% 할인율을 초과하는 세일상품 구매시 포인트를 사용하실 수 없습니다.");
    //    return;
    //}
    
    if(voucherRateCheck == "1"){
        layerAlert("할인율이 10%를 초과하여 포인트를 사용하실 수 없습니다.");
        return;
    }
    
        
    if(use_point > parseInt($('#total').val())){
        layerAlert("H.Point 사용액은 결제할 금액보다 같거나 작아야 합니다.");
        return;
    }
    // 가용 포인트, 사용포인트 10만 이상일때 
    if(use_point >= 100000 && parseInt(2059) >= 100000){
        $('#counter').html("03:00초");  
        $('#codeInput').val('');
        showlayer(self, '#popwrap3', 'use_hpoint', use_point);
        return;
    }
    
    if(parseInt(deliveryCost) > 0){
        var total = $('#total').val();
        if(parseInt(deliveryCost) > 0){
            if((parseInt(total) <= parseInt(deliveryCost) && parseInt(use_point) >= parseInt(total)) 
                    || (parseInt(total) >= parseInt(deliveryCost) && parseInt(use_point) > (parseInt(total) - parseInt(deliveryCost))))
            {
                layerAlert("배송비는 H.Point로 결제하실 수 없습니다.");
                $('#hpointpay').val(0);
                return;
            }
        }
    }
  
    doPwcheck(self, '3', use_point);
}   

function doUseEGiftAmount(self){
    var use_point = $('#egiftpay').val();
    var deliveryCost = $('#cartDeliveryCost').val();

    if(use_point == '' || use_point == '0' ){
        layerAlert("사용하실 금액을 입력해주세요.");
        return;
    }

    if(parseInt(0) < 0){
        $('#egiftpay').val("");
        return;
    }
   
    if(use_point > parseInt($('#total').val())){
        layerAlert("e-money 사용액은 결제할 금액보다 같거나 작아야 합니다.");
        return;
    }
    if(parseInt(deliveryCost) > 0){
        var total = $('#total').val();
        if(parseInt(deliveryCost) > 0){
            if((parseInt(total) <= parseInt(deliveryCost) && parseInt(use_point) >= parseInt(total)) 
                    || (parseInt(total) >= parseInt(deliveryCost) && parseInt(use_point) > (parseInt(total) - parseInt(deliveryCost))))
            {
                layerAlert("배송비는 e-money로 결제하실 수 없습니다.");
                $('#egiftpay').val(0);
                return;
            }
        }
    }
    
    doPwcheck(self, '4', use_point);
}

function cancelUsePoint(self){
    var use_point = $('#pointpay').val();

    if($(self).prop('class') == 'btn dis_s min_auto'){
        return;
    }
    
    if(use_point == '' || use_point == '0'){
        return;
    }
    
    $.ajax({
        type: "GET",
        url: "/ko/checkout/cancelUsePoint",
        dataType: "json",
        //data: {"usePoint": use_point},
        success: function(data){
            if(data == "NEED_LOGIN"){
                alert("need login");
                location.href = "/ko/member/login";
            }
            
            if(data.errorMsg != ''){
                return;
            }else{
                $('.sum_box').html(data.totalPricePage);
                //2019.7.4 khg
                setDeliveryMode();
                
                fn_updateDiscInfo();
                
                checkRedVoucher();
                $('#pointpay').val('');
                $('#pointpay').prop('readonly',false);
                $(self).prop('class','btn dis_s min_auto');
                $(self).prop('disabled',false);
                $('#btnUsePoint').prop('disabled',false);
                $('#btnUsePoint').prop('class','btn add_s min_auto');
                $('#point_useall').prop("checked", false);
                $('#point_useall').prop('disabled',false);
                $('#accumulationPoint').val(data.accumulationPoint);
                $('#accumulationHPoint').val(data.accumulationHPoint);
                $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
                
                
                $('#chk_pointAmount').val("");
            }
        }//,
        //error: function(xhr,  Status, error) {
            //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
        //}
    });
}

function cancelUseHPoint(self){
    var use_point = $('#hpointpay').val();

    if($(self).prop('class') == 'btn dis_s min_auto'){
        return;
    }
    
    if(use_point == '' || use_point == '0'){
        return;
    }
    
    $.ajax({
        type: "GET",
        url: "/ko/checkout/cancelUseHPoint",
        dataType: "json",
        //data: {"usePoint": use_point},
        success: function(data){
            if(data == "NEED_LOGIN"){
                alert("need login");
                location.href = "/ko/member/login";
            }
            
            if(data.errorMsg != ''){
                return;
            }else{
                $('.sum_box').html(data.totalPricePage);
                //2019.7.4 khg
                setDeliveryMode();
                
                fn_updateDiscInfo();
                
                checkRedVoucher();
                $('#hpointpay').val('');
                $('#hpointpay').prop('readonly',false);
                $(self).prop('class','btn dis_s min_auto');
                $(self).prop('disabled',false);
                $('#btnUseHPoint').prop('disabled',false);
                $('#btnUseHPoint').prop('class','btn add_s min_auto');
                $('#hpoint_useall').prop("checked", false);
                $('#hpoint_useall').prop('disabled',false);
                $('#accumulationPoint').val(data.accumulationPoint);
                $('#accumulationHPoint').val(data.accumulationHPoint);
                $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
                
                
                $('#chk_pointAmount').val("");
            }
        }//,
        //error: function(xhr,  Status, error) {
            //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
        //}
    });
}

function setUsePoint(use_point){
        $.ajax({
            type: "GET",
            url: "/ko/checkout/usePoint",
            dataType: "json",
            data: {"usePoint": use_point},
            success: function(data){
                if(data == "NEED_LOGIN"){
                    alert("need login");
                    location.href = "/ko/member/login";
                } else if(data == "BAD_AUTH") {
					layerAlert("잘못된 접근입니다.");
					return;
                }
                
                if(data.errorMsg != ''){
                	layerAlert(data.errorMsg);
                    return;
                }else{
                    $('.sum_box').html(data.totalPricePage);
                    //2019.7.4 khg
                    setDeliveryMode();
                    
                    fn_updateDiscInfo();
                    
                    checkRedVoucher();
                    $('#pointpay').prop('readonly',true);
                    $('#btnUsePoint').prop('class','btn dis_s min_auto');
                    $('#btnUsePoint').prop('disabled',true);
                    $('#btnCancelUsePoint').prop('disabled',false);
                    $('#btnCancelUsePoint').prop('class','btn add_s min_auto');
                    $('#point_useall').prop('disabled',true);
                    $('#accumulationPoint').val(data.accumulationPoint);
                    $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                    $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
                    
                    
                    $('#chk_pointAmount').val(use_point);
                }
            }//,
            //error: function(xhr,  Status, error) {
                //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
            //}
        });
}

function setUseEGift(use_egift_amount){
    $.ajax({
        type: "GET",
        url: "/ko/checkout/useEGiftAmount",
        dataType: "json",
        data: {"useEGiftAmount": use_egift_amount},
        success: function(data){
            if(data == "NEED_LOGIN"){
                location.href = "/ko/member/login";
            } else if(data == "BAD_AUTH") {
                layerAlert("잘못된 접근입니다.");
                return;
            }
            
            if(data.errorMsg != ''){
                layerAlert(data.errorMsg);
                return;
            }else{
                $('.sum_box').html(data.totalPricePage);
                //2019.7.4 khg
                setDeliveryMode();
                
                fn_updateDiscInfo();
                
                checkRedVoucher();
                $('#egiftpay').prop('readonly',true);
                $('#btnUseEGiftAmount').prop('class','btn dis_s min_auto');
                $('#btnUseEGiftAmount').prop('disabled',true);
                $('#btnCancelUseEGiftAmount').prop('disabled',false);
                $('#btnCancelUseEGiftAmount').prop('class','btn add_s min_auto');

                $('#gc_egift_useall').prop('disabled',true);
                $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
            }
        }
    });
}

function setUseHPoint(use_point){
       $.ajax({
           type: "GET",
           url: "/ko/checkout/useHPoint",
           dataType: "json",
           data: {"useHPoint": use_point},
           success: function(data){
               if(data == "NEED_LOGIN"){
                   alert("need login");
                   location.href = "/ko/member/login";
               } else if(data == "BAD_AUTH") {
            	   layerAlert("잘못된 접근입니다.");
            	   return;
               }
               
               if(data.errorMsg != ''){
            	   layerAlert(data.errorMsg);
                   return;
               }else{
                   $('.sum_box').html(data.totalPricePage);
                   //2019.7.4 khg
                   setDeliveryMode();
                   
                   fn_updateDiscInfo();
                   
                   checkRedVoucher();
                   $('#hpointpay').prop('readonly',true);
                   $('#btnUseHPoint').prop('class','btn dis_s min_auto');
                   $('#btnUseHPoint').prop('disabled',true);
                   $('#btnCancelUseHPoint').prop('disabled',false);
                   $('#btnCancelUseHPoint').prop('class','btn add_s min_auto');
                   $('#hpoint_useall').prop('disabled',true);
                   $('#accumulationPoint').val(data.accumulationPoint);
                   $('#accumulationHPoint').val(data.accumulationHPoint);
                   $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                   $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
                    
                   //$('#chk_pointAmount').val(use_point);
                
               }
           }//,
           //error: function(xhr,  Status, error) {
               //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
           //}
       });
}

function doUseGiftAmount(self){
    var use_gift_amount = $('#giftpay').val();
    var use_point = $('#pointpay').val(); 
    var saleCheck = "0";
    var voucherRateCheck = $("#voucherRateCheck").val();

    if($(self).prop('class') == 'btn dis_s min_auto'){
        return;
    }
    
     
    
    if(use_gift_amount == '' || use_gift_amount == '0'){
        layerAlert("사용하실 금액을 입력해주세요.");
        return;
    }
    
    if($("#btnUsePoint").attr("class") == "btn add_s min_auto" && $("#btnUseGiftAmount").attr("class") == "btn add_s min_auto") { //둘다 적용 전
        $('#pointpay').val("");
    }else {
        if(use_point > 0){
            layerAlert("한섬마일리지와 기프트카드는 함께 사용할 수 없습니다.");
            return;
        }  
    }
    
    if(parseInt(0) < 0){
        $('#giftpay').val("");
        return;
    }
    
    if(saleCheck == "1"){
        layerAlert("10% 할인율을 초과하는 세일상품 구매시 기프트카드를 사용하실 수 없습니다.");
        return;
    }
    
    if(voucherRateCheck == "1"){
        layerAlert("할인율이 10%를 초과하여 기프트카드를 사용하실 수 없습니다.");
        return;
    }
    
    if(use_gift_amount > parseInt($('#total').val())){
        layerAlert("한섬마일리지/기프트카드 사용액은 결제할 금액보다 같거나 작아야 합니다.");
        return;
    }
    
    
    var deliveryCost = 0.0;
    var total = $('#total').val();
    if(parseInt(deliveryCost) > 0){
        if((parseInt(total) <= parseInt(deliveryCost) && parseInt(use_gift_amount) >= parseInt(total)) 
                || (parseInt(total) >= parseInt(deliveryCost) && parseInt(use_gift_amount) > (parseInt(total) - parseInt(deliveryCost))))
        {
            layerAlert("배송비는 기프트카드로 결제하실 수 없습니다.");
            return;
        }
    }
    
    
    doPwcheck(self, '2', use_gift_amount);
}

function cancelUseGiftAmount(self){
    var use_gift_amount = $('#giftpay').val();

    if($(self).prop('class') == 'btn dis_s min_auto'){
        return;
    }
    
    if(use_gift_amount == '' || use_gift_amount == '0'){
        return;
    }
    
    $.ajax({
        type: "GET",
        url: "/ko/checkout/cancelUseGiftAmount",
        dataType: "json",
        //data: {"useGiftAmount": use_gift_amount},
        success: function(data){
            if(data == "NEED_LOGIN"){
                alert("need login");
                location.href = "/ko/member/login";
            }
            
            if(data.errorMsg != ''){
                return;
            }else{
                $('.sum_box').html(data.totalPricePage);
                //2019.7.4 khg
                setDeliveryMode();
                
                fn_updateDiscInfo();
                
                checkRedVoucher();
                $('#giftpay').val('');
                $('#giftpay').prop('readonly',false);
                $(self).prop('class','btn dis_s min_auto');
                $(self).prop('disabled',false);
                $('#btnUseGiftAmount').prop('disabled',false);
                $('#btnUseGiftAmount').prop('class','btn add_s min_auto');
                $('#gc_useall').prop("checked", false);
                $('#gc_useall').prop('disabled',false);
                $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
                
                
                $('#chk_giftAmount').val("");
            }
        }//,
        //error: function(xhr,  Status, error) {
            //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
        //}
    });
}

function cancelUseEGiftAmount(self){
    var use_egift_amount = $('#egiftpay').val();

    if($(self).prop('class') == 'btn dis_s min_auto'){
        return;
    }
    
    if(use_egift_amount == '' || use_egift_amount == '0'){
        return;
    }
    
    $.ajax({
        type: "GET",
        url: "/ko/checkout/cancelUseEGiftAmount",
        dataType: "json",
        success: function(data){
            if(data == "NEED_LOGIN"){
                location.href = "/ko/member/login";
            }
            
            if(data.errorMsg != ''){
                return;
            }else{
                $('.sum_box').html(data.totalPricePage);
                //2019.7.4 khg
                setDeliveryMode();
                
                fn_updateDiscInfo();
                
                checkRedVoucher();
                $('#egiftpay').val('');
                $('#egiftpay').prop('readonly',false);
                $(self).prop('class','btn dis_s min_auto');
                $(self).prop('disabled',false);
                $('#btnUseEGiftAmount').prop('disabled',false);
                $('#btnUseEGiftAmount').prop('class','btn add_s min_auto');
                $('#gc_egift_useall').prop("checked", false);
                $('#gc_egift_useall').prop('disabled',false);
                $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
            }
        }
    });
}

function setUseGiftAmount(use_gift_amount){
        $.ajax({
            type: "GET",
            url: "/ko/checkout/useGiftAmount",
            dataType: "json",
            data: {"useGiftAmount": use_gift_amount},
            success: function(data){
                if(data == "NEED_LOGIN"){
                    alert("need login");
                    location.href = "/ko/member/login";
            	} else if(data == "BAD_AUTH") {
					layerAlert("잘못된 접근입니다.");
					return;
            	}
                
                if(data.errorMsg != ''){
                	layerAlert(data.errorMsg);
                    return;
                }else{
                    $('.sum_box').html(data.totalPricePage);
                    //2019.7.4 khg
                    setDeliveryMode();
                    
                    fn_updateDiscInfo();
                    
                    checkRedVoucher();
                    $('#giftpay').prop('readonly',true);
                    $('#btnUseGiftAmount').prop('class','btn dis_s min_auto');
                    $('#btnUseGiftAmount').prop('disabled',true);
                    $('#btnCancelUseGiftAmount').prop('disabled',false);
                    $('#btnCancelUseGiftAmount').prop('class','btn add_s min_auto');

                    
                    
                    $('#gc_useall').prop('disabled',true);
                    $('#txtAccumulationPoint').text("한섬마일리지 "+numberFormatComma(data.accumulationPoint)+'M');
                    $('#txtAccumulationHPoint').text("H.Point "+numberFormatComma(data.accumulationHPoint)+'P');
                    
                    
                    $("#chk_giftAmount").val(use_gift_amount);
                }
            }//,
            //error: function(xhr,  Status, error) {
                //alert('sendRequest error : ' + xhr.status + " ( " + error + " ) " );
            //}
        });
}

function showCkout(self){
    if($(self).val() == 'KO001' || $(self).val() == 'KO001-2' || $(self).val() == 'KO006' || $(self).val() == 'KO007' || $(self).val() == 'KO008' ){ 
        $('#ckout_wrap').hide();
        $('#ckout_wrap2').hide();
        $('#ckout_wrap0').hide();
    }else if($(self).val() == 'KO001-4'){
    	$('#ckout_wrap').hide();
    	$('#ckout_wrap2').show();
    	$('#ckout_wrap0').hide();
    }else if($(self).val() == 'KO001-3'){
        $('#ckout_wrap').hide();
        $('#ckout_wrap2').hide();
        $('#ckout_wrap0').show();
    }else{
        
        $('#ckout_wrap').show();
        $('#ckout_wrap2').hide();
        $('#ckout_wrap0').hide();
        
    }
    
    if($(self).val() == 'KO003'){
        $("#virtualText").show();
    }else{
        $("#virtualText").hide();
    }
    
    if($(self).val() == 'KO006'){
    	$('#paytype').val('LGCNS');
    }else{
    	$('#paytype').val('Inicis');
    }
    if(typeof fn_updateDiscInfo === "function"){
        fn_updateDiscInfo();
    }
}

function showlayer(self,obj,gubun,pnt){
    var obj=$(obj);
    //var lp=($(window).width()-obj.width())/2;
    var tp=($(window).height()-obj.height())/2+$(window).scrollTop();
    //if(lp < 0) lp=0;
    if(tp < 0) tp=0;
    
    $("#productLayer").show();
    
    obj.css("top", tp).css("position", "absolute").css("z-index", 101);
    obj.show();
    
    $('#userInfoForm').find('input[name=gubun]').val(gubun);
    $('#userInfoForm').find('input[name=pnt]').val(pnt);
}


function viewPopup(v){
    var obj=$(v);
    //var lp=($(window).width()-obj.width())/2;
    var tp=($(window).height()-obj.height())/2+$(window).scrollTop();
    //if(lp < 0) lp=0;
    if(tp < 0) tp=0;
    
    $("#productLayer").show();
    
    obj.css("top", tp).css("position", "absolute").css("z-index", 101);
    obj.show();
    
    //return false;
}

function hideShowLayer() {
    $("#productLayer .layerBg").removeClass("white");
    $("#productLayer").hide();
}

function showExchangeRate(){
    
    var exchangeRateList = '[{symbol=¥, exchangeRate=192.12, currencyCode=CNY}, {symbol=$, exchangeRate=1287.5, currencyCode=USD}]';
    
    
    //var productPrice = $("#productPrice").val() == null || $("#productPrice").val() == 0 ? 0 : $("#productPrice").val();
    //var qty = $("#txtqty").val();
    //var sumPrice = parseFloat(productPrice * qty);
    var sumPrice = parseFloat($('#total').val());
    
    var addHtml = ""; 
    
    
        var currecy = "CNY";
        var rate = "192.12";
        var simbol = "¥";
        
        var exchangeAmt = Math.round(parseFloat(sumPrice) / parseFloat(rate) * 100) / 100 ;
        
        addHtml += "<p>"+ simbol + ""+addComma(exchangeAmt); +"</p>"
    
        var currecy = "USD";
        var rate = "1287.5";
        var simbol = "$";
        
        var exchangeAmt = Math.round(parseFloat(sumPrice) / parseFloat(rate) * 100) / 100 ;
        
        addHtml += "<p>"+ simbol + ""+addComma(exchangeAmt); +"</p>"
    
    //alert(addHtml);
        
    $("#exchangeDiv").html(addHtml);
    $(".delch_box").css("display","block");
    
}

function hideExchangeRate(){
    $(".delch_box").css("display","none");  
}

function numberFormatComma(num) {
    num = Math.floor(num).toString();
    var pattern = /(-?[0-9]+)([0-9]{3})/;
     
    while(pattern.test(num)) {
        num = num.replace(pattern,"$1,$2");
    }
    return num;
}


function escrowPopup() {
	window.open('https://mark.inicis.com/mark/escrow_popup.php?mid=handsomep1','에스크로서비스 가입확인','width=640,height=680');
}


function validationchk() { 
    var inputTextchk = /^[0-9a-zA-Z\s]+$/;
    var inputadresschk = /^[0-9a-zA-Z-\s/-]+$/;
    var inputNumberchk = /^[0-9]+$/;

    if (!inputTextchk.test($("#rcpt_name").val()) && ($("#rcpt_name").val().length > 0) )  {
        return false;
    }
    if (!inputadresschk.test($("#line1").val()) && ($("#line1").val().length > 0) ) {
        return false;
    }
    if (!inputadresschk.test($("#line2").val()) && ($("#line2").val().length > 0) ) {
        return false;
    }
    if (!inputTextchk.test($("#City").val()) && ($("#City").val().length > 0) ) {
        return false;
    }
    if (!inputTextchk.test($("#State").val()) && ($("#State").val().length > 0) ) {
        return false;
    }
    if (!inputTextchk.test($("#adress").val()) && ($("#adress").val().length > 0) ) {
        return false;
    }
    if (!inputNumberchk.test($("#Phone").val()) && ($("#Phone").val().length > 0) ) {
        return false;
    }
    
}

function validationOrderchk() { 
    var inputTextchk = /^[0-9a-zA-Z\s]+$/;
    var inputadresschk = /^[0-9a-zA-Z-\s/-]+$/;
    var inputNumberchk = /^[0-9]+$/;

    if (!inputTextchk.test($("#rcpt_name").val()) && ($("#rcpt_name").val().length > 0) )  {
        var lc = new layerAlert("Contact Name : "+"checkout.shipto.onlyeng.msg" );
        lc.confirmAction = function(){$("#rcpt_name").focus();};
        return false;
    }
//     if (!inputadresschk.test($("#line1").val()) && ($("#line1").val().length > 0) ) {
//         var lc = new layerAlert("Street address : "+"checkout.shipto.onlyeng.msg" );
//         lc.confirmAction = function(){$("#line1").focus();};
//         return false;
//     }
//     if (!inputadresschk.test($("#line2").val()) && ($("#line2").val().length > 0) ) {
//         var lc = new layerAlert("Street address2 : "+"checkout.shipto.onlyeng.msg" );
//         lc.confirmAction = function(){$("#line2").focus();};
//         return false;
//     }
//     if (!inputTextchk.test($("#City").val()) && ($("#City").val().length > 0) ) {
//         var lc = new layerAlert("City : "+"checkout.shipto.onlyeng.msg" );
//         lc.confirmAction = function(){$("#City").focus();};
//         return false;
//     }
//     if (!inputTextchk.test($("#State").val()) && ($("#State").val().length > 0) ) {
//         var lc = new layerAlert("State : "+"checkout.shipto.onlyeng.msg" );
//         lc.confirmAction = function(){$("#State").focus();};
//         return false;
//     }
    if (!inputTextchk.test($("#adress").val()) && ($("#adress").val().length > 0) ) {
        var lc = new layerAlert("Zip/Postal Code : "+"checkout.shipto.zipcode.msg" );
        lc.confirmAction = function(){$("#adress").focus();};
        return false;
    }
    if (!inputNumberchk.test($("#Phone").val()) && ($("#Phone").val().length > 0) ) {
        var lc = new layerAlert("Phone number : "+"checkout.shipto.phone.msg" );
        lc.confirmAction = function(){$("#Phone").focus();};
        return false;
    }
    
}

function checkoutCartView() {
	$.ajax({
		type: "GET",
		url: "/ko/checkout/checkoutCartView",
		data : {},
		dataType : "html",
		async: false,
		error : function( request, status, error ){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			return false;
		}, 
		success : function( data ){
			$("#checkoutCartView").html(data);
		}
	});
}

// 2019.11.04 추가
function dutyCkout1909(self) {
	if($(self).val() == '1'){
		$('.con_tab01').show();
		$('.con_tab02').hide();
		$("#passport").val("");
		$("#reChkPassport").val("");
	}else{
		$('.con_tab01').hide();
		$('.con_tab02').show();
		$("#idCardNum").val("");
		$("#reChkIdCardNum").val("");
	}
}

function selectRegion(self,isCouponUpdate) {
    if(!isCouponUpdate){
        globalOrderInitialize();
    }

	var isoCode = $(self).val();                                
	var totalPrice = $("#total").val();                         
	var deliveryCost = $("#cartDeliveryCost").val();            
	var customDueAmount = $("#customDueAmount").val();          
	var maxAmtForTax = $("#maxAmtForTax_" + isoCode).val();     
	
	totalPrice = totalPrice != null ? Number(totalPrice) : 0;
	deliveryCost = deliveryCost != null ? Number(deliveryCost) : 0;
	customDueAmount = customDueAmount != null ? Number(customDueAmount) : 0;
	maxAmtForTax = maxAmtForTax != null ? Number(maxAmtForTax) : 0;
	
	var subTotal = totalPrice - deliveryCost - customDueAmount; 
	
	/*
	if(isoCode == "CN") {
	    $("#line1").attr('placeholder', 'checkout.shipto.chn.placeholder');
        $("#line2").attr('placeholder', 'checkout.shipto.chn.placeholder');
        $("#City").attr('placeholder', 'checkout.shipto.chn.placeholder');
        $("#State").attr('placeholder', 'checkout.shipto.chn.placeholder');
    } else
    */
    if(isoCode != null && isoCode != "") {
        $("#line1").attr('placeholder', 'checkout.shipto.eng.placeholder');
        $("#line2").attr('placeholder', 'checkout.shipto.eng.placeholder');
        $("#City").attr('placeholder', 'checkout.shipto.eng.placeholder');
        $("#State").attr('placeholder', 'checkout.shipto.eng.placeholder');
    } else {
        $("#line1").attr('placeholder', '');
        $("#line2").attr('placeholder', '');
        $("#City").attr('placeholder', '');
        $("#State").attr('placeholder', '');
    }
	//20210531 배송지 -> 영국 25만원 조건 추가
	if(isoCode == 'GB' && totalPrice < 250000){
		var la = new layerAlert("checkout.alert.region.unitedkingdom");
	}
	var applyChinaTariffProxyService = false
    if(isoCode == "CN" && applyChinaTariffProxyService) {
		if(subTotal < maxAmtForTax) {
            var customDutiesPaymentYN = $("#customDutiesPaymentYN").val();
            if(customDutiesPaymentYN == 1 && isCouponUpdate == true){
                var la = new globalLayerAlert3();
                setDeliveryModeGlobal();	
            } else {
                var lc = new globalLayerConfirm();
                lc.confirmAction = function() {
                    var la = new globalLayerAlert();
                    $("#customDutiesPaymentYN").val(1);
                    setDeliveryModeGlobal();    
                }

                lc.cancelAction = function() {
                    var la = new layerAlert('checkout.customduties.lessthan80.confirmN.layer');
                    $("#customDutiesPaymentYN").val(0);
                    setDeliveryModeGlobal();    
                }
            }
		} else {
			var la = new globalLayerAlert2();
			$("#idCardNum").val("");
			$("#reChkIdCardNum").val("");
			$("#passport").val("");
			$("#reChkPassport").val("");
			$("#customDutiesPaymentYN").val(0);
			setDeliveryModeGlobal();    
		}
		
		$("#dutyVat").show();
	} else {
		$("#dutyVat").hide();
		$("#idCardNum").val("");
		$("#reChkIdCardNum").val("");
		$("#passport").val("");
		$("#reChkPassport").val("");
		$("#customDutiesPaymentYN").val(0);
		setDeliveryModeGlobal();    
	}
}

function fn_notUseCustomDutiesPayment(){
//     $("#dutyVat").hide();
    $("#idCardNum").val("");
    $("#reChkIdCardNum").val("");
    $("#passport").val("");
    $("#reChkPassport").val("");
    $("#customDutiesPaymentYN").val(0);
    setDeliveryModeGlobal();    
}

function globalOrderInitialize() {
	$("#line1").val("");
	$("#line2").val("");
	$("#City").val("");
	$("#State").val("");
	$("#adress").val("");
	$("#Phone").val("");
}

var globalLayerConfirm = function(){
	var alertTag;
	alertTag = '<div class="layerArea" style="z-index:1110;display:none" id="layerDiv">';
	alertTag +='    <div class="layerBg" onclick="fn_notUseCustomDutiesPayment();"></div> ';
	alertTag +='    <div class="popwrap w_type_1" style="z-index:150;"> ';
	alertTag +='        <div class="pop_cnt">';
	
	alertTag +='            <p class="pop_txt_190911" style="font-size:16px;">checkout.customduties.lessthan80.confirm.layer1</p>';
	alertTag +='            <p class="pop_txt_190911" style="font-size:16px;">checkout.customduties.lessthan80.confirm.layer2</p>';
	alertTag +='            <p class="pop_txt_190911">checkout.customduties.lessthan80.confirm.layer3</p>';
		
	alertTag +='            <div class="btnwrap">';
	alertTag +='                <input type="button" id="btnNo" class="btn wt_s" value="checkout.customduties.lessthan80.confirm.no" style="min-width:120px;">';
	alertTag +='                <input type="button" id="btnOk" class="btn gray_s mr0" value="checkout.customduties.lessthan80.confirm.ok" style="min-width:120px;">';
	alertTag +='            </div>';
	alertTag +='        </div>';
// 	alertTag +='        <a href="javascript:fn_notUseCustomDutiesPayment();" class="btn_close"><img src="/_ui/desktop/common/images/popup/ico_close.png" alt="닫기"></a>';
	alertTag +='    </div>';
	alertTag +='</div>';

	var t = this;
	var scrollTopCurrent = $(window).scrollTop();
	var $alertLayer = $(alertTag).appendTo($("body"));

	this.confirmAction = function(){};
	this.cancelAction = function(){};

	$alertLayer.find('.popwrap').css('marginTop', getMarginTop2(250,scrollTopCurrent));
	$("#layerDiv").show();
	
	var existedStyle = $('body').attr('style') || '';
	if (existedStyle) {
		existedStyle += ';';
	}
	
// 	$('body').attr('style', existedStyle + 'overflow:hidden !important');
	$alertLayer.find('.popwrap').css('marginTop', getMarginTop($alertLayer.find('.popwrap').outerHeight()));
	$alertLayer.find('.btn.gray_s').click(function(){
		$('body').attr('style', existedStyle);
		$alertLayer.remove();
		t.confirmAction();
	});
	
	$alertLayer.find('.btn_close').click(function(){
		$('body').attr('style', existedStyle);
		$alertLayer.remove();
	});
	
	$alertLayer.find('.btn.wt_s').click(function(){
		$('body').attr('style', existedStyle);
		$alertLayer.remove();
		t.cancelAction();
	});
};

var globalLayerAlert = function() {
	var alertTag;
	alertTag = '<div class="layerArea" style="z-index:1110;display:none" id="layerDiv">';
	alertTag +='    <div class="layerBg"></div> ';
	alertTag +='    <div class="popwrap w_type_1" style="z-index:150;"> ';
	alertTag +='        <div class="pop_cnt">';
	alertTag +='            <h4 class="pop_noti_tit_190911">checkout.customduties.lessthan80.confirmY.layer.title</h4>';
	alertTag +='            <ul class="pop_noti_list_190911">';
	alertTag +='                <li>checkout.customduties.lessthan80.confirmY.layer.content1</li>';
	alertTag +='                <li>checkout.customduties.lessthan80.confirmY.layer.content2</li>';
	alertTag +='                <li>checkout.customduties.lessthan80.confirmY.layer.content3</li>';
	alertTag +='            </ul>';
	alertTag +='            <div class="btnwrap">';
	alertTag +='                <input type="button" id="btnOk" class="btn gray_s mr0" value="checkout.customduties.lessthan80.confirm.ok" style="min-width:120px;">';
	alertTag +='            </div>';
	alertTag +='        </div>';
	alertTag +='        <a href="javascript:void(0);" class="btn_close"><img src="/resources/_ui/desktop/common/images/popup/ico_close.png" alt="닫기"></a>';
	alertTag +='    </div>';
	alertTag +='</div>';
	
	var t = this;
	var scrollTopCurrent = $(window).scrollTop();
	var $alertLayer = $(alertTag).appendTo($("body"));
	
	this.confirmAction = function(){};
	this.cancelAction = function(){};
	
	$alertLayer.find('.popwrap').css('marginTop', getMarginTop2(250,scrollTopCurrent));
	$("#layerDiv").show();
	
	var existedStyle = $('body').attr('style') || '';
	if (existedStyle) {
		existedStyle += ';';
	}
	
// 	$('body').attr('style', existedStyle + 'overflow:hidden !important');
	$alertLayer.find('.popwrap').css('marginTop', getMarginTop($alertLayer.find('.popwrap').outerHeight()));
	$alertLayer.find('.btn.gray_s').click(function(){
		$('body').attr('style', existedStyle);
		$alertLayer.remove();
		t.confirmAction();
	});
	
	$alertLayer.find('.layerBg, .btn_close').click(function(){
		$('body').attr('style', existedStyle);
		$alertLayer.remove();
	});
};

var globalLayerAlert2 = function() {
	var alertTag;
	alertTag = '<div class="layerArea" style="z-index:1110;display:none" id="layerDiv">';
	alertTag +='    <div class="layerBg"></div> ';
	alertTag +='    <div class="popwrap w_type_1" style="z-index:150;"> ';
	alertTag +='        <div class="pop_cnt">';
	alertTag +='            <p class="pop_txt_190911"><strong>checkout.customduties.over80.layer</strong><br/></p>';
	alertTag +='            <p class="pop_txt_190911">checkout.customduties.over80.layer2</p>';
	alertTag +='            <div class="btnwrap">';
	alertTag +='                <input type="button" id="btnOk" class="btn gray_s mr0" value="checkout.customduties.lessthan80.confirm.ok" style="min-width:120px;">';
	alertTag +='            </div>';
	alertTag +='        </div>';
	alertTag +='        <a href="javascript:void(0);" class="btn_close"><img src="/resources/_ui/desktop/common/images/popup/ico_close.png" alt="닫기"></a>';
	alertTag +='    </div>';
	alertTag +='</div>';
	
	var t = this;
	var scrollTopCurrent = $(window).scrollTop();
	var $alertLayer = $(alertTag).appendTo($("body"));
	
	this.confirmAction = function(){};
	this.cancelAction = function(){};
	
	$alertLayer.find('.popwrap').css('marginTop', getMarginTop2(250,scrollTopCurrent));
	$("#layerDiv").show();
	
	var existedStyle = $('body').attr('style') || '';
	if (existedStyle) {
		existedStyle += ';';
	}
	
// 	$('body').attr('style', existedStyle + 'overflow:hidden !important');
	$alertLayer.find('.popwrap').css('marginTop', getMarginTop($alertLayer.find('.popwrap').outerHeight()));
	$alertLayer.find('.btn.gray_s').click(function(){
		$('body').attr('style', existedStyle);
		$alertLayer.remove();
		t.confirmAction();
	});
	
	$alertLayer.find('.layerBg, .btn_close').click(function(){
		$('body').attr('style', existedStyle);
		$alertLayer.remove();
	});
};

var globalLayerAlert3 = function() {
    var alertTag;
    alertTag = '<div class="layerArea" style="z-index:1110;display:none" id="layerDiv">';
    alertTag +='    <div class="layerBg"></div> ';
    alertTag +='    <div class="popwrap w_type_1" style="z-index:150;"> ';
    alertTag +='        <div class="pop_cnt">';
    alertTag +='            <p class="pop_txt_190911">checkout.customduties.coupon.layer</p>';
    alertTag +='            <div class="btnwrap">';
    alertTag +='                <input type="button" id="btnOk" class="btn gray_s mr0" value="checkout.customduties.lessthan80.confirm.ok" style="min-width:120px;">';
    alertTag +='            </div>';
    alertTag +='        </div>';
    alertTag +='        <a href="javascript:void(0);" class="btn_close"><img src="/resources/_ui/desktop/common/images/popup/ico_close.png" alt="닫기"></a>';
    alertTag +='    </div>';
    alertTag +='</div>';

    var t = this;
    var scrollTopCurrent = $(window).scrollTop();
    var $alertLayer = $(alertTag).appendTo($("body"));

    this.confirmAction = function(){};
    this.cancelAction = function(){};

    $alertLayer.find('.popwrap').css('marginTop', getMarginTop2(250,scrollTopCurrent));
    $("#layerDiv").show();

    var existedStyle = $('body').attr('style') || '';
    if (existedStyle) {
        existedStyle += ';';
    }

// 	$('body').attr('style', existedStyle + 'overflow:hidden !important');
    $alertLayer.find('.popwrap').css('marginTop', getMarginTop($alertLayer.find('.popwrap').outerHeight()));
    $alertLayer.find('.btn.gray_s').click(function(){
        $('body').attr('style', existedStyle);
        $alertLayer.remove();
        t.confirmAction();
    });

    $alertLayer.find('.layerBg, .btn_close').click(function(){
        $('body').attr('style', existedStyle);
        $alertLayer.remove();
    });
};

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

/* 주문서간편결제 : 결제 인증 후 콜백
원클릭구매 : iframe 안에서 실행되기 때문에, target 을 _top으로 지정합니다
 */
var checkoutPaymentAuthCallback = function (resultCode, resultMsg, result) {
    switch(resultCode) {
	case "2406": // 쿠키정보가 없습니다.
	    new layerAlert(resultMsg);
		//layerWpayCookieSettingView();
		//WPAY.close();
		//window.parent.postMessage({layerClose:'layerClose'},'*');
		//window.parent.postMessage({inicisPopOpen:'inicisPopOpen'},'*');
		
		console.log("쿠키 정보가 없음");
		break;
	case "0000":
	    //상품상세와 결제페이지 구분이 안되기 때문에 
	    //window.parent.postMessage({loadingShow:'loadingShow'},'*');
        
		var $PAY_FORM = $("#PAY_FORM");
		addHidden($PAY_FORM, 'wtid'	, result.wtid);
		
		$PAY_FORM.attr({
			'method': 'POST',
			'action': '/ko/inicis/inipaywpay/sop/response',
			'target': '_top'
		});
		
		$PAY_FORM.submit();
		break;
	default:
		new layerAlert(resultMsg);
		//WPAY.close();
		//window.parent.postMessage({layerClose:'layerClose'},'*');
		break;
	}
}

function setEcommerceData(action){
    var mode = $("[name='mode']:checked").val();
    var paymentType = "";
    if(typeof mode != undefined && mode !== null && mode !== ''){
        
        if(mode == "KO001-3"){
            paymentType = "1CLICK 결제";
        }else if(mode == "KO001"){
            paymentType = "신용카드";
        }else if(mode == "KO002"){
            paymentType = "실시간 계좌이체";
        }else if(mode == "INIKakao"){
            paymentType = "카카오페이";
        }else if(mode == "KO003"){
            paymentType = "가상계좌";
        }else if(mode == "KO006"){
            paymentType = "스마일페이";
        }else if(mode == "KO001-4"){
            paymentType = "현대카드 레드쇼핑바우처";
        }
        
    }
    
    var stepLevel = "1";
    var actionField = { 'step': stepLevel}; //결제단계
    if(action == "Checkout2(바로주문)"){
        stepLevel = "2";
        actionField = { 'step': stepLevel, 'option': paymentType }; //결제단계 및 결제수단;
    }
    
    dataLayer.push({
    'event': 'ga_event', 'layerCategory' : 'Ecommerce', 'layerAction' : 'Checkout','layerLabel' : '바로주문',
    'ecommerce': {
	    'currencyCode': 'KRW', //통화
	    'checkout': {
		    'actionField': actionField,
		    'products':
			    [
			        
                    /* 변형 제품(옵션) */
                    
                    
                    /* 상품 정상이월세일 */
                    
			        {
			        
				    "id": "TH2C1WSH719MM1", //상품코드
				    "name": "히든 플래킷 셔츠", //상품명
				    "brand": "TIME HOMME", //상품 브랜드
				    
				    "category": decodeURIComponent("MEN")+"/"+decodeURIComponent("TOP")+"/"+decodeURIComponent("SHIRTS"), //상품 카테고리
				    "variant": "BLUE_105", //변형 제품(옵션)
				    "quantity": "1", //상품 수량
				    "dimension19": "정상상품_정상가" //상품 정상이월세일
				    }
				    
			    ]
		    }
	    }
    });
    dataLayer.push({
	    'layerCategory' : undefined,
	    'layerAction' : undefined,
	    'layerLabel' : undefined,
	    'nonInteraction' : false,
	    'ecommerce' : undefined
    });
}

function disabledHistoryBack(){
    history.pushState(null, null, "#Back");
    $(window).bind("hashchange", function () {
        history.pushState(null, null, "#Back");
    });
}
//]]>
