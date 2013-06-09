package com.github.fxlex.example

import App._
import com.google.common.eventbus.Subscribe
import processing.core.PApplet
import com.github.fxlex.library.{ProcessingClass, ProcessingClassConfig}

case class PaddleRight(var x:Float, var y:Float,var w:Float=20,var h:Float=100,
                      var c:Int=ProcessingClassConfig.p.color(255),
                      var intersect:Boolean=false) extends ProcessingClass {

  import processing.core.PConstants._
  import PApplet._
  import p._

  @Subscribe
  def moveUp(status:RIGHT_PLAYER_UP.type) = y-= 10

  @Subscribe
  def moveDown(status:RIGHT_PLAYER_DOWN.type) = y += 10

  @Subscribe
  def collisionDetection(status:COLLISION_DETECTION.type) {
    if (intersect(ball)) {
      xSpeed += -1.15f
      ball.pos.x = x - w - ball.r
      c = color(255, 0, 0)
    }
    else
      c = color(255)
  }

  def intersect(b:Ball):Boolean = (dist(x-w/2, b.pos.y, b.pos.x, b.pos.y) < b.r && b.pos.y > y-h/2 && b.pos.y < y+h/2)

  @Subscribe
  def display(status:DISPLAY_PADDLES.type) {
    fill(c)
    rectMode(CENTER)
    noStroke

    rect(x, y, w, h)
    y = constrain(y, 0+h/2, height-h/2)
  }
}
