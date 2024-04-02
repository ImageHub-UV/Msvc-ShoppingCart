package org.imagehub.springcloud.msvc.shoppingcart.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_users")
public class CartUser {

    /*
        * This is a intermediate in charge of register the id of the user in a cart item in particular
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This is unique because every user can have only one cart at a time
    @Column(name = "user_id", unique = true)
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        CartUser o = (CartUser) obj;
        return this.userId != null && this.userId.equals(o.userId);

    }
}
