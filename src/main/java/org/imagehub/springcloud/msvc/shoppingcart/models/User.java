package org.imagehub.springcloud.msvc.shoppingcart.models;


public record User(
    Long id,
    String username,
    String password,
    String first_name,
    String last_name,
    String email
) {

}
