package edu.luc.etl.cs313.scala.akkaclickcounter
package model

import akka.actor.Actor

object BoundedCounterActor {
  // semantic input events
  case object Increment
  case object Decrement
  case object Reset

  // abstract model states
  case object Empty
  case object Counting
  case object Full
}

/**
 * A stateful bounded counter actor.
 * It receives input events and responds with the resulting counter value
 * and abstract model state.
 */
class BoundedCounterActor(val min: Int, val max: Int) extends Actor {

  import BoundedCounterActor._

  // Reflection in conjunction with default argument value is very messy.
  // This explicit default constructor makes it very easy to create
  // new instances through reflection.
  def this() = this(0, 10)

  require { min < max }

  private var value: Int = min

  override def receive = {
    // the data invariant is encoded in the various conditionals
    case Increment =>
      if (value < max) value += 1
      sender ! (value, if (value == max) Full else Counting)
    case Decrement =>
      if (value > min) value -= 1
      sender ! (value, if (value == min) Empty else Counting)
    case Reset =>
      value = min
      sender ! (value, Empty)
  }
}
