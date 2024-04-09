package org.imagehub.springcloud.msvc.shoppingcart.services;

import org.imagehub.springcloud.msvc.shoppingcart.clients.ImageClientRest;
import org.imagehub.springcloud.msvc.shoppingcart.clients.UserClientRest;
import org.imagehub.springcloud.msvc.shoppingcart.models.Image;
import org.imagehub.springcloud.msvc.shoppingcart.models.User;
import org.imagehub.springcloud.msvc.shoppingcart.models.entity.Cart;
import org.imagehub.springcloud.msvc.shoppingcart.models.entity.CartImage;
import org.imagehub.springcloud.msvc.shoppingcart.models.entity.CartUser;
import org.imagehub.springcloud.msvc.shoppingcart.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository repository;

    @Autowired
    private ImageClientRest imageClientRest;

    @Autowired
    private UserClientRest userClientRest;

    @Override
    @Transactional()
    public Optional<Cart> createNewCart(Long userId) {

        Optional<Cart> existingCart = repository.findByCartUserUserId(userId);
        if (existingCart.isPresent()) {
            return existingCart;
        }

        Cart cart = new Cart();

        User userMvsc = userClientRest.getUser(userId);

        CartUser cartUser = new CartUser();
        cartUser.setUserId(userMvsc.getId());

        cart.setCartUser(cartUser);

        repository.save(cart);

        return Optional.of(cart);
    }

    @Override
    @Transactional
    public Cart saveCart(Cart cart) {
        return repository.save(cart);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cart> listCarts() {
        return (List<Cart>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cart> getCartByUserId(Long userId) {
        return repository.findByCartUserUserId(userId);
    }

    @Override
    @Transactional
    public void deleteCartByUserId(Long userId) {
        repository.deleteByCartUserUserId(userId);
    }

    @Override
    @Transactional
    public Optional<Image> assignImageToCart(Image image, Long userId) {
        Optional<Cart> o = repository.findByCartUserUserId(userId);
        if(o.isPresent()){
            Image imageMsvc = imageClientRest.getImage(image.getImage_id());

            Cart cart = o.get();
            CartImage cartImage = new CartImage();
            cartImage.setImageId(imageMsvc.getImage_id());

            cart.addCartImage(cartImage);
            repository.save(cart);

            return Optional.of(imageMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Image> deleteImageFromCart(Image image, Long userId) {
        Optional<Cart> o = repository.findByCartUserUserId(userId);
        if(o.isPresent()){
            Image imageMsvc = imageClientRest.getImage(image.getImage_id());

            Cart cart = o.get();
            CartImage cartImage = new CartImage();
            cartImage.setImageId(imageMsvc.getImage_id());

            cart.removeCartImage(cartImage);
            repository.save(cart);

            return Optional.of(imageMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteAllImagesFromCart(Long userId) {
        Optional<Cart> o = repository.findByCartUserUserId(userId);
        if(o.isPresent()){
            Cart cart = o.get();
            cart.removeAllCartImages();
            repository.save(cart);
        }
    }
}
