package Login;

import XMLconvert.User;
import XMLconvert.JAXBObjectToXml;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringWriter;

import Connection.connect;


public class SettingsForm extends JFrame {

    JLabel uNameJlabel, nameLabel, lNameJlabel, passJlabel, ageJlabel, genreJlabel, friendsJlabel;
    JTextField uNameJtext,nametext, lNameJtext, genreJtext, friendsJtext;
    JButton register;
    JPasswordField passJpf;
    JSpinner ageSp;


    SettingsForm(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFrame reg = new JFrame("Register Form");
        register = new JButton("Register user");
        nameLabel = new JLabel("Name");
        lNameJlabel = new JLabel("Last Name");
        passJlabel = new JLabel("Password");
        ageJlabel = new JLabel("Age");
        genreJlabel = new JLabel("Genre");
        friendsJlabel = new JLabel("Friends");
        genreJtext = new JTextField();
        friendsJtext = new JTextField();
        nametext = new JTextField();
        lNameJtext = new JTextField();
        passJpf = new JPasswordField();
        ageSp = new JSpinner();
        try {
            ageSp.commitEdit();
        }catch (java.text.ParseException e){  }


        nameLabel.setBounds(40,70,400,30);
        nametext.setBounds(130,70,150,30);
        lNameJlabel.setBounds(40,110,400,30);
        lNameJtext.setBounds(130,110,150,30);
        passJlabel.setBounds(40,150,400,30);
        passJpf.setBounds(130,150,150,30);
        ageJlabel.setBounds(40,190,400,30);
        ageSp.setBounds(130,190,100,30);
        genreJlabel.setBounds(40,230,400,30);
        genreJtext.setBounds(130,230,150,30);
        friendsJlabel.setBounds(40,270,400,30);
        friendsJtext.setBounds(130,270,150,30);
        register.setBounds(40,400,150,30);






        reg.add(nameLabel);
        reg.add(nametext);
        reg.add(lNameJlabel);
        reg.add(lNameJtext);
        reg.add(passJlabel);
        reg.add(passJpf);
        reg.add(ageJlabel);
        reg.add(ageSp);
        reg.add(genreJlabel);
        reg.add(genreJtext);
        reg.add(friendsJlabel);
        reg.add(friendsJtext);
        reg.add(register);
        reg.setSize(400,500);
        reg.setLayout(null);
        reg.setVisible(true);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String password = passJpf.getText();
                String name = nametext.getText();
                String lastname = lNameJtext.getText();
                int ageValue = (Integer) ageSp.getValue();
                String age = Integer.toString(ageValue);
                User user = new User();
                user.setPass(password);
                user.setName(name);
                user.setLastname(lastname);
                user.setAge(age);
                user.setLike(genreJtext.getText());
                user.setFriends(friendsJtext.getText());
                JAXBObjectToXml xml = new JAXBObjectToXml();
                connect c = new connect();
                String send="&"+xml.ConvertToXML(user,User.class)+"<!--new_usu-->";
                c.connect(send);

            }
        });


    }

}
