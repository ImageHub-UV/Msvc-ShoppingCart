package org.imagehub.springcloud.msvc.shoppingcart.models.entity;

import jakarta.persistence.*;
import org.imagehub.springcloud.msvc.shoppingcart.models.Image;
import org.imagehub.springcloud.msvc.shoppingcart.models.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_user_id", referencedColumnName = "id")
    private CartUser cartUser;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<CartImage> cartImages;

    @Transient //This atribute is not going to be saved in the database
    private List<Image> images;

    @Transient
    private User user;

    public Cart() {
        cartImages = new ArrayList<>();
        images = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartUser getCartUser() {
        return cartUser;
    }

    public void setCartUser(CartUser cartUser) {
        this.cartUser = cartUser;
    }

    public List<CartImage> getCartImages() {
        return cartImages;
    }

    public void setCartImages(List<CartImage> cartImages) {
        this.cartImages = cartImages;
    }

    public void addCartImage(CartImage cartImage) {
        cartImages.add(cartImage);
    }

    public void removeCartImage(CartImage cartImage) {
        cartImages.remove(cartImage);
    }

    public void removeAllCartImages() {
        cartImages.clear();
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
