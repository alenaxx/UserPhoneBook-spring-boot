CREATE SCHEMA IF NOT EXISTS test_schema;
CREATE TABLE test_schema.userr (
     id UUID NOT NULL PRIMARY KEY,
     name VARCHAR(100) ,
     number VARCHAR(100)
);
CREATE TABLE test_schema.phoneBook (
     id UUID NOT NULL PRIMARY KEY,
     name VARCHAR(100) ,
     number VARCHAR(100),
     userId UUID REFERENCES test_schema.userr (id)
);

