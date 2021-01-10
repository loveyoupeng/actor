package org.loveyoupeng.actor;

public interface Identifier extends Comparable<Identifier> {

  int value();

  @Override
  default int compareTo(final Identifier other) {
    return value() - other.value();
  }
}
