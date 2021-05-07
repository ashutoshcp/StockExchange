# StockExchange
```
Input format: <order-id> <time> <stock> <buy/sell> <price> <qty>
```
```
Output format: <buy-order-id> <sell-price> <qty> <sell-order-id>
```
The exchange follows a FirstInFirstOut Price-Time order-matching rule, which states that: "The first order in the order-book at a price level is the first order matched. All orders at the same price level are filled according to time priority." The exchange works like a market where lower selling prices and higher buying prices get priority.

A trade is executed when a buy price is greater than or equal to a sell price. The trade is recorded at the price of the sell order regardless of the price of the buy order.
