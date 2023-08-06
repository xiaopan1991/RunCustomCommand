package com.github.xiaopan1991.runcustomcommand.actions;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PopAction extends AnAction {

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

    public PopAction() {
        super();
    }

    public PopAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

//    public static String executeBatFile(String file, boolean isCloseWindow) {
//
//    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        // TODO: insert action logic here

        Project project = event.getProject();
        try {
            Process process = Runtime.getRuntime().exec("cmd /c start D:\\script.bat");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception ex) {
            Messages.showMessageDialog(project, "Error executing bat file: " + ex.getMessage(), "Error", Messages.getErrorIcon());
        }
    }

    @Override
    public void update(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        event.getPresentation().setEnabledAndVisible(project != null);
    }
}
