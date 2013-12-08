package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.MemberGetDetails;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;
import com.nicoinc.system.ibms.model.Member;
import com.nicoinc.system.ibms.model.MemberLeader;

public class ViewMemberDetails extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;
    private JProgressBar mProgressBar;
    private JTable mLeaderList;
    private LeaderTableModel mLeaderModel;
    private JLabel mName;
    private JLabel lblDataNasc;
    private JLabel mBirthday;
    private JLabel lblSexo;
    private JLabel lblSangue;
    private JLabel lblEstadoCivil;
    private JLabel lblRg;
    private JLabel lblCpf;
    private JLabel mGender;
    private JLabel mBlood;
    private JLabel mMaritial;
    private JLabel mRG;
    private JLabel mCPF;
    private JLabel lblCelular;
    private JLabel mLeader;
    private JLabel mGeneration;
    private JLabel mMobile;
    private JLabel lblTelResidencial;
    private JLabel lblTelComercial;
    private JLabel lblEmail;
    private JLabel mPhoneHome;
    private JLabel mPhoneWork;
    private JLabel mEmail;
    private JLabel lblEndereo;
    private JLabel lblComplemento;
    private JLabel lblBairro;
    private JLabel lblCep;
    private JLabel lblCidade;
    private JLabel mAddress;
    private JLabel mAddressExtra;
    private JLabel mDistrict;
    private JLabel mCity;
    private JLabel mCEP;
    private JLabel lblEntrada;
    private JLabel lblDataMembro;
    private JLabel lblData;
    private JLabel lblComo;
    private JLabel mStart;
    private JLabel mIsMember;
    private JLabel mMemberDate;
    private JLabel mMemberStartType;
    private JLabel lblDesativao;
    private JLabel mGraduation;
    private JLabel lblDesativao_1;
    private JLabel mEnd;
    private JLabel lblHistricoClula;
    private JLabel lblHistricoEncontro;
    private JLabel lblHistricoMinistrio;
    private JScrollPane scrollPane_1;
    private JScrollPane scrollPane_2;
    private JScrollPane scrollPane_3;
    private JScrollPane scrollPane_4;

    public ViewMemberDetails(Member member) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        mLeaderModel = new LeaderTableModel();
        mLeaderList = new JTable(mLeaderModel);
        mLeaderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(mLeaderList);

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        JLabel lblNewLabel = new JLabel("Nome");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));

        mName = new JLabel("-");
        mName.setFont(new Font("Arial", Font.BOLD, 14));

        lblDataNasc = new JLabel("Data Nasc.");
        lblDataNasc.setFont(new Font("Arial", Font.BOLD, 14));

        mBirthday = new JLabel("-");
        mBirthday.setFont(new Font("Arial", Font.BOLD, 14));

        lblSexo = new JLabel("Sexo");
        lblSexo.setFont(new Font("Arial", Font.BOLD, 14));

        lblSangue = new JLabel("Sangue");
        lblSangue.setFont(new Font("Arial", Font.BOLD, 14));

        lblEstadoCivil = new JLabel("Estado Civil");
        lblEstadoCivil.setFont(new Font("Arial", Font.BOLD, 14));

        lblRg = new JLabel("RG");
        lblRg.setFont(new Font("Arial", Font.BOLD, 14));

        lblCpf = new JLabel("CPF");
        lblCpf.setFont(new Font("Arial", Font.BOLD, 14));

        mGender = new JLabel("-");
        mGender.setFont(new Font("Arial", Font.BOLD, 14));

        mBlood = new JLabel("-");
        mBlood.setFont(new Font("Arial", Font.BOLD, 14));

        mMaritial = new JLabel("DESQUITADO");
        mMaritial.setFont(new Font("Arial", Font.BOLD, 14));

        mRG = new JLabel("000.000.000-0");
        mRG.setFont(new Font("Arial", Font.BOLD, 14));

        mCPF = new JLabel("000.000.000-00");
        mCPF.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel lblLider = new JLabel("L\u00EDder");
        lblLider.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel lblGerao = new JLabel("Gera\u00E7\u00E3o");
        lblGerao.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblCelular = new JLabel("Celular");
        lblCelular.setFont(new Font("Arial", Font.BOLD, 14));
        
        mLeader = new JLabel("-");
        mLeader.setFont(new Font("Arial", Font.BOLD, 14));
        
        mGeneration = new JLabel("-");
        mGeneration.setFont(new Font("Arial", Font.BOLD, 14));
        
        mMobile = new JLabel("(19) 99999-9999");
        mMobile.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblTelResidencial = new JLabel("Tel Residencial");
        lblTelResidencial.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblTelComercial = new JLabel("Tel Comercial");
        lblTelComercial.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        
        mPhoneHome = new JLabel("(19) 99999-9999");
        mPhoneHome.setFont(new Font("Arial", Font.BOLD, 14));
        
        mPhoneWork = new JLabel("(19) 99999-9999");
        mPhoneWork.setFont(new Font("Arial", Font.BOLD, 14));
        
        mEmail = new JLabel("-");
        mEmail.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblEndereo = new JLabel("Endere\u00E7o");
        lblEndereo.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblComplemento = new JLabel("Complemento");
        lblComplemento.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblBairro = new JLabel("Bairro");
        lblBairro.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblCep = new JLabel("CEP");
        lblCep.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblCidade = new JLabel("Cidade");
        lblCidade.setFont(new Font("Arial", Font.BOLD, 14));
        
        mAddress = new JLabel("-");
        mAddress.setFont(new Font("Arial", Font.BOLD, 14));
        
        mAddressExtra = new JLabel("-");
        mAddressExtra.setFont(new Font("Arial", Font.BOLD, 14));
        
        mDistrict = new JLabel("-");
        mDistrict.setFont(new Font("Arial", Font.BOLD, 14));
        
        mCity = new JLabel("-");
        mCity.setFont(new Font("Arial", Font.BOLD, 14));
        
        mCEP = new JLabel("-");
        mCEP.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblEntrada = new JLabel("Cadastro");
        lblEntrada.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblDataMembro = new JLabel("Membro");
        lblDataMembro.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblData = new JLabel("Entrada");
        lblData.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblComo = new JLabel("Como");
        lblComo.setFont(new Font("Arial", Font.BOLD, 14));
        
        mStart = new JLabel("-");
        mStart.setFont(new Font("Arial", Font.BOLD, 14));
        
        mIsMember = new JLabel("-");
        mIsMember.setFont(new Font("Arial", Font.BOLD, 14));
        
        mMemberDate = new JLabel("-");
        mMemberDate.setFont(new Font("Arial", Font.BOLD, 14));
        
        mMemberStartType = new JLabel("-");
        mMemberStartType.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblDesativao = new JLabel("Gradua\u00E7\u00E3o");
        lblDesativao.setFont(new Font("Arial", Font.BOLD, 14));
        
        mGraduation = new JLabel("-");
        mGraduation.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblDesativao_1 = new JLabel("Desativa\u00E7\u00E3o");
        lblDesativao_1.setFont(new Font("Arial", Font.BOLD, 14));
        
        mEnd = new JLabel("-");
        mEnd.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel lblHistricoLder = new JLabel("Hist\u00F3rico L\u00EDder");
        lblHistricoLder.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel lblHistricoCurso = new JLabel("Hist\u00F3rico Curso");
        lblHistricoCurso.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblHistricoClula = new JLabel("Hist\u00F3rico C\u00E9lula");
        lblHistricoClula.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblHistricoEncontro = new JLabel("Hist\u00F3rico Encontro");
        lblHistricoEncontro.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblHistricoMinistrio = new JLabel("Hist\u00F3rico Minist\u00E9rio");
        lblHistricoMinistrio.setFont(new Font("Arial", Font.BOLD, 14));
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        scrollPane_2 = new JScrollPane();
        scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        scrollPane_3 = new JScrollPane();
        scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        scrollPane_4 = new JScrollPane();
        scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(10)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                                        .addComponent(mName, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
                                    .addGap(5)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(mBirthday, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblDataNasc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(5)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(mGender, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSexo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(5)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblSangue)
                                        .addComponent(mBlood, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
                                    .addGap(5)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(mMaritial, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblEstadoCivil, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                    .addGap(5)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(lblRg, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(lblCpf, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                            .addGap(10))
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(mRG, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(mCPF, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                            .addGap(10))))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(mLeader, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblLider, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                    .addGap(5)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(mGeneration, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblGerao, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                                    .addGap(5)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(lblCelular, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(lblTelResidencial, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(lblTelComercial, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(mMobile, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(mPhoneHome, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(mPhoneWork, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(mEmail, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)))
                                    .addGap(10))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(mAddress, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblEndereo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
                                    .addGap(5)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(mAddressExtra, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(mDistrict, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(mCity, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(mCEP, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(lblComplemento, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(lblBairro, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(lblCep, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                            .addGap(10))))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(mStart, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(mIsMember, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(lblEntrada, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(lblDataMembro, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(5)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(lblData, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(lblComo, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(mMemberDate, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                            .addComponent(mMemberStartType, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGap(5)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(mGraduation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblDesativao, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                    .addGap(5)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(mEnd, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                                        .addComponent(lblDesativao_1, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblHistricoLder, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(lblHistricoCurso, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblHistricoClula, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(lblHistricoEncontro, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblHistricoMinistrio, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)))
                    .addContainerGap())
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(570, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel)
                        .addComponent(lblEstadoCivil, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSangue, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSexo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataNasc, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRg, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mBirthday, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mGender, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mBlood, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mMaritial, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mRG, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mCPF, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblLider, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGerao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCelular, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTelResidencial, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTelComercial, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mLeader, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mGeneration, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mMobile, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mPhoneHome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mPhoneWork, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblEndereo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblComplemento, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCep, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBairro, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mAddress, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mAddressExtra, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mDistrict, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mCity, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mCEP, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblEntrada, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataMembro, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblData, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblComo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDesativao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDesativao_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mStart, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mIsMember, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mMemberDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mMemberStartType, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mGraduation, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mEnd, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblHistricoCurso, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblHistricoLder, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblHistricoClula, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblHistricoEncontro, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                            .addGap(10)
                            .addComponent(lblHistricoMinistrio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                    .addGap(10)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(10))
        );
        setLayout(groupLayout);

        new MemberGetDetails(member.mId, this).start();
        disableFields();
    }

    @Override
    public void onCommandFinished(RequestResult result) {
        switch (result.getCode()) {
        case WITHOUT_CONNECTION:
            JOptionPane.showMessageDialog(this, "Sem conexão com a internet.\nNão foi possível completar a ação.");
            enableFields();
            break;
        case SERVER_ERROR:
            JOptionPane.showMessageDialog(this, "Erro no servidor. Código: " + result.getData("ERROR_CODE")
                    + "\nTente mais tarde.");
            enableFields();
            break;
        case UNKNOWN:
            JOptionPane.showMessageDialog(this, "Erro desconhecido.\nFeche o aplicativo e tente novamente.");
            enableFields();
            break;
        case OK:
            switch (result.getCommand()) {
            case MEMBER_GET_DETAILS:
                Member member = (Member) result.getData("MEMBER");
                mLeaderModel.setList((List<MemberLeader>) result.getData("LEADER_LIST"));
                mLeaderList.invalidate();

                mName.setText(member.mName);
                mBirthday.setText(sDateFormatter.format(member.mBirthday));
                if (member.mGender == 1) {
                    mGender.setText("M");
                } else if (member.mGender == 2) {
                    mGender.setText("F");
                }
                switch (member.mBloodType) {
                case 0:
                    mBlood.setText("-");
                    break;
                case 1:
                    mBlood.setText("O+");
                    break;
                case 2:
                    mBlood.setText("O-");
                    break;
                case 3:
                    mBlood.setText("A+");
                    break;
                case 4:
                    mBlood.setText("A-");
                    break;
                case 5:
                    mBlood.setText("B+");
                    break;
                case 6:
                    mBlood.setText("B-");
                    break;
                case 7:
                    mBlood.setText("AB+");
                    break;
                case 8:
                    mBlood.setText("AB-");
                    break;
                }

                switch (member.mMaritialStatus) {
                case 0:
                    mMaritial.setText("-");
                    break;
                case 1:
                    mMaritial.setText("SOLTEIRO");
                    break;
                case 2:
                    mMaritial.setText("CASADO");
                    break;
                case 3:
                    mMaritial.setText("VIUVO");
                    break;
                case 4:
                    mMaritial.setText("DESQUITADO");
                    break;
                }
                mRG.setText(member.mRG);
                mCPF.setText(member.mCPF);
                mLeader.setText(member.mLeaderName);
                mGeneration.setText(member.mGenerationName);
                mMobile.setText(member.mMobile);
                mPhoneHome.setText(member.mPhoneHome);
                mPhoneWork.setText(member.mPhoneWork);
                mEmail.setText(member.mEmail);
                mAddress.setText(member.mAddress);
                mAddressExtra.setText(member.mAddressMore);
                mDistrict.setText(member.mDistrict);
                mCity.setText(member.mCity);
                mCEP.setText(member.mZIP);
                mStart.setText(sDateFormatter.format(member.mCreateDate));
                if (member.mStartDate.getTime() == 0) {
                    mIsMember.setText("NÃO");
                    mMemberDate.setText("-");
                    mMemberStartType.setText("-");
                } else {
                    mIsMember.setText("SIM");
                    mMemberDate.setText(sDateFormatter.format(member.mStartDate));
                    switch (member.mStartType) {
                    case 1:
                        mMemberStartType.setText("BATISMO");
                        break;
                    case 2:
                        mMemberStartType.setText("ACLAMAÇÃO");
                        break;
                    }
                }

                switch(member.mLevel) {
                case 0:
                    mGraduation.setText("-");
                    break;
                case 1:
                    mGraduation.setText("DISCÍPULO");
                    break;
                case 2:
                    mGraduation.setText("PASTOR");
                    break;
                case 3:
                    mGraduation.setText("BISPO");
                    break;
                case 4:
                    mGraduation.setText("APÓSTOLO");
                    break;
                case 5:
                    mGraduation.setText("12 DE 1ª GERAÇÃO - LEVANTADO");
                    break;
                case 6:
                    mGraduation.setText("12 DE 1ª GERAÇÃO - LEGITIMADO");
                    break;
                case 7:
                    mGraduation.setText("12 DE 1ª GERAÇÃO - CONSAGRADO");
                    break;
                case 8:
                    mGraduation.setText("12 DE 2ª GERAÇÃO - LEVANTADO");
                    break;
                case 9:
                    mGraduation.setText("12 DE 2ª GERAÇÃO - LEGITIMADO");
                    break;
                case 10:
                    mGraduation.setText("12 DE 2ª GERAÇÃO - CONSAGRADO");
                    break;
                case 11:
                    mGraduation.setText("12 DE 3ª GERAÇÃO - LEVANTADO");
                    break;
                case 12:
                    mGraduation.setText("12 DE 3ª GERAÇÃO - LEGITIMADO");
                    break;
                case 13:
                    mGraduation.setText("12 DE 3ª GERAÇÃO - CONSAGRADO");
                    break;
                case 14:
                    mGraduation.setText("12 DE 4ª GERAÇÃO - LEVANTADO");
                    break;
                case 15:
                    mGraduation.setText("12 DE 4ª GERAÇÃO - LEGITIMADO");
                    break;
                case 16:
                    mGraduation.setText("12 DE 4ª GERAÇÃO - CONSAGRADO");
                    break;
                }
                if (member.mEndDate.getTime() == 0) {
                    mEnd.setText("-");
                } else {
                    mEnd.setText(sDateFormatter.format(member.mEndDate));
                }

                enableFields();
                break;

            default:
                break;
            }
        }
    }

    private void enableFields() {
        mLeaderList.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mLeaderList.setEnabled(false);
        mProgressBar.setVisible(true);
    }

    private static class CourseTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 2139100891339821696L;
        private static final String[] COLUMN_NAMES = { "DATA", "CURSO", "EDIÇÃO", "SITUAÇÃO" };
        private List<Course> mLeaderList = new ArrayList<Course>();
        private List<CourseSubscribe> mSubscribeList = new ArrayList<CourseSubscribe>();

        public void setList(List<Course> courseList, List<CourseSubscribe> subscribeList) {
            mLeaderList.clear();
            mLeaderList.addAll(courseList);
            mSubscribeList.clear();
            mSubscribeList.addAll(subscribeList);
        }

        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override
        public int getRowCount() {
            return mLeaderList.size();
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            if (row > mSubscribeList.size()) {
                return null;
            }
            Course course = mLeaderList.get(row);
            CourseSubscribe subscribe = mSubscribeList.get(row);
            switch (column) {
            case 0:
                return sDateFormatter.format(course.mStartDate);
            case 1:
                return course.mCourseTypeName;
            case 2:
                return course.mVersion;
            case 3:
                if (subscribe.mCompleted) {
                    return "FORMADO";
                } else if (subscribe.mEndDate.getTime() != 0) {
                    return "DESISTENTE";
                } else if (Calendar.getInstance().getTime().getTime() <= course.mEndDate.getTime()) {
                    return "CURSANDO";
                }
                return "INCOMPLETO";
            }
            return null;
        }
    }

    private static class LeaderTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 2139100891339821696L;
        private static final String[] COLUMN_NAMES = { "INÍCIO", "FINAL", "LÍDER" };
        private List<MemberLeader> mList = new ArrayList<MemberLeader>();

        public void setList(List<MemberLeader> list) {
            mList.clear();
            mList.addAll(list);
        }

        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override
        public int getRowCount() {
            return mList.size();
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            if (row > mList.size()) {
                return null;
            }
            MemberLeader item = mList.get(row);
            switch (column) {
            case 0:
                return sDateFormatter.format(item.mStartDate);
            case 1:
                if (item.mEndDate.getTime() == 0) {
                    return null;
                }
                return sDateFormatter.format(item.mStartDate);
            case 2:
                return item.mLeaderName;
            }
            return null;
        }
    }
}
