//상품이 의사/ 의료사업자만 구매할 수 있는 상품인지 체크
const form = {
    itemId : '',
    rate : ''
}

//장바구니 담기 버튼 눌렀을 때, 등급만 확인하면 되니 등급값만 보냄
function cartCheck(itemId, rate) {

    form.itemId = itemId;
    form.rate = rate;

        $.ajax({
            url: '/home/cart/itemCheck',
            type: 'POST',
            data: form,
            dataType: "json",
            //success, error, complete 은 done, fail, always 로 대체
            success: function(idAndResult){

                //자바스크립트는 값 게터로 가져올때 get안써주고 필드이름만?
                cartAlert(idAndResult.itemId, idAndResult.result);
            }
        })
}

//값이 0이면 로그인 안한 사용자이므로 로그인 화면, 1이면 일반 사용자가 의약품 구매이므로 경고창
//자바스크립트 cartPut()실행해 상품 카트에 담음
function cartAlert(itemId, result){

    if(result == '0'){
        goLogin();
    } else if(result == '1'){
        alert("의사/ 의료사업자가 아니면 이 상품은 장바구니에 담지 못합니다!");
    } else if(result == '2'){
        cartPut(itemId);
    }
}

//로그인으로 가기
function goLogin() {
  var form = document.createElement("form");
  form.setAttribute("method", "GET");
  form.setAttribute("action", "/login");
  document.body.appendChild(form);
  form.submit();
}

//상품 장바구니에 담기
function cartPut(itemId) {
  var form = document.createElement("form");
  form.setAttribute("method", "post");
  form.setAttribute("action", "/cart/itemInstantPut/" + itemId);
  document.body.appendChild(form);
  form.submit();
}