package springmvc.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCT", schema = "store")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", nullable = false, unique = true)
  private Integer productId;

  @Column(name = "product_name", nullable = false, unique = true)
  private String productName;

  @Column(name = "product_image", nullable = false)
  private String productImage;

  @Column(name = "product_description")
  private String productDescription;

  @Column(name = "product_quantity", nullable = false)
  private Integer productQuantity;

  @Column(name = "product_price", nullable = false)
  private Float productPrice;

  @Column(name = "product_promotion")
  private Float productPromotion;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
  private Set<OrderDetail> orderDetails;

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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Set<OrderDetail> getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(Set<OrderDetail> orderDetails) {
    this.orderDetails = orderDetails;
  }

  public Product() {
  }
}
