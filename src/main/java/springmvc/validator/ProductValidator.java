package springmvc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import springmvc.dao.ProductDAO;
import springmvc.entities.Product;

@Component
public class ProductValidator implements Validator {

  @Autowired
  private ProductDAO productDAO;

  @Override
  public boolean supports(Class<?> aClass) {
    return aClass.equals(Product.class);
  }

  @Override
  public void validate(Object o, Errors errors) {

    Product product = (Product) o;

    // Check Product
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName","NotEmpty.productForm.productName");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productImage","NotEmpty.productForm.productImage");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productPrice","NotEmpty.productForm.productPrice");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productQuantity","NotEmpty.productForm.productQuantity");
  }
}
