CREATE TABLE "users" (
                         "id" long PRIMARY KEY,
                         "sub" varchar,
                         "first_name" varchar,
                         "last_name" varchar,
                         "email" varchar
);

CREATE TABLE "products" (
                            "id" long PRIMARY KEY,
                            "description" varchar,
                            "price" decimal
);

CREATE TABLE "categories" (
                              "id" long PRIMARY KEY,
                              "name" varchar
);

CREATE TABLE "product_categories" (
                                      "id" long PRIMARY KEY,
                                      "product_id" long,
                                      "category_id" long
);

CREATE TABLE "payments" (
                            "id" long PRIMARY KEY,
                            "amount" decimal,
                            "currency" enum,
                            "order_id" int
);

CREATE TABLE "orders" (
                          "id" long PRIMARY KEY,
                          "status" enum,
                          "orderedDate" date,
                          "customerComments" varchar,
                          "user_id" long
);

CREATE TABLE "order_items" (
                               "id" long PRIMARY KEY,
                               "quantity" int,
                               "order_id" long,
                               "product_id" long
);

CREATE TABLE "shopping_carts" (
                                  "id" long PRIMARY KEY,
                                  "product_id" long,
                                  "quantity" int,
                                  "user_id" long
);

CREATE TABLE "products_product_categories" (
                                               "products_id" long,
                                               "product_categories_product_id" long,
                                               PRIMARY KEY ("products_id", "product_categories_product_id")
);

ALTER TABLE "products_product_categories" ADD FOREIGN KEY ("products_id") REFERENCES "products" ("id");

ALTER TABLE "products_product_categories" ADD FOREIGN KEY ("product_categories_product_id") REFERENCES "product_categories" ("product_id");


CREATE TABLE "categories_product_categories" (
                                                 "categories_id" long,
                                                 "product_categories_category_id" long,
                                                 PRIMARY KEY ("categories_id", "product_categories_category_id")
);

ALTER TABLE "categories_product_categories" ADD FOREIGN KEY ("categories_id") REFERENCES "categories" ("id");

ALTER TABLE "categories_product_categories" ADD FOREIGN KEY ("product_categories_category_id") REFERENCES "product_categories" ("category_id");


ALTER TABLE "payments" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "orders" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "order_items" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "order_items" ADD FOREIGN KEY ("product_id") REFERENCES "products" ("id");

ALTER TABLE "shopping_carts" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");
CREATE TABLE "users" (
                         "id" long PRIMARY KEY,
                         "sub" varchar,
                         "first_name" varchar,
                         "last_name" varchar,
                         "email" varchar
);

CREATE TABLE "products" (
                            "id" long PRIMARY KEY,
                            "description" varchar,
                            "price" decimal
);

CREATE TABLE "categories" (
                              "id" long PRIMARY KEY,
                              "name" varchar
);

CREATE TABLE "product_categories" (
                                      "id" long PRIMARY KEY,
                                      "product_id" long,
                                      "category_id" long
);

CREATE TABLE "payments" (
                            "id" long PRIMARY KEY,
                            "amount" decimal,
                            "currency" enum,
                            "order_id" int
);

CREATE TABLE "orders" (
                          "id" long PRIMARY KEY,
                          "status" enum,
                          "orderedDate" date,
                          "customerComments" varchar,
                          "user_id" long
);

CREATE TABLE "order_items" (
                               "id" long PRIMARY KEY,
                               "quantity" int,
                               "order_id" long,
                               "product_id" long
);

CREATE TABLE "shopping_carts" (
                                  "id" long PRIMARY KEY,
                                  "product_id" long,
                                  "quantity" int,
                                  "user_id" long
);

CREATE TABLE "products_product_categories" (
                                               "products_id" long,
                                               "product_categories_product_id" long,
                                               PRIMARY KEY ("products_id", "product_categories_product_id")
);

ALTER TABLE "products_product_categories" ADD FOREIGN KEY ("products_id") REFERENCES "products" ("id");

ALTER TABLE "products_product_categories" ADD FOREIGN KEY ("product_categories_product_id") REFERENCES "product_categories" ("product_id");


CREATE TABLE "categories_product_categories" (
                                                 "categories_id" long,
                                                 "product_categories_category_id" long,
                                                 PRIMARY KEY ("categories_id", "product_categories_category_id")
);

ALTER TABLE "categories_product_categories" ADD FOREIGN KEY ("categories_id") REFERENCES "categories" ("id");

ALTER TABLE "categories_product_categories" ADD FOREIGN KEY ("product_categories_category_id") REFERENCES "product_categories" ("category_id");


ALTER TABLE "payments" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "orders" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "order_items" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "order_items" ADD FOREIGN KEY ("product_id") REFERENCES "products" ("id");

ALTER TABLE "shopping_carts" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");
