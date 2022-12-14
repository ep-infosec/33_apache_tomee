/*
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

package org.apache.openejb.core;

import org.apache.openejb.ProxyInfo;
import org.apache.openejb.core.ivm.IntraVmServer;
import org.apache.openejb.spi.ApplicationServer;

import jakarta.ejb.EJBHome;
import jakarta.ejb.EJBMetaData;
import jakarta.ejb.EJBObject;
import jakarta.ejb.Handle;
import jakarta.ejb.HomeHandle;

public class ServerFederation implements ApplicationServer {
    private static final IntraVmServer localServer = new IntraVmServer();

    private static final ThreadLocal<ApplicationServer> applicationServer = new ThreadLocal<ApplicationServer>();

    public Handle getHandle(final ProxyInfo proxyInfo) {
        return getApplicationServer().getHandle(proxyInfo);
    }

    public EJBMetaData getEJBMetaData(final ProxyInfo proxyInfo) {
        return getApplicationServer().getEJBMetaData(proxyInfo);
    }

    public HomeHandle getHomeHandle(final ProxyInfo proxyInfo) {
        return getApplicationServer().getHomeHandle(proxyInfo);
    }

    public EJBObject getEJBObject(final ProxyInfo proxyInfo) {
        return getApplicationServer().getEJBObject(proxyInfo);
    }

    public EJBHome getEJBHome(final ProxyInfo proxyInfo) {
        return getApplicationServer().getEJBHome(proxyInfo);
    }

    public Object getBusinessObject(final ProxyInfo proxyInfo) {
        return getApplicationServer().getBusinessObject(proxyInfo);
    }

    public static void setApplicationServer(final ApplicationServer server) {
        // todo why do we restrict null?  This makes call to setApplicationServer non symetrical. Throw an exception?
        if (server != null) {
            applicationServer.set(server);
        }
    }

    public static ApplicationServer getApplicationServer() {
        final ApplicationServer server = applicationServer.get();
        if (server == null) {
            // todo: consider making this the thread local intialValue
            return localServer;
        }
        return server;
    }

}

