package org.loveyoupeng.actor;

public interface Dispatcher<D extends Dispatcher<D>> {

  void setContext(final Context context);
}
