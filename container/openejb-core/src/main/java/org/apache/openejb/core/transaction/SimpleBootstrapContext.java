/*
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

package org.apache.openejb.core.transaction;

import org.apache.geronimo.connector.work.GeronimoWorkManager;

import jakarta.resource.spi.BootstrapContext;
import jakarta.resource.spi.XATerminator;
import jakarta.resource.spi.work.WorkContext;
import jakarta.resource.spi.work.WorkManager;
import jakarta.transaction.TransactionSynchronizationRegistry;
import java.util.Timer;

public class SimpleBootstrapContext implements BootstrapContext {
    private final WorkManager workManager;
    private final XATerminator xaTerminator;

    public SimpleBootstrapContext(final WorkManager workManager) {
        this.workManager = workManager;
        xaTerminator = null;
    }

    public SimpleBootstrapContext(final WorkManager workManager, final XATerminator xaTerminator) {
        this.workManager = workManager;
        this.xaTerminator = xaTerminator;
    }

    public WorkManager getWorkManager() {
        return workManager;
    }

    public XATerminator getXATerminator() {
        return xaTerminator;
    }

    public Timer createTimer() {
        return new Timer(true);
    }

    public TransactionSynchronizationRegistry getTransactionSynchronizationRegistry() {
        // for Geronimo transaction manager, it implements XATerminator, TransactionManager & TransactionSynchronizationRegistry
        if (this.xaTerminator != null && this.xaTerminator instanceof TransactionSynchronizationRegistry) {
            return (TransactionSynchronizationRegistry) this.xaTerminator;
        }
        return null;
    }

    public boolean isContextSupported(final Class<? extends WorkContext> cls) {
        if (workManager instanceof GeronimoWorkManager) {
            final GeronimoWorkManager geronimoWorkManager = (GeronimoWorkManager) workManager;
            return geronimoWorkManager.isContextSupported(cls);
        }

        return false;
    }
}
