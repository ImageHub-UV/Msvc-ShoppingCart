package org.imagehub.springcloud.msvc.shoppingcart.clients;

import org.imagehub.springcloud.msvc.shoppingcart.models.Image;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-gateway", contextId = "imageClient")
public interface ImageClientRest {
    @GetMapping("/api/images/api/Image/get-image-by-id?id={id}")
    Image getImage(@PathVariable Long id);
}