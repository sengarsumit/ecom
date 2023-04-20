package com.ecommerce.ecweb.category;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class cat_meta_data_value {
    @Id
    private UUID metadid;
    @ManyToOne
    @JoinColumn(name ="category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "cateogry_meta_fieldid")
    private Category_metadata category_metadata;

    private String value;
}
