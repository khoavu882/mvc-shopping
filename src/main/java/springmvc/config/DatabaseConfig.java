package springmvc.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springmvc.dao.CategoryDAO;
import springmvc.dao.OrderDAO;
import springmvc.dao.ProductDAO;
import springmvc.dao.UserDAO;
import springmvc.dao.impl.CategoryDAOImpl;
import springmvc.dao.impl.OrderDAOImpl;
import springmvc.dao.impl.ProductDAOImpl;
import springmvc.dao.impl.UserDAOImpl;
import springmvc.entities.Product;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@ComponentScan("springmvc.*" )
@EnableTransactionManagement
// Load to Environment.
@PropertySource("classpath:datasource-cfg.properties")
public class DatabaseConfig {

  @Autowired
  private Environment env;

  @Bean(name = "dataSource")
  public DataSource getDataSource() {
    BasicDataSource dataSource = new BasicDataSource();

    // Xem: datasource-cfg.properties
    dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
    dataSource.setUrl(env.getProperty("ds.url"));
    dataSource.setUsername(env.getProperty("ds.username"));
    dataSource.setPassword(env.getProperty("ds.password"));

    System.out.println("## getDataSource: " + dataSource);
    return dataSource;
  }

  //    @Autowired
  @Bean(name = "sessionFactory")
  public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
    System.out.println("## getSessionFactory .... ");
    try {
      Properties properties = new Properties();
      // Xem: ds-hibernate-cfg.properties
      properties.put("hibernate.dialect", Objects.requireNonNull(env.getProperty("hibernate.dialect")));
      properties.put("hibernate.show_sql", Objects.requireNonNull(env.getProperty("hibernate.show_sql")));
      properties.put("hibernate.hbm2ddl.auto", Objects.requireNonNull(env.getProperty("hibernate.hbm2ddl.auto")));
      properties.put("current_session_context_class", Objects.requireNonNull(env.getProperty("current_session_context_class")));

      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
      // Package contain entity classes
      // Package chứa các entity class.
      factoryBean.setPackagesToScan("springmvc.*");
      factoryBean.setDataSource(dataSource);
      factoryBean.setHibernateProperties(properties);
      factoryBean.afterPropertiesSet();
      SessionFactory sf = factoryBean.getObject();
      System.out.println("## getSessionFactory: " + sf);
      return sf;
    } catch (Exception e) {
      System.out.println("Error getSessionFactory: " + e);
      e.printStackTrace();
      throw e;
    }
  }

  // Hibernate Transaction Manager
  @Autowired
  @Bean(name = "transactionManager")
  public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {

    return new HibernateTransactionManager(sessionFactory);
  }

  @Bean
  public OrderDAO getOrderDao() {
    return new OrderDAOImpl();
  }

  @Bean
  public UserDAO getUserDao(){
    return new UserDAOImpl();
  }

  @Bean
  public ProductDAO getProductDao() { return new ProductDAOImpl(); }

  @Bean
  public CategoryDAO getCategoryDao() { return new CategoryDAOImpl(); }
}