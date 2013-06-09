package com.github.fxlex.example

import processing.core.PApplet
import App._
import processing.core.PConstants._
import com.github.fxlex.library.{ProcessingClassConfig, Status}


class Pong extends PApplet {

override def setup {
  size(w,h)
  ProcessingClassConfig(this)
  App()
  smooth()
  frameRate(30)
  Status += INIT
}

override def draw {

  if (Status?STARTED)
      eventBus.post(STARTED)
}

override def mousePressed() = if(Status ?! WIN1 && Status ?! WIN2 ) Status += STARTED

override def keyReleased {
    if (key == 'r' && (Status?WIN1 || Status?WIN2)) {
      score1 = 0
      score2 = 0
      Status(STARTED)
    }
}

override def keyPressed  {
  key match {
    case w if key.toLower == 'w' => Status += LEFT_PLAYER_UP
    case s if key.toLower == 's' => Status += LEFT_PLAYER_DOWN
    case CODED =>
      keyCode match {
        case UP => Status   += RIGHT_PLAYER_UP
        case DOWN => Status += RIGHT_PLAYER_DOWN
        case _ =>    // key is coded but not important for us
      }
    case _ =>   // key is not important for us
  }
}

}

