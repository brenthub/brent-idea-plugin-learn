package org.brent.go.template;

import com.intellij.codeInsight.template.*;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

/**
 * 宏
 * Created by Brent on 2015/2/6.
 */
public class DirectoryNameMacro extends Macro {

    public static final String NAME = "directoryName";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getPresentableName() {
        return NAME;
    }

    @Nullable
    @Override
    public Result calculateResult(Expression[] expressions, ExpressionContext expressionContext) {
        PsiElement ele = expressionContext.getPsiElementAtStartOffset();
        if (ele != null) {
            VirtualFile file = ele.getContainingFile().getVirtualFile();
            if (file != null) {
                VirtualFile parent = file.getParent();
                if (parent != null) {
                    return calculateResult(parent);
                }
            }
        }
        return null;
    }

    protected TextResult calculateResult(VirtualFile virtualFile) {
        return new TextResult(virtualFile.getName());
    }
}
