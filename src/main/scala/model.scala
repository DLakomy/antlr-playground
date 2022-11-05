package antlrPlayground
package model

// no opaque for simplicity
type ParseResult = Map[String, String]

final case class ParserConfig(fnName: String, keyArgName: String, valueArgName: String)
