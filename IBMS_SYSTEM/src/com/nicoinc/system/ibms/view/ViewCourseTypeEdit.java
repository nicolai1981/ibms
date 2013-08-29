package com.nicoinc.system.ibms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.nicoinc.system.ibms.command.CourseTypeGetList;
import com.nicoinc.system.ibms.command.CourseTypeUpdate;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.CourseType;
import com.nicoinc.system.ibms.model.Generation;

public class ViewCourseTypeEdit extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;
    private JTextField mName;
    private JButton mButtonSave;
    private JComboBox mCourseTypeList;
    private JProgressBar mProgressBar;
    private CourseType mCurrentCourseType;
    private JLabel mStartDate;

    public ViewCourseTypeEdit() {
        JLabel lblNewLabel = new JLabel("ALTERAR TIPO DO CURSO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblGerao = new JLabel("Tipo do curso a ser alterada");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourseTypeList = new JComboBox();
        mCourseTypeList.setFont(new Font("Arial", Font.PLAIN, 14));
        mCourseTypeList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mCurrentCourseType = (CourseType) mCourseTypeList.getSelectedItem();
                if (mCurrentCourseType != null) {
                    mName.setText(mCurrentCourseType.mName);
                    mStartDate.setText(sDateFormatter.format(mCurrentCourseType.mStartDate));
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblNewLabel_1 = new JLabel("Nome");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mName = new JTextField();
        mName.setFont(new Font("Arial", Font.PLAIN, 14));
        mName.setColumns(10);

        JLabel lblDataDaCriao = new JLabel("Data da Cria\u00E7\u00E3o");
        lblDataDaCriao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JLabel("DD/MM/AAAA");
        mStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        mButtonSave = new JButton("Salvar");
        mButtonSave.setIcon(new ImageIcon(ViewCourseTypeEdit.class.getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CourseType courseType = checkData();
                if (courseType != null) {
                    mName.setEnabled(false);
                    mButtonSave.setEnabled(false);
                    mCourseTypeList.setEnabled(false);
                    mProgressBar.setVisible(true);
                    new CourseTypeUpdate(courseType, ViewCourseTypeEdit.this).run();
                }
            }
        });

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(mProgressBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(lblGerao, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(mCourseTypeList, 0, 1110, Short.MAX_VALUE)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 1108, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mButtonSave))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblGerao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mCourseTypeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mButtonSave)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(369, Short.MAX_VALUE))
        );

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addComponent(mName, GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
                                .addComponent(lblNewLabel_1)
                                .addComponent(lblDataDaCriao)
                                .addComponent(mStartDate))
                                .addContainerGap())
                );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNewLabel_1)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(mName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(lblDataDaCriao)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(mStartDate)
                        .addContainerGap(14, Short.MAX_VALUE))
                );
        panel.setLayout(gl_panel);
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
            case GET_COURSE_TYPE_LIST:
                mCourseTypeList.removeAllItems();
                for (CourseType item : Application.getInstance().getCourseTypeList()) {
                    if (item.mEndDate.getTime() == 0) {
                        mCourseTypeList.addItem(item);
                    }
                }
                JOptionPane.showMessageDialog(this,"Tipo de curso alterado com sucesso.");
                enableFields();
                break;
            case UPDATE_COURSE_TYPE:
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
        mCourseTypeList.setEnabled(true);
        mButtonSave.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private CourseType checkData() {
        String name = mName.getText().trim().toUpperCase();
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(this,"O nome do tipo de curso deve ser preenchido.");
            return null;
        }

        for (Generation item : Application.getInstance().getGenerationList()) {
            if (name.equals(item.mName) && !name.equals(mCurrentCourseType.mName)) {
                JOptionPane.showMessageDialog(this,"O nome do tipo de curso j� existe.");
                return null;
            }
        }

        CourseType courseType = new CourseType();
        courseType.mId = mCurrentCourseType.mId;
        courseType.mName = name;
        courseType.mStartDate = new Date(mCurrentCourseType.mStartDate.getTime());
        return courseType;
    }
}
