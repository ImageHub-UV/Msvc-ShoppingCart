package org.imagehub.springcloud.msvc.shoppingcart;

import org.imagehub.springcloud.msvc.shoppingcart.clients.ImageClientRest;
import org.imagehub.springcloud.msvc.shoppingcart.clients.UserClientRest;
import org.imagehub.springcloud.msvc.shoppingcart.controllers.CartController;
import org.imagehub.springcloud.msvc.shoppingcart.models.User;
import org.imagehub.springcloud.msvc.shoppingcart.models.entity.Cart;
import org.imagehub.springcloud.msvc.shoppingcart.services.CartService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private ImageClientRest imageClientRest;

    @MockBean
    private UserClientRest userClientRest;

    @Autowired
    private CartController cartController;

    @Test
    public void testCreateNewCart() {
        User user = new User();
        user.setId(1L);
        Mockito.when(userClientRest.getUser(1L)).thenReturn(user);


        Optional<Cart> cart = cartService.createNewCart(1L);

        assertTrue(cart.isPresent());
        assertEquals(1L, cart.get().getCartUser().getUserId());
    }
}
