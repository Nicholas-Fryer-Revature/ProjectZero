package main

import scala.collection.mutable.ArrayBuffer

case class Car(
  make: String, 
  model: String, 
  trim: Array[String],
  color: Array[String],
  engine: Array[String]
)