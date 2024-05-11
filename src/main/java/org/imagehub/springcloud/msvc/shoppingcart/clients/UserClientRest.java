package org.imagehub.springcloud.msvc.shoppingcart.clients;

import org.imagehub.springcloud.msvc.shoppingcart.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-msvc-service")// Have to be identical to the name of the service
public interface UserClientRest {
    @GetMapping("/user/do/{id}") //Have to be identical to the path of the service
    User getUser(@PathVariable Long id);
}
