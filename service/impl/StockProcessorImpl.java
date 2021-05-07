package service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.PriorityQueue;

import comparators.BuyStockComparator;
import comparators.SellStockComparator;
import model.StockInput;
import model.StockType;
import service.StockProcessor;

public class StockProcessorImpl implements StockProcessor {

    @Override
    public void process(BufferedReader br) throws IOException {
        String line;
        PriorityQueue<StockInput> sell = new PriorityQueue<>(100, new SellStockComparator());
        PriorityQueue<StockInput> buy = new PriorityQueue<>(100, new BuyStockComparator());
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            line = line.trim();
            line = line.replaceAll("  ", " ");
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
            PriorityQueue<StockInput> sell, PriorityQueue<StockInput> buy, StockType type, StockInput input) {
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
}
