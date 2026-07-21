create table users
(
    id          bigserial   not null primary key,
    name        varchar(20) not null unique
);
create index idx_users_name on users(name);

create table wishlists
(
    id          bigserial   not null primary key,
    user_id     bigint      not null references users(id),
    title       varchar(50) not null,
    description varchar(500)
);
create index idx_wishlists_user_id on wishlists(user_id);

create table gifts
(
    id          bigserial   not null primary key,
    wishlist_id bigint      not null references wishlists(id),
    title       varchar(50) not null,
    price       bigint      not null,
    description varchar(500),
    reserved    boolean     default false
);
create index idx_gifts_wishlist_id on gifts(wishlist_id);
