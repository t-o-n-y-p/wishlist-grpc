package ru.otus.wishlist.database;

import java.util.List;
import java.util.Optional;
import ru.otus.wishlist.domain.User;

public interface UserDatabaseService {

  List<User> findAll();

  User saveUser(User value);

  Optional<User> findByName(String name);
}
