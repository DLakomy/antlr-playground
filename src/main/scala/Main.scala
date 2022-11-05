package antlrPlayground

import model._

@main def printResults: Unit =
  val listenerResult = listenerBasedParser.parse(
    constants.exampleInput,
    constants.fnName,
    constants.keyArgName,
    constants.valueArgName)

  printResult(listenerResult)


def printResult(result: ParseResult): Unit =
   result.foreachEntry { (k, v) =>
    println("MSG_KEY: "+k)
    println("MSG_TXT: "+v)
    println
  }