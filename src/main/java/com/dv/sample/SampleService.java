package com.dv.sample;

import org.qi4j.api.mixin.Mixins;

@Mixins(SampleService.SampleServiceMixin.class)
public interface SampleService {

    void execute();

    class SampleServiceMixin implements SampleService {

        @Override
        public void execute() {

            System.out.println("Invocation of Service");
        }
    }
}
