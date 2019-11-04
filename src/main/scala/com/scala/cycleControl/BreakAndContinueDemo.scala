package com.scala.cycleControl

import com.wangyg.netty.basic.util.Logger

import scala.util.control.Breaks._

object BreakAndContinueDemo extends App {
  //  println("\n=====BREAK EXAMPLE===")
  //  //breakable
  //  breakable{
  //    for(i <- 1 to 10) {
  //      println(i)
  //      if (i > 4)
  //        break //break执行时，
  //    }
  //  }
  //  println("\n ====continue ====")
  //  for(x<- 1 to 10) {
  //    breakable{
  //
  //      if(x ==4)
  //        break()
  //
  //      println(x) //没有4
  //    }
  //  }

  //  val  i= 10
  //  i match {
  //    case x => Logger.info(x)
  //  }

  //匹配表达式中使用Case类 并添加 if逻辑判断
  //  val i =1
  //
  //  i match {
  //    case a if 0 to 9 contains a => println("0-9 range:" +a)
  //    case b if 10 to 19 contains b => println("10-19 range:" + b)
  //    case c if 20 to 29 contains c => println("20-29 range :" + c)
  //    case _ => println("hm....")
  //  }
  //
  //  val s="Foo"
  //  try {
  //    val i = s.toInt
  //  }catch {
  //    case e : Exception => e.printStackTrace()
  //  }




}


//trait  Animal
//case class Dog(name: String) extends  Animal
//case class Cat(name: String) extends  Animal
//
//case object Woodpecker extends  Animal
//
//object caseClassTest extends  App{
//  println(determineType(new Dog("Rockey")))
//  println(determineType(new Cat("Rust the Cat")))
//
//  println(determineType(Woodpecker))
//
//  def determineType(x:Animal): String ={
//    x match {
//      case Dog(moniker) => "got a Dog, name=" + moniker
//      case _:Cat => "got a cat "
//      case Woodpecker => "that was a Woodpecker"
//      case _=> "that was something else"
//    }
//  }
//}
