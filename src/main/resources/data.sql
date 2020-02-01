DROP TABLE IF EXISTS wallet;

CREATE TABLE wallet (
  id INT AUTO_INCREMENT PRIMARY KEY,
  amount DECIMAL(10,2) NOT NULL,
  name VARCHAR(200) DEFAULT NULL
);
 
INSERT INTO wallet (amount, name) VALUES
  (206.74, 'Wallet de Nick'),
  (123, 'Mi cartera de prueba');