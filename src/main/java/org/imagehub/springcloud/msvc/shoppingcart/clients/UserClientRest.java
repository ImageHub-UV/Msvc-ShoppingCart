package org.imagehub.springcloud.msvc.shoppingcart.clients;

import org.imagehub.springcloud.msvc.shoppingcart.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-gateway", contextId = "userClient")
public interface UserClientRest {
    @GetMapping("/api/user/user/do/{id}")
    User getUser(@PathVariable Long id);
}