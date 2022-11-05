package antlrPlayground
package tests

import constants._
import model._
import listenerBasedParser.parse
import listenerBasedParser.MsgListener // TODO hide better
import org.scalatest.flatspec.AnyFlatSpec

class ListenerBasedParserSpec extends AnyFlatSpec:

  "The listener based parser" should "return an expected output" in {
    val output = parse(
      exampleInput,
      ParserConfig(
        fnName,
        keyArgName,
        valueArgName
      )
    )

    // _.toString was chosen, since the failure is very readable
    assert(output.toString==expectedOutput.toString)
  }
