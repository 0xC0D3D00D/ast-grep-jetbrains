package io.mamad.astgrepjetbrains.idea.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import io.mamad.astgrepjetbrains.idea.settings.AppState

class AstGrepLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(
        project: Project,
        file: VirtualFile,
        serverStarter: LspServerSupportProvider.LspServerStarter
    ) {
        val installed = AstGrepInstaller.astGrepInstalled()
        if (installed || AppState.getInstance().pluginState.handledInstallBanner) {
            serverStarter.ensureServerStarted(AstGrepLspServerDescriptor(project))
        }
    }

}