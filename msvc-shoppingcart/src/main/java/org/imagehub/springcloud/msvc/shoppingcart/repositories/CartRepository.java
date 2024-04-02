package org.imagehub.springcloud.msvc.shoppingcart.repositories;

import org.imagehub.springcloud.msvc.shoppingcart.models.entity.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findByCartUserUserId(Long userId);
    void deleteByCartUserUserId(Long userId);
}

