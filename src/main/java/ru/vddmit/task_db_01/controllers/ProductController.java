package ru.vddmit.task_db_01.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vddmit.task_db_01.models.Product;
import ru.vddmit.task_db_01.services.ProductService;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/products")
    public String products(@RequestParam(name = "product_name", required = false) String productName, Model model) {
        model.addAttribute("products", productService.listProducts(productName));
        return "/products";
    }

    @GetMapping("/product/{product_id}/edit")
    public String editProductForm(@PathVariable String product_id, Model model) {
        long id = Long.parseLong(product_id.replace("\u00A0", "").trim());
        logger.info("Id of product to edit: {}", id);
        model.addAttribute("product", productService.getProductById(id));
        return "/edit_product";
    }

    @PostMapping("/product/{product_id}/edit")
    public String editProduct(@PathVariable String product_id, @ModelAttribute Product product) {
        long id = Long.parseLong(product_id.replace("\u00A0", "").trim());
        product.setProductId(id);
        productService.saveProduct(product);
        logger.info("Product updated with id: {}", id);
        return "redirect:/products";
    }

    @PostMapping("/product/create")
    public String createProduct(Product product) {
        productService.saveProduct(product);
        logger.info("Product created with id: {}", product.getProductId());
        return "redirect:/products";
    }

    @PostMapping("/product/{product_id}/delete")
    public String deleteProduct(@PathVariable String product_id, Model model) {
        long id = Long.parseLong(product_id.replace("\u00A0", "").trim());
        if (!productService.canDeleteProduct(id)) {
            model.addAttribute("error", "Cannot delete product. It is linked to one or more orders.");
            logger.error("Cannot delete product. It is linked to one or more orders. id = {}, product_id = {}", id, product_id);
            return "redirect:/products";
        }
        productService.deleteProduct(id);
        logger.info("Product deleted with id: {}", id);
        return "redirect:/products";
    }
}
