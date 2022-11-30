package com.warehouse.app.api.product.product;

import com.warehouse.app.factory.product.product.FactoryCreateProduct;
import com.warehouse.app.factory.product.product.FactoryModifyProduct;
import com.warehouse.app.domain.product.product.Product;
import com.warehouse.app.factory.product.product.IFactoryCreateProduct;
import com.warehouse.app.factory.product.product.IFactoryModifyProduct;
import com.warehouse.app.service.product.product.ProductService;
import com.warehouse.app.domain.DataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {

    @Autowired
    @Qualifier("Default_FactoryCreateProduct")
    private IFactoryCreateProduct factoryCreate;

    @Autowired
    @Qualifier("Default_FactoryModifyProduct")
    private IFactoryModifyProduct factoryModify;

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getProducts () {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct (@PathVariable("id") Long id) {
        return service.get(id);
    }

    @PostMapping
    public ResponseEntity<Product> postProduct(@RequestBody(required = false) DataMap<Object> payload) throws Exception {
        Product product = factoryCreate.save(payload);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> putProduct(@PathVariable("id") Long id, @RequestBody(required = false) DataMap<Object> payload) throws Exception {
        Product product = factoryModify.update(id, payload);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        service.delete(id);
    }

}