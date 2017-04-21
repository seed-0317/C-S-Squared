package dao;

import model.User;
import java.util.List;

/**
 * Created by nof191 on 4/17/17.
 */
public interface UsersDAO {

    // Interface to get users

    List<User> getAllUser();

    // Used to update user data

    void saveUser(User userToSave) throws Exception;

    User getUser (String username);


}
