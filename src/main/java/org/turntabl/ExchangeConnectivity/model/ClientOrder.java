package org.turntabl.ExchangeConnectivity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientOrder {

    private String orderId;

    private String clientId;

    private String portfolioId;

    private String side;

    private int quantity;

    private String ticker;

    private String timeStamp;

    private String url;

    private double price;

    private String orderKey;

    public ClientOrder() {
    }

    public ClientOrder(String orderId, String clientId, String portfolioId, String side, int quantity, String ticker, String url, String orderKey, double price) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.portfolioId = portfolioId;
        this.side = side;
        this.quantity = quantity;
        this.ticker = ticker;
        this.url = url;
        this.orderKey = orderKey;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public String getOrderKey() { return orderKey; }

    public void setOrderKey(String orderKey) { this.orderKey = orderKey; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", portfolioId='" + portfolioId + '\'' +
                ", ticker='" + ticker + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", side='" + side + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", orderKey='" + orderKey + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
