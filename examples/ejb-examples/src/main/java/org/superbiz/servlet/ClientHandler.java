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
package org.superbiz.servlet;

import jakarta.xml.ws.handler.Handler;
import jakarta.xml.ws.handler.MessageContext;

public class ClientHandler implements Handler {

    public boolean handleMessage(MessageContext messageContext) {
        WebserviceServlet.write("    ClientHandler handleMessage");
        return true;
    }

    public void close(MessageContext messageContext) {
        WebserviceServlet.write("    ClientHandler close");
    }

    public boolean handleFault(MessageContext messageContext) {
        WebserviceServlet.write("    ClientHandler handleFault");
        return true;
    }
}