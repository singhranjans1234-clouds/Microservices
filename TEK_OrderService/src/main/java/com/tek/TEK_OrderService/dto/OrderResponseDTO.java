package com.tek.TEK_OrderService.dto;

public class OrderResponseDTO {

    private Long id;
    private String product;
    private Double amount;
    private UserDTO user;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Long id, String product, Double amount, UserDTO user) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
