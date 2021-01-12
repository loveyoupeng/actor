package org.loveyoupeng.actor;

public abstract class TypedActor implements Actor {

  @SuppressWarnings("rawtypes")
  private Dispatcher dispatcher;

  void setDispatcher(@SuppressWarnings("rawtypes") final Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public final void receive(final Event event, final Context context) {
    dispatcher.setContext(context);
    event.payload().dispatch(dispatcher);
  }
}
