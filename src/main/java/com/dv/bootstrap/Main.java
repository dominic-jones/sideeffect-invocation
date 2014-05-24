package com.dv.bootstrap;

import com.dv.sample.SampleTransient;
import org.qi4j.api.activation.ActivationException;
import org.qi4j.api.structure.Module;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.bootstrap.SingletonAssembler;

public class Main {

    public static void main(String[] args) {

        SingletonAssembler assembler;
        try {
            assembler = new SingletonAssembler() {
                @Override
                public void assemble(ModuleAssembly module) throws AssemblyException {

                    module.transients(SampleTransient.class);
                }
            };
        } catch (AssemblyException | ActivationException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        Module module = assembler.module();

        SampleTransient sampleTransient = module.newTransient(SampleTransient.class);
        sampleTransient.execute();
    }
}
