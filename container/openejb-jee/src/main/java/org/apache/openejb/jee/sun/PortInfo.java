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
package org.apache.openejb.jee.sun;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serviceEndpointInterface",
    "wsdlPort",
    "stubProperty",
    "callProperty",
    "messageSecurityBinding"
})
public class PortInfo {
    @XmlElement(name = "service-endpoint-interface")
    protected String serviceEndpointInterface;
    @XmlElement(name = "wsdl-port")
    protected WsdlPort wsdlPort;
    @XmlElement(name = "stub-property")
    protected List<StubProperty> stubProperty;
    @XmlElement(name = "call-property")
    protected List<CallProperty> callProperty;
    @XmlElement(name = "message-security-binding")
    protected MessageSecurityBinding messageSecurityBinding;

    public String getServiceEndpointInterface() {
        return serviceEndpointInterface;
    }

    public void setServiceEndpointInterface(final String value) {
        this.serviceEndpointInterface = value;
    }

    public WsdlPort getWsdlPort() {
        return wsdlPort;
    }

    public void setWsdlPort(final WsdlPort value) {
        this.wsdlPort = value;
    }

    public List<StubProperty> getStubProperty() {
        if (stubProperty == null) {
            stubProperty = new ArrayList<StubProperty>();
        }
        return this.stubProperty;
    }

    public List<CallProperty> getCallProperty() {
        if (callProperty == null) {
            callProperty = new ArrayList<CallProperty>();
        }
        return this.callProperty;
    }

    public MessageSecurityBinding getMessageSecurityBinding() {
        return messageSecurityBinding;
    }

    public void setMessageSecurityBinding(final MessageSecurityBinding value) {
        this.messageSecurityBinding = value;
    }
}
