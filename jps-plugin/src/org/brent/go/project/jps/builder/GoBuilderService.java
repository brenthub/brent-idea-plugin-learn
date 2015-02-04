package org.brent.go.project.jps.builder;

import com.intellij.openapi.diagnostic.Logger;
import org.brent.go.project.jps.model.JpsGoModuleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildOutputConsumer;
import org.jetbrains.jps.builders.BuildTarget;
import org.jetbrains.jps.builders.BuildTargetType;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ProjectBuildException;
import org.jetbrains.jps.incremental.TargetBuilder;
import org.jetbrains.jps.incremental.resources.ResourcesBuilder;
import org.jetbrains.jps.incremental.resources.StandardResourceBuilderEnabler;
import org.jetbrains.jps.model.module.JpsModule;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by brent on 2015/2/4.
 */
public class GoBuilderService extends TargetBuilder<GoSourceRootDescriptor, GoTarget> {

    public static final String NAME = "go";

    private final static Logger LOG = Logger.getInstance(GoBuilderService.class);

    protected GoBuilderService(Collection<? extends BuildTargetType<? extends GoTarget>> buildTargetTypes) {
        super(Arrays.asList(GoTargetType.PRODUCTION, GoTargetType.TESTS));
        ResourcesBuilder.registerEnabler(new StandardResourceBuilderEnabler() {
            @Override
            public boolean isResourceProcessingEnabled(JpsModule jpsModule) {
                return !(jpsModule.getModuleType() instanceof JpsGoModuleType);
            }
        });
    }

    @Override
    public void build(@NotNull GoTarget goTarget,
                      @NotNull DirtyFilesHolder<GoSourceRootDescriptor,
                              GoTarget> dirtyFilesHolder,
                      @NotNull BuildOutputConsumer buildOutputConsumer,
                      @NotNull CompileContext compileContext) throws ProjectBuildException, IOException {

        LOG.info("test...");
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return null;
    }
}
