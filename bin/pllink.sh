#!/usr/bin/bash
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole ${1+"$@"}