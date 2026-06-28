package ru.otus.wishlist.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("gifts")
public record Gift(
    @Id @Column("id") Long id,
    @Column("wishlist_id") AggregateReference<Wishlist, Long> wishlistId,
    @Column("title") String title,
    @Column("price") Long price,
    @Column("description") String description,
    @Column("reserved") Boolean reserved) {}
