#!/usr/bin/bash
java -classpath "$(pwd)/lib/*" org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -g
java -classpath "$(pwd)/lib/*" org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -s