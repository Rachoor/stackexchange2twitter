package org.tweet.stackexchange.persistence.setup;

import org.springframework.context.ApplicationEvent;

public final class AfterSetupEvent extends ApplicationEvent {

    public AfterSetupEvent(final Object sourceToSet) {
        super(sourceToSet);
    }

    // API

}