default: init

init: reset-docker up-db wait-instance init-database

reset-docker:
	-docker-compose down --rmi=local --volumes --remove-orphans

up-db:
	docker-compose up --force-recreate -d db

build:
	docker-compose build

down:
	docker-compose down --remove-orphans

ssh:
	docker-compose exec db /bin/bash

init-database:
	docker-compose exec db sh -d "echo 'hello'"

wait-instance:
	sleep 60
