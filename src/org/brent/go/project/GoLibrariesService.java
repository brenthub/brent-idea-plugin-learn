package org.brent.go.project;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Brent on 2015/2/6.
 */
public abstract class GoLibrariesService implements PersistentStateComponent<GoLibrariesService.GoLibrariesState> {

    private GoLibrariesState state = new GoLibrariesState();

    @Nullable
    @Override
    public GoLibrariesState getState() {
        return state;
    }

    @Override
    public void loadState(GoLibrariesState goLibrariesState) {
        XmlSerializerUtil.copyBean(goLibrariesState, state);
    }

    /**
     * 获取定义在模块下的库（继承application和project的库）
     *
     * @param module
     * @return
     */
    public static Collection<? extends VirtualFile> getUserDefinedLibraries(Module module) {
        Set<VirtualFile> result = ContainerUtil.newLinkedHashSet();
        result.addAll(filesFromUrls(GoModuleLibrariesService.getInstance(module).getLibraryRootUrls()));
        result.addAll(getUserDefinedLibraries(module.getProject()));
        result.addAll(getUserDefinedLibraries());
        return result;
    }

    /**
     * 获取定义在项目下的库
     *
     * @param project
     * @return
     */
    public static Collection<? extends VirtualFile> getUserDefinedLibraries(Project project) {
        return filesFromUrls(GoProjectLibrariesService.getInstance(project).getLibraryRootUrls());
    }

    /**
     * 获取定义在Application下的库
     *
     * @return
     */
    @NotNull
    public static Collection<? extends VirtualFile> getUserDefinedLibraries() {
        return filesFromUrls(GoApplicationLibrariesService.getInstance().getLibraryRootUrls());
    }

    /**
     * 根据库路径获取文件
     *
     * @param urls
     * @return
     */
    private static Collection<? extends VirtualFile> filesFromUrls(Collection<String> urls) {
        return ContainerUtil.skipNulls(ContainerUtil.map(urls, new Function<String, VirtualFile>() {
            @Override
            public VirtualFile fun(String url) {
                return VirtualFileManager.getInstance().findFileByUrl(url);
            }
        }));
    }

    /**
     * 获取库根路径
     *
     * @return
     */
    public Collection<String> getLibraryRootUrls() {
        return state.getUrls();
    }

    /**
     * 设置库根路径
     *
     * @param urls
     */
    public void setLibraryRootUrls(Collection<String> urls) {
        this.state.setUrls(urls);
    }


    public static class GoLibrariesState {
        private Collection<String> urls = ContainerUtil.newArrayList();

        public Collection<String> getUrls() {
            return urls;
        }

        public void setUrls(Collection<String> urls) {
            this.urls = urls;
        }
    }


}
