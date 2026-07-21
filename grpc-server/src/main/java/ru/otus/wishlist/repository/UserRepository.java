package ru.otus.wishlist.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.ListCrudRepository;
import ru.otus.wishlist.domain.User;

public interface UserRepository extends ListCrudRepository<User, Long> {

  Optional<User> findByName(String name);

  List<User> findAll(Sort sort);
}
