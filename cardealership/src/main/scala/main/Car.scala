package main

import scala.collection.mutable.ArrayBuffer

case class Car(
  make: String, 
  model: String, 
  trim: List[String],
  color: List[String],
  engine: List[String]
)