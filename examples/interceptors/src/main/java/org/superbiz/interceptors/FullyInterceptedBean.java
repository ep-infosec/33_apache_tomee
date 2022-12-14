/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.superbiz.interceptors;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptors;
import jakarta.interceptor.InvocationContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @version $Rev$ $Date$
 */
@Stateless
@Local
@Interceptors({ClassLevelInterceptorOne.class, ClassLevelInterceptorTwo.class})
public class FullyInterceptedBean extends FullyInterceptedSuperClass implements FullyIntercepted {

    @Interceptors({MethodLevelInterceptorOne.class, MethodLevelInterceptorTwo.class})
    public List<String> businessMethod() {
        List<String> list = new ArrayList<String>();
        list.add("businessMethod");
        return list;
    }

    @Interceptors({MethodLevelInterceptorOne.class, MethodLevelInterceptorTwo.class})
    public List<String> methodWithDefaultInterceptorsExcluded() {
        List<String> list = new ArrayList<String>();
        list.add("methodWithDefaultInterceptorsExcluded");
        return list;
    }

    @AroundInvoke
    protected Object beanClassBusinessMethodInterceptor(InvocationContext ic) throws Exception {
        return Utils.addClassSimpleName(ic, "beanClassBusinessMethodInterceptor");
    }
}
