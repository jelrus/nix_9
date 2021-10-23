cd ../../../..
call mvn install:install-file -Dfile=${project.basedir}/src/main/resources/libs/string_utils.jar -DgroupId=stringutils -DartifactId=string_utils -Dversion=1.0 -Dpackaging=jar
call mvn clean install