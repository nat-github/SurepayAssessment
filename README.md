# SurepayAssessment

## Overview
`SurepayAssessment` is a project that aims to verify the functionality of the https://jsonplaceholder.typicode.com/ API. This project uses Cucumber,  behavior-driven development (BDD) testing framework and RestAssured , Java based API automation library 
to define and execute API automation scenarios.

## Purpose
The main purpose of this project is to demonstrate the API automation capabilities of an individual

## Structure
The project is structured as follows:

1. `src - main`: This directory contains the source code for the project. It includes the Java classes(like model,context,utils and config) and resources required for the project's functionality.
2. `src - test`: This directory contains the test code for the project. It includes the Step definitons and runner file.
3. `test - resources`: This directory contains the resources required for the project. It includes feature files and mocked response files

## Getting Started
To get started with this project, you will need to have the following tools and technologies installed on your system:

1. Java Development Kit (JDK) 11
2. Maven
3. Cucumber
4. RestAssured

Once you have installed these tools and technologies, you can follow these steps to get started with the project:

1. Clone the repository: You can clone the repository using the following command:
   `git clone https://github.com/nat-github/SurepayAssessment`

There are two branches - feature/development and main.

feature/development is used for development and main is used for production

2.Navigate to the project directory: Open your terminal and navigate to the directory where you want to work on the project.

3.Build the project: You can build the project using the following command:

    `mvn clean install`
4.Run the tests: You can run the tests using the following command:
                                `mvn test` 
 or tag based execution like    `'mvn test -Dcucumber.options="--tags @FunctionalTesting"`

Review the test results: After running the tests, you can review the test results by checking the output of the `mvn test` command
And cucumber reports are available in:
**target/cucumber-reports/report.html**

5.Project has also been configured to run in **CircleCI** . Please check **config.yml** in .circleci folder for details
