import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ListManagementApp extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> jList;
    private JCheckBox selectEvenCheckBox;
    private JCheckBox selectOddCheckBox;
    private JComboBox<String> comboBox;

    public ListManagementApp() {
        setSize(800, 300);
        // Создание модели списка
        listModel = new DefaultListModel<>();

        // Создание JList с использованием модели
        jList = new JList<>(listModel);
        jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Создание флажка (Checkbox) для выбора всех нечетных строк
        selectEvenCheckBox = new JCheckBox("Выбрать все чётные строки");
        selectOddCheckBox = new JCheckBox("Выбрать все нечётные строки");
        selectEvenCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectEvenCheckBox.isSelected()) {
                    selectEvenRows();
                } else {
                    clearSelection();
                }
                updateComboBoxSelection();
            }
        });

        selectOddCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectOddCheckBox.isSelected()) {
                    selectOddRows();
                } else {
                    clearSelection();
                }
            }
        });

        // Создание выпадающего списка (JComboBox)
        comboBox = new JComboBox<>();

        // Создание кнопки для очистки выбора
        JButton clearSelectionButton = new JButton("Очистить выбор");
        clearSelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSelection();
                updateComboBoxSelection();
            }
        });

        // Создание панели с флажком, выпадающим списком и кнопкой
        JPanel controlPanel = new JPanel();
        controlPanel.add(selectEvenCheckBox);
        controlPanel.add(selectOddCheckBox);
        controlPanel.add(comboBox);
        controlPanel.add(clearSelectionButton);

        // Добавление компонентов в окно
        getContentPane().add(new JScrollPane(jList), BorderLayout.CENTER);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);

        // Настройка окна
        setTitle("Приложение управления списком");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Добавление значений в список
    public void addItems(List<String> items) {
        for (String item : items) {
            listModel.addElement(item);
        }
    }

    // Выбор всех нечетных строк
    private void selectEvenRows() {
        int size = listModel.getSize();
        for (int i = 0; i < size; i++) {
            if ((i+1) % 2 == 0) {
                jList.addSelectionInterval(i, i);
            }
        }
    }
    private void selectOddRows() {
        int size = listModel.getSize();
        for (int i = 0; i < size; i++) {
            if ((i+1) % 2 != 0) {
                jList.addSelectionInterval(i, i);
            }
        }
    }

    // Обновление выбранных значений в выпадающем списке
    private void updateComboBoxSelection() {
        Object[] selectedValues = jList.getSelectedValues();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(convertToStringArray(selectedValues));
        comboBox.setModel(comboBoxModel);
    }

    // Очистка выбора
    private void clearSelection() {
        jList.clearSelection();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        comboBox.setModel(comboBoxModel);
    }

    // Преобразование массива объектов в массив строк
    private String[] convertToStringArray(Object[] objects) {
        String[] strings = new String[objects.length];
        for (int i = 0; i < objects.length; i++) {
            strings[i] = objects[i].toString();
        }
        return strings;
    }

    public static void main(String[] args) {
        ListManagementApp app = new ListManagementApp();

        // Пример добавления значений в список
        List<String> items = new ArrayList<>();
        items.add("Значение 1");
        items.add("Значение 2");
        items.add("Значение 3");
        items.add("Значение 4");
        items.add("Значение 5");
        items.add("Значение 6");
        items.add("Значение 7");
        items.add("Значение 8");
        items.add("Значение 9");
        items.add("Значение 10");
        app.addItems(items);
    }
}