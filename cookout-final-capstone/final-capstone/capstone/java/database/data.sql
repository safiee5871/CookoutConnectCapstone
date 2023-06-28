BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,email_address) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER', 'user1@email.com');
INSERT INTO users (username,password_hash,role,email_address) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN', 'user2@email.com');
INSERT INTO users (username, password_hash, role, email_address) VALUES ('Al', '$2a$10$HCyXjv3r51lsK0uWXCCui.D0npfVb3BIy7U9YiYZRBQGdyvz3d6Fe', 'ROLE_USER', 'al@email.com');
INSERT INTO users (username, password_hash, role, email_address) VALUES ('Xavier', '$2a$10$HCyXjv3r51lsK0uWXCCui.D0npfVb3BIy7U9YiYZRBQGdyvz3d6Fe', 'ROLE_USER', 'xavier@email.com');
INSERT INTO users (username, password_hash, role, email_address) VALUES ('Michelle', '$2a$10$HCyXjv3r51lsK0uWXCCui.D0npfVb3BIy7U9YiYZRBQGdyvz3d6Fe', 'ROLE_ADMIN', 'michelle@email.com');
INSERT INTO users (username, password_hash, role, email_address) VALUES ('Chrysti', '$2a$10$HCyXjv3r51lsK0uWXCCui.D0npfVb3BIy7U9YiYZRBQGdyvz3d6Fe', 'ROLE_ADMIN', 'chrysti@email.com');
INSERT INTO users (username,password_hash,role,email_address) VALUES ('Eric','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER', 'eric@email.com');
INSERT INTO users (username,password_hash,role,email_address) VALUES ('Michael','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN', 'michael@email.com');
INSERT INTO users (username, password_hash, role, email_address) VALUES ('Lance', '$2a$10$HCyXjv3r51lsK0uWXCCui.D0npfVb3BIy7U9YiYZRBQGdyvz3d6Fe', 'ROLE_USER', 'lance@email.com');
INSERT INTO users (username, password_hash, role, email_address) VALUES ('Holly', '$2a$10$HCyXjv3r51lsK0uWXCCui.D0npfVb3BIy7U9YiYZRBQGdyvz3d6Fe', 'ROLE_USER', 'holly@email.com');
INSERT INTO users (username, password_hash, role, email_address) VALUES ('Skitty', '$2a$10$HCyXjv3r51lsK0uWXCCui.D0npfVb3BIy7U9YiYZRBQGdyvz3d6Fe', 'ROLE_ADMIN', 'skitty@email.com');
INSERT INTO users (username, password_hash, role, email_address) VALUES ('Tego', '$2a$10$HCyXjv3r51lsK0uWXCCui.D0npfVb3BIy7U9YiYZRBQGdyvz3d6Fe', 'ROLE_ADMIN', 'tego@email.com');

INSERT INTO cookout(cookout_name, date, time, location) VALUES ('Birthday Cookout', '2023-06-02', '12:00:00', 'Philly');
INSERT INTO cookout(cookout_name, date, time, location) VALUES ('Family BBQ', '2023-07-10', '11:00:00', 'Al"s house');
INSERT INTO cookout(cookout_name, date, time, location) VALUES ('Family Reunion Cookout', '2023-10-09', '14:00:00', 'The park');
INSERT INTO cookout (cookout_name, date, time, location) VALUES ('Birthday Cookout', '2023-06-02', '12:00:00', 'Columbus');
INSERT INTO cookout (cookout_name, date, time, location) VALUES ('Housewarming', '2023-07-22', '12:00:00', 'New House');
INSERT INTO cookout (cookout_name, date, time, location) VALUES ('Labor Day', '2023-09-03', '14:00:00', 'The Park');
INSERT INTO cookout(cookout_name, date, time, location) VALUES ('Graduation', '2023-06-02', '12:00:00', 'Columbus');
INSERT INTO cookout(cookout_name, date, time, location) VALUES ('Birthday BBQ', '2023-07-10', '11:00:00', 'Al"s house');
INSERT INTO cookout(cookout_name, date, time, location) VALUES ('Baby Shower', '2023-10-09', '14:00:00', 'The park');
INSERT INTO cookout (cookout_name, date, time, location) VALUES ('Birthday BBQ', '2023-06-02', '12:00:00', 'Columbus');
INSERT INTO cookout (cookout_name, date, time, location) VALUES ('New Cat', '2023-07-22', '12:00:00', 'New House');
INSERT INTO cookout (cookout_name, date, time, location) VALUES ('Memorial Day', '2023-09-03', '14:00:00', 'The Park');

INSERT INTO menu (cookout_id, item_name, item_type, allergens) VALUES (1, 'potato salad', 'sidedish', 'egg');
INSERT INTO menu (cookout_id, item_name, item_type, allergens) VALUES (1, 'veg kabobs', 'sidedish', 'none');
INSERT INTO menu (cookout_id, item_name, item_type, allergens) VALUES (1, 'hot dogs', 'entree', 'soy');
INSERT INTO menu (cookout_id, item_name, item_type, allergens) VALUES (1, 'popsicles', 'dessert', 'none');
INSERT INTO menu (cookout_id, item_name, item_type, allergens) VALUES (1, 'hamburgers', 'entree', 'none');
INSERT INTO menu (cookout_id, item_name, item_type, allergens) VALUES (1, 'pizza', 'entree', 'soy');
INSERT INTO menu (cookout_id, item_name, item_type, allergens) VALUES (1, 'pasta salad', 'sidedish', 'egg');
INSERT INTO menu (cookout_id, item_name, item_type, allergens) VALUES (1, 'grilled veggies', 'sidedish', 'none');
INSERT INTO menu (cookout_id, item_name, item_type, allergens) VALUES (1, 'veggie dogs', 'entree', 'soy');
INSERT INTO menu (cookout_id, item_name, item_type, allergens) VALUES (1, 'salad', 'sidedish', 'egg');
INSERT INTO menu (cookout_id, item_name, item_type, allergens) VALUES (1, 'buns', 'sidedish', 'none');
INSERT INTO menu (cookout_id, item_name, item_type, allergens) VALUES (1, 'watermelon', 'sidedish', 'soy');

INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (3, 1, 'Host', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (3, 4, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (3, 2, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (3, 3, 'Chef', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (3, 8, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (3, 9, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (3, 5, 'Host', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (3, 6, 'Chef', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (3, 7, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (2, 1, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (5, 1, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (7, 1, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (6, 1, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (6, 2, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (7, 2, 'Host', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (8, 3, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (2, 2, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (2, 3, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (8, 2, 'Guest', true);
INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) VALUES (8, 1, 'Guest', true);

INSERT INTO user_order (user_id, menu_item_id) VALUES (1, 1);
INSERT INTO user_order (user_id, menu_item_id) VALUES (2, 3);
INSERT INTO user_order (user_id, menu_item_id) VALUES (3, 2);

COMMIT TRANSACTION;
