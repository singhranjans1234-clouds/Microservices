package com.tek.TEK_OrderService.dto;

public class OrderRequestDTO {

    private Long userId;
    private String product;
    private Double amount;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(Long userId, String product, Double amount) {
        this.userId = userId;
        this.product = product;
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
