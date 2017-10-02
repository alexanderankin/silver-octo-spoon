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

javac src/*.java -d bin || die 1 "error compiling"
java -cp bin Main