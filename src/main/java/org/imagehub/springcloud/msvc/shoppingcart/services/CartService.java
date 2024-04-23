package org.imagehub.springcloud.msvc.shoppingcart.services;

import org.imagehub.springcloud.msvc.shoppingcart.models.Image;
import org.imagehub.springcloud.msvc.shoppingcart.models.User;
import org.imagehub.springcloud.msvc.shoppingcart.models.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {

    Optional<Cart> createNewCart(Long userId);

    Cart saveCart(Cart cart);

    List<Cart> listCarts();

    Optional<Cart> getCartByUserId(Long userId);

    void deleteCartByUserId(Long userId);

    Optional<Image> assignImageToCart(Image image, Long userId);

    Optional<Image> deleteImageFromCart(Image image, Long userId);

    void deleteAllImagesFromCart(Long userId);
}
