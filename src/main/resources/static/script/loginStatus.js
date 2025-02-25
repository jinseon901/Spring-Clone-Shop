function handleLoginStatus() {
    const addToCartButton = document.querySelector('input[type="submit"][value="ADD TO CART"]');
    const buyItNowButton = document.querySelector('input[type="button"][value="BUY IT NOW"]');
    
    if (!isLoggedIn) {
        addToCartButton.disabled = true;
        addToCartButton.value = "Login to Add to Cart";
        addToCartButton.style.backgroundColor = "#d3d3d3";
        addToCartButton.style.cursor = "not-allowed";

        addToCartButton.addEventListener('click', function (e) {
            e.preventDefault();
            alert('Please login to add items to your cart.');
        });
        
        buyItNowButton.disabled = true;
        buyItNowButton.value = "Login to Buy it now";
        buyItNowButton.style.backgroundColor = "#d3d3d3";
        buyItNowButton.style.cursor = "not-allowed";
        
        buyItNowButton.addEventListener('click', function (e){
			e.preventDefault();
			alert('Please login to buy items to your cart');
		});
    }
}