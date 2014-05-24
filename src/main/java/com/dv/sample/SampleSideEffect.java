package com.dv.sample;

import org.qi4j.api.sideeffect.SideEffectOf;

public abstract class SampleSideEffect extends SideEffectOf<SampleTransient> implements SampleTransient {

    @Override
    public void execute() {

        System.out.println("Invocation of SideEffect");
    }
}
