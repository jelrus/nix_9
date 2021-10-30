cd ../../../..
call mvn install:install-file -Dfile=${project.basedir}/src/main/resources/libs/string_utils.jar -DgroupId=customcollections -DartifactId=custom_collections -Dversion=1.0 -Dpackaging=jar
call mvn clean install