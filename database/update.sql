/**1. �û���Ϣ��**/
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
  i_id    INTEGER AUTO_INCREMENT NOT NULL
  COMMENT '�û�id,����',
  v_name  VARCHAR(100)           NOT NULL
  COMMENT '�ǳ�',
  v_photo VARCHAR(200)           NOT NULL
  COMMENT 'ͷ��',
  c_sex   CHAR(1)                NOT NULL
  COMMENT '�Ա�',
  v_sign  VARCHAR(100)           NOT NULL
  COMMENT '����ǩ��',
  i_level INTEGER                NOT NULL DEFAULT 0
  COMMENT '�û��ȼ�',
  CONSTRAINT x_user_sex CHECK (c_sex IN ('m', 'f', 'u')),
  CONSTRAINT x_user_level CHECK (i_level >= 0),
  PRIMARY KEY (i_id)
)
  ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE utf8mb4_unicode_ci;
