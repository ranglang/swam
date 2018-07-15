/*
 * Copyright 2018 Lucas Satabin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package swam
package text

import util._
import parser._
import unresolved._

import utest._

import better.files._

import fastparse.core._

import cats.effect._

object SpecTests extends TestSuite {

  val tests = Tests {
    'compiling - {
      val compiler = new Compiler[IO]
      for (wast <- ("core" / "test" / "resources" / "spec-test").glob("*.wast")) {
        val script = TestScriptParser.script.parse(wast.contentAsString).get.value
        for (ValidModule(mod) <- script) try {
          val io = compiler.compile(mod)
          io.unsafeRunSync()
        } catch {
          case e: ResolutionException =>
            val positioner = new WastPositioner(wast.path)
            for (pos <- e.positions)
              println(positioner.render(pos))
            throw e
        }
      }
    }
  }

}
