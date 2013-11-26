package com.nicoinc.system.ibms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseGetSubscribeList;
import com.nicoinc.system.ibms.command.CourseUpdateSubscribe;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;

public class ViewCourseStudent extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;
    private Course mCourse;
    private JLabel mCourseType;
    private JLabel mStartDate;
    private JLabel mEndDate;
    private JLabel mTotalLessons;
    private JList<CourseSubscribe> mStudentList;
    private DefaultListModel<CourseSubscribe> mStudentListModel;
    private JLabel mStudentName;
    private JLabel mStudentStartDate;
    private JComboBox<String> mStudentCompleted;
    private JComboBox<String> mStudentEnd;
    private JLabel mStudentEndDate;
    private JButton mButtonSave;

    private JProgressBar mProgressBar;
    private JTextField mStudentTotalLessons;

    private boolean mSaveAction = false;

    public ViewCourseStudent(Course course) {
        mCourse = course;

        JLabel lblAlunospresena = new JLabel("Alunos");
        lblAlunospresena.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPaneComplete = new JScrollPane();
        scrollPaneComplete.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneComplete.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mStudentListModel = new DefaultListModel<CourseSubscribe>();
        mStudentList = new JList<CourseSubscribe>(mStudentListModel);
        mStudentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mStudentList.setFont(new Font("Arial", Font.BOLD, 14));
        mStudentList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    CourseSubscribe student = (CourseSubscribe) mStudentList.getSelectedValue();
                    if (student != null) {
                        mStudentName.setText(student.mMemberName);
                        mStudentStartDate.setText(sDateFormatter.format(student.mStartDate));
                        if (student.mCompleted) {
                            mStudentCompleted.setSelectedIndex(1);
                        } else {
                            mStudentCompleted.setSelectedIndex(0);
                        }

                        mStudentTotalLessons.setText(String.valueOf(student.mTotalLessons));

                        if (student.mEndDate.getTime() == 0) {
                            mStudentEnd.setSelectedIndex(0);
                            mStudentEndDate.setText("-");
                        } else {
                            mStudentEnd.setSelectedIndex(1);
                            mStudentEndDate.setText(sDateFormatter.format(student.mEndDate));
                        }
                    }
                }
            }
        });
        scrollPaneComplete.setViewportView(mStudentList);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblNomeDoAluno = new JLabel("Nome do aluno");
        lblNomeDoAluno.setFont(new Font("Arial", Font.PLAIN, 14));

        mStudentName = new JLabel("-");
        mStudentName.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataDeInscrio = new JLabel("Data de inscri\u00E7\u00E3o");
        lblDataDeInscrio.setFont(new Font("Arial", Font.PLAIN, 14));

        mStudentStartDate = new JLabel("-");
        mStudentStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblAulasPresente = new JLabel("Aulas Presente");
        lblAulasPresente.setFont(new Font("Arial", Font.PLAIN, 14));

        mStudentTotalLessons = new JTextField();
        mStudentTotalLessons.setFont(new Font("Arial", Font.PLAIN, 14));
        mStudentTotalLessons.setColumns(10);

        JLabel lblCompletouOCurso = new JLabel("Completou o curso");
        lblCompletouOCurso.setFont(new Font("Arial", Font.PLAIN, 14));

        mStudentCompleted = new JComboBox<String>();
        mStudentCompleted.addItem("NÃO");
        mStudentCompleted.addItem("SIM");
        mStudentCompleted.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblDesistente = new JLabel("Desistente");
        lblDesistente.setFont(new Font("Arial", Font.PLAIN, 14));

        mStudentEnd = new JComboBox<String>();
        mStudentEnd.addItem("NÃO");
        mStudentEnd.addItem("SIM");
        mStudentEnd.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblDataDeDesistncia = new JLabel("Data de desist\u00EAncia");
        lblDataDeDesistncia.setFont(new Font("Arial", Font.PLAIN, 14));

        mStudentEndDate = new JLabel("-");
        mStudentEndDate.setFont(new Font("Arial", Font.BOLD, 14));

        mButtonSave = new JButton("Salvar");
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CourseSubscribe student = checkData();
                if (student != null) {
                    disableFields();
                    CourseSubscribe oldStudent = (CourseSubscribe) mStudentList.getSelectedValue();
                    if ((student.mEndDate.getTime() != 0) && (oldStudent.mEndDate.getTime() == 0)) {
                        int result = JOptionPane.showConfirmDialog(ViewCourseStudent.this,
                                "Deseja realmente marcar o aluno " + oldStudent.mMemberName + " como desistente?");
                        if (result != 0) {
                            return;
                        }
                    }
                    new CourseUpdateSubscribe(student, ViewCourseStudent.this).run();
                }
            }
        });
        mButtonSave.setIcon(new ImageIcon(ViewCourseStudent.class
                .getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(mStudentTotalLessons, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                .addComponent(lblAulasPresente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18)
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(mStudentCompleted, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCompletouOCurso, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(lblNomeDoAluno)
                        .addComponent(mStudentName)
                        .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
                            .addComponent(mStudentStartDate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDataDeInscrio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(mStudentEnd, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDesistente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                            .addGap(18)
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                .addComponent(mStudentEndDate, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDataDeDesistncia)))
                        .addComponent(mButtonSave))
                    .addContainerGap(318, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNomeDoAluno)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mStudentName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(lblDataDeInscrio)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mStudentStartDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblAulasPresente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCompletouOCurso, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mStudentTotalLessons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStudentCompleted, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblDesistente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataDeDesistncia))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mStudentEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStudentEndDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(271)
                    .addComponent(mButtonSave)
                    .addContainerGap())
        );
        panel_1.setLayout(gl_panel_1);

        JLabel lblGerao = new JLabel("Tipo do curso");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourseType = new JLabel(mCourse.mCourseTypeName);
        mCourseType.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblNewLabel_1 = new JLabel("Data inicial");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JLabel(sDateFormatter.format(mCourse.mStartDate));
        mStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataFinal = new JLabel("Data final");
        lblDataFinal.setFont(new Font("Arial", Font.PLAIN, 14));

        mEndDate = new JLabel(sDateFormatter.format(mCourse.mEndDate));
        mEndDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblTotalDeAulas = new JLabel("Total de Aulas");
        lblTotalDeAulas.setFont(new Font("Arial", Font.PLAIN, 14));

        mTotalLessons = new JLabel(String.valueOf(mCourse.mTotalLessons));
        mTotalLessons.setFont(new Font("Arial", Font.BOLD, 14));

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(scrollPaneComplete, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 569, Short.MAX_VALUE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(mCourseType, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18)
                                    .addComponent(mStartDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblGerao, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18)
                                    .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
                            .addGap(18)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblDataFinal, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18)
                                    .addComponent(lblTotalDeAulas, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18)
                                    .addComponent(mTotalLessons, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))))
                        .addComponent(lblAlunospresena, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblGerao)
                        .addComponent(lblNewLabel_1)
                        .addComponent(lblDataFinal)
                        .addComponent(lblTotalDeAulas))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mCourseType)
                        .addComponent(mStartDate)
                        .addComponent(mEndDate)
                        .addComponent(mTotalLessons))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(lblAlunospresena, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                        .addComponent(scrollPaneComplete, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        setLayout(groupLayout);

        new CourseGetSubscribeList(mCourse, this).run();
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
            case COURSE_GET_SUBSCRIBE_LIST:
                List<CourseSubscribe> subscriptionList = (List<CourseSubscribe>) result.getData(CourseGetSubscribeList.SUBSCRIBE_LIST);
                for (CourseSubscribe subscription : subscriptionList) {
                    if (!subscription.mIsTeacher) {
                        mStudentListModel.addElement(subscription);
                    }
                }

                if (mSaveAction) {
                    mSaveAction = false;
                    JOptionPane.showMessageDialog(this, "Dados salvos com sucesso.");
                }
                enableFields();
                break;

            case COURSE_UPDATE_SUBSCRIBE:
                mSaveAction = true;
                clearFields();
                new CourseGetSubscribeList(mCourse, ViewCourseStudent.this).run();
                break;

            default:
                break;
            }
        }
    }

    private void enableFields() {
        mStudentList.setEnabled(true);
        mStudentCompleted.setEnabled(true);
        mStudentEnd.setEnabled(true);
        mButtonSave.setEnabled(true);
        mStudentTotalLessons.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mStudentList.setEnabled(false);
        mStudentCompleted.setEnabled(false);
        mStudentEnd.setEnabled(false);
        mButtonSave.setEnabled(false);
        mStudentTotalLessons.setEnabled(false);
        mProgressBar.setVisible(true);
    }

    private void clearFields() {
        mStudentListModel.removeAllElements();
        mStudentName.setText("-");
        mStudentStartDate.setText("-");
        mStudentCompleted.setSelectedIndex(-1);
        mStudentEnd.setSelectedIndex(-1);
        mStudentEndDate.setText("-");
        mStudentTotalLessons.setText("0");
    }

    private CourseSubscribe checkData() {
        CourseSubscribe student = new CourseSubscribe();
        CourseSubscribe oldData = (CourseSubscribe) mStudentList.getSelectedValue();

        student.mId = oldData.mId;
        student.mCourseId = oldData.mCourseId;
        student.mMemberId = oldData.mMemberId;
        student.mMemberName = oldData.mMemberName;
        student.mStartDate = new Date(oldData.mStartDate.getTime());
        student.mEndDate = new Date(oldData.mEndDate.getTime());
        student.mIsTeacher = false;

        student.mCompleted = (mStudentCompleted.getSelectedIndex() == 1);

        if (mStudentEnd.getSelectedIndex() == 0) {
            student.mEndDate = new Date(0);
        } else if ((mStudentEnd.getSelectedIndex() == 1) && (student.mEndDate.getTime() == 0)) {
            student.mEndDate = Calendar.getInstance().getTime();
        }

        String totalLessons = mStudentTotalLessons.getText().trim().toUpperCase();
        if (totalLessons.length() == 0) {
            JOptionPane.showMessageDialog(this, "O total de aulas deve ser preenchido.");
            return null;
        }
        try {
            student.mTotalLessons = Integer.parseInt(totalLessons);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O total de aulas deve ser um número");
            return null;
        }
        if (student.mTotalLessons > mCourse.mTotalLessons) {
            JOptionPane.showMessageDialog(this, "O total de aulas não pode ser maior que o número de aulas do curso.");
            return null;
        }

        return student;
    }
}
