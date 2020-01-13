package courseWork.controllers;

import courseWork.logic.Product;
import courseWork.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class ProductController {

    private productService productService;
    @Autowired
    public void setService(@NotNull final  productService service) {
        this.productService  = service;
    }

    @GetMapping(path="/getAllProducts/")
    public @ResponseBody
    @NotNull Iterable<Product> getOrderProducts() {
        // This returns a JSON or XML with the users
        return this.productService.getAllProducts();
    }
}
