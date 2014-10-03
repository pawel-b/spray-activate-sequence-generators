package pl.countries.webapp

import scala.util._
import akka.actor._
import akka.io.IO
import spray.can.Http
import spray.http.StatusCodes
import spray.json._
import spray.routing.HttpService
import spray.http.HttpMethods
import spray.routing.directives.LogEntry
import spray.http.HttpRequest
import akka.event.Logging
import spray.http.Uri.apply
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply
import spray.routing.directives.DetachMagnet.fromUnit
import spray.routing.directives.LoggingMagnet.forMessageFromFullShow
import pl.countries.persistence.CountryDao
import pl.countries.persistence.ModelJsonProtocol._
import pl.countries.persistence.CityDao

object Boot extends App {

  // Create an Akka system
  implicit val system = ActorSystem("countries")

  //create http actor
  val httpService = system.actorOf(Props[HttpServiceActor], "http-service")

  IO(Http) ! Http.Bind(httpService, "localhost", port = 8080)

}

class HttpServiceActor extends Actor with FlightsHttpService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing,
  // timeout handling or alternative handler registration
  def receive = runRoute(httpRoute)
}

// this trait defines our service behavior independently from the service actor
trait FlightsHttpService extends HttpService {

  // we use the enclosing ActorContext's or ActorSystem's dispatcher for our Futures and Scheduler
  implicit def executionContext = actorRefFactory.dispatcher

  val httpRoute =
    path("ping") {
      detach() {
        complete("PONG!")
      }
    } ~
      path("countries") {
        get {
          detach() {
            complete {
              CountryDao.getAllCountries
            }
          }
        }
      } ~
      path("cities") {
        get {
          detach() {
            complete {
              CityDao.getAllCities
            }
          }
        }
      } ~
      path("countries" / Segment) { code =>
        get {
          detach() {
            complete {
              CountryDao.getByCode(code)
            }
          }
        }
      }

}
