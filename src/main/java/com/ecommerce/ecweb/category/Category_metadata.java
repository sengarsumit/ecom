package com.ecommerce.ecweb.category;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Category_metadata {
    @Id
    private UUID MID;   //metadata id
    private String name;
    @OneToMany(mappedBy = "category_metadata")
    private List<cat_meta_data_value> cat_metaDataValueList;

}
