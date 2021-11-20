cd ../../../..
call mvn install:install-file -Dfile=${project.basedir}/src/main/resources/libs/math_set.jar -DgroupId=mathset -DartifactId=math_set -Dversion=1.0 -Dpackaging=jar
call mvn clean install