package com.microservice.product;

import com.microservice.product.config.AppConfig;
import com.microservice.product.entity.Product;
import com.microservice.product.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            ProductService productService = context.getBean(ProductService.class);
            Product product = new Product("Microservice Gateway", 149.99);
            productService.addProduct(product);
            System.out.println("Produit ajouté avec succès ! Total en base : "
                    + productService.getAllProducts().size());
        }
    }
}
