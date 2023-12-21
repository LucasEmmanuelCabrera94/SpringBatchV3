@echo off

set "JAR_PATH=C:\Users\lucas.cabrera\Documents\Backend\springbatch\out\artifacts\springbatch_jar\springbatch.jar"
set "LOCATION_PATH=C:\Users\lucas.cabrera\Documents\Backend\springbatch\target\classes\application.properties"

java -jar "%JAR_PATH%" --spring.config.location=C:\Users\lucas.cabrera\Documents\Backend\springbatch\src\main\resources\prueba.properties

pause
exit

