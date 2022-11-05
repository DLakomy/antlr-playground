# Antlr Playground

## Introduction
This is my first time playing with ANTLR. I've had a case where parsing PL/SQL could be
useful. There was an export similar to the example input in `constants`. Eventually
it was solved another way, but I did want do try using a parser generator, so here
is my attempt.

## Done
- basic listener-based parser

## TODO
- tests
- replace Java with G4 (+ sbt plugin)
- visitor pattern
- benchmark both

## Won't be done
There are things that should be done if the project were meant to be a tool used
for something more than my playground:
- custom `charStream` (uppercase, the grammar assumes so)
- semantical validation (the implementation is based on a lot of unsafe assumptions)
- better model (`type ParseResult = Map[String, String]` is good enough, but too concrete in general, I think; `opaque` or a `case class` could be a bit better;
there should be some ADT for errors, as well)