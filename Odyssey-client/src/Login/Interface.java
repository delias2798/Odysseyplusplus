package Login;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Vector;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Interface extends JFrame  {
    DefaultTableModel model = new DefaultTableModel();
    DefaultListModel listModelUser, listModelFriends;
    String[] columnNames = {"Title",
            "Artist",
            "Album",
            "Year",
            "Genre",
            "Place"};
    Object[] row = new Object[6];

    JTable table;
    JScrollPane scroll, scrollList, flistScroll;
    JButton addMusic, editMusic, delMusic, playMusic, search, fRequest;
    JList searchUsers, flist;
    JFileChooser chooser;
    JLabel ArtistJlabel, TitleJLabel, AlbumJlabel, YearJlabel, GenreJlabel;
    JTextField ArtistJtext,TitleJtext, AlbumJtext, GenreJtext, YearJtext, searchJText;
    String song = "/home/david/Documents/Mp3/Animal I Have Become.mp3";
    Interface() throws UnsupportedTagException, InvalidDataException, IOException, NotSupportedException{
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFrame Interface = new JFrame("Interface Form");
        listModelFriends = new DefaultListModel();
        listModelUser = new DefaultListModel();
        ArtistJlabel = new JLabel("Artist");
        ArtistJtext = new JTextField();
        TitleJLabel =new JLabel("Title");
        TitleJtext = new JTextField();
        AlbumJlabel = new JLabel("Album");
        AlbumJtext = new JTextField();
        YearJlabel = new JLabel("Year");
        YearJtext = new JTextField();
        GenreJlabel = new JLabel("Genre");
        GenreJtext = new JTextField();
        addMusic = new JButton("Add");
        editMusic = new JButton("Edit");
        delMusic = new JButton("Delete");
        playMusic = new JButton("Play");
        searchJText = new JTextField();
        search = new JButton("Search User");
        fRequest = new JButton("Friend Request");
        chooser = new JFileChooser();
        searchUsers = new JList(listModelUser);
        searchUsers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        searchUsers.setLayoutOrientation(JList.VERTICAL);
        searchUsers.setVisibleRowCount(-1);
        flist = new JList(listModelFriends);
        table = new JTable();
        model.setColumnIdentifiers(columnNames);
        table.setModel(model);
        scroll = new JScrollPane(table);
        scrollList = new JScrollPane(searchUsers);
        flistScroll = new JScrollPane(flist);
        table.setFillsViewportHeight(true);


        scroll.setBounds(30,30,300,100);
        addMusic.setBounds(30,150,100,30);
        delMusic.setBounds(200,150,100,30);
        editMusic.setBounds(30,200,100,30);
        playMusic.setBounds(200,200,100,30);
        ArtistJlabel.setBounds(30,250,100,30);
        ArtistJtext.setBounds(80,250,160,30);
        TitleJLabel.setBounds(30,300,100,30);
        TitleJtext.setBounds(80,300,160,30);
        AlbumJlabel.setBounds(30,350,100,30);
        AlbumJtext.setBounds(80,350,160,30);
        YearJlabel.setBounds(30,400,100,30);
        YearJtext.setBounds(80,400,160,30);
        GenreJlabel.setBounds(30,450,100,30);
        GenreJtext.setBounds(80,450,160,30);
        searchJText.setBounds(600,130,230,30);
        search.setBounds(600,170,150,30);
        fRequest.setBounds(830,170,150,30);
        scrollList.setBounds(600,30,300,100);
        flistScroll.setBounds(600,250,300,100);





        Interface.add(scroll);
        Interface.add(addMusic);
        Interface.add(delMusic);
        Interface.add(editMusic);
        Interface.add(playMusic);
        Interface.add(ArtistJlabel);
        Interface.add(ArtistJtext);
        Interface.add(TitleJLabel);
        Interface.add(TitleJtext);
        Interface.add(AlbumJlabel);
        Interface.add(AlbumJtext);
        Interface.add(YearJlabel);
        Interface.add(YearJtext);
        Interface.add(GenreJlabel);
        Interface.add(GenreJtext);
        Interface.add(scrollList);
        Interface.add(searchJText);
        Interface.add(search);
        Interface.add(fRequest);
        Interface.add(flistScroll);
        Interface.setSize(1000,500);
        Interface.setLayout(null);
        Interface.setVisible(true);
        addMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 FILES", "mp3", "mp3");
                chooser.setFileFilter(filter);
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION){
                    song = chooser.getSelectedFile().getAbsolutePath();

                }
                Mp3File mp3file = null;
                try {
                    mp3file = new Mp3File(song);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                }
                if (mp3file.hasId3v1Tag()) {
                    ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                    row[0] = id3v1Tag.getArtist();
                    row[1] = id3v1Tag.getTitle();
                    row[2] = id3v1Tag.getAlbum();
                    row[3] = id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")";
                    row[4] = id3v1Tag.getYear();
                    row[5] = song;
                    model.addRow(row);
                    listModelUser.addElement(id3v1Tag.getArtist());
                }else {
                    ID3v1 id3v1Tag;
                    if (mp3file.hasId3v1Tag()) {
                        id3v1Tag =  mp3file.getId3v1Tag();
                    } else {
                        id3v1Tag = new ID3v1Tag();
                        mp3file.setId3v1Tag(id3v1Tag);
                    }
                    id3v1Tag.setArtist("Artist");
                    id3v1Tag.setTitle("Title");
                    id3v1Tag.setAlbum("Album");
                    id3v1Tag.setYear("Year");
                    id3v1Tag.setGenre(0);


                }

                String message = "Who Framed Roger Rabbit";
                for (int i = 0; i < message.length(); i += 10) {
                    System.out.println(message.substring(i, Math.min(i + 10, message.length())));
                }




            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int i = table.getSelectedRow();
                ArtistJtext.setText(model.getValueAt(i,0).toString());
                TitleJtext.setText(model.getValueAt(i,1).toString());
                AlbumJtext.setText(model.getValueAt(i,2).toString());
                GenreJtext.setText(model.getValueAt(i,3).toString());
                YearJtext.setText(model.getValueAt(i,4).toString());
            }
        });
        editMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int i = table.getSelectedRow();
                if (i >= 0){
                    song = model.getValueAt(i,5).toString();
                    Mp3File mp3file = null;
                    try {
                        mp3file = new Mp3File(song);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (UnsupportedTagException e) {
                        e.printStackTrace();
                    } catch (InvalidDataException e) {
                        e.printStackTrace();
                    }
                    model.setValueAt(ArtistJtext.getText(), i, 0);
                    model.setValueAt(TitleJtext.getText(), i, 1);
                    model.setValueAt(AlbumJtext.getText(), i, 2);
                    model.setValueAt(GenreJtext.getText(), i, 3);
                    model.setValueAt(YearJtext.getText(), i, 4);
                    ID3v1 id3v1Tag;
                    if (mp3file.hasId3v1Tag()) {
                        id3v1Tag =  mp3file.getId3v1Tag();
                    } else {
                        id3v1Tag = new ID3v1Tag();
                        mp3file.setId3v1Tag(id3v1Tag);
                    }
                    String myString = GenreJtext.getText();
                    int gen = Integer.parseInt(myString);
                    id3v1Tag.setArtist(ArtistJtext.getText());
                    id3v1Tag.setTitle(TitleJtext.getText());
                    id3v1Tag.setAlbum(AlbumJtext.getText());
                    id3v1Tag.setYear(YearJtext.getText());
                    id3v1Tag.setGenre(gen);
                    try {
                        mp3file.save(TitleJtext.getText() + ".mp3");

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NotSupportedException e) {
                        e.printStackTrace();
                    }


                }else {


                    JOptionPane.showMessageDialog(null, "There is no option", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
                }


            }
        });
        delMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int i = table.getSelectedRow();
                if (i >= 0){
                    model.removeRow(i);
                }else {
                    JOptionPane.showMessageDialog(null, "There is no option", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        fRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = searchUsers.getSelectedIndex();
                String user = (String) listModelUser.getElementAt(index);
                listModelFriends.addElement(user);
            }
        });
    }

}
