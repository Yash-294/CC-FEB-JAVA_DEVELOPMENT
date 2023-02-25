package com.codeclausetask2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;

public class LandingController {

    private Books book;
    private Connection con;

    @FXML
    private Label alert;
    @FXML
    private TextField bookname;
    @FXML
    private TextField bookid;
    @FXML
    private TableView<Books> table;
    @FXML
    private TableColumn<Books,String> Name;
    @FXML
    private TableColumn<Books,String> Number;

    public void initialize() throws SQLException{
        book=new Books();
        con=DBConnection.getConnection();
        ObservableList<Books> list=FXCollections.observableArrayList();
        list=book.getAvailableBooks(con);
        table.setItems(list);
        Name.setCellValueFactory(new PropertyValueFactory<Books,String>("name"));
        Number.setCellValueFactory(new PropertyValueFactory<Books,String>("id"));
    }

    @FXML
    private void returnBook() throws IOException {
        App.setRoot("return");
    }
    
    @FXML
    private void issueBook() throws IOException {
        App.setRoot("issue");
    }

    @FXML
    public void addBook() throws SQLException{
        con=DBConnection.getConnection();
        String query="SELECT ID FROM BOOKS WHERE ID='"+bookid.getText()+"';";
        ResultSet rs=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(query);
        if(rs.next()!=false){
            alert.setTextFill(Paint.valueOf("red"));
            alert.setText("Book already exists");
        }
        else{
            query="INSERT INTO BOOKS(NAME,ID,STATUS) VALUES('"+bookname.getText()+"','"+bookid.getText()+"','AVAILABLE');";
            con.prepareStatement(query).executeUpdate();
            alert.setTextFill(Paint.valueOf("green"));
            alert.setText("Book successfully added to the database");
        }

    }
}
