package ru.otus.wishlist.database.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.otus.wishlist.database.UserDatabaseService;
import ru.otus.wishlist.domain.User;
import ru.otus.wishlist.repository.UserRepository;
import ru.otus.wishlist.transactional.TransactionManager;

@Service
@AllArgsConstructor
public class UserDatabaseServiceImpl implements UserDatabaseService {

  private final UserRepository userRepository;
  private final TransactionManager transactionManager;

  @Override
  public List<User> findAll() {
    return userRepository.findAll(Sort.by("id"));
  }

  @Override
  public User saveUser(User value) {
    return transactionManager.doInTransaction(() -> userRepository.save(value));
  }

  @Override
  public Optional<User> findByName(String name) {
    return userRepository.findByName(name);
  }
}
