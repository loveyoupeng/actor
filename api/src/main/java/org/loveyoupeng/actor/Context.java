package org.loveyoupeng.actor;

public interface Context {

  void send(final Identifier target, final @SuppressWarnings("rawtypes") Payload message);
}
