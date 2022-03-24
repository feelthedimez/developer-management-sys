.SILENT:

all: compile tests package

compile:
	echo "Compile stage"
	mvn clean
	mvn compile

test:
	echo "Testing stage"
	mvn test

package:
	echo "Creating a JAR"
	mvn install -Dmaven.test.skip
