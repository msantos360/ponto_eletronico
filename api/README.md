docker run --name mysql-ponto --network dev-net -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=ponto -d mysql:5.6.51
