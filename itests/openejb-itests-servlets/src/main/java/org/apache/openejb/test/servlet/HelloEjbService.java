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
package org.apache.openejb.test.servlet;

import jakarta.ejb.Stateless;
import jakarta.jws.WebService;

@WebService(
    portName = "HelloEjbPort",
    serviceName = "HelloEjbService",
    targetNamespace = "http://examples.org/wsdl",
    endpointInterface = "org.apache.openejb.test.servlet.HelloEjb"
)
@Stateless(name = "TestHelloEjbService")
public class HelloEjbService implements HelloEjb {
    public String hello(String name) {
        if (name == null) name = "World";
        return "Hello " + name + " from EJB Webservice!";
    }
}
