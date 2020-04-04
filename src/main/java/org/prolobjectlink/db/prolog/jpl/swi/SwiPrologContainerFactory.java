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

import io.github.prolobjectlink.db.HierarchicalCache;
import io.github.prolobjectlink.db.etc.Settings;
import io.github.prolobjectlink.db.prolog.PrologContainerFactory;

public final class SwiPrologContainerFactory extends PrologContainerFactory {

	public SwiPrologContainerFactory(Settings settins) {
		super(settins, new SwiPrologDatabaseProvider());
	}

	public HierarchicalCache createHierarchicalCache() {
		return new SwiPrologHierarchicalCache(getProvider(), getSettings(), this);
	}

}
