insert into users(id, name)
values
    (-1, 'test1'),
    (-2, 'test2');

insert into wishlists(id, user_id, title, description)
values
    (-1, -1, 'wishlist1', null),
    (-2, -1, 'wishlist2', null),
    (-3, -2, 'wishlist3', 'description3'),
    (-4, -2, 'wishlist4', null);

insert into gifts(id, wishlist_id, title, price, description, reserved)
values
    (-1, -3, 'gift1', 1, null, true),
    (-2, -3, 'gift2', 2, null, false),
    (-3, -4, 'gift3', 3, null, true),
    (-4, -4, 'gift4', 4, 'description4', false);
