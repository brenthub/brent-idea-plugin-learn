package org.brent.go.jps.builder;

import org.brent.go.jps.model.JpsGoModuleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildTargetLoader;
import org.jetbrains.jps.builders.ModuleBasedBuildTargetType;
import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.module.JpsTypedModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brent on 2015/2/4.
 */
public class GoTargetType extends ModuleBasedBuildTargetType<GoTarget>{

    public static final GoTargetType PRODUCTION=new GoTargetType("go-production",false);

    public static final GoTargetType TESTS=new GoTargetType("go-tests",true);

    private final boolean myTests;

    public GoTargetType(String typeId, boolean myTests) {
        super(typeId);
        this.myTests = myTests;
    }

    @NotNull
    @Override
    public List<GoTarget> computeAllTargets(JpsModel jpsModel) {
        List<GoTarget> targets=new ArrayList<GoTarget>();
        for (JpsTypedModule<JpsDummyElement> mudule:jpsModel.getProject().getModules(JpsGoModuleType.INSTANCE)){

        }
        return null;
    }

    @NotNull
    @Override
    public BuildTargetLoader<GoTarget> createLoader(@NotNull JpsModel jpsModel) {
        return null;
    }
}
