/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxdatabaseprogramming;

import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author Diles
 */
public class JavaFXDatabaseProgramming extends Application {
    Connection conn = null;
    Statement statement = null;
    
    List<Buku> allBuku;
    
    private static final Logger logger = Logger.getLogger(JavaFXDatabaseProgramming.class.getName());
    private BukuDao bukuDao = new BukuDao();
                    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Form Buku");
       
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Data Buku");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        Label lblNoISBN = new Label("No. ISBN:");
        grid.add(lblNoISBN, 0, 1);
        
        TextField txtNoISBN = new TextField();
        grid.add(txtNoISBN, 1,1);
        txtNoISBN.setPrefColumnCount(20);
        
        Label lblJudul = new Label("Judul Buku:");
        grid.add(lblJudul, 0, 2);
        
        TextField txtJudul = new TextField();
        grid.add(txtJudul, 1, 2);
        
        Label lblPengarang = new Label("Pengarang:");
        grid.add(lblPengarang, 0, 3);
        
        TextField txtPengarang = new TextField();
        grid.add(txtPengarang, 1, 3);
        
        Label lblTahun = new Label("Tahun Terbit:");
        grid.add(lblTahun, 0, 4);
        
        TextField txtTahun = new TextField();
        grid.add(txtTahun, 1, 4);
        
        Label lblPenerbit = new Label("Penerbit:");
        grid.add(lblPenerbit, 0, 5);
        
        TextField txtPenerbit = new TextField();
        grid.add(txtPenerbit, 1, 5);
        
        Button btnView = new Button("View");
        Button btnSimpan = new Button("Simpan");
        Button btnUbah = new Button("Ubah");
        Button btnHapus = new Button("Hapus");
        Button btnBatal = new Button("Batal");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnView);
        hbBtn.getChildren().add(btnSimpan);
        hbBtn.getChildren().add(btnUbah);
        hbBtn.getChildren().add(btnHapus);
        hbBtn.getChildren().add(btnBatal);
        grid.add(hbBtn, 1, 7);
        
        TableView tbv = new TableView();
        TableColumn<String, Buku> cl1 = new TableColumn<>("ISBN");
        cl1.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        TableColumn<String, Buku> cl2 = new TableColumn<>("Judul");
        cl2.setCellValueFactory(new PropertyValueFactory<>("judul"));
        TableColumn<String, Buku> cl3 = new TableColumn<>("Pengarang");
        cl3.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        TableColumn<String, Buku> cl4 = new TableColumn<>("Tahun Terbit");
        cl4.setCellValueFactory(new PropertyValueFactory<>("tahunTerbit"));
        TableColumn<String, Buku> cl5 = new TableColumn<>("Penerbit");
        cl5.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        tbv.getColumns().add(cl1);
        tbv.getColumns().add(cl2);
        tbv.getColumns().add(cl3);
        tbv.getColumns().add(cl4);
        tbv.getColumns().add(cl5);
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(tbv);
        vbox.setSpacing(5);
        grid.add(vbox, 0, 9, 2, 1);
        
        btnSimpan.setOnAction(actionEvent -> {
            String isbn = txtNoISBN.getText().trim();
            String judul = txtJudul.getText().trim();
            String pengarang = txtPengarang.getText().trim();
            String tahunTerbit = txtTahun.getText();
            String penerbit = txtPenerbit.getText();
            
            try {
                bukuDao.saveBuku(isbn, judul, pengarang, tahunTerbit, penerbit);
                this.alert("Simpan", "Data barhasil disimpan!", AlertType.INFORMATION);
                viewData(tbv);
            } catch (SQLException exception) {
		logger.log(Level.SEVERE, exception.getMessage());
            }

	});
        
        btnUbah.setOnAction(actionEvent -> {
            String isbn = txtNoISBN.getText().trim();
            String judul = txtJudul.getText().trim();
            String pengarang = txtPengarang.getText().trim();
            String tahunTerbit = txtTahun.getText();
            String penerbit = txtPenerbit.getText();
            
            try {
                bukuDao.updateBuku(isbn, judul, pengarang, tahunTerbit, penerbit);
                this.alert("Ubah", "Data barhasil diubah!", AlertType.INFORMATION);
                viewData(tbv);
            } catch (SQLException exception) {
		logger.log(Level.SEVERE, exception.getMessage());
            }

	});
        
        btnHapus.setOnAction(actionEvent -> {
            String isbn = txtNoISBN.getText().trim();
            
            try {
                bukuDao.deleteBuku(isbn);
                this.alert("Hapus", "Data barhasil dihapus!", AlertType.INFORMATION);
                viewData(tbv);
            } catch (SQLException exception) {
		logger.log(Level.SEVERE, exception.getMessage());
            }

	});
        
        
         btnView.setOnAction(actionEvent -> {
             viewData(tbv);
	});

        
        Scene scene = new Scene(grid, 520, 520);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void alert(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
    }
    
    public void viewData(TableView tbView){
        tbView.getItems().clear();
        try {
                allBuku = bukuDao.viewBuku();
                for (Buku buku : allBuku){
                    tbView.getItems().add(buku);
                    //System.out.println(buku.getISBN());
                }
            } catch (SQLException exception) {
		logger.log(Level.SEVERE, exception.getMessage());
            } 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
