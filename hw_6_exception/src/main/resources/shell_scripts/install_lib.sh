cd ../../../..
mvn install:install-file -Dfile=src/main/resources/libs/calendar.jar \
                         -DgroupId=calendar \
                         -DartifactId=calendar \
                         -Dversion=1.0 -Dpackaging=jar