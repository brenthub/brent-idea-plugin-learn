package org.brent.go.jps.model;

import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.serialization.JpsProjectExtensionSerializer;

/**
 * Created by Brent on 2015/2/5.
 */
public class JpsGoCompilerOptionsSerializer extends JpsProjectExtensionSerializer {
    public static final String COMPILER_OPTIONS_COMPONENT_NAME = "GoCompilerOptions";

    public JpsGoCompilerOptionsSerializer() {
        super("compiler.xml", COMPILER_OPTIONS_COMPONENT_NAME);
    }


    @Override
    public void loadExtension(JpsProject jpsProject, Element element) {
        JpsGoCompilerOptionsExtension extension = JpsGoCompilerOptionsExtension.getOrCreateExctension(jpsProject);
        GoCompilerOptions options = XmlSerializer.deserialize(element, GoCompilerOptions.class);
        if (options != null) {
            extension.setOptions(options);
        }
    }

    @Override
    public void saveExtension(JpsProject jpsProject, Element element) {

    }
}
