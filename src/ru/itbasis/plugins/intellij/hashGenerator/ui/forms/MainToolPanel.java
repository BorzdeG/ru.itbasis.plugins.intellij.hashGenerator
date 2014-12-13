package ru.itbasis.plugins.intellij.hashGenerator.ui.forms;

import ru.itbasis.plugins.intellij.hashGenerator.resources.ResourcesLoader;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.zip.CRC32;

public class MainToolPanel {
//	private static final Logger LOG = Logger.getInstance(MainToolPanel.class.getName());

	private JPanel     mainPanel;
	private JTextField txtValue;
	private JTextArea  txtResult;
	private JCheckBox  cbUpperCase;

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public MainToolPanel() {
		cbUpperCase.setText(ResourcesLoader.getString("MainToolPanel.cbUpperCase"));
		txtValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (KeyEvent.VK_ENTER == key) {
					generateHash();
				}
			}
		});
		cbUpperCase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generateHash();
			}
		});
	}

	private void generateHash() {
		txtResult.setText("");
		txtValue.setText(txtValue.getText().trim());
		if (txtValue.getText().isEmpty()) {
			return;
		}
		txtResult.append("CRC32: " + getAsCrc32() + "\n");
		txtResult.append("BCrypt(10): " + getAsBcrypt(10) + "\n");
	}

	private String getAsCrc32() {
		CRC32 crc = new CRC32();
		crc.update(txtValue.getText().getBytes(Charset.defaultCharset()));
		String s = Integer.toHexString((int) crc.getValue());
		return cbUpperCase.isSelected() ? s.toUpperCase(Locale.getDefault()) : s;
	}

	private String getAsBcrypt(int salt) {
		return BCrypt.hashpw(txtValue.getText(), BCrypt.gensalt(salt));
	}
}
