package ru.vddmit.task_db_01.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vddmit.task_db_01.models.Product;
import ru.vddmit.task_db_01.services.ProductService;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public String products(@RequestParam(name = "product_name", required = false) String productName, Model model) {
        model.addAttribute("products", productService.listProducts(productName));
        return "/products";
    }
    @GetMapping("/product/{product_id}/edit")
    public String editProductForm(@PathVariable long product_id, Model model) {
        model.addAttribute("product", productService.getProductById(product_id));
        return "/edit_product";
    }
    @PostMapping("/product/{product_id}/edit")
    public String editProduct(@PathVariable long product_id, @ModelAttribute Product product, Model model) {
        product.setProductId(product_id);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/product/{product_id}")
    public String productInfo(@PathVariable long product_id, Model model) {
        model.addAttribute("product", productService.getProductById(product_id));
        return "product_info";
    }

    @PostMapping("/product/create")
    public String createProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }


    @PostMapping("/product/{product_id}/delete")
    public String deleteProduct(@PathVariable String product_id) {
        long id = Long.parseLong(product_id.replace("\u00A0", "").trim());
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
