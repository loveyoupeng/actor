package org.loveyoupeng.actor;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class ActorTest {

  @Test
  public void test_api() {
    final Actor actor = new Actor() {
      private final Identity identity = new Identity() {
        @Override
        public int rank() {
          return 0;
        }
      };

      @Override
      public void receive(final Event event, final Context context) {
        context.sendTo(identity(), event);
      }

      @Override
      public void start(final Context context) {
        System.out.println(context.domain().identity());
      }

      @Override
      public Identity identity() {
        return identity;
      }
    };
    final Domain domain = mock(Domain.class);
    final Context context = mock(Context.class);
    final Event event = mock(Event.class);
    doAnswer(invocation -> {
      actor.start(context);
      return null;
    }).when(domain).addActor(actor);
    when(context.domain()).thenReturn(domain);
    domain.addActor(actor);
    verify(context, times(1)).domain();
    actor.receive(event, context);
    verify(context, times(1)).sendTo(any(Identity.class), eq(event));
  }

}
