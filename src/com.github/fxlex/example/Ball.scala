package com.github.fxlex.example

import App._
import processing.core.PVector
import com.github.fxlex.library.{ProcessingClassConfig, ProcessingClass}


case class Ball(var pos:PVector,var speedY:Float=ProcessingClassConfig.p.random(-4,-2),
                var r:Float=20) extends ProcessingClass {

  import p._

  def display {
    noStroke
    fill(3, 255, 29)
    ellipse(pos.x, pos.y, r*2, r*2)
  }

  def move(speed:Float) {
    pos.add(speed,speedY,0)

    if (pos.y + r > height || pos.y - r < 0)
      speedY *= -1

  }

  def checkPoint {

    if (outRight) {
      pos.x = width/2
      xSpeed = random(-4, -3)
      score1 += 1
    }

    if (outLeft) {
      pos.x = width/2
      xSpeed = random(3, 4)
      score2 += 1
    }
  }

  def outRight:Boolean =  pos.x - r > width
  def outLeft:Boolean =  pos.x + r < 0
}