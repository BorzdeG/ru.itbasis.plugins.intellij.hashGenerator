package ru.itbasis.plugins.intellij.hashGenerator.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;
import ru.itbasis.plugins.intellij.hashGenerator.ui.forms.MainToolPanel;

public class HashGeneratorToolWindowFactory implements ToolWindowFactory {
	@Override
	public void createToolWindowContent(@NotNull Project project,
	                                    @NotNull com.intellij.openapi.wm.ToolWindow toolWindow) {

		ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
		Content content = contentFactory.createContent(new MainToolPanel().getMainPanel(), "", false);
		toolWindow.getContentManager().addContent(content);
	}
}
