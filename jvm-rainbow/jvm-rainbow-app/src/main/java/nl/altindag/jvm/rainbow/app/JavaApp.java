/*
 * Copyright 2022 Thunderberry.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.altindag.jvm.rainbow.app;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import nl.altindag.jvm.rainbow.service.GreetingsService;
import nl.altindag.jvm.rainbow.service.GroovyService;
import nl.altindag.jvm.rainbow.service.JavaService;
import nl.altindag.jvm.rainbow.service.KotlinService;
import nl.altindag.jvm.rainbow.service.ScalaService;

public class JavaApp {

    public static void main(String[] args) {
        System.out.println(new JavaService().hello());
        System.out.println(new KotlinService().hello());
        System.out.println(new ScalaService().hello());
        System.out.println(new GroovyService().hello());

        // Below steps are required to call Clojure from Java
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("nl.altindag.jvm.rainbow.service.ClojureService"));
        IFn greetingsFactory = Clojure.var("nl.altindag.jvm.rainbow.service.ClojureService", "reify-greetings-service");
        GreetingsService clojureService = (GreetingsService) greetingsFactory.invoke();
        System.out.println(clojureService.hello());
    }

}
