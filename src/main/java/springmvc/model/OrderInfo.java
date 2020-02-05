package springmvc.model;

import java.util.List;

public class OrderInfo {

  private int orderId;
  private int orderNum;
  private int userID;
  private List<OrderDetailInfo> details;

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(int orderNum) {
    this.orderNum = orderNum;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public List<OrderDetailInfo> getDetails() {
    return details;
  }

  public void setDetails(List<OrderDetailInfo> details) {
    this.details = details;
  }


}