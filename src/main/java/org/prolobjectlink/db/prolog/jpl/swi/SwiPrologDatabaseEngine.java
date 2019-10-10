/*-
 * #%L
 * prolobjectlink-jpx-jpl-swi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
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

import org.prolobjectlink.db.prolog.PrologDatabaseEngine;
import org.prolobjectlink.db.prolog.PrologProgrammer;
import org.prolobjectlink.prolog.PrologProvider;
import org.prolobjectlink.prolog.jpl.swi.SwiPrologEngine;

public class SwiPrologDatabaseEngine extends SwiPrologEngine implements PrologDatabaseEngine {

	SwiPrologDatabaseEngine() {
		super(new SwiPrologDatabaseProvider());
	}

	SwiPrologDatabaseEngine(PrologProvider provider) {
		super(provider);
	}

	SwiPrologDatabaseEngine(PrologProvider provider, String file) {
		super(provider, file);
	}

	public PrologProgrammer getProgrammer() {
		return new SwiPrologProgrammer(getProvider());
	}

}
