/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.openejb.test.beans;

import jakarta.ejb.EntityContext;
import jakarta.ejb.FinderException;
import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeBean implements jakarta.ejb.EntityBean {
    int id;
    String lastName;
    String firstName;

    EntityContext ejbContext;

    public int ejbHomeSum(final int one, final int two) {
        return one + two;
    }

    public Integer ejbFindByPrimaryKey(final Integer primaryKey)
        throws jakarta.ejb.FinderException {
        boolean found = false;
        try {
            final InitialContext jndiContext = new InitialContext();

            final javax.sql.DataSource ds = (javax.sql.DataSource) jndiContext.lookup("java:comp/env/jdbc/orders");

            final Connection con = ds.getConnection();

            try {
                final PreparedStatement stmt = con.prepareStatement("select * from Employees where EmployeeID = ?");
                try {
                    stmt.setInt(1, primaryKey.intValue());
                    final ResultSet rs = stmt.executeQuery();
                    found = rs.next();
                } finally {
                    stmt.close();
                }
            } finally {
                con.close();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            throw new FinderException("FindByPrimaryKey failed");
        }

        if (found)
            return primaryKey;
        else
            throw new jakarta.ejb.ObjectNotFoundException();


    }

    public java.util.Collection ejbFindAll() throws FinderException {
        try {
            final InitialContext jndiContext = new InitialContext();

            final javax.sql.DataSource ds = (javax.sql.DataSource) jndiContext.lookup("java:comp/env/jdbc/orders");

            final Connection con = ds.getConnection();

            java.util.Vector keys;
            try {
                final Statement stmt = con.createStatement();
                try {
                    final ResultSet rs = stmt.executeQuery("select EmployeeID from Employees");
                    keys = new java.util.Vector();
                    while (rs.next()) {
                        keys.addElement(new Integer(rs.getInt("EmployeeID")));
                    }
                } finally {
                    stmt.close();
                }
            } finally {
                con.close();
            }
            return keys;
        } catch (final Exception e) {
            e.printStackTrace();
            throw new FinderException("FindAll failed");
        }
    }

    public Integer ejbCreate(final String fname, final String lname)
        throws jakarta.ejb.CreateException {
        try {
            lastName = lname;
            firstName = fname;

            final InitialContext jndiContext = new InitialContext();

            final javax.sql.DataSource ds = (javax.sql.DataSource) jndiContext.lookup("java:comp/env/jdbc/orders");

            final Connection con = ds.getConnection();

            try {
                PreparedStatement stmt = con.prepareStatement("insert into Employees (FirstName, LastName) values (?,?)");
                try {
                    stmt.setString(1, firstName);
                    stmt.setString(2, lastName);
                    stmt.executeUpdate();

                    stmt = con.prepareStatement("select EmployeeID from Employees where FirstName = ? AND LastName = ?");
                    stmt.setString(1, firstName);
                    stmt.setString(2, lastName);
                    final ResultSet set = stmt.executeQuery();
                    while (set.next())
                        id = set.getInt("EmployeeID");
                } finally {
                    stmt.close();
                }
            } finally {
                con.close();
            }

            return new Integer(id);

        } catch (final Exception e) {
            e.printStackTrace();
            throw new jakarta.ejb.CreateException("can't create");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(final String lname) {
        lastName = lname;
    }

    public void setFirstName(final String fname) {
        firstName = fname;
    }

    public void ejbLoad() {
        try {
            final InitialContext jndiContext = new InitialContext();

            final javax.sql.DataSource ds = (javax.sql.DataSource) jndiContext.lookup("java:comp/env/jdbc/orders");

            final Connection con = ds.getConnection();
            try {
                final PreparedStatement stmt = con.prepareStatement("select * from Employees where EmployeeID = ?");
                try {
                    final Integer primaryKey = (Integer) ejbContext.getPrimaryKey();
                    stmt.setInt(1, primaryKey.intValue());
                    final ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        lastName = rs.getString("LastName");
                        firstName = rs.getString("FirstName");
                    }
                } finally {
                    stmt.close();
                }
            } finally {
                con.close();
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    public void ejbStore() {
        try {
            final InitialContext jndiContext = new InitialContext();

            final javax.sql.DataSource ds = (javax.sql.DataSource) jndiContext.lookup("java:comp/env/jdbc/orders");
            final Connection con = ds.getConnection();

            try {
                final PreparedStatement stmt = con.prepareStatement("update Employees set FirstName = ?, LastName = ? where EmployeeID = ?");
                try {
                    stmt.setString(1, firstName);
                    stmt.setString(2, lastName);
                    stmt.setInt(3, id);
                    stmt.execute();
                } finally {
                    stmt.close();
                }
            } finally {
                con.close();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }

    public void ejbRemove() {

        try {
            final InitialContext jndiContext = new InitialContext();

            final javax.sql.DataSource ds =
                (javax.sql.DataSource) jndiContext.lookup("java:comp/env/jdbc/orders");

            final Connection con = ds.getConnection();

            try {
                final PreparedStatement stmt = con.prepareStatement("delete from Employees where EmployeeID = ?");
                try {
                    final Integer primaryKey = (Integer) ejbContext.getPrimaryKey();
                    stmt.setInt(1, primaryKey.intValue());
                    stmt.executeUpdate();
                } finally {
                    stmt.close();
                }
            } finally {
                con.close();
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void setEntityContext(final jakarta.ejb.EntityContext cntx) {
        ejbContext = cntx;
    }

    public void unsetEntityContext() {
    }
}
    
        