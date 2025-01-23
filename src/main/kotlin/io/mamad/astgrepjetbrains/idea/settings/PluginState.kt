package io.mamad.astgrepjetbrains.idea.settings

data class PluginState(
    var handledInstallBanner: Boolean = false,
    var astgrepVersion: String = "unknown"
)