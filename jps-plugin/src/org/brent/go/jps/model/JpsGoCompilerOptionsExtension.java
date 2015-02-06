package org.brent.go.jps.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.ex.JpsCompositeElementBase;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;

/**
 * Created by Brent on 2015/2/5.
 */
public class JpsGoCompilerOptionsExtension extends JpsCompositeElementBase<JpsGoCompilerOptionsExtension> {

    public static final JpsElementChildRole<JpsGoCompilerOptionsExtension> ROLE = JpsElementChildRoleBase.create("GoCompilerOptions");

    private GoCompilerOptions options;

    public JpsGoCompilerOptionsExtension(GoCompilerOptions myOptions) {
        this.options = myOptions;
    }

    public GoCompilerOptions getOptions() {
        return options;
    }

    public void setOptions(GoCompilerOptions options) {
        this.options = options;
    }

    @NotNull
    @Override
    public JpsGoCompilerOptionsExtension createCopy() {
        return new JpsGoCompilerOptionsExtension(new GoCompilerOptions(options));
    }

    public static JpsGoCompilerOptionsExtension getOrCreateExtension(JpsProject project) {
        JpsGoCompilerOptionsExtension extension = project.getContainer().getChild(ROLE);
        if (extension == null) {
            extension = project.getContainer().setChild(ROLE, new JpsGoCompilerOptionsExtension(new GoCompilerOptions()));
        }
        return extension;
    }
}
