package org.imagehub.springcloud.msvc.shoppingcart.controllers;

import feign.FeignException;
import jakarta.validation.Valid;
import org.imagehub.springcloud.msvc.shoppingcart.models.Image;
import org.imagehub.springcloud.msvc.shoppingcart.models.entity.Cart;
import org.imagehub.springcloud.msvc.shoppingcart.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CartController {
    private final CartService service;


    public CartController(CartService cartService) {
        this.service = cartService;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createNewCartByUser/{userId}")
    public ResponseEntity<?> createNewCartByUser(@PathVariable Long userId) {
        Optional<Cart> o;

        try {
            o = service.createNewCart(userId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message",
                    "Don't exist any user with this id or error with the user service: " + e.getMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Cart>> listCarts() {
        return ResponseEntity.ok(service.listCarts());
    }

    /*
     * public Map<String, List<Cart>> listCarts() {
     * return Collections.singletonMap("carts", service.listCarts());
     * }
     */

    @GetMapping("/getCartByUserId/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {

        Optional<Cart> o;

        try {
            o = service.getCartByUserId(userId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message",
                    "Don't exist user with this id or error with the user service: " + e.getMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteCartByUserId/{userId}")
    public ResponseEntity<?> deleteCartByUserId(@PathVariable Long userId) {
        try {
            service.deleteCartByUserId(userId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message",
                    "Don't exist user with this id or error with the user service: " + e.getMessage()));
        }

        return ResponseEntity.ok().body(Collections.singletonMap("message", "Cart deleted successfully"));
    }

    @PutMapping("/updateCartByUserId/{userId}")
    public ResponseEntity<?> editCart(@Valid @RequestBody Cart cart, BindingResult result, @PathVariable Long userId) {

        if (result.hasErrors()) {
            return validate(result);
        }

        Optional<Cart> o;

        try {
            o = service.getCartByUserId(userId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message",
                    "Don't exist user with this id or error with the user service: " + e.getMessage()));
        }

        if (o.isPresent()) {
            Cart cartDb = o.get();

            cartDb.setCartUser(cart.getCartUser());
            cartDb.setCartImages(cart.getCartImages());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCart(cartDb));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/assignImageToCartByUserId/{userId}/{imageId}")
    public ResponseEntity<?> assignImageToCart(@PathVariable Long userId, @PathVariable Long imageId) {
        Optional<Image> o;

        try {
            o = service.assignImageToCart(imageId, userId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message",
                            "Don't exist image or user with this id or error with the image or user service: "
                                    + e.getMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteImageFromCart/{userId}")
    public ResponseEntity<?> deleteImageFromCart(@RequestBody Image image, @PathVariable Long userId) {
        Optional<Image> o;

        try {
            o = service.deleteImageFromCart(image, userId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message",
                            "Don't exist image or user with this id or error with the image or user service: "
                                    + e.getMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteAllImagesFromCart/{userId}")
    public ResponseEntity<?> deleteAllImageFromCart(@PathVariable Long userId) {
        try {
            service.deleteAllImagesFromCart(userId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message",
                    "Don't exist user with this id or error with the user service: " + e.getMessage()));
        }

        return ResponseEntity.ok().body(Collections.singletonMap("message", "All images deleted successfully"));
    }

    private ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
