#!/usr/bin/bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
echo $JAVA_HOME
sudo touch /etc/ld.so.conf.d/java.conf
echo $JAVA_HOME/jre/lib/amd64 | sudo tee -a /etc/ld.so.conf.d/java.conf
echo $JAVA_HOME/jre/lib/amd64/server | sudo tee -a /etc/ld.so.conf.d/java.conf
sudo ldconfig
export PATH=$PATH:$JAVA_HOME/bin
export CLASSPATH=$CLASSPATH:$JAVA_HOME/lib/tools.jar
export CLASSPATH=$CLASSPATH:$JAVA_HOME/jre/lib/rt.jar
export SWI_HOME=/opt/pas-linux-x64-1.0.0/swi
export PATH=$PATH:$SWI_HOME/bin
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/opt/pas-linux-x64-1.0.0/swi/packages/jpl
export CLASSPATH=$CLASSPATH:/opt/pas-linux-x64-1.0.0/swi/packages/jpl/jpl.jar
kill $(jps -l | grep org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole | awk '{print $1}')
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -m
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -j "$(dirname "$(pwd)")/lib/prolobjectlink-jpx-model.jar"
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.jpl.swi.SwiPrologDatabaseConsole -z 9110