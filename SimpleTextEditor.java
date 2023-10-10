import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SimpleTextEditor extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public SimpleTextEditor() {
        setTitle("Simple Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        fileChooser = new JFileChooser();

        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(openMenuItem);
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(saveMenuItem);
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void openFile() {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                textArea.read(reader, null);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                textArea.write(writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimpleTextEditor editor = new SimpleTextEditor();
                editor.setVisible(true);
            }
        });
    }
}

