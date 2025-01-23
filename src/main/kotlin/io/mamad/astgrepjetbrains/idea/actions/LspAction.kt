package io.mamad.astgrepjetbrains.idea.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.platform.lsp.api.LspServer
import io.mamad.astgrepjetbrains.idea.lsp.AstGrepService

abstract class LspAction(text: String) : AnAction(text) {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val lspServer = AstGrepService.getRunningLspServer(project) ?: return
        actionPerformed(lspServer)
    }

    abstract fun actionPerformed(lspServer: LspServer)
}