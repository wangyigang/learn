package com.wangyg.scala

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{HTable, Put}
import org.apache.hadoop.hbase.mapreduce.TableInputFormat

object HbaseAPiTest {
  def main(args: Array[String]): Unit = {

  }
  def test2(): Unit ={

  }



  def test1(): Unit ={
    val conf = HBaseConfiguration.create()
    val tableName = "/iteblog"
    conf.set(TableInputFormat.INPUT_TABLE, tableName)
    val myTable = new HTable(conf, tableName);
    var p:Put = null
    p = new Put(new String("row999").getBytes());
    p.add("cf".getBytes(), "column_name".getBytes(), new String("value999").getBytes());
    myTable.put(p);
    myTable.flushCommits();
  }

}
