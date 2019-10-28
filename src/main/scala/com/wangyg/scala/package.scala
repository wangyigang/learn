package com.wangyg

//package obejct (和包名相同)
package object scala {
  //field
  val MAGIC_NUM=42
  //method
  def echo(a:Any){println(a)}
  //enumeration - 枚举
  object Margin extends Enumeration{
    type Margin= Value
    val TOP,BOTTOM, left,right=Value
  }
  //type definition
  
}
