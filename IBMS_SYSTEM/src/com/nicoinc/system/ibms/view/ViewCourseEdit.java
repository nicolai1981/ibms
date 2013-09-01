package com.nicoinc.system.ibms.view;

import java.awt.Color;
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
import javax.swing.border.LineBorder;

import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseGetList;
import com.nicoinc.system.ibms.command.CourseUpdate;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseType;

public class ViewCourseEdit extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;

    private JComboBox mCourseList;
    private JComboBox mCourseTypeList;
    private JTextField mStartDate;
    private JTextField mEndDate;
    private JTextField mTotalLessons;
    private JProgressBar mProgressBar;
    private JButton mButtonSave;

    public ViewCourseEdit() {
        JLabel lblNewLabel = new JLabel("EDITAR CURSO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblCursoASer = new JLabel("Curso a ser alterado");
        lblCursoASer.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourseList = new JComboBox();
        mCourseList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Course currentCourse = (Course) mCourseList.getSelectedItem();
                if (currentCourse != null) {
                    for (int i=0; i < mCourseTypeList.getItemCount(); i++) {
                        if (((CourseType)mCourseTypeList.getItemAt(i)).mId == currentCourse.mCourseTypeId) {
                            mCourseTypeList.setSelectedIndex(i);
                        }
                    }
                    mStartDate.setText(sDateFormatter.format(currentCourse.mStartDate));
                    mEndDate.setText(sDateFormatter.format(currentCourse.mEndDate));
                    mTotalLessons.setText(String.valueOf(currentCourse.mTotalLessons));
                }
            }
        });
        mCourseList.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));

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

        mButtonSave = new JButton("Salvar");
        mButtonSave.setIcon(new ImageIcon(ViewCourseEdit.class.getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Course course = checkData();
                if (course != null) {
                    disableFields();
                    new CourseUpdate(course, ViewCourseEdit.this).run();
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
                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                        .addComponent(lblCursoASer, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mCourseList, 0, 1110, Short.MAX_VALUE)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(mButtonSave)
                        .addComponent(mProgressBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addGap(18)
                    .addComponent(lblCursoASer, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mCourseList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(mButtonSave)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(389, Short.MAX_VALUE))
        );

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(lblGerao, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(lblDataFinal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(mCourseTypeList, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblTotalDeAulas, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mTotalLessons, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(206, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblGerao)
                        .addComponent(lblNewLabel_1)
                        .addComponent(lblDataFinal)
                        .addComponent(lblTotalDeAulas, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                            .addComponent(mCourseTypeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(mTotalLessons, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(16, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        setLayout(groupLayout);

        // Fill course type list
        for (CourseType item : Application.getInstance().getCourseTypeList()) {
            if (item.mEndDate.getTime() == 0) {
                mCourseTypeList.addItem(item);
            }
        }

        // Fill course list
        long currentTime = Calendar.getInstance().getTimeInMillis();
        for (Course item : Application.getInstance().getCourseList()) {
            if (item.mEndDate.getTime() > currentTime) {
                item.mStringFormat = Course.COMPLETE_INFORMATION;
                mCourseList.addItem(item);
            }
        }
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
            case COURSE_GET_LIST:
                JOptionPane.showMessageDialog(this,"Curso alterado com sucesso.");
                enableFields();
                mCourseList.removeAllItems();
                long currentTime = Calendar.getInstance().getTimeInMillis();
                for (Course item : Application.getInstance().getCourseList()) {
                    if (item.mEndDate.getTime() > currentTime) {
                        item.mStringFormat = Course.COMPLETE_INFORMATION;
                        mCourseList.addItem(item);
                    }
                }
                break;
            case COURSE_UPDATE:
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
                    mStartDate.setText("");
                    mEndDate.setText("");
                    mCourseTypeList.setSelectedIndex(-1);
                    mCourseList.setSelectedIndex(-1);
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
        mCourseList.setEnabled(true);
        mButtonSave.setEnabled(true);
        mTotalLessons.setEditable(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mStartDate.setEnabled(false);
        mEndDate.setEnabled(false);
        mCourseTypeList.setEnabled(false);
        mCourseList.setEnabled(false);
        mButtonSave.setEnabled(false);
        mTotalLessons.setEditable(false);
        mProgressBar.setVisible(true);
    }

    private Course checkData() {
        Course course = new Course();
        Course currentCourse = (Course)mCourseList.getSelectedItem();
        course.mId = currentCourse.mId;
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
            if ((currentCourse.mId != item.mId)
                && (course.mCourseTypeId == item.mCourseTypeId)
                && ((course.mStartDate.getTime() < item.mEndDate.getTime())
                    || (course.mEndDate.getTime() < item.mEndDate.getTime()))) {
                JOptionPane.showMessageDialog(this, "O período do curso deve estar depois do final de todos os cursos do mesmo tipo."
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
            JOptionPane.showMessageDialog(this,"O total de aulas deve ser um número");
            return null;
        }
        if (course.mTotalLessons == 0) {
            JOptionPane.showMessageDialog(this,"O total de aulas não pode ser 0.");
            return null;
        }

        return course;
    }
}
