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

package org.apache.openejb.jee.jba.cmp;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://jboss.org}property-name"/&gt;
 *         &lt;element ref="{http://jboss.org}column-name" minOccurs="0"/&gt;
 *         &lt;element ref="{http://jboss.org}not-null" minOccurs="0"/&gt;
 *         &lt;sequence minOccurs="0"&gt;
 *           &lt;element ref="{http://jboss.org}jdbc-type"/&gt;
 *           &lt;element ref="{http://jboss.org}sql-type"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "propertyName",
    "columnName",
    "notNull",
    "jdbcType",
    "sqlType"
})
@XmlRootElement(name = "property")
public class Property {

    @XmlElement(name = "property-name", required = true)
    protected PropertyName propertyName;
    @XmlElement(name = "column-name")
    protected ColumnName columnName;
    @XmlElement(name = "not-null")
    protected NotNull notNull;
    @XmlElement(name = "jdbc-type")
    protected JdbcType jdbcType;
    @XmlElement(name = "sql-type")
    protected SqlType sqlType;

    /**
     * Gets the value of the propertyName property.
     *
     * @return possible object is
     * {@link PropertyName }
     */
    public PropertyName getPropertyName() {
        return propertyName;
    }

    /**
     * Sets the value of the propertyName property.
     *
     * @param value allowed object is
     *              {@link PropertyName }
     */
    public void setPropertyName(final PropertyName value) {
        this.propertyName = value;
    }

    /**
     * Gets the value of the columnName property.
     *
     * @return possible object is
     * {@link ColumnName }
     */
    public ColumnName getColumnName() {
        return columnName;
    }

    /**
     * Sets the value of the columnName property.
     *
     * @param value allowed object is
     *              {@link ColumnName }
     */
    public void setColumnName(final ColumnName value) {
        this.columnName = value;
    }

    /**
     * Gets the value of the notNull property.
     *
     * @return possible object is
     * {@link NotNull }
     */
    public NotNull getNotNull() {
        return notNull;
    }

    /**
     * Sets the value of the notNull property.
     *
     * @param value allowed object is
     *              {@link NotNull }
     */
    public void setNotNull(final NotNull value) {
        this.notNull = value;
    }

    /**
     * Gets the value of the jdbcType property.
     *
     * @return possible object is
     * {@link JdbcType }
     */
    public JdbcType getJdbcType() {
        return jdbcType;
    }

    /**
     * Sets the value of the jdbcType property.
     *
     * @param value allowed object is
     *              {@link JdbcType }
     */
    public void setJdbcType(final JdbcType value) {
        this.jdbcType = value;
    }

    /**
     * Gets the value of the sqlType property.
     *
     * @return possible object is
     * {@link SqlType }
     */
    public SqlType getSqlType() {
        return sqlType;
    }

    /**
     * Sets the value of the sqlType property.
     *
     * @param value allowed object is
     *              {@link SqlType }
     */
    public void setSqlType(final SqlType value) {
        this.sqlType = value;
    }

}
