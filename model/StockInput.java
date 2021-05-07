package model;

public class StockInput {
    private final String    id;
    private final Integer   time;
    private final String    stockName;
    private final StockType type;
    private final Double    price;
    private       Integer   quantity;

    public StockInput(
            String id, Integer time, String stockName, StockType type, Double price, Integer quantity) {
        this.id = id;
        this.time = time;
        this.stockName = stockName;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public Integer getTime() {
        return time;
    }

    public String getStockName() {
        return stockName;
    }

    public StockType getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override public String toString() {
        return "model.StockInput{"
                + "id='"
                + id
                + '\''
                + ", time="
                + time
                + ", stockName='"
                + stockName
                + '\''
                + ", type="
                + type
                + ", price="
                + price
                + ", quantity="
                + quantity
                + '}';
    }
}
