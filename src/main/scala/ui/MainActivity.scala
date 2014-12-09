package edu.luc.etl.cs313.scala.akkaclickcounter
package ui

import akka.actor.{ActorRef, Props, ActorSystem}
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View

import model.BoundedCounterActor
import BoundedCounterActor._

/**
 * The main Android activity, which provides the required lifecycle methods.
 * By mixing in the reactive view behaviors, this class serves as the Adapter
 * in the Model-View-Adapter pattern. It connects the Android GUI view with the
 * reactive model.
 */
class MainActivity extends Activity with TypedActivity {

  private def TAG = "clickcounter-android-akka"

  var adapter: ActorRef = _

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    Log.i(TAG, "onCreate")
    // inject the (implicit) dependency on the view
    setContentView(R.layout.main)
    // instantiate the adapter actor, which will instantiate the counter model
    val system = ActorSystem("clickcounter")
    // FIXME cannot get this to work without explicit instantiation of the actor
    adapter = system.actorOf(Props(
      new AdapterActor(this, system.actorOf(Props(classOf[BoundedCounterActor], 0, 5)))))
  }

  override def onStart() = {
    super.onStart()
    Log.i(TAG, "onStart")
    adapter ! Reset
  }

  /**
   * Sends an increment message to the adapter. This method is connected to the
   * corresponding onClick event (actual button presses) in the view itself,
   * usually with the help of the graphical layout editor; the connection also
   * shows up in the XML source of the view layout.
   */
  def onIncrement(view: View): Unit = adapter ! Increment

  /** Sends an decrement message to the adapter. */
  def onDecrement(view: View): Unit = adapter ! Decrement

  /** Sends a reset message to the adapter. */
  def onReset(view: View): Unit = adapter ! Reset
}
