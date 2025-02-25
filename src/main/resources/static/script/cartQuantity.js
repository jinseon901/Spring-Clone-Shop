function increaseQuantity(optionId) {
    $.ajax({
        url: '/user/cart/quantity',
        type: 'POST',
        data: {
            command: 'cartQuantity',
            optionId: optionId,
            actionType: 'increase'
        },
        success: function(response) {
            console.log('Quantity increased successfully:', response);
            updateCartUI(response);
            updateTotalPrice();
        },
        error: function(xhr, status, error) {
            console.error('Error increasing quantity:', error);
        }
    });
}


function decreaseQuantity(optionId) {
    $.ajax({
        url: '/user/cart/quantity',
        type: 'POST',
        data: { 
			command: 'cartQuantity', 
        	optionId: optionId,
        	actionType: 'decrease' 
        },
        success: function(response) {
            console.log('Quantity increased successfully:', response);
            updateCartUI(response);
            updateTotalPrice();
        },
        error: function(xhr, status, error) {
            console.error('Error increasing quantity:', error);
        }
    });
}

function deleteItem(optionId) {
    $.ajax({
        url: '/user/cart/quantity',
        type: 'POST',
        data: { 
			command: 'cartQuantity',
			optionId: optionId,
       		actionType: 'delete'},
        success: function(response) {
            console.log('Quantity increased successfully:', response);
            updateCartUI(response);
            updateTotalPrice();
            $('#cart-' + optionId).remove(); //화면에 바로 적용
        },
        error: function(xhr, status, error) {
            console.error('Error increasing quantity:', error);
        }
    });
}

//수량 변경 후 ui 업데이트하기
function updateCartUI(response) {
			const quantity = parseInt(response.newQuantity);
			const optionId = response.optionId;
            // 수량 업데이트
            $('#cart-' + optionId + ' .quantity').text(quantity);
}

function updateTotalPrice(){
		//여기부터 total price
		let totalPrice = 0; // 총 가격 초기화

        // 각 상품의 행을 순회
        document.querySelectorAll('tbody tr').forEach(row => {
            const unitPrice = parseFloat(row.getAttribute('data-unit-price')); // 상품 단가
            const quantity = parseInt(row.querySelector('.quantity').textContent); // 상품 수량
            // 총 가격 계산
            totalPrice += unitPrice * quantity;
            
            const optionId = row.getAttribute('id');
			const newPrice = (unitPrice * quantity).toLocaleString('ko-KR');
            $('#' + optionId + ' .price').text(newPrice);
        });
        $('#total-price').text(totalPrice.toLocaleString('ko-KR'));
}



