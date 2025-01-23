package io.mamad.astgrepjetbrains.idea.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.OSProcessHandler
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.Lsp4jClient
import com.intellij.platform.lsp.api.LspServerListener
import com.intellij.platform.lsp.api.LspServerNotificationsHandler
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import io.mamad.astgrepjetbrains.idea.settings.AppState

class AstGrepLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "ast-grep") {
    override fun createCommandLine(): GeneralCommandLine {
        val settingState = AppState.getInstance().lspSettings

        return GeneralCommandLine(settingState.path).apply {
            withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE)
            withWorkDirectory(project.basePath)
            withCharset(Charsets.UTF_8)
            addParameter("lsp")
            addParameter("--config")
            addParameter(settingState.configFile)
        }
    }

    override fun createInitializationOptions(): Any {
        return AppState.getInstance().lspSettings
    }

    override fun startServerProcess(): OSProcessHandler {
        val startingCommandLine = createCommandLine()
        val handler = OSProcessHandler(startingCommandLine)
        LOG.info("$this: starting LSP server: $startingCommandLine")

        return handler
    }

    override fun isSupportedFile(file: VirtualFile): Boolean {
        return true
    }

    override val lspServerListener: LspServerListener =
        AstGrepLspServerListener(project, clientCapabilities.textDocument?.synchronization?.didSave == true)
}