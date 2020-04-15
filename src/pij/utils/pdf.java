/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.utils;

/**
 *
 * @author HP
 */
import pij.entity.don;
import pij.entity.don_crud;
import pij.entity.stock;
import pij.entity.stock_crud;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
public class pdf {
 Connection con;
        private Statement ste;
    public pdf()  {
        
        con = MyConnection.getInstance().getCnx();
          
    
}
    public void add(String file) throws FileNotFoundException, SQLException, DocumentException{
      
        /* Create Connection objects */
//                con = DataBase.getInstance().getConnection();
                Document my_pdf_report = new Document();
                    
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
                 ste=con.createStatement();
                  PdfPCell table_cell, table_cell_1,table_cell_2,table_cell_3;
                  PdfPTable my_report_table = new PdfPTable(4);
                  table_cell=new PdfPCell(new Phrase("Libelle"));
                  table_cell_1=new PdfPCell(new Phrase("Quantite"));
                  table_cell_2=new PdfPCell(new Phrase("Date"));
                   table_cell_3=new PdfPCell(new Phrase("Stock_id"));
                 // table_cell_3=new PdfPCell(new Phrase("id_seance"));
                  my_report_table.addCell(table_cell);
                  my_report_table.addCell(table_cell_1);
                  my_report_table.addCell(table_cell_2);
                  my_report_table.addCell(table_cell_3);

                //  my_report_table.addCell(table_cell_3);
                  ResultSet rs=ste.executeQuery("select d.*,s.type from don d INNER JOIN stock s on d.Stock_id = s.id ");
                  my_pdf_report.open(); 

                        
                               
                                
               
                while (rs.next()) {  
                    
                              String orderState= rs.getString("libelle");
                                table_cell=new PdfPCell(new Phrase(orderState));
                               my_report_table.addCell(table_cell);
                                           
                                int orderState1= rs.getInt("quantite");
                                table_cell_1=new PdfPCell(new Phrase(orderState1));
                               my_report_table.addCell(table_cell_1);
                                
                               
                                //String orderState2= rs.getDate("date");
                               // table_cell_2=new PdfPCell(new Phrase(orderState2));
                              // my_report_table.addCell(table_cell_2);
                               
                                String orderState3= rs.getString("type");
                                table_cell_3=new PdfPCell(new Phrase(orderState3));
                               my_report_table.addCell(table_cell_3);
                               
                               /* my_report_table.addCell(""+table_cell_2);
                                String orderState2=rs.getString("type_absence");*/
                                
                               //  table_cell_3=new PdfPCell(new Phrase("id_seance"));
                                //my_report_table.addCell(table_cell_3).toString();
                                 //String orderState3= rs.getString("id_seance");
                                
                                 
                                
                                
                                
                                //my_report_table.addCell(""+orderState);
                                
                                
//                                table_cell=new PdfPCell(new Phrase(id));
//                                my_report_table.addCell(table_cell).toString();
                                
//                                
//                                
//                                float tt=rs.getFloat("total");
//                                table_cell=new PdfPCell(new Phrase(tt));
//                                my_report_table.addCell(table_cell);
//                                
//                                String orderState= rs.getString("orderState");
//                                table_cell=new PdfPCell(new Phrase(orderState));
//                                my_report_table.addCell(table_cell);
                }
                                
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
               /* Close all DB related objects */
                 rs.close();
                ste.close();
                con.close();
        
    }
}
    

