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
                                                    'testhouseimage.png',
                                                    'testimgthumb.png',
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
                                                    'testhouseimage.png',
                                                    'testimgthumb.png',
                                                    38.956890,
                                                    -92.316221
                                              );
