package org.brent.go.jps.model;

import com.intellij.execution.configurations.PathEnvironmentVariableUtil;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.JpsElementFactory;
import org.jetbrains.jps.model.JpsElementTypeWithDefaultProperties;
import org.jetbrains.jps.model.library.sdk.JpsSdkType;

import java.io.File;

/**
 * Created by Brent on 2015/2/5.
 */
public class JpsGoSdkType extends JpsSdkType<JpsDummyElement> implements JpsElementTypeWithDefaultProperties {
    public static final JpsGoSdkType INSTANCE = new JpsGoSdkType();

    @NotNull
    @Override
    public Object createDefaultProperties() {
        return JpsElementFactory.getInstance().createDummyElement();
    }

    /**
     * 获取SDK中可执行go文件路径
     *
     * @param sdkHome
     * @return
     */
    public static File getGoExecutableFile(String sdkHome) {
        File fromSdkPath = getExecutable(new File(sdkHome, "bin").getAbsolutePath(), "go");
        //从环境变量中拿
        File fromEnvironment = PathEnvironmentVariableUtil.findInPath("go");
        return fromSdkPath.canExecute() || fromEnvironment == null ? fromSdkPath : fromEnvironment;
    }

    /**
     * 获取模块路径下编译后的可执行文件
     *
     * @param modulePath
     * @param out
     * @return
     */
    public static String getBinaryPathByModulePath(String modulePath, String out) {
        return out + File.pathSeparator + getBinaryFileNameForPath(modulePath);
    }

    /**
     * 获取可执行文件路径
     *
     * @param path
     * @param command
     * @return
     */
    private static File getExecutable(String path, String command) {
        return new File(path, getBinaryFileNameForPath(command));
    }

    /**
     * 获取可执行文件名
     *
     * @param path
     * @return
     */
    private static String getBinaryFileNameForPath(String path) {
        String resultBinaryName = FileUtil.getNameWithoutExtension(PathUtil.getFileName(path));
        return SystemInfo.isWindows ? resultBinaryName + ".exe" : resultBinaryName;
    }
}

