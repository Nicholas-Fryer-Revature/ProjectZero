package main

import scala.util.matching.Regex
import scala.io.StdIn

object CommandLine {

  val commandArgPattern: Regex = "(\\w+)\\s*(\\w*)".r

  def welcomeMessage(): Unit = {
    println("\nWelcome to Dealership Manager")
  }

  def commandMessage(): Unit = {
    println("\nBasic Commands: new, view, edit, delete, help, exit")
  }

  def menu(): Unit = {
    var runLoop = true; 

    while(runLoop){
      commandMessage()
      var input = StdIn.readLine().toLowerCase().trim()

      input match {
        //CREATE

        case commandArgPattern(cmd1, cmd2) if cmd1 == "new" && cmd2 == "purchase" => {
          Purchases.newPurchase()
        }


        //READ
        
        //View All Customers Ever
        case commandArgPattern(cmd1, cmd2) if cmd1 == "view" && cmd2 == "customers" => {
          DAO.printCustomers()
        }

        //View purchase of 1 customer
        case commandArgPattern(cmd1, cmd2) if cmd1 == "view" && cmd2 == "purchases" => {
          Purchases.viewPurchases()
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

        case commandArgPattern(cmd1, cmd2) if cmd1 == "view" && cmd2 == "help" => {
          println("\nView Commands")
          println("customers-------View All Customers Ever")
          println("purchases-------View All Purchases From 1 Customer")
        }

        //idk
        case commandArgPattern(cmd1, cmd2) if cmd1 == "view sales" => {

        }

        //UPDATE

        //edit purchase info based on purchaser's credentials
        case commandArgPattern(cmd1, cmd2) if cmd1 == "edit" && cmd2 == "purchase" => {
          Purchases.updatePurchase()
        }

        //edit customer information
        case commandArgPattern(cmd1, cmd2) if cmd1 == "edit" && cmd2 == "customer" => {
          Purchases.updateCustomer()
        }

        case commandArgPattern(cmd1, cmd2) if cmd1 == "edit" && cmd2 == "help" => {
          println("\nEdit Commands")
          println("purchase-------Edit A Customer's Purchase Information")
          println("customer-------Edit A Customer's Personal Information")
        }

        //DELETE

        //Delete a purchase row in db
        case commandArgPattern(cmd1, cmd2) if cmd1 == "delete" && cmd2 == "purchase" => {
          Purchases.deletePurchase()
        }

        //Delete customer and their purchases from DB
        case commandArgPattern(cmd1, cmd2) if cmd1 == "delete" && cmd2 == "customer" => {
          Purchases.deleteCustomer()
        }

        case commandArgPattern(cmd1, cmd2) if cmd1 == "delete" && cmd2 == "help" => {
          println("\nDelete Commands")
          println("purchase-------Delete A Customer's Purchase")
          println("customer-------Delete A Customer And Their Purchases")
        }


        
        case commandArgPattern(cmd1, cmd2) if cmd1 == "help" => {
          commandMessage()
          println("new--------Enter A New Purchase")
          println("view-------View Purchases")
          println("edit-------Edit Purchases And Customers")
          println("delete-----Delete Purchases And Customers")
          println("help-------\"basic command\" + help Prints Available Commands.")
          println("exit-------Exit The Program")
        }

        case commandArgPattern(cmd1, cmd2) if cmd1 == "exit" => {
          runLoop = false
        }
        
        case _ => {
          println("Unrecognized Command")
        }
      }
    }
    println("\nGoodbye!")
  }
}