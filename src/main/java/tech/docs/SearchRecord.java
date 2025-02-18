package tech.docs;

import java.time.Instant;

public record SearchRecord(long id, String path, String text) {
  
  private static long nanoId() {
    var i = Instant.now();
    return Double.valueOf(i.getEpochSecond() * 1E9 + i.getNano()).longValue();
  }
  
  public SearchRecord(String path, String text) {
    
    this(nanoId(), path, text);
  }
}
