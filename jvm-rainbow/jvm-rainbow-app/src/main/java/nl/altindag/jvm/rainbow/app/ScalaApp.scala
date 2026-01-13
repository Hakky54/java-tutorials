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
package nl.altindag.jvm.rainbow.app

import clojure.java.api.Clojure
import nl.altindag.jvm.rainbow.service._

object ScalaApp {

  private val clojureServiceNameSpace: String = "nl.altindag.jvm.rainbow.service.ClojureService"

  def main(args: Array[String]): Unit = {
    println(new JavaService().hello())
    println(new KotlinService().hello())
    println(new ScalaService().hello())
    println(new GroovyService().hello())

    // Below steps are required to call Clojure from Scala
    val require = Clojure.`var`("clojure.core", "require")
    require.invoke(Clojure.read(clojureServiceNameSpace))
    val greetingsFactory = Clojure.`var`(clojureServiceNameSpace, "reify-greetings-service")
    val clojureService = greetingsFactory.invoke.asInstanceOf[GreetingsService]
    println(clojureService.hello)
  }

}
