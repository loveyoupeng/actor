package org.loveyoupeng.actor;

public interface Identity {
  default int rank() { return hashCode(); }
  default String value() { return toString(); }
}
