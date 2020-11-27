package org.turntabl.ExchangeConnectivity.model;

public class OrderRequest {
    private String product;
    private Integer quantity;
    private Double price;
    private String side;

    public OrderRequest() {
    }

    public OrderRequest(String product, Integer quantity, Double price, String side) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "{" +
                "\"product\": \"" + product + "\"" +
                ", \"quantity\":\"" + quantity + "\"" +
                ", \"price\":\"" + price + "\"" +
                ", \"side\":\"" + side + "\"" +
                "}";
    }
}
