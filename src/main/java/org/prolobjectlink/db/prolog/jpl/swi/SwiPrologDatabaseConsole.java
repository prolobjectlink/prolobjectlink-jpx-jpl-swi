/*
 * #%L
 * prolobjectlink-jpx-jpl-swi
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package org.prolobjectlink.db.prolog.jpl.swi;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.prolobjectlink.db.DatabaseConsole;
import org.prolobjectlink.db.DatabaseServer;
import org.prolobjectlink.db.prolog.AbstractDatabaseConsole;
import org.prolobjectlink.web.application.GrizzlyModelGenerator;
import org.prolobjectlink.web.application.ModelGenerator;
import org.prolobjectlink.web.platform.GrizzlyServerControl;
import org.prolobjectlink.web.platform.GrizzlyWebServer;
import org.prolobjectlink.web.platform.WebPlatformUtil;
import org.prolobjectlink.web.platform.WebServerControl;
import org.prolobjectlink.web.platform.linux.grizzly.LinuxGrizzlyWebServer;
import org.prolobjectlink.web.platform.macosx.grizzly.MacosxGrizzlyWebServer;
import org.prolobjectlink.web.platform.win32.grizzly.Win32GrizzlyWebServer;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public class SwiPrologDatabaseConsole extends AbstractDatabaseConsole implements DatabaseConsole {

	public SwiPrologDatabaseConsole() {
		super(new SwiPrologDatabaseProvider());
	}

	public static void main(String[] args) {
		new SwiPrologDatabaseConsole().run(args);
	}

	public WebServerControl getWebServerControl(int port) {
		GrizzlyWebServer server = null;
		DatabaseServer database = null;
		if (WebPlatformUtil.runOnWindows()) {
//			database = new Win32DatabaseServer();
			server = new Win32GrizzlyWebServer(port);
		} else if (WebPlatformUtil.runOnOsX()) {
//			database = new MacosxDatabaseServer();
			server = new MacosxGrizzlyWebServer(port);
		} else if (WebPlatformUtil.runOnLinux()) {
//			database = new LinuxDatabaseServer();
			server = new LinuxGrizzlyWebServer(port);
		} else {
			Logger.getLogger(GrizzlyServerControl.class.getName()).log(Level.SEVERE, null, "Not supported platfor");
			System.exit(1);
		}
		return new GrizzlyServerControl(server, database);
	}

	public ModelGenerator getModelGeneratorInstance() {
		return new GrizzlyModelGenerator();
	}

}
