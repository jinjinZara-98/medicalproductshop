$(document).ready(function(){

    setTotalInfo()

});

/* 체크박스 전체 선택 */
$(".all_check_input").on("click", function(){

    /* 체크박스 체크/해제 */
    if($(".all_check_input").prop("checked")){
        $(".individual_cart_checkbox").attr("checked", true);
    } else{
        $(".individual_cart_checkbox").attr("checked", false);
    }

    /* 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수, 종류) */
	setTotalInfo($(".cart_info_td"));
})

//체크여부에따른 종합 정보 변화
$(".individual_cart_checkbox").on("change", function(){
	/* 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수, 종류) */
	setTotalInfo($(".cart_info_td"));
});

//체크 박스의 변환에 따라서 실행되는 메서드를 추가해주어야 하는데 그에 앞서서 '종합 종보 섹션'의 데이터
function setTotalInfo(){

    let totalCount = 0;				// 총 갯수
	let totalKind = 0;				// 총 종류
    let totalPrice = 0;             //총 가격

    //element(<td> 객체)에 있는 체크박스('.individual_cart_checkbox' <input>)가 checked 상태일 때
    //ture이며 참이 된다는 의미입니다. 따라서 체크상태일 때 if 구현부의 코드가 실행 되게 됩니다.
    //is() 메서드는 해당 메서드를 호출하는 객체가 is() 메서드의 인자로 지정한 선택자를 가지고 있으면 true를 반환
    $(".cart_info_td").each(function(index, element){

        if($(element).find(".individual_cart_checkbox").is(":checked") === true){	//체크여부
		    // 총 갯수
            totalCount += parseInt($(element).find(".individual_Quantity").val());

            // 총 종류
            totalKind += 1;

            /* 총 가격, parseInt <input> 태그의 값을 얻어오면 그 값은 'string' 타입으로 인식이 되어서 이를 int 타입으로 변경 */
            //let이나 var 타입으로 지정한 변수에 대입을 안해주고 바로 더해줘서 파싱?
            totalPrice += parseInt($(element).find(".individual_TotalPrice").val());
		}

	});

    // 총 갯수
	$(".totalCount_span").text(totalCount);

	// 총 종류
	$(".totalKind_span").text(totalKind);

    /* toLocaleString() 통화 형식(#,###) 출력 */
	$(".totalPrice_span").text(totalPrice.toLocaleString());
}


//cancel버튼 누르면 이 메서드 실행, form을 만들어 post로 /cart/" + id + "/cancel을 호출
function cancel(id) {
     var form = document.createElement("form");
     form.setAttribute("method", "post");
     form.setAttribute("action", "/cart/" + id + "/cancel");
     document.body.appendChild(form);
     form.submit();
     alert("장바구니에서 상품이 삭제되었습니다!");
}


/* 주문 페이지 이동할때 체크한 장바구니 상품 정보 주기 */
<!--$(".btn btn-dark my-3").on("click", function(){-->
function order() {

    //동적으로 생성할 <input> 태그 문자열 값이 저장될 form_contents 변수를 선언하고 빈 문자열 값으로 초기화
	let form_contents ='';

	//<input>의 name값에 orders[0], orders[1], orders[2] 와 값이 index 값을 주어야 하는데
	//이러한 index값 역할을 할 orderNumber 변수를 선언하고 0으로 초기화
	let orderNumber = 0;

    //cart_info_td라는 class값을 가진걸 순회
	$(".cart_info_td").each(function(index, element){

        //체크여부 확인
		if($(element).find(".individual_cart_checkbox").is(":checked") == true){

            //각 CartId값 Count값 갖고와 대입
			let cartId = $(element).find(".individual_CartId").val();
			let itemId = $(element).find(".individual_ItemId").val();
			let name = $(element).find(".individual_Name").val();
			let imageSrc = $(element).find(".individual_ImageSrc").val();
			let quantity = $(element).find(".individual_Quantity").val();
            let price = $(element).find(".individual_Price").val();

            //두 변수의 값과 index(each 메서드 첫 번째 인자) 값을 활용해서 서버로 전송되어야 할 <input> 태그를 문자열 값으로 만들어내고
            //앞서 선언한 form_contents 변수에 문자열을 더해줍니다.

            //이런 형식으로
            //<input type="hidden" name="orders[0].cartId" value="">

			let cartId_input = "<input name = 'orders[" + orderNumber + "].cartId' type='hidden' value='" + cartId + "'>";
			form_contents += cartId_input;

            let itemId_input = "<input name = 'orders[" + orderNumber + "].itemId' type='hidden' value='" + itemId + "'>";
			form_contents += itemId_input;

            let name_input = "<input name = 'orders[" + orderNumber + "].name' type='hidden' value='" + name + "'>";
			form_contents += name_input;

            let imageSrc_input = "<input name = 'orders[" + orderNumber + "].imageSrc' type='hidden' value='" + imageSrc + "'>";
			form_contents += imageSrc_input;

			let quantity_input = "<input name = 'orders[" + orderNumber + "].quantity' type='hidden' value='" + quantity + "'>";
			form_contents += quantity_input;

            let price_input = "<input name = 'orders[" + orderNumber + "].price' type='hidden' value='" + price + "'>";
			form_contents += price_input;

            //그리고 index값 역할을 하는 orderNumber는 다음 객체에 접근할 때 +1이 될 수 있도록 코드를 추가합니다.
			orderNumber += 1;
		}
	});

    //each 메서드를 벗어나서 form_contents 변수에 저장된 문자열(<input> 태그) 값을
    //주문 페이지 이동 <form> 태그 내부에 추가되도록 코드를 추가하고, <form> 태그를 서버로 전송합니다.
	$(".order_form").html(form_contents);
	$(".order_form").submit();
}