/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.apache.openejb.maven.plugin;

import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;

/**
 * The type StartTomEEMojo.
 * Start and forget TomEE.
 */
@Mojo(name = "start", requiresDependencyResolution = ResolutionScope.RUNTIME_PLUS_SYSTEM)
public class StartTomEEMojo extends UpdatableTomEEMojo {
    @Override
    public String getCmd() {
        return "start";
    }

    @Override
    protected boolean getWaitTomEE() {
        return false;
    }

    @Override
    protected int getConnectAttempts() {
        return (checkStarted ? checkStartedAttempts : 1);
    }
}
