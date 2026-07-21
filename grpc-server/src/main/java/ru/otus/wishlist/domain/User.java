package ru.otus.wishlist.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record User(@Id @Column("id") Long id, @Column("name") String name) {}
