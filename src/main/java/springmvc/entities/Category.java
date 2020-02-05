package springmvc.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CATEGORY", schema = "store")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id", nullable = false, unique = true)
  private Integer categoryId;

  @Column(name = "category_name", nullable = false)
  private String categoryName;

  @Column(name = "category_keyword", nullable = false)
  private String categoryKeyword;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
  private Set<Product> products;

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryKeyword() {
    return categoryKeyword;
  }

  public void setCategoryKeyword(String categoryKeyword) {
    this.categoryKeyword = categoryKeyword;
  }

  public Set<Product> getProducts() {
    return products;
  }

  public void setProducts(Set<Product> products) {
    this.products = products;
  }

  public Category() {
  }
}
