package ru.otus.wishlist.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.ListCrudRepository;
import ru.otus.wishlist.domain.Gift;

public interface GiftRepository extends ListCrudRepository<Gift, Long> {

  List<Gift> findByWishlistId(Long wishlistId, Sort sort);
}
