BEGIN TRANSACTION;

DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS user_order;
DROP TABLE IF EXISTS user_cookout;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cookout;

CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	email_address varchar(50) NOT NULL,
	user_notes varchar(500)
);

CREATE TABLE cookout (
    cookout_id SERIAL PRIMARY KEY,
    cookout_name VARCHAR(100),
    date DATE,
    time TIME,
    location VARCHAR(500)
);

CREATE TABLE user_cookout (
    user_id INTEGER,
    cookout_id INTEGER,
	cookout_role VARCHAR(20),
	rsvp BOOLEAN,
    PRIMARY KEY (user_id, cookout_id),
    CONSTRAINT fk_user_cookout_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_user_cookout_cookout_id FOREIGN KEY (cookout_id) REFERENCES cookout(cookout_id)
);

CREATE TABLE menu (
    menu_item_id SERIAL PRIMARY KEY,
    cookout_id int NOT NULL,
    item_name varchar(50) NOT NULL,
    item_type varchar(50) NOT NULL,
    allergens varchar(500),
    CONSTRAINT fk_cookout_id FOREIGN KEY (cookout_id) REFERENCES cookout(cookout_id)
);

CREATE TABLE user_order (
order_id SERIAL PRIMARY KEY,
user_id int NOT NULL,
menu_item_id int NOT NULL,
CONSTRAINT fk_user_order_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
CONSTRAINT fk_user_order_menu_item_id FOREIGN KEY (menu_item_id) REFERENCES menu(menu_item_id)
);

COMMIT TRANSACTION;