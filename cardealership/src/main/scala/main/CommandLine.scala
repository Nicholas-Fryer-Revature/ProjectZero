package main

import scala.util.matching.Regex
import scala.io.StdIn

object CommandLine {

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  def welcomeMessage(): Unit = {
    println("\nWelcome to Dealership Manager\n")
  }

  def menu(): Unit = {
    var runLoop = true; 

    while(runLoop){

      var input = StdIn.readLine()

      input match {
        case commandArgPattern(cmd, arg) if cmd == "newpurchase" => {
          Purchases.newPurchase()
        }

        case commandArgPattern(cmd, arg) if cmd == "viewpurchase" => {

        }

        case commandArgPattern(cmd, arg) if cmd == "editpurchase" => {

        }

      }
    }
  }
}