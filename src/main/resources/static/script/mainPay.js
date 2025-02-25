async function requestPayment() {
	try{
			//결제 요청
		const response = await PortOne.requestPayment({
			  // Store ID 설정
			  storeId: "store-062ca9c8-2580-4947-89e7-0773124c342d",
			  // 채널 키 설정
			  channelKey: "channel-key-4be88ee7-b26a-4faf-ae68-5b184d3ea3ec",
			  paymentId: `payment-${crypto.randomUUID()}`,
			  orderName: getCartSummary(),
			  totalAmount: parseFormattedPrice(document.getElementById('total-price')),
			  currency: "CURRENCY_KRW",
			  payMethod: "CARD",
		});
		
		//결제 실패 시 안내
		if (response.code !== undefined) {
		    // 오류 발생
		    return alert(response.message);
		 }
		 
		 const postCode = document.getElementById('sample6_postcode').value;
		 const address = document.getElementById('sample6_address').value;
		 const detailAddress = document.getElementById('sample6_detailAddress').value;
		 const extraAddress = document.getElementById('sample6_extraAddress').value;
		 
		 //결제 완료시 서버에 정보 전달
		const notified = await fetch(`/payment_complete`, {
	    method: "POST",
	    headers: { "Content-Type": "application/json" },
	    // paymentId와 주문 정보를 서버에 전달합니다
	    body: JSON.stringify({
	      paymentId: response.paymentId,
	      orderName: getCartSummary(),
	      totalAmount: parseFormattedPrice(document.getElementById('total-price')),
	      address: {
		    "postCode": postCode,
		    "address": address,
		    "detailAddress": detailAddress,
		    "extraAddress": extraAddress
		  },
		  user:{
			"name": document.getElementById('oderer').value,
			"phone": document.getElementById('phone').value,
			"email": document.getElementById('email').value
		  }
	    }),
	  });
	  
	  if (!notified.ok) {
		    const errorData = await notified.json();
		    console.error("Server error:", errorData);
		    alert("결제 처리 중 서버 오류가 발생했습니다. 다시 시도하세요.");
		    return;
		}else{ //결제처리 완료된 경우
			window.location.href = "/user/order_list";
		}
	}catch(e){
		console.error("Unexpected error:", e);
        alert("결제 요청 중 오류가 발생했습니다. 다시 시도하세요.");
        console.log(document.getElementById('total-price'));
	}
}

function getCartSummary() {
    const rows = document.querySelectorAll('tbody tr'); // tbody 안의 모든 행 가져오기

    const firstProductName = rows[0].querySelector('td:nth-child(2)').textContent.trim(); // 첫 번째 상품 이름
    const otherProductCount = rows.length - 1; // 나머지 상품 개수

    if (otherProductCount > 0) {
        return `${firstProductName} 외 ${otherProductCount}`;
    } else {
        return firstProductName; // 상품이 하나뿐인 경우
    }
}

//총액 숫자로 변환
function parseFormattedPrice(priceElement) {
	const priceString = priceElement.textContent;
    // 문자열에서 모든 콤마 제거 후 숫자로 변환
    const numericValue = Number(priceString.replace(/,/g, ''));
    return numericValue;
}