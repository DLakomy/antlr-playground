package antlrPlayground

import model._
import constants._

@main def printResults: Unit =
  val listenerResult = listenerBasedParser.parse(
    exampleInput,
    ParserConfig(
      fnName,
      keyArgName,
      valueArgName
    )
  )

  printResult(listenerResult)


def printResult(result: ParseResult): Unit =
   result.foreachEntry { (k, v) =>
    println("MSG_KEY: "+k)
    println("MSG_TXT: "+v)
    println
  }