package springmvc.model;

import springmvc.entities.User;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {

  private String description = "";
  private User user;

  private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<CartLineInfo> getCartLines() {
    return cartLines;
  }

  private CartLineInfo findLineById(int code) {
    for (CartLineInfo line : this.cartLines) {
      if (line.getProductInfo().getProductId().equals(code)) {
        return line;
      }
    }
    return null;
  }

    public void addProduct(ProductInfo productInfo, int quantity) {
    CartLineInfo line = this.findLineById(productInfo.getProductId());

    if (line == null) {
      line = new CartLineInfo();
      line.setQuantity(0);
      line.setProductInfo(productInfo);
      this.cartLines.add(line);
    }
    int newQuantity = line.getQuantity() + quantity;
    if (newQuantity <= 0) {
      this.cartLines.remove(line);
    } else {
      line.setQuantity(newQuantity);
    }
  }

  public void validate() {

  }

  public void updateProduct(int code, int quantity) {
    CartLineInfo line = this.findLineById(code);

    if (line != null) {
      if (quantity <= 0) {
        this.cartLines.remove(line);
      } else {
        line.setQuantity(quantity);
      }
    }
  }

  public void removeProduct(ProductInfo productInfo) {
    CartLineInfo line = this.findLineById(productInfo.getProductId());
    if (line != null) {
      this.cartLines.remove(line);
    }
  }

  public boolean isEmpty() {
    return this.cartLines.isEmpty();
  }

  public int getQuantityTotal() {
    int quantity = 0;
    for (CartLineInfo line : this.cartLines) {
      quantity += line.getQuantity();
    }
    return quantity;
  }

  public float getAmountTotal() {
    float total = 0;
    for (CartLineInfo line : this.cartLines) {
      total += line.getAmount();
    }
    return total;
  }

  public void updateQuantity(CartInfo cartForm) {
    if (cartForm != null) {
      List<CartLineInfo> lines = cartForm.getCartLines();
      for (CartLineInfo line : lines) {
        this.updateProduct(line.getProductInfo().getProductId(), line.getQuantity());
      }
    }
  }

  public CartInfo() {
  }

  public CartInfo(String description, User user) {
    this.description = description;
    this.user = user;
  }
}