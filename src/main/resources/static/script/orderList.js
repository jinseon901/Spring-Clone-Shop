document.addEventListener('DOMContentLoaded', () => {
    updateDetailTable();
    updateOrderTable();
    updateStatistics();
});

// Detail Table 처리 함수
function updateDetailTable() {
    document.querySelectorAll('.detail-table tbody tr').forEach(row => {
        const unitPrice = parseInt(row.getAttribute('data-unit-price')); // 상품 단가
        const quantity = parseInt(row.querySelector('.quantity').textContent); // 상품 수량
        const newPrice = (unitPrice * quantity).toLocaleString('ko-KR');
        row.querySelector('.price').textContent = newPrice;
        console.log(unitPrice);
    });
}

// Order Table 처리 함수
function updateOrderTable() {
    document.querySelectorAll('.order-table tbody tr').forEach(row => {
		const amountElement = row.querySelector('.totalAmount');
		if (!amountElement) {		 //이유는 모르겠는데 이렇게 null검사 로직 추가하니까 해결됨
	        console.error('Element not found in row:', row);
	        return; // Null 요소가 있다면 이후 코드를 실행하지 않음
	    }
		
        const amount = parseInt(row.querySelector('.totalAmount').textContent);
        row.querySelector('.totalAmount').textContent = amount.toLocaleString('ko-KR');

        let status = row.querySelector('.status').textContent;
        row.querySelector('.status').textContent = getStatusText(status);
    });
}

// 상태 텍스트 변환 함수
function getStatusText(status) {
    switch (status) {
        case '0':
            return '결제 대기 중';
        case '1':
            return '상품 준비 중';
        case '2':
            return '배송 중';
        case '3':
            return '배송 완료';
        default:
            return '알 수 없음';
    }
}

// 통계 업데이트 함수
function updateStatistics() {
    let paymentCompleted = 0;
    let inDelivery = 0;
    let deliveryCompleted = 0;

    document.querySelectorAll('.order-table tbody tr').forEach(row => {
		const statusElement = row.querySelector('.status');
		if(!statusElement){
			console.error('Element not found in row:', row);
			return;
		}
		
        const status = row.querySelector('.status').textContent;
        switch (status) {
            case '상품 준비 중':
                paymentCompleted += 1;
                break;
            case '배송 중':
                inDelivery += 1;
                break;
            case '배송 완료':
                deliveryCompleted += 1;
                break;
        }
    });

    document.querySelector('.payment-completed').textContent = paymentCompleted;
    document.querySelector('.in-delivery').textContent = inDelivery;
    document.querySelector('.delivery-completed').textContent = deliveryCompleted;
}
