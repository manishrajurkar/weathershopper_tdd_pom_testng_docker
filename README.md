# Automation Framework

This is a UI test automation framework developed based on the Page Object Model (POM) Design Pattern using TestNG framework


## Getting Started

## Prerequisites
1. Java 8
2. Maven
3. Git

## How to Install and Run the Project

All you need to do is to run the below command to build the project using Maven

#### 1. Copy GitHub https URL
```bash
git clone https://github.com/manishrajurkar87/weathershopper_tdd_pom_testng_docker.git
```
#### 2. Navigate to the location where pom.xml file is present
```bash
cd weathershopper_tdd_pom_testng_docker/  
```
#### 3. Run maven command
```bash
mvn clean install
```

## How to Use the Project

Page Object Model (POM) is a design pattern, that creates Object Repository for web UI elements. The advantage of the model is that it reduces code duplication and improves test maintenance.

Under this model, for each web page in the application, there should be a corresponding Page Class. This Page class will identify the WebElements of that web page and also contains Page methods which perform operations on those WebElements



