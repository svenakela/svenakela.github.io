#!/bin/bash

rm -rf docs/*

mvn clean compile generate-resources site exec:java -Dexec.mainClass=tech.docs.SearchIndexer
