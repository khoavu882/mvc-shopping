package springmvc.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import springmvc.dao.CategoryDAO;
import springmvc.entities.Category;

import java.util.List;

@Transactional
public class CategoryDAOImpl implements CategoryDAO {

  @Autowired
  SessionFactory sessionFactory;

  private Session session;


  @Override
  public List<Category> getAllCategory() {
    session = sessionFactory.getCurrentSession();
    return (List<Category>) session.createQuery("from Category").list();
  }
}
