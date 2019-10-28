package com.wangyg.scala

package otherscope {
  class  Foo{
    def exec(f:(String) => Unit, name: String): Unit ={
      f(name)  //传入一个函数，一个name名字
    }
  }
}

object  ClosureExeaple extends  App{

  def sayHello(name: String): Unit ={
    println(s"$hello, $name")
  }
  var hello = "Hello"
  val foo = new otherscope.Foo
  foo.exec(sayHello,"AI")

  hello = "Hola" //foo.exec()执行方法时，$hello也是作用于范围外的数据，但是也使用了，这个就是闭包，生命周期一致，封闭范围内的一个对变量的引用
  foo.exec(sayHello, "Lorenzo")


}

object test1 extends  App{
  //在= 号右边进行返回一个匿名函数 () => {}
  def saySomthing(prefix: String) =(s:String) => {prefix+ " "+ s}
  val sayhello =saySomthing("hello")
  println(sayhello("ai"))
}

object  test2 extends  App{
  val seq = scala.collection.immutable.LinearSeq(1,2,3)
  println(seq.head)
  println(seq.tail)
  println(seq.isEmpty)
}

object  test3 extends  App{
    var DB_TYPE = "MYSQL"
  DB_TYPE match {
    case "MYSQL" => println("mysql")
    case _ => throw new Exception(s"Does not support database $DB_TYPE.")
  }
}

object test4 extends  App{
  val count = 21398
  for (i <- Range( 0, (count/100)+1)){
    val startRow = i*100
    val endRow = startRow + 100
    println(endRow)
  }
}