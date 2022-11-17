// 수량 버튼 조작
let quantity = $(".quantity_input").val();
<!--        let price = $(".individual_Price").val();-->

$(".plus_btn").on("click", function(){
    $(".quantity_input").val(++quantity);
<!--            $(".price").val(quantity * price);-->
});

$(".minus_btn").on("click", function(){
    if(quantity > 1){
        $(".quantity_input").val(--quantity);
    }
});


//상품이 의사/ 의료사업자만 구매할 수 있는 상품인지 체크하는 값 미리 생성
const form = {
    rate : ''
}

//장바구니 담기 버튼 눌렀을 때, 등급만 확인하면 되니 등급값만 보냄
//돌아온 값을 cartAlert메서드 파라미터에
function cartCheck() {

    form.rate = $(".individual_Rate").val();

        $.ajax({
            url: '/cart/itemCheck',
            type: 'POST',
            data: form,
            dataType: "json",
            success: function(result){
                cartAlert(result);
            }
        })
}

//값이 0이면 로그인 안한 사용자이므로 로그인 화면, 1이면 일반 사용자가 의약품 구매이므로 경고창
//2이면 구매 가능하므로 위에 생성한 cart_form 보냄
function cartAlert(result){

    //반환값 문자열이므로 ' '붙여좀
    if(result == '0'){
        goLogin();
    } else if(result == '1'){
        alert("의사/ 의료사업자가 아니면 이 상품은 장바구니에 담지 못합니다!");
    } else if(result == '2'){
        $(".cart_form").submit();
    }
}


//주문하기 버튼 눌렀을 때, 등급만 확인하면 되니 등급값만 보냄
//돌아온 값을 orderAlert메서드 파라미터에
function orderCheck() {

    form.rate = $(".individual_Rate").val();

        $.ajax({
            url: '/order/itemCheck',
            type: 'POST',
            data: form,
            dataType: "json",
            success: function(result){
                orderAlert(result);
            }
        })
}

//값이 0이면 로그인 안한 사용자이므로 로그인 화면, 1이면 일반 사용자가 의약품 구매이므로 경고창
//2이면 구매 가능하므로 자바스크립트 order()실행해 상품 카트에 담음
function orderAlert(result){

    //반환값 문자열이므로 ' '붙여좀
    if(result == '0'){
        window.location.href = '/login';
<!--                goLogin();-->
    } else if(result == '1'){
        alert("의사/ 의료사업자가 아니면 구매 할 수 없습니다!");
    } else if(result == '2'){
        order();
    }
}

function goLogin() {
  var form = document.createElement("form");
  form.setAttribute("method", "get");
  form.setAttribute("action", "/login");
  document.body.appendChild(form);
  form.submit();
}

//주문체크해서 주문가능하면 상품정보 넘기고 주문페이지로 가는 메서드
function order() {
    //상품정보 값을 갖고와
    let itemId = $(".individual_ItemId").val();
    let name = $(".individual_Name").val();
    let imageSrc = $(".individual_ImageSrc").val();
    let price = $(".individual_Price").val();
    let rate = $(".individual_Rate").val();

    let quantity = $(".quantity_input").val();

    //주문폼 값에 세팅
    $(".order_form").find("input[name='orders[0].itemId']").val(itemId);
    $(".order_form").find("input[name='orders[0].name']").val(name);
    $(".order_form").find("input[name='orders[0].imageSrc']").val(imageSrc);
    $(".order_form").find("input[name='orders[0].price']").val(price);
    $(".order_form").find("input[name='orders[0].rate']").val(rate);

    $(".order_form").find("input[name='orders[0].quantity']").val(quantity);

    $(".order_form").submit();
}