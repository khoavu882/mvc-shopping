package springmvc.dao;

import springmvc.entities.Order;
import springmvc.entities.OrderDetail;
import springmvc.model.CartInfo;
import springmvc.model.OrderDetailInfo;
import springmvc.model.OrderInfo;
import springmvc.service.PaginationResult;

import java.util.List;

public interface OrderDAO {

  public Order getOrder(int orderId);
  public void saveOrder(CartInfo cartInfo);
  public PaginationResult<Order> listOrder(int page, int maxResult, int maxNavigationPage,  int userId);
  public PaginationResult<OrderDetail> listOrderDetail(int page, int maxResult, int maxNavigationPage, int orderId);
}
