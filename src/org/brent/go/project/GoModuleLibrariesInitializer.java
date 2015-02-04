package org.brent.go.project;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleComponent;
import com.intellij.openapi.vfs.*;
import com.intellij.util.Alarm;
import io.netty.util.internal.ConcurrentSet;
import org.jetbrains.annotations.NotNull;

/**
 * Created by brent on 2015/2/3.
 */
public class GoModuleLibrariesInitializer implements ModuleComponent {

    private final static Logger LOG = Logger.getInstance(GoModuleLibrariesInitializer.class);

    private VirtualFileAdapter myFilesListener=new VirtualFileAdapter() {

        @Override
        public void fileCreated(@NotNull VirtualFileEvent event) {
            handleEvent(event);
        }

        @Override
        public void fileDeleted(@NotNull VirtualFileEvent event) {
            handleEvent(event);
        }

        @Override
        public void fileMoved(@NotNull VirtualFileMoveEvent event) {
            handleEvent(event);
        }

        private void handleEvent(VirtualFileEvent event){
            if (filesToWatch.contains(event.getFile())){
                scheduleUpdate();
            }else {
                for(VirtualFile file: filesToWatch) {
                    if (VfsUtilCore.isAncestor(file, event.getFile(), true)) {
                        scheduleUpdate();
                    }
                }
            }
        }
    };

    private ConcurrentSet<VirtualFile> filesToWatch=new ConcurrentSet<VirtualFile>();

    private static final int UPDATE_DELAY=300;

    private final Module myModule;



    private final Alarm myAlearm;

    public GoModuleLibrariesInitializer(Module module) {
        this.myModule=module;
        this.myAlearm = new Alarm(Alarm.ThreadToUse.POOLED_THREAD,myModule);
    }

    public void scheduleUpdate(){
        scheduleUpdate(UPDATE_DELAY);
    }

    public void scheduleUpdate(int delay) {
        myAlearm.addRequest(new Runnable() {
            @Override
            public void run() {
                //
                LOG.info("running...");
            }
        },delay);
    }

    @Override
    public void projectOpened() {
        //do nothing

    }

    @Override
    public void projectClosed() {
        this.disposeComponent();
    }

    @Override
    public void moduleAdded() {

    }

    @Override
    public void initComponent() {
        //do nothing
        LOG.info("initComponent...");
    }

    @Override
    public void disposeComponent() {
        VirtualFileManager.getInstance().removeVirtualFileListener(myFilesListener);

    }

    @NotNull
    @Override
    public String getComponentName() {
        return getClass().getName();
    }
}
