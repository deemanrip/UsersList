package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("UsersDao")
public class UsersDaoImpl implements UsersDao {

    @Override
    public List<User> getAll() {
        List<User> users = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            users = query.list();
        }
        return users;
    }

    @Override
    public List<User> getByName(String name) {
        List<User> users = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE name=:paramName", User.class);
            query.setParameter("paramName", name);
            users = query.list();
        }
        return users;
    }

    @Override
    public User getById(int id) {
        User user = null;
        Query query;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            query = session.createQuery("FROM User WHERE id=:paramId");
            query.setParameter("paramId", id);
            user = (User) query.uniqueResult();
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteUser(int id) {
        User user = getById(id);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getUsersPerPage(int fromRow, int numberOfRows) {
        List<User> users = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            query.setFirstResult(fromRow);
            query.setMaxResults(numberOfRows);
            users = query.list();
        }
        return users;
    }
}