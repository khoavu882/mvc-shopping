package springmvc.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import springmvc.dao.OrderDAO;
import springmvc.dao.ProductDAO;
import springmvc.dao.UserDAO;
import springmvc.entities.Order;
import springmvc.entities.OrderDetail;
import springmvc.entities.Product;
import springmvc.model.CartInfo;
import springmvc.model.CartLineInfo;
import springmvc.service.PaginationResult;
import java.util.List;

@Transactional
public class OrderDAOImpl implements OrderDAO {

  @Autowired
  SessionFactory sessionFactory;

  @Autowired
  ProductDAO productDAO;

  @Autowired
  UserDAO userDAO;

  private Session session;

  @Override
  public Order getOrder(int orderId) {
    session = sessionFactory.getCurrentSession();

    return (Order) session.createQuery("from Order where order_id =:x").setParameter("x",orderId).uniqueResult();
  }

  @Override
  public void saveOrder(CartInfo cartInfo) {

    session = sessionFactory.getCurrentSession();

    Order order = new Order();
    order.setOrderDescription(cartInfo.getDescription());
    order.setUser(cartInfo.getUser());
    session.save(order);

    List<CartLineInfo> cartLineInfos = cartInfo.getCartLines();
    for (CartLineInfo line: cartLineInfos) {
      OrderDetail orderDetail = new OrderDetail();
      Product product = productDAO.findProductById(line.getProductInfo().getProductId());
      orderDetail.setOrder(order);
      orderDetail.setProduct(product);
      orderDetail.setQuantity(line.getQuantity());
      session.save(orderDetail);
    }
  }

  @Override
  public PaginationResult listOrder(int page, int maxResult, int maxNavigationPage, int userId) {

    session = sessionFactory.getCurrentSession();
    return new PaginationResult(session.createQuery("from Order where user_id =:x").setParameter("x",userId), page, maxResult, maxNavigationPage);
  }

  @Override
  public PaginationResult listOrderDetail(int page, int maxResult, int maxNavigationPage, int orderId) {

    session = sessionFactory.getCurrentSession();
    return new PaginationResult(session.createQuery("from OrderDetail where order_id =:x").setParameter("x", orderId), page, maxResult, maxNavigationPage);
  }
}