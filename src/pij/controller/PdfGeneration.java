/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Date;

/**
 *
 * @author HP
 */
public class PdfGeneration {
    void pdfGeneration (String libelle,int Stock_id ){
        Document document =new Document();
       try {
           PdfWriter.getInstance(document, new FileOutputStream(libelle+".pdf"));
           document.open();
           document.add(new Paragraph("\n\nhello"+Stock_id , FontFactory.getFont(FontFactory.TIMES)));
           Chunk signature =new Chunk("jjjjjjjjjjj");
           Paragraph base =new Paragraph(signature);
           document.add(base);
           
       }catch(Exception e){e.printStackTrace();
           
       }
       document.close();
   }
    
}
