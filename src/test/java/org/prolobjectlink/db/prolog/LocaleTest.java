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
package org.prolobjectlink.db.prolog;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;
import org.prolobjectlink.BaseTest;

public class LocaleTest extends BaseTest {

	private Locale locale = Locale.getDefault();
	private PrologLocale prologLocale = new PrologLocale();

	@Test
	public final void testGetJavaUtilLocale() {
		assertEquals(locale, prologLocale.getJavaUtilLocale());
	}

	@Test
	public final void testGetLanguage() {
		assertEquals(locale.getLanguage(), prologLocale.getLanguage());
	}

	@Test
	public final void testGetCountry() {
		assertEquals(locale.getCountry(), prologLocale.getCountry());
	}

	@Test
	public final void testGetVariant() {
		assertEquals(locale.getVariant(), prologLocale.getVariant());
	}

}
