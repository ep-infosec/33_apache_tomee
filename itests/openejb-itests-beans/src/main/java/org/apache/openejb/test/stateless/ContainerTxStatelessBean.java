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
package org.apache.openejb.test.stateless;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.ejb.CreateException;
import jakarta.ejb.EJBException;
import jakarta.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import jakarta.transaction.RollbackException;

import org.apache.openejb.test.object.Account;

public class ContainerTxStatelessBean implements jakarta.ejb.SessionBean {


    private String name;
    private SessionContext ejbContext;
    private InitialContext jndiContext;
    public final String jndiDatabaseEntry = "jdbc/stateless/containerManagedTransaction/database";


    //=============================
    // Home interface methods
    //    

    //    
    // Home interface methods
    //=============================


    //=============================
    // Remote interface methods
    //    

    public String txMandatoryMethod(final String message) {
        return message;
    }

    public String txNeverMethod(final String message) {
        return message;
    }

    public String txNotSupportedMethod(final String message) {
        return message;
    }

    public String txRequiredMethod(final String message) {
        return message;
    }

    public String txRequiresNewMethod(final String message) {
        return message;
    }

    public String txSupportsMethod(final String message) {
        return message;
    }

    public void openAccount(final Account acct, final Boolean rollback) throws RollbackException {

        try {
            final DataSource ds = (DataSource) jndiContext.lookup("java:comp/env/database");
            final Connection con = ds.getConnection();

            try {
                /*[2] Update the table */
                final PreparedStatement stmt = con.prepareStatement("insert into Account (SSN, First_name, Last_name, Balance) values (?,?,?,?)");
                try {
                    stmt.setString(1, acct.getSsn());
                    stmt.setString(2, acct.getFirstName());
                    stmt.setString(3, acct.getLastName());
                    stmt.setInt(4, acct.getBalance());
                    stmt.executeUpdate();
                } finally {
                    stmt.close();
                }
            } finally {
                con.close();
            }
        } catch (final Exception e) {
            //throw new RemoteException("[Bean] "+e.getClass().getName()+" : "+e.getMessage());
        }
    }

    public Account retreiveAccount(final String ssn) {
        final Account acct = new Account();
        try {
            final DataSource ds = (DataSource) jndiContext.lookup("java:comp/env/database");
            final Connection con = ds.getConnection();

            try {
                final PreparedStatement stmt = con.prepareStatement("select * from Account where SSN = ?");
                try {
                    stmt.setString(1, ssn);
                    final ResultSet rs = stmt.executeQuery();
                    if (!rs.next()) return null;

                    acct.setSsn(rs.getString(1));
                    acct.setFirstName(rs.getString(2));
                    acct.setLastName(rs.getString(3));
                    acct.setBalance(rs.getInt(4));
                } finally {
                    stmt.close();
                }
            } finally {
                con.close();
            }
        } catch (final Exception e) {
            //throw new RemoteException("[Bean] "+e.getClass().getName()+" : "+e.getMessage());
        }
        return acct;
    }


    //    
    // Remote interface methods
    //=============================


    //=================================
    // SessionBean interface methods
    //    

    /**
     * @throws jakarta.ejb.CreateException
     */
    public void ejbCreate() throws jakarta.ejb.CreateException {
        try {
            jndiContext = new InitialContext();
        } catch (final Exception e) {
            throw new CreateException("Can not get the initial context: " + e.getMessage());
        }
    }

    /**
     * Set the associated session context. The container calls this method
     * after the instance creation.
     */
    public void setSessionContext(final SessionContext ctx) throws EJBException, RemoteException {
        ejbContext = ctx;
    }

    /**
     * A container invokes this method before it ends the life of the session
     * object. This happens as a result of a client's invoking a remove
     * operation, or when a container decides to terminate the session object
     * after a timeout.
     */
    public void ejbRemove() throws EJBException, RemoteException {
    }

    /**
     * The activate method is called when the instance is activated
     * from its "passive" state. The instance should acquire any resource
     * that it has released earlier in the ejbPassivate() method.
     */
    public void ejbActivate() throws EJBException, RemoteException {
    }

    /**
     * The passivate method is called before the instance enters
     * the "passive" state. The instance should release any resources that
     * it can re-acquire later in the ejbActivate() method.
     */
    public void ejbPassivate() throws EJBException, RemoteException {
    }

    //
    // SessionBean interface methods
    //==================================
    public String remove(final String arg) {
        return arg;
    }


}
