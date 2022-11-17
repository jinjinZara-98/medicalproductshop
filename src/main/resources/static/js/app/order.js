$(document).ready(function(){

    let totalCount = 0;				// 총 갯수
	let totalKind = 0;				// 총 종류
    let totalPrice = 0;             //총 가격

    $(".order_info_td").each(function(index, element){

        // 총 갯수
		totalCount += parseInt($(element).find(".individual_Quantity").val());

		// 총 종류
		totalKind += 1;

        /* 총 가격, parseInt <input> 태그의 값을 얻어오면 그 값은 'string' 타입으로 인식이 되어서 이를 int 타입으로 변경 */
        //let이나 var 타입으로 지정한 변수에 대입을 안해주고 바로 더해줘서 파싱?
		totalPrice += parseInt($(element).find(".individual_TotalPrice").val());
	});

    // 총 갯수
	$(".totalCount_span").text(totalCount);

	// 총 종류
	$(".totalKind_span").text(totalKind);

    /* toLocaleString() 통화 형식(#,###) 출력 */
	$(".totalPrice_span").text(totalPrice.toLocaleString());
});


/* 주문 페이지 이동할때 체크한 장바구니 상품 정보 주기 */
function order() {

    //동적으로 생성할 <input> 태그 문자열 값이 저장될 form_contents 변수를 선언하고 빈 문자열 값으로 초기화
	let form_contents ='';

	//<input>의 name값에 orders[0], orders[1], orders[2] 와 값이 index 값을 주어야 하는데
	//이러한 index값 역할을 할 orderNumber 변수를 선언하고 0으로 초기화
	let orderNumber = 0;


    //상품받는 수령자 정보 결제정보, 리스트 아님
    //출력된 회원정보를 사용자가 수정한 값인 input태그의 값을 갖고와 변수에 넣고 다시 input태그 만들어 form태그 안에 추가
    let memberId = $(".memberId").val();
    let memberId_input = "<input name = 'memberId' type='hidden' value='" + memberId + "'>";
    form_contents += memberId_input;

    let name = $("#memberName").val();
    let name_input = "<input name = 'name' type='hidden' value='" + name + "'>";
    form_contents += name_input;

    let phoneNumber = $("#phoneNumber").val();
    let phoneNumber_input = "<input name = 'phoneNumber' type='hidden' value='" + phoneNumber + "'>";
    form_contents += phoneNumber_input;

    let address = $("#address").val();
    let address_input = "<input name = 'address' type='hidden' value='" + address + "'>";
    form_contents += address_input;

    let addressDetail = $("#addressDetail").val();
    let addressDetail_input = "<input name = 'addressDetail' type='hidden' value='" + addressDetail + "'>";
    form_contents += addressDetail_input;

    //select는 id값으로
    let deliveryMessage = $("#deliveryMessage option:selected").val();
    let deliveryMessage_input = "<input name = 'deliveryMessage' type='hidden' value='" + deliveryMessage + "'>";
    form_contents += deliveryMessage_input;

    let accountHost = $("#accountHost").val();
    let accountHost_input = "<input name = 'accountHost' type='hidden' value='" + accountHost + "'>";
    form_contents += accountHost_input;

    let bankName = $("#bankName option:selected").val();
    let bankName_input = "<input name = 'bankName' type='hidden' value='" + bankName + "'>";
    form_contents += bankName_input;

    let accountNumber = $("#accountNumber").val();
    let accountNumber_input = "<input name = 'accountNumber' type='hidden' value='" + accountNumber + "'>";
    form_contents += accountNumber_input;


    //cart_info_td라는 class값을 가진걸 순회
	$(".order_info_td").each(function(index, element){

          //각 상품의 장바구니아이디 상품아이디 수량 총가격 갖고와 대입
          let cartId = $(element).find(".individual_CartId").val();
          let itemId = $(element).find(".individual_ItemId").val();
          let name = $(element).find(".individual_Name").val();
          let imageSrc = $(element).find(".individual_ImageSrc").val();
          let quantity = $(element).find(".individual_Quantity").val();
          let price = $(element).find(".individual_Price").val();
          let totalPrice = $(element).find(".individual_TotalPrice").val();

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

          let totalPrice_input = "<input name = 'orders[" + orderNumber + "].totalPrice' type='hidden' value='" + totalPrice + "'>";
          form_contents += totalPrice_input;

          //그리고 index값 역할을 하는 orderNumber는 다음 객체에 접근할 때 +1이 될 수 있도록 코드를 추가합니다.
          orderNumber += 1;

	});

    //each 메서드를 벗어나서 form_contents 변수에 저장된 문자열(<input> 태그) 값을
    //주문 페이지 이동 <form> 태그 내부에 추가되도록 코드를 추가하고, <form> 태그를 서버로 전송합니다.
	$(".order_form").html(form_contents);
	$(".order_form").submit();

	//수령자 입력이 잘못되면 다시 주문페이지로 돌아올 수 있으므로 경고창 바로 띄워주기 힘듬
    //alert("주문이 정상적으로 처리되었습니다!");
}