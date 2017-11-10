#Role Table
INSERT INTO `property_management_app`.`role` (`role_id`, `name`, `description`) VALUES ('1', 'Tenant', 'A person who lives at a property.');
INSERT INTO `property_management_app`.`role` (`role_id`, `name`, `description`) VALUES ('2', 'Manager', 'A manager of the property');
INSERT INTO `property_management_app`.`role` (`role_id`, `name`, `description`) VALUES ('3', 'Landlord', 'The owner of the property.');


#User table
INSERT INTO `property_management_app`.`user` (`user_id`, `first_name`, `last_name`, `email`, `password`, `role_id`, `image_url_main`, `image_url_thumb`) VALUES ('1', 'Noah', 'Panicola', 'noahpanicola@gmail.com', 'test', '2', 'test.png', 'test2.png');
INSERT INTO `property_management_app`.`user` (`user_id`, `first_name`, `last_name`, `email`, `password`, `role_id`, `image_url_main`, `image_url_thumb`) VALUES ('2', 'Anthony', 'Forsythe', 'forsythetony@gmail.com', 'test', '2', 'test.png', 'test3.png');
INSERT INTO `property_management_app`.`user` (`user_id`, `first_name`, `last_name`, `email`, `password`, `role_id`, `image_url_main`, `image_url_thumb`) VALUES ('3', 'David', 'Dean', 'daviddean@gmail.com', 'testtest', '1', 'test.png', 'test2.png');
INSERT INTO `property_management_app`.`user` (`user_id`, `first_name`, `last_name`, `email`, `password`, `role_id`, `image_url_main`, `image_url_thumb`) VALUES ('4', 'Taylor', 'Ratliff', 'tayratliff@gmail.com', 'testtest', '1', 'test.png', 'test3.png');

#Property table
INSERT INTO `property_management_app`.`property` (  `street_address`,
                                                    `state`, `zip`,
                                                    `image_url_main`,
                                                    `image_url_thumb`,
                                                    `coord_lat`,
                                                    `coord_long`)
                                        VALUES (    '1609 University Avenue',
                                                    'MO',
                                                    '65201',
                                                    'https://i.imgur.com/DqS7BbZ.jpg',
                                                    'https://i.imgur.com/DqS7BbZ.jpg',
                                                    38.946796,
                                                    -92.316347
                                              );

INSERT INTO `property_management_app`.`property` (  `street_address`,
                                                    `state`, `zip`,
                                                    `image_url_main`,
                                                    `image_url_thumb`,
                                                    `coord_lat`,
                                                    `coord_long`)
                                        VALUES (    '1603 Hinkson Avenue',
                                                    'MO',
                                                    '65201',
                                                    'https://i.imgur.com/Iwlxnpn.png',
                                                    'https://i.imgur.com/Iwlxnpn.png',
                                                    38.956890,
                                                    -92.316221
                                              );

INSERT INTO `property_management_app`.`property` (  `street_address`,
                                                    `state`, `zip`,
                                                    `image_url_main`,
                                                    `image_url_thumb`,
                                                    `coord_lat`,
                                                    `coord_long`)
                                        VALUES (    '1512 Ross Street',
                                                    'MO',
                                                    '65201',
                                                    'https://i.imgur.com/0qymran.png',
                                                    'https://i.imgur.com/0qymran.png',
                                                    38.943656,
                                                    -92.317632
                                              );

INSERT INTO `property_management_app`.`property` (  `street_address`,
                                                    `state`, `zip`,
                                                    `image_url_main`,
                                                    `image_url_thumb`,
                                                    `coord_lat`,
                                                    `coord_long`)
                                        VALUES (    '413 Hitt Street',
                                                    'MO',
                                                    '65201',
                                                    'https://i.imgur.com/V38g1Rv.png',
                                                    'https://i.imgur.com/V38g1Rv.png',
                                                    38.946876,
                                                    -92.325776
                                              );
-- Assign properties to users
INSERT INTO `property_management_app`.`user_property` ( `user_id`, `property_id`, `is_manager`) VALUES ( 1 , 1, 1);
INSERT INTO `property_management_app`.`user_property` ( `user_id`, `property_id`, `is_manager`) VALUES ( 1 , 2, 1);
INSERT INTO `property_management_app`.`user_property` ( `user_id`, `property_id`, `is_manager`) VALUES ( 1 , 3, 1);
INSERT INTO `property_management_app`.`user_property` ( `user_id`, `property_id`, `is_manager`) VALUES ( 1 , 4, 1);

-- Populate Settings with necessary information for app to run
INSERT INTO `property_management_app`.`settings` (`category`, `name`, `value`) VALUES ('GoogleMaps', 'ApiKey', 'AIzaSyArUtQP9qjYB9nDrQ5zTEqaWIKc1yhXV0g');
INSERT INTO `property_management_app`.`settings` (`category`, `name`, `value`) VALUES ('GoogleMaps', 'RootUrl', 'https://maps.googleapis.com/maps/api/geocode/json?');
INSERT INTO `property_management_app`.`settings` (`category`, `name`, `value`) VALUES ('Main', 'RootUrl', 'http://localhost:8080');
