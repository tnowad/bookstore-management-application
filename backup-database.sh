docker exec bookstore-management-database sh -c 'exec mysqldump --databases bookstore -uroot -padmin123' > ./database/init/backup.sql