import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import components.DropDown.YearDropDown;
import components.DropDown.ProblemDropDown;;

public class Main {
    private static final Color BACKGROUND_COLOR = new Color(18, 18, 18);
    private static final Color TEXT_COLOR = new Color(220, 220, 220);
    private static JTextPane resultArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                UIManager.put("Panel.background", BACKGROUND_COLOR);
                UIManager.put("OptionPane.background", BACKGROUND_COLOR);
                UIManager.put("Label.foreground", TEXT_COLOR);
            } catch (Exception e) {
                e.printStackTrace();
            }

            JFrame frame = new JFrame("AP CSA Problem Tester");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);
            frame.setLayout(new GridBagLayout());
            frame.getContentPane().setBackground(BACKGROUND_COLOR);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel titleLabel = new JLabel("AP CSA Problem Tester");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(TEXT_COLOR);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            frame.add(titleLabel, gbc);

            JLabel yearLabel = new JLabel("Select Year:");
            yearLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            yearLabel.setForeground(TEXT_COLOR);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            frame.add(yearLabel, gbc);

            YearDropDown yearDropDown = new YearDropDown("./problem/");
            gbc.gridx = 1;
            gbc.gridy = 1;
            frame.add(yearDropDown, gbc);

            JLabel problemLabel = new JLabel("Select Problem:");
            problemLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            problemLabel.setForeground(TEXT_COLOR);
            gbc.gridx = 0;
            gbc.gridy = 2;
            frame.add(problemLabel, gbc);

            // String[] problemOptions = {"1", "2", "3", "4", "All"};
            // JComboBox<String> problemDropDown = new JComboBox<>(problemOptions);
            // gbc.gridx = 1;
            // gbc.gridy = 2;
            // frame.add(problemDropDown, gbc);

            ProblemDropDown problemOptions = new ProblemDropDown();
            gbc.gridx = 1;
            gbc.gridy = 2;
            frame.add(problemOptions, gbc);

            JButton runButton = new JButton("Run Test");
            runButton.setFont(new Font("Arial", Font.BOLD, 16));
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            frame.add(runButton, gbc);

            resultArea = new JTextPane();
            resultArea.setEditable(false);
            resultArea.setBackground(new Color(30, 30, 30));
            resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            JScrollPane scrollPane = new JScrollPane(resultArea);
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weighty = 1.0;
            frame.add(scrollPane, gbc);

            runButton.addActionListener(e -> {
                String year = (String) yearDropDown.getSelectedItem();
                String problem = (String) problemOptions.getSelectedItem();
                runTest(year, problem);
            });

            frame.setVisible(true);
        });
    }

    private static void runTest(String year, String problem) {
        resultArea.setText("");
        try {
            File file = new File("./problem/" + year);
            URL url = file.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url});

            Class<?> testClass = classLoader.loadClass("Test");
            Object testInstance = testClass.getDeclaredConstructor().newInstance();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            PrintStream oldOut = System.out;
            System.setOut(printStream);

            if ("All".equals(problem)) {
                Method[] methods = testClass.getMethods();
                for (Method method : methods) {
                    if (method.getName().matches("one|two|three|four")) {
                        method.invoke(testInstance);
                    }
                }
            } else {
                Method testMethod = testClass.getMethod(getMethodName(problem));
                testMethod.invoke(testInstance);
            }

            System.out.flush();
            System.setOut(oldOut);
            String results = outputStream.toString();
            displayColoredText(results);

            classLoader.close();

        } catch (Exception e) {
            displayColoredText("Error occurred while running the test: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private static String getMethodName(String problem) {
        switch (problem) {
            case "1": return "one";
            case "2": return "two";
            case "3": return "three";
            case "4": return "four";
            default: throw new IllegalArgumentException("Invalid problem number");
        }
    }

    private static void displayColoredText(String text) {
        StyledDocument doc = resultArea.getStyledDocument();
        resultArea.setText(""); // Clear previous content

        Pattern pattern = Pattern.compile("\u001B\\[([0-9;]*)m");
        Matcher matcher = pattern.matcher(text);
        int lastIndex = 0;
        Color currentColor = Color.WHITE; // Default color

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            // Add text before the color code
            try {
                doc.insertString(doc.getLength(), text.substring(lastIndex, start), getStyle(currentColor));
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            // Update color based on the ANSI code
            String colorCode = matcher.group(1);
            if (colorCode.equals("0")) {
                currentColor = Color.WHITE; // Reset to default
            } else {
                currentColor = getColorFromCode(colorCode);
            }

            lastIndex = end;
        }

        // Add remaining text
        try {
            doc.insertString(doc.getLength(), text.substring(lastIndex), getStyle(currentColor));
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private static Color getColorFromCode(String code) {
        switch (code) {
            case "31": return Color.RED;
            case "32": return Color.GREEN;
            case "33": return Color.YELLOW;
            case "34": return Color.BLUE;
            case "0;31": return Color.RED;
            case "0;32": return Color.GREEN;
            case "0;33": return Color.YELLOW;
            case "0;34": return Color.BLUE;
            default: return Color.WHITE;
        }
    }

    private static Style getStyle(Color color) {
        Style style = resultArea.addStyle("Color Style", null);
        StyleConstants.setForeground(style, color);
        return style;
    }

}