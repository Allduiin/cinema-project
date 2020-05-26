package cinema.service.impl;

import java.util.Optional;
import cinema.dao.UserDao;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.User;
import cinema.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    UserDao userDao;
    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
