package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      String hql = "FROM User u";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
      EntityGraph<User> entityGraph = sessionFactory.getCurrentSession().createEntityGraph(User.class);
      entityGraph.addAttributeNodes("car");
      query.setHint("javax.persistence.fetchgraph", entityGraph);
      return query.getResultList();
   }


   @Override
   @SuppressWarnings("unchecked")
   public User getUsersByCarModelAndSeries(String model, int series) {
      String hql = "FROM User user JOIN FETCH user.car car WHERE car.model = :model AND car.series = :series";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.setMaxResults(1).getSingleResult();
   }
}
