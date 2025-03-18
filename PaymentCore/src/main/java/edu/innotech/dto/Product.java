package edu.innotech.dto;

public class Product {
    private Long id;
    private String account;
    private Double amount;
    private ProductType productTypeId;
    private User userId;

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public Double getAmount() {
        return amount;
    }

    public ProductType getProductTypeId() {
        return productTypeId;
    }

    public User getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setProductTypeId(ProductType productTypeId) {
        this.productTypeId = productTypeId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
