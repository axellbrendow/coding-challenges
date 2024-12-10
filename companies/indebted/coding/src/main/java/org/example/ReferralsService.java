package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// interviewer feedback: use optional to force the api users to think about a missing value
// interviewer feedback: create a custom type for cents instead of using long
// (this is easier in languages that have the "type alias" feature like C, C++, and Go)

public class ReferralsService {
  public List<String> convertToListOfEmails(String email1, String email2) {
    return Stream.of(email1, email2).filter(Objects::nonNull).collect(Collectors.toSet()).stream().toList();
  }

  /**
   * @param balance balance is expected to be in dollars
   * @return the value in cents
   */
  public Optional<Long> convertBalance(BigDecimal balance) {
    if (balance == null) return Optional.empty();
    return Optional.of(balance.multiply(BigDecimal.valueOf(100)).longValue());
  }

  private static final DateTimeFormatter CLIENT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final DateTimeFormatter INDEBTED_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public Optional<String> convertDate(String date) {
    if (date == null) return Optional.empty();
    final var localDate = LocalDate.parse(date, CLIENT_DATE_FORMAT);
    return Optional.of(localDate.format(INDEBTED_DATE_FORMAT));
  }
}
