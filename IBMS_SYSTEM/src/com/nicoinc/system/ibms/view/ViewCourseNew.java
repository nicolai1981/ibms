package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseCreate;
import com.nicoinc.system.ibms.command.CourseGetList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseType;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ViewCourseNew extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;

    private FrameHome mHome;
    private JComboBox<CourseType> mCourseTypeList;
    private JComboBox<String> mWeekDay;
    private JTextField mStartDate;
    private JTextField mEndDate;
    private JTextField mTotalLessons;
    private JButton mButtonSave;
    private JProgressBar mProgressBar;
    private JTextField mClass1;
    private JTextField mClass2;
    private JTextField mClass3;
    private JTextField mClass4;
    private JTextField mClass5;
    private JTextField mClass6;
    private JTextField mClass7;
    private JTextField mClass8;
    private JTextField mClass9;
    private JTextField mClass10;
    private JTextField mClass11;
    private JTextField mClass12;
    private JTextField mClass13;
    private JTextField mClass14;
    private JTextField mClass15;
    private JTextField mClass16;
    private JTextField mClass17;
    private JTextField mClass18;
    private JTextField mClass19;
    private JTextField mClass20;
    private JTextField mClass21;
    private JTextField mClass22;
    private JTextField mClass23;
    private JTextField mClass24;
    private JTextField mClass25;
    private JTextField mClass26;
    private JTextField mClass27;
    private JTextField mClass28;
    private JTextField mClass29;
    private JTextField mClass30;
    private JTextField mClass31;
    private JTextField mClass32;
    private JTextField mClass33;
    private JTextField mClass34;
    private JTextField mClass35;
    private JTextField mClass36;
    private JTextField mClass37;
    private JTextField mClass38;
    private JTextField mClass39;
    private JTextField mClass40;
    private JTextField mClass41;
    private JTextField mClass42;
    private JTextField mClass43;
    private JTextField mClass44;
    private JTextField mClass45;
    private JTextField mClass46;
    private JTextField mClass47;
    private JTextField mClass48;
    private JTextField mClass49;
    private JTextField mClass50;

    public ViewCourseNew(FrameHome home) {
        mHome = home;

        JLabel lblNewLabel = new JLabel("CRIAR CURSO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblGerao = new JLabel("Tipo do curso");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourseTypeList = new JComboBox<CourseType>();
        mCourseTypeList.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblNewLabel_1 = new JLabel("Data inicial");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JTextField();
        mStartDate.setFont(new Font("Arial", Font.PLAIN, 14));
        mStartDate.setColumns(10);

        JLabel lblDataFinal = new JLabel("Data final");
        lblDataFinal.setFont(new Font("Arial", Font.PLAIN, 14));

        mEndDate = new JTextField();
        mEndDate.setFont(new Font("Arial", Font.PLAIN, 14));
        mEndDate.setColumns(10);

        JLabel lblTotalDeAulas = new JLabel("Total de aulas");
        lblTotalDeAulas.setFont(new Font("Arial", Font.PLAIN, 14));

        mTotalLessons = new JTextField();
        mTotalLessons.setFont(new Font("Arial", Font.PLAIN, 14));
        mTotalLessons.setColumns(10);

        mButtonSave = new JButton("Criar");
        mButtonSave.setIcon(new ImageIcon(ViewCourseNew.class
                .getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Course course = checkData();
                if (course != null) {
                    disableFields();
                    new CourseCreate(course, ViewCourseNew.this).run();
                }
            }
        });

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        
        JLabel lblDiaDaSemana = new JLabel("Dia da semana");
        lblDiaDaSemana.setFont(new Font("Arial", Font.PLAIN, 14));

        mWeekDay = new JComboBox<String>();
        mWeekDay.setFont(new Font("Arial", Font.PLAIN, 14));
        mWeekDay.addItem("Domingo");
        mWeekDay.addItem("Segunda");
        mWeekDay.addItem("Terça");
        mWeekDay.addItem("Quarta");
        mWeekDay.addItem("Quinta");
        mWeekDay.addItem("Sexta");
        mWeekDay.addItem("Sábado");

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Dias das aulas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(null);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 1110, GroupLayout.PREFERRED_SIZE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblGerao, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                .addComponent(mCourseTypeList, 0, 476, Short.MAX_VALUE))
                            .addGap(5)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(mStartDate, GroupLayout.DEFAULT_SIZE, 80, GroupLayout.DEFAULT_SIZE)
                                .addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                            .addGap(5)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblDataFinal, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addComponent(mEndDate, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                            .addGap(5)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(mTotalLessons, 0, 0, Short.MAX_VALUE)
                                .addComponent(lblTotalDeAulas, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                            .addGap(5)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(mWeekDay, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDiaDaSemana, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                        .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, 1110, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mButtonSave, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 910, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(10)
                    .addComponent(lblNewLabel)
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_1)
                        .addComponent(lblGerao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataFinal, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotalDeAulas, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDiaDaSemana, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(1)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mCourseTypeList, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mTotalLessons, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mWeekDay, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                    .addGap(20)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
                    .addGap(180)
                    .addComponent(mButtonSave)
                    .addGap(10)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        JLabel lblAula = new JLabel("1\u00AA Aula");
        lblAula.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula.setBounds(10, 25, 80, 17);
        panel.add(lblAula);
        
        mClass1 = new JTextField();
        mClass1.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass1.setColumns(10);
        mClass1.setBounds(10, 44, 80, 23);
        panel.add(mClass1);
        
        JLabel lblAula_1 = new JLabel("2\u00AA Aula");
        lblAula_1.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_1.setBounds(100, 25, 80, 17);
        panel.add(lblAula_1);
        
        mClass2 = new JTextField();
        mClass2.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass2.setColumns(10);
        mClass2.setBounds(100, 44, 80, 23);
        panel.add(mClass2);
        
        JLabel lblAula_2 = new JLabel("3\u00AA Aula");
        lblAula_2.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_2.setBounds(190, 25, 80, 17);
        panel.add(lblAula_2);
        
        mClass3 = new JTextField();
        mClass3.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass3.setColumns(10);
        mClass3.setBounds(190, 44, 80, 23);
        panel.add(mClass3);
        
        JLabel lblAula_3 = new JLabel("4\u00AA Aula");
        lblAula_3.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_3.setBounds(280, 25, 80, 17);
        panel.add(lblAula_3);
        
        mClass4 = new JTextField();
        mClass4.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass4.setColumns(10);
        mClass4.setBounds(280, 44, 80, 23);
        panel.add(mClass4);
        
        JLabel lblAula_4 = new JLabel("5\u00AA Aula");
        lblAula_4.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_4.setBounds(370, 25, 80, 17);
        panel.add(lblAula_4);
        
        mClass5 = new JTextField();
        mClass5.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass5.setColumns(10);
        mClass5.setBounds(370, 44, 80, 23);
        panel.add(mClass5);
        
        JLabel lblAula_5 = new JLabel("6\u00AA Aula");
        lblAula_5.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_5.setBounds(460, 25, 80, 17);
        panel.add(lblAula_5);
        
        mClass6 = new JTextField();
        mClass6.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass6.setColumns(10);
        mClass6.setBounds(460, 44, 80, 23);
        panel.add(mClass6);
        
        JLabel lblAula_6 = new JLabel("7\u00AA Aula");
        lblAula_6.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_6.setBounds(550, 25, 80, 17);
        panel.add(lblAula_6);
        
        mClass7 = new JTextField();
        mClass7.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass7.setColumns(10);
        mClass7.setBounds(550, 44, 80, 23);
        panel.add(mClass7);
        
        JLabel lblAula_7 = new JLabel("8\u00AA Aula");
        lblAula_7.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_7.setBounds(640, 25, 80, 17);
        panel.add(lblAula_7);
        
        mClass8 = new JTextField();
        mClass8.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass8.setColumns(10);
        mClass8.setBounds(640, 44, 80, 23);
        panel.add(mClass8);
        
        JLabel lblAula_8 = new JLabel("9\u00AA Aula");
        lblAula_8.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_8.setBounds(730, 25, 80, 17);
        panel.add(lblAula_8);
        
        mClass9 = new JTextField();
        mClass9.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass9.setColumns(10);
        mClass9.setBounds(730, 44, 80, 23);
        panel.add(mClass9);
        
        JLabel lblAula_9 = new JLabel("10\u00AA Aula");
        lblAula_9.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_9.setBounds(820, 25, 80, 17);
        panel.add(lblAula_9);
        
        mClass10 = new JTextField();
        mClass10.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass10.setColumns(10);
        mClass10.setBounds(820, 44, 80, 23);
        panel.add(mClass10);
        
        JLabel lblAula_10 = new JLabel("11\u00AA Aula");
        lblAula_10.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_10.setBounds(10, 78, 80, 17);
        panel.add(lblAula_10);
        
        mClass11 = new JTextField();
        mClass11.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass11.setColumns(10);
        mClass11.setBounds(10, 97, 80, 23);
        panel.add(mClass11);
        
        JLabel lblAula_11 = new JLabel("12\u00AA Aula");
        lblAula_11.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_11.setBounds(100, 78, 80, 17);
        panel.add(lblAula_11);
        
        mClass12 = new JTextField();
        mClass12.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass12.setColumns(10);
        mClass12.setBounds(100, 97, 80, 23);
        panel.add(mClass12);
        
        JLabel lblAula_12 = new JLabel("13\u00AA Aula");
        lblAula_12.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_12.setBounds(190, 78, 80, 17);
        panel.add(lblAula_12);
        
        mClass13 = new JTextField();
        mClass13.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass13.setColumns(10);
        mClass13.setBounds(190, 97, 80, 23);
        panel.add(mClass13);
        
        JLabel lblAula_13 = new JLabel("14\u00AA Aula");
        lblAula_13.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_13.setBounds(280, 78, 80, 17);
        panel.add(lblAula_13);
        
        mClass14 = new JTextField();
        mClass14.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass14.setColumns(10);
        mClass14.setBounds(280, 97, 80, 23);
        panel.add(mClass14);
        
        JLabel lblAula_14 = new JLabel("15\u00AA Aula");
        lblAula_14.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_14.setBounds(370, 78, 80, 17);
        panel.add(lblAula_14);
        
        mClass15 = new JTextField();
        mClass15.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass15.setColumns(10);
        mClass15.setBounds(370, 97, 80, 23);
        panel.add(mClass15);
        
        JLabel lblAula_15 = new JLabel("16\u00AA Aula");
        lblAula_15.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_15.setBounds(460, 78, 80, 17);
        panel.add(lblAula_15);
        
        mClass16 = new JTextField();
        mClass16.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass16.setColumns(10);
        mClass16.setBounds(460, 97, 80, 23);
        panel.add(mClass16);
        
        JLabel lblAula_16 = new JLabel("17\u00AA Aula");
        lblAula_16.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_16.setBounds(550, 78, 80, 17);
        panel.add(lblAula_16);
        
        mClass17 = new JTextField();
        mClass17.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass17.setColumns(10);
        mClass17.setBounds(550, 97, 80, 23);
        panel.add(mClass17);
        
        JLabel lblAula_17 = new JLabel("18\u00AA Aula");
        lblAula_17.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_17.setBounds(640, 78, 80, 17);
        panel.add(lblAula_17);
        
        mClass18 = new JTextField();
        mClass18.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass18.setColumns(10);
        mClass18.setBounds(640, 97, 80, 23);
        panel.add(mClass18);
        
        JLabel lblAula_18 = new JLabel("19\u00AA Aula");
        lblAula_18.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_18.setBounds(730, 78, 80, 17);
        panel.add(lblAula_18);
        
        mClass19 = new JTextField();
        mClass19.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass19.setColumns(10);
        mClass19.setBounds(730, 97, 80, 23);
        panel.add(mClass19);
        
        JLabel lblAula_19 = new JLabel("20\u00AA Aula");
        lblAula_19.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_19.setBounds(820, 78, 80, 17);
        panel.add(lblAula_19);
        
        mClass20 = new JTextField();
        mClass20.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass20.setColumns(10);
        mClass20.setBounds(820, 97, 80, 23);
        panel.add(mClass20);
        
        JLabel lblAula_29 = new JLabel("21\u00AA Aula");
        lblAula_29.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_29.setBounds(10, 131, 80, 17);
        panel.add(lblAula_29);
        
        mClass21 = new JTextField();
        mClass21.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass21.setColumns(10);
        mClass21.setBounds(10, 150, 80, 23);
        panel.add(mClass21);
        
        JLabel lblAula_28 = new JLabel("22\u00AA Aula");
        lblAula_28.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_28.setBounds(100, 131, 80, 17);
        panel.add(lblAula_28);
        
        mClass22 = new JTextField();
        mClass22.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass22.setColumns(10);
        mClass22.setBounds(100, 150, 80, 23);
        panel.add(mClass22);
        
        JLabel lblAula_27 = new JLabel("23\u00AA Aula");
        lblAula_27.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_27.setBounds(190, 131, 80, 17);
        panel.add(lblAula_27);
        
        mClass23 = new JTextField();
        mClass23.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass23.setColumns(10);
        mClass23.setBounds(190, 150, 80, 23);
        panel.add(mClass23);
        
        JLabel lblAula_26 = new JLabel("24\u00AA Aula");
        lblAula_26.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_26.setBounds(280, 131, 80, 17);
        panel.add(lblAula_26);
        
        mClass24 = new JTextField();
        mClass24.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass24.setColumns(10);
        mClass24.setBounds(280, 150, 80, 23);
        panel.add(mClass24);
        
        JLabel lblAula_25 = new JLabel("25\u00AA Aula");
        lblAula_25.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_25.setBounds(370, 131, 80, 17);
        panel.add(lblAula_25);
        
        mClass25 = new JTextField();
        mClass25.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass25.setColumns(10);
        mClass25.setBounds(370, 150, 80, 23);
        panel.add(mClass25);
        
        JLabel lblAula_24 = new JLabel("26\u00AA Aula");
        lblAula_24.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_24.setBounds(460, 131, 80, 17);
        panel.add(lblAula_24);
        
        mClass26 = new JTextField();
        mClass26.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass26.setColumns(10);
        mClass26.setBounds(460, 150, 80, 23);
        panel.add(mClass26);
        
        JLabel lblAula_23 = new JLabel("27\u00AA Aula");
        lblAula_23.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_23.setBounds(550, 131, 80, 17);
        panel.add(lblAula_23);
        
        mClass27 = new JTextField();
        mClass27.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass27.setColumns(10);
        mClass27.setBounds(550, 150, 80, 23);
        panel.add(mClass27);
        
        JLabel lblAula_22 = new JLabel("28\u00AA Aula");
        lblAula_22.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_22.setBounds(640, 131, 80, 17);
        panel.add(lblAula_22);
        
        mClass28 = new JTextField();
        mClass28.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass28.setColumns(10);
        mClass28.setBounds(640, 150, 80, 23);
        panel.add(mClass28);
        
        JLabel lblAula_21 = new JLabel("29\u00AA Aula");
        lblAula_21.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_21.setBounds(730, 131, 80, 17);
        panel.add(lblAula_21);
        
        mClass29 = new JTextField();
        mClass29.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass29.setColumns(10);
        mClass29.setBounds(730, 150, 80, 23);
        panel.add(mClass29);
        
        JLabel lblAula_20 = new JLabel("30\u00AA Aula");
        lblAula_20.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_20.setBounds(820, 131, 80, 17);
        panel.add(lblAula_20);
        
        mClass30 = new JTextField();
        mClass30.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass30.setColumns(10);
        mClass30.setBounds(820, 150, 80, 23);
        panel.add(mClass30);
        
        JLabel lblAula_30 = new JLabel("31\u00AA Aula");
        lblAula_30.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_30.setBounds(10, 184, 80, 17);
        panel.add(lblAula_30);
        
        mClass31 = new JTextField();
        mClass31.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass31.setColumns(10);
        mClass31.setBounds(10, 203, 80, 23);
        panel.add(mClass31);
        
        JLabel lblAula_31 = new JLabel("32\u00AA Aula");
        lblAula_31.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_31.setBounds(100, 184, 80, 17);
        panel.add(lblAula_31);
        
        mClass32 = new JTextField();
        mClass32.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass32.setColumns(10);
        mClass32.setBounds(100, 203, 80, 23);
        panel.add(mClass32);
        
        JLabel lblAula_32 = new JLabel("33\u00AA Aula");
        lblAula_32.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_32.setBounds(190, 184, 80, 17);
        panel.add(lblAula_32);
        
        mClass33 = new JTextField();
        mClass33.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass33.setColumns(10);
        mClass33.setBounds(190, 203, 80, 23);
        panel.add(mClass33);
        
        JLabel lblAula_33 = new JLabel("34\u00AA Aula");
        lblAula_33.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_33.setBounds(280, 184, 80, 17);
        panel.add(lblAula_33);
        
        mClass34 = new JTextField();
        mClass34.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass34.setColumns(10);
        mClass34.setBounds(280, 203, 80, 23);
        panel.add(mClass34);
        
        JLabel lblAula_34 = new JLabel("35\u00AA Aula");
        lblAula_34.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_34.setBounds(370, 184, 80, 17);
        panel.add(lblAula_34);
        
        mClass35 = new JTextField();
        mClass35.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass35.setColumns(10);
        mClass35.setBounds(370, 203, 80, 23);
        panel.add(mClass35);
        
        JLabel lblAula_35 = new JLabel("36\u00AA Aula");
        lblAula_35.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_35.setBounds(460, 184, 80, 17);
        panel.add(lblAula_35);
        
        mClass36 = new JTextField();
        mClass36.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass36.setColumns(10);
        mClass36.setBounds(460, 203, 80, 23);
        panel.add(mClass36);
        
        JLabel lblAula_36 = new JLabel("37\u00AA Aula");
        lblAula_36.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_36.setBounds(550, 184, 80, 17);
        panel.add(lblAula_36);
        
        mClass37 = new JTextField();
        mClass37.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass37.setColumns(10);
        mClass37.setBounds(550, 203, 80, 23);
        panel.add(mClass37);
        
        JLabel lblAula_37 = new JLabel("38\u00AA Aula");
        lblAula_37.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_37.setBounds(640, 184, 80, 17);
        panel.add(lblAula_37);
        
        mClass38 = new JTextField();
        mClass38.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass38.setColumns(10);
        mClass38.setBounds(640, 203, 80, 23);
        panel.add(mClass38);
        
        JLabel lblAula_38 = new JLabel("39\u00AA Aula");
        lblAula_38.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_38.setBounds(730, 184, 80, 17);
        panel.add(lblAula_38);
        
        mClass39 = new JTextField();
        mClass39.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass39.setColumns(10);
        mClass39.setBounds(730, 203, 80, 23);
        panel.add(mClass39);
        
        JLabel lblAula_39 = new JLabel("40\u00AA Aula");
        lblAula_39.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_39.setBounds(820, 184, 80, 17);
        panel.add(lblAula_39);
        
        mClass40 = new JTextField();
        mClass40.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass40.setColumns(10);
        mClass40.setBounds(820, 203, 80, 23);
        panel.add(mClass40);
        
        JLabel lblAula_49 = new JLabel("41\u00AA Aula");
        lblAula_49.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_49.setBounds(10, 237, 80, 17);
        panel.add(lblAula_49);
        
        mClass41 = new JTextField();
        mClass41.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass41.setColumns(10);
        mClass41.setBounds(10, 256, 80, 23);
        panel.add(mClass41);
        
        JLabel lblAula_48 = new JLabel("42\u00AA Aula");
        lblAula_48.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_48.setBounds(100, 237, 80, 17);
        panel.add(lblAula_48);
        
        mClass42 = new JTextField();
        mClass42.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass42.setColumns(10);
        mClass42.setBounds(100, 256, 80, 23);
        panel.add(mClass42);
        
        JLabel lblAula_47 = new JLabel("43\u00AA Aula");
        lblAula_47.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_47.setBounds(190, 237, 80, 17);
        panel.add(lblAula_47);
        
        mClass43 = new JTextField();
        mClass43.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass43.setColumns(10);
        mClass43.setBounds(190, 256, 80, 23);
        panel.add(mClass43);
        
        JLabel lblAula_46 = new JLabel("44\u00AA Aula");
        lblAula_46.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_46.setBounds(280, 237, 80, 17);
        panel.add(lblAula_46);
        
        mClass44 = new JTextField();
        mClass44.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass44.setColumns(10);
        mClass44.setBounds(280, 256, 80, 23);
        panel.add(mClass44);
        
        JLabel lblAula_45 = new JLabel("45\u00AA Aula");
        lblAula_45.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_45.setBounds(370, 237, 80, 17);
        panel.add(lblAula_45);
        
        mClass45 = new JTextField();
        mClass45.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass45.setColumns(10);
        mClass45.setBounds(370, 256, 80, 23);
        panel.add(mClass45);
        
        JLabel lblAula_44 = new JLabel("46\u00AA Aula");
        lblAula_44.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_44.setBounds(460, 237, 80, 17);
        panel.add(lblAula_44);
        
        mClass46 = new JTextField();
        mClass46.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass46.setColumns(10);
        mClass46.setBounds(460, 256, 80, 23);
        panel.add(mClass46);
        
        JLabel lblAula_43 = new JLabel("47\u00AA Aula");
        lblAula_43.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_43.setBounds(550, 237, 80, 17);
        panel.add(lblAula_43);
        
        mClass47 = new JTextField();
        mClass47.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass47.setColumns(10);
        mClass47.setBounds(550, 256, 80, 23);
        panel.add(mClass47);
        
        JLabel lblAula_42 = new JLabel("48\u00AA Aula");
        lblAula_42.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_42.setBounds(640, 237, 80, 17);
        panel.add(lblAula_42);
        
        mClass48 = new JTextField();
        mClass48.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass48.setColumns(10);
        mClass48.setBounds(640, 256, 80, 23);
        panel.add(mClass48);
        
        JLabel lblAula_41 = new JLabel("49\u00AA Aula");
        lblAula_41.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_41.setBounds(730, 237, 80, 17);
        panel.add(lblAula_41);
        
        mClass49 = new JTextField();
        mClass49.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass49.setColumns(10);
        mClass49.setBounds(730, 256, 80, 23);
        panel.add(mClass49);
        
        JLabel lblAula_40 = new JLabel("50\u00AA Aula");
        lblAula_40.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAula_40.setBounds(820, 237, 80, 17);
        panel.add(lblAula_40);
        
        mClass50 = new JTextField();
        mClass50.setFont(new Font("Arial", Font.PLAIN, 14));
        mClass50.setColumns(10);
        mClass50.setBounds(820, 256, 80, 23);
        panel.add(mClass50);
        setLayout(groupLayout);

        // Fill course type list
        for (CourseType item : Application.getInstance().getCourseTypeActivatedList()) {
            if (item.mEndDate.getTime() == 0) {
                mCourseTypeList.addItem(item);
            }
        }

        mProgressBar.setVisible(false);
    }

    @Override
    public void onCommandFinished(RequestResult result) {
        switch (result.getCode()) {
        case WITHOUT_CONNECTION:
            JOptionPane.showMessageDialog(this, "Sem conexão com a internet.\nNão foi possível completar a ação.");
            enableFields();
            break;
        case SERVER_ERROR:
            JOptionPane.showMessageDialog(this, "Erro no servidor.\nTente mais tarde.");
            enableFields();
            break;
        case UNKNOWN:
            JOptionPane.showMessageDialog(this, "Erro desconhecido.\nFeche o aplicativo e tente novamente.");
            enableFields();
            break;
        case OK:
            switch (result.getCommand()) {
            case COURSE_GET_LIST:
                JOptionPane.showMessageDialog(this, "Curso criado com sucesso.");
                enableFields();
                mHome.showSelectCourse();
                break;
            case COURSE_CREATE:
                mStartDate.setText("");
                mEndDate.setText("");
                mTotalLessons.setText("");
                mCourseTypeList.setSelectedIndex(-1);
                new CourseGetList(this).start();
                break;
            default:
                break;
            }
        }
    }

    private void enableFields() {
        mStartDate.setEnabled(true);
        mEndDate.setEnabled(true);
        mCourseTypeList.setEnabled(true);
        mButtonSave.setEnabled(true);
        mTotalLessons.setEditable(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mStartDate.setEnabled(false);
        mEndDate.setEnabled(false);
        mCourseTypeList.setEnabled(false);
        mButtonSave.setEnabled(false);
        mTotalLessons.setEditable(false);
        mProgressBar.setVisible(true);
    }

    private Course checkData() {
        Course course = new Course();
        course.mCourseTypeId = ((CourseType) mCourseTypeList.getSelectedItem()).mId;

        String startDate = mStartDate.getText().trim().toUpperCase();
        if (startDate.length() == 0) {
            JOptionPane.showMessageDialog(this, "A data inicial deve ser preenchida.");
            return null;
        }
        if (startDate.length() != 10) {
            JOptionPane.showMessageDialog(this, "A data inicial deve estar no formato DD/MM/YYYY");
            return null;
        }
        try {
            course.mStartDate = sDateFormatter.parse(startDate);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "A data inicial deve estar no formato DD/MM/YYYY");
            return null;
        }
        if (course.mStartDate.getTime() < Calendar.getInstance().getTimeInMillis()) {
            JOptionPane.showMessageDialog(this, "A data inicial não pode ser antes da data de hoje.");
            return null;
        }

        String endDate = mEndDate.getText().trim().toUpperCase();
        if (endDate.length() == 0) {
            JOptionPane.showMessageDialog(this, "A data final deve ser preenchida.");
            return null;
        }
        if (endDate.length() != 10) {
            JOptionPane.showMessageDialog(this, "A data inicial deve estar no formato DD/MM/YYYY");
            return null;
        }
        try {
            course.mEndDate = sDateFormatter.parse(endDate);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "A data final deve estar no formato DD/MM/YYYY");
            return null;
        }

        if (course.mStartDate.getTime() > course.mEndDate.getTime()) {
            JOptionPane.showMessageDialog(this, "A data final deve ser antes da data inicial.");
            return null;
        }

        String totalLessons = mTotalLessons.getText().trim().toUpperCase();
        if (totalLessons.length() == 0) {
            JOptionPane.showMessageDialog(this, "O total de aulas deve ser preenchido.");
            return null;
        }
        try {
            course.mTotalLessons = Integer.parseInt(totalLessons);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O total de aulas deve ser um número");
            return null;
        }
        if (course.mTotalLessons == 0) {
            JOptionPane.showMessageDialog(this, "O total de aulas não pode ser 0.");
            return null;
        }
        if (course.mTotalLessons > 50) {
            JOptionPane.showMessageDialog(this, "O total de aulas não pode ser maior que 50.");
            return null;
        }
        if (course.mTotalLessons < 0) {
            JOptionPane.showMessageDialog(this, "O total de aulas não pode ser negativo.");
            return null;
        }

        return course;
    }
}
