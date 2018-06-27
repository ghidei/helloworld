package sample.hello

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef
import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.Terminated

import scala.io.Source

import scala.concurrent.Await
import scala.concurrent.duration._

object Main {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("Hello")
    val a = system.actorOf(Props[HelloWorld], "helloWorld")
    system.actorOf(Props(classOf[Terminator], a), "terminator")
    //NOT PART OF THE ORIGINAL CODE
    Await.ready(system.terminate(), 60.seconds)

  }

  class Terminator(ref: ActorRef) extends Actor with ActorLogging {
    context watch ref
    def receive = {
      case Terminated(_) =>
        log.info("{} has terminated, shutting down system", ref.path)
        context.system.terminate()
    }
  }

  //NOT PART OF THE ORIGINAL CODE
  def verifyCorrectness(): Boolean = {
    val input : Source = Source.fromFile("ldfi-akka/logs.log")
    input.getLines().exists { line => line.contains("Done") }
  }

}