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

package uk.gov.hmrc.epayeapi.controllers

import javax.inject.{Inject, Singleton}

import akka.stream.Materializer
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, EssentialAction}
import uk.gov.hmrc.auth.core.AuthConnector
import uk.gov.hmrc.domain.EmpRef
import uk.gov.hmrc.epayeapi.models.EmpRefsResponse
import uk.gov.hmrc.epayeapi.models.Formats._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
case class GetEmpRefs @Inject() (
  authConnector: AuthConnector,
  implicit val ec: ExecutionContext,
  implicit val mat: Materializer
)
  extends ApiController {

  def getEmpRefs(): EssentialAction = EmpRefsAction { empRefs =>
    Action { request =>
      Ok(Json.toJson(EmpRefsResponse.fromSeq(empRefs.toSeq)))
    }
  }

  def sandbox(): EssentialAction =
    EnrolmentsAction(epayeEnrolment, epayeRetrieval) { _ =>
      Action { _ =>
        Ok(Json.toJson(EmpRefsResponse.fromSeq(Seq(
          EmpRef("001", "0000001"),
          EmpRef("002", "0000002"),
          EmpRef("003", "0000003")
        ))))
      }
    }
}
