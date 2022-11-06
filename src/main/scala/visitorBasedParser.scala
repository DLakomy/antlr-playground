package antlrPlayground
package visitorBasedParser

import model._
import PlSqlParser._
import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream, CharStreams}

import collection.JavaConverters._

def parse(input: String, cfg: ParserConfig): ParseResult =

  // AFAIK the stream shold be uppercase
  // for this project this will be enough tho
  val charStream = CharStreams.fromString(input)
  val lexer = PlSqlLexer(charStream)
  val tokens = CommonTokenStream(lexer)
  val parser = PlSqlParser(tokens)

  val msgVisitor = new MsgVisitor(cfg)

  msgVisitor.visit(parser.anonymous_block)


private class MsgVisitor(cfg: ParserConfig) extends PlSqlParserBaseVisitor[ParseResult]:

  override def visitAnonymous_block(ctx: Anonymous_blockContext) =
    val fnCalls: List[Function_callContext] =
      ctx.seq_of_statements.statement.asScala.toList.map(_.function_call)

    val fnVisitor = AddMsgVisitor(cfg)

    val parsedCalls = fnCalls.map(_.accept(fnVisitor))

    parsedCalls.flatten.toMap


// I've mentioned something about better model... (String, String) is one of weak points here,
// it's not self explaining (unlike a case class)
private class AddMsgVisitor(cfg: ParserConfig) extends PlSqlParserBaseVisitor[Option[(String, String)]]:
  override def visitFunction_call(ctx: Function_callContext) =

    if ctx.routine_name.getText.toLowerCase != cfg.fnName then
      None
    else
      val argList = ctx.function_argument.argument
      val filtered = argList.asScala.toList.filter( arg => List(cfg.keyArgName, cfg.valueArgName) contains arg.identifier.getText )

      // assuming it has two elements (as mentioned in README: no semantical validation)
      filtered match
        case List(k, v) => Some(k.expression.getText, v.expression.getText)
        case _ => None
