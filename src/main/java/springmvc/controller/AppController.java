package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springmvc.dao.ProductDAO;
import springmvc.dao.UserDAO;
import springmvc.entities.User;
import springmvc.service.PaginationResult;
import springmvc.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class AppController {

  @Autowired
  UserDAO userDAO;

  @Autowired
  ProductDAO productDAO;

  @RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
  public String sortProduct(@RequestParam(value = "order", defaultValue = "") String order,
                            @RequestParam(value = "category", defaultValue = "") String category,
                            @RequestParam(value = "name", defaultValue = "") String likeName,
                            @RequestParam(value = "page", defaultValue = "1") int page, ModelMap model) {

    final int maxResult = 8;
    final int maxNavigationPage = 10;

    PaginationResult products = productDAO.findAllProduct(page, maxResult, maxNavigationPage, category, order);

    model.addAttribute("productsList", products);
    model.addAttribute("order", order);
    model.addAttribute("category", category);
    model.addAttribute("title", "Home");
    return "index";
  }

  @RequestMapping(value = "search", method = RequestMethod.POST)
  public String goToSearchPage(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "keywords", defaultValue = "") String keywords, ModelMap model) {

    final int maxResult = 8;
    final int maxNavigationPage = 10;

    PaginationResult products = productDAO.findAllProductByKeywords(page, maxResult, maxNavigationPage, keywords);

    model.addAttribute("keywords", keywords);
    model.addAttribute("productsList", products);
    model.addAttribute("title", ""+keywords+" - K Tech");

    return "search";
  }

  @RequestMapping(value = "login", method = RequestMethod.GET)
  public String loginPage(ModelMap model) {
    model.addAttribute("title", "Login Page");
    return "login";
  }

  @RequestMapping(value = "register", method = RequestMethod.GET)
  public String registerPage(ModelMap model) {
    model.addAttribute("title", "Sign In Page");
    model.addAttribute("userForm", new User());
    return "register";
  }

  @RequestMapping(value = "register", method = RequestMethod.POST)
  public String registerProcessing(@ModelAttribute("userForm") User user, ModelMap model,
                                   BindingResult result, UserValidator userValidator) {

    userValidator.validate(user, result);

    if (result.hasErrors()) {
      model.addAttribute("title", "Sign In Page");
      model.addAttribute("registerFail", "Cannot Register! Please check information again.");
      return "register";
    }

    if (userDAO.saveUser(user)) {
      return "redirect:home";
    }

    model.addAttribute("title", "Sign In Page");
    model.addAttribute("registerFail", "Cannot Register! Please check information again.");
    return "register";
  }

  @GetMapping("checkExistUser")
  @ResponseBody
  public String checkExistUser(HttpServletRequest request) {
    String userName = request.getParameter("userName");
    User user = userDAO.findByUserName(userName);

    if (user != null) {
      return "true";
    } else {
      return "false";
    }
  }

  @RequestMapping(value = "edit", method = RequestMethod.GET)
  public String goToEditUser(@RequestParam("username") String username, ModelMap model) {

    User user = userDAO.findByUserName(username);

    model.addAttribute("user", user);
    model.addAttribute("message", "Edit User Fails Change Again!");

    return "";
  }

  @RequestMapping(value = "manager", method = RequestMethod.GET)
  public String gotoAdminPage(ModelMap model,
                              @RequestParam(value = "name", defaultValue = "") String likeName,
                              @RequestParam(value = "page", defaultValue = "1") int page) {

    final int maxResult = 8;
    final int maxNavigationPage = 10;

    PaginationResult users = userDAO.findAllUser(page, maxResult, maxNavigationPage);

    model.addAttribute("usersList", users);
    model.addAttribute("title", "Admin Page");
    return "manager";
  }

  @RequestMapping("404")
  public String goto404Page(ModelMap model) {
    model.addAttribute("title", "Error 404!");
    return "404";
  }

  @RequestMapping(value = "contact", method = RequestMethod.GET)
  public String contactPage(ModelMap model) {
    model.addAttribute("title", "Contact Page");
    return "contact";
  }

  @RequestMapping(value = "about", method = RequestMethod.GET)
  public String aboutPage(ModelMap model) {
    model.addAttribute("title", "About Page");
    return "about";
  }
}
