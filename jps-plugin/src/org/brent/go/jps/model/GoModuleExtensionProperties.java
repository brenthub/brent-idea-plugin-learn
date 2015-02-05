package org.brent.go.jps.model;

import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xmlb.annotations.AbstractCollection;
import com.intellij.util.xmlb.annotations.Tag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Brent on 2015/2/5.
 */
public class GoModuleExtensionProperties {

    @Tag("includePaths")
    @NotNull
    @AbstractCollection(surroundWithTag = false, elementTag = "path")
    public List<String> myIncluedePaths = ContainerUtil.newArrayList();

    @Tag("parseTransforms")
    @NotNull
    @AbstractCollection(surroundWithTag = false, elementTag = "transform")
    public List<String> myParseTransforms = ContainerUtil.newArrayList();

    public GoModuleExtensionProperties() {

    }

    public GoModuleExtensionProperties(GoModuleExtensionProperties props) {
        this.myParseTransforms = ContainerUtil.newArrayList(props.myParseTransforms);
        this.myIncluedePaths = ContainerUtil.newArrayList(props.myIncluedePaths);
    }
}
