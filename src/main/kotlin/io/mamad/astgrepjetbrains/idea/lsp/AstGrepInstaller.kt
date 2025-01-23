package io.mamad.astgrepjetbrains.idea.lsp

import io.mamad.astgrepjetbrains.idea.ui.AstGrepNotifier
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.SystemInfo
import com.intellij.platform.lsp.api.LspServerManager
import com.intellij.util.text.SemVer
import io.mamad.astgrepjetbrains.idea.settings.AppState
import io.mamad.astgrepjetbrains.idea.settings.AstGrepLspSettings

object AstGrepInstaller {
    enum class InstallOption(val binary: String, val installCommand: String) {
        BREW("brew", "brew install ast-grep");

        fun isInstalled(): Boolean {
            return which(binary) != null
        }

        fun install(project: Project) {
            val cmd = GeneralCommandLine("sh", "-c", installCommand)
            val process = cmd.createProcess()
            val ret = process.waitFor()
            val out = process.inputStream.bufferedReader().readText()
            val astgrepNotifier = AstGrepNotifier(project)
            if (ret == 0) {
                astgrepNotifier.notifyInstallSuccess()
                LspServerManager.getInstance(project)
                    .stopAndRestartIfNeeded(AstGrepLspServerSupportProvider::class.java)
            } else {
                astgrepNotifier.notifyInstallFailure(out, ret)
            }
        }
    }

    fun astGrepInstalled(): Boolean {
        val defaultPath = AstGrepLspSettings().path
        val state = AppState.getInstance().lspSettings
        return state.path != defaultPath || io.mamad.astgrepjetbrains.idea.lsp.AstGrepInstaller.which(defaultPath) != null
    }

    fun getCliVersion(): SemVer? {
        val cmd = GeneralCommandLine("ast-grep", "--version")
        val process = cmd.createProcess()
        process.waitFor()
        val out = process.inputStream.bufferedReader().readText().replace("ast-grep", "").trim()
        return SemVer.parseFromText(out)
    }

    data class VersionCheckInfo(val min: SemVer, val latest: SemVer)

    fun getMostUpToDateCliVersion(): io.mamad.astgrepjetbrains.idea.lsp.AstGrepInstaller.VersionCheckInfo? {
        // TODO: Get this from github
        return io.mamad.astgrepjetbrains.idea.lsp.AstGrepInstaller.VersionCheckInfo(
            SemVer.parseFromText("0.30.0")!!,
            SemVer.parseFromText("0.30.0")!!
        )
    }

    fun which(binary: String): String? {
        val cmd = GeneralCommandLine("which", binary)

        val process = cmd.createProcess()
        process.waitFor()
        val result = process.inputStream.bufferedReader().readLine()

        return if (result == "") null else result
    }

    fun isWindows(): Boolean {
        return SystemInfo.isWindows
    }

    fun getInstallOptions(): List<io.mamad.astgrepjetbrains.idea.lsp.AstGrepInstaller.InstallOption> {
        return io.mamad.astgrepjetbrains.idea.lsp.AstGrepInstaller.InstallOption.values().filter { it.isInstalled() }
    }
}