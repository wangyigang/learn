package com.wangyg.com.wangyg.test

object Test0827 {
  def main(args: Array[String]): Unit = {
    val dawn = Person("Dawn")
    val a = Array(Person("wangy"), Person("yi"), Person("gang"))
    println(a.mkString(","))

    val p = Person1("gg")
    println(p.name)
  }
}

class Person {
  var name: String = _

  override def toString: String = name
}

object Person {
  //通过伴生类中的apply方法构造
  def apply(name: String): Person = {
    val p = new Person
    p.name = name
    p
  }
}

//将类声明为case class
case class Person1(var name: String, var age: Int)

// 为case 类添加多个构造函数
object Person1 {

  def apply() = new Person1("noname", 0)

  def apply(name: String) = new Person1(name, 0)

}

trait Animal {
  def speak
}

//创建一个伴生类
object Animal {

  private class Dog extends Animal {
    override def speak: Unit = {
      println("Dog...")
    }
  }

  private class Cat extends Animal {
    override def speak = {
      println("Cat...")
    }
  }

  //定义apply方法
  def apply(s: String): Animal = {
    if (s == "dog") new Dog
    else new Cat
  }
}
object  test extends  App{
//  val cat = Animal("cat")
//  cat.speak
//  val dog = Animal("dog")
//  dog.speak

  val a  =Array(1,2,4)
  val b = a.find( {x:Int => x>2} ) //只会找到一个
  println(b)// Some(3)

}
