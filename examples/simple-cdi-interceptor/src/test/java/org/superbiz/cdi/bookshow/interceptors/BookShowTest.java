/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.superbiz.cdi.bookshow.interceptors;

import junit.framework.TestCase;
import org.superbiz.cdi.bookshow.beans.BookShow;

import jakarta.ejb.EJB;
import jakarta.ejb.embeddable.EJBContainer;

public class BookShowTest extends TestCase {

    @EJB
    private BookShow bookForAShowBean;
    EJBContainer ejbContainer;

    /**
     * Bootstrap the Embedded EJB Container
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        ejbContainer = EJBContainer.createEJBContainer();
        ejbContainer.getContext().bind("inject", this);
    }

    /**
     * Test basic interception
     */
    public void testMethodShouldBeIntercepted() {

        bookForAShowBean.getMoviesList();

    }

}
