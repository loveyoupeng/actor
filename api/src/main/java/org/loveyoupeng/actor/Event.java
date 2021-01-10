package org.loveyoupeng.actor;

public interface Event extends Identifiable {
  Identifier target();
  Identifier source();

  @SuppressWarnings({"rawtypes"})
  Payload payload();
}
