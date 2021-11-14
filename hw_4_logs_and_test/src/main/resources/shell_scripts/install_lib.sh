cd ../../../..
mvn install:install-file -Dfile=src/main/resources/libs/custom_collections.jar \
                         -DgroupId=customcollections \
                         -DartifactId=custom_collections \
                         -Dversion=1.0 -Dpackaging=jar