package org.imagehub.springcloud.msvc.shoppingcart.models;


public record User(
    Long id,
    String username,
    String first_name,
    String last_name,
    String email
) {

}
