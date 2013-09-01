package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseTypeCreate;
import com.nicoinc.system.ibms.command.CourseTypeGetList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.CourseType;
import com.nicoinc.system.ibms.model.Generation;

public class ViewCourseTypeNew extends JPanel implements CommandListener {
    private static final long serialVersionUID = 5607527208663662381L;
    private JTextField mName;
    private JButton mButtonSave;
    private JProgressBar mProgressBar;

    public ViewCourseTypeNew() {
        JLabel lblNewLabel = new JLabel("CRIAR TIPO DE CURSO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblNewLabel_1 = new JLabel("Nome");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mName = new JTextField();
        mName.setFont(new Font("Arial", Font.PLAIN, 14));
        mName.setColumns(10);

        mButtonSave = new JButton("Criar");
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                CourseType courseType = checkData();
                if (courseType != null) {
                    disableFields();
                    new CourseTypeCreate(courseType, ViewCourseTypeNew.this).run();
                }
            }
        });
        mButtonSave.setSelectedIcon(null);
        mButtonSave.setIcon(new ImageIcon(ViewCourseTypeNew.class.getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                        .addComponent(lblNewLabel_1)
                        .addComponent(mName, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                        .addComponent(mButtonSave)
                        .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblNewLabel_1)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(mButtonSave, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(478, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }

    @Override
    public void onCommandFinished(RequestResult result) {
        switch(result.getCode()) {
        case WITHOUT_CONNECTION:
            JOptionPane.showMessageDialog(this,"Sem conexão com a internet.\nNão foi possível completar a ação.");
            enableFields();
            break;
        case SERVER_ERROR:
            JOptionPane.showMessageDialog(this,"Erro no servidor.\nTente mais tarde.");
            enableFields();
            break;
        case UNKNOWN:
            JOptionPane.showMessageDialog(this,"Erro desconhecido.\nFeche o aplicativo e tente novamente.");
            enableFields();
            break;
        case OK:
            switch (result.getCommand()) {
            case COURSE_TYPE_GET_LIST:
                JOptionPane.showMessageDialog(this,"Tipo de curso criado com sucesso.");
                enableFields();
                break;
            case COURSE_TYPE_CREATE:
                JsonObject root = result.getJSON();
                if (root.has("ERROR_CODE")) {
                    switch (root.getAsInt()) {
                    case 0:
                        JOptionPane.showMessageDialog(this,"Erro");
                        enableFields();
                        break;
                    default:
                        JOptionPane.showMessageDialog(this,"Código de erro desconhecido. Código: " + root.getAsInt());
                        enableFields();
                        break;
                    }
                } else {
                    mName.setText("");
                    new CourseTypeGetList(this).start();
                }
                break;
            default:
                break;
            }
        }
    }

    private void enableFields() {
        mName.setEnabled(true);
        mButtonSave.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mName.setEnabled(false);
        mButtonSave.setEnabled(false);
        mProgressBar.setVisible(true);
    }

    private CourseType checkData() {
        String name = mName.getText().trim().toUpperCase();
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(this,"O nome do tipo de curso deve ser preenchido.");
            return null;
        }

        for (Generation item : Application.getInstance().getGenerationList()) {
            if (name.equals(item.mName)) {
                JOptionPane.showMessageDialog(this,"O nome do tipo de curso já existe.");
                return null;
            }
        }

        CourseType courseType = new CourseType();
        courseType.mName = name;
        courseType.mStartDate = Calendar.getInstance().getTime();
        return courseType;
    }
}
