package components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class DropDown {
    
    private static final Color BACKGROUND_COLOR = new Color(30, 30, 30);
    private static final Color TEXT_COLOR = new Color(220, 220, 220);
    private static final Color HIGHLIGHT_COLOR = new Color(0, 120, 215);
    private static final Color BORDER_COLOR = new Color(60, 60, 60);
    private static final Color BLUE_COLOR = new Color(42, 111, 230);

    private static class ModernComboBoxUI extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("./down-chevron.png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            
            JButton button = new JButton(scaledIcon);
            button.setBackground(BACKGROUND_COLOR);
            button.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            button.setFocusPainted(false);
            return button;
        }
    }

    private static class ModernComboBoxRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
            renderer.setOpaque(true);
            renderer.setBorder(new EmptyBorder(10, 15, 10, 15));
            renderer.setFont(new Font("Arial", Font.PLAIN, 14));
            if (isSelected) {
                renderer.setBackground(HIGHLIGHT_COLOR);
                renderer.setForeground(TEXT_COLOR);
            } else {
                renderer.setBackground(BACKGROUND_COLOR);
                renderer.setForeground(TEXT_COLOR);
            }
            
            return renderer;
        }
    }

    public static class ModernDropDown extends JComboBox<String> {
        public ModernDropDown() {
            setUI(new ModernComboBoxUI());
            setRenderer(new ModernComboBoxRenderer());
            setBackground(BACKGROUND_COLOR);
            setForeground(BLUE_COLOR);
            setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1, true));
            setFont(new Font("Arial", Font.PLAIN, 14));
            setFocusable(false);
            
            // Set the text color of the selected item in the combo box
            ((JLabel)getRenderer()).setForeground(TEXT_COLOR);
        }

        @Override
        public void setPopupVisible(boolean v) {
            super.setPopupVisible(v);
            // Ensure the arrow button repaints when the popup is shown/hidden
            getComponent(0).repaint();
        }
    }

    public static class YearDropDown extends ModernDropDown {
        private ArrayList<String> problemYear;

        public YearDropDown(String rootPath) {
            super();
            problemYear = getYearOfProblem(rootPath);
            for (String year : problemYear) {
                addItem(year);
            }
        }

        private ArrayList<String> getYearOfProblem(String rootPath) {
            ArrayList<String> years = new ArrayList<>();
            File folder = new File(rootPath);
            
            if (folder.exists() && folder.isDirectory()) {
                for (File file : folder.listFiles()) {
                    if (file.isDirectory()) {
                        years.add(file.getName());
                    }
                }
            } else {
                System.out.println("目录不存在或路径错误: " + rootPath);
            }
            return years;
        }
    }

    public static class ProblemDropDown extends ModernDropDown {
        public ProblemDropDown() {
            super();
            String[] quarters = {"1", "2", "3", "4", "All"};
            for (String quarter : quarters) {
                addItem(quarter);
            }
        }
    }
}