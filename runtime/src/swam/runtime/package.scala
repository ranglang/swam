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

import scala.language.higherKinds

package object runtime {

  /** Represents the imported elements of an instance.
    *  These can either be other module [[Instance]]s or Scala
    *  functions and variables made available to interact between
    *  both worlds.
    */
  type Imports[F[_]] = Map[String, ImportedModule[F]]

  def truncate(f: Float): Float =
    if (f < 0)
      f.ceil
    else
      f.floor

  def truncate(d: Double): Double =
    if (d < 0)
      d.ceil
    else
      d.floor

}