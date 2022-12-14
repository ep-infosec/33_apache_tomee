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
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.openejb.jee;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exception-mappingType", propOrder = {
    "exceptionType",
    "wsdlMessage",
    "wsdlMessagePartName",
    "constructorParameterOrder"
})
public class ExceptionMapping implements Keyable<QName> {
    @XmlElement(name = "exception-type", required = true)
    protected String exceptionType;
    @XmlElement(name = "wsdl-message", required = true)
    protected QName wsdlMessage;
    @XmlElement(name = "wsdl-message-part-name")
    protected String wsdlMessagePartName;
    @XmlElement(name = "constructor-parameter-order")
    protected ConstructorParameterOrder constructorParameterOrder;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;

    public QName getKey() {
        return getWsdlMessage();
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(final String value) {
        this.exceptionType = value;
    }

    public QName getWsdlMessage() {
        return wsdlMessage;
    }

    public void setWsdlMessage(final QName value) {
        this.wsdlMessage = value;
    }

    public String getWsdlMessagePartName() {
        return wsdlMessagePartName;
    }

    public void setWsdlMessagePartName(final String value) {
        this.wsdlMessagePartName = value;
    }

    public ConstructorParameterOrder getConstructorParameterOrder() {
        return constructorParameterOrder;
    }

    public void setConstructorParameterOrder(final ConstructorParameterOrder value) {
        this.constructorParameterOrder = value;
    }

    public String getId() {
        return id;
    }

    public void setId(final String value) {
        this.id = value;
    }
}
