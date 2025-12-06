package com.example.angularbuilder.actions

import com.example.angularbuilder.utils.AngularProjectCreator
import com.example.angularbuilder.dialogs.CreateAngularProjectDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile

/**
 * Действие для создания нового Angular проекта
 */
class CreateAngularProjectAction : AnAction("Create Angular Project", 
    "Создать новый Angular проект", null) {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: run {
            Messages.showErrorDialog(
                null as com.intellij.openapi.project.Project?,
                "Проект не найден. Пожалуйста, откройте проект в IntelliJ IDEA.",
                "Ошибка"
            )
            return
        }

        // Получаем выбранную директорию или корень проекта
        val baseDir = e.getData(com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE)
            ?: project.baseDir

        // Показываем диалог для ввода параметров проекта
        val dialog = CreateAngularProjectDialog(project, baseDir)
        if (dialog.showAndGet()) {
            val projectName = dialog.getProjectName()
            val projectPath = dialog.getProjectPath()
            val routing = dialog.isRoutingEnabled()
            val style = dialog.getStyleFormat()

            try {
                // Создаем Angular проект
                AngularProjectCreator.createProject(
                    project,
                    projectName,
                    projectPath,
                    routing,
                    style
                )
                
                Messages.showInfoMessage(
                    project,
                    "Angular проект '$projectName' успешно создан в директории:\n$projectPath",
                    "Проект создан"
                )
                
                // Обновляем файловую систему
                project.baseDir.refresh(false, true)
            } catch (ex: Exception) {
                Messages.showErrorDialog(
                    project,
                    "Ошибка при создании Angular проекта:\n${ex.message}",
                    "Ошибка создания проекта"
                )
            }
        }
    }

    override fun update(e: AnActionEvent) {
        // Действие доступно всегда, когда открыт проект
        e.presentation.isEnabled = e.project != null
    }
}

