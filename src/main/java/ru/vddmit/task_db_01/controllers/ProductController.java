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
    public String editProductForm(@PathVariable String product_id, Model model) {
        long id = Long.parseLong(product_id.replace("\u00A0", "").trim());
        System.out.println("Id of edit product: " + product_id);
        model.addAttribute("product", productService.getProductById(id));
        return "/edit_product";
    }

    @PostMapping("/product/{product_id}/edit")
    public String editProduct(@PathVariable String product_id, @ModelAttribute Product product, Model model) {
        long id = Long.parseLong(product_id.replace("\u00A0", "").trim());
        product.setProductId(id);
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
    public String deleteProduct(@PathVariable String product_id, Model model) {

        long id = Long.parseLong(product_id.replace("\u00A0", "").trim());
        System.out.println("Id of product for delete: " + id);
        if (!productService.canDeleteProduct(id)) {
            model.addAttribute("error", "Cannot delete product. It is linked to one or more orders.");
            return "redirect:/products";
        }
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
