package springmvc.model;

import springmvc.entities.Product;

import java.util.Set;

public class OrderDetailInfo {
  private int id;
  private Set<Product> products;
  private int quantity;
  private float price;
  private float amount;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Set<Product> getProducts() {
    return products;
  }

  public void setProducts(Set<Product> products) {
    this.products = products;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public OrderDetailInfo(int id, Set<Product> products, int quantity, float price, float amount) {
    this.id = id;
    this.products = products;
    this.quantity = quantity;
    this.price = price;
    this.amount = amount;
  }
}