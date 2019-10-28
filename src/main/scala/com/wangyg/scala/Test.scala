package com.wangyg.scala

import scala.collection.mutable.ArrayBuffer

object Test {
  def test1(): Unit = {
    val b = ArrayBuffer[Int]()
    b += 1
    println(b(0))
    b += (1, 2, 3, 5)
    b.insert(2, 6)
  }


  def main(args: Array[String]): Unit = {
    test2()
  }
  def test2(): Unit ={
//    val p  = new Person("Adam", "Meyer")

    val orange = new Fruit("Orange")
  }
}
class Fruit(n: String, w: Int) {
  val name = n
  val weight = w
  println("This is a " + n)
  println("Weight = " + w)

  def this(n: String) = this(n, 10)

  override def toString: String = "wangyg"
}



////多思考
//class Person(var firstName:String , var lastName: String){
//  println("the constructor begins")
//
//  private val HOME =System.getProperty("user.home")
//
//  def printHome{ println(s"HOME = $HOME")}
//
//  var age=0
//  override def toString: String = s"$firstName $lastName is $age years old"
//}


class Persion {
  private[this] var age = 0

  print("hello")

  def test(): Unit = {
    println(age)

  }
}