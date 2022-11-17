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
            success: function(idAndResult){

                //자바스크립트는 값 게터로 가져올때 get안써주고 필드이름만?
                cartAlert(idAndResult.itemId, idAndResult.result);
            }
        })
}
//0은 로그인 하지 않은 사용자가 상품을 장바구니에 담으려 할 때
//현재페이지는 로그인한 사용자의 홈화면이므로 0이 나올 수 없음
//값이 1이면 경고창, 2이면 자바스크립트 cartPut()실행해 상품 카트에 담음
function cartAlert(itemId, result){

    if(result == '1'){
        alert("의사/ 의료사업자가 아니면 이 상품은 장바구니에 담지 못합니다!");
    } else if(result == '2'){
        alert("상품이 장바구니에 담겼습니다!");
        cartPut(itemId);
    }
}

function cartPut(itemId) {
  var form = document.createElement("form");
  form.setAttribute("method", "post");
  form.setAttribute("action", "/cart/" + itemId + "/itemInstantPut");
  document.body.appendChild(form);
  form.submit();
}