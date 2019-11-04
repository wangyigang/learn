package com.scala.chapter4

import com.wangyg.netty.basic.util.Logger


/**
 * 为类创建一个 主构造函数
 */
object MainConstrutorTester  extends  App {
  val p = new Person("wang", "yigang")

}

/**
 * 测试： 调用构造函数时，是否会调用类内的所有属性的定义和方法的执行
 *
 * @param firstName
 *
 * @param lastName
 */
class Person(var firstName :String , var lastName : String){
  println(" the constructor begins")
  //包含类字段
  private val HOME=System.getProperty("user.home")
  var age =0

  //包含字段方法i
  override def toString: String = s"$firstName $lastName is $age years old"

  def test1(){
    Logger.info("test1()")
    this.age = 10
  }


}
