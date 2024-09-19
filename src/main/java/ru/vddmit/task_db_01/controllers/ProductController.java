package ru.vddmit.task_db_01.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vddmit.task_db_01.models.Product;
import ru.vddmit.task_db_01.services.ProductService;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;



    @GetMapping("/")
    public String products(Model model){

        model.addAttribute("products", productService.listProducts());

        return "products";
    }
    @GetMapping("/product/{product_id}")
    public String productInfo(@PathVariable int product_id, Model model){
        model.addAttribute("product", productService.getProductById(product_id));
        return "product_info";
    }

    @PostMapping("/product/create")
    public String createProduct(Product product){
    productService.saveProduct(product);
    return "redirect:/";
    }
    @PostMapping("/product/delete/{product_id}")
    public String deleteProduct(@PathVariable int product_id){
        productService.deleteProduct(product_id);
        return "redirect:/";
    }
}
