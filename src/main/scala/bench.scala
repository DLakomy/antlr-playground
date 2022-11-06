package antlrPlayground
package bench

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra._
import constants._
import model._

@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
class ListenerVsVisitorParser:
  val cfg = ParserConfig(
    fnName,
    keyArgName,
    valueArgName
  )

  @Benchmark def listener(blackhole: Blackhole) =
    val result = listenerBasedParser.parse(
      exampleInput,
      cfg
    )
    blackhole.consume(result)

  @Benchmark def visitor(blackhole: Blackhole) =
    val result = visitorBasedParser.parse(
      exampleInput,
      cfg
    )
    blackhole.consume(result)

