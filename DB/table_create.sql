DROP DATABASE property_management_app;
CREATE DATABASE property_management_app;
USE property_management_app;



/*
  ROLE ID
*/
CREATE TABLE `role` (
  role_id       SMALLINT UNSIGNED,
  name          VARCHAR(200) NOT NULL DEFAULT 'n/a',
  description   VARCHAR(300) NOT NULL DEFAULT '',
  PRIMARY KEY (role_id)
);


/*
  USER TABLE
*/
--  FIXME:
--    For right now I'm going to allow the password and password_salt fields to
--    be NULL just for testing purposes. This should be changed.
CREATE TABLE `user` (

  user_id             SERIAL,
  first_name          VARCHAR(200) NOT NULL DEFAULT '',
  last_name           VARCHAR(200) NOT NULL DEFAULT '',
  email               VARCHAR(200) NOT NULL DEFAULT '',
  password            CHAR(128) NULL,
  role_id             SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  image_url_main      VARCHAR(2083),
  image_url_thumb     VARCHAR(2083),
  PRIMARY KEY (user_id),
  FOREIGN KEY (role_id) REFERENCES role(role_id)
);

/*
  PROPERTY TABLE
*/

CREATE TABLE `property` (

  property_id         SERIAL,
  street_address      VARCHAR(200) NOT NULL DEFAULT '',
  state               VARCHAR(2),
  zip                 VARCHAR(10),
  image_url_main      VARCHAR(2083),
  image_url_thumb     VARCHAR(2083),
  coord_lat           DECIMAL(7,4) NOT NULL DEFAULT 0.0,
  coord_long          DECIMAL(7,4) NOT NULL DEFAULT 0.0,
  PRIMARY KEY (property_id)
);

/*
  USER PROPERTY TABLE
*/

CREATE TABLE `user_property` (

  user_prop_id      SERIAL,
  user_id           BIGINT UNSIGNED NOT NULL,
  property_id       BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (user_prop_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (property_id) REFERENCES property(property_id)
);

/*
  PROPERTY IMAGE TABLE
*/

CREATE TABLE `property_image` (
  prop_img_id       SERIAL,
  property_id       BIGINT UNSIGNED NOT NULL,
  image_url         VARCHAR(2083),
  image_url_thumb   VARCHAR(2083),
  PRIMARY KEY (prop_img_id),
  FOREIGN KEY (property_id) REFERENCES property(property_id)
);

/*
  WORK ITEM TABLE
*/

CREATE TABLE `work_item` (
  work_item_id        SERIAL,
  property_id         BIGINT UNSIGNED NOT NULL,
  date_created        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  is_accepted         BIT NOT NULL DEFAULT 0,
  description         VARCHAR(500) NOT NULL DEFAULT '',
  time_scheduled      TIMESTAMP NULL,
  estimated_time      DOUBLE NOT NULL DEFAULT 1.0,
  fee                 DOUBLE NOT NULL DEFAULT 0.0,
  PRIMARY KEY (work_item_id),
  FOREIGN KEY (property_id) REFERENCES property(property_id)
);

/*
  USER WORK ITEM
*/

CREATE TABLE `user_work_item` (
  user_work_item_id         SERIAL,
  user_id                   BIGINT UNSIGNED NOT NULL,
  work_item_id              BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (user_work_item_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (work_item_id) REFERENCES work_item(work_item_id)
);

/*
  MESSAGE TABLE
*/

CREATE TABLE `message` (
  message_id          SERIAL,
  header              VARCHAR(200) NOT NULL DEFAULT '',
  body                VARCHAR(500) NOT NULL DEFAULT '',
  time_sent           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (message_id)
);


/*
  USER MESSAGE TABLE
*/

CREATE TABLE `user_message` (
  user_message_id     SERIAL,
  sender_id           BIGINT UNSIGNED NOT NULL,
  receiver_id         BIGINT UNSIGNED NOT NULL,
  message_id          BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (user_message_id),
  FOREIGN KEY (sender_id) REFERENCES user(user_id),
  FOREIGN KEY (receiver_id) REFERENCES user(user_id),
  FOREIGN KEY (message_id) REFERENCES message(message_id)
);


/*
  LOG TABLE
*/

CREATE TABLE `log` (
  log_id            SERIAL,
  user_id           BIGINT UNSIGNED NOT NULL,
  message           VARCHAR(100),
  log_time          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (log_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);


/*
  SETTINGS TABLE
*/

CREATE TABLE `settings` (
  setting_id        SERIAL,
  category          VARCHAR(30),
  name              VARCHAR(30),
  value             VARCHAR(30),
  PRIMARY KEY (setting_id)
);


/*
  MOVE IN RECORD TABLE
*/

CREATE TABLE `move_in_record` (
  mir_id            SERIAL,
  date_recorded     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_id           BIGINT UNSIGNED,
  property_id       BIGINT UNSIGNED,
  PRIMARY KEY (mir_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (property_id) REFERENCES property(property_id)
);

/*
    MIR RECORD PHOTO
*/
CREATE TABLE `mir_photo` (
  mir_photo_id        SERIAL,
  mir_id              BIGINT UNSIGNED NOT NULL,
  photo_url_main      VARCHAR(2083),
  photo_url_thumb     VARCHAR(2083),
  date_taken          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (mir_photo_id),
  FOREIGN KEY (mir_id) REFERENCES move_in_record(mir_id)
);
