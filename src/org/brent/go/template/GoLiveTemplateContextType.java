
package org.brent.go.template;

import com.intellij.codeInsight.template.TemplateContextType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

abstract public class GoLiveTemplateContextType extends TemplateContextType {
    protected GoLiveTemplateContextType(@NotNull @NonNls String id, @NotNull String presentableName) {
        super(id, presentableName);
    }
    /*protected GoLiveTemplateContextType(@NotNull @NonNls String id, @NotNull String presentableName, @Nullable Class<? extends TemplateContextType> baseContextType) {
        super(id, presentableName, baseContextType);
    }

    public boolean isInContext(@NotNull final PsiFile file, final int offset) {
        if (PsiUtilCore.getLanguageAtOffset(file, offset).isKindOf(GoLanguage.INSTANCE)) {
            PsiElement element = getFirstCompositeElement(file.findElementAt(offset));
            return element != null && isInContext(element);
        }

        return false;
    }

    @Nullable
    private static PsiElement getFirstCompositeElement(@Nullable PsiElement at) {
        if (at instanceof PsiComment || at instanceof LeafPsiElement && ((LeafPsiElement) at).getElementType() == GoTypes.STRING)
            return at;
        PsiElement result = at;
        while (result != null && (result instanceof PsiWhiteSpace || result.getChildren().length == 0)) {
            result = result.getParent();
        }
        return result;
    }

    protected abstract boolean isInContext(@NotNull PsiElement element);

    public SyntaxHighlighter createHighlighter() {
        return new GoSyntaxHighlighter();
    }

    public static class GoEverywhereContextType extends GoLiveTemplateContextType {
        protected GoEverywhereContextType() {
            super("GO", "Go", EverywhereContextType.class);
        }

        @Override
        protected boolean isInContext(@NotNull PsiElement element) {
            return !(element instanceof PsiComment ||
                    element instanceof LeafPsiElement && ((LeafPsiElement) element).getElementType() == GoTypes.STRING);
        }
    }

    public static class GoFileContextType extends GoLiveTemplateContextType {
        protected GoFileContextType() {
            super("GO_FILE", "Go file", EverywhereContextType.class);
        }

        @Override
        protected boolean isInContext(@NotNull PsiElement element) {
            return element.getParent() instanceof GoFile && !(element instanceof GoTopLevelDeclaration);
        }
    }

    public static class GoTypeContextType extends GoLiveTemplateContextType {
        protected GoTypeContextType() {
            super("GO_TYPE", "Go type", EverywhereContextType.class);
        }

        @Override
        protected boolean isInContext(@NotNull PsiElement element) {
            return element instanceof GoType;
        }
    }

    public static class GoBlockContextType extends GoLiveTemplateContextType {
        protected GoBlockContextType() {
            super("GO_BLOCK", "Go block", EverywhereContextType.class);
        }

        @Override
        protected boolean isInContext(@NotNull PsiElement element) {
            return element instanceof GoSimpleStatement && PsiTreeUtil.getParentOfType(element, GoBlock.class) != null;
        }
    }*/
}
