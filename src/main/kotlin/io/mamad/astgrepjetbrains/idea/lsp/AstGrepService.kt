package io.mamad.astgrepjetbrains.idea.lsp

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.LspServer
import com.intellij.platform.lsp.api.LspServerManager
import com.intellij.platform.lsp.api.LspServerState
import kotlinx.coroutines.CoroutineScope

@Service(Service.Level.PROJECT)
class AstGrepService(val cs: CoroutineScope) {
    companion object {
        fun getInstance(project: Project): AstGrepService = project.service<AstGrepService>()

        // There might be no more than one running server
        // because `AstGrepLspServerDescriptor` extends `ProjectWideLspServerDescriptor`
        fun getRunningLspServer(project: Project): LspServer? =
            LspServerManager.getInstance(project)
                .getServersForProvider(AstGrepLspServerSupportProvider::class.java)
                .firstOrNull { it.state == LspServerState.Running }
    }
}