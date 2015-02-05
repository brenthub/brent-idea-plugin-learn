package org.brent.go.jps.builder;

import org.brent.go.jps.model.JpsGoModuleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.*;
import org.jetbrains.jps.builders.storage.BuildDataPaths;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.indices.IgnoredFileIndex;
import org.jetbrains.jps.indices.ModuleExcludeIndex;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.java.JpsJavaClasspathKind;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import org.jetbrains.jps.model.module.JpsModule;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by brent on 2015/2/4.
 */
public class GoTarget extends ModuleBasedTarget<GoSourceRootDescriptor> {


    public GoTarget( @NotNull JpsModule module,ModuleBasedBuildTargetType<?> targetType) {
        super(targetType, module);
    }

    @Override
    public boolean isTests() {
        return false;
    }

    @Override
    public String getId() {
        return myModule.getName();
    }

    @Override
    public Collection<BuildTarget<?>> computeDependencies(BuildTargetRegistry buildTargetRegistry, TargetOutputIndex targetOutputIndex) {
        List<BuildTarget<?>> dependencies=new ArrayList<BuildTarget<?>>();
        Set<JpsModule> mudules= JpsJavaExtensionService.dependencies(myModule)
                .includedIn(JpsJavaClasspathKind.compile(isTests())).getModules();
        for (JpsModule module: mudules){
            if (module.getModuleType().equals(JpsGoModuleType.INSTANCE)){
                dependencies.add(new GoTarget(module,getGoTargetType()));
            }
        }
        return null;
    }

    public GoTargetType getGoTargetType(){
        return (GoTargetType)getTargetType();
    }

    @NotNull
    @Override
    public List<GoSourceRootDescriptor> computeRootDescriptors(JpsModel jpsModel, ModuleExcludeIndex moduleExcludeIndex, IgnoredFileIndex ignoredFileIndex, BuildDataPaths buildDataPaths) {
        return null;
    }

    @Nullable
    @Override
    public GoSourceRootDescriptor findRootDescriptor(String s, BuildRootIndex buildRootIndex) {
        return null;
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return null;
    }

    @NotNull
    @Override
    public Collection<File> getOutputRoots(CompileContext compileContext) {
        return null;
    }
}
