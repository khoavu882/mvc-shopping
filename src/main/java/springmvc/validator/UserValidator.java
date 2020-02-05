package springmvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import springmvc.entities.User;

@Component
public class UserValidator implements Validator {
  @Override
  public boolean supports(Class<?> aClass) {
    return aClass.equals(User.class);
  }

  @Override
  public void validate(Object o, Errors errors) {

    User user = (User) o;

    // Check User
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName","NotEmpty.userForm.userName");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","NotEmpty.userForm.password");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","NotEmpty.userForm.email");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","NotEmpty.userForm.firstName");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName","NotEmpty.userForm.lastName");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address","NotEmpty.userForm.address");
  }
}
