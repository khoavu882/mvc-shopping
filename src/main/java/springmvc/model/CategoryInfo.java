package springmvc.model;

import springmvc.entities.Category;

public class CategoryInfo {

  private Integer categoryId;
  private String categoryName;
  private String categoryKeyword;

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

  public CategoryInfo(Category category) {
    this.categoryId = category.getCategoryId();
    this.categoryName = category.getCategoryName();
    this.categoryKeyword = category.getCategoryKeyword();
  }
}
