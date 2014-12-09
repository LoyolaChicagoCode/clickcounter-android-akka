package edu.luc.etl.cs313.scala.akkaclickcounter
package ui

import akka.actor.{ActorRef, Actor}
import model.BoundedCounterActor._

/**
 * The part of the Adapter in the Model-View-Adapter pattern that
 * keeps the view updated. To this end, it maps semantic update messages
 * to actual view changes visible to the user. It also forwards user input
 * messages to the counter model.
 *
 * @param activity the main activity containing the views to access
 * @param counter the actor serving as the counter model
 */
class AdapterActor(activity: MainActivity, counter: ActorRef) extends Actor {

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
