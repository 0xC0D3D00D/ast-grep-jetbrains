package io.mamad.astgrepjetbrains.idea.ui

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.EditorNotificationPanel
import com.intellij.ui.EditorNotifications
import io.mamad.astgrepjetbrains.idea.lsp.AstGrepInstaller
import io.mamad.astgrepjetbrains.idea.settings.AppState

class AstGrepInstallBannerProvider : EditorNotifications.Provider<AstGrepInstallBannerProvider.AstGrepInstallBanner>(),
    DumbAware {

    class AstGrepInstallBanner(project: Project) : EditorNotificationPanel(Status.Error) {
        init {
            if (!AstGrepInstaller.isWindows()) {
                val installOptions = AstGrepInstaller.getInstallOptions()
                installOptions.forEach {
                    createActionLabel("Install with ${it.name.lowercase()}") {
                        it.install(project)
                        AppState.getInstance().pluginState.handledInstallBanner = true
                        EditorNotifications.getInstance(project).updateAllNotifications()
                    }
                }
            }
            createActionLabel("Ignore Extension") {
                AppState.getInstance().pluginState.handledInstallBanner = true
                EditorNotifications.getInstance(project).updateAllNotifications()

            }

            text("ast-grep is not installed")
        }
    }

    override fun createNotificationPanel(
        file: VirtualFile,
        fileEditor: FileEditor,
        project: Project
    ): AstGrepInstallBanner? {
        if (AstGrepInstaller.isWindows() || AstGrepInstaller.astGrepInstalled() || AppState.getInstance().pluginState.handledInstallBanner) {
            return null
        }
        if (AstGrepInstaller.isWindows()) {
            AstGrepNotifier(project).notifyWindowsNotSupported()
            return null
        }
        return AstGrepInstallBanner(project)
    }

    override fun getKey(): Key<AstGrepInstallBanner> {
        return KEY
    }

    companion object {
        val KEY = Key.create<AstGrepInstallBanner>("astgrep.install.banner")
    }
}