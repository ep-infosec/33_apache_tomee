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
package org.apache.openejb.deployment.entity.cmp.cmr.manytomany;

import jakarta.ejb.CreateException;
import jakarta.ejb.EJBLocalHome;
import jakarta.ejb.FinderException;


/**
 * @version $Revision$ $Date$
 */
public interface BLocalHome extends EJBLocalHome {

    // Create
    public BLocal create(Integer field1) throws CreateException;

    // Finder
    public BLocal findByPrimaryKey(Integer primaryKey) throws FinderException;

}
