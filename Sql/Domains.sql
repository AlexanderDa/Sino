CREATE DOMAIN identification AS character varying(11)
CHECK (value similar to '%[0-9]%[0-9]%[0-9]%[0-9]%[0-9]%[0-9]%[0-9]%[0-9]%[0-9]-%[0-9]') NOT NULL;


CREATE DOMAIN note as REAL default 0
CHECK(value >=0 and value <=10);


CREATE DOMAIN day as integer default 0
CHECK(value >=0);




