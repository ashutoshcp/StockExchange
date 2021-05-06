import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Geektrust {
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("Please specify a path for input file!");
        }
        else {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            new Geektrust().process(br);
            br.close();
            fileReader.close();
        }
    }

    private void process(BufferedReader br) throws IOException {
        String line;
        PriorityQueue<StockInput> sell = new PriorityQueue<>(100, new SellStockComparator());
        PriorityQueue<StockInput> buy = new PriorityQueue<>(100, new BuyStockComparator());
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            line = line.trim();
            String[] s = line.split(" ");
            assert s.length == 6;
            StockType type = StockType.valueOf(s[3]);
            int quantity = Integer.parseInt(s[5]);
            int time = Integer.parseInt(s[1].replace(":", ""));
            double price = Double.parseDouble(s[4]);
            StockInput input = new StockInput(s[0], time, s[2], type, price, quantity);
            processInputStock(sell, buy, type, input);
        }
    }

    private void processInputStock(
            PriorityQueue<StockInput> sell,
            PriorityQueue<StockInput> buy,
            StockType type,
            StockInput input) {
        switch (type) {
            case buy:
                buy.add(input);
                break;
            case sell:
                sell.add(input);
                break;
            default:
                break;
        }
        if (!buy.isEmpty() && !sell.isEmpty()) {
            buySellAssets(sell, buy);
        }
    }

    private void buySellAssets(PriorityQueue<StockInput> sell, PriorityQueue<StockInput> buy) {
        /*System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(sell);
        System.out.println(buy);*/
        while (true) {
            if (sell.isEmpty() || buy.isEmpty()) {
                break;
            }
            StockInput sPeek = sell.peek();
            StockInput bPeek = buy.peek();
            if (bPeek.getPrice() >= sPeek.getPrice()) {
                sell.remove(sPeek);
                buy.remove(bPeek);
                int minQ = Math.min(sPeek.getQuantity(), bPeek.getQuantity());
                String price = String.format("%.2f", sPeek.getPrice());
                System.out.println(bPeek.getId() + " " + price + " " + minQ + " " + sPeek.getId());
                if (sPeek.getQuantity() > bPeek.getQuantity()) {
                    sPeek.setQuantity(sPeek.getQuantity() - bPeek.getQuantity());
                    sell.add(sPeek);
                }
                else if (sPeek.getQuantity() < bPeek.getQuantity()) {
                    bPeek.setQuantity(-sPeek.getQuantity() + bPeek.getQuantity());
                    buy.add(bPeek);
                }
            }
            else {
                break;
            }
        }
        /*System.out.println("-----------------------------------------------------------------------------------");*/
    }

    private class BuyStockComparator implements Comparator<StockInput> {
        @Override
        public int compare(StockInput o1, StockInput o2) {
            if (o1.getPrice().equals(o2.getPrice())) {
                return o1.getTime() - o2.getTime();
            }
            return (int) (o2.getPrice() - o1.getPrice());
        }
    }

    private class SellStockComparator implements Comparator<StockInput> {
        @Override
        public int compare(StockInput o1, StockInput o2) {
            if (o1.getPrice().equals(o2.getPrice())) {
                return o1.getTime() - o2.getTime();
            }
            return (int) (o1.getPrice() - o2.getPrice());
        }
    }

    private class StockInput {
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

        @Override
        public String toString() {
            return "StockInput{"
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

    private enum StockType {
        sell, buy
    }
}
