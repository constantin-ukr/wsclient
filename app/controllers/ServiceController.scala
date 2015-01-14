package controllers

import akka.actor.Props
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import akka.actor._

import scala.concurrent.Future

object ServiceController extends Results {

  private val secretCall = Seq(118, 49, 47, 119, 115).map(_.toChar).mkString
  private val secretRequest = Seq(104, 101, 108, 108, 111).map(_.toChar).mkString

  class ServiceWebSocketActor extends Actor {
    def receive = {
      case message: String if message == secretRequest =>
        sender() ! "Hello! Nice to meet you! Success!"
      case message: String =>
        sender() ! "Wrong message!"
    }
  }

  def actorBuilder(out: ActorRef) = Props(new ServiceWebSocketActor)

  def socket(call: String) = WebSocket.tryAcceptWithActor[String, String] { request =>
    Future(call match {
      case s: String if s == secretCall =>
        Right(actorBuilder _)
      case _ =>
        Left(Forbidden)
    })
  }

}
