# ast-grep-jetbrains

![Build](https://github.com/0xC0D3D00D/ast-grep-jetbrains/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/26387-ast-grep.svg)](https://plugins.jetbrains.com/plugin/26387-ast-grep)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/26387-ast-grep.svg)](https://plugins.jetbrains.com/plugin/26387-ast-grep)

<!-- Plugin description -->
This plugin integrates ast-grep with JetBrains IDEs to provide code diagnostics based on ast-grep rules. It highlights issues as info, warnings, or errors, helping you identify potential problems more quickly. You can configure it by specifying an AST-Grep configuration file, and it currently supports code diagnostics only. Future updates may add additional features.
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "ast-grep-jetbrains"</kbd> >
  <kbd>Install</kbd>
  
- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/26387-ast-grep) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/26387-ast-grep/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/0xC0D3D00D/ast-grep-jetbrains/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
