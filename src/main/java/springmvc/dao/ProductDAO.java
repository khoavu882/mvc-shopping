package springmvc.dao;

import springmvc.entities.Product;
import springmvc.model.ProductInfo;
import springmvc.service.PaginationResult;

public interface ProductDAO {

  public Product findProductById(int productId);
  public Product findByProductName(String productName);
  public void saveProduct(Product product);
  public void deleteProduct(Product product);
  public void updateProduct(Product product);
  public PaginationResult<Product> findAllProduct(int page, int maxResult, int maxNavigationPage, String category,String order);

  public PaginationResult<Product> findAllProductByKeywords(int page, int maxResult, int maxNavigationPage, String keywords);
}