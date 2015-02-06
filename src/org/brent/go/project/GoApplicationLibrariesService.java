package org.brent.go.project;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;

/**
 * Created by Brent on 2015/2/6.
 */
@State(
        name = "GoLibraries",
        storages = @Storage(file = StoragePathMacros.MODULE_FILE)
)
public class GoApplicationLibrariesService extends GoLibrariesService {

    public static GoApplicationLibrariesService getInstance() {
        return ServiceManager.getService(GoApplicationLibrariesService.class);
    }
}
