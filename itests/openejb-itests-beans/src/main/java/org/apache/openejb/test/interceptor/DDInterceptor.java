/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.openejb.test.interceptor;

import jakarta.interceptor.InvocationContext;


/**
 * @version $Rev$ $Date$
 */
public class DDInterceptor {


    /**
     * The interceptor method.
     * This should intercept all business methods in this bean class
     * except those annotated with <code>@ExcludeddInterceptors</code>
     *
     * @param ctx - InvocationContext
     * @return - the result of the next method invoked. If a method returns void, proceed returns null.
     * For lifecycle callback interceptor methods, if there is no callback method defined on the bean class,
     * the invocation of proceed in the last interceptor method in the chain is a no-op, and null is returned.
     * If there is more than one such interceptor method, the invocation of proceed causes the container to execute those methods in order.
     * @throws Exception or application exceptions that are allowed in the throws clause of the business method.
     */
    public Object ddInterceptor(final InvocationContext ctx) throws Exception {
        Interceptor.profile(ctx, "ddInterceptor");
        return ctx.proceed();
    }

    /**
     * The interceptor method.
     * This should intercept postConstruct of the bean
     *
     * @param ctx - InvocationContext
     * @throws Exception
     */
    public void ddInterceptorPostConstruct(final InvocationContext ctx) throws Exception {
        Interceptor.profile(ctx, "ddInterceptorPostConstruct");
        ctx.proceed();
        return;
    }


    /**
     * The interceptor method.
     * This should intercept postActivate of the bean
     *
     * @param ctx - InvocationContext
     * @throws Exception
     */
    public void ddInterceptorPostActivate(final InvocationContext ctx) throws Exception {
        Interceptor.profile(ctx, "ddInterceptorPostActivate");
        ctx.proceed();
        return;
    }

    /**
     * The interceptor method.
     * This should intercept prePassivate of the bean.
     *
     * @param ctx - InvocationContext
     * @throws Exception
     */
    public void ddInterceptorPrePassivate(final InvocationContext ctx) throws Exception {
        Interceptor.profile(ctx, "ddInterceptorPrePassivate");
        ctx.proceed();
        return;
    }

    /**
     * The interceptor method.
     * This should intercept preDestroy of the bean.
     *
     * @param ctx - InvocationContext
     * @throws Exception
     */
    public void ddInterceptorPreDestroy(final InvocationContext ctx) throws Exception {
        Interceptor.profile(ctx, "ddInterceptorPreDestroy");
        ctx.proceed();
        return;
    }

}