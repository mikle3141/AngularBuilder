package com.example.angularbuilder.utils;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.openapi.project.Project;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Утилита для создания Angular проекта через Angular CLI
 */
public class AngularProjectCreator {
    
    /**
     * Создает новый Angular проект
     * 
     * @param project IntelliJ проект
     * @param projectName название Angular проекта
     * @param projectPath путь для создания проекта
     * @param routing включить ли routing
     * @param style формат стилей (CSS, SCSS, SASS, Less, Stylus)
     * @throws Exception если не удалось создать проект
     */
    public static void createProject(
            Project project,
            String projectName,
            String projectPath,
            boolean routing,
            String style
    ) throws Exception {
        
        // Проверяем наличие Angular CLI
        if (!isAngularCliInstalled()) {
            throw new Exception(
                "Angular CLI не установлен. " +
                "Установите его командой: npm install -g @angular/cli"
            );
        }
        
        // Проверяем и создаем директорию если нужно
        Path targetPath = Paths.get(projectPath);
        if (!Files.exists(targetPath)) {
            Files.createDirectories(targetPath);
        }
        
        // Формируем команду для создания проекта
        GeneralCommandLine commandLine = new GeneralCommandLine();
        commandLine.setExePath("ng");
        commandLine.setWorkDirectory(projectPath);
        
        // Базовые параметры
        commandLine.addParameter("new");
        commandLine.addParameter(projectName);
        
        // Параметры создания
        commandLine.addParameter("--skip-git"); // Пропускаем инициализацию git
        commandLine.addParameter("--skip-install"); // Пропускаем установку зависимостей (можно установить позже)
        
        if (routing) {
            commandLine.addParameter("--routing");
        }
        
        commandLine.addParameter("--style");
        commandLine.addParameter(style.toLowerCase());
        
        // Выполняем команду
        try {
            CapturingProcessHandler processHandler = new CapturingProcessHandler(commandLine);
            ProcessOutput output = processHandler.runProcess(60000); // 60 секунд таймаут
            
            int exitCode = output.getExitCode();
            if (exitCode != 0) {
                String errorOutput = output.getStderr();
                throw new ExecutionException(
                    "Ошибка выполнения команды Angular CLI. Код выхода: " + exitCode +
                    (errorOutput != null && !errorOutput.isEmpty() ? "\nОшибка: " + errorOutput : "")
                );
            }
            
        } catch (ExecutionException e) {
            throw new Exception("Ошибка при выполнении Angular CLI: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("Ошибка при выполнении Angular CLI: " + e.getMessage(), e);
        }
    }
    
    /**
     * Проверяет, установлен ли Angular CLI глобально
     * 
     * @return true если Angular CLI установлен
     */
    private static boolean isAngularCliInstalled() {
        try {
            GeneralCommandLine commandLine = new GeneralCommandLine();
            commandLine.setExePath("ng");
            commandLine.addParameter("version");
            
            CapturingProcessHandler processHandler = new CapturingProcessHandler(commandLine);
            ProcessOutput output = processHandler.runProcess(10000); // 10 секунд таймаут
            
            return output.getExitCode() == 0;
        } catch (Exception e) {
            return false;
        }
    }
}

