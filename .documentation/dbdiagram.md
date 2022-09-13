```
Table users {
  id int
  sub string
  first_name string
  last_name string
  email string
}

Table products as P {
  id int
  sub string
  first_name string
  last_name string
  email string
}

Table categories as PC {
  id int
  name string
}

Table product_categories {
  id int
  product_id int [ref: <> P.id]
  category_id int [ref: <> PC.id]
}
```