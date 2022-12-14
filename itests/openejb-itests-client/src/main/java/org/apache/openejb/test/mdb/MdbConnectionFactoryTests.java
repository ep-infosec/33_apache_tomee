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
package org.apache.openejb.test.mdb;

import jakarta.jms.Connection;
import jakarta.jms.Destination;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageProducer;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Session;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class MdbConnectionFactoryTests extends MdbTestClient {
    public MdbConnectionFactoryTests() {
        super("ConnectionFactory.");
    }

    public void test01_createConnection() throws Exception {
        final Connection connection = createConnection();
        try {
            assertNotNull("Jms connection is null.", connection);
        } finally {
            MdbUtil.close(connection);
        }
    }

    public void test02_directRpc() throws Exception {
        final Connection connection = createConnection();
        Session session = null;
        MessageProducer producer = null;
        MessageConsumer consumer = null;
        try {

            // create request
            final Map<String, Object> request = new TreeMap<String, Object>();
            request.put("method", "businessMethod(java.lang.String)");
            request.put("args", new Object[]{"cheese"});

            // initialize session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final Destination requestQueue = session.createQueue("BasicMdb");
            final Destination responseQueue = session.createTemporaryQueue();

            // Create a request messages
            final ObjectMessage requestMessage = session.createObjectMessage();
            requestMessage.setJMSReplyTo(responseQueue);
            requestMessage.setObject((Serializable) request);

            // Send the request message
            producer = session.createProducer(requestQueue);
            producer.send(requestMessage);

//            System.out.println("\n" + "***************************************\n" +
//                    "Sent request message: " + requestMessage + "\n" +
//                    "         request map: " + request + "\n" +
//                    "            to queue: " + requestQueue + "\n" +
//                    "***************************************\n\n");

            // create consumer
            consumer = session.createConsumer(responseQueue);
//            System.out.println("\n" + "***************************************\n" +
//                    "Listening for response at : " + responseQueue + "\n" +
//                    "***************************************\n\n");

            // wait for response mesage
            final Message message = consumer.receive(1000);

            // verify message
            assertNotNull("Did not get a response message", message);
            assertTrue("Response message is not an ObjectMessage", message instanceof ObjectMessage);
            final ObjectMessage responseMessage = (ObjectMessage) message;
            final Serializable object = responseMessage.getObject();
            assertNotNull("Response ObjectMessage contains a null object");
            assertTrue("Response ObjectMessage does not contain an instance of Map", object instanceof Map);
            final Map response = (Map) object;

            // process results
            if (response.containsKey("exception")) {
                throw (Exception) response.get("return");
            }
            final String returnValue = (String) response.get("return");
            assertEquals("eseehc", returnValue);
        } finally {
            MdbUtil.close(producer);
            MdbUtil.close(session);
            MdbUtil.close(connection);
        }
    }

    public void test03_proxy() throws Exception {
        final BasicMdbObject basicMdbObject = MdbProxy.newProxyInstance(BasicMdbObject.class, connectionFactory, "BasicMdb");
        try {
            final String returnValue = basicMdbObject.businessMethod("blah");
            assertEquals("halb", returnValue);
        } finally {
            MdbProxy.destroyProxy(basicMdbObject);
        }
    }
}
