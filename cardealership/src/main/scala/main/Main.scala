package main

import scala.collection.mutable.Map
import scala.collection.mutable.ArrayBuffer

object Main{

  //var carOptions : ArrayBuffer[Car] = ArrayBuffer()

  def populateDataTester(): Unit ={
    val Charger = Car("Dodge", "Charger", List("SXT", "GT", "R/T", "Hellcat"), List("TorRed", "Go Mango", "Hellraisin", "Pitch Black", "F8 Green"), List("3.6 V6", "5.7 V8", "6.2 V8", "6.4 V8"))
    val Challenger = Car("Dodge", "Challenger", List("SXT", "GT", "R/T", "Hellcat"), List("TorRed", "Go Mango", "Hellraisin", "Pitch Black", "F8 Green"), List("3.6 V6", "5.7 V8", "6.2 V8", "6.4 V8"))

    //carOptions.addOne(Charger)
    //carOptions.addOne(Challenger)
  }

  def main(args: Array[String]): Unit = {
    CommandLine.welcomeMessage()
    populateDataTester()
    Purchases.newPurchase()
  }
}