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
package org.superbiz.cdi.qualifier;

import static org.junit.Assert.assertEquals;

import jakarta.ejb.embeddable.EJBContainer;
import jakarta.inject.Inject;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PaymentTest {

    private static EJBContainer container;

    @Inject
    @PaymentQualifier(type=PaymentType.CREDITCARD)
    private Payment paymentCreditCard;
    
    @Inject
    @PaymentQualifier(type=PaymentType.CASH)
    private Payment paymentCash;

    @BeforeClass
    public static void start() {
        container = EJBContainer.createEJBContainer();
    }

    @Before
    public void setUp() throws Exception {
        container.getContext().bind("inject", this);
    }

    @Test
    public void mustReturnCreditCard() {
    	
        assertEquals(paymentCreditCard.pay(), "creditCard");
    }
    
    @Test
    public void mustReturnCash() {
    	
        assertEquals(paymentCash.pay(), "cash");
    }

    @AfterClass
    public static void stop() {
        container.close();
    }
}
