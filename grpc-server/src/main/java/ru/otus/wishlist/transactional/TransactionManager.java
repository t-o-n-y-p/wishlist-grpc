package ru.otus.wishlist.transactional;

public interface TransactionManager {

  <T> T doInTransaction(TransactionAction<T> action);

  void doInTransaction(Runnable action);
}
