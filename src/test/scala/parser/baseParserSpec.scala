package antlrPlayground
package tests

import constants._
import model._
import org.scalatest.flatspec.AnyFlatSpec

abstract class BaseParserSpec(parserFn: (String, ParserConfig) => ParseResult) extends AnyFlatSpec:

  "The listener based parser" should "return an expected output" in {
    val output = parserFn(
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
