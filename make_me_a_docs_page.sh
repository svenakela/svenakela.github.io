#!/bin/bash

rm -rf docs/*

mvn clean compile site exec:java -Dexec.mainClass=tech.docs.SearchIndexer
