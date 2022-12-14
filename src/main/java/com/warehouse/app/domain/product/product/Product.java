package com.warehouse.app.domain.product.product;

import com.warehouse.app.domain.WarehouseEntity;
import com.warehouse.app.domain.category.Category;
import com.warehouse.app.domain.platform.Platform;
import com.warehouse.app.domain.product.collection.Collection;
import com.warehouse.app.domain.product.situation.ProductSituation;
import com.warehouse.app.domain.user.User;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Entity
@Table(name = "db_products", uniqueConstraints = {
        @UniqueConstraint(
                name = "uc_products",
                columnNames = {"name", "version", "platform", "format", "region", "category"})
})
public class Product implements WarehouseEntity<Product> {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String VERSION = "version";
    public static final String PLATFORM = "platform";
    public static final String FORMAT = "format";
    public static final String REGION = "region";
    public static final String CATEGORY = "category";
    public static final String DATE_ORIGEN = "date_origen";
    public static final String USER_AUDIT = "user_audit";
    public static final String SITUATION_FK = "situation_fk";
    public static final String COLLECTION_FK = "collection_fk";

    // Base ID
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence_name",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    @Column(name = ID, nullable = false, unique = true)
    private Long id;

    // UK
    @Column(name = NAME, nullable = false)
    private String name;
    @Column(name = VERSION, nullable = false)
    private String version;
    @JoinColumn(name = PLATFORM, nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Platform platform;
    @Column(name = FORMAT, nullable = false)
    private String format;
    @Column(name = REGION, nullable = false)
    private String region;
    @JoinColumn(name = CATEGORY, nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Category category;

    // Misc
    @Column(name = DATE_ORIGEN, nullable = false)
    private Date dateOrigen;
    @JoinColumn(name = USER_AUDIT, nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private User userAudit;
    @JoinColumn(name = SITUATION_FK)
    @OneToOne(fetch = FetchType.EAGER)
    private ProductSituation situation;

    @JoinColumn(name = COLLECTION_FK)
    @OneToOne(fetch = FetchType.EAGER)
    private Collection collection;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDateOrigen() {
        return dateOrigen;
    }

    public void setDateOrigen(Date dateOrigen) {
        this.dateOrigen = dateOrigen;
    }

    public User getUserAudit() {
        return userAudit;
    }

    public void setUserAudit(User userAudit) {
        this.userAudit = userAudit;
    }

    public ProductSituation getSituation() {
        return situation;
    }

    public void setSituation(ProductSituation situation) {
        this.situation = situation;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    @Override
    public Product update(Product entity) {
        return null;
    }

    @Override
    public Product clone() {
        return new Product(
                getId(),
                getName(),
                getVersion(),
                getPlatform(),
                getFormat(),
                getRegion(),
                getCategory(),
                getDateOrigen(),
                getUserAudit(),
                getSituation(),
                getCollection());
    }
}
