package org.brent.go.jps.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.ex.JpsCompositeElementBase;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;
import org.jetbrains.jps.model.module.JpsModule;

import java.util.Collections;
import java.util.List;

/**
 * Created by Brent on 2015/2/5.
 */
public class JpsGoModuleExtension extends JpsCompositeElementBase<JpsGoModuleExtension> {

    private final GoModuleExtensionProperties pro;
    public static final JpsElementChildRole<JpsGoModuleExtension> ROLE = JpsElementChildRoleBase.create("Go");

    @NotNull
    @Override
    public JpsGoModuleExtension createCopy() {
        return new JpsGoModuleExtension(this);
    }

    public JpsGoModuleExtension(GoModuleExtensionProperties pro) {
        this.pro = pro;
    }

    public JpsGoModuleExtension(JpsGoModuleExtension ext) {
        this.pro = ext.pro;
    }

    public GoModuleExtensionProperties getProperties() {
        return pro;
    }

    public List<String> getIncludePaths() {
        return Collections.unmodifiableList(pro.myIncluedePaths);
    }

    public List<String> getParseTransforms() {
        return Collections.unmodifiableList(pro.myParseTransforms);
    }

    public static JpsGoModuleExtension getExtension(JpsModule module) {
        return module != null ? module.getContainer().getChild(ROLE) : null;
    }
}
