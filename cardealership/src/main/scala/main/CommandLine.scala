package main

import scala.util.matching.Regex
import scala.io.StdIn

object CommandLine {

  val commandArgPattern: Regex = "(\\w+)\\s*(\\w*)".r

  def welcomeMessage(): Unit = {
    println("\nWelcome to Dealership Manager\n\nBasic Commands: new, view, edit, delete, help, exit")
  }

  def menu(): Unit = {
    var runLoop = true; 

    while(runLoop){

      var input = StdIn.readLine()

      input match {
        //CREATE

        case commandArgPattern(cmd1, cmd2) if cmd1 == "new" && cmd2 == "purchase" => {
          Purchases.newPurchase()
        }

        //READ
        
        //View purchase of 1 customer
        case commandArgPattern(cmd1, cmd2) if cmd1 == "view purchase" => {

        }

        //view sum of orders by brand
        case commandArgPattern(cmd1, cmd2) if cmd1 == "view makeorders" => {

        }

        //view sum of model order with sums of trims?
        case commandArgPattern(cmd1, cmd2) if cmd1 == "view modelorders" => {

        }

        //view total number of orders
        case commandArgPattern(cmd1, cmd2) if cmd1 == "view totalorders" => {

        }

        //view sum of sales
        case commandArgPattern(cmd1, cmd2) if cmd1 == "view totalsales" => {

        }

        //idk
        case commandArgPattern(cmd1, cmd2) if cmd1 == "view sales" => {

        }

        //UPDATE

        //edit purchase info based on purchaser's credentials
        case commandArgPattern(cmd1, cmd2) if cmd1 == "edit" && cmd2 == "purchase" => {

        }

        //edit customer information
        case commandArgPattern(cmd1, cmd2) if cmd1 == "edit" && cmd2 == "customer" => {

        }

        case commandArgPattern(cmd1, cmd2) if cmd1 == "edit" && cmd2 == "help" => {
          println("edit: purchase/customer")
        }

        //DELETE

        //Delete a purchase row in db
        case commandArgPattern(cmd1, cmd2) if cmd1 == "delete purchase" => {

        }


        case commandArgPattern(cmd1, cmd2) if cmd1 == "help" => {
          println("\nnew, view, edit, delete, help, exit")
        }

        case commandArgPattern(cmd1, cmd2) if cmd1 == "exit" => {
          runLoop = false
        }
        
        case _ => {
          println("Unrecognized Command From My Program")
        }



        //update customer
        //Help ?
        //command ?

      }
    }
    println("\nGoodbye!")
  }
}