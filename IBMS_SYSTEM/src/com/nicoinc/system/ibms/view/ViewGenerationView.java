package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
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
    private DefaultListModel mOffListModel;
    private DefaultListModel mMemberListModel;
    private DefaultListModel mLeaderListModel;

    public ViewGenerationView(Generation generation) {
        JLabel lblGerao = new JLabel("Gera\u00E7\u00E3o");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel label = new JLabel("L\u00EDder");
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        mLeader = new JLabel("-");
        mLeader.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataDeCriao = new JLabel("Data de Cria\u00E7\u00E3o");
        lblDataDeCriao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JLabel("DD/MM/AAAA");
        mStartDate.setHorizontalAlignment(SwingConstants.LEFT);
        mStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o");
        lblSituao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStatus = new JLabel("-");
        mStatus.setHorizontalAlignment(SwingConstants.LEFT);
        mStatus.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataDeDesativao = new JLabel("Data de Desativa\u00E7\u00E3o");
        lblDataDeDesativao.setFont(new Font("Arial", Font.PLAIN, 14));

        mEndDate = new JLabel("DD/MM/AAAA");
        mEndDate.setHorizontalAlignment(SwingConstants.LEFT);
        mEndDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblTotal = new JLabel("Total de disc\u00EDpulos Ativos");
        lblTotal.setFont(new Font("Arial", Font.PLAIN, 14));

        mGenerationCount = new JLabel("0");
        mGenerationCount.setHorizontalAlignment(SwingConstants.LEFT);
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

        mLeaderListModel = new DefaultListModel();
        JList leaderList = new JList(mLeaderListModel);
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

        mMemberListModel = new DefaultListModel();
        JList memberList = new JList(mMemberListModel);
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

        mOffListModel = new DefaultListModel();
        JList offList = new JList(mOffListModel);
        offList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane_off.setViewportView(offList);

        JLabel mName = new JLabel("DD/MM/AAAA");
        mName.setHorizontalAlignment(SwingConstants.LEFT);
        mName.setFont(new Font("Arial", Font.BOLD, 14));

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(mName, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblGerao, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE))
                            .addGap(10)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(label, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                                .addComponent(mLeader, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                            .addGap(10))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblTotal_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mQtdLeader, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED))
                                .addComponent(scrollPane_leader, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                                .addComponent(lblLderes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(5)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblDiscpulos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrollPane_member, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblTotal_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mQtdMemberTotal, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(lblMembros, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mQtdMember, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(lblNoMembros, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mQtdMemberNot, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
                            .addGap(5)
                            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                    .addComponent(lblTotal_3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mQtdOffTotal, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(label_8, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mQtdOffMember, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(label_10, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mQtdOffMemberNot, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                    .addPreferredGap(ComponentPlacement.RELATED))
                                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                    .addComponent(lblDesligados, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                                    .addGap(10))
                                .addComponent(scrollPane_off, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
                            .addGap(10))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(mStartDate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDataDeCriao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                            .addGap(10)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblSituao, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addComponent(mStatus, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                            .addGap(10)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(mEndDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDataDeDesativao, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(mGenerationCount, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTotal))))
                    .addGap(0))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblGerao)
                        .addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(mName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mLeader))
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblDataDeCriao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSituao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataDeDesativao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStatus, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mGenerationCount, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblLderes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDesligados, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDiscpulos, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblTotal_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mQtdLeader, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotal_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mQtdMemberTotal, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMembros, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mQtdMember, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNoMembros, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mQtdMemberNot, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mQtdOffMemberNot, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_10, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mQtdOffMember, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_8, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mQtdOffTotal, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotal_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(scrollPane_leader, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                        .addComponent(scrollPane_member, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                        .addComponent(scrollPane_off, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
                    .addGap(10))
        );
        setLayout(groupLayout);

        setGenerationData(generation);
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
    }
}
