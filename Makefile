APPLICATION_CONTAINER_NAME=application-digital-account

run:
	./gradlew clean build
	java -jar build/libs/teste-backends-all.jar src/test/resources/input

test:
	./gradlew clean build test

unit-test:
	./gradlew test -Dtest.type=unit

integration-test:
	./gradlew test -Dtest.type=integration

setup-dependencies:
	@$(MAKE) down
	./gradlew clean build -x test
	docker-compose up -d --build
	docker stop ${APPLICATION_CONTAINER_NAME}
setup-git-hooks:
	@bash setup-git-hooks.sh
