package springmvc.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import springmvc.dao.ProductDAO;
import springmvc.entities.Product;
import springmvc.service.PaginationResult;

@Transactional
public class ProductDAOImpl implements ProductDAO {

  @Autowired
  SessionFactory sessionFactory;

  private Session session;

  @Override
  public Product findProductById(int productId) {

    session = sessionFactory.getCurrentSession();
    return (Product) session.createQuery("from Product where product_id=:x").setParameter("x", productId).uniqueResult();
  }

  @Override
  public Product findByProductName(String productName) {

    session = sessionFactory.getCurrentSession();
    return (Product) session.createQuery("from Product where product_name=:x").setParameter("x", productName).uniqueResult();
  }

  @Override
  public void saveProduct(Product product) {

    session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(product);
  }

  @Override
  public void updateProduct(Product product) {
    session = sessionFactory.getCurrentSession();

    session.update(product);
  }

  @Override
  public void deleteProduct(Product product) {

    session = sessionFactory.getCurrentSession();
    Product result = findByProductName(product.getProductName());
    session.delete(result);
  }

  @Override
  public PaginationResult findAllProduct(int page, int maxResult, int maxNavigationPage, String category, String order) {

    session = sessionFactory.getCurrentSession();

    if (category.equals("")) {
      if (order.equals("lowToHigh")) {

        return new PaginationResult(session.createQuery("from Product order by product_price , product_name"), page, maxResult, maxNavigationPage);
      } else if (order.equals("highToLow")) {

        return new PaginationResult(session.createQuery("from Product order by product_price desc, product_name"), page, maxResult, maxNavigationPage);
      } else if (order.equals("aToZ")) {

        return new PaginationResult(session.createQuery("from Product order by product_name"), page, maxResult, maxNavigationPage);
      } else {

        return new PaginationResult(session.createQuery("from Product"), page, maxResult, maxNavigationPage);
      }
    } else {
      if (order.equals("lowToHigh")) {

        return new PaginationResult(session.createQuery("from Product where category_id = '"+category+"' order by product_price , product_name"), page, maxResult, maxNavigationPage);
      } else if (order.equals("highToLow")) {

        return new PaginationResult(session.createQuery("from Product where category_id = '"+category+"' order by product_price desc, product_name"), page, maxResult, maxNavigationPage);
      } else if (order.equals("aToZ")) {

        return new PaginationResult(session.createQuery("from Product where category_id = '"+category+"' order by product_name"), page, maxResult, maxNavigationPage);
      }  else {

        return new PaginationResult(session.createQuery("from Product where category_id = '"+category+"'"), page, maxResult, maxNavigationPage);
      }
    }
  }

  @Override
  public PaginationResult<Product> findAllProductByKeywords(int page, int maxResult, int maxNavigationPage, String keywords) {
    session = sessionFactory.getCurrentSession();

    return new PaginationResult(session.createQuery("from Product where product_name like:x").setParameter("x", "%"+keywords+"%"), page, maxResult, maxNavigationPage);
  }
}