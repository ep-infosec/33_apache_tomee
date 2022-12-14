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

package org.apache.openejb.jee.jba;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "integrity",
    "confidentiality",
    "establishTrustInTarget",
    "establishTrustInClient",
    "detectMisordering",
    "detectReplay"
})
@XmlRootElement(name = "transport-config")
public class TransportConfig {

    @XmlElement(required = true)
    protected String integrity;
    @XmlElement(required = true)
    protected String confidentiality;
    @XmlElement(name = "establish-trust-in-target", required = true)
    protected String establishTrustInTarget;
    @XmlElement(name = "establish-trust-in-client", required = true)
    protected String establishTrustInClient;
    @XmlElement(name = "detect-misordering")
    protected String detectMisordering;
    @XmlElement(name = "detect-replay")
    protected String detectReplay;

    /**
     * Gets the value of the integrity property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIntegrity() {
        return integrity;
    }

    /**
     * Sets the value of the integrity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIntegrity(final String value) {
        this.integrity = value;
    }

    /**
     * Gets the value of the confidentiality property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getConfidentiality() {
        return confidentiality;
    }

    /**
     * Sets the value of the confidentiality property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConfidentiality(final String value) {
        this.confidentiality = value;
    }

    /**
     * Gets the value of the establishTrustInTarget property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEstablishTrustInTarget() {
        return establishTrustInTarget;
    }

    /**
     * Sets the value of the establishTrustInTarget property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEstablishTrustInTarget(final String value) {
        this.establishTrustInTarget = value;
    }

    /**
     * Gets the value of the establishTrustInClient property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEstablishTrustInClient() {
        return establishTrustInClient;
    }

    /**
     * Sets the value of the establishTrustInClient property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEstablishTrustInClient(final String value) {
        this.establishTrustInClient = value;
    }

    /**
     * Gets the value of the detectMisordering property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDetectMisordering() {
        return detectMisordering;
    }

    /**
     * Sets the value of the detectMisordering property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDetectMisordering(final String value) {
        this.detectMisordering = value;
    }

    /**
     * Gets the value of the detectReplay property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDetectReplay() {
        return detectReplay;
    }

    /**
     * Sets the value of the detectReplay property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDetectReplay(final String value) {
        this.detectReplay = value;
    }

}
