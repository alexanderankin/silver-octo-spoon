#!/usr/bin/env bash

warn () {
  echo "$0:" "$@" >&2
}
die () {
  rc=$1
  shift
  warn "$@"
  exit $rc
}

javac src/*.java -g -d bin -Xlint:unchecked || die 1 "error compiling"
java -cp bin Main