package com.example.springfletta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springfletta.entity.Product;
import com.example.springfletta.entity.ProductOption;
import com.example.springfletta.service.ProductListService;
import com.example.springfletta.service.ProductViewService;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	//생성자 주입
	private final ProductListService productListService;
	private final ProductViewService productViewService;

    public ProductController(ProductListService productListService, ProductViewService productViewService) {
        this.productListService = productListService;
        this.productViewService = productViewService;
    }
	
	@GetMapping("/product_list")
	public String productList(@RequestParam(name ="sort", defaultValue = "default") String sort, Model model) {
		List<Product> productList = productListService.getProductListByCategory(sort);
		//setAttribute 역할로, jsp에서 ${}를 통해 접근 가능
		model.addAttribute("productList", productList);
		model.addAttribute("sort", sort);
		
		return "product/productList";
	}
	
	@GetMapping("product_view")
	public String productView(@RequestParam(name = "productId", defaultValue = "default") Long productId, Model model) {
		Product product = productViewService.getProductById(productId); 
		List<ProductOption> optionList = productViewService.getOptionByProduct(product);
        logger.info("📢 optionList 값: {}", optionList); // ✅ INFO 로그 출력
		
		model.addAttribute("product", product);
		model.addAttribute("optionList", optionList);
		
		return "product/productView";
	}

}
