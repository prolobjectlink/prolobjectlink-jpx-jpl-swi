@echo off
FOR /F "tokens=1,2 delims= " %%G IN ('jps -l') DO IF %%H==io.github.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole taskkill /F /PID %%G