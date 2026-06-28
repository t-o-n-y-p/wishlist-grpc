package ru.otus.wishlist.transactional;

import java.util.function.Supplier;

public interface TransactionAction<T> extends Supplier<T> {}
