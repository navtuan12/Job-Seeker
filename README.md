
# Job Seeker




## Features

- **User Authentication**: Register, login, and update user profiles.
- **Company Management**: Register, login, and manage company profiles.
- **Job Management**: Create, update, and search for job postings.
- **Security**: Password encryption using BCrypt and JWT for secure authentication.
- **Validation**: Input validation for user and company registration.

## Demo



## Tech Stack

**Client:** 
- **ReactJs**
- **Tailwind**

**Server:**
- **Java 8**
- **Spring Boot**
- **Spring Security**
- **MapStruct**
- **Lombok**
- **Maven**

**Database**
- **MongoDB**


## Getting Started

### Prerequisites

- **Java 11**: Ensure you have JDK 11 installed.
- **Maven**: Ensure you have Maven installed.
- **MongoDB**: Ensure you have MongoDB installed and running.
- **NodeJs**: Ensure you have NodeJs 20.11.1 installed.
### Installation

**1. Clone the repository:**
```sh
git clone https://github.com/navtuan12/Job-Seeker.git
cd Job-Seeker
```

**Client**

Install dependencies:

```sh
cd client
npm install
```
Run the application:

```sh
npm run dev
```
**Server**

Update the JDK version in pom.xml: Ensure the *<source>* and *<target>* versions are set to **11**

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>11</source>
                <target>11</target>
                <annotationProcessorPaths>
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                        <version>1.18.24</version>
                    </path>
                    <path>
                        <groupId>org.mapstruct</groupId>
                        <artifactId>mapstruct-processor</artifactId>
                        <version>1.5.2.Final</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Create *.env* file base on *.env.example*:
```env
MONGO_DATABASE= <datebase-name>
MONGO_USER= <mongodb-user>
MONGO_PASSWORD= <mongodb-password>
MONGO_CLUSTER= <mongodb-cluster>
SIGNER_KEY= <jwt-signer-key>
```

Install dependencies:

```sh
mvn clean install
```

Run the application:

```sh
mvn spring-boot:run
```
