package com.ecommerce.ecweb.category;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Category {
    @Id
    private UUID catID;
    private String Name;
    @ManyToOne
    @JoinColumn(name = "parent_catid")

    private Category category;

    @OneToMany
    private List<Category> parent;

    @OneToMany(mappedBy = "category")
    private List<cat_meta_data_value> cat_metaDataValue;


}
