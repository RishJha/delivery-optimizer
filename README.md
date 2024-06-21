# Delivery Optimizer

This project is a Java spring boot application designed to optimize delivery routes for a delivery agent. Agent needs to pick up orders from multiple restaurants and deliver them to corresponding customers in the shortest possible time. The optimization involves calculating travel times between locations using the Haversine formula and considering preparation times at each restaurant.


## Requirements

- Java 17
- Maven (for building and dependency management)

## Usage

1. **Clone the Repository**
```bash
git clone https://github.com/RishJha/delivery-optimizer.git
```

2. **Build the project**
```bash
mvn clean package
```

3. **Run the application**

Development
```bash
mvn spring-boot:run
```

Production
```bash
java -jar target/deliveryoptimizer-0.0.1-SNAPSHOT.jar
```

## Sample Request

1. Route creation api

Request
```bash
curl --location 'localhost:8080/v1/route' \
--header 'Content-Type: application/json' \
--data '{
    "agent": {
        "name": "Aman",
        "location": {
            "latitude": 12.935,
            "longitude": 77.625
        }
    },
    "deliveries": [
        {
            "restaurant": {
                "name": "R1",
                "location": {
                    "latitude": 12.937,
                    "longitude": 77.626
                }
            },
            "customer": {
                "name": "C1",
                "location": {
                    "latitude": 12.938,
                    "longitude": 77.627
                }
            },
            "prepTime": 15
        },
        {
            "restaurant": {
                "name": "R2",
                "location": {
                    "latitude": 12.936,
                    "longitude": 77.628
                }
            },
            "customer": {
                "name": "C2",
                "location": {
                    "latitude": 12.939,
                    "longitude": 77.629
                }
            },
            "prepTime": 20
        }
    ]
}'
```
Response
```json
{
    "route": [
        {
            "name": "R1",
            "location": {
                "longitude": 77.626,
                "latitude": 12.937
            }
        },
        {
            "name": "R2",
            "location": {
                "longitude": 77.628,
                "latitude": 12.936
            }
        },
        {
            "name": "C1",
            "location": {
                "longitude": 77.627,
                "latitude": 12.938
            }
        },
        {
            "name": "C2",
            "location": {
                "longitude": 77.629,
                "latitude": 12.939
            }
        }
    ],
    "totalTime": 37.94595945124141
}
```

2. Test Api
```bash
curl --location 'localhost:8080/v1/route/test'
```

