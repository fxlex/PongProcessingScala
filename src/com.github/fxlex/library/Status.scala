package com.github.fxlex.library

import com.google.common.eventbus.EventBus


object BaseIdGenerator {
  var nextBaseId = 1

  def next() = {
    nextBaseId += 1
    BigInt(2).pow(nextBaseId)
  }
}

abstract class StatusCode   {
  var id:BigInt = BaseIdGenerator.next()

  def &(statusCode:StatusCode) = {
    object s extends StatusCode
    s.id = this.id | statusCode.id
    s
  }

  def unary_!() = {
    object s extends StatusCode
    s.id = -this.id
    s
  }

}


object Status {

  var eventBus:EventBus = _
  def init(_eventBus:EventBus)  = eventBus = _eventBus

  private var status = BigInt(0)

  private def notifyChanges(statusCode:StatusCode) = {
    eventBus.post(statusCode)
  }


  def +=(statusCode:StatusCode) = {
    status |=  statusCode.id
    notifyChanges(statusCode)
    this
  }

  def -=(statusCode:StatusCode) = {
    status &~=  statusCode.id
    this
  }

  def apply(expr:Boolean,statusCode:StatusCode):Unit = {
       if(expr) apply(statusCode)
  }

  def apply(statusCode:StatusCode) = {
    status = statusCode.id
    notifyChanges(statusCode)
  }
  def apply(statusCode:Int) = status = statusCode

  def ?(statusCode:StatusCode):Boolean = {
   (status & statusCode.id)  == statusCode.id
  }

  def ?!(statusCode:StatusCode):Boolean = ! ?(statusCode)



}


