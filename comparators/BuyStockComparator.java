package comparators;

import java.util.Comparator;

import model.StockInput;

public class BuyStockComparator implements Comparator<StockInput> {
    @Override
    public int compare(StockInput o1, StockInput o2) {
        if (o1.getPrice().equals(o2.getPrice())) {
            return o1.getTime() - o2.getTime();
        }
        return (int) (o2.getPrice() - o1.getPrice());
    }
}
