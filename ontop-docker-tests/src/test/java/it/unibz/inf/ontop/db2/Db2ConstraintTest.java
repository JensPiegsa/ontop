package it.unibz.inf.ontop.db2;

/*
 * #%L
 * ontop-test
 * %%
 * Copyright (C) 2009 - 2014 Free University of Bozen-Bolzano
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

import it.unibz.inf.ontop.AbstractConstraintTest;

public class Db2ConstraintTest extends AbstractConstraintTest {

	public Db2ConstraintTest(String method) {
		super(method);
	}

	@Override
	protected String getConnectionPassword() {
		return "db2inst1-pwd";
	}

	@Override
	protected String getConnectionString() {
		return "jdbc:db2://tom.inf.unibz.it:50000/sbooks";
	}

	@Override
	protected String getConnectionUsername() {
		return "db2inst1";
	}

	@Override
	protected String getDriverName() {
		return "com.ibm.db2.jcc.DB2Driver";
	}
}
