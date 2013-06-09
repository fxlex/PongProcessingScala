package com.github.fxlex.example

import com.google.common.eventbus.Subscribe
import processing.core.PConstants._
import processing.core.PApplet._
import com.github.fxlex.example.App
import App._
import com.github.fxlex.library.{ProcessingClassConfig, ProcessingClass}

case class PaddleLeft(var x:Float, var y:Float,var w:Float=20,var h:Float=100,
                      var c:Int=ProcessingClassConfig.p.color(255),
                      var intersect:Boolean=false) extends ProcessingClass {

  import p._

  @Subscribe
  def moveUp(status:LEFT_PLAYER_UP.type) = y-= 10

  @Subscribe
  def moveDown(status:LEFT_PLAYER_DOWN.type) = y += 10

  @Subscribe
  def collisionDetection(status:COLLISION_DETECTION.type) {
    if (intersect(ball)) {
      xSpeed *= -1.15f
      ball.pos.x = x + w + ball.r
      c = color(255, 0, 0)
    }
    else
      c = color(255)
  }

  def intersect(b:Ball) = dist(x+w/2, b.pos.y, b.pos.x, b.pos.y) < b.r && b.pos.y > y-h/2 && b.pos.y < y+h/2

  @Subscribe
  def display(status:DISPLAY_PADDLES.type) {
    fill(c)
    rectMode(CENTER)
    noStroke

    rect(x, y, w, h)
    y = constrain(y, 0+h/2, height-h/2)
  }
}