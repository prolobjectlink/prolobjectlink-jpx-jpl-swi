#!/usr/bin/bash
kill $(jps -l | grep org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole | awk '{print $1}')