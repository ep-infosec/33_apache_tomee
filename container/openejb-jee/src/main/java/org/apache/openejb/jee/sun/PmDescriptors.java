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
    "pmDescriptor",
    "pmInuse"
})
public class PmDescriptors {
    @XmlElement(name = "pm-descriptor", required = true)
    protected List<PmDescriptor> pmDescriptor;
    @XmlElement(name = "pm-inuse", required = true)
    protected PmInuse pmInuse;

    public List<PmDescriptor> getPmDescriptor() {
        if (pmDescriptor == null) {
            pmDescriptor = new ArrayList<PmDescriptor>();
        }
        return this.pmDescriptor;
    }

    public PmInuse getPmInuse() {
        return pmInuse;
    }

    public void setPmInuse(final PmInuse value) {
        this.pmInuse = value;
    }
}
