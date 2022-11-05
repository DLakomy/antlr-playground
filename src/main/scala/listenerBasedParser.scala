package antlrPlayground
package listenerBasedParser

// mutable class, using "return" keyword, variables, Java API...
// I've never written Scala code this ugly
// first time with antlr, gotta study more :/

import model._
import PlSqlParser._
import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream, tree as antlrTree}
import antlrTree.ParseTreeWalker

import collection.JavaConverters._

def parse(input: String, cfg: ParserConfig): ParseResult =

  // AFAIK the stream shold be uppercase
  // for this project this will be enough tho
  val charStream = ANTLRInputStream(input)
  val lexer = PlSqlLexer(charStream)
  val tokens = CommonTokenStream(lexer)
  val parser = PlSqlParser(tokens)
  val tree = parser.anonymous_block;

  val extractor = MsgListener(cfg)
  ParseTreeWalker.DEFAULT.walk(extractor, tree)

  extractor.result

// leaky abstraction, conceal don't feel
private class MsgListener(cfg: ParserConfig) extends PlSqlParserBaseListener:

  var result: ParseResult = Map.empty

  override def enterFunction_call(ctx: Function_callContext): Unit =
    if ctx.routine_name.getText.toLowerCase != cfg.fnName then return

    val argList = ctx.function_argument.argument
    val filtered = argList.asScala.toList.filter( arg => List(cfg.keyArgName, cfg.valueArgName) contains arg.identifier.getText )
    val grouped = filtered.grouped(2).toList

    // tuples are (msgCode, msgTxt)
    val mapped = grouped.collect {
      case List(name, message) => name.expression.getText -> message.expression.getText
    }

    result = result ++ mapped