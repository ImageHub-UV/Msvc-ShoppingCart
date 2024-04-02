package org.imagehub.springcloud.msvc.shoppingcart.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public class Image {
    /*
     * This model has the same attributes as the Image model in the imagehub project
     */

    private Long image_id;
    private Long image_creator;
    private String image_name;
    private String image_description;
    private String image_format;
    private Double image_price;
    private Boolean image_is_free;

    public Long getImage_id() {
        return image_id;
    }

    public void setImage_id(Long image_id) {
        this.image_id = image_id;
    }

    public Long getImage_creator() {
        return image_creator;
    }

    public void setImage_creator(Long image_creator) {
        this.image_creator = image_creator;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_description() {
        return image_description;
    }

    public void setImage_description(String image_description) {
        this.image_description = image_description;
    }

    public String getImage_format() {
        return image_format;
    }

    public void setImage_format(String image_format) {
        this.image_format = image_format;
    }

    public Double getImage_price() {
        return image_price;
    }

    public void setImage_price(Double image_price) {
        this.image_price = image_price;
    }

    public Boolean getImage_is_free() {
        return image_is_free;
    }

    public void setImage_is_free(Boolean image_is_free) {
        this.image_is_free = image_is_free;
    }
}
