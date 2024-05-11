package org.imagehub.springcloud.msvc.shoppingcart.models;

import java.util.List;

public record Category(
    Long categoryId,
    String name,
    String description) {
}
