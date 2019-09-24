@echo off
FOR /F "tokens=1,2 delims= " %%G IN ('jps -l') DO IF %%H==org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole taskkill /F /PID %%G

SET CURRENT_DIRECTORY=%~dp0
for %%B in (%CURRENT_DIRECTORY%.) do set PROLOBJECTLINK_HOME=%%~dpB
SET CLASSPATH=%PROLOBJECTLINK_HOME%lib\*

: default jdk
java -classpath %CLASSPATH% org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -m
java -classpath %CLASSPATH% org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -j %PROLOBJECTLINK_HOME%lib\prolobjectlink-jpx-model.jar
java -classpath %CLASSPATH% org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -z 9110