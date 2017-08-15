package inviqa.com.workshopstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import inviqa.com.workshopstream.api.WorkshopStreamService
import inviqa.com.workshop.api.WorkshopService

import scala.concurrent.Future

/**
  * Implementation of the WorkshopStreamService.
  */
class WorkshopStreamServiceImpl(workshopService: WorkshopService) extends WorkshopStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(workshopService.hello(_).invoke()))
  }
}
