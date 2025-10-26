package Javacode;

import java.util.*;

//Class to represent a Stock
class Stock {
 private String symbol;
 private String name;
 private double price;

 public Stock(String symbol, String name, double price) {
     this.symbol = symbol;
     this.name = name;
     this.price = price;
 }

 public String getSymbol() { return symbol; }
 public String getName() { return name; }
 public double getPrice() { return price; }

 public void setPrice(double price) { this.price = price; }

 @Override
 public String toString() {
     return symbol + " (" + name + ") - $" + price;
 }
}

//Class to represent a user's holding
class PortfolioItem {
 private Stock stock;
 private int quantity;

 public PortfolioItem(Stock stock, int quantity) {
     this.stock = stock;
     this.quantity = quantity;
 }

 public Stock getStock() { return stock; }
 public int getQuantity() { return quantity; }

 public void addQuantity(int qty) { this.quantity += qty; }
 public void removeQuantity(int qty) { this.quantity -= qty; }

 public double getValue() {
     return stock.getPrice() * quantity;
 }

 @Override
 public String toString() {
     return stock.getSymbol() + " - " + quantity + " shares ($" + getValue() + ")";
 }
}

//Class to represent a User
class User {
 private String username;
 private double balance;
 private List<PortfolioItem> portfolio;

 public User(String username, double balance) {
     this.username = username;
     this.balance = balance;
     this.portfolio = new ArrayList<>();
 }

 public String getUsername() { return username; }
 public double getBalance() { return balance; }

 public void buyStock(Stock stock, int quantity) {
     double cost = stock.getPrice() * quantity;
     if (cost > balance) {
         System.out.println("❌ Insufficient funds to buy " + quantity + " shares of " + stock.getSymbol());
         return;
     }
     balance -= cost;
     boolean found = false;
     for (PortfolioItem item : portfolio) {
         if (item.getStock().getSymbol().equals(stock.getSymbol())) {
             item.addQuantity(quantity);
             found = true;
             break;
         }
     }
     if (!found) {
         portfolio.add(new PortfolioItem(stock, quantity));
     }
     System.out.println("✅ Bought " + quantity + " shares of " + stock.getSymbol());
 }

 public void sellStock(Stock stock, int quantity) {
     for (PortfolioItem item : portfolio) {
         if (item.getStock().getSymbol().equals(stock.getSymbol())) {
             if (item.getQuantity() < quantity) {
                 System.out.println("❌ Not enough shares to sell.");
                 return;
             }
             item.removeQuantity(quantity);
             balance += stock.getPrice() * quantity;
             System.out.println("✅ Sold " + quantity + " shares of " + stock.getSymbol());
             return;
         }
     }
     System.out.println("❌ You don't own any shares of " + stock.getSymbol());
 }

 public void viewPortfolio() {
     System.out.println("\n📊 Portfolio for " + username + ":");
     if (portfolio.isEmpty()) {
         System.out.println("No stocks owned.");
         return;
     }
     double totalValue = 0;
     for (PortfolioItem item : portfolio) {
         System.out.println(item);
         totalValue += item.getValue();
     }
     System.out.println("Total Portfolio Value: $" + totalValue);
     System.out.println("Available Balance: $" + balance);
 }
}

//Main class for Stock Trading Platform
public class StockTradingPlatform {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);

     // Create some stocks
     List<Stock> market = new ArrayList<>();
     market.add(new Stock("AAPL", "Apple Inc.", 180.25));
     market.add(new Stock("GOOGL", "Alphabet Inc.", 135.80));
     market.add(new Stock("TSLA", "Tesla Motors", 250.10));

     // Create user
     User user = new User("Rutuja", 10000);

     while (true) {
         System.out.println("\n===== STOCK TRADING PLATFORM =====");
         System.out.println("1. View Market Data");
         System.out.println("2. Buy Stock");
         System.out.println("3. Sell Stock");
         System.out.println("4. View Portfolio");
         System.out.println("5. Exit");
         System.out.print("Enter choice: ");
         int choice = sc.nextInt();

         if (choice == 1) {
             System.out.println("\n📈 Market Data:");
             for (Stock s : market)
                 System.out.println(s);
         } else if (choice == 2) {
             System.out.print("Enter stock symbol to buy: ");
             String symbol = sc.next().toUpperCase();
             System.out.print("Enter quantity: ");
             int qty = sc.nextInt();

             Stock selected = null;
             for (Stock s : market)
                 if (s.getSymbol().equals(symbol)) selected = s;

             if (selected != null) user.buyStock(selected, qty);
             else System.out.println("❌ Stock not found.");
         } else if (choice == 3) {
             System.out.print("Enter stock symbol to sell: ");
             String symbol = sc.next().toUpperCase();
             System.out.print("Enter quantity: ");
             int qty = sc.nextInt();

             Stock selected = null;
             for (Stock s : market)
                 if (s.getSymbol().equals(symbol)) selected = s;

             if (selected != null) user.sellStock(selected, qty);
             else System.out.println("❌ Stock not found.");
         } else if (choice == 4) {
             user.viewPortfolio();
         } else if (choice == 5) {
             System.out.println("👋 Exiting... Thank you for trading!");
             break;
         } else {
             System.out.println("Invalid choice!");
         }
     }

     sc.close();
 }
}
