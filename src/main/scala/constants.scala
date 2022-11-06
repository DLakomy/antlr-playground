package antlrPlayground
package constants

import model._

// anonymised input
// I assume, for simplicity, that the outpur looks almost exactly like that
// ie. p_txt is after p_name, and there is no other commands
val exampleInput: String =
"""BEGIN
  import_utils.add_msg(
    p_id=>utils.id(1232)
  , p_name=>'MSG_WITH_CONCAT'
  , p_lang=>'pl'
  , p_txt=>unistr('concat part1 in function')
    ||unistr(' "part two in quotes, after linebreak, in function"')
  );
  import_utils.add_msg(
    p_id=>utils.id(634)
  , p_name=>'TASK.DEF_NOT_FOUND'
  , p_lang=>'pl'
  , p_txt=>'Quite simple msg'
  );
  import_utils.add_msg(
    p_id=>utils.id(7654)
  , p_name=>'MSG3'
  , p_lang=>'pl'
  , p_txt=>string_utils.join(to_varchar2('part1'), to_varchar2('part2'))
  );
END;
"""

val expectedOutput: ParseResult = Map(
  "'MSG_WITH_CONCAT'" -> """unistr('concat part1 in function')||unistr(' "part two in quotes, after linebreak, in function"')""",
  "'TASK.DEF_NOT_FOUND'" -> "'Quite simple msg'",
  "'MSG3'" -> "string_utils.join(to_varchar2('part1'),to_varchar2('part2'))"
)

val fnName = "import_utils.add_msg"
val keyArgName = "p_name"
val valueArgName = "p_txt"
