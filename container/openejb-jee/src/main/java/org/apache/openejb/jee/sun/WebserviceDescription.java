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

import org.apache.openejb.jee.Keyable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "webserviceDescriptionName",
    "wsdlPublishLocation"
})
public class WebserviceDescription implements Keyable<String> {
    @XmlElement(name = "webservice-description-name", required = true)
    protected String webserviceDescriptionName;
    @XmlElement(name = "wsdl-publish-location")
    protected String wsdlPublishLocation;

    public String getKey() {
        return webserviceDescriptionName;
    }

    public String getWebserviceDescriptionName() {
        return webserviceDescriptionName;
    }

    public void setWebserviceDescriptionName(final String value) {
        this.webserviceDescriptionName = value;
    }

    public String getWsdlPublishLocation() {
        return wsdlPublishLocation;
    }

    public void setWsdlPublishLocation(final String value) {
        this.wsdlPublishLocation = value;
    }
}
