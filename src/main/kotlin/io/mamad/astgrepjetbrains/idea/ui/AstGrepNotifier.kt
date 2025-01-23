package io.mamad.astgrepjetbrains.idea.ui

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.util.text.SemVer

class AstGrepNotifier(private val project: Project) {
    fun notifyInstallSuccess() {
        NotificationGroupManager
            .getInstance()
            .getNotificationGroup("Install Group")
            .createNotification(
                "ast-grep has been installed successfully",
                NotificationType.INFORMATION
            ).apply {
                notify(project)
            }
    }

    fun notifyInstallFailure(output: String, exitCode: Int) {
        val message = "ast-grep failed to install with exit code $exitCode and output:\n$output"
        NotificationGroupManager
            .getInstance()
            .getNotificationGroup("Install Group")
            .createNotification(
                message,
                NotificationType.ERROR
            ).apply {
                notify(project)
            }
    }

    fun notifyUpdateNeeded(needed: SemVer, current: SemVer) {
        val message =
            "The ast-grep Extension requires a ast-grep CLI version $needed, the current installed version is $current, please update"
        NotificationGroupManager
            .getInstance()
            .getNotificationGroup("Install Group")
            .createNotification(
                message,
                NotificationType.WARNING
            ).apply {
                notify(project)
            }
    }

    fun notifyUpdateAvailable(current: SemVer, latest: SemVer) {
        val message =
            "Some features of the ast-grep Extension require a ast-grep CLI version ${latest}, but the current installed version is ${current}, some features may be disabled, please upgrade."
        NotificationGroupManager
            .getInstance()
            .getNotificationGroup("Install Group")
            .createNotification(
                message,
                NotificationType.INFORMATION
            ).apply {
                notify(project)
            }
    }

    fun notifyWindowsNotSupported() {
        val message = "ast-grep idea plugin is not supported on Windows currently, please use WSL or a VM"

        NotificationGroupManager
            .getInstance()
            .getNotificationGroup("Install Group")
            .createNotification(
                message,
                NotificationType.ERROR
            ).apply {
                notify(project)
            }
    }
}