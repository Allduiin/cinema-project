package cinema.service.impl;

import cinema.dao.UserDao;
import cinema.model.User;
import cinema.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email).orElse(null);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }
}
