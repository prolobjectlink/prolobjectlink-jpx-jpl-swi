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
package io.github.prolobjectlink;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.github.prolobjectlink.GraphEdge;
import io.github.prolobjectlink.db.DatabaseClass;
import io.github.prolobjectlink.db.DatabaseFunction;
import io.github.prolobjectlink.db.DatabaseSequence;
import io.github.prolobjectlink.db.DatabaseUser;
import io.github.prolobjectlink.db.RelationalGraph;
import io.github.prolobjectlink.domain.model.Address;
import io.github.prolobjectlink.domain.model.Department;
import io.github.prolobjectlink.domain.model.Employee;
import io.github.prolobjectlink.domain.model.Person;

public class DatabaseSchemaTest extends BaseTest {

	@Test
	public void testGenerate() {
		assertFalse(hschema.generate().isEmpty());
		assertFalse(rschema.generate().isEmpty());
	}

	@Test
	public void testCompile() {
		assertEquals(4, hschema.compile().size());
		assertEquals(4, rschema.compile().size());
	}

	@Test
	public void testAddClassClassOfQ() {
		DatabaseClass dbc = rdb.getSchema().addClass(Person.class, "");
		assertEquals(1, rdb.getSchema().countClasses());
		assertNotNull(dbc);
	}

	@Test
	public void testAddClassString() {
		DatabaseClass dbc = rdb.getSchema().addClass("Person", "");
		assertEquals(1, rdb.getSchema().countClasses());
		assertNotNull(dbc);
	}

	@Test
	public void testAddClassStringDatabaseClass() {
		DatabaseClass dbp = rdb.getSchema().addClass("Person", "");
		DatabaseClass dbe = rdb.getSchema().addClass("Employee", "", dbp);
		assertEquals(2, rdb.getSchema().countClasses());
		dbp.isSuperClassOf(dbe);
		dbe.isSubClassOf(dbp);
	}

	@Test
	public void testAddClassStringDatabaseClassArray() {
		DatabaseClass dbp = rdb.getSchema().addClass("Person", "");
		DatabaseClass dbe = rdb.getSchema().addClass("Employee", "", dbp);
		DatabaseClass dbm = rdb.getSchema().addClass("Manager", "", dbp, dbe);
		assertEquals(3, rdb.getSchema().countClasses());
		dbp.isSuperClassOf(dbm);
		dbe.isSuperClassOf(dbm);
		dbm.isSubClassOf(dbp);
		dbm.isSubClassOf(dbe);
	}

	@Test
	public void testAddAbstractClassClassOfQ() {
		DatabaseClass dbp = rdb.getSchema().addAbstractClass(Person.class, "");
		assertTrue(dbp.isAbstract());
		assertNotNull(dbp);
	}

	@Test
	public void testAddAbstractClassString() {
		DatabaseClass dbp = rdb.getSchema().addAbstractClass("Person", "");
		assertTrue(dbp.isAbstract());
		assertNotNull(dbp);
	}

	@Test
	public void testAddAbstractClassStringDatabaseClass() {
		DatabaseClass dbp = rdb.getSchema().addAbstractClass("Person", "");
		DatabaseClass dbe = rdb.getSchema().addAbstractClass("Employee", "", dbp);
		assertTrue(dbp.isAbstract());
		assertTrue(dbe.isAbstract());
		dbp.isSuperClassOf(dbe);
		dbe.isSubClassOf(dbp);
	}

	@Test
	public void testAddAbstractClassStringDatabaseClassArray() {
		DatabaseClass dbp = rdb.getSchema().addAbstractClass("Person", "");
		DatabaseClass dbe = rdb.getSchema().addAbstractClass("Employee", "", dbp);
		DatabaseClass dbm = rdb.getSchema().addAbstractClass("Manager", "", dbp, dbe);
		assertTrue(dbp.isAbstract());
		assertTrue(dbe.isAbstract());
		assertTrue(dbm.isAbstract());
		dbp.isSuperClassOf(dbm);
		dbe.isSuperClassOf(dbm);
		dbm.isSubClassOf(dbp);
		dbm.isSubClassOf(dbe);
	}

	@Test
	public void testRemoveClass() {
		assertFalse(rdb.getSchema().containsClass("Person"));
		rdb.getSchema().addClass("Person", "");
		assertTrue(rdb.getSchema().containsClass("Person"));
		rdb.getSchema().removeClass("Person");
		assertFalse(rdb.getSchema().containsClass("Person"));
		assertEquals(0, rdb.getSchema().countClasses());
	}

	@Test
	public void testContainsClass() {
		assertFalse(rdb.getSchema().containsClass("Person"));
		rdb.getSchema().addClass("Person", "");
		assertTrue(rdb.getSchema().containsClass("Person"));
		assertEquals(1, rdb.getSchema().countClasses());
	}

	@Test
	public void testGetClassClassOfQ() {
		assertEquals(null, rdb.getSchema().getClass(Person.class));
		DatabaseClass c = rdb.getSchema().addClass(Person.class, "");
		assertEquals(c, rdb.getSchema().getClass(Person.class));
		assertEquals(1, rdb.getSchema().countClasses());
	}

	@Test
	public void testGetClassString() {
		assertEquals(null, rdb.getSchema().getClass("Person"));
		DatabaseClass c = rdb.getSchema().addClass("Person", "");
		assertEquals(c, rdb.getSchema().getClass("Person"));
		assertEquals(1, rdb.getSchema().countClasses());
	}

	@Test
	public void testGetOrAddClassString() {

		assertEquals(0, rdb.getSchema().countClasses());
		DatabaseClass dbc = rdb.getSchema().getOrAddClass("Person");
		assertEquals(1, rdb.getSchema().countClasses());
		assertNotNull(dbc);
		dbc = rdb.getSchema().getOrAddClass("Person");
		assertEquals(1, rdb.getSchema().countClasses());
		assertNotNull(dbc);

	}

	@Test
	public void testGetOrAddClassStringDatabaseClass() {

		assertEquals(0, rdb.getSchema().countClasses());
		DatabaseClass dbp = rdb.getSchema().getOrAddClass("Person");
		DatabaseClass dbe = rdb.getSchema().getOrAddClass("Employee", dbp);
		assertEquals(2, rdb.getSchema().countClasses());
		dbp.isSuperClassOf(dbe);
		dbe.isSubClassOf(dbp);
		dbe = rdb.getSchema().getOrAddClass("Employee", dbp);
		assertEquals(2, rdb.getSchema().countClasses());

	}

	@Test
	public void testGetOrAddClassStringDatabaseClassArray() {

		DatabaseClass dbp = rdb.getSchema().getOrAddClass("Person");
		DatabaseClass dbe = rdb.getSchema().getOrAddClass("Employee", dbp);
		DatabaseClass dbm = rdb.getSchema().getOrAddClass("Manager", dbp, dbe);
		assertEquals(3, rdb.getSchema().countClasses());
		dbp.isSuperClassOf(dbm);
		dbe.isSuperClassOf(dbm);
		dbm.isSubClassOf(dbp);
		dbm.isSubClassOf(dbe);
		dbm = rdb.getSchema().getOrAddClass("Manager", dbp, dbe);
		assertEquals(3, rdb.getSchema().countClasses());

	}

	@Test
	public void testGetClasses() {
		rdb.getSchema().addClass(Person.class, "");
		rdb.getSchema().addClass(Address.class, "");
		rdb.getSchema().addClass(Employee.class, "");
		rdb.getSchema().addClass(Department.class, "");
		assertEquals(4, rdb.getSchema().getClasses().size());
	}

	@Test
	public void testCountClasses() {
		rdb.getSchema().addClass(Person.class, "");
		rdb.getSchema().addClass(Address.class, "");
		rdb.getSchema().addClass(Employee.class, "");
		rdb.getSchema().addClass(Department.class, "");
		assertEquals(4, rdb.getSchema().countClasses());
	}

	@Test
	public void testAddFunction() {
		DatabaseFunction f = rdb.getSchema().addFunction("sqrt", "");
		assertEquals(1, rdb.getSchema().countFunctions());
		assertNotNull(f);
	}

	@Test
	public void testRemoveFunction() {
		assertFalse(rdb.getSchema().containsFunction("sqrt"));
		DatabaseFunction dbf = rdb.getSchema().addFunction("sqrt", "");
		assertEquals(dbf, rdb.getSchema().getFunction("sqrt"));
		assertTrue(rdb.getSchema().containsFunction("sqrt"));
		rdb.getSchema().removeFunctions("sqrt");
		assertFalse(rdb.getSchema().containsFunction("sqrt"));
		assertEquals(0, rdb.getSchema().countFunctions());
	}

	@Test
	public void containsFunction() {
		assertFalse(rdb.getSchema().containsFunction("sqrt"));
		DatabaseFunction dbf = rdb.getSchema().addFunction("sqrt", "");
		assertEquals(dbf, rdb.getSchema().getFunction("sqrt"));
		assertTrue(rdb.getSchema().containsFunction("sqrt"));
	}

	@Test
	public void testGetFunctions() {
		rdb.getSchema().addFunction("sin", "");
		rdb.getSchema().addFunction("cos", "");
		rdb.getSchema().addFunction("tan", "");
		rdb.getSchema().addFunction("sqrt", "");
		assertEquals(4, rdb.getSchema().getFunctions().size());
	}

	@Test
	public void testGetFunction() {
		assertEquals(null, rdb.getSchema().getFunction("sqrt"));
		DatabaseFunction dbf = rdb.getSchema().addFunction("sqrt", "");
		assertEquals(dbf, rdb.getSchema().getFunction("sqrt"));
	}

	@Test
	public void testCountFunctions() {
		DatabaseFunction f = rdb.getSchema().addFunction("sqrt", "");
		assertEquals(1, rdb.getSchema().countFunctions());
		assertNotNull(f);
	}

	@Test
	public void testAddSequence() {
		DatabaseSequence s = rdb.getSchema().addSequence("person_sequence", "", Person.class, 1);
		assertEquals(1, rdb.getSchema().countSequences());
		assertNotNull(s);
	}

	@Test
	public void testRemoveSequence() {
		assertFalse(rdb.getSchema().containsSequence("person_sequence"));
		DatabaseSequence dbs = rdb.getSchema().addSequence("person_sequence", "", Person.class, 1);
		assertEquals(dbs, rdb.getSchema().getSequence("person_sequence"));
		assertTrue(rdb.getSchema().containsSequence("person_sequence"));
		rdb.getSchema().removeSequence("person_sequence");
		assertFalse(rdb.getSchema().containsSequence("person_sequence"));
		assertEquals(0, rdb.getSchema().countSequences());
	}

	@Test
	public void testContainsSequence() {
		assertFalse(rdb.getSchema().containsSequence("person_sequence"));
		DatabaseSequence dbs = rdb.getSchema().addSequence("person_sequence", "", Person.class, 1);
		assertEquals(dbs, rdb.getSchema().getSequence("person_sequence"));
		assertTrue(rdb.getSchema().containsSequence("person_sequence"));
	}

	@Test
	public void testGetSequences() {
		rdb.getSchema().addSequence("person_sequence", "", Person.class, 1);
		rdb.getSchema().addSequence("address_sequence", "", Person.class, 1);
		rdb.getSchema().addSequence("employee_sequence", "", Person.class, 1);
		rdb.getSchema().addSequence("department_sequence", "", Person.class, 1);
		assertEquals(4, rdb.getSchema().getSequences().size());
	}

	@Test
	public void testGetSequence() {
		assertEquals(null, rdb.getSchema().getSequence("person_sequence"));
		DatabaseSequence dbs = rdb.getSchema().addSequence("person_sequence", "", Person.class, 1);
		assertEquals(dbs, rdb.getSchema().getSequence("person_sequence"));
	}

	@Test
	public void testCountSequences() {
		DatabaseSequence s = rdb.getSchema().addSequence("person_sequence", "", Person.class, 1);
		assertEquals(1, rdb.getSchema().countSequences());
		assertNotNull(s);
	}

	@Test
	public void testAddUser() {
		DatabaseUser u = rdb.getSchema().addUser("user", "pass");
		assertEquals(2, rdb.getSchema().countUsers());
		assertNotNull(u);
	}

	@Test
	public void testRemoveUser() {
		assertFalse(rdb.getSchema().containsSequence("user"));
		DatabaseUser u = rdb.getSchema().addUser("user", "pass");
		assertEquals(u, rdb.getSchema().getUser("user"));
		assertEquals(2, rdb.getSchema().countUsers());
		assertTrue(rdb.getSchema().containsUser("user"));
		rdb.getSchema().removeUser("user");
		assertFalse(rdb.getSchema().containsUser("user"));
		assertEquals(1, rdb.getSchema().countUsers());
	}

	@Test
	public void testContainsUser() {
		assertFalse(rdb.getSchema().containsSequence("user"));
		DatabaseUser u = rdb.getSchema().addUser("user", "pass");
		assertEquals(u, rdb.getSchema().getUser("user"));
		assertTrue(rdb.getSchema().containsUser("user"));
	}

	@Test
	public void testGetUsers() {
		rdb.getSchema().addUser("user1", "pass1");
		rdb.getSchema().addUser("user2", "pass2");
		rdb.getSchema().addUser("user3", "pass3");
		rdb.getSchema().addUser("user4", "pass4");
		assertEquals(5, rdb.getSchema().getUsers().size());
	}

	@Test
	public void testGetUser() {
		assertEquals(null, rdb.getSchema().getUser("user"));
		DatabaseUser u = rdb.getSchema().addUser("user", "pass");
		assertEquals(u, rdb.getSchema().getUser("user"));
	}

	@Test
	public void testCountUsers() {
		DatabaseUser s = rdb.getSchema().addUser("user", "pass");
		assertEquals(2, rdb.getSchema().countUsers());
		assertNotNull(s);
	}

	@Test
	public void testGetVersion() {
		assertEquals(0, rdb.getSchema().getVersion());
	}

	@Test
	public void testGetLocation() {
		assertEquals("db/pdb/relational" + File.separator + "test" + File.separator + "database",
				rdb.getLocation());
	}

	@Test
	public void testGetProvider() {
		assertNotNull(rdb.getSchema().getProvider());
	}

	@Test
	public void testGetGraph() {

		RelationalGraph<DatabaseClass, DatabaseClass> hg = hschema.getGraph();
		System.out.println(hg);
		assertEquals(hg, hschema.getGraph());
		assertEquals(4, hg.countVertices());

		for (GraphEdge<DatabaseClass> edge : hschema.getGraph().getEdges()) {
			System.out.println(edge);
		}

		RelationalGraph<DatabaseClass, DatabaseClass> rg = rschema.getGraph();
		System.out.println(rg);
		assertEquals(rg, rschema.getGraph());
		assertEquals(4, rg.countVertices());
		// assertEquals(4, rg.countEdges());

		for (GraphEdge<DatabaseClass> edge : rschema.getGraph().getEdges()) {
			System.out.println(edge.getFrom() + "--->" + edge.getTo());
		}

	}

	@Test
	public void testClear() {

		// assertFalse(hschema.getUsers().isEmpty());
		assertFalse(hschema.getClasses().isEmpty());
		assertFalse(hschema.getFunctions().isEmpty());
		assertFalse(hschema.getSequences().isEmpty());
		hschema.clear();
		// assertTrue(hschema.getUsers().isEmpty());
		assertTrue(hschema.getClasses().isEmpty());
		assertTrue(hschema.getFunctions().isEmpty());
		assertTrue(hschema.getSequences().isEmpty());

	}

	@Test
	public void testLoad() {

		assertEquals(2, hdb.getSchema().load().countUsers());
		assertEquals(4, hdb.getSchema().load().countClasses());
		assertEquals(4, hdb.getSchema().load().countFunctions());
		assertEquals(4, hdb.getSchema().load().countSequences());

		assertEquals(2, rdb.getSchema().load().countUsers());
		assertEquals(4, rdb.getSchema().load().countClasses());
		assertEquals(4, rdb.getSchema().load().countFunctions());
		assertEquals(4, rdb.getSchema().load().countSequences());

	}

	@Test
	public void testFlush() {

		hschema.flush();
		rschema.flush();

		assertEquals(2, hdb.getSchema().load().countUsers());
		assertEquals(4, hdb.getSchema().load().countClasses());
		assertEquals(4, hdb.getSchema().load().countFunctions());
		assertEquals(4, hdb.getSchema().load().countSequences());

		assertEquals(2, rdb.getSchema().load().countUsers());
		assertEquals(4, rdb.getSchema().load().countClasses());
		assertEquals(4, rdb.getSchema().load().countFunctions());
		assertEquals(4, rdb.getSchema().load().countSequences());

	}

}
