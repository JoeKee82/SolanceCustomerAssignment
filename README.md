# Solance 


## Running the Application

Always a good idea to run when first cloning code to local
``` bash
   mvn clean install
```

## Command line (Maven)
``` bash
   mvn org.springframework.boot:spring-boot-maven-plugin:run
```

## Command line (Docker)
``` bash
   docker build -t customer-service . 
   docker run -p 8080:8080 customer-service
```


## Usage

To see H2 database records go to [h2 console](http://localhost:8080/h2-console/) (sa/password)


1. Retrieve Customers
   ```bash
      curl --location 'http://localhost:8080/customer'
   ```
2. Register Customer
    ```bash
    curl --location --request PUT 'http://localhost:8080/customer' \
   --header 'Content-Type: application/json' \
   --data '{"iban": "123456789", "currency": "EUR", "balance" : 200.0, "accountStatus": "INACTIVE"}'
   ```
3. Open customer account 
    ```bash
    curl --location --request PATCH 'http://localhost:8080/customer/1'
   ```
4. Create customer deposit transaction (pay-in from our customer)
    ```bash
    
   ```
5. Create customer payment instruction (pay-out to a beneficiary of our customer)
    ```bash
    
   ```

## Notes