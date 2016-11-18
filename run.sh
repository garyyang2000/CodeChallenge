#!/bin/bash
javac -cp ".:./org.json.jar" Match.java
java -cp ".:./org.json.jar" Match
curl -XPOST -F file=@results.txt https://challenge-check.sortable.com/validate
