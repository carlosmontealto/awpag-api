CREATE TABLE tb_client (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(60) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE tb_client
ADD CONSTRAINT uk_client UNIQUE (email);
