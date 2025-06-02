import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FormPanel extends JPanel {
    private JTextField nameField;
    private JTextField reminders;

    private JRadioButton monday;
    private JRadioButton tuesday;
    private JRadioButton wednesday;
    private JRadioButton thursday;
    private JRadioButton friday;
    private JRadioButton saturday;
    private ButtonGroup dayRadioButtonGroup;

    private JCheckBox morningShift;
    private JCheckBox afternoonShift;
    private JCheckBox evningShift;

    private JRadioButton fullTime;
    private JRadioButton partTime;
    private ButtonGroup shiftRadioButtonGroup;

    private JComboBox<String> partTimeShiftLenght;
    private JScrollPane listScrollPane; //scrollbar
    private JButton submitButton;

    private FormPanelListener formPanelListener;

    public FormPanel() {
        initFormPanel();
        initComponents();
        initLayout();
        activateForm();
    }

    public void setFormPanelListener(FormPanelListener formPanelListener) {
        this.formPanelListener = formPanelListener;
    }

    private void initFormPanel() {
        setPreferredSize(new Dimension(400, 300));
    }

    private void initComponents() {
        nameField = new JTextField(20); //collumns- broj znakova koji ce se vidit
        reminders = new JTextField();
        //listOfWorkers = new JTextArea();


        //--------------DANI U TJEDNU-----------------------------------
        monday = new JRadioButton("Monday");
        tuesday = new JRadioButton("Tuesday");
        wednesday = new JRadioButton("Wednesday");
        thursday = new JRadioButton("Thursday");
        friday = new JRadioButton("Friday");
        saturday = new JRadioButton("Saturday");
        dayRadioButtonGroup = new ButtonGroup();
        dayRadioButtonGroup.add(monday);
        dayRadioButtonGroup.add(tuesday);
        dayRadioButtonGroup.add(wednesday);
        dayRadioButtonGroup.add(thursday);
        dayRadioButtonGroup.add(friday);
        dayRadioButtonGroup.add(saturday);

        //--------------SMJENA-----------------------------------
        morningShift = new JCheckBox(); //jchecchbix je jer se moze vise shitova na dan raditu
        afternoonShift = new JCheckBox();
        evningShift = new JCheckBox();

        //--------------FULLTIME/PARTTIME-----------------------------------
        fullTime = new JRadioButton("Full work time");
        fullTime.setSelected(true); //defaultno selected true
        partTime = new JRadioButton("Partial work time");
        shiftRadioButtonGroup = new ButtonGroup();
        shiftRadioButtonGroup.add(fullTime);
        shiftRadioButtonGroup.add(partTime);

        //--------------BR SATI ZA SMJENU-----------------------------------
        partTimeShiftLenght = new JComboBox<>();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addAll(List.of("1 h", "1:30 h", "2 h", "2:30 h", "3 h", "3:30 h", "4 h"));
        partTimeShiftLenght.setModel(comboBoxModel);
        partTimeShiftLenght.setSelectedIndex(-1);


        submitButton = new JButton("Submit");
    }

    private void initLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // 1. Name field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Name:"), gbc);
        gbc.gridy++;
        add(nameField, gbc);
        gbc.gridwidth = 1;

        // 2. Days available
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Days available:"), gbc);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        add(monday, gbc);
        gbc.gridx = 1;
        add(tuesday, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        add(wednesday, gbc);
        gbc.gridx = 1;
        add(thursday, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        add(friday, gbc);
        gbc.gridx = 1;
        add(saturday, gbc);

        // 3. Shifts available (horizontal layout)
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Shifts available:"), gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        morningShift.setText("Morning");
        add(morningShift, gbc);
        gbc.gridx = 1;
        afternoonShift.setText("Afternoon");
        add(afternoonShift, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        evningShift.setText("Evening");
        add(evningShift, gbc);

        // 4. Work time type (horizontal)
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Work time type:"), gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(fullTime, gbc);
        gbc.gridx = 1;
        add(partTime, gbc);

        // 5. Part-time duration
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Part-time duration:"), gbc);
        gbc.gridy++;
        add(partTimeShiftLenght, gbc);

        // 6. Right column – Reminders
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.gridx = 2;
        gbcRight.gridy = 0;
        gbcRight.gridheight = 10;
        gbcRight.fill = GridBagConstraints.BOTH;
        gbcRight.anchor = GridBagConstraints.NORTH;
        gbcRight.insets = new Insets(5, 15, 5, 5);
        gbcRight.weightx = 0.4;
        gbcRight.weighty = 1;

        JPanel remindersPanel = new JPanel(new BorderLayout(5, 5));
        remindersPanel.add(new JLabel("Reminders:"), BorderLayout.NORTH);
        reminders.setPreferredSize(new Dimension(200, 200));
        remindersPanel.add(reminders, BorderLayout.CENTER);
        add(remindersPanel, gbcRight);

        // 7. Submit button (bottom, center)
        gbc.gridx = 0;
        gbc.gridy = 20;
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(submitButton, gbc);

        gbc.gridx++;
    }

    private int getSelectedDay() {
        int day = -1;
        if (monday.isSelected()) day = 0;
        if (tuesday.isSelected()) day = 1;
        if (wednesday.isSelected()) day = 2;
        if (thursday.isSelected()) day = 3;
        if (friday.isSelected()) day = 4;
        if (saturday.isSelected()) day = 5;
        return day;
    }

    private ArrayList<Integer> getSelectedShifts() {
        ArrayList<Integer> shifts = new ArrayList<>();
        if (morningShift.isSelected()) shifts.add(0);
        if (afternoonShift.isSelected()) shifts.add(1);
        if (evningShift.isSelected()) shifts.add(2);
        return shifts;
    }

    private void activateForm() {
        submitButton.addActionListener(actionEvent -> {
            String name = nameField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the worker's name", "Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String notes = reminders.getText();

            int day = getSelectedDay();
            if (day == -1) {
                JOptionPane.showMessageDialog(this, "Please select day", "Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ArrayList<Integer> times = getSelectedShifts();
            if (times.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter at least ine shift", "Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ArrayList<Shift> shifts = new ArrayList<>();
            for (int time : times) {
                Shift shift = new Shift(1, time);
                shifts.add(shift);
            }

            Worker worker = new Worker(name, notes, shifts);
            if (formPanelListener != null) {
                formPanelListener.formPanelEventOccurred(worker);
            } else {
                JOptionPane.showMessageDialog(this, "FormPanelListener is null", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
