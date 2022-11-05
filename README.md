# Antlr Playground

## Introduction
This is my first time playing with ANTLR. I've had a case where parsing PL/SQL could be
useful. There was an export similar to the example input in `constants`. Eventually
it was solved another way, but I did want do try using a parser generator, so here
is my attempt.

## Short tour
There are `.*g4` which describe the grammar, and `*Base.java` classes which provide some customisations.
ANTL4 sbt plugin generates the parser (Java classes with the said `*Base` as a superclass). Then the Scala
code uses these classes to parse some input and do the task required with the parse result.

## Done
- basic listener-based parser
- tests of the listener parser
- using the ANTL4 generator instead of keeping generated classes in the repo

## TODO
- visitor pattern + tests
- benchmark both

## Won't be done
There are things that should be done if the project were meant to be a tool used
for something more than my playground:
- custom `charStream` (uppercase, the grammar assumes so)
- semantical validation (the implementation is based on a lot of unsafe assumptions)
- better model (`type ParseResult = Map[String, String]` is good enough, but too concrete in general, I think; `opaque` or a `case class` could be a bit better;
there should be some ADT for errors, as well)

## Credits:
Source of the grammar and the `*Base.java` files: https://github.com/antlr/grammars-v4/tree/master/sql/plsql
