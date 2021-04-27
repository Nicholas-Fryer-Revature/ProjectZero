package main

import argonaut._, Argonaut._
import scalaz._, Scalaz._
import scala.io.BufferedSource
import scala.io.Source

object JSONUtil{

  //Read Cars.Json into a big string
  def getFileContent(): String ={
    var file: BufferedSource = null
    try{
      file = Source.fromFile("Cars.json")
      file.getLines().mkString(" ")
    }
    finally{
      if(file != null) file.close()
    }
  }

  val carJSONString = getFileContent()

  //Make List of Car Case Classes from JSON String
  implicit def CarCodecJson: CodecJson[Car] = casecodec5(Car.apply, Car.unapply)("make", "model", "trim", "color", "engine")

  val carList = carJSONString.decodeOption[List[Car]].getOrElse(Nil)
}