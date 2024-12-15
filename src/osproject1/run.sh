#!/bin/bash

rm *.class
javac *.java
java CPU $1 $2
