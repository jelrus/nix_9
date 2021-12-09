cd ../../../..
call mvn install:install-file -Dfile=${project.basedir}/src/main/resources/libs/calendar.jar -DgroupId=calendar -DartifactId=calendar -Dversion=1.0 -Dpackaging=jar
call mvn clean install