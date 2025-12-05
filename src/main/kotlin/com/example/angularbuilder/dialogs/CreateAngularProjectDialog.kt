package com.example.angularbuilder.dialogs

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.vfs.VirtualFile
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.swing.*

/**
 * Диалог для ввода параметров создания Angular проекта
 */
class CreateAngularProjectDialog(
    project: Project?,
    private val baseDir: VirtualFile
) : DialogWrapper(project) {

    private val projectNameField = JTextField("my-angular-app", 30)
    private val projectPathField = JTextField(baseDir.path, 30)
    private val routingCheckBox = JCheckBox("Добавить Angular routing", true)
    private val styleComboBox = JComboBox(arrayOf("CSS", "SCSS", "SASS", "Less", "Stylus"))

    init {
        title = "Создать новый Angular проект"
        init()
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)

        // Название проекта
        val namePanel = JPanel(FlowLayout(FlowLayout.LEFT))
        namePanel.add(JLabel("Название проекта:"))
        namePanel.add(projectNameField)
        panel.add(namePanel)

        // Путь проекта
        val pathPanel = JPanel(FlowLayout(FlowLayout.LEFT))
        pathPanel.add(JLabel("Директория:"))
        pathPanel.add(projectPathField)
        panel.add(pathPanel)

        // Routing
        panel.add(routingCheckBox)

        // Стили
        val stylePanel = JPanel(FlowLayout(FlowLayout.LEFT))
        stylePanel.add(JLabel("Формат стилей:"))
        stylePanel.add(styleComboBox)
        panel.add(stylePanel)

        return panel
    }

    fun getProjectName(): String = projectNameField.text.trim()
    fun getProjectPath(): String = projectPathField.text.trim()
    fun isRoutingEnabled(): Boolean = routingCheckBox.isSelected
    fun getStyleFormat(): String = styleComboBox.selectedItem as String
}

