-- user table
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
	`user_id`  INTEGER NOT NULL AUTO_INCREMENT, 
	`username` VARCHAR(255) NOT NULL,
	`password` MEDIUMTEXT NOT NULL,
	`role`     VARCHAR(50) NOT NULL,
	PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='user table for shop website' AUTO_INCREMENT=1;

-- products
DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
	`rec_id`  INTEGER NOT NULL AUTO_INCREMENT, 
	`product_name` VARCHAR(255) NOT NULL,
	`updated_by` INTEGER NOT NULL,		
	`updated_on` DATETIME NOT NULL,
	`price` INTEGER NOT NULL,
	PRIMARY KEY (`rec_id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='List of products available in site' AUTO_INCREMENT=1;
-- orders
DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
	`order_id`  INTEGER NOT NULL AUTO_INCREMENT, 
	`order_name` VARCHAR(255) NOT NULL,
	`order_time` DATETIME NOT NULL,		
	`order_address` MEDIUMTEXT  NOT NULL,
   `order_amount` INTEGER NOT NULL,
	PRIMARY KEY (`order_id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='List of order available in site' AUTO_INCREMENT=1;