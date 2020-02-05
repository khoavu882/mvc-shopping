package springmvc.model;

import springmvc.entities.Product;

public class ProductInfo {

  private Integer productId;
  private String productName;
  private String productImage;
  private String productDescription;
  private Integer productQuantity;
  private Float productPrice;
  private Float productPromotion;
  private Integer categoryId;

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public Integer getProductQuantity() {
    return productQuantity;
  }

  public void setProductQuantity(Integer productQuantity) {
    this.productQuantity = productQuantity;
  }

  public Float getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(Float productPrice) {
    this.productPrice = productPrice;
  }

  public Float getProductPromotion() {
    return productPromotion;
  }

  public void setProductPromotion(Float productPromotion) {
    this.productPromotion = productPromotion;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public ProductInfo(Product product) {
    this.productId = product.getProductId();
    this.productName = product.getProductName();
    this.productImage = product.getProductImage();
    this.productDescription = product.getProductDescription();
    this.productQuantity = product.getProductQuantity();
    this.productPrice = product.getProductPrice();
    this.productPromotion = product.getProductPromotion();
    this.categoryId = product.getCategory().getCategoryId();
  }
}
