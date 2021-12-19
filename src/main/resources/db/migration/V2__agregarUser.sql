CREATE TABLE IF NOT EXISTS users(
  id serial,
  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NULL,
  PRIMARY KEY (id),
  UNIQUE  (username)
  );
