package org.loveyoupeng.actor;

import java.util.concurrent.atomic.AtomicInteger;

public class TestIdentifier implements Identifier {
  private static final AtomicInteger counter = new AtomicInteger(0);
  private final int value;

  public TestIdentifier() {
    this.value = counter.incrementAndGet();
  }

  @Override
  public int value() {
    return value;
  }
}
