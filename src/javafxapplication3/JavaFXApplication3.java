/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/*
TO DO:

insert logos

*/


package javafxapplication3;

import eu.hansolo.enzo.common.Section;
import eu.hansolo.enzo.gauge.SimpleGauge;
import eu.hansolo.enzo.gauge.SimpleGaugeBuilder;
import eu.hansolo.enzo.imgsplitflap.SplitFlap;
import eu.hansolo.enzo.imgsplitflap.SplitFlapBuilder;
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
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;  
import javax.imageio.ImageIO;

/**
 *
 * @author Ossama
 */

public class JavaFXApplication3 extends Application {
    
    GridPane image_pane = new GridPane();
    GridPane Mainpane = new GridPane();
    GridPane Subpane = new GridPane();
    GridPane grid_pane1 = new GridPane();
    GridPane grid_pane2 = new GridPane();
    private SplitFlap dollar,area1000000,area100000,area10000,area1000, area100, area10, area1, fassila1, fassila2;
    
    HBox hBox_outter1;
    HBox hBox_outter2;
    
    HBox hBox_outter_last;
    HBox hBox_old_Mussalah;
    HBox hbox_splitflap;

    boolean split = false;
    boolean pane1_full = false;

    private int total_donnation = 0;
    private int old_total_donnation = 0 ;
    private int  dollar_value = 0 ;
    
    private AnimationTimer translate_timer;
    private long translate_lastTimerCall;
    
    String image;
    
    File file1;
//    Image image1;
    ImageView imageview_tile1;
    File blink_file ;
    Image blink_image1;
    ImageView blink_image;
    double border_width = 0.75;
    String style_outter =   "-fx-border-color: black;" + "-fx-border-width: " + border_width + ";";
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
    
//Node Positioning///////////////////////////////////
    
    int titleX = 50;
    int titleY = 0;
    int image_paneX = -65;
    int image_paneY = 75;
    int footerX = 35;
    int footerY = -20;
 //////////////////////////////////////////////////////   
    
    
// Image sizing ////////////////////////////////
    double image_Width = 7.2;   //7.95 
    double image_Height =7.2;  //7.95
    int old_Mussalah_imageWidth = 1150;
//////////////////////////////////////////////////////    

    int max_donnation_pane1 = 1169;
    int max_donnation_pane2 = 1805;

    int i,j;
    
    Connection c;
    String SQL;
    ResultSet rs;
        
    @Override 
        public void init() throws IOException {
            

            Font.loadFont(JavaFXApplication3.class.getResource("Fonts/Bebas.ttf").toExternalForm(),30);
            Font.loadFont(JavaFXApplication3.class.getResource("Fonts/avantgarde-demi.ttf").toExternalForm(),30);
            
            blink_file = new File("blink.png");
            blink_image1 = new Image(blink_file.toURI().toString());
            blink_image = new ImageView(blink_image1);
            blink_image.setFitWidth(image_Width);
            blink_image.setPreserveRatio(true);
    
            new Thread(() -> 
        {
             System.out.println(" ... Database thread starting.....");
//             boolean firsttime = true;
             for (;;) 
             {
                 try 
                {
//                    old_total_donnation = total_donnation;
                    try
                    {
//                        if (firsttime){
                        c = DBConnect.connect();
                        SQL = "Select sum(total_donnation) as total_donnation from donations";
                        rs = c.createStatement().executeQuery(SQL);
                        while (rs.next()) 
                        {total_donnation = rs.getInt("total_donnation");}
                        c.close(); 
//                        if (total_donnation != old_total_donnation){System.out.format("Total Donation Update: %s \n", total_donnation);}
                        dollar_value = (total_donnation-900)*850;
                        total_donnation = total_donnation - 311; //311
//                        firsttime = false;
//                        }
//                        total_donnation = total_donnation +5;
                        Thread.sleep(4000);
                    }
                    catch (Exception e){Logger.getLogger(JavaFXApplication3.class.getName()).log(Level.SEVERE, null, e);}
                }     
                 catch (Exception ex) {Logger.getLogger(JavaFXApplication3.class.getName()).log(Level.SEVERE, null, ex); Thread.currentThread().interrupt();}
             }
         }).start();

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
                if (now > translate_lastTimerCall + 10000_000_000l) 
                {                    
                    try 
                    {
                        if(total_donnation != old_total_donnation)
                        {
                            
                            System.out.format("$$ value: %s \n", dollar_value);
                            
//                            
                            

                            if (dollar_value<1000000 && dollar_value>99999)
                            {
                                area1.setText(Integer.toString(dollar_value).substring(5, 6));
                                area10.setText(Integer.toString(dollar_value).substring(4, 5));
                                area100.setText(Integer.toString(dollar_value).substring(3, 4));
                                area1000.setText(Integer.toString(dollar_value).substring(2, 3)); 
                                area10000.setText(Integer.toString(dollar_value).substring(1, 2)); 
                                area100000.setText(Integer.toString(dollar_value).substring(0, 1)); 
                                area1000000.setText(" ");
                                fassila1.setText(" ");
                                fassila2.setText(",");
                            } 
                            
                            else if (dollar_value>1000000) 
                            {
                                area1.setText(Integer.toString(dollar_value).substring(6, 7));
                                area10.setText(Integer.toString(dollar_value).substring(5, 6));
                                area100.setText(Integer.toString(dollar_value).substring(4, 5));
                                area1000.setText(Integer.toString(dollar_value).substring(3, 4)); 
                                area10000.setText(Integer.toString(dollar_value).substring(2, 3)); 
                                area100000.setText(Integer.toString(dollar_value).substring(1, 2)); 
                                area1000000.setText(Integer.toString(dollar_value).substring(0, 1));
                                fassila1.setText(",");
                                fassila2.setText(",");
                            }
                            
                            else if (dollar_value<100000 && dollar_value >9999) 
                            {
                                area1.setText(Integer.toString(dollar_value).substring(4, 5));
                                area10.setText(Integer.toString(dollar_value).substring(3, 4));
                                area100.setText(Integer.toString(dollar_value).substring(2, 3));
                                area1000.setText(Integer.toString(dollar_value).substring(1, 2));
                                area10000.setText(Integer.toString(dollar_value).substring(0, 1));
                                area100000.setText(" ");
                                area1000000.setText(" ");
                                fassila1.setText(" ");
                                fassila2.setText(",");
                            }
                            
                            else if (dollar_value<10000 && dollar_value >999) 
                            {
                                area1.setText(Integer.toString(dollar_value).substring(3, 4));
                                area10.setText(Integer.toString(dollar_value).substring(2, 3));
                                area100.setText(Integer.toString(dollar_value).substring(1, 2));
                                area1000.setText(Integer.toString(dollar_value).substring(0, 1));
                                area10000.setText(" ");
                                area100000.setText(" ");
                                area1000000.setText(" ");
                                fassila1.setText(" ");
                                fassila2.setText(",");
                            }
                            
                            else if (dollar_value<1000 && dollar_value >99) 
                            {
                                area1.setText(Integer.toString(dollar_value).substring(2, 3));
                                area10.setText(Integer.toString(dollar_value).substring(1, 2));
                                area100.setText(Integer.toString(dollar_value).substring(0, 1));
                                area1000.setText(" ");
                                area10000.setText(" ");
                                area100000.setText(" ");
                                area1000000.setText(" ");
                                fassila1.setText(" ");
                                fassila2.setText(" ");
                            }
                            
                             else if (dollar_value<100 && dollar_value >9) 
                            {
                                area1.setText(Integer.toString(dollar_value).substring(1, 2));
                                area10.setText(Integer.toString(dollar_value).substring(0, 1));
                                area100.setText(" ");
                                area1000.setText(" ");
                                area10000.setText(" ");
                                area100000.setText(" ");
                                area1000000.setText(" ");
                                fassila1.setText(" ");
                                fassila2.setText(" ");
                            }
                            
                             else if (dollar_value<10) 
                            {
                                area1.setText(Integer.toString(dollar_value).substring(0, 1));
                                area10.setText(" ");
                                area100.setText(" ");
                                area1000.setText(" ");
                                area10000.setText(" ");
                                area100000.setText(" ");
                                area1000000.setText(" ");
                                fassila1.setText(" ");
                                fassila2.setText(" ");
                            }
                            

//                            
                            old_total_donnation = total_donnation;
//                            System.out.format("Working");
                            
                            if (!pane1_full)
                            {
                                grid_pane1.getChildren().clear();
                                grid_pane1.getChildren().removeAll(imageview_tile1,hBox_outter_last);                                
                            }
                            grid_pane2.getChildren().clear();
                            grid_pane2.getChildren().removeAll(imageview_tile2,hBox_outter_last);

                            
                            
                            for(i=0; i<=total_donnation; i++)
                            {
                                if (pane1_full){ if(ft1 != null) { ft1.stop(); } break;}
                                ImageView imageview_tile1 = new ImageView(new Image(getClass().getResourceAsStream("/Images/image_tiles/pane1_img"+i+".png")));                                   
                                imageview_tile1.setFitWidth(image_Width);
                                imageview_tile1.setFitHeight(image_Height);
                                imageview_tile1.setPreserveRatio(false);
                                hBox_outter1 = new HBox();
                                hBox_outter1.setStyle(style_outter);
                                hBox_outter1.getChildren().add(imageview_tile1);
                                grid_pane1.add(hBox_outter1, current_column_pane1,current_row_pane1);   
                                current_column_pane1 = current_column_pane1+1;
                                
                                if (current_column_pane1 == max_columns_pane1 )
                                {
                                    current_row_pane1 = current_row_pane1+1;
                                    current_column_pane1 = 0;
                                }
                                if (i == max_donnation_pane1 ){ pane1_full = true; if(ft1 != null) { ft1.stop(); } break; }
                                if (i == total_donnation)
                                {
                                    if (i != max_donnation_pane1)
                                    {
//                                        String nodeId = hBox_outter1.idProperty().get();
//                                        System.out.println(nodeId);
                                                
                                                
//                                        hBox_outter_last = new HBox();
//                                        hBox_outter_last.setStyle(style_outter);
//                                        hBox_outter_last.getChildren().add(blink_image);
//                                        
//                                        if(ft1 != null) { ft1.stop(); }
//                                        ft1 = new FadeTransition(Duration.millis(1000), hBox_outter_last);
//                                        ft1.setFromValue(1.0);
//                                        ft1.setToValue(0.3);
//                                        ft1.setCycleCount(Animation.INDEFINITE);
//                                        ft1.setAutoReverse(true);
//                                        ft1.play();
//                                        grid_pane1.add(hBox_outter_last, current_column_pane1,current_row_pane1);
//                                        hBox_outter_last.opacityProperty().bind(opacity);
//                                        opacityTransition.stop();
//                                        opacityTransition.play();
                                    }
                                }
                            }

                            if (i < total_donnation)
                            {
                                total_donnation_left = total_donnation - max_donnation_pane1;
                                for(j=0; j<=total_donnation_left; j++)
                                {

                                    ImageView imageview_tile2 = new ImageView(new Image(getClass().getResourceAsStream("/Images/image_tiles/pane2_img"+j+".png")));      
                                    imageview_tile2.setFitWidth(image_Width);
                                    imageview_tile2.setFitHeight(image_Height);
                                    imageview_tile2.setPreserveRatio(false);
                                    
                                    hBox_outter2 = new HBox();
                                    hBox_outter2.setStyle(style_outter);
                                    hBox_outter2.getChildren().add(imageview_tile2);
                                    
                                    
                                    grid_pane2.add(hBox_outter2, current_column_pane2,current_row_pane2);       
                                    current_column_pane2 = current_column_pane2+1;
                                    if (current_column_pane2 == max_columns_pane2 )
                                    {
                                        current_row_pane2 = current_row_pane2+1;
                                        current_column_pane2 = 0;
                                    }
                                    if (j >= max_donnation_pane2 ){ System.out.println("Pane 2 full"); break;}
                                    if (j == total_donnation_left)
                                    {
                                        if (j != max_donnation_pane2)
                                        {
//                                            hBox_outter_last = new HBox();
//                                            hBox_outter_last.setStyle(style_outter);
//                                            hBox_outter_last.getChildren().add(blink_image);
                                            
//                                            if(ft != null) { ft.stop(); System.out.println("ft stoped");}
//                                            
//                                            ft = new FadeTransition(Duration.millis(500), hBox_outter_last);
//                                            ft.setFromValue(1.0);
//                                            ft.setToValue(0.3);
//                                            ft.setCycleCount(Animation.INDEFINITE);
//                                            ft.setAutoReverse(true);
//                                            ft.play();
                                            
//                                            grid_pane2.add(hBox_outter_last, current_column_pane2,current_row_pane2);
                                        }
                                    }  
                                }
                            }  
                            current_column_pane1 =0;
                            current_row_pane1=0;
                            current_column_pane2=0;
                            current_row_pane2=0;
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
//        Scene scene = new Scene(root, 1920, 1080);
        Scene scene = new Scene(root, 1280, 1024);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        String image = JavaFXApplication3.class.getResource("/Images/13.jpg").toExternalForm();
        Mainpane.setStyle("-fx-background-image: url('" + image + "'); -fx-background-image-repeat: yes; -fx-background-size: 1280 1024;-fx-background-position: top right;");          
        
        area1000000 = SplitFlapBuilder.create().minWidth(50).minHeight(90).maxHeight(90).flipTime(150).selection(SplitFlap.NUMERIC).textColor(Color.WHITESMOKE).build();
        area100000 = SplitFlapBuilder.create().minWidth(50).minHeight(90).maxHeight(90).flipTime(150).selection(SplitFlap.NUMERIC).textColor(Color.WHITESMOKE).build();
        area10000 = SplitFlapBuilder.create().minWidth(50).minHeight(90).maxHeight(90).flipTime(150).selection(SplitFlap.NUMERIC).textColor(Color.WHITESMOKE).build();
        area1000 = SplitFlapBuilder.create().minWidth(50).minHeight(90).maxHeight(90).flipTime(150).selection(SplitFlap.NUMERIC).textColor(Color.WHITESMOKE).build();
        area100 = SplitFlapBuilder.create().minWidth(50).minHeight(90).maxHeight(90).flipTime(150).selection(SplitFlap.NUMERIC).textColor(Color.WHITESMOKE).build();
        area10 = SplitFlapBuilder.create().minWidth(50).minHeight(90).maxHeight(90).flipTime(150).selection(SplitFlap.NUMERIC).textColor(Color.WHITESMOKE).build();
        area1 = SplitFlapBuilder.create().minWidth(50).minHeight(90).maxHeight(90).flipTime(150).selection(SplitFlap.NUMERIC).textColor(Color.WHITESMOKE).build();
        fassila1 = SplitFlapBuilder.create().minWidth(50).minHeight(90).maxHeight(90).flipTime(0).selection(SplitFlap.EXTENDED).textColor(Color.WHITESMOKE).build();
        fassila2 = SplitFlapBuilder.create().minWidth(50).minHeight(90).maxHeight(90).flipTime(0).selection(SplitFlap.EXTENDED).textColor(Color.WHITESMOKE).build();
        dollar = SplitFlapBuilder.create().minWidth(50).minHeight(90).maxHeight(90).flipTime(0).selection(SplitFlap.EXTENDED).textColor(Color.WHITESMOKE).build();
        fassila1.setText(",");
        fassila2.setText(",");
        dollar.setText("$");
        
        HBox hbox_splitflap = new HBox();
        hbox_splitflap.setSpacing(5);
        hbox_splitflap.getChildren().addAll(dollar,area1000000,fassila1,area100000,area10000,area1000,fassila2,area100,area10,area1);
        hbox_splitflap.setTranslateY(-10);       
        
        Text donnation_text = new Text("TOTAL $$$ RAISED   ");
        donnation_text.setId("donnation_text");
        donnation_text.setFill(Color.WHITE);             
        HBox footer = new HBox();
        footer.getChildren().addAll(donnation_text,hbox_splitflap);
        footer.setId("prayertime_pane");
        footer.setPadding(new Insets(30, 90, 7, 70));
        footer.setTranslateX(footerX);
        footer.setTranslateY(footerY);
        
        
        Text title_text2 = new Text("SMS YOUR SILENT PLEDGE TO  ");
        title_text2.setId("title_text2");
        title_text2.setFill(Color.WHITE);
//        title_text2.setTranslateY(30);
//        title_text2.setTranslateX(-40);
        
        TextFlow sms_flow = new TextFlow();
        Text title_text4 = new Text("0410 856 170");
        title_text4.setId("title_text4");
        title_text4.setFill(Color.GOLDENROD);
//        title_text4.setTranslateY(30);
//        title_text4.setTranslateX(-40);
        sms_flow.getChildren().addAll(title_text2,title_text4);
        sms_flow.setTranslateY(70);
        sms_flow.setTranslateX(-105);
        
        image_pane =   image_pane();
        
        TextFlow Title_flow = new TextFlow();
        Text title_text1 = new Text("              DAAR IBN ABBAS  & DAAR AL-QURAN\n\n");
        title_text1.setId("title_text1");
        title_text1.setFill(Color.GOLDENROD);
        Text title_text3 = new Text("     CONDELL PARK MASJID FUNDRAISER");
        title_text3.setId("title_text3");
        title_text3.setFill(Color.WHITE);
        Title_flow.getChildren().addAll(title_text1,title_text3);
        title_text3.setTranslateY(-37);
        Title_flow.setTranslateY(titleY);
        Title_flow.setTranslateX(titleX);
        Title_flow.setPadding(new Insets(20, -50, -20, 20));
        Title_flow.setId("prayertime_pane");
                
        Mainpane.add(Title_flow, 1, 1,10,2);
        Mainpane.add(image_pane, 1, 3,6,12);
        Mainpane.add(footer, 0, 16,7,2);
        Mainpane.add(sms_flow, 5, 17,7,2);
        
        DropShadow ds = new DropShadow();
        ds.setOffsetY(10.0);
        ds.setOffsetX(10.0);
        ds.setColor(Color.BLACK);
        
        Title_flow.setEffect(ds);
        image_pane.setEffect(ds);
        donnation_text.setEffect(ds);
        hbox_splitflap.setEffect(ds);
        
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
        System.exit(0);
    }
    
    public GridPane image_pane() {
      
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
//        Mainpane.setId("Mainpane");
        
        ImageView imageview_old_Mussalah = new ImageView(new Image(getClass().getResourceAsStream("/Images/mussalah_old_cut_round_grey.png")));      
        imageview_old_Mussalah.setFitWidth(old_Mussalah_imageWidth);
        imageview_old_Mussalah.setPreserveRatio(true);
        
        hBox_old_Mussalah = new HBox();
        hBox_old_Mussalah.getChildren().add(imageview_old_Mussalah);
        hBox_old_Mussalah.setId("prayertime_pane");
        hBox_old_Mussalah.setPadding(new Insets(30, 30, 30, 30));
//        image_pane.setGridLinesVisible(true);
        image_pane.add(grid_pane1,0,0);
        image_pane.add(grid_pane2,1,0);
        image_pane.add(hBox_old_Mussalah,0,0);
        grid_pane1.toFront();
        grid_pane2.toFront(); 
        
        image_pane.setTranslateX(image_paneX);
        image_pane.setTranslateY(image_paneY);
        
        int pane1_setTranslateX = 150;
        int pane1_setTranslateY = 207;
        
        grid_pane1.setTranslateX(pane1_setTranslateX);
        grid_pane1.setTranslateY(pane1_setTranslateY);
        
        grid_pane2.setTranslateX(-760 +pane1_setTranslateX );
        grid_pane2.setTranslateY(-18*(image_Height+(border_width*2))+ pane1_setTranslateY);

        return image_pane;
    }
    
    
}
