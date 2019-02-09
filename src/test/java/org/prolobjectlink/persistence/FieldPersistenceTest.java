/*-
 * #%L
 * prolobjectlink-jpp-jpl-swi
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
package org.prolobjectlink.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.prolobjectlink.BaseTest;
import org.prolobjectlink.domain.classes.NonFieldClass;
import org.prolobjectlink.domain.classes.StaticFieldClass;
import org.prolobjectlink.domain.classes.StaticFinalFieldClass;
import org.prolobjectlink.domain.classes.TransientFieldClass;

public class FieldPersistenceTest extends BaseTest {

	@Test
	public final void testEmpty() {

		storage.getTransaction().begin();
		storage.insert(new NonFieldClass());
		assertTrue(storage.contains(NonFieldClass.class));
		assertTrue(storage.contains(new NonFieldClass()));
		assertEquals(new NonFieldClass(), storage.find(new NonFieldClass()));
		storage.delete(new NonFieldClass());
		assertFalse(storage.contains(NonFieldClass.class));
		assertFalse(storage.contains(new NonFieldClass()));
		storage.getTransaction().commit();
		storage.getTransaction().close();

	}

	@Test
	public final void testStatic() {

		storage.getTransaction().begin();
		storage.insert(new StaticFieldClass());
		assertTrue(storage.contains(StaticFieldClass.class));
		assertTrue(storage.contains(new StaticFieldClass()));
		assertEquals(new StaticFieldClass(), storage.find(new StaticFieldClass()));
		storage.delete(new StaticFieldClass());
		assertFalse(storage.contains(StaticFieldClass.class));
		assertFalse(storage.contains(new StaticFieldClass()));
		storage.getTransaction().commit();
		storage.getTransaction().close();

	}

	@Test
	public final void testStaticFinal() {

		storage.getTransaction().begin();
		storage.insert(new StaticFinalFieldClass());
		assertTrue(storage.contains(StaticFinalFieldClass.class));
		assertTrue(storage.contains(new StaticFinalFieldClass()));
		assertEquals(new StaticFinalFieldClass(), storage.find(new StaticFinalFieldClass()));
		storage.delete(new StaticFinalFieldClass());
		assertFalse(storage.contains(StaticFinalFieldClass.class));
		assertFalse(storage.contains(new StaticFinalFieldClass()));
		storage.getTransaction().commit();
		storage.getTransaction().close();

	}

	@Test
	public final void testTransient() {

		storage.getTransaction().begin();
		storage.insert(new TransientFieldClass());
		assertTrue(storage.contains(new TransientFieldClass()));
		assertEquals(new TransientFieldClass(), storage.find(new TransientFieldClass()));
		storage.delete(new TransientFieldClass());
		assertFalse(storage.contains(new TransientFieldClass()));
		storage.getTransaction().commit();
		storage.getTransaction().close();

	}

}
