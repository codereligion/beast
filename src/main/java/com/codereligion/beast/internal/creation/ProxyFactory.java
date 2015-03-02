/**
 * Copyright 2013 www.codereligion.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codereligion.beast.internal.creation;

import com.codereligion.cherry.reflect.BeanIntrospections;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Creates proxies.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 02.03.2015
 */
class ProxyFactory {

    /**
     * Creates a proxy for the given {@code beanClass} which will intercept the method calls of equals, hashCode and toString in order to return an specific
     * result according to the given {@code propertyState}.
     *
     * @param beanClass     the {@link Class} to create the proxy for
     * @param propertyState the {@link PropertyState} which determines the behavior of the proxy
     * @return the created proxy
     * @throws IllegalArgumentException when the given {@code beanClass} is {@code final}
     */
    @SuppressWarnings("unchecked")
    static <T> T createProxy(final Class<T> beanClass, final PropertyState propertyState) {

        // TODO move those IAE to the AbstractTest and make them ISE here
        if (Modifier.isFinal(beanClass.getModifiers())) {
            throw new IllegalArgumentException("Can not create proxy for final class " + beanClass.getCanonicalName() + ".");
        }

        final boolean isConcreteClass = !beanClass.isInterface() && !Modifier.isAbstract(beanClass.getModifiers());

        if (isConcreteClass && !BeanIntrospections.hasDefaultConstructor(beanClass)) {
            throw new IllegalArgumentException("Can not create proxy for property class " + beanClass.getCanonicalName() +
                                               " because of missing default constructor. Either provide a default constructor " +
                                               "or add a CustomInstanceProvider for that class.");
        }

        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setCallback(new MethodInterceptor() {

            private final Integer propertyStateFlagAsInteger = propertyState.ordinal();
            private final String propertyStateFlagAsString = String.valueOf(propertyState.ordinal());

            @Override
            public Object intercept(final Object thisObject, final Method method, final Object[] args, final MethodProxy methodProxy) throws Throwable {

                if (method.getName().equals(ObjectMethodNames.EQUALS)) {

                    final Object thatObject = args[0];

                    if (thatObject == null) {
                        return Boolean.FALSE;
                    }

                    if (thisObject == thatObject) {
                        return Boolean.TRUE;
                    }

                    final Class<?> thisSuperClass = thisObject.getClass().getSuperclass();
                    final Class<?> thatSuperClass = thatObject.getClass().getSuperclass();

                    if (!thisSuperClass.equals(thatSuperClass)) {
                        return Boolean.FALSE;
                    }

                    if (thisObject.hashCode() == thatObject.hashCode()) {
                        return Boolean.TRUE;
                    }

                    return Boolean.FALSE;
                } else if (method.getName().equals(ObjectMethodNames.HASH_CODE)) {
                    return this.propertyStateFlagAsInteger;
                } else if (method.getName().equals(ObjectMethodNames.TO_STRING)) {
                    return this.propertyStateFlagAsString;
                }

                return methodProxy.invokeSuper(thisObject, args);
            }
        });
        return (T) enhancer.create();
    }
}
