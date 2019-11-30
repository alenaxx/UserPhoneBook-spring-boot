CREATE TABLE userr (
     id UUID NOT NULL PRIMARY KEY,
     name VARCHAR(100) ,
     number VARCHAR(100)
);
CREATE TABLE phoneBook (
     id UUID NOT NULL PRIMARY KEY,
     name VARCHAR(100) ,
     number VARCHAR(100),
     userId UUID REFERENCES userr (id)
);

