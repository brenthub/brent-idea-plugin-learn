package org.brent.go.project.jps.builder;

import org.jetbrains.jps.builders.BuildRootDescriptor;
import org.jetbrains.jps.builders.BuildTarget;

import java.io.File;

/**
 * Created by brent on 2015/2/4.
 */
public class GoSourceRootDescriptor extends BuildRootDescriptor {

    private final File root;
    private final GoTarget goTarget;

    public GoSourceRootDescriptor(File root, GoTarget goTarget) {
        this.root = root;
        this.goTarget = goTarget;
    }

    @Override
    public String getRootId() {
        return root.getAbsolutePath();
    }

    @Override
    public File getRootFile() {
        return root;
    }

    @Override
    public BuildTarget<?> getTarget() {
        return goTarget;
    }
}
