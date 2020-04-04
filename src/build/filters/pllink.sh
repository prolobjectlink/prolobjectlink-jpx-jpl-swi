#!/usr/bin/bash

###
## #%L
## prolobjectlink-jpx-jpl-swi
## %%
## Copyright (C) 2019 Prolobjectlink Project
## %%
## This program is free software: you can redistribute it and/or modify
## it under the terms of the GNU Lesser General Public License as
## published by the Free Software Foundation, either version 2.1 of the
## License, or (at your option) any later version.
## 
## This program is distributed in the hope that it will be useful,
## but WITHOUT ANY WARRANTY; without even the implied warranty of
## MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
## GNU General Lesser Public License for more details.
## 
## You should have received a copy of the GNU General Lesser Public
## License along with this program.  If not, see
## <http://www.gnu.org/licenses/lgpl-2.1.html>.
## #L%
###

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
java -classpath "$(dirname "$(pwd)")/lib/*" ${Main.Class} ${1+"$@"}