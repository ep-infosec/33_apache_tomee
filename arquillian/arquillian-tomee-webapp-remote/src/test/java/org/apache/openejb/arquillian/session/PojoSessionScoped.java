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

package org.apache.openejb.arquillian.session;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@SessionScoped
public class PojoSessionScoped implements Serializable {
    private static AtomicInteger ID = new AtomicInteger();

    private long ms;
    private int id;

    public PojoSessionScoped() {
        ms = System.currentTimeMillis();
    }

    @PostConstruct
    public void initId() {
        id = ID.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public long getMs() {
        return ms;
    }
}
