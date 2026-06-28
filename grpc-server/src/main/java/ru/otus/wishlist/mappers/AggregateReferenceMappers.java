package ru.otus.wishlist.mappers;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

interface AggregateReferenceMappers<T, ID> {

  default AggregateReference<T, ID> map(ID source) {
    return source == null ? null : AggregateReference.to(source);
  }
}
