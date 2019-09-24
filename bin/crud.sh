#!/usr/bin/bash
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -m
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -c "$(dirname "$(pwd)")/lib/prolobjectlink-jpx-model.jar"
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -j "$(dirname "$(pwd)")/lib/prolobjectlink-jpx-model.jar"