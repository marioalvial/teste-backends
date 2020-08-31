DIRECTORY=src/test/resources/input

run:
	./gradlew clean build -x test
	java -jar build/libs/teste-backends-all.jar ${DIRECTORY}

test:
	./gradlew clean test

unit-test:
	./gradlew test -Dtest.type=unit

integration-test:
	./gradlew test -Dtest.type=integration
