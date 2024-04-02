package org.imagehub.springcloud.msvc.shoppingcart.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_images")
public class CartImage {

    /*
     * This is a intermediate in charge of register the id of the image in a cart item in particular
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="image_id")
    private Long imageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        CartImage o = (CartImage) obj;
        return this.imageId != null && this.imageId.equals(o.imageId);

    }
}
