POSTGRES in computer

docker run -e DB_URL=jdbc:postgresql://host.docker.internal:<PORT>/<DB_NAME>?sslmode=disable   -e DB_USER=<USERNAME>   -e DB_PASSWORD='<PASSWORD>'   -e JWT_SECRET='<JWT_KEY>'   -p 8080:8080 backend

POSTGRES in docker :

docker run --network=host \
  -e DB_URL=jdbc:postgresql://localhost:<PORT>/<DB_NAME>?sslmode=disable \
  -e DB_USER=<DB_USER> \
  -e DB_PASSWORD='<DB_PASSWORD>' \
  -e JWT_SECRET='<JWT_SECRET>' \
  -p 8080:8080 backend
