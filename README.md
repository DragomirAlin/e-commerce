# E-Commerce

![build](https://img.shields.io/github/workflow/status/dragomiralin/e-commerce/ECommerce%20CI?style=plastic)
![issues](https://img.shields.io/github/issues/dragomiralin/e-commerce)
![last commit](https://img.shields.io/github/last-commit/dragomiralin/e-commerce)

Another E-Commerce project used to learn and practice a wide range of best practices and tools.
:dancers: Hands-On with Spring JPA, Hibernate, Transactions, SQL

![shop](.documentation/img/online-shop.png)

## Documentation

The E-Commerce documentation, diagrams, schemas, notes can be found
here [.documentation](https://github.com/DragomirAlin/e-commerce/tree/main/.documentation)

## High-level description

The provided services are:

- **User service** - handles the user's data and billing information
- **Product service** - handles the products catalog(create, delete, update, retrieve)
- **Shopping cart service** - handles the user's shopping cart(persist the items, update, delete, checkout)
- **Order service** - handles the user's orders(create order from shopping cart, update, delete, retrieve)
- **Payment service** - handles the orders' payments using external services(Stripe, PayPal, BTPay)
- **Category service** - handles the products categories(relation between products and categories)

### My Objectives:
- Apply best practices
- Play with transactions
- Clean Code
- Use Design Patterns
- Keep it simple
- Enjoy the work

## Planning
I'm going to build a monolithic application with Hexagonal Architecture, and then I'm going to split it into
microservices.
Detailed planning can be found here [Planning](./documentation/planning.md)
[E-Commerce Kanban](https://github.com/DragomirAlin/spring-transactions/projects/1)


### Data Model
[E-Commerce Data Model](./documentation/dbdiagram.md)

### References and further reading
- [Transaction Management](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/transaction.html#:~:text=The%20Spring%20Framework%20provides%20a,Java%20Data%20Objects%20(JDO).)
- [https://microservices.io/](https://microservices.io/)
- [https://12factor.net/](https://12factor.net/)

