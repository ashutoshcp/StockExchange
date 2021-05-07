import java.util.Comparator;

public class SellStockComparator implements Comparator<StockInput> {
    @Override public int compare(StockInput o1, StockInput o2) {
        if (o1.getPrice().equals(o2.getPrice())) {
            return o1.getTime() - o2.getTime();
        }
        return (int) (o1.getPrice() - o2.getPrice());
    }
}
