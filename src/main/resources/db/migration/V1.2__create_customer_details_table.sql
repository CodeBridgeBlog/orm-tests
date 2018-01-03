create table customer_details (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  age INTEGER
);
