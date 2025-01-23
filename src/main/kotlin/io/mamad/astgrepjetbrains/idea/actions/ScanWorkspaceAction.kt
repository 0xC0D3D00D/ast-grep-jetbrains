package io.mamad.astgrepjetbrains.idea.actions

import com.intellij.platform.lsp.api.LspServer
import io.mamad.astgrepjetbrains.idea.lsp.AstGrepLanguageServer

class ScanWorkspaceAction : LspAction("Scan Workspace with ast-grep") {
    override fun actionPerformed(lspServer: LspServer) {
        val params = AstGrepLanguageServer.ScanWorkspaceParams(full = false)
        lspServer.sendNotification { (it as AstGrepLanguageServer).scanWorkspace(params) }
    }
}