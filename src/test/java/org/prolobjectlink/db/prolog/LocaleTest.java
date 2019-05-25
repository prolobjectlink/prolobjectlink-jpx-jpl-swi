/*-
 * #%L
 * prolobjectlink-jpx-jpl-swi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
