## Interview Challenge: Java Springboot Kafka Application

Welcome to the Interview Challenge for a Java Springboot Kafka Application! In this challenge, you will have to fix a bug in a Springboot application that reads messages from Kafka and saves them to a PostgreSQL database. In addition, you will have to implement a new feature that allows retrieving scanner data by type and date range through a new endpoint in the Springboot application.

### Prerequisites
- Basic knowledge of Java and Springboot
- Basic knowledge of Kafka and PostgreSQL
- A local environment with Java, Docker, and Docker Compose installed

### Getting Started
1. Fork this repository to your GitHub account
2. Clone the forked repository to your local environment
3. Start the Docker Compose environment by running the following command in the root directory of the project:
```shell
docker-compose up -d
```
4. Open the project in your preferred Java IDE
5. Solve the bug in the Kafka Consumer and implement the new feature to retrieve scanner data
6. Test your changes thoroughly
7. Commit your changes and push them to your forked repository
8. Share the link to your forked repository with the interviewer

### Challenge Instructions
#### Bug Fix

1. The Springboot application is consuming messages from the Kafka topic scan_topic.
2. There is a bug in the Kafka Consumer that is preventing messages from being saved to the PostgreSQL database.
3. Your task is to fix the bug so that messages are saved correctly to the database.

#### New Feature

1. Implement a new endpoint in the Springboot application that allows retrieving scanner data by type and date range.
2. The endpoint should accept the following parameters:
   - type: the type of scanner data to retrieve (required)
   - startDate: the start date of the range to retrieve (optional)
   - endDate: the end date of the range to retrieve (optional)
3. The endpoint should return a JSON array of scanner data objects that match the specified type and date range.
4. If the startDate parameter is not provided, the endpoint should return data from the beginning of the database records.
5. If the endDate parameter is not provided, the endpoint should return data up to the most recent records in the database.

### Good Luck!
We hope you enjoy this Interview Challenge and learn something new in the process. Good luck and happy coding! Don't forget to reach out to us if you have any questions or concerns.