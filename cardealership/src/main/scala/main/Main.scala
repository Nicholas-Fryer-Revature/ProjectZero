package main

import scala.collection.mutable.Map
import scala.collection.mutable.ArrayBuffer
//import util.PostgreSQLUtil

object Main{

  /*
  var carOptions : ArrayBuffer[Car] = ArrayBuffer()

  def populateDataTester(): Unit ={
    val Charger = Car("Dodge", "Charger", List("SXT", "GT", "R/T", "Hellcat"), List("TorRed", "Go Mango", "Hellraisin", "Pitch Black", "F8 Green"), List("3.6 V6", "5.7 V8", "6.2 V8", "6.4 V8"))
    val Challenger = Car("Dodge", "Challenger", List("SXT", "GT", "R/T", "Hellcat"), List("TorRed", "Go Mango", "Hellraisin", "Pitch Black", "F8 Green"), List("3.6 V6", "5.7 V8", "6.2 V8", "6.4 V8"))

    carOptions.addOne(Charger)
    carOptions.addOne(Challenger)
  }
  */

  def main(args: Array[String]): Unit = {
    DAO.viewAll()
    DAO.testExists("Nic")
    DAO.insertPuchase("Nick", "Fryer", "419-217-7041", "9404 Bemis Rd Bellevue, OH 44811", "Dodge", "Charger", "R/T", "Go Mango", "5.7 V8", 30000)
    CommandLine.welcomeMessage()
    //CommandLine.menu()
  }
}