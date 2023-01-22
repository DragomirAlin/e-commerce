```
Table users as U{
  id long [PK]
  sub varchar
  first_name varchar
  last_name varchar
  email varchar
}

Table products as P {
  id long [PK]
  description varchar
  price decimal
}

Table categories as C {
  id long [PK]
  name varchar
}

Table product_categories as PC {
  id long [PK]
  product_id long [ref: <> P.id]
  category_id long [ref: <> C.id]
}

Table payments as PY {
  id long [PK]
  amount decimal
  currency enum
  order_id int [ref: > O.id]
}

Table orders as O{
  id long [PK]
  status enum
  orderedDate date
  customerComments varchar
  user_id long [ref: > U.id]
}

Table order_items {
  id long [PK]
  quantity int
  order_id long [ref: > O.id]
  product_id long [ref: - P.id]
}

Table shopping_carts {
  id long [PK]
  product_id long
  quantity int
  user_id long [ref: > U.id]
}


```