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
import java.util.Set;
import java.util.Vector;

import Connection.connect;
import XMLconvert.JAXBObjectToXml;
import XMLconvert.Track;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.sun.rowset.internal.Row;

public class Interface extends JFrame  {
    DefaultTableModel model = new DefaultTableModel();
    DefaultListModel listModelUser;
    String[] columnNames = {"Title",
            "Artist",
            "Album",
            "Year",
            "Genre",
            "Place"};
    Object[] row = new Object[6];

    JTable table;
    JScrollPane scroll, scrollList;
    JButton addMusic, editMusic, delMusic, playMusic, search, fRequest, Art, Albu, Title, Settings;
    JList searchUsers;
    JFileChooser chooser;
    JLabel ArtistJlabel, TitleJLabel, AlbumJlabel, YearJlabel, GenreJlabel;
    JTextField ArtistJtext,TitleJtext, AlbumJtext, GenreJtext, YearJtext, searchJText,Lyrics;
    JSlider progress;
    String song = "/home/david/Documents/Mp3/Animal I Have Become.mp3";
    Interface() throws UnsupportedTagException, InvalidDataException, IOException, NotSupportedException{
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFrame Interface = new JFrame("Interface Form");
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
        Art = new JButton("Art");
        Albu = new JButton("Albu");
        Title = new JButton("Title");
        Settings = new JButton("Settings");
        Lyrics = new JTextField();
        progress = new JSlider(JSlider.HORIZONTAL);
        chooser = new JFileChooser();
        searchUsers = new JList(listModelUser);
        searchUsers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        searchUsers.setLayoutOrientation(JList.VERTICAL);
        searchUsers.setVisibleRowCount(-1);
        table = new JTable();
        model.setColumnIdentifiers(columnNames);
        table.setModel(model);
        scroll = new JScrollPane(table);
        scrollList = new JScrollPane(searchUsers);
        table.setFillsViewportHeight(true);


        scroll.setBounds(30,40,300,100);
        Art.setBounds(30,5,100,30);
        Albu.setBounds(140,5,100,30);
        Title.setBounds(250,5,100,30);
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
        fRequest.setBounds(750,170,150,30);
        scrollList.setBounds(600,30,300,100);
        Settings.setBounds(900,1,100,30);
        Lyrics.setBounds(360,50,200,300);
        progress.setBounds(330,350,250,100);





        Interface.add(scroll);
        Interface.add(Art);
        Interface.add(Albu);
        Interface.add(Title);
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
        Interface.add(Settings);
        Interface.add(progress);
        Interface.add(Lyrics);
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
                    row[0] = id3v1Tag.getArtist();
                    row[1] = id3v1Tag.getTitle();
                    row[2] = id3v1Tag.getAlbum();
                    row[3] = id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")";
                    row[4] = id3v1Tag.getYear();
                    row[5] = song;
                    model.addRow(row);

                    ArtistJtext.setText("Artist");
                    TitleJtext.setText("Title");
                    AlbumJtext.setText("Album");
                    YearJtext.setText("Year");
                    GenreJtext.setText("0");




                }

                Track track = new Track();
                track.setTitle(TitleJtext.getText());
                track.setArtist(ArtistJtext.getText());
                track.setAlbum(AlbumJtext.getText());
                track.setYear(YearJtext.getText());
                track.setGenre(GenreJtext.getText());

                JAXBObjectToXml xml = new JAXBObjectToXml();
                String send="&"+xml.ConvertToXML(track,Track.class)+"<!--new_can-->";
                System.out.println(send);
                /*connect c = new connect();
                c.connect(send);*/





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
                System.out.println(i);
                if (i >= 0){
                    song = model.getValueAt(i,5).toString();
                    /*Mp3File mp3file = null;
                    try {
                        mp3file = new Mp3File(song);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (UnsupportedTagException e) {
                        e.printStackTrace();
                    } catch (InvalidDataException e) {
                        e.printStackTrace();
                    }*/
                    model.setValueAt(ArtistJtext.getText(), i, 0);
                    model.setValueAt(TitleJtext.getText(), i, 1);
                    model.setValueAt(AlbumJtext.getText(), i, 2);
                    model.setValueAt(GenreJtext.getText(), i, 3);
                    model.setValueAt(YearJtext.getText(), i, 4);
                    /*ID3v1 id3v1Tag;
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
                    }*/
                    Track track = new Track();
                    track.setTitle(TitleJtext.getText());
                    track.setArtist(ArtistJtext.getText());
                    track.setAlbum(AlbumJtext.getText());
                    track.setYear(YearJtext.getText());
                    track.setGenre(GenreJtext.getText());

                    JAXBObjectToXml xml = new JAXBObjectToXml();
                    String send="&"+xml.ConvertToXML(track,Track.class)+"<!--act_can-->";
                    System.out.println(send);
                    /*connect c = new connect();
                    c.connect(send);*/



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
                    Track track = new Track();
                    track.setTitle(TitleJtext.getText());
                    track.setArtist(ArtistJtext.getText());
                    track.setAlbum(AlbumJtext.getText());
                    track.setYear(YearJtext.getText());
                    track.setGenre(GenreJtext.getText());

                    JAXBObjectToXml xml = new JAXBObjectToXml();
                    String send="&"+xml.ConvertToXML(track,Track.class)+"<!--del_can-->";
                    System.out.println(send);
                    /*connect c = new connect();
                    c.connect(send);*/
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
            }
        });
        Settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SettingsForm settings = new SettingsForm();
            }
        });
    }

}
