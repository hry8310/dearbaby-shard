 

DROP TABLE IF EXISTS `dearbaby_hz_shard_tbl_heat`;

CREATE TABLE `dearbaby_hz_shard_tbl_heat` (
  `id` bigint(20) NOT NULL,
  `time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 

LOCK TABLES `dearbaby_hz_shard_tbl_heat` WRITE;

insert  into `dearbaby_hz_shard_tbl_heat`(`id`,`time`) values 
(1,2);

UNLOCK TABLES;
 