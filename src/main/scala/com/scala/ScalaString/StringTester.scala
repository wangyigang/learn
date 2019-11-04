package com.scala.ScalaString

import com.wangyg.netty.basic.util.Logger
import org.junit.Test

object StringTester {
  def main(args: Array[String]): Unit = {
    test2()
  }


  def test2(): Unit = {
    //实现break

  }

  def test1(): Unit = {
    //    val s = "hello world"
    //    println(s.length)
    //
    //    s.foreach(println) //遍历输出每个字符
    //
    //    s.getBytes.foreach(println)

    //    val result = "helllo world".filter(_!= 'l')
    //    println(result)
    //    println("scala".drop(2).take(2).capitalize) //从2开始截取，take 将下标2的去除掉

    //    val a = "Marisa"
    //    val b = "marisa"
    //
    //    println(a.equalsIgnoreCase(b))
    //    val foo ="""this is
    //        |a multiline
    //        |string
    //        |""".stripMargin.replaceAll("\r\n", " ")
    //
    //    println(foo)
    //
    //    "hello world".split(" ").foreach(println)
    //
    //    val name = "Fred"
    //    val age = 33
    //    val weight=200.00
    //    println(s"$name is $age years old, and weights $weight pounds")
    //
    //    println(f"$name is $age years old, and weight $weight%.2f pounds.")

    //    println("HELLO".map(c => (c.toByte + 32).toChar))
    //    println("HELLO".map(toLower))

    //    val numPattern = "[0-9]+".r
    val address = "123 Main Street Suite 101"
    //    val match1 = numPattern.findFirstIn(address) //findfirstIn-会查找到第一个符合的，后面的不会在进行查找
    //    Logger.info(match1)
    //    val iterator = numPattern.findAllIn(address) //返回一个迭代器，进行迭代
    //    iterator.foreach(Logger.info) //也可以转为array , list进行使用
    //
    //    match1 match {
    //      case Some(s) => Logger.info(s"found: $s")
    //      case None => _
    //    }

    //    Logger.info(address.replaceAll("[0-9]", "x")) // xxx Main Street Suite xxx
    //    //也可以创建一个正则表达式，然后在正则表达式上调用replaceAllIn，然后请把结果股给一个新的变量
    //
    //    val regx="[0-9]".r
    //    val newAddress=regx.replaceAllIn("123 Main Street", "x")
    //    Logger.info(newAddress) //将结果赋给一个新的值，然后输出新的值

    //    Logger.info("100".toInt())

    //如果要处理10进制以外的进制，可以使用Java 的ParseInt方法
    //    println(Integer.parseInt("10", 2))
    //    println(Integer.parseInt("100", 2))
    //
    //    println("100".toInt_self(16))
    //
    //    Logger.info(toInt("1").getOrElse(0))
    //    Logger.info(toInt("a").getOrElse(0))
    //    val aString = "abc"
    //    toInt(aString) match {
    //      case Some(n) => println(n)
    //      case None => println("boom that wasnt a number")
    //    }

    //    val a = 1000L
    //    println(a.isValidByte) //false
    //    println(a.isValidShort) //true

    //    val a =0:Short

    //    val a=0.3
    //    val b=0.1+0.2
    //    println(~=(a, b, 0.0001))
    //    println(a == b)
    //
    //    print(MathUtils.~=(a,b,0.00001))
    //
    //

    //    var b =BigInt(1234567890)
    //    println(b)

    //    val r = scala.util.Random
    //    println(r.nextInt(100)) //可以给定范围，生成 0- n-1范围内非数值
    //
    //    //格式化输出
    //    val pi =3.1415926
    //    println("%06.2f".format(pi))
    //
    //    val a = Array("apple", "banana","orange")
    //    val newArray= for(e<- a) yield{
    //      e.toUpperCase
    //    }
    //    newArray.foreach(println)

    //    for((e, count) <- a.zipWithIndex){
    //      println(s"$count is $e ")
    //    }
    /*
    0 is apple
    1 is banana
    2 is orange
     */

    //    for{
    //      i <- 1 to 3
    //      j <- 1 to 5
    //      k <- 1 to 10
    //    }println(s"i=$i, j=$j, k=$k")

  }


  object MathUtils {
    def ~=(x: Double, y: Double, precision: Double) = {
      if ((x - y).abs < precision) true else false
    }
  }


  def ~=(x: Double, y: Double, precision: Double) = {
    if ((x - y).abs < precision) true else false
  }


  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: NumberFormatException => None
    }
  }

  //穿件一个隐式转换的类和方法来解决这个问题
  implicit class StringToInt(s: String) {
    //传入需要转换的进制类型
    /**
     * 将名字定义加后缀  _self
     *
     * @param radix
     * @return
     */
    @throws(classOf[NumberFormatException])
    def toInt_self(radix: Int = 10) = Integer.parseInt(s, radix)
  }


  def toLower(c: Char): Char = {
    (c.toByte + 32).toChar
  }

}
