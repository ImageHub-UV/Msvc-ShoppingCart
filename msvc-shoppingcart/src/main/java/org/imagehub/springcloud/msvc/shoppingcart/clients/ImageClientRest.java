package org.imagehub.springcloud.msvc.shoppingcart.clients;

import org.imagehub.springcloud.msvc.shoppingcart.models.Image;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="msvc-images", url = "localhost:8001") // Have to be identical to the name of the service
public interface ImageClientRest  {

    @GetMapping("/images/{id}") //Have to be identical to the path of the service
    Image getImage(@PathVariable Long id);
}