package ru.otus.wishlist.database.impl;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.otus.wishlist.database.GiftDatabaseService;
import ru.otus.wishlist.domain.Gift;
import ru.otus.wishlist.repository.GiftRepository;
import ru.otus.wishlist.transactional.TransactionManager;

@Service
@AllArgsConstructor
public class GiftDatabaseServiceImpl implements GiftDatabaseService {

  private final GiftRepository giftRepository;
  private final TransactionManager transactionManager;

  @Override
  public List<Gift> findByWishlistId(Long wishlistId) {
    return giftRepository.findByWishlistId(wishlistId, Sort.by("id"));
  }

  @Override
  public Gift saveGift(Gift value) {
    return transactionManager.doInTransaction(() -> giftRepository.save(value));
  }

  @Override
  public Optional<Gift> findById(Long id) {
    return giftRepository.findById(id);
  }

  @Override
  public void deleteGift(Long id) {
    transactionManager.doInTransaction(() -> giftRepository.deleteById(id));
  }
}
