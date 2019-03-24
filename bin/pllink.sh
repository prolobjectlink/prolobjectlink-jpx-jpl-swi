#!/usr/bin/bash
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.prolog.jpl.swi.SwiPrologDatabaseConsole ${1+"$@"}