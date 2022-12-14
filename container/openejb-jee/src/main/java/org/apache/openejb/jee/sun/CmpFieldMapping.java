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
    "fieldName",
    "columnName",
    "readOnly",
    "fetchedWith"
})
public class CmpFieldMapping {
    @XmlElement(name = "field-name", required = true)
    protected String fieldName;
    @XmlElement(name = "column-name", required = true)
    protected List<ColumnName> columnName;
    @XmlElement(name = "read-only")
    protected ReadOnly readOnly;
    @XmlElement(name = "fetched-with")
    protected FetchedWith fetchedWith;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(final String value) {
        this.fieldName = value;
    }

    public List<ColumnName> getColumnName() {
        if (columnName == null) {
            columnName = new ArrayList<ColumnName>();
        }
        return this.columnName;
    }

    public ReadOnly getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(final ReadOnly value) {
        this.readOnly = value;
    }

    public FetchedWith getFetchedWith() {
        return fetchedWith;
    }

    public void setFetchedWith(final FetchedWith value) {
        this.fetchedWith = value;
    }
}
