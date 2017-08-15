package inviqa.com.workshop.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import inviqa.com.workshop.api.WorkshopService
import com.lightbend.lagom.scaladsl.broker.kafka.LagomKafkaComponents
import com.softwaremill.macwire._

class WorkshopLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new WorkshopApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new WorkshopApplication(context) with LagomDevModeComponents

  override def describeServices = List(
    readDescriptor[WorkshopService]
  )
}

abstract class WorkshopApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with CassandraPersistenceComponents
    with LagomKafkaComponents
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[WorkshopService](wire[WorkshopServiceImpl])

  // Register the JSON serializer registry
  override lazy val jsonSerializerRegistry = WorkshopSerializerRegistry

  // Register the Workshop persistent entity
  persistentEntityRegistry.register(wire[WorkshopEntity])
}
