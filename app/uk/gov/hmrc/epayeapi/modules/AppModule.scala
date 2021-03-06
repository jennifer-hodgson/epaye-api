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

package uk.gov.hmrc.epayeapi.modules

import javax.inject.Singleton
import com.google.inject.{AbstractModule, Provides}
import uk.gov.hmrc.auth.core.AuthConnector
import uk.gov.hmrc.play.auth.microservice.connectors.{AuthConnector => PlayAuthConnector}
import uk.gov.hmrc.epayeapi.config._
import uk.gov.hmrc.epayeapi.connectors.EpayeApiConfig
import uk.gov.hmrc.play.http.HttpPost
import uk.gov.hmrc.play.http.ws.WSHttp

class AppModule() extends AbstractModule {
  def configure(): Unit = {
    bind(classOf[HttpPost]).to(classOf[WSHttpImpl]).asEagerSingleton()
    bind(classOf[PlayAuthConnector]).to(classOf[MicroserviceAuthConnector]).asEagerSingleton()
    bind(classOf[AuthConnector]).to(classOf[ActualAuthConnector]).asEagerSingleton()
    bind(classOf[WSHttp]).to(classOf[WSHttpImpl]).asEagerSingleton()
    bind(classOf[Startup]).to(classOf[AppStartup]).asEagerSingleton()
  }

  @Provides
  @Singleton
  def provideEpayeApiConfig(context: AppContext): EpayeApiConfig = {
    EpayeApiConfig(context.config.baseUrl("epaye"))
  }
}
