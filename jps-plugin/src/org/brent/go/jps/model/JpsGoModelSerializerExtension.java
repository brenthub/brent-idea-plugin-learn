package org.brent.go.jps.model;

import com.intellij.util.xmlb.SkipDefaultValuesSerializationFilters;
import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.JpsElement;
import org.jetbrains.jps.model.JpsElementFactory;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.serialization.JpsModelSerializerExtension;
import org.jetbrains.jps.model.serialization.JpsProjectExtensionSerializer;
import org.jetbrains.jps.model.serialization.facet.JpsFacetConfigurationSerializer;
import org.jetbrains.jps.model.serialization.library.JpsSdkPropertiesSerializer;
import org.jetbrains.jps.model.serialization.module.JpsModulePropertiesSerializer;

import java.util.Collections;
import java.util.List;

/**
 * Created by Brent on 2015/2/5.
 */
public class JpsGoModelSerializerExtension extends JpsModelSerializerExtension {
    public static final String GO_SDK_TYPE_ID = "GO SDK";

    @NotNull
    @Override
    public List<? extends JpsModulePropertiesSerializer<?>> getModulePropertiesSerializers() {
        return Collections.singletonList(new JpsModulePropertiesSerializer<JpsDummyElement>(JpsGoModuleType.INSTANCE,
                "GO_MODULE", null) {
            @Override
            public JpsDummyElement loadProperties(Element element) {
                return JpsElementFactory.getInstance().createDummyElement();
            }

            @Override
            public void saveProperties(@NotNull JpsDummyElement jpsElement, @NotNull Element element) {
            }
        });
    }

    @NotNull
    @Override
    public List<? extends JpsSdkPropertiesSerializer<?>> getSdkPropertiesSerializers() {
        return Collections.singletonList(new JpsSdkPropertiesSerializer<JpsDummyElement>(GO_SDK_TYPE_ID,
                JpsGoSdkType.INSTANCE) {
            @NotNull
            @Override
            public JpsDummyElement loadProperties(Element element) {
                return JpsElementFactory.getInstance().createDummyElement();
            }

            @Override
            public void saveProperties(@NotNull JpsDummyElement jpsElement, @NotNull Element element) {
            }
        });
    }

    @NotNull
    @Override
    public List<? extends JpsFacetConfigurationSerializer<?>> getFacetConfigurationSerializers() {
        return Collections.singletonList(new JpsFacetConfigurationSerializer<JpsGoModuleExtension>(JpsGoModuleExtension.ROLE, GoFacetConstants.ID, GoFacetConstants.NAME) {
            @Override
            protected JpsGoModuleExtension loadExtension(@NotNull Element element, String s, JpsElement jpsElement, JpsModule jpsModule) {
                GoModuleExtensionProperties props = XmlSerializer.deserialize(element, GoModuleExtensionProperties.class);
                return new JpsGoModuleExtension(props == null ? new GoModuleExtensionProperties() : props);
            }

            @Override
            protected void saveExtension(JpsGoModuleExtension extension, Element element, JpsModule jpsModule) {
                XmlSerializer.serializeInto(extension.getProperties(), element, new SkipDefaultValuesSerializationFilters());
            }
        });
    }

    @NotNull
    @Override
    public List<? extends JpsProjectExtensionSerializer> getProjectExtensionSerializers() {
        return Collections.singletonList(new JpsGoCompilerOptionsSerializer());
    }
}
