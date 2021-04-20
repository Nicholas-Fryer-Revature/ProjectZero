package main

import argonaut._, Argonaut._
import scalaz._, Scalaz._
import scala.io.BufferedSource
import scala.io.Source
//import main.Car 

object JSONUtil{

  case class Car(
  make: String, 
  model: String, 
  trim: List[String],
  color: List[String],
  engine: List[String]
  )

  //Read File into Collection Main.carOptions
  /*
  val carJSONString = (s:String) => {
    var file: BufferedSource = null
    try{
      file = Source.fromFile("Cars.json")
      file.getLines().mkString(" ")
    }
    finally{
      if(file != null) file.close()
    }
  }
  */

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
  //Read Cars.Json into a big string

  implicit def CarCodecJson: CodecJson[Car] = casecodec5(Car.apply, Car.unapply)("make", "model", "trim", "color", "engine")

  val carList = carJSONString.decodeOption[List[Car]].getOrElse(Nil)
  //val carList: Option[List[Car]] = Parse.decodeOption[List[Car]](carJSONString)
  //val carList = carJSONString.decodeOption[List[Car]].getOrElse(Nil)
  //val people = carJSONString.decodeOption[List[Person]].getOrElse(Nil)
  //val carOptions = Parse.decodeOption[List[Car]].getOrElse(Nil)
}