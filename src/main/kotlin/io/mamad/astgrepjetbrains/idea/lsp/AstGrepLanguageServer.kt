package io.mamad.astgrepjetbrains.idea.lsp

import org.eclipse.lsp4j.jsonrpc.services.JsonNotification
import org.eclipse.lsp4j.services.LanguageServer

interface AstGrepLanguageServer : LanguageServer {
    // Notifications

    @JsonNotification("ast-grep/scanWorkspace")
    fun scanWorkspace(params: ScanWorkspaceParams)

    @JsonNotification("ast-grep/refreshRules")
    fun refreshRules(params: List<Void> = emptyList())

    class ScanWorkspaceParams(@Suppress("unused") val full: Boolean)
}