# MSSC Beer Inventory Service

# Responsibility
- Manages Beer Inventory
- Takes in inventory from brewing operations
- Provides inventory for orders 

# Default Port Mappings - Brewery Services
| Service Name | Port | 
| --------| -----|
| [Beer Service](https://github.com/AlexeiStrug/Brewery-SpringCloud-Microservice/tree/master/beer-service) | 8080 |
| [Beer Order Service](https://github.com/AlexeiStrug/Brewery-SpringCloud-Microservice/tree/master/beer-order-service) | 8081 |
| [Beer Inventory Service](https://github.com/AlexeiStrug/Brewery-SpringCloud-Microservice/tree/master/beer-inventory-service) | 8082 |
| [Beer Inventory-Failover Service](https://github.com/AlexeiStrug/Brewery-SpringCloud-Microservice/tree/master/inventory-failover) | 8083 |

# Default Port Mappings - Spring Cloud Services
| Service Name | Port | 
| --------| -----|
| [Config Service](https://github.com/AlexeiStrug/Brewery-SpringCloud-Microservice/tree/master/brewery-config-server) | 8888 |
| [Eureka Service](https://github.com/AlexeiStrug/Brewery-SpringCloud-Microservice/tree/master/brewery-eureka) | 8761 |
| [Gateway Service](https://github.com/AlexeiStrug/Brewery-SpringCloud-Microservice/tree/master/brewery-gateway) | 9090 |

# BOM Service
| Service Name |
| --------|
| [BOM Service](https://github.com/AlexeiStrug/Brewery-SpringCloud-Microservice/tree/master/brewery-bom) |

# Third-part Service
| Service Name | Port | 
| --------| -----|
| [ActiveMQ Artemis Docker](https://github.com/vromero/activemq-artemis-docker) | 8161 |
