import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RecursiveListerFrame extends JFrame {
    private JTextArea textArea;

    public RecursiveListerFrame() {
        setTitle("Recursive Lister");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Recursive File Lister", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton startButton = new JButton("Start");
        JButton quitButton = new JButton("Quit");

        startButton.addActionListener(e -> openDirectoryChooser());
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void openDirectoryChooser() {
        File workingDirectory = new File(System.getProperty("user.dir"));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(workingDirectory);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            textArea.setText("");
            listFiles(selectedDirectory);
        }
    }

    private void listFiles(File directory) {
        if (directory.isFile()) {
            textArea.append(directory.getAbsolutePath() + "\n");
        } else {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    listFiles(file);
                }
            }
        }
    }
}