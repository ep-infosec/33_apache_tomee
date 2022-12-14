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
package org.apache.openejb.cdi;

import junit.framework.TestCase;
import org.apache.openejb.OpenEJB;
import org.apache.openejb.assembler.classic.Assembler;
import org.apache.openejb.assembler.classic.SecurityServiceInfo;
import org.apache.openejb.assembler.classic.TransactionServiceInfo;
import org.apache.openejb.config.ConfigurationFactory;
import org.apache.openejb.config.EjbModule;
import org.apache.openejb.core.ivm.naming.InitContextFactory;
import org.apache.openejb.jee.Beans;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.StatelessBean;
import org.junit.AfterClass;
import org.junit.Before;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.ejb.EJB;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Qualifier;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InterceptorBinding;
import jakarta.interceptor.Interceptors;
import jakarta.interceptor.InvocationContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Properties;

@SuppressWarnings("deprecation")
public class SimpleCdiTest extends TestCase {

    private InitialContext ctx;

    @Before
    public void setUp() throws Exception {

        final ConfigurationFactory config = new ConfigurationFactory();
        final Assembler assembler = new Assembler();

        assembler.createTransactionManager(config.configureService(TransactionServiceInfo.class));
        assembler.createSecurityService(config.configureService(SecurityServiceInfo.class));

        final EjbJar ejbJar = new EjbJar();
        ejbJar.addEnterpriseBean(new StatelessBean(Echo.class));

        final Beans beans = new Beans();
        beans.addInterceptor(EchoInterceptor.class);
        beans.addDecorator(EchoDecorator.class);
        beans.addManagedClass(SimpleModel.class);
        beans.addManagedClass(ProducesEjbInjector.class);

        final EjbModule module = new EjbModule(ejbJar);
        module.setBeans(beans);

        assembler.createApplication(config.configureApplication(module));

        final Properties properties = new Properties(System.getProperties());
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, InitContextFactory.class.getName());
        ctx = new InitialContext(properties);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        OpenEJB.destroy();
    }

    @Override
    protected void tearDown() throws Exception {
        OpenEJB.destroy();
    }

    public void testSimple() {
        try {
            final EchoLocal echo = (EchoLocal) ctx.lookup("EchoLocal");
            final String result = echo.echo("Gurkan");
            assertEquals("Gurkan", result);

            assertTrue(EchoInterceptor.RUN);
            assertTrue(NormalEjbInterceptor.RUN);
            assertTrue(NormalEjbInterceptor.INJECTED);

        } catch (final NamingException e) {
            e.printStackTrace();
        }
    }

    @InterceptorBinding
    @Target(value = {ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface EchoInterceptorBinding {

    }

    @Interceptor
    @EchoInterceptorBinding
    public static class EchoInterceptor implements Serializable {

        public static boolean RUN = false;

        @AroundInvoke
        public Object aroundInvoke(final InvocationContext context) throws Exception {
            RUN = true;
            return context.proceed();
        }
    }

    @Stateless
    @EchoInterceptorBinding
    @Interceptors(value = {NormalEjbInterceptor.class})
    public static class Echo implements EchoLocal {

        @Inject
        private SimpleModel model;

        @Inject
        private ProducesEjbInjector injector;

        @Override
        public String echo(final String echo) {
            assertNotNull(model);
            return echo;
        }

    }

    @Local
    public static interface EchoLocal {
        public String echo(String echo);
    }

    public static class ProducesEjbInjector {

        @Inject
        @EchoEjbQualifier
        private EchoLocal echo;

        public EchoLocal getEcho() {
            return echo;
        }

        public void setEcho(final EchoLocal echo) {
            this.echo = echo;
        }
    }

    public static class NormalEjbInterceptor implements Serializable {

        public static boolean RUN = false;
        public static boolean INJECTED = false;

        @Inject
        private SimpleModel injection;

        @AroundInvoke
        public Object aroundInvoke(final InvocationContext context) throws Exception {
            RUN = true;
            if (injection != null) {
                INJECTED = true;
            }

            return context.proceed();
        }
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.FIELD})
    public static @interface EchoEjbQualifier {

    }

    public static class SimpleModel implements Serializable {

        @EJB
        private EchoLocal echoLocal;

        @Produces
        @EchoEjbQualifier
        @EJB
        private EchoLocal local2ViaProduce;

        public EchoLocal getLocal2ViaProduce() {
            return local2ViaProduce;
        }

        public void setLocal2ViaProduce(final EchoLocal local2ViaProduce) {
            this.local2ViaProduce = local2ViaProduce;
        }

        public EchoLocal getEchoLocal() {
            return echoLocal;
        }

        public void setEchoLocal(final EchoLocal echoLocal) {
            this.echoLocal = echoLocal;
        }
    }

    @Decorator
    public static class EchoDecorator implements EchoLocal {

        @Inject
        @Delegate
        private EchoLocal local;

        @Override
        public String echo(final String echo) {
            return local.echo(echo);
        }
    }
}
