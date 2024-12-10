package org.example;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ReferralsServiceTest {
  @Test
  public void testBalance() {
    final var service = new ReferralsService();
    assertEquals(Optional.empty(), service.convertBalance(null));
    assertEquals(Optional.of(8525L), service.convertBalance(new BigDecimal("85.25")));
  }

  @Test
  public void testDateConversion() {
    final var service = new ReferralsService();

    assertEquals(Optional.empty(), service.convertDate(null));

    assertEquals(Optional.of("2023-01-20"), service.convertDate("20/01/2023"));
    assertEquals(Optional.of("2023-02-10"), service.convertDate("10/02/2023"));

    assertThrows(Exception.class, () -> service.convertDate("10/02/20233"));
    assertThrows(Exception.class, () -> service.convertDate("100220233"));
    assertThrows(Exception.class, () -> service.convertDate("10/0220233"));
    assertThrows(Exception.class, () -> service.convertDate("1002/20233"));
    assertThrows(Exception.class, () -> service.convertDate("/02/2024"));
    assertThrows(Exception.class, () -> service.convertDate("10//2023"));
    assertThrows(Exception.class, () -> service.convertDate("10/02/"));
    assertThrows(Exception.class, () -> service.convertDate("10//"));
    assertThrows(Exception.class, () -> service.convertDate("/02/"));
    assertThrows(Exception.class, () -> service.convertDate("//2023"));
    assertThrows(Exception.class, () -> service.convertDate("//"));
    assertThrows(Exception.class, () -> service.convertDate(""));
  }

  @Test
  public void testEmails() {
    final var service = new ReferralsService();
    assertEquals(List.of(), service.convertToListOfEmails(null, null));

    assertEquals(
      List.of("john.s.doe@indebted.co"),
      service.convertToListOfEmails("john.s.doe@indebted.co", null)
    );

    assertEquals(
      List.of("john.s.doe@indebted.co", "john.s.doe.backup@indebted.co"),
      service.convertToListOfEmails("john.s.doe@indebted.co", "john.s.doe.backup@indebted.co")
    );

    assertEquals(
      List.of("john.s.doe@indebted.co"),
      service.convertToListOfEmails("john.s.doe@indebted.co", "john.s.doe@indebted.co")
    );
  }
}
