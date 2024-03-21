CREATE TABLE tb_installment(
  id BIGINT NOT NULL AUTO_INCREMENT,
  client_id BIGINT NOT NULL,
  description VARCHAR(20) NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  installment_amount TINYINT NOT NULL,
  created_at DATETIME NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE tb_installment ADD constraint fk_installment_client
FOREIGN KEY (client_id) REFERENCES tb_client (id);