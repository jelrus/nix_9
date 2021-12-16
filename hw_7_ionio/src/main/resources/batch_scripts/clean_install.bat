cd ../../../..
call mvn install:install-file -Dfile=${project.basedir}/src/main/resources/libs/csvio.jar -DgroupId=csvio -DartifactId=csvio -Dversion=1.0 -Dpackaging=jar
call mvn clean install