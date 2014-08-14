/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraintsBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;  
import javax.imageio.ImageIO;
/**
 *
 * @author samia
 */
public class JavaFXApplication3 extends Application {
    
    GridPane image_pane = new GridPane();
    GridPane Mainpane = new GridPane();
    GridPane Subpane = new GridPane();
    GridPane grid_pane1 = new GridPane();
    GridPane grid_pane2 = new GridPane();
    
    HBox hBox_outter;
    HBox hBox_outter_last;
    boolean split = true;
    

    int total_donnation = 1200;
    int old_total_donnation = 0 ;
    
    private AnimationTimer translate_timer;
    private long translate_lastTimerCall;
    
    String image;
    
    File file1;
//    Image image1;
    ImageView imageview_tile1;
    File blink_file ;
    Image blink_image1;
    ImageView blink_image;
    String style_outter;
    FadeTransition ft;
    FadeTransition ft1;
    File file2;
//    Image image2;
    Image  pane1_tiled_image;
    Image  pane2_tiled_image;
    
    ImageView imageview_tile2; 
    int total_donnation_left;
    
    int max_columns_pane1 = 45;
    int max_rows_pane1 = 26;
    int current_row_pane1 = 0;
    int current_column_pane1 = 0;
    int max_columns_pane2 = 43;
    int max_rows_pane2 = 42;
    int current_row_pane2 = 0;
    int current_column_pane2 = 0;
    //////////////////////////////////
    double image_Width = 7.95;   
    double image_Height = 7.95;
    ///////////////////////////////////    

    int max_donnation_pane1 = 1169;
    int max_donnation_pane2 = 1805;

    int i,j;
    
    Connection c;
    String SQL;
    ResultSet rs;
        
    @Override 
        public void init() throws IOException {
            if(split)
            {
                File file = new File("new_mussalah_1.png"); // I have bear.jpg in my working directory  
                FileInputStream fis = new FileInputStream(file);  
                BufferedImage image = ImageIO.read(fis); //reading the image file  

                int rows = 26; //You should decide the values for rows and cols variables  
                int cols = 45;  
                int chunks = rows * cols;  

                int chunkWidth = image.getWidth() / cols; // determines the chunk width and height  
                int chunkHeight = image.getHeight() / rows;  
                int count = 0;  
                BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks  
                for (int x = 0; x < rows; x++) {  
                    for (int y = 0; y < cols; y++) {  
                        //Initialize the image array with image chunks  
                        imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());  

                        // draws the image chunk  
                        Graphics2D gr = imgs[count++].createGraphics();  
                        gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                        gr.dispose();  
                    }  
                }  
                System.out.println("Splitting done");  

                //writing mini images into image files  
                for (int i = 0; i < imgs.length; i++) {  
                    ImageIO.write(imgs[i], "png", new File("pane1_img" + i + ".png"));  
                }  
                System.out.println("Mini images created");  
                File file2 = new File("new_mussalah_2.png"); // I have bear.jpg in my working directory  
                FileInputStream fis2 = new FileInputStream(file2);  
                BufferedImage image2 = ImageIO.read(fis2); //reading the image file  

                int rows2 = 42; //You should decide the values for rows and col variables  
                int cols2 = 43;  
                int chunks2 = rows2 * cols2;  

                int chunkWidth2 = image2.getWidth() / cols2; // determines the chunk width and height  
                int chunkHeight2 = image2.getHeight() / rows2;  
                int count2 = 0;  
                BufferedImage imgs2[] = new BufferedImage[chunks2]; //Image array to hold image chunks  
                for (int x = 0; x < rows2; x++) {  
                    for (int y = 0; y < cols2; y++) {  
                        //Initialize the image array with image chunks  
                        imgs2[count2] = new BufferedImage(chunkWidth2, chunkHeight2, image2.getType());  

                        // draws the image chunk  
                        Graphics2D gr2 = imgs2[count2++].createGraphics();  
                        gr2.drawImage(image2, 0, 0, chunkWidth2, chunkHeight2, chunkWidth2 * y, chunkHeight2 * x, chunkWidth2 * y + chunkWidth2, chunkHeight2 * x + chunkHeight2, null);  
                        gr2.dispose();  
                    }  
                }  
                System.out.println("Splitting done");  

                //writing mini images into image files  
                for (int i = 0; i < imgs2.length; i++) 
                {  
                    ImageIO.write(imgs2[i], "png", new File("pane2_img" + i + ".png")); 
                }  
                System.out.println("Mini images created"); 
            }
           
            translate_timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > translate_lastTimerCall + 30000_000_000l) 
                {
                    old_total_donnation = total_donnation;
                    try 
                    {
//                        c = DBConnect.connect();
//                        SQL = "Select * from donations";
//                        rs = c.createStatement().executeQuery(SQL);
//                        while (rs.next()) 
//                        {total_donnation = rs.getInt("total_donnation");}
//                        c.close();
                        System.out.format("Total Donation: %s \n", total_donnation);

                        total_donnation ++;
                        if(total_donnation != old_total_donnation)
                        {
                            grid_pane1.getChildren().clear();
                            grid_pane1.getChildren().removeAll(imageview_tile1);
                            grid_pane2.getChildren().clear();
                            grid_pane2.getChildren().removeAll(imageview_tile2);

                            for(i=0; i<=total_donnation; i++)
                            {
                                file1 = new File("pane1_img"+i+".png");
                                pane1_tiled_image = new Image(file1.toURI().toString(),image_Width,image_Height,false,false);
                                imageview_tile1 = new ImageView(pane1_tiled_image);
    //                            imageview_tile1.setCache(true);
    //                            imageview_tile1.setCacheHint(CacheHint.SPEED);
    //                                image_tile1.setFitWidth(image_Width);
    //                                image_tile1.setFitHeight(image_Height);
    //                                image_tile1.setPreserveRatio(false);

                                blink_file = new File("blink.png");
                                blink_image1 = new Image(blink_file.toURI().toString());
                                blink_image = new ImageView(blink_image1);
                                blink_image.setFitWidth(image_Width);
                                blink_image.setPreserveRatio(true);

    //                                hBox_outter = new HBox();
    //                                style_outter = "-fx-border-color: white;" + "-fx-border-width: 0;";
    //                                hBox_outter.setStyle(style_outter);
    //                                hBox_outter.getChildren().add(image_tile1);
                                hBox_outter_last = new HBox();
                                hBox_outter_last.setStyle(style_outter);
                                hBox_outter_last.getChildren().add(blink_image);

                                ft1 = new FadeTransition(Duration.millis(500), hBox_outter_last);
                                ft1.setFromValue(1.0);
                                ft1.setToValue(0.3);
                                ft1.setCycleCount(Animation.INDEFINITE);
                                ft1.setAutoReverse(true);

                                ft1.play();

                                grid_pane1.add(imageview_tile1, current_column_pane1,current_row_pane1);   
    //                            grid_pane1.setCacheHint(CacheHint.SPEED);
                                current_column_pane1 = current_column_pane1+1;
                                if (current_column_pane1 == max_columns_pane1 )
                                {
                                    current_row_pane1 = current_row_pane1+1;
                                    current_column_pane1 = 0;
                                }
                                if (i == max_donnation_pane1 ){ System.out.println("Pane 1 full"); break;}
                                if (i == total_donnation)
                                {
                                    if (i != max_donnation_pane1)
                                    {
                                        grid_pane1.add(hBox_outter_last, current_column_pane1,current_row_pane1);
                                    }
                                }
                            }

                            if (i < total_donnation)
                            {
                                total_donnation_left = total_donnation - max_donnation_pane1;
                                for(j=0; j<=total_donnation_left; j++)
                                {
                                    file2 = new File("pane2_img"+j+".png");
                                    pane2_tiled_image = new Image(file2.toURI().toString(),image_Width,image_Height,false,false);
                                    imageview_tile2 = new ImageView(pane2_tiled_image);
    //                                imageview_tile2.setCache(true);
    //                                imageview_tile2.setCacheHint(CacheHint.SPEED);
    //                                    image_tile2.setFitWidth(image_Width);
    //                                    image_tile2.setFitHeight(image_Height);
                    //                image_tile2.setPreserveRatio(true);

                                    blink_file = new File("blink.png");
                                    blink_image1 = new Image(blink_file.toURI().toString());
                                    blink_image = new ImageView(blink_image1);
                                    blink_image.setFitWidth(image_Width);
                                    blink_image.setPreserveRatio(true);

    //                                    hBox_outter = new HBox();
    //                                    style_outter = "-fx-border-color: white;" + "-fx-border-width: 0;";
    //                                    hBox_outter.setStyle(style_outter);
    //                                    hBox_outter.getChildren().add(image_tile2);
                                    hBox_outter_last = new HBox();
                                    hBox_outter_last.setStyle(style_outter);
                                    hBox_outter_last.getChildren().add(blink_image);

                                    ft = new FadeTransition(Duration.millis(500), hBox_outter_last);
                                    ft.setFromValue(1.0);
                                    ft.setToValue(0.3);
                                    ft.setCycleCount(Animation.INDEFINITE);
                                    ft.setAutoReverse(true);
                                    ft.play();

                                    grid_pane2.add(imageview_tile2, current_column_pane2,current_row_pane2);       
                                    current_column_pane2 = current_column_pane2+1;
                                    if (current_column_pane2 == max_columns_pane2 )
                                    {
                                        current_row_pane2 = current_row_pane2+1;
                                        current_column_pane2 = 0;
                                    }
                                    if (j == max_donnation_pane2 ){ System.out.println("Pane 2 full"); break;}
                                    if (j == total_donnation_left)
                                    {
                                        if (j != max_donnation_pane2)
                                        {
                                            grid_pane2.add(hBox_outter_last, current_column_pane2,current_row_pane2);
    //                                        grid_pane2.setCacheHint(CacheHint.SPEED);
                                        }
                                    }  
                                }
                            }  
                            current_column_pane1 =0;
                            current_row_pane1=0;
                            current_column_pane2=0;
                            current_row_pane2=0;
    //                      System.out.print("current column 1: "); 
    //                      System.out.println(current_column_pane1); 
    //                      System.out.print("current row 1: "); 
    //                      System.out.println(current_row_pane1); 
                            
                        }

                    }
                    catch (Exception ex) {}
                    translate_lastTimerCall = now;
                    
                }
            }
        };
    }
    
    @Override
    public void start(Stage stage) {

        Pane root = new Pane();
//        Scene scene = new Scene(root, 1280, 1024);
        Scene scene = new Scene(root, 983, 736);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        Mainpane =   Main_pane();
        image_pane =   image_pane();
        Mainpane.add(image_pane, 1, 10,11,4);  
        scene.setRoot(Mainpane);
//        stage.sizeToScene(); 
        stage.show();
        translate_timer.start(); 
        
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public GridPane Main_pane() {
        
         
//        image = JavaFXApplication3.class.getResource("/Images/greensquare.gif").toExternalForm();
//        Mainpane.setStyle("-fx-background-image: url('" + image + "'); -fx-background-repeat: repeat; ");  
//        Mainpane.setStyle("-fx-background-image: url('" + image + "');  -fx-background-repeat: no-repeat; -fx-background-size: contain;");        
        
        Mainpane.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build()       
        );
        Mainpane.getRowConstraints().setAll(
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/19.0).build()
        );
//        Mainpane.setGridLinesVisible(true);
        Mainpane.setId("Mainpane");
        
        return Mainpane;
    }
    
    public GridPane image_pane() {
      
        String image_old = JavaFXApplication3.class.getResource("/Images/mussalah_old.png").toExternalForm();
//        image_pane.setStyle("-fx-background-image: url('" + image_old + "'); -fx-background-repeat: no-repeat; -fx-background-size: contain;");        
        image_pane.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build()       
        );
        image_pane.getRowConstraints().setAll(
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/13.0).build()
        );
//        image_pane.setGridLinesVisible(true);
        image_pane.setPadding(new Insets(10, 10, 10, 10));
        image_pane.add(grid_pane1,3,9,13,14);
        image_pane.add(grid_pane2,15,1,12,21);
//        image_pane.setId("prayertime_pane");
        image_pane.setPadding(new Insets(20, 20, 20, 20));
        DropShadow ds = new DropShadow();
        ds.setOffsetY(10.0);
        ds.setOffsetX(10.0);
        ds.setColor(Color.BLACK);
        image_pane.setEffect(ds);
        return image_pane;
    }
    
    public void update() throws Exception{
      
    }
    
    public void draw() throws Exception{
        
    }
}

