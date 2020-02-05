package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springmvc.dao.CategoryDAO;
import springmvc.dao.OrderDAO;
import springmvc.dao.ProductDAO;
import springmvc.dao.UserDAO;
import springmvc.entities.Product;
import springmvc.entities.User;
import springmvc.instance.MyConstants;
import springmvc.model.CartInfo;
import springmvc.model.ProductInfo;
import springmvc.service.PaginationResult;
import springmvc.util.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequestMapping("cart")
public class CartController {

  @Autowired
  OrderDAO orderDAO;

  @Autowired
  UserDAO userDAO;

  @Autowired
  ProductDAO productDAO;

  @Autowired
  CategoryDAO categoryDAO;

  @Autowired
  JavaMailSender mailSender;

  public void sendMailToCustomer(String mailAddress) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(MyConstants.MY_EMAIL);
    message.setTo(mailAddress);
    message.setSubject("K Tech");
    message.setText("Thank you for Shopping at K Tech! , Your order will come in 1 day , please keep phone to get amazing product !");
    mailSender.send(message);
  }

  @RequestMapping(value = { "info" }, method = RequestMethod.GET)
  public String shoppingCartHandler(HttpServletRequest request, ModelMap model) {
    CartInfo myCart = Utils.getCartInSession(request);
    model.addAttribute("cartForm", myCart);
    model.addAttribute("title", "Cart Info! - K Tech");
    return "carts";
  }

  @RequestMapping(value = "add", method = RequestMethod.GET)
  public String addToCart(HttpServletRequest http, ModelMap model,
                          @RequestParam(value = "productId", defaultValue = "") int productId) {

    Product product = productDAO.findProductById(productId);

    CartInfo cartInfo = Utils.getCartInSession(http);

    ProductInfo productInfo = new ProductInfo(product);

    cartInfo.addProduct(productInfo, 1);

    model.addAttribute("cartForm", cartInfo);
    return "redirect:/cart/info";
  }

  @InitBinder
  public void myInitBinder(WebDataBinder dataBinder) {
    Object target = dataBinder.getTarget();
    if (target == null) {
      return;
    }
    System.out.println("Target=" + target);
  }

  @RequestMapping({ "update" })
  public String updateQuantityHandel(HttpServletRequest request, ModelMap model,
                                     @RequestParam(value = "productId", defaultValue = "") String productId,
                                     @RequestParam(value = "quantity", defaultValue = "") String quantity) {

      CartInfo cartInfo = Utils.getCartInSession(request);
      cartInfo.updateProduct(Integer.parseInt(productId), Integer.parseInt(quantity));

    return "redirect:/cart/info";
  }

  @GetMapping("checkOutOfStock")
  @ResponseBody
  public String checkOutOfStock(@RequestParam("productId") String productId, @RequestParam("quantity") String quantity) {

    Product product = productDAO.findProductById(Integer.parseInt(productId));
    System.err.println(product.getProductName());
    if (product.getProductQuantity() <= Integer.parseInt(quantity) ) {
      return "out";
    } else if (Integer.parseInt(quantity) < 1 | quantity.equals("")) {
      return "value";
    } else { return "true";}

  }

  @RequestMapping({ "remove" })
  public String removeProductHandler(HttpServletRequest request, ModelMap model,
                                     @RequestParam(value = "productId", defaultValue = "") String productId) {
    Product product = null;
    if (productId != null && productId.length() > 0) {
      product = productDAO.findProductById(Integer.parseInt(productId));
    }
    if (product != null) {
      CartInfo cartInfo = Utils.getCartInSession(request);

      ProductInfo productInfo = new ProductInfo(product);

      cartInfo.removeProduct(productInfo);
    }
    return "redirect:/cart/info";
  }

  @RequestMapping(value = { "accept" }, method = RequestMethod.GET)
  public String goToCheckOutPage(HttpServletRequest request, Principal principal,ModelMap model){

    User user = userDAO.findByUserName(principal.getName());
    CartInfo cartInfo = Utils.getCartInSession(request);
    cartInfo.setUser(user);
    orderDAO.saveOrder(cartInfo);

    sendMailToCustomer(user.getEmail());

    model.addAttribute("user", user);
    model.addAttribute("message", "Thanks for Shopping");
    model.addAttribute("title", "Check out Success! - K Tech");
    return "check-out";
  }

  @RequestMapping(value = "manager", method = RequestMethod.GET)
  public String goToOrderPage(@RequestParam(value = "page", defaultValue = "1") int page, Principal principal, ModelMap model) {

    final int maxResult = 8;
    final int maxNavigationPage = 10;

    User user = userDAO.findByUserName(principal.getName());

    PaginationResult orders = orderDAO.listOrder(page, maxResult, maxNavigationPage, user.getUserId());

    model.addAttribute("orders", orders);
    model.addAttribute("title", "Your Orders");
    return "order";
  }

  @RequestMapping(value = "detail", method = RequestMethod.GET)
  public String goToOrderDetailPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "orderID") int orderID, ModelMap model) {

    final int maxResult = 8;
    final int maxNavigationPage = 10;

//    System.err.println("----------------------------------------------------   "+orderId+"   ------------------------------------------------------");

    PaginationResult orderDetails = orderDAO.listOrderDetail(page, maxResult, maxNavigationPage, orderID);

    model.addAttribute("orderDetails", orderDetails);
    model.addAttribute("title", "Detail Your Order! - K Tech");

    return "order-detail";
  }

}