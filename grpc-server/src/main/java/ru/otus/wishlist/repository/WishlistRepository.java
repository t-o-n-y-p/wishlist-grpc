package ru.otus.wishlist.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.ListCrudRepository;
import ru.otus.wishlist.domain.Wishlist;

public interface WishlistRepository extends ListCrudRepository<Wishlist, Long> {

  List<Wishlist> findByUserId(Long userId, Sort sort);
}
