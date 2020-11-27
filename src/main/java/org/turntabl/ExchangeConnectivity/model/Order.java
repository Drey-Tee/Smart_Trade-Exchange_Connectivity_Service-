package org.turntabl.ExchangeConnectivity.model;

public class Order {

    private String product;
    private String quantity;
    private String price;
    private String side;
    private int marketId;

    public Order() {
    }

    public Order(String product, String quantity, String price, String side, int marketId) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.marketId = marketId;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getProduct() {
        return product;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getSide() {
        return side;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public static Order deserializer(String data){
       String[] splits=  data.split(":");
        String[] value = splits[4].split("=");
        return new Order(splits[0],splits[1],splits[2],splits[3],Integer.parseInt(value [0]));
    }


}
