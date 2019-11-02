package factures;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controllers.Alertes;
import dbManager.Manager;
import java.awt.Desktop;
//import static eshopn.models.PDFProduit.imagetoByteArray;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import model.Commande;
import model.Employe;
import model.Facture;
import model.LigneCommande;
import model.LigneFacture;
import model.Produit;
import outils.Constante;

/**
 *
 * @author hp
 */
public class PrintFacture {

        private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    private String template
            = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
            + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
            + "\n"
            + "<body style=\"font-family: Consolas;\">\n"
            + "\n"
            + "<div style=\"text-align:center;font-size:10px;\">\n"
            + "<p>$adresse</p>\n"
            + "<p>Contribuable : $nro</p>\n"
            + "<p>RC : $rc</p>\n"
            + "</div>\n"
            + "<br />\n"
            + "<table style=\"text-align: left;font-size:11px;\">\n"
            + "    <tr>\n"
            + "        <td><b><u>Date:</u></b>&nbsp;&nbsp; $date</td>\n"
            + "    </tr>\n"
            + "    <tr>\n"
            + "       <td colspan=\"2\"><b><u>Caissier(i&egrave;re):</u></b>&nbsp;&nbsp; $gestName</td>\n"
            + "    </tr>\n"
            + "    <tr>\n"
            + "        <td><b><u>Facture No:</u></b>&nbsp;&nbsp; $no</td>\n"
            + "    </tr>\n"
            + "    <tr>\n"
            + "        <td><b><u>Tel client:</u></b>&nbsp;&nbsp; $phone</td>\n"
            + "        <td></td>\n"
            + "    </tr>\n"
            + "    <tr>\n"
            + "        <td><b><u>Mode de paiement:</u></b>&nbsp;&nbsp; $mode</td>\n"
            + "    </tr>\n"
            + "    <tr>\n"
            + "        <td><b><u>Remise:</u></b>&nbsp;&nbsp; $remise %</td>\n"
            + "    </tr>\n"
            + "</table>\n"
            + "<br />\n"
            + "<table style=\"text-align: left;font-size:11px;\">\n"
            + "    <tr>\n"
            + "        <td><b><u>Montant total:</u></b>&nbsp;&nbsp; $total Fcfa</td>\n"
            + "    </tr>\n"
            + "    <tr>\n"
            + "        <td><b><u>Net &agrave; payer:</u></b>&nbsp;&nbsp; $NAP Fcfa</td>\n"
            + "    </tr>\n"
            + "</table>\n"
            + "\n"
            + "<br />\n"
            + "\n"
            + "<p>******************** Listes des produits ********************</p>"
            + "<br />\n"
            + "<table id=\"produits\" border=\"1\" style=\"border-collapse: collapse;\">\n"
            + "    <tr>\n"
            + "        <th><b>Libelle</b></th>\n"
            + "        <th style=\"width=5%;\"><b>Qte</b></th>\n"
            + "        <th><b>P.U</b></th>\n"
            + "        <th><b>S. Total</b></th>\n"
            + "    </tr>\n"
            + "    $products"
            + "</table>\n"
            + "<p>-------------------------------------------------------------------------</p>"
            + "<p style=\"font-size:10px;\">Les produits vendus et livrés ne sont ni repris ni échangés</p>"
            + "<p>-------------------------------------------------------------------------</p>"
            + "\n"
            + "</body>\n"
            + "\n"
            + "</html>";

    public Date date = new Date();

    public File print(Facture facture, Employe emp, ObservableList<LigneFacture> listeLigneFacture,
            double remise, String mode, String no, String adresse, boolean visualise) throws DocumentException, FileNotFoundException, BadElementException, IOException, PrinterException {
        System.out.println("sdfsdf");
        date = facture.getDateEnregistrement();
        template = template.replace("$date", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date));
        template = template.replace("$gestName", (emp.getId() +" - " + emp.getNom()));
        template = template.replace("$phone", Constante.TEL);

        String rows = "";
        double montant = 0.0;
        float stot = 0.0f;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2); //arrondi à 2 chiffres apres la virgules
        df.setMinimumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);
        for (LigneFacture p : listeLigneFacture) {
            stot = p.getPrixUnitaire() * p.getQuantite();
            montant += stot;
            rows += "<tr style=\"font-size:10px;\"><td>" + Manager.em.find(Produit.class, p.getLigneFactureId().getProduitId()).getNom() + "</td><td>"
                    + p.getQuantite() + "</td><td>" + p.getPrixUnitaire() + "</td><td>" + stot + "</td></tr>";

        }

        template = template.replace("$products", rows);

        template = template.replace("$total", "" + (df.format(montant)));
        template = template.replace("$remise", String.valueOf(remise));
        template = template.replace("$NAP", "" + df.format((1 - remise / 100) * montant));
        template = template.replace("$mode", mode);
        template = template.replace("$adresse", adresse);
        template = template.replace("$nro", "0");
        template = template.replace("$rc", "0");
        template = template.replace("$no", no);

        Document document = new Document(new Rectangle(315, 850));
        document.setMargins(10, 10, 0, 0);
        File file;
        if (visualise) {
            file = new File(Constante.CHEMIN + "VIS" + ".pdf");
        } else {
            file = new File(Constante.CHEMIN + facture.getId() + ".pdf");
        }
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        Image image = Image.getInstance(Constante.CHEMINLOGOPRINT);
        image.setSpacingBefore(150f);
        image.setSpacingAfter(150f);
        image.scaleAbsolute(80f, 80f);
        image.setAlignment(Image.ALIGN_CENTER);
        document.add(image);

        List<Element> parts = HTMLWorker.parseToList(new StringReader(template), null);
        for (int i = 0; i < parts.size(); i++) {
            Element e = (Element) parts.get(i);
            document.add(e);
        }

        document.close();
        if(visualise){
            Desktop d = Desktop.getDesktop();
            try {
                d.open(file);
            } catch (IOException ex) {
                Logger.getLogger(PrintFacture.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("rsfsdfsdfs");
        return file;
    }
    public void ecrireRapportCommande(ObservableList<Commande> commandes, String nom, LocalDate from, LocalDate to) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(Constante.CHEMINRAPPORTCMD + nom + ".pdf"));
            document.open();
            addTitle(document, "Rapport des Commandes entre le " + from.toString() + " et le " + to.toString());
            Paragraph par = new Paragraph();
            double tot = 0.0, total = 0.0;
            for(Commande commande: commandes){
                tot = 0.0;
                par.add("Commande numero " + commande.getId() + " Montant " + commande.getMontant()
                + " Créée par " + Manager.em.find(Employe.class, commande.getEmployeid()) + " Le " + commande.getDateEnregistrement().toString());
                addEmptyLine(par, 1);
                par.add(createTableCommande(commande));
                for(LigneCommande loc : commande.getligneCommandes(Manager.em)){
                    tot += loc.getPrixUnitaire() * loc.getQuantite();
                }
                par.add(new Paragraph(
                "Total Commande: " + tot + " XAF ", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
                addEmptyLine(par, 1);
                total += tot;
            }
            addEmptyLine(par, 3);
            par.add(new Paragraph(
                "Total: " + total + " XAF ", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
            document.add(par);
            document.close();
            open(Constante.CHEMINRAPPORTCMD+nom+".pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintFacture.class.getName()).log(Level.SEVERE, null, ex);
            Alertes.alerte("Error", "There was a problem while creating the pdf");
        } catch (DocumentException ex) {
            Logger.getLogger(PrintFacture.class.getName()).log(Level.SEVERE, null, ex);
            Alertes.alerte("Error", "There was a problem while creating the pdf");
        }
    }
   
    public PdfPTable createTableCommande(Commande commande){
        PdfPTable table = new PdfPTable(8);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        table.setWidthPercentage(100);  
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        
        PdfPCell c1 = new PdfPCell(new Phrase("Code"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Nom"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantité"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Prix Unitaire"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Fournisseur"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date d'enregistrement"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Employé"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        table.setHeaderRows(1);
        
        for (LigneCommande loc : commande.getligneCommandes(Manager.em)){
            table.addCell(""+loc.getLigneCommandeId().getProduitid());
            table.addCell(""+Manager.em.find(Produit.class,loc.getLigneCommandeId().getProduitid()).getNom());
            table.addCell(""+loc.getQuantite());
            table.addCell(""+loc.getPrixUnitaire());
            table.addCell(""+(loc.getQuantite()*loc.getPrixUnitaire()));
            table.addCell(""+loc.getFournisseurid());
            table.addCell(""+commande.getDateEnregistrement().toString());
            table.addCell(""+commande.getEmployeid());
        }
        return table;
    }

    public void ecrireRapportFacture(ObservableList<Facture> factures, String nom, LocalDate from, LocalDate to) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(Constante.CHEMINRAPPORTVTE + nom + ".pdf"));
            document.open();
            addTitle(document, "Rapport des Factures entre le " + from.toString() + " et le " + to.toString());
            Paragraph par = new Paragraph();
            double tot = 0.0, total = 0.0;
            for(Facture fact: factures){
                tot = 0.0;
                par.add("Facture numero " + fact.getId() + " Montant " + fact.getMontant()
                + " Créée par " + Manager.em.find(Employe.class, fact.getEmployeId()) + " Le " + fact.getDateEnregistrement().toString());
                addEmptyLine(par, 1);
                par.add(createTableFacture(fact));
                for(LigneFacture loc : fact.ligneFactures(Manager.em)){
                    tot += loc.getPrixUnitaire() * loc.getQuantite();
                }
                par.add(new Paragraph(
                "Total Commande: " + tot + " XAF ", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
                addEmptyLine(par, 1);
                total += tot;
            }
            addEmptyLine(par, 3);
            par.add(new Paragraph(
                "Total: " + total + " XAF ", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
            document.add(par);
            document.close();
            open(Constante.CHEMINRAPPORTVTE+nom+".pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintFacture.class.getName()).log(Level.SEVERE, null, ex);
            Alertes.alerte("Error", "There was a problem while creating the pdf");
        } catch (DocumentException ex) {
            Logger.getLogger(PrintFacture.class.getName()).log(Level.SEVERE, null, ex);
            Alertes.alerte("Error", "There was a problem while creating the pdf");
        }
    }
   
    public PdfPTable createTableFacture(Facture fact){
        PdfPTable table = new PdfPTable(7);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        table.setWidthPercentage(100);  
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        
        PdfPCell c1 = new PdfPCell(new Phrase("Code"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Nom"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantité"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Prix Unitaire"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Date d'enregistrement"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Employé"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        table.setHeaderRows(1);
        
        for (LigneFacture loc : fact.ligneFactures(Manager.em)){
            table.addCell(""+loc.getLigneFactureId().getProduitId());
            table.addCell(""+Manager.em.find(Produit.class,loc.getLigneFactureId().getProduitId()).getNom());
            table.addCell(""+loc.getQuantite());
            table.addCell(""+loc.getPrixUnitaire());
            table.addCell(""+(loc.getQuantite()*loc.getPrixUnitaire()));
            table.addCell(""+fact.getDateEnregistrement().toString());
            table.addCell(""+fact.getEmployeId());
        }
        return table;
    }
    
    public void addTitle(Document document, String nom) {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph(nom, catFont));
        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Rapport généré par: SuperMarket" + ", " + LocalDate.now().toString(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 3);
        try {
            document.add(preface);
        } catch (DocumentException ex) {
            Logger.getLogger(PrintFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    public static void open(String chemin) {
        Desktop d = Desktop.getDesktop();
        try {
            d.open(new File(chemin));
        } catch (IOException ex) {
            Logger.getLogger(PrintFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
