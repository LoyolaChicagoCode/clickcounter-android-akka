package edu.luc.etl.cs313.scala.akkaclickcounter
package ui

import akka.actor.{Props, Actor}
import model.BoundedCounterActor
import model.BoundedCounterActor._

/**
 * The part of the Adapter in the Model-View-Adapter pattern that
 * keeps the view updated. To this end, it maps semantic update messages
 * to actual view changes visible to the user. It also forwards user input
 * messages to the counter model.
 */
class AdapterActor(activity: MainActivity) extends Actor {

  /** Child actor for the counter model. */
  val counter = context.actorOf(Props(classOf[BoundedCounterActor], 0, 5))

  override def receive = {
    case value: Int => ui {
      activity.findView(TR.textview_value).setText(value.toString)
    }
    case Empty => ui {
      activity.findView(TR.button_increment).setEnabled(true)
      activity.findView(TR.button_decrement).setEnabled(false)
    }
    case Counting => ui {
      activity.findView(TR.button_increment).setEnabled(true)
      activity.findView(TR.button_decrement).setEnabled(true)
    }
    case Full => ui {
      activity.findView(TR.button_increment).setEnabled(false)
      activity.findView(TR.button_decrement).setEnabled(true)
    }
    case other => counter ! other
  }

  def ui(task: => Unit) =
    activity.runOnUiThread(new Runnable { override def run() = task })
}
