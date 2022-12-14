/**
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.openejb.test.entity.cmp;

import jakarta.ejb.EJBHome;
import jakarta.ejb.CreateException;
import jakarta.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.Collection;

public interface UnknownCmpHome extends EJBHome {
    UnknownCmpObject createObject(String name) throws CreateException, RemoteException;

    UnknownCmpObject findByPrimaryKey(Object primarykey) throws FinderException, RemoteException;

    Collection findEmptyCollection() throws FinderException, RemoteException;

    Collection findByLastName(String lastName) throws FinderException, RemoteException;

    public int sum(int x, int y) throws RemoteException;
}
