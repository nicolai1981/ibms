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
    private JComboBox mCourseTypeList;
    private JTextField mStartDate;
    private JTextField mEndDate;
    private JTextField mTotalLessons;
    private JLabel mVersion;
    private JProgressBar mProgressBar;
    private JButton mButtonSave;
    private Course mCourse;
    private FrameHome mHome;

    public ViewCourseEdit(FrameHome home, Course course) {
        mHome = home;
        mCourse = course;

        JLabel lblNewLabel = new JLabel("EDITAR CURSO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        mButtonSave = new JButton("Salvar");
        mButtonSave.setIcon(new ImageIcon(ViewCourseEdit.class
                .getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
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

        JLabel lblGerao = new JLabel("Tipo do curso");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourseTypeList = new JComboBox();
        mCourseTypeList.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblNewLabel_1 = new JLabel("Data inicial");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JTextField();
        mStartDate.setFont(new Font("Arial", Font.PLAIN, 14));
        mStartDate.setText(sDateFormatter.format(mCourse.mStartDate));

        JLabel lblDataFinal = new JLabel("Data final");
        lblDataFinal.setFont(new Font("Arial", Font.PLAIN, 14));

        mEndDate = new JTextField();
        mEndDate.setFont(new Font("Arial", Font.PLAIN, 14));
        mEndDate.setText(sDateFormatter.format(mCourse.mEndDate));

        JLabel lblTotalDeAulas = new JLabel("Total de aulas");
        lblTotalDeAulas.setFont(new Font("Arial", Font.PLAIN, 14));

        mTotalLessons = new JTextField();
        mTotalLessons.setFont(new Font("Arial", Font.PLAIN, 14));
        mTotalLessons.setText(String.valueOf(mCourse.mTotalLessons));

        JLabel lblEdio = new JLabel("Edi\u00E7\u00E3o");
        lblEdio.setFont(new Font("Arial", Font.PLAIN, 14));

        mVersion = new JLabel("");
        mVersion.setFont(new Font("Arial", Font.BOLD, 14));
        mVersion.setText(String.valueOf(mCourse.mVersion));

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout
                .setHorizontalGroup(groupLayout
                        .createParallelGroup(Alignment.LEADING)
                        .addGroup(
                                groupLayout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 987,
                                                                Short.MAX_VALUE)
                                                        .addGroup(
                                                                groupLayout
                                                                        .createSequentialGroup()
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                lblGerao,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                480,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.UNRELATED)
                                                                                                        .addComponent(
                                                                                                                lblNewLabel_1,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                130,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                mCourseTypeList,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                480,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.UNRELATED)
                                                                                                        .addComponent(
                                                                                                                mStartDate,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                130,
                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING,
                                                                                                false)
                                                                                        .addComponent(mEndDate)
                                                                                        .addComponent(
                                                                                                lblDataFinal,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                130, Short.MAX_VALUE))
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                mTotalLessons,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                100,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(
                                                                                                lblTotalDeAulas,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                100,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                mVersion,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                100,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(
                                                                                                lblEdio,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                100,
                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 987,
                                                                Short.MAX_VALUE)
                                                        .addComponent(mButtonSave, GroupLayout.PREFERRED_SIZE, 130,
                                                                GroupLayout.PREFERRED_SIZE)).addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addGap(10)
                        .addComponent(lblNewLabel)
                        .addGap(15)
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblGerao)
                                        .addComponent(lblNewLabel_1)
                                        .addComponent(lblDataFinal)
                                        .addComponent(lblTotalDeAulas)
                                        .addComponent(lblEdio, GroupLayout.PREFERRED_SIZE, 17,
                                                GroupLayout.PREFERRED_SIZE))
                        .addGap(5)
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(mCourseTypeList, GroupLayout.PREFERRED_SIZE, 23,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mTotalLessons, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(mVersion, GroupLayout.PREFERRED_SIZE, 23,
                                                GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED, 139, Short.MAX_VALUE).addComponent(mButtonSave)
                        .addGap(10)
                        .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)));
        setLayout(groupLayout);

        // Fill course type list
        for (CourseType item : Application.getInstance().getCourseTypeAllList()) {
            mCourseTypeList.addItem(item);
            if (mCourse.mCourseTypeId == item.mId) {
                mCourseTypeList.setSelectedItem(item);
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
                JOptionPane.showMessageDialog(this, "Curso alterado com sucesso.");
                enableFields();
                mHome.showSelectCourse();
                break;
            case COURSE_UPDATE:
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
        course.mId = mCourse.mId;
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

        return course;
    }
}
