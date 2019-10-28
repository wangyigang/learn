package com.wangyg.scala

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.util.Random

object DateTest extends App {

  println(String.format("%06d", Int.box(Random.nextInt(100000))))

}


object Test2 {
  def main(args: Array[String]): Unit = {
    test17()
  }

  def test17(): Unit = {
    var dupCNT = 1
    if (dupCNT > 0) {
      throw new Exception(s"$dupCNT duplicated IDs found")
    }
    println("hello")
  }

  //使用偏函数解决
  def test16(): Unit = {
    val list = List(1, 2, 3, 4, "hello")
    //定义一个偏函数
    //1. partitialFunction[Any,Int] 表示偏函数接收的参数时any,返回类型是Int
    //2. isdefinedAt() 如果返回true,就会调用apply方法，如果false，就过滤掉
    //3. apply 构造器，对传入的值+1 , 并返回新的集合
    val par = new PartialFunction[Any, Int] {
      override def isDefinedAt(x: Any): Boolean = {
        x.isInstanceOf[Int]
      }

      override def apply(v1: Any): Int = {
        v1.asInstanceOf[Int] + 1
      }
    }
    //偏函数的使用
    val list2 = list.collect(par)
    println(list2)

    def par2: PartialFunction[Any, Int] = {
      case i: Int => i + 1
      case d: Double => (d * 2).toInt
    }

    val list4 = List(1, 2, 3, 4.0)
    val list5: List[Int] = list4.collect(par2)
    println(list5)
  }

  //模式匹配，匹配对象
  def test15(): Unit = {

  }

  object Square {
    def unapply(arg: Double): Option[Double] = Some(math.sqrt(arg))

    def apply(z: Double): Double = z * z
  }

  def test14(): Unit = {
    val ch = 'V'
    val res = ch match {
      case '+' => println("ok~")
      // 1. mychar = ch  2.  然后再去匹配  3. 这样的语法，我们称为模式中的变量
      case mychar if mychar.equals('n') => println("ok~" + mychar)
      case _ => println("ok~~")
    }
  }

  //模式匹配
  def test13(): Unit = {
    val oper = '+'
    val n1 = 20
    val n2 = 10
    var res = 0
    oper match {
      case '+' => res = n1 + n2
      case '-' => res = n1 - n2
      case '*' => res = n1 * n2
      case '/' => res = n1 / n2
      case _ => println("nothing")
    }
    println("res=" + res)
  }

  def test12(): Unit = {
    //并行计算
    (1 to 100).par.foreach(println)
  }

  //迭代器
  def test11(): Unit = {
    val iterator = List(1, 2, 3, 4, 5).iterator //  获取到迭代器 [链表实现]
    println("--------遍历方式1 -----------------")
    while (iterator.hasNext) { // hasNext 方法可以判断是否有下一个
      println(iterator.next()) // next 取出下一个值
    }
    println("--------遍历方式2 for -----------------")
    val iterator2 = List(11, 22, 33, 44, 55).iterator
    for (enum <- iterator2) {
      println(enum) //
    }
  }

  def test10(): Unit = {
    val i8 = (1 to 3).scanLeft(3)(test)
    println(i8)
  }

  def test(num1: Int, num2: Int): Int = {
    num1 * num2
  }

  def test9(): Unit = {
    val names = List("Alice", "Bob", "Nick")

    def upper(s: String): String = {
      s.toUpperCase
    }
    //注意：每个字符串也是char集合
    println(names.flatMap(upper)) // List(A,l,c,e....)
  }


  def test8(): Unit = {
    val list = ListBuffer(1, 2, 3)

  }

  def test7(): Unit = {
    //    val q1 = new mutable.Queue[Int]()
    //    q1 ++= mutable.Queue(1,2,3,4,5)
    //    println(q1)
    val set = mutable.Set(1, 2, 3)
    set.remove(1)

    println(set)
  }

  def test6(): Unit = {
    val list4 = List(1, 2, 3, "abc")
    val list5 = 4 :: 5 :: 6 :: list4 :: Nil
    println(list5)
  }

  //添加元素
  def test5(): Unit = {
    var list = List(1, 2, 3, "abc")
    val list2: Any = 4 +: list
    println(list2.toString)
    println(list)
  }

  def test4(): Unit = {
    val tup = (1, 2, 3)
    println(tup.productElement(0))
  }

  def test3(): Unit = {
    //字符串就是一个char的集合 indexSeq
    val arr = ArrayBuffer(2, 3, 5)
    val arr2 = ArrayBuffer(1, 2, 3)
    arr.appendAll(arr2)
    for (elem <- arr) {
      println(elem)
    }
  }


  def test2(): Unit = {
    println(fbn(3))
  }

  def fbn(n: Int): Int = {
    if (n == 1 || n == 2) {
      1
    } else {
      fbn(n - 1) + fbn(n - 2)
    }
  }

  def test(): Unit = {
    val dog = new Dog
    println(dog.sum(10, 20))

    val f1 = dog.sum _
    println("f1" + f1)
    println(f1(50, 60))
  }
}

class Dog {
  def sum(n1: Int, n2: Int) {
    n1 + n2
  }
}

