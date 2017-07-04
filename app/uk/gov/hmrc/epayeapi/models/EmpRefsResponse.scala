/*
 * Copyright 2017 HM Revenue & Customs
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

package uk.gov.hmrc.epayeapi.models

import uk.gov.hmrc.domain.EmpRef

case class EmpRefsResponse(_embedded: Seq[EmpRefItem])

object EmpRefsResponse {
  def fromSeq(seq: Seq[EmpRef]): EmpRefsResponse =
    EmpRefsResponse(seq.map(EmpRefItem(_)))
  def apply(empRef: EmpRef): EmpRefsResponse =
    EmpRefsResponse(Seq(EmpRefItem(empRef)))
}

case class EmpRefItem(empRef: EmpRef, _links: EmpRefLinks)

object EmpRefItem {
  def apply(empRef: EmpRef): EmpRefItem =
    EmpRefItem(empRef, EmpRefLinks(empRef))
}

case class EmpRefLinks(totals: Link)

object EmpRefLinks {
  def apply(empRef: EmpRef): EmpRefLinks =
    EmpRefLinks(Link(s"/${empRef.encodedValue}/total"))
}

case class Link(href: String)




