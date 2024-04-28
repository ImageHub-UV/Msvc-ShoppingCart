package org.imagehub.springcloud.msvc.shoppingcart.models;


public record Image(
    Long image_id,
    Long image_creator,
    String image_name,
    String image_description,
    String image_format,
    Double image_price,
    Boolean image_is_free
) {

}
