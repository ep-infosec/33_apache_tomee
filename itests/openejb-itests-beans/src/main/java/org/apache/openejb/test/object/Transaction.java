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
package org.apache.openejb.test.object;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import jakarta.transaction.UserTransaction;

public class Transaction implements java.io.Externalizable {

    private String instance;

    public Transaction(final UserTransaction obj) {
        instance = obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode());
    }

    public Transaction() {
    }

    public boolean equals(final Object object) {
        if (!(object instanceof Transaction)) return false;

        final Transaction that = (Transaction) object;
        return this.instance.equals(that.instance);
    }

    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeUTF(instance);
    }

    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        instance = in.readUTF();
    }

    public String toString() {
        return instance;
    }
}
