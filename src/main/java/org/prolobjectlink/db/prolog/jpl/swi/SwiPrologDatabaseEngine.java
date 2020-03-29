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

import org.prolobjectlink.db.ObjectConverter;
import org.prolobjectlink.db.prolog.PrologDatabaseEngine;
import org.prolobjectlink.db.prolog.PrologObjectConverter;
import org.prolobjectlink.db.prolog.PrologProgrammer;

import io.github.prolobjectlink.prolog.PrologProvider;
import io.github.prolobjectlink.prolog.PrologTerm;
import io.github.prolobjectlink.prolog.jpl.swi.SwiPrologEngine;

public class SwiPrologDatabaseEngine extends SwiPrologEngine implements PrologDatabaseEngine {

	private final ObjectConverter<PrologTerm> converter;

	SwiPrologDatabaseEngine() {
		super(new SwiPrologDatabaseProvider());
		converter = new PrologObjectConverter(provider);
	}

	SwiPrologDatabaseEngine(PrologProvider provider) {
		super(provider);
		converter = new PrologObjectConverter(provider);
	}

	SwiPrologDatabaseEngine(PrologProvider provider, String file) {
		super(provider, file);
		converter = new PrologObjectConverter(provider);
	}

	public boolean unify(Object x, Object y) {
		PrologTerm xt = converter.toTerm(x);
		PrologTerm yt = converter.toTerm(y);
		return unify(xt, yt);
	}

	public PrologProgrammer getProgrammer() {
		return new SwiPrologProgrammer(getProvider());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((converter == null) ? 0 : converter.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SwiPrologDatabaseEngine other = (SwiPrologDatabaseEngine) obj;
		if (converter == null) {
			if (other.converter != null)
				return false;
		} else if (!converter.equals(other.converter)) {
			return false;
		}
		return true;
	}

}
