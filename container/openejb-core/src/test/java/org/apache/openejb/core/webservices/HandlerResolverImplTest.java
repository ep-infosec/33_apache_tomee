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
package org.apache.openejb.core.webservices;

import junit.framework.TestCase;
import org.apache.openejb.assembler.classic.Assembler;
import org.apache.openejb.assembler.classic.HandlerChainInfo;
import org.apache.openejb.assembler.classic.WsBuilder;
import org.apache.openejb.config.ConfigurationFactory;
import org.apache.openejb.config.ReadDescriptors;
import org.apache.openejb.jee.HandlerChains;

import javax.naming.InitialContext;
import javax.xml.namespace.QName;
import jakarta.xml.ws.handler.Handler;
import jakarta.xml.ws.handler.PortInfo;
import java.net.URL;
import java.util.List;

public class HandlerResolverImplTest extends TestCase {

    public void setUp() throws Exception {
        new Assembler();
    }

    public void testBasic() throws Exception {
        final HandlerChains handlerChains = readHandlerChains("/handlers.xml");
        assertEquals(3, handlerChains.getHandlerChain().size());

        final List<HandlerChainInfo> handlerChainInfos = ConfigurationFactory.toHandlerChainInfo(handlerChains);
        final List<HandlerChainData> handlerChainDatas = WsBuilder.toHandlerChainData(handlerChainInfos, getClass().getClassLoader());
        final HandlerResolverImpl resolver = new HandlerResolverImpl(handlerChainDatas, null, new InitialContext());

        List<Handler> handlers = null;

        handlers = resolver.getHandlerChain(new TestPortInfo(null, null, null));
        assertEquals(3, handlers.size());
    }

    public void testServiceMatching() throws Exception {
        final HandlerChains handlerChains = readHandlerChains("/handlers_service.xml");
        assertEquals(3, handlerChains.getHandlerChain().size());

        final List<HandlerChainInfo> handlerChainInfos = ConfigurationFactory.toHandlerChainInfo(handlerChains);
        final List<HandlerChainData> handlerChainDatas = WsBuilder.toHandlerChainData(handlerChainInfos, getClass().getClassLoader());
        final HandlerResolverImpl resolver = new HandlerResolverImpl(handlerChainDatas, null, new InitialContext());

        List<Handler> handlers = null;

        handlers = resolver.getHandlerChain(new TestPortInfo(null, null, null));
        assertEquals(0, handlers.size());

        final QName serviceName1 = new QName("http://java.sun.com/xml/ns/javaee", "Bar");
        handlers = resolver.getHandlerChain(new TestPortInfo(null, null, serviceName1));
        assertEquals(1, handlers.size());

        final QName serviceName2 = new QName("http://java.sun.com/xml/ns/javaee", "Foo");
        handlers = resolver.getHandlerChain(new TestPortInfo(null, null, serviceName2));
        assertEquals(2, handlers.size());

        final QName serviceName3 = new QName("http://java.sun.com/xml/ns/javaee", "FooBar");
        handlers = resolver.getHandlerChain(new TestPortInfo(null, null, serviceName3));
        assertEquals(1, handlers.size());

        final QName serviceName4 = new QName("http://java.sun.com/xml/ns/javaee", "BarFoo");
        handlers = resolver.getHandlerChain(new TestPortInfo(null, null, serviceName4));
        assertEquals(0, handlers.size());
    }

    public void testBindingMatching() throws Exception {
        final HandlerChains handlerChains = readHandlerChains("/handlers_bindings.xml");
        assertEquals(3, handlerChains.getHandlerChain().size());

        final List<HandlerChainInfo> handlerChainInfos = ConfigurationFactory.toHandlerChainInfo(handlerChains);
        final List<HandlerChainData> handlerChainDatas = WsBuilder.toHandlerChainData(handlerChainInfos, getClass().getClassLoader());
        final HandlerResolverImpl resolver = new HandlerResolverImpl(handlerChainDatas, null, new InitialContext());

        List<Handler> handlers = null;

        handlers = resolver.getHandlerChain(new TestPortInfo(null, null, null));
        assertEquals(0, handlers.size());

        handlers = resolver.getHandlerChain(new TestPortInfo("##SOAP12_HTTP", null, null));
        assertEquals(0, handlers.size());

        handlers = resolver.getHandlerChain(new TestPortInfo("##SOAP11_HTTP", null, null));
        assertEquals(2, handlers.size());

        handlers = resolver.getHandlerChain(new TestPortInfo("##SOAP11_HTTP_MTOM", null, null));
        assertEquals(1, handlers.size());
    }

    public void testPortMatching() throws Exception {
        final HandlerChains handlerChains = readHandlerChains("/handlers_port.xml");
        assertEquals(3, handlerChains.getHandlerChain().size());

        final List<HandlerChainInfo> handlerChainInfos = ConfigurationFactory.toHandlerChainInfo(handlerChains);
        final List<HandlerChainData> handlerChainDatas = WsBuilder.toHandlerChainData(handlerChainInfos, getClass().getClassLoader());
        final HandlerResolverImpl resolver = new HandlerResolverImpl(handlerChainDatas, null, new InitialContext());

        List<Handler> handlers = null;

        handlers = resolver.getHandlerChain(new TestPortInfo(null, null, null));
        assertEquals(0, handlers.size());

        final QName portName1 = new QName("http://java.sun.com/xml/ns/javaee", "Bar");
        handlers = resolver.getHandlerChain(new TestPortInfo(null, portName1, null));
        assertEquals(1, handlers.size());

        final QName portName2 = new QName("http://java.sun.com/xml/ns/javaee", "Foo");
        handlers = resolver.getHandlerChain(new TestPortInfo(null, portName2, null));
        assertEquals(2, handlers.size());

        final QName portName3 = new QName("http://java.sun.com/xml/ns/javaee", "FooBar");
        handlers = resolver.getHandlerChain(new TestPortInfo(null, portName3, null));
        assertEquals(1, handlers.size());

        final QName portName4 = new QName("http://java.sun.com/xml/ns/javaee", "BarFoo");
        handlers = resolver.getHandlerChain(new TestPortInfo(null, portName4, null));
        assertEquals(0, handlers.size());
    }

    public void testMixedMatching() throws Exception {
        final HandlerChains handlerChains = readHandlerChains("/handlers_mixed.xml");
        assertEquals(3, handlerChains.getHandlerChain().size());

        final List<HandlerChainInfo> handlerChainInfos = ConfigurationFactory.toHandlerChainInfo(handlerChains);
        final List<HandlerChainData> handlerChainDatas = WsBuilder.toHandlerChainData(handlerChainInfos, getClass().getClassLoader());
        final HandlerResolverImpl resolver = new HandlerResolverImpl(handlerChainDatas, null, new InitialContext());

        List<Handler> handlers = null;

        handlers = resolver.getHandlerChain(new TestPortInfo(null, null, null));
        assertEquals(0, handlers.size());

        final QName serviceName1 = new QName("http://java.sun.com/xml/ns/javaee", "Bar");
        final QName portName1 = new QName("http://java.sun.com/xml/ns/javaee", "FooBar");
        final String binding1 = "##XML_HTTP";
        handlers = resolver.getHandlerChain(new TestPortInfo(binding1, portName1, serviceName1));
        assertEquals(3, handlers.size());

        final String binding2 = "##SOAP11_HTTP";
        handlers = resolver.getHandlerChain(new TestPortInfo(binding2, portName1, serviceName1));
        assertEquals(2, handlers.size());

        final QName serviceName2 = new QName("http://java.sun.com/xml/ns/javaee", "Baaz");
        final QName portName2 = new QName("http://java.sun.com/xml/ns/javaee", "Baaz");
        handlers = resolver.getHandlerChain(new TestPortInfo(binding1, portName2, serviceName2));
        assertEquals(1, handlers.size());
    }

    private HandlerChains readHandlerChains(final String filePath) throws Exception {
        final URL url = getClass().getResource(filePath);
        assertNotNull("Could not find handler chains file " + filePath, url);
        final HandlerChains handlerChains = ReadDescriptors.readHandlerChains(url);
        return handlerChains;
    }

    private static class TestPortInfo implements PortInfo {

        private final String bindingID;
        private final QName portName;
        private final QName serviceName;

        public TestPortInfo(final String bindingID, final QName portName, final QName serviceName) {
            this.bindingID = bindingID;
            this.portName = portName;
            this.serviceName = serviceName;
        }

        public String getBindingID() {
            return this.bindingID;
        }

        public QName getPortName() {
            return this.portName;
        }

        public QName getServiceName() {
            return this.serviceName;
        }

    }

}
