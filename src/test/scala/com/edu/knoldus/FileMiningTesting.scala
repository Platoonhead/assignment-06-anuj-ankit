package com.edu.knoldus

import org.scalatest.{FlatSpec}
import scala.concurrent.duration._
import scala.concurrent.Await

class FileMiningTesting  extends FlatSpec{

  val testobject = new FileMining

  it should "match file count in directory  and underlying directory" in{

    assert(Await.result(testobject.beginWithDirectory("Folder1"),10.second).length == 7)

  }

}
