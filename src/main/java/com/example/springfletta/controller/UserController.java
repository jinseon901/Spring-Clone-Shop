package com.example.springfletta.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springfletta.entity.Address;
import com.example.springfletta.entity.Order;
import com.example.springfletta.service.CartService;
import com.example.springfletta.service.OrderService;

@Controller
@RequestMapping("/user")
public class UserController {
	private final CartService cartService;
	private final OrderService orderService;
	
	public UserController(CartService cartService, OrderService orderService) {
		this.cartService = cartService;
		this.orderService = orderService;
	}
	//Cart 기능
	@GetMapping("/cart")
    public String getCart(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String userId = userDetails.getUsername();
        model.addAttribute("cartList", cartService.getCartByUser(userId));
        return "user/cart"; 
    }
	
	@PostMapping("/cart/{command}")
	public String cart(@PathVariable("command") String command,
								@RequestParam("optionId") Long optionId, 
								@RequestParam("productId") Long productId, 
								@RequestParam("quantity") Integer quantity,
								Model model, 
								@AuthenticationPrincipal UserDetails userDetails) {
		String userId = userDetails.getUsername();
		
		if(command.equals("add")) {
			cartService.addToCart(userId, productId, optionId, quantity);
		}
		//cartService.getCartByUser(userId); 여기서 하는게 아니에용 ㅎㅎ;;
		return "redirect:/user/cart";
	}
	
	@PostMapping("/cart/quantity")
	public ResponseEntity<Map<String, Object>> updateCartQuantity(@RequestParam("optionId") Long optionId, 
													@RequestParam("actionType") String actionType) {
		cartService.updateCartQuantity(optionId, actionType);
		
		 Map<String, Object> response = new HashMap<>();
	        response.put("optionId", optionId);
	        response.put("newQuantity", cartService.getQuantityByOptionId(optionId));
	        
	        return ResponseEntity.ok(response);
	}
	
	//Order 기능
	@GetMapping("/order_sheet/{command}")
	public String getOrderSheet(Model model, 
												@AuthenticationPrincipal UserDetails userDetails,
												@PathVariable("command") String command) {
		String userId = userDetails.getUsername();
		model.addAttribute("command",command);
		if(command.equals("cart")) {
			model.addAttribute("cartList", cartService.getCartByUser(userId));
		}else { //상품 개별 구매의 경우
			//model.addAttribute("")
		}
        return "user/orderSheet";
	}
	
	@GetMapping("/order_list")
	public String getOrderList(Model model, 
											@AuthenticationPrincipal UserDetails userDetails) {
		String userId = userDetails.getUsername();
		List<Order> orderList = orderService.getOrderListByUserId(userId);
		Address address = orderService.getAddressByUserId(userId);
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("address", address);
		return "user/orderList";
	}
}
