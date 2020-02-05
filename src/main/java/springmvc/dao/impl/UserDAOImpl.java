package springmvc.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import springmvc.dao.UserDAO;
import springmvc.entities.Product;
import springmvc.entities.Role;
import springmvc.entities.User;
import springmvc.service.PaginationResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
public class UserDAOImpl implements UserDAO {

  @Autowired
  SessionFactory sessionFactory;

  private Session session;

  @Override
  public User findByUserName(String userName) {
    session = sessionFactory.getCurrentSession();
    User user = (User) session.createQuery("from User where user_name=:x").setParameter("x", userName).uniqueResult();
    return user;
  }

  @Override
  public boolean saveUser(User user) {
    session = sessionFactory.getCurrentSession();
    if (findByUserName(user.getUserName()) != null) {
      return false;
    }
    Role rolesEntity = (Role) session.createQuery("from Role where role_name = 'USER' ").uniqueResult();
    Set<Role> s = new HashSet<Role>();
    s.add(rolesEntity);
    user.setRoles(s);
    session.save(user);
    return true;
  }

  @Override
  public List<User> findAllUser() {
    session = sessionFactory.getCurrentSession();
    List<User> users = session.createQuery("from User").getResultList();
    return users;
  }

  @Override
  public PaginationResult findAllUser(int page, int maxResult, int maxNavigationPage) {
    session = sessionFactory.getCurrentSession();
    return new PaginationResult(session.createQuery("from User"), page, maxResult, maxNavigationPage);
  }

} // END CLASS
