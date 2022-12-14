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
package org.apache.openejb.jee.sun;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "servletNameOrUrlPattern",
    "cacheHelperRefOrDispatcherOrTimeoutOrRefreshFieldOrHttpMethodOrKeyFieldOrConstraintField"
})
public class CacheMapping {
    @XmlElements({
        @XmlElement(name = "servlet-name", required = true, type = ServletName.class),
        @XmlElement(name = "url-pattern", required = true, type = UrlPattern.class)
    })
    protected List<Object> servletNameOrUrlPattern;
    @XmlElements({
        @XmlElement(name = "cache-helper-ref", type = CacheHelperRef.class),
        @XmlElement(name = "dispatcher", type = Dispatcher.class),
        @XmlElement(name = "timeout", type = Timeout.class),
        @XmlElement(name = "refresh-field", type = RefreshField.class),
        @XmlElement(name = "http-method", type = HttpMethod.class),
        @XmlElement(name = "key-field", type = KeyField.class),
        @XmlElement(name = "constraint-field", type = ConstraintField.class)
    })
    protected List<Object> cacheHelperRefOrDispatcherOrTimeoutOrRefreshFieldOrHttpMethodOrKeyFieldOrConstraintField;

    public List<Object> getServletNameOrUrlPattern() {
        if (servletNameOrUrlPattern == null) {
            servletNameOrUrlPattern = new ArrayList<Object>();
        }
        return this.servletNameOrUrlPattern;
    }

    public List<Object> getCacheHelperRefOrDispatcherOrTimeoutOrRefreshFieldOrHttpMethodOrKeyFieldOrConstraintField() {
        if (cacheHelperRefOrDispatcherOrTimeoutOrRefreshFieldOrHttpMethodOrKeyFieldOrConstraintField == null) {
            cacheHelperRefOrDispatcherOrTimeoutOrRefreshFieldOrHttpMethodOrKeyFieldOrConstraintField = new ArrayList<Object>();
        }
        return this.cacheHelperRefOrDispatcherOrTimeoutOrRefreshFieldOrHttpMethodOrKeyFieldOrConstraintField;
    }
}
