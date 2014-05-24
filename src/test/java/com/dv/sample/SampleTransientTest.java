package com.dv.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.qi4j.api.composite.TransientBuilderFactory;
import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.mixin.Mixins;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.test.AbstractQi4jTest;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SampleTransientTest extends AbstractQi4jTest {

    @Structure
    TransientBuilderFactory transientBuilderFactory;

    @Mock
    static MockHandler mockHandler;

    @Before
    public void resetHandler() {

        reset(mockHandler);
    }

    @Override
    public void assemble(ModuleAssembly module) throws AssemblyException {

        module.services(MockService.class);

        module.transients(SampleTransient.class);
    }

    @Test
    public void givenTransientWithSideEffectsWhenInvokingMethodShouldExecuteSideEffectOnlyOnce() {

        SampleTransient sample = transientBuilderFactory.newTransient(SampleTransient.class);

        sample.execute();

        verify(mockHandler, times(1)).handle();
    }

    @Mixins(MockService.MockServiceMixin.class)
    public interface MockService extends SampleService {

        class MockServiceMixin implements MockService {

            @Override
            public void execute() {

                mockHandler.handle();
            }
        }
    }

}
