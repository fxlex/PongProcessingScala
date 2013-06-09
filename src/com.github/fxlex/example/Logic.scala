package com.github.fxlex.example

import App._
import com.google.common.eventbus.Subscribe
import com.github.fxlex.library.{ProcessingClass, Status}
import com.github.fxlex.example.App.STARTED

object Logic extends ProcessingClass {

  @Subscribe
  def started(win:STARTED.type)  = {
    Gui.middleLineAndBackground

    ball.display
    ball.move(xSpeed)

    Status += DISPLAY_PADDLES
    Status += COLLISION_DETECTION

    ball.checkPoint
    Gui.score

    Status(score1 == winScore,WIN1)
    Status(score2 == winScore,WIN2)

  }
}
