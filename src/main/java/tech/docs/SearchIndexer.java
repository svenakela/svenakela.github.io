package tech.docs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SearchIndexer {
  
  static private String DIR = "./docs";
  static private String OUT = "search.json";
  
  public static void main(String[] args) throws IOException {
    
    var directory = Paths.get(DIR);
    var output = Paths.get(String.join("/", DIR, OUT));
    var mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    try (Stream<Path> paths=Files.walk(directory)) {
      var indeces = paths
        .filter(Files::isRegularFile)
        .filter(f -> f.toString().toLowerCase().endsWith(".html"))
        .map(SearchIndexer::createSearchRecord)
        .mapMulti(Optional::ifPresent)
        .collect(Collectors.toList());
        
        Files.write(output, mapper.writeValueAsBytes(indeces));
    }
  }
  
  private static Optional<SearchRecord> createSearchRecord(Path path) {
    try {
      var text = Jsoup
          .parse(path.toFile(), "UTF-8")
          .select("header")
          .next()
          .text()
          .replaceAll("\\R+", " ")  // new lines on all OSes
          .replaceAll("[\"`'\\\\]", "");
      var filePath = path.toString().replaceAll(DIR, ".");
      
      return Optional.of(new SearchRecord(filePath, text));
      
    } catch (IOException e) {
      System.out.println("Failed file: " + path);
      return Optional.empty();
    }
  }
  
}
