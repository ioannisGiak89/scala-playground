package inviqa.com.workshopstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import inviqa.com.workshopstream.api.WorkshopStreamService
import inviqa.com.workshop.api.WorkshopService
import com.softwaremill.macwire._

class WorkshopStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new WorkshopStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new WorkshopStreamApplication(context) with LagomDevModeComponents

  override def describeServices = List(
    readDescriptor[WorkshopStreamService]
  )
}

abstract class WorkshopStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[WorkshopStreamService](wire[WorkshopStreamServiceImpl])

  // Bind the WorkshopService client
  lazy val workshopService = serviceClient.implement[WorkshopService]
}
