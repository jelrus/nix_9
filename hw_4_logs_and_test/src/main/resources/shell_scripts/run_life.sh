sh ./install_lib.sh
cd ../../../..
mvn clean install -Plife -DskipTests
java -jar ./target/hw_4_logs_and_test.jar