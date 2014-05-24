package com.dv.sample;

import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.sideeffect.SideEffects;

@SideEffects(SampleSideEffect.class)
@Mixins(SampleTransient.SampleTransientMixin.class)
public interface SampleTransient {

    void execute();

    class SampleTransientMixin implements SampleTransient {

        @Override
        public void execute() {

            System.out.println("Invocation of method");
        }
    }
}
