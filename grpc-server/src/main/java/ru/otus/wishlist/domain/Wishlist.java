package ru.otus.wishlist.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("wishlists")
public record Wishlist(
    @Id @Column("id") Long id,
    @Column("user_id") AggregateReference<User, Long> userId,
    @Column("title") String title,
    @Column("description") String description) {}
