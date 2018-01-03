create table customer (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  details_id UUID REFERENCES customer_details(id)
);
