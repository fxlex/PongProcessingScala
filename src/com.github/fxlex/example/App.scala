package com.github.fxlex.example

import com.google.common.eventbus.EventBus
import processing.core.PVector
import com.github.fxlex.library.{Status, StatusCode}


object App {

  def apply() = {
    val l = Array(Gui,Logic,paddleLeft,paddleRight)
    l foreach(eventBus.register(_))
    Status.init(eventBus)
  }

  val w         = 800
  val h         = 500

  var score1,score2   = 0
  var xSpeed          = -4.0f
  val winScore        = 1

  lazy val ball:Ball              = Ball(new PVector(400, 250))
  lazy val paddleLeft:PaddleLeft  = PaddleLeft(60, h/2)
  lazy val paddleRight            = PaddleRight(740,w/2)
  lazy val eventBus               = new EventBus

  type S = StatusCode
  object INIT                 extends S
  object WIN1                 extends S
  object WIN2                 extends S
  object STARTED              extends S
  object LEFT_PLAYER_UP       extends S
  object LEFT_PLAYER_DOWN     extends S
  object RIGHT_PLAYER_UP      extends S
  object RIGHT_PLAYER_DOWN    extends S
  object DISPLAY_PADDLES      extends S
  object COLLISION_DETECTION  extends S
  object CANNOT_SCORE         extends S

}
