#!/usr/bin/bash
kill $(jps -l | grep org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole | awk '{print $1}')
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -m
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -j "$(dirname "$(pwd)")/lib/prolobjectlink-jpx-model.jar"
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -z 9110