cd ../../../..
call mvn install:install-file -Dfile=${project.basedir}/src/main/resources/libs/custom_collections.jar -DgroupId=customcollections -DartifactId=custom_collections -Dversion=1.0 -Dpackaging=jar
call mvn clean install