package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseCreate;
import com.nicoinc.system.ibms.command.CourseGetList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseType;

public class ViewCourseNew extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;

    private JComboBox mCourseTypeList;
    private JTextField mStartDate;
    private JTextField mEndDate;
    private JTextField mTotalLessons;
    private JButton mButtonSave;
    private JProgressBar mProgressBar;

    public ViewCourseNew() {
        JLabel lblNewLabel = new JLabel("CRIAR CURSO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblGerao = new JLabel("Tipo do curso");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourseTypeList = new JComboBox();
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
        mButtonSave.setIcon(new ImageIcon(ViewCourseNew.class.getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
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
        mProgressBar.setVisible(false);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblGerao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mCourseTypeList, 0, 476, Short.MAX_VALUE))
                            .addGap(18)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(mStartDate)
                                .addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblDataFinal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mEndDate, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(mTotalLessons, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTotalDeAulas, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
                        .addComponent(mButtonSave))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_1)
                        .addComponent(lblGerao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataFinal, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotalDeAulas, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mCourseTypeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mTotalLessons, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addComponent(mButtonSave)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(472, Short.MAX_VALUE))
        );
        setLayout(groupLayout);

        // Fill course type list
        for (CourseType item : Application.getInstance().getCourseTypeList()) {
            if (item.mEndDate.getTime() == 0) {
                mCourseTypeList.addItem(item);
            }
        }
        
    }

    @Override
    public void onCommandFinished(RequestResult result) {
        switch(result.getCode()) {
        case WITHOUT_CONNECTION:
            JOptionPane.showMessageDialog(this,"Sem conex�o com a internet.\nN�o foi poss�vel completar a a��o.");
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
            case GET_COURSE_LIST:
                JOptionPane.showMessageDialog(this,"Curso criado com sucesso.");
                enableFields();
                break;
            case CREATE_COURSE:
                JsonObject root = result.getJSON();
                if (root.has("ERROR_CODE")) {
                    switch (root.getAsInt()) {
                    case 0:
                        JOptionPane.showMessageDialog(this,"Erro");
                        enableFields();
                        break;
                    default:
                        JOptionPane.showMessageDialog(this,"C�digo de erro desconhecido. C�digo: " + root.getAsInt());
                        enableFields();
                        break;
                    }
                } else {
                    mStartDate.setText("");
                    mEndDate.setText("");
                    mCourseTypeList.setSelectedIndex(-1);
                    new CourseGetList(this).start();
                }
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
        course.mCourseTypeId = ((CourseType)mCourseTypeList.getSelectedItem()).mId;

        String startDate = mStartDate.getText().trim().toUpperCase();
        if (startDate.length() == 0) {
            JOptionPane.showMessageDialog(this,"A data inicial deve ser preenchida.");
            return null;
        }
        if (startDate.length() != 10) {
            JOptionPane.showMessageDialog(this,"A data inicial deve estar no formato DD/MM/YYYY");
            return null;
        }
        try {
            course.mStartDate = sDateFormatter.parse(startDate);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,"A data inicial deve estar no formato DD/MM/YYYY");
            return null;
        }

        String endDate = mEndDate.getText().trim().toUpperCase();
        if (endDate.length() == 0) {
            JOptionPane.showMessageDialog(this,"A data final deve ser preenchida.");
            return null;
        }
        if (endDate.length() != 10) {
            JOptionPane.showMessageDialog(this,"A data inicial deve estar no formato DD/MM/YYYY");
            return null;
        }
        try {
            course.mEndDate = sDateFormatter.parse(endDate);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,"A data final deve estar no formato DD/MM/YYYY");
            return null;
        }

        if (course.mStartDate.getTime() > course.mEndDate.getTime()) {
            JOptionPane.showMessageDialog(this,"A data final deve ser antes da data inicial.");
            return null;
        }

        for (Course item : Application.getInstance().getCourseList()) {
            if ((course.mCourseTypeId == item.mCourseTypeId)
                && ((course.mStartDate.getTime() < item.mEndDate.getTime())
                    || (course.mEndDate.getTime() < item.mEndDate.getTime()))) {
                JOptionPane.showMessageDialog(this, "O per�odo do curso deve estar depois do final de todos os cursos do mesmo tipo."
                        + "\nData do outro curso: "
                        + sDateFormatter.format(item.mStartDate) + " - "
                        + sDateFormatter.format(item.mEndDate));
                return null;
            }

        }

        String totalLessons = mTotalLessons.getText().trim().toUpperCase();
        if (totalLessons.length() == 0) {
            JOptionPane.showMessageDialog(this,"O total de aulas deve ser preenchido.");
            return null;
        }
        try {
            course.mTotalLessons = Integer.parseInt(totalLessons);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"O total de aulas deve ser um n�mero");
            return null;
        }
        if (course.mTotalLessons == 0) {
            JOptionPane.showMessageDialog(this,"O total de aulas n�o pode ser 0.");
            return null;
        }

        return course;
    }
}
