package org.brent.go.project;

import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleServiceManager;

/**
 * Created by Brent on 2015/2/6.
 */
@State(
        name = "GoLibraries",
        storages = @Storage(file = StoragePathMacros.MODULE_FILE)
)
public class GoModuleLibrariesService extends GoLibrariesService {
    public static GoModuleLibrariesService getInstance(Module module) {
        return ModuleServiceManager.getService(module, GoModuleLibrariesService.class);

    }
}
