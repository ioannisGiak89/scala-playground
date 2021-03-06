package inviqa.com.workshop.impl

import inviqa.com.workshop.api
import inviqa.com.workshop.api.{WorkshopService}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}

/**
  * Implementation of the WorkshopService.
  */
class WorkshopServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends WorkshopService {

  override def hello(id: String) = ServiceCall { _ =>
    // Look up the Workshop entity for the given ID.
    val ref = persistentEntityRegistry.refFor[WorkshopEntity](id)

    // Ask the entity the Hello command.
    ref.ask(Hello(id))
  }

  override def useGreeting(id: String) = ServiceCall { request =>
    // Look up the Workshop entity for the given ID.
    val ref = persistentEntityRegistry.refFor[WorkshopEntity](id)

    // Tell the entity to use the greeting message specified.
    ref.ask(UseGreetingMessage(request.message))
  }


  override def greetingsTopic(): Topic[api.GreetingMessageChanged] =
    TopicProducer.singleStreamWithOffset {
      fromOffset =>
        persistentEntityRegistry.eventStream(WorkshopEvent.Tag, fromOffset)
          .map(ev => (convertEvent(ev), ev.offset))
    }

  private def convertEvent(helloEvent: EventStreamElement[WorkshopEvent]): api.GreetingMessageChanged = {
    helloEvent.event match {
      case GreetingMessageChanged(msg) => api.GreetingMessageChanged(helloEvent.entityId, msg)
    }
  }
}
