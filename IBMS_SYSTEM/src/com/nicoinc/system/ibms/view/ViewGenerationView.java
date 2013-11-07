package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;

public class ViewGenerationView extends JPanel {
    private static final long serialVersionUID = -8908763683014288749L;
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    private JLabel mLeader;
    private JComboBox<Generation> mGenerationList;
    private JLabel mStartDate;
    private JLabel mStatus;
    private JLabel mEndDate;
    private JLabel mGenerationCount;
    private JLabel mQtdLeader;
    private JLabel mQtdMemberTotal;
    private JLabel mQtdMember;
    private JLabel mQtdMemberNot;
    private JLabel mQtdOffTotal;
    private JLabel mQtdOffMember;
    private JLabel mQtdOffMemberNot;
    private JProgressBar mProgressBar;
    private DefaultListModel<Member> mOffListModel;
    private DefaultListModel<Member> mMemberListModel;
    private DefaultListModel<Member> mLeaderListModel;

    public ViewGenerationView() {
        JLabel lblGerao = new JLabel("Gera\u00E7\u00E3o");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        mGenerationList = new JComboBox<Generation>();
        mGenerationList.setFont(new Font("Arial", Font.PLAIN, 14));
        mGenerationList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                clearFields();
                Generation currentGeneration = (Generation) mGenerationList.getSelectedItem();
                if (currentGeneration != null) {
                    setGenerationData(currentGeneration);
                }
            }
        });

        JLabel label = new JLabel("L\u00EDder");
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        mLeader = new JLabel("-");
        mLeader.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataDeCriao = new JLabel("Data de Cria\u00E7\u00E3o");
        lblDataDeCriao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JLabel("DD/MM/AAAA");
        mStartDate.setHorizontalAlignment(SwingConstants.CENTER);
        mStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o");
        lblSituao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStatus = new JLabel("-");
        mStatus.setHorizontalAlignment(SwingConstants.LEFT);
        mStatus.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataDeDesativao = new JLabel("Data de Desativa\u00E7\u00E3o");
        lblDataDeDesativao.setFont(new Font("Arial", Font.PLAIN, 14));

        mEndDate = new JLabel("DD/MM/AAAA");
        mEndDate.setHorizontalAlignment(SwingConstants.CENTER);
        mEndDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblTotal = new JLabel("Total de disc\u00EDpulos Ativos");
        lblTotal.setFont(new Font("Arial", Font.PLAIN, 14));

        mGenerationCount = new JLabel("0");
        mGenerationCount.setHorizontalAlignment(SwingConstants.CENTER);
        mGenerationCount.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblLderes = new JLabel("L\u00EDderes");
        lblLderes.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblTotal_1 = new JLabel("Total:");
        lblTotal_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mQtdLeader = new JLabel("0");
        mQtdLeader.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane_leader = new JScrollPane();
        scrollPane_leader.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_leader.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mLeaderListModel = new DefaultListModel<Member>();
        JList<Member> leaderList = new JList<Member>(mLeaderListModel);
        leaderList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane_leader.setViewportView(leaderList);

        JLabel lblDiscpulos = new JLabel("Disc\u00EDpulos");
        lblDiscpulos.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblTotal_2 = new JLabel("Total:");
        lblTotal_2.setFont(new Font("Arial", Font.PLAIN, 14));

        mQtdMemberTotal = new JLabel("0");
        mQtdMemberTotal.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblMembros = new JLabel("Membros:");
        lblMembros.setFont(new Font("Arial", Font.PLAIN, 14));

        mQtdMember = new JLabel("0");
        mQtdMember.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblNoMembros = new JLabel("N\u00E3o Membros:");
        lblNoMembros.setFont(new Font("Arial", Font.PLAIN, 14));

        mQtdMemberNot = new JLabel("0");
        mQtdMemberNot.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane_member = new JScrollPane();
        scrollPane_member.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_member.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mMemberListModel = new DefaultListModel<Member>();
        JList<Member> memberList = new JList<Member>(mMemberListModel);
        memberList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane_member.setViewportView(memberList);

        JLabel lblDesligados = new JLabel("Desligados");
        lblDesligados.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblTotal_3 = new JLabel("Total:");
        lblTotal_3.setFont(new Font("Arial", Font.PLAIN, 14));

        mQtdOffTotal = new JLabel("0");
        mQtdOffTotal.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel label_8 = new JLabel("Membros:");
        label_8.setFont(new Font("Arial", Font.PLAIN, 14));

        mQtdOffMember = new JLabel("0");
        mQtdOffMember.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel label_10 = new JLabel("N\u00E3o Membros:");
        label_10.setFont(new Font("Arial", Font.PLAIN, 14));

        mQtdOffMemberNot = new JLabel("0");
        mQtdOffMemberNot.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane_off = new JScrollPane();
        scrollPane_off.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_off.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mOffListModel = new DefaultListModel<Member>();
        JList<Member> offList = new JList<Member>(mOffListModel);
        offList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane_off.setViewportView(offList);

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

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
                                                        .addGroup(
                                                                groupLayout
                                                                        .createSequentialGroup()
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addComponent(lblGerao)
                                                                                        .addComponent(
                                                                                                mGenerationList,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                549,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createSequentialGroup()
                                                                                                        .addGroup(
                                                                                                                groupLayout
                                                                                                                        .createParallelGroup(
                                                                                                                                Alignment.TRAILING,
                                                                                                                                false)
                                                                                                                        .addComponent(
                                                                                                                                mStartDate,
                                                                                                                                Alignment.LEADING,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                        .addComponent(
                                                                                                                                lblDataDeCriao,
                                                                                                                                Alignment.LEADING,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE))
                                                                                                        .addGap(18)
                                                                                                        .addGroup(
                                                                                                                groupLayout
                                                                                                                        .createParallelGroup(
                                                                                                                                Alignment.LEADING,
                                                                                                                                false)
                                                                                                                        .addComponent(
                                                                                                                                lblSituao,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                        .addComponent(
                                                                                                                                mStatus,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                103,
                                                                                                                                Short.MAX_VALUE))
                                                                                                        .addGap(18)
                                                                                                        .addGroup(
                                                                                                                groupLayout
                                                                                                                        .createParallelGroup(
                                                                                                                                Alignment.LEADING,
                                                                                                                                false)
                                                                                                                        .addComponent(
                                                                                                                                mEndDate,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                        .addComponent(
                                                                                                                                lblDataDeDesativao,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE))))
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                label,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                32,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(mGenerationCount)
                                                                                        .addComponent(mLeader)
                                                                                        .addComponent(lblTotal)))
                                                        .addGroup(
                                                                groupLayout
                                                                        .createSequentialGroup()
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createParallelGroup(
                                                                                                                Alignment.LEADING,
                                                                                                                false)
                                                                                                        .addComponent(
                                                                                                                lblLderes,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                        .addGroup(
                                                                                                                groupLayout
                                                                                                                        .createSequentialGroup()
                                                                                                                        .addComponent(
                                                                                                                                lblTotal_1)
                                                                                                                        .addPreferredGap(
                                                                                                                                ComponentPlacement.RELATED)
                                                                                                                        .addComponent(
                                                                                                                                mQtdLeader,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                33,
                                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                                        .addComponent(
                                                                                                scrollPane_leader,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                360, Short.MAX_VALUE))
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                lblTotal_2)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                mQtdMemberTotal,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                36,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                lblMembros)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                mQtdMember,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                47,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                lblNoMembros)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                mQtdMemberNot,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                47,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                        .addComponent(
                                                                                                lblDiscpulos,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                352,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(
                                                                                                scrollPane_member,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                378,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING,
                                                                                                false)
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                lblTotal_3)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                mQtdOffTotal,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                47,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                label_8,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                62,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                mQtdOffMember,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                47,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                label_10,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                91,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                mQtdOffMemberNot,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                47,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                        .addComponent(lblDesligados)
                                                                                        .addComponent(scrollPane_off)))
                                                        .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 1110,
                                                                Short.MAX_VALUE)).addContainerGap()));
        groupLayout
                .setVerticalGroup(groupLayout
                        .createParallelGroup(Alignment.LEADING)
                        .addGroup(
                                groupLayout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(lblGerao)
                                                        .addComponent(label, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(mGenerationList, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mLeader))
                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(Alignment.LEADING)
                                                        .addGroup(
                                                                groupLayout
                                                                        .createSequentialGroup()
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.BASELINE)
                                                                                        .addComponent(
                                                                                                lblDataDeCriao,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                17,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(
                                                                                                lblSituao,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                17,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(
                                                                                                lblDataDeDesativao,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                17,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                Alignment.BASELINE)
                                                                                        .addComponent(
                                                                                                mStartDate,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                17,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(
                                                                                                mStatus,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                17,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(
                                                                                                mEndDate,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                17,
                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(
                                                                groupLayout
                                                                        .createSequentialGroup()
                                                                        .addComponent(lblTotal,
                                                                                GroupLayout.PREFERRED_SIZE, 17,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(mGenerationCount,
                                                                                GroupLayout.PREFERRED_SIZE, 17,
                                                                                GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(lblLderes, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblDiscpulos, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblDesligados, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(lblTotal_1, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mQtdLeader, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblTotal_2, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mQtdMemberTotal, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblMembros, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mQtdMember, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblNoMembros, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mQtdMemberNot, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mQtdOffMemberNot, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label_10, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mQtdOffMember, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label_8, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mQtdOffTotal, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblTotal_3, GroupLayout.PREFERRED_SIZE, 17,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(scrollPane_leader, GroupLayout.PREFERRED_SIZE,
                                                                434, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(scrollPane_member, GroupLayout.PREFERRED_SIZE,
                                                                434, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(scrollPane_off, GroupLayout.PREFERRED_SIZE, 433,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(17, Short.MAX_VALUE)));
        setLayout(groupLayout);

        // Fill generation list
        for (Generation item : Application.getInstance().getGenerationAllList()) {
            mGenerationList.addItem(item);
        }
    }

    private void enableFields() {
        mGenerationList.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mGenerationList.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void clearFields() {
        mOffListModel.removeAllElements();
        mMemberListModel.removeAllElements();
        mLeaderListModel.removeAllElements();
        mQtdLeader.setText("0");
        mQtdMemberTotal.setText("0");
        mQtdMember.setText("0");
        mQtdMemberNot.setText("0");
        mQtdOffTotal.setText("0");
        mQtdOffMember.setText("0");
        mQtdOffMemberNot.setText("0");
        mGenerationCount.setText("0");
    }

    private void setGenerationData(Generation generation) {
        mStartDate.setText(sDateFormatter.format(generation.mStartDate));
        if (generation.mEndDate.getTime() == 0) {
            mStatus.setText("ATIVO");
            mEndDate.setText("-");
        } else {
            mStatus.setText("INATIVO");
            mEndDate.setText(sDateFormatter.format(generation.mEndDate));
        }
        Member leader = Application.getInstance().getLeader(generation.mLeaderId);
        if (leader == null) {
            mLeader.setText("-");
        } else {
            mLeader.setText(leader.mName);
        }
        disableFields();

        List<Member> list = Application.getInstance().getGenerationMemberList(generation.mId);
        int qtdLeader = 0;
        int qtdMember = 0;
        int qtdMemberNot = 0;
        int qtdOffMember = 0;
        int qtdOffMemberNot = 0;

        for (Member member : list) {
            if (member.mEndDate.getTime() == 0) {
                if (member.mIsLeader) {
                    mLeaderListModel.addElement(member);
                    qtdLeader++;
                } else {
                    mMemberListModel.addElement(member);
                    if (member.mStartDate.getTime() == 0) {
                        qtdMemberNot++;
                    } else {
                        qtdMember++;
                    }
                }
            } else {
                mOffListModel.addElement(member);
                if (member.mStartDate.getTime() == 0) {
                    qtdOffMemberNot++;
                } else {
                    qtdOffMember++;
                }
            }
        }
        list.clear();

        mQtdLeader.setText(String.valueOf(qtdLeader));
        mQtdMemberTotal.setText(String.valueOf(qtdMember + qtdMemberNot));
        mQtdMember.setText(String.valueOf(qtdMember));
        mQtdMemberNot.setText(String.valueOf(qtdMemberNot));
        mQtdOffTotal.setText(String.valueOf(qtdOffMember + qtdOffMemberNot));
        mQtdOffMember.setText(String.valueOf(qtdOffMember));
        mQtdOffMemberNot.setText(String.valueOf(qtdOffMemberNot));
        mGenerationCount.setText(String.valueOf(qtdLeader + qtdMember + qtdMemberNot));

        enableFields();
    }
}
