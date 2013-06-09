package com.github.fxlex.library

import processing.core.PApplet

class ProcessingClass {
  val  p = ProcessingClassConfig.p
}

object ProcessingClassConfig {
  var p:PApplet = _
  def apply(_p:PApplet) = p = _p
}

