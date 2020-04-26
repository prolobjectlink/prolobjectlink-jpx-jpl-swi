#!/usr/bin/bash
kill $(jps -l | grep io.github.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole | awk '{print $1}')