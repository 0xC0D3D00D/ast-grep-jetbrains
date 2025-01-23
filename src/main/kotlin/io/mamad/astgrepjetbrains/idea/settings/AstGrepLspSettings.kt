package io.mamad.astgrepjetbrains.idea.settings

data class AstGrepLspSettings(
    var path: String = "ast-grep",
    var configFile: String = "sgconfig.yml",
    var ignoreCliVersion: Boolean = false,
) {
    fun toFlattenedMap(): Map<String, String> {
        return mapOf(
            "path" to path,
            "configFile" to configFile,
        )
    }
}