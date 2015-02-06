package org.brent.go.project;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Brent on 2015/2/6.
 */
@State(
        name = "GoLibraries",
        storages = @Storage(file = StoragePathMacros.PROJECT_CONFIG_DIR + "/goLibraries.xml")
)
public class GoProjectLibrariesService extends GoLibrariesService {

    public static GoProjectLibrariesService getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, GoProjectLibrariesService.class);
    }
}
