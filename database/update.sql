/**1. 用户信息表**/
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
  i_id    INTEGER AUTO_INCREMENT NOT NULL
  COMMENT '用户id,主键',
  v_name  VARCHAR(100)           NOT NULL
  COMMENT '昵称',
  v_photo VARCHAR(200)           NOT NULL
  COMMENT '头像',
  c_sex   CHAR(1)                NOT NULL
  COMMENT '性别',
  v_sign  VARCHAR(100)           NOT NULL
  COMMENT '个性签名',
  i_level INTEGER                NOT NULL DEFAULT 0
  COMMENT '用户等级',
  CONSTRAINT x_user_sex CHECK (c_sex IN ('m', 'f', 'u')),
  CONSTRAINT x_user_level CHECK (i_level >= 0),
  PRIMARY KEY (i_id)
)
  ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE utf8mb4_unicode_ci;
