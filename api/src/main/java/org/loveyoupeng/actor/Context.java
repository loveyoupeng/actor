package org.loveyoupeng.actor;

public interface Context {

  void send(Identifier target, @SuppressWarnings("rawtypes") Payload message);
}
