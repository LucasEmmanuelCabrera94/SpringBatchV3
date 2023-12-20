@echo off

set "JAR_PATH=C:\Users\lucas.cabrera\Documents\Backend\springbatch\out\artifacts\springbatch_jar\springbatch.jar"

java -jar "%JAR_PATH%" --spring.config.location=C:\Users\lucas.cabrera\Documents\Backend\springbatch\src\main\resources\application.properties

pause
exit

