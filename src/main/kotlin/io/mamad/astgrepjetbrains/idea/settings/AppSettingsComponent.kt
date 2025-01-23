package io.mamad.astgrepjetbrains.idea.settings

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.*

class AppSettingsComponent(settings: AstGrepLspSettings) {
    private var panel: DialogPanel? = null
    private var lspSettings: AstGrepLspSettings = settings

    init {
        panel = panel {
            group("General") {
                row("ast-grep Path") {
                    textField()
                        .bindText(lspSettings::path)
                        .comment("Path to the ast-grep executable.")
                }
                row("Configuration Path") {
                    textField()
                        .bindText(lspSettings::configFile)
                        .comment("Path to the config file.")
                }
                row {
                    checkBox("Ignore cli version")
                        .bindSelected(lspSettings::ignoreCliVersion)
                        .comment("Ignore CLI Version, and enable all extension features (Warning: this is mainly for extension development, and can break things if enabled!)")
                }
            }
        }

    }

    fun getPanel(): DialogPanel? {
        return panel
    }

}