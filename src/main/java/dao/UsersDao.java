package dao;

import model.User;

import java.util.List;

public interface UsersDao {
    List<User> getAll();
    List<User> getByName(String name);
    User getById(int id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> getUsersPerPage(int fromRow, int numberOfRows);
}
