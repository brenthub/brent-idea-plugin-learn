package org.brent.go.project.jps.model;

import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.ex.JpsElementTypeWithDummyProperties;
import org.jetbrains.jps.model.module.JpsModuleType;

/**
 * Created by Brent on 2015/2/4.
 */
public class JpsGoModuleType extends JpsElementTypeWithDummyProperties implements JpsModuleType<JpsDummyElement>{

    public static final JpsGoModuleType INSTANCE=new JpsGoModuleType();
}
