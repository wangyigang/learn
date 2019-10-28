package com.wangyg.scala

case class Person(var username:String, var password:String){
  var age = 0
  var firstName= ""
  var lastName =""
  val address = None:Option[Address]
}
case class Address(city:String, state:String, zip:String)





class Stock {
  //var
  private var price :Double = _
  def setPrice(p:Double) {price = p}
  def isHigher(that:Stock) : Boolean = {
    this.price > that.price
  }
}



object  Driver extends  App{
  val s1 = new Stock
  s1.setPrice(20)

  val s2= new Stock
  s2.setPrice(100)

  println(s2.isHigher(s1))
}
