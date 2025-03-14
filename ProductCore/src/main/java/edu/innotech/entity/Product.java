package edu.innotech.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedEntityGraph(
        name = "Product.with-product-type",
        attributeNodes = {
                @NamedAttributeNode("productTypeId"),
                @NamedAttributeNode("userId")
        }
)

@Getter
@Setter
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account")
    private String account;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_type_id")
    private ProductType productTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", amount=" + amount +
                ", productTypeId=" + productTypeId.toString() +
                ", userId=" + userId.toString() +
                '}';
    }
}
