CREATE TABLE IF NOT EXISTS user_tbl (
  id serial PRIMARY KEY,
  username varchar(250) NOT NULL,
  email varchar(250) NOT NULL
);