nutrilizr
=========

**Nutrilizr** - Nutrition information for family and friends using the **fatsecret.com** API.

### Providing credentials for the Fat Secret API

For the `dev` profile, consumer client key and secret have to be added to the file `/src/main/resources/secret-dev.properties`, e.g.

```
fatsecret.key=MY-KEY
fatsecret.secret=MY-SECRET
```

### Running the *nutrilizr* server

The server providing the REST food search API at `localhost:8080/api/` can by started using Maven from an IDE or using the included wrapper, like

```
>./mvnw spring-boot:run
```

### Using the food search REST API

To search for food items utilizing the **fatsecret.com** API, submit a request such as the following:

```
http://localhost:8080/api/food/search?query=pasta&page=42
```

Or retrieve detailed information about a food item, e.g.

```
http://localhost:8080/api/food?id=4424
```

Enjoy!