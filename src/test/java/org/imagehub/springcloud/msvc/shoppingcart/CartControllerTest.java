package org.imagehub.springcloud.msvc.shoppingcart;

import org.imagehub.springcloud.msvc.shoppingcart.controllers.CartController;
import org.imagehub.springcloud.msvc.shoppingcart.models.Image;
import org.imagehub.springcloud.msvc.shoppingcart.models.entity.Cart;
import org.imagehub.springcloud.msvc.shoppingcart.repositories.CartRepository;
import org.imagehub.springcloud.msvc.shoppingcart.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CartService service;


    @Test
    public void testCreateNewCartByUser() throws Exception {
        when(service.createNewCart(1L)).thenReturn(Optional.of(new Cart()));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/createNewCartByUser/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testListCarts() throws Exception {
        when(service.listCarts()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetCarByUserId() throws Exception {
        when(service.getCartByUserId(1L)).thenReturn(Optional.of(new Cart()));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getCartByUserId/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeleteCartByUserId() throws Exception {
        when(service.deleteCartByUserId(1L)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/deleteCartByUserId/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testEditCar() throws Exception {
        Cart cart = new Cart();
        when(service.getCartByUserId(1L)).thenReturn(Optional.of(cart));
        when(service.saveCart(cart)).thenReturn(cart);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/updateCartByUserId/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"cartUser\": \"user1\", \"cartImages\": []}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAssignImageToCart() throws Exception {
        Image image = new Image(null, null, null, null, null, null, null, null, null, null, null, null);
        when(service.assignImageToCart(1L, 1L)).thenReturn(Optional.of(image));

        mockMvc.perform(MockMvcRequestBuilders
                .put("/assignImageToCartByUserId/{userId}/{imageId}", 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteImageFromCart() throws Exception {
        Image image = new Image(null, null, null, null, null, null, null, null, null, null, null, null);
        when(service.deleteImageFromCart(1L, 1L)).thenReturn(Optional.of(image));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/deleteImageFromCart/{userId}/{imageId}", 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAllImageFromCart() throws Exception {
        when(service.deleteAllImagesFromCart(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/deleteAllImagesFromCart/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetImageFromCart() throws Exception {
        Image image = new Image(null, null, null, null, null, null, null, null, null, null, null, null);
        when(service.getImageFromCart(1L, 1L)).thenReturn(Optional.of(image));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/getImageFromCart/{userId}/{imageId}", 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
