/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
    
    GridPane image_pane;
    GridPane Mainpane = new GridPane();
    GridPane Subpane = new GridPane();
    boolean split = false;
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
                for (int i = 0; i < imgs2.length; i++) {  
                    ImageIO.write(imgs2[i], "png", new File("pane2_img" + i + ".png")); 
                }  
                System.out.println("Mini images created"); 
            }
    }
    
    
    
    @Override
    public void start(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 1024);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
                
        Mainpane = new GridPane();        
        String image = JavaFXApplication3.class.getResource("/Images/green-wallpaper.jpg").toExternalForm();
//        Mainpane.setStyle("-fx-background-image: url('" + image + "'); -fx-background-repeat: repeat; ");  
        Mainpane.setStyle("-fx-background-image: url('" + image + "'); -fx-background-image-repeat: repeat; -fx-background-size: 1920 1080;-fx-background-position: bottom left;");        
        
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
//        Mainpane.setGridLinesVisible(true);
        Mainpane.setId("Mainpane");
        GridPane image_pane =   image_pane();
         
  //============================================
        DropShadow ds = new DropShadow();
        ds.setOffsetY(10.0);
        ds.setOffsetX(10.0);
        ds.setColor(Color.BLACK);
        image_pane.setEffect(ds);
  //============================================
        Mainpane.add(image_pane, 2, 4,9,8);  
        scene.setRoot(Mainpane);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public GridPane image_pane() {
      
        Subpane = new GridPane();        
        String image = JavaFXApplication3.class.getResource("/Images/mussalah_old.png").toExternalForm();
        Subpane.setStyle("-fx-background-image: url('" + image + "'); -fx-background-image-repeat: no-repeat;");        
        
        Subpane.getColumnConstraints().setAll(
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
        Subpane.getRowConstraints().setAll(
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
        Subpane.setGridLinesVisible(true);
        GridPane grid_pane1 = new GridPane();
        GridPane grid_pane2 = new GridPane();
        
        int max_columns_pane1 = 45;
        int max_rows_pane1 = 26;
        int current_row_pane1 = 0;
        int current_column_pane1 = 0;
        int max_columns_pane2 = 43;
        int max_rows_pane2 = 42;
        int current_row_pane2 = 0;
        int current_column_pane2 = 0;
        double image_Width = 7.9;
        
        ///////////////////////////////////
        int total_donnation = 2974;
        //////////////////////////////////
        
        int max_donnation_pane1 = 1169;
        int max_donnation_pane2 = 1805;
        
        int i;
        
        for(i=0; i<=total_donnation; i++)
        {
//            ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("img"+i+".jpg")));
//            ImageView image_tile1 = new ImageView(new Image(getClass().getResourceAsStream("/Images/tiles/greensquare.gif")));
            File file1 = new File("pane1_img"+i+".png");
            Image image1 = new Image(file1.toURI().toString());
            ImageView image_tile1 = new ImageView(image1);
            image_tile1.setFitWidth(image_Width);
            image_tile1.setPreserveRatio(true);
            
            File blink_file = new File("pane1_img0.jpg");
            Image blink_image1 = new Image(blink_file.toURI().toString());
            ImageView blink_image = new ImageView(blink_image1);
            blink_image.setFitWidth(image_Width);
            blink_image.setPreserveRatio(true);
            
            HBox hBox_outter = new HBox();
            String style_outter = "-fx-border-color: white;" + "-fx-border-width: 0;";
            hBox_outter.setStyle(style_outter);
            hBox_outter.getChildren().add(image_tile1);
            HBox hBox_outter_last = new HBox();
            hBox_outter_last.setStyle(style_outter);
            hBox_outter_last.getChildren().add(blink_image);
            
            FadeTransition ft1 = new FadeTransition(Duration.millis(500), hBox_outter_last);
            ft1.setFromValue(1.0);
            ft1.setToValue(0.3);
            ft1.setCycleCount(Animation.INDEFINITE);
            ft1.setAutoReverse(true);

            ft1.play();
            
            grid_pane1.add(hBox_outter, current_column_pane1,current_row_pane1);       
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
            int total_donnation_left = total_donnation - max_donnation_pane1;
            for(int j=0; j<=total_donnation_left; j++)
            {
    //            ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("img"+i+".jpg")));
    //            ImageView image_tile1 = new ImageView(new Image(getClass().getResourceAsStream("/Images/tiles/greensquare.gif")));
                File file2 = new File("pane2_img"+j+".png");
                Image image2 = new Image(file2.toURI().toString());
                ImageView image_tile2 = new ImageView(image2);
                image_tile2.setFitWidth(image_Width);
                image_tile2.setPreserveRatio(true);

                File blink_file = new File("pane2_img0.jpg");
                Image blink_image1 = new Image(blink_file.toURI().toString());
                ImageView blink_image = new ImageView(blink_image1);
                blink_image.setFitWidth(image_Width);
                blink_image.setPreserveRatio(true);

                HBox hBox_outter = new HBox();
                String style_outter = "-fx-border-color: white;" + "-fx-border-width: 0;";
                hBox_outter.setStyle(style_outter);
                hBox_outter.getChildren().add(image_tile2);
                HBox hBox_outter_last = new HBox();
                hBox_outter_last.setStyle(style_outter);
                hBox_outter_last.getChildren().add(blink_image);

                FadeTransition ft = new FadeTransition(Duration.millis(500), hBox_outter_last);
                ft.setFromValue(1.0);
                ft.setToValue(0.3);
                ft.setCycleCount(Animation.INDEFINITE);
                ft.setAutoReverse(true);

                ft.play();

                grid_pane2.add(hBox_outter, current_column_pane2,current_row_pane2);       
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
                    }
                }  
            }
        }
//        
//        System.out.print("current column 1: "); 
//        System.out.println(current_column_pane1); 
//        System.out.print("current row 1: "); 
//        System.out.println(current_row_pane1); 
        
        Subpane.add(grid_pane1,3,9,13,13);
        Subpane.add(grid_pane2,16,1,12,21);
        Subpane.setId("prayertime_pane");
//        Subpane.setPadding(new Insets(20, 20, 20, 20));
        
        
        return Subpane;
    }
}

