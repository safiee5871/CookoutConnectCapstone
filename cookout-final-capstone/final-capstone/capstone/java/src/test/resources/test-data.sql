BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER', 'emailaddress');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER', 'email2');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER', 'emailtest3');

INSERT INTO cookout (cookout_name, date, time, location) VALUES ('Birthday Cookout', '2023-06-02', '12:00:00', 'Columbus');
INSERT INTO cookout (cookout_name, date, time, location) VALUES ('Housewarming', '2023-07-22', '12:00:00', 'New House');
INSERT INTO cookout (cookout_name, date, time, location) VALUES ('Labor Day', '2023-09-03', '14:00:00', 'The Park');

INSERT INTO menu (menu_item_id, cookout_id, item_name, item_type, allergens) VALUES (1, 'potato salad', 'sidedish', 'egg');
INSERT INTO menu (menu_item_id, cookout_id, item_name, item_type, allergens) VALUES (2, 'veg kabobs', 'sidedish', 'none');
INSERT INTO menu (menu_item_id, cookout_id, item_name, item_type, allergens) VALUES (3,  'hot dogs', 'entree', 'soy');

INSERT INTO user_cookout (user_id, cookout_id, role, rsvp) VALUES (1, 1, 'Host', true);
INSERT INTO user_cookout (user_id, cookout_id, role, rsvp) VALUES (2, 1, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, role, rsvp) VALUES (3, 2, 'Host', true);
INSERT INTO user_cookout (user_id, cookout_id, role, rsvp) VALUES (3, 3, 'Chef', true);

INSERT INTO user_order (user_id, menu_item_id) VALUES (1, 1);
INSERT INTO user_order (user_id, menu_item_id) VALUES (2, 3);
INSERT INTO user_order (user_id, menu_item_id) VALUES (3, 2);

INSERT INTO cookout(cookout_name, date, time, location)
VALUES ('Birthday Cookout',, '2023-06-02', '12:00:00', 'Columbus');

INSERT INTO cookout(cookout_name, date, time, location)
VALUES ('Family BBQ', '2023-07-10', '11:00:00', 'Als house');

INSERT INTO cookout(cookout_name, date, time, location)
VALUES ('Family Reunion Cookout', '2023-10-09', '14:00:00', 'The park');

COMMIT TRANSACTION;
