<h1 align="center">
    RCVE
</h1>
<p align="center">
    Random Camera Videos Explorer
</p>

## Built with

![Spring]
![Docker]
![React]

## Prerequisites

- [Java](https://www.oracle.com/br/java/technologies/downloads/) (17+)
- [Docker](https://docs.docker.com/get-docker/)
- [YouTube Data API Key](https://developers.google.com/youtube/v3/getting-started)

## Setup environment

1. Clone the repository:
```bash
git clone https://github.com/davisiqueira1/random-camera-videos-explorer.git rcve
```
2. Add your YouTube API key to the application.properties file in the backend directory
```bash
api.key=YOUR_API_KEY
```

## Run with Docker

1. Navigate to the project root directory
```bash
cd rcve
```
2. Build the backend Docker image
```bash
docker build -t rcve-be:1.0 ./backend
```
3. Build the frontend Docker image
```bash
docker build -t rcve-fe:1.0 ./frontend
```
4. Create the Docker network
```bash
docker network create rcve-network
```
5. Start the services with Docker Compose
```bash
docker-compose up
```

[Docker]: https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white

[Spring]: https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white

[React]: https://img.shields.io/badge/-ReactJs-61DAFB?logo=react&logoColor=white&style=for-the-badge