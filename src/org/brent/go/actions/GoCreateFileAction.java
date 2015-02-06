package org.brent.go.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidatorEx;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.containers.ContainerUtil;
import org.brent.go.GoIcons;
import org.brent.go.GoNamesValidator;
import org.jetbrains.annotations.NotNull;

import java.util.Properties;

public class GoCreateFileAction extends CreateFileFromTemplateAction implements DumbAware { // todo: rewrite with package support?
    private static final String NEW_GO_FILE = "New Go File";
    private static final String PACKAGE = "PACKAGE";

    @Override
    protected PsiFile createFile(String name, @NotNull String templateName, @NotNull PsiDirectory dir) {
        FileTemplate template = FileTemplateManager.getInstance().getInternalTemplate(templateName);
        Properties properties = new Properties();
        properties.setProperty(PACKAGE, ContainerUtil.getLastItem(StringUtil.split(dir.getName(), "-")));
        try {
            PsiElement element = FileTemplateUtil.createFromTemplate(template, name, properties, dir);
            if (element instanceof PsiFile) return (PsiFile) element;
        } catch (Exception e) {
            LOG.warn(e);
            return null;
        }
        return super.createFile(name, templateName, dir);
    }

    public GoCreateFileAction() {
        super(NEW_GO_FILE, "", GoIcons.ICON);
    }

    @Override
    protected void buildDialog(final Project project, PsiDirectory directory, @NotNull CreateFileFromTemplateDialog.Builder builder) {
        // todo: check that file already exists
        builder.
                setTitle(NEW_GO_FILE).
                addKind("Empty file", GoIcons.ICON, "Go File").
                addKind("Simple Application", GoIcons.ICON, "Go Application").
                setValidator(new InputValidatorEx() {
                    @Override
                    public boolean checkInput(String name) {
                        return true;
                    }

                    @Override
                    public boolean canClose(@NotNull String name) {
                        return !StringUtil.isEmptyOrSpaces(name) && getErrorText(name) == null;
                    }

                    @Override
                    public String getErrorText(@NotNull String name) {
                        String error = " is not a valid Go file name";
                        if (StringUtil.isEmpty(name)) return null;
                        boolean ok = new GoNamesValidator().isIdentifier(name, project);
                        if (ok && FileUtil.sanitizeFileName(name).equals(name)) {
                            return null;
                        }

                        return "'" + name + "'" + error;
                    }
                })
        ;
    }

    @NotNull
    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return NEW_GO_FILE;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof GoCreateFileAction;
    }
}