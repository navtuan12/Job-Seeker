
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

- **Docker Engine**
- 
### Installation

**1. Install Docker**
https://docs.docker.com/engine/install/

**2. Clone the repository:**
```sh
git clone https://github.com/navtuan12/Online-Job-Seeker-ReactJs-Spring-MongoDB.git
cd Online-Job-Seeker-ReactJs-Spring-MongoDB
```
**3. Config** **`docker-compose.yml`**
```yml
  environment:
        - MONGO_URI=
        - MONGO_DATABASE=JobSeeker
        - SIGNER_KEY=
```
**`MONGO_URI`** : your MongoDB connection link.

**`SIGNER_KEY`**: JWT signer key --> Secret Key Generator: https://generate-random.org/encryption-key-generator

**4. Run** 
```sh
docker compose up --build -d
```

**5. Go to browser**
http://localhost:3000
