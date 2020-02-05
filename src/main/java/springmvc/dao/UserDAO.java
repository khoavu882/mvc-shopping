package springmvc.dao;

import springmvc.entities.User;
import springmvc.service.PaginationResult;

import java.util.List;

public interface UserDAO {
  public User findByUserName(String username);
  public boolean saveUser(User user);
  public List<User> findAllUser();
  public PaginationResult<User> findAllUser(int page, int maxResult, int maxNavigationPage);
}
