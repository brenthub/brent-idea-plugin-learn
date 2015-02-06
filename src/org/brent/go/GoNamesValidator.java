
package org.brent.go;

import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiNameHelper;
import org.jetbrains.annotations.NotNull;

public class GoNamesValidator implements NamesValidator {
    public boolean isKeyword(@NotNull String name, Project project) {
        return PsiNameHelper.getInstance(project).isKeyword(name);
    }

    public boolean isIdentifier(@NotNull String name, Project project) {
        return PsiNameHelper.getInstance(project).isIdentifier(name);
    }
}
