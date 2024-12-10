package org.example;

import java.math.BigDecimal;

public class Main {
  public static void main(String[] args) {
    System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
    System.out.println(0.1 + 0.2);
  }
}
