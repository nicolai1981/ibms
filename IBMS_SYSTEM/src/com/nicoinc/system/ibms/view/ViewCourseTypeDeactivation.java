package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseTypeGetList;
import com.nicoinc.system.ibms.command.CourseTypeUpdate;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.CourseType;

public class ViewCourseTypeDeactivation extends JPanel implements CommandListener {
    private static final long serialVersionUID = 7561701453305316660L;

    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private JComboBox mCourseTypeList;
    private JLabel mStartDate;
    private JLabel mStatus;
    private JLabel mEndDate;
    private JButton mButtonAction;
    private JProgressBar mProgressBar;

    public ViewCourseTypeDeactivation() {
        JLabel lblAtivarDesativar = new JLabel("ATIVAR / DESATIVAR TIPO DE CURSO");
        lblAtivarDesativar.setHorizontalAlignment(SwingConstants.CENTER);
        lblAtivarDesativar.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblGerao = new JLabel("Tipo do curso");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourseTypeList = new JComboBox();
        mCourseTypeList.setFont(new Font("Arial", Font.PLAIN, 14));
        mCourseTypeList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                CourseType currentCourseType = (CourseType) mCourseTypeList.getSelectedItem();
                if (currentCourseType != null) {
                    mStartDate.setText(sDateFormatter.format(currentCourseType.mStartDate));
                    if (currentCourseType.mEndDate.getTime() == 0) {
                        mStatus.setText("ATIVO");
                        mEndDate.setText("-");
                        mButtonAction.setText("DESATIVAR");
                    } else {
                        mStatus.setText("INATIVO");
                        mEndDate.setText(sDateFormatter.format(currentCourseType.mEndDate));
                        mButtonAction.setText("ATIVAR");
                    }
                }
            }
        });

        JLabel lblDataDeCriao = new JLabel("Data de Cria\u00E7\u00E3o");
        lblDataDeCriao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JLabel("DD/MM/AAAA");
        mStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o");
        lblSituao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStatus = new JLabel("-");
        mStatus.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataDeDesativao = new JLabel("Data de Desativa\u00E7\u00E3o");
        lblDataDeDesativao.setFont(new Font("Arial", Font.PLAIN, 14));

        mEndDate = new JLabel("DD/MM/AAAA");
        mEndDate.setFont(new Font("Arial", Font.BOLD, 14));

        mButtonAction = new JButton("-");
        mButtonAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CourseType currentCourseType = (CourseType) mCourseTypeList.getSelectedItem();
                if (currentCourseType.mEndDate.getTime() == 0) {
                    int result = JOptionPane.showConfirmDialog(ViewCourseTypeDeactivation.this, "Deseja realmente desativar o tipo do curso: " + currentCourseType.mName);
                    if (result != 0) {
                        return;
                    }
                }

                disableFields();

                CourseType courseType = new CourseType();
                courseType.mId = currentCourseType.mId;
                courseType.mName = currentCourseType.mName;
                courseType.mStartDate = currentCourseType.mStartDate;
                if (currentCourseType.mEndDate.getTime() == 0) {
                    courseType.mEndDate = Calendar.getInstance().getTime();
                }
                new CourseTypeUpdate(courseType, ViewCourseTypeDeactivation.this).start();
            }
        });
        mButtonAction.setIcon(null);
        mButtonAction.setFont(new Font("Arial", Font.PLAIN, 14));

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblAtivarDesativar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(lblGerao)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(mStartDate)
                                .addComponent(lblDataDeCriao, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblSituao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mStatus, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblDataDeDesativao)
                                .addComponent(mEndDate))
                            .addGap(697))
                        .addComponent(mButtonAction)
                        .addComponent(mProgressBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(mCourseTypeList, 0, 1110, Short.MAX_VALUE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblAtivarDesativar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(lblGerao)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mCourseTypeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(11)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblDataDeCriao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSituao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataDeDesativao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(mStatus, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                            .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                        .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mButtonAction)
                    .addGap(18)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(92, Short.MAX_VALUE))
        );
        setLayout(groupLayout);

        // Fill course type list
        for (CourseType item : Application.getInstance().getCourseTypeList()) {
            mCourseTypeList.addItem(item);
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
            case COURSE_TYPE_GET_LIST:
                mCourseTypeList.removeAllItems();
                for (CourseType item : Application.getInstance().getCourseTypeList()) {
                    mCourseTypeList.addItem(item);
                }
                JOptionPane.showMessageDialog(this,"Tipo de curso alterado com sucesso.");
                enableFields();
                break;

            case COURSE_TYPE_UPDATE:
                new CourseTypeGetList(this).start();
                break;
            default:
                break;
            }
        }
    }

    private void enableFields() {
        mCourseTypeList.setEnabled(true);
        mButtonAction.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mCourseTypeList.setEnabled(false);
        mButtonAction.setEnabled(false);
        mProgressBar.setVisible(true);
    }
}
