package io.mamad.astgrepjetbrains.idea.settings

data class AppStateData(
    var appSettings: AstGrepLspSettings = AstGrepLspSettings(),
    var pluginState: PluginState = PluginState(),
)