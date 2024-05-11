package org.imagehub.springcloud.msvc.shoppingcart.clients;

import org.imagehub.springcloud.msvc.shoppingcart.models.Image;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="images-service-service") // Have to be identical to the name of the service
public interface ImageClientRest  {
    @GetMapping("/api/Image/get-image-by-id?id={id}") //Have to be identical to the path of the service
    Image getImage(@PathVariable Long id);
}