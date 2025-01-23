package io.mamad.astgrepjetbrains.idea.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.platform.lsp.api.LspServerListener
import io.mamad.astgrepjetbrains.idea.settings.AppState
import io.mamad.astgrepjetbrains.idea.ui.AstGrepNotifier
import org.eclipse.lsp4j.InitializeResult

class AstGrepLspServerListener(
    private val project: Project,
    private val nativeDidSaveSupport: Boolean,
) : LspServerListener {
    fun checkVersion() {
        val settings = AppState.getInstance()
        val current = AstGrepInstaller.getCliVersion()
        val versionInfo = AstGrepInstaller.getMostUpToDateCliVersion()
        if (versionInfo != null && current != null && !settings.lspSettings.ignoreCliVersion) {
            val latest = versionInfo.latest
            val needed = versionInfo.min
            settings.pluginState.astgrepVersion = current.toString()
            if (current < needed) {
                AstGrepNotifier(project).notifyUpdateNeeded(needed, current)
            } else if (current < latest) {
                AstGrepNotifier(project).notifyUpdateAvailable(current, latest)
            }
        }
    }

    override fun serverInitialized(params: InitializeResult) {
        if (!nativeDidSaveSupport) {
            project.messageBus.connect().subscribe(VirtualFileManager.VFS_CHANGES, FileSaveManager(project))
        }

        checkVersion()
    }
}