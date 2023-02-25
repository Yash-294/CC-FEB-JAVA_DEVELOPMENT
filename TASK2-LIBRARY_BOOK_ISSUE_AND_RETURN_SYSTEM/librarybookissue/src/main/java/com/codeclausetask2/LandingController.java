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
    private TextField studname;
    @FXML
    private TextField password;
    @FXML
    private TextField userid;
    @FXML
    private TextField rollno;
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

    @FXML
    public void addStudent() throws SQLException{
        con=DBConnection.getConnection();
        String query="SELECT USERID FROM STUDENTS WHERE USERID='"+userid.getText()+"';";
        ResultSet rs=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(query);
        if(rs.next()!=false){
            alert.setTextFill(Paint.valueOf("red"));
            alert.setText("UserId already exists!! Enter a different One");
        }
        else{
            query="SELECT ROLLNO FROM STUDENTS WHERE ROLLNO='"+rollno.getText()+"';";
            rs=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(query);
            if(rs.next()!=false){
                alert.setTextFill(Paint.valueOf("red"));
                alert.setText("Roll Number already exists!! Enter a different One");
            }
            else{
                query="INSERT INTO STUDENTS(NAME,ROLLNO,USERID,PASSWORD,ROLE) VALUES('"+studname.getText()+"','"+rollno.getText()+"','"+userid.getText()+"','"+password.getText()+"','STUDENT');";
                con.prepareStatement(query).executeUpdate();
                alert.setTextFill(Paint.valueOf("green"));
                alert.setText("Student successfully added to the database");
            }
        }
    }

    @FXML
    public void logOut() throws IOException{
        App.setRoot("login");
    }
}
