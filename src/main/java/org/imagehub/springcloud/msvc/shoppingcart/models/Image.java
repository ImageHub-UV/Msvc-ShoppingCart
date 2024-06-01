package org.imagehub.springcloud.msvc.shoppingcart.models;

import java.util.List;
import org.imagehub.springcloud.msvc.shoppingcart.models.Category;

public record Image(
        Long imageId,
        Long creator,
        String src,
        String name,
        String description,
        String format,
        Double price,
        Boolean isFree,
        Boolean isDefault,
        Long width,
        Long height,
        List<Category> categories) {

}
