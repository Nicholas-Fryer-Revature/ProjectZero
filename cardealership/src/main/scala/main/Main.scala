package main
object Main{

  /**
    * TODO
    * 
    * Impliment Exit from input validations
    * 
    * Make Phone uniqe constraint
    * 
    * Add try/catch for all DAO methods, catch generic exception
    * 
    * Check for integrity with update customer, They could change their name and Phone but have
    * different IDs. will break program
    * 
    */

  def main(args: Array[String]): Unit = {
    CommandLine.welcomeMessage()
    CommandLine.menu()
  }
}