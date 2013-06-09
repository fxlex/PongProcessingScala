package com.github.fxlex.example

import App._
import com.google.common.eventbus.Subscribe
import processing.core.PConstants._
import com.github.fxlex.library.ProcessingClass


object Gui extends ProcessingClass {
   import p._

  @Subscribe
  def player1Wins(win:WIN1.type)  = {
    textAlign(CENTER)
    fill(255)
    textSize(50)
    text("Player One Wins!!", width/2, height/2)
    textSize(25)
    text("press 'R' to restart", width/2, height/2 + 40)
  }

  @Subscribe
  def player2Wins(win:WIN2.type)  = {
    textAlign(CENTER)
    fill(255)
    textSize(50)
    text("Player Two Wins!!", width/2, height/2)
    textSize(25)
    text("press 'R' to restart", width/2, height/2 + 40)
  }

  @Subscribe
  def init(win:INIT.type)  = {
    background(0)
    textAlign(CENTER)
    fill(255)
    textSize(160)
    text("P   N G", width/2, height/2)
    textSize(20)
    text("Click to begin!", width/2,height/2+80)
    fill(3, 255, 29)
    textSize(180)
    text("O", width/4 + 102, height/2 + 5)
    fill(255)
    textAlign(LEFT)
    textSize(15)
    text("Player 1: u= 'W', down = 'S'",40f, height*0.87f)
    text("Player 2: u= 'UARROW', down  = 'DOWN ARROW'", 40f, height*0.92f)
    text("Score " + winScore +" points to win!",width*0.74f,height*.92f)
  }

  def middleLineAndBackground {
    background(0)
    for (i <- 0 to height + 50 by 50) {
      fill(255)
      noStroke
      rectMode(CENTER)
      rect(width/2, i, 7, 30)
    }
  }

  def score {
    textAlign(CENTER)
    textSize(20)
    fill(255)
    text("Player One:  " + score1, 200, 50)
    text("Player two:  " + score2, 600, 50)
  }
}
