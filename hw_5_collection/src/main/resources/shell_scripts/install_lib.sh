cd ../../../..
mvn install:install-file -Dfile=src/main/resources/libs/math_set.jar \
                         -DgroupId=mathset \
                         -DartifactId=math_set \
                         -Dversion=1.0 -Dpackaging=jar