# üK223 group 5

Prerequisites:
- Docker Desktop
- Intellij IDE
- Visual Studio Code

First thing you will need to do is to clone he following repositories:

### Frontend:
````
git clone https://github.com/LorenaVennemann/ueK223_Frontend.git
````
### Backend:
````
git clone https://github.com/LorenaVennemann/uek223_Backend.git
````
Then you must open the backend in IntelliJ.
Before you can execute it, you will need to prepare your Docker by starting it and execute the following command in Powershell or the Command Prompt:
````
docker run --name postgres_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
````
It is important to make sure that no other container is running on that port.
Now that the Docker container is running you must open the frontend in VScode and run the following commands:
```
npm install --global yarn
yarn start
```

To see the swagger documentation you must start the project and go to the following link:
http://localhost:8080/myapi/swagger-ui/index.html#/
