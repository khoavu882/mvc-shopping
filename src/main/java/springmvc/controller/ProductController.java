package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springmvc.dao.CategoryDAO;
import springmvc.dao.ProductDAO;
import springmvc.entities.Category;
import springmvc.entities.Product;
import springmvc.service.PaginationResult;
import springmvc.validator.ProductValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

  @Autowired
  ProductDAO productDAO;

  @Autowired
  CategoryDAO categoryDAO;

  @RequestMapping(value = "detail", method = RequestMethod.GET)
  public String gotoProductsPage(@RequestParam("productId") int productId,ModelMap model) {

    Product product = productDAO.findProductById(productId);

    model.addAttribute("product", product);
    model.addAttribute("title", "Product Page");
    return "product";
  }

  @RequestMapping(value = "list", method = RequestMethod.GET)
  public String gotoProductsListPage(ModelMap model,
                                     @RequestParam(value = "name", defaultValue = "") String likeName,
                                     @RequestParam(value = "page", defaultValue = "1") int page) {
    final int maxResult = 8;
    final int maxNavigationPage = 10;

    PaginationResult products = productDAO.findAllProduct(page, maxResult, maxNavigationPage, "", "");

    model.addAttribute("productsList", products);
    model.addAttribute("title", "List Product Page");
    return "products-list";
  }

  //////////////////////////////////////////////////////////////////////////////////////
  @RequestMapping(value = "createNew", method = RequestMethod.GET)
  public String registerPage(ModelMap model) {

    getDependencyForProductForm(model);
    model.addAttribute("title", "Product Additional Page");
    return "product-additional";
  }

  @RequestMapping(value = "save", method = RequestMethod.POST)
  public String registerProcessing(@ModelAttribute("productForm") Product product, ModelMap model,
                                   BindingResult result, ProductValidator productValidator) {

    productValidator.validate(product, result);

    if (result.hasErrors()) {
      model.addAttribute("title", "Product Additional Page");
      model.addAttribute("registerFail", "Cannot Additional! Please check information again.");
      getDependencyForProductForm(model);
      return "product-additional";
    }

    if (productDAO.findByProductName(product.getProductName()) == null){

        productDAO.saveProduct(product);
      return "redirect:list";
    }
    else {
      productDAO.updateProduct(product);

      model.addAttribute("title", "Product Additional Page");
      model.addAttribute("registerFail", "Cannot Additional! Please check information again.");
      return "redirect:list";
    }
  }

  @GetMapping("checkExistProduct")
  @ResponseBody
  public String checkExistProduct(HttpServletRequest request){
    String productName = request.getParameter("productName");
    Product product = productDAO.findByProductName(productName);

    if (product != null) { return "true"; }
    else { return "false"; }
  }

  @RequestMapping(value = "update", method = RequestMethod.GET)
  public String updateProduct(@RequestParam("productId") int productId, ModelMap model) {

    Product product = productDAO.findProductById(productId);
    List<Category> categories = categoryDAO.getAllCategory();
    model.addAttribute("productForm", product);
    model.addAttribute("categories", categories);
    model.addAttribute("title", "Product Edit Page");
    model.addAttribute("message", "Product Edit Fail! Add Again.");
      return "product-additional";
  }

  @RequestMapping(value = "delete-{productId}", method = RequestMethod.GET)
  public String deleteProductProcessing(@PathVariable("productId") int productId, ModelMap model) {

    Product product = productDAO.findProductById(productId);
    productDAO.deleteProduct(product);
    return "redirect:list";
  }


  public void getDependencyForProductForm(ModelMap model){
    List<Category> categories = categoryDAO.getAllCategory();
    model.addAttribute("categories", categories);
    model.addAttribute("productForm", new Product());
  }

}
