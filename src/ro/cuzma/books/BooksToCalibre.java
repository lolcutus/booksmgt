package ro.cuzma.books;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.Vector;

import ro.cuzma.larry.persistance.xml.XMLUtil;
import ro.cuzma.larry.persistance.xml.exception.XMLException;
import ro.cuzma.larry.persistance.xml.samples.sample2.objects.CalibreBook;
import ro.cuzma.larry.persistance.xml.samples.sample2.objectxmlconnection.PackageHelper;

public class BooksToCalibre {

    /**
     * @param args
     * @throws XMLException
     */
    public static void main(String[] args) throws XMLException {
        BooksDatabase bdb = new BooksDatabase();
        bdb.load(new File("e:\\Carti\\all.xml"));
        BookRow br;
        CalibreBook cbk;
        for (int i = 742; i < bdb.getBooks().size(); i++) {
            br = (BookRow) bdb.getBooks().get(i);
            cbk = new CalibreBook();
            cbk.setTitle((String) br.getDisplayValue(BookRow.COLUMN_TITLE));
            Vector obj = (Vector) br.getValue(BookRow.COLUMN_AUTHORS);
            for (int j = 0; j < obj.size(); j++) {
                cbk.addCreator(((Author) obj.get(j)).getStringRow());
            }
            cbk.setPublisher((String) br.getDisplayValue(BookRow.COLUMN_PUBLISHER));
            cbk.setDate((String) br.getDisplayValue(BookRow.COLUMN_APPARITION_DATE));
            GregorianCalendar gc = (GregorianCalendar) br.getValue(BookRow.COLUMN_BOUGHT_DATE);
            // GregorianCalendar gc = (GregorianCalendar) tcc.getValue();
            if (gc != null) {
                cbk.setBoughtDate(XMLUtil.convertGregorianCalendar(gc, new SimpleDateFormat(
                        "yyyy-MM-dd")));
            }
            cbk.setIsbn((String) br.getDisplayValue(BookRow.COLUMN_ISBN));
            // cbk.setRating((String) br.getDisplayValue(BookRow.COLUMN_));
            cbk.setLanguage((String) br.getDisplayValue(BookRow.COLUMN_LANGUAGE));
            cbk.setSeries((String) br.getDisplayValue(BookRow.COLUMN_SERIE));
            String tmp = (String) br.getDisplayValue(BookRow.COLUMN_SERIE_POS);
            if (tmp != null && !tmp.equals("")) {
                cbk.setSeries_index(tmp);
            }
            tmp = (String) br.getDisplayValue(BookRow.COLUMN_TOREAD);
            if (tmp.equals("0")) {
                cbk.setToRead("false");
            } else {
                cbk.setToRead("true");
            }
            tmp = (String) br.getDisplayValue(BookRow.COLUMN_FORMAT);
            if (tmp.equals("Paper")) {
                cbk.setDigitalBook("false");
                continue;
            } else {
                cbk.setDigitalBook("true");
            }
            tmp = (String) br.getDisplayValue(BookRow.COLUMN_COLLECTION_POS);
            if (tmp != null && !tmp.equals("")) {
                cbk.setColnr(new Long(tmp));
            }
            tmp = (String) br.getDisplayValue(BookRow.COLUMN_PRICE);
            if (tmp != null && !tmp.equals("")) {
                cbk.setPrice(new Double(tmp));
            }
            cbk.setCollection((String) br.getDisplayValue(BookRow.COLUMN_COLLECTION));
            cbk.setType((String) br.getDisplayValue(BookRow.COLUMN_BOOK_TYPE));
            tmp = (String) br.getDisplayValue(BookRow.COLUMN_READ);
            if (tmp != null && !tmp.equals("")) {
                cbk.setReadNr(new Long(tmp));
            }

            cbk.setLanguage((String) br.getDisplayValue(BookRow.COLUMN_LANGUAGE));
            cbk.setCurrency((String) br.getDisplayValue(BookRow.COLUMN_CURRENCY));
            PackageHelper bh = new PackageHelper(cbk);
            String savedFile = br.getID() + "-" + cbk.getTitle() + "-" + cbk.getDigitalBook();
            savedFile = savedFile.replace("?", "").replace("\"", "").replace(":", "");
            String saveFileNoExtension = "e://testbooks//" + savedFile;
            int pos = i + 1;
            System.out.println(pos + "/" + bdb.getBooks().size() + " adding file: "
                    + cbk.getTitle() + "-" + cbk.getDigitalBook()
                    + br.getDisplayValue(BookRow.COLUMN_AUTHORS) + "-" + cbk.getLanguage());
            // XMLUtil.fileToZIP(XMLUtil.saveXMLToFile("test.opf", bh), saveFileNoExtension +
            // ".zip");
            if (false)
                try {
                    String line;
                    String command = "calibredb add -d \"" + saveFileNoExtension + ".zip\"";
                    System.out.println("Execute: " + command);
                    Process p = Runtime.getRuntime().exec(command);
                    BufferedReader input = new BufferedReader(new InputStreamReader(
                            p.getInputStream()));
                    while ((line = input.readLine()) != null) {
                        System.out.print(line + " ");
                    }
                    System.out.println("");
                    input.close();
                } catch (Exception err) {
                    err.printStackTrace();
                }
            if (cbk.getDigitalBook() == "true") {
                try {
                    String line;
                    String param = "list -f title,*digitalbook --separator=; -s \"title:\"\"\""
                            + cbk.getTitle() + "\"\"\" #digitalbook:\"\"\"yes\"\"\" \"";
                    Process p = Runtime.getRuntime().exec(
                            "C://Program Files (x86)//Calibre2//calibredb " + param);
                    BufferedReader input = new BufferedReader(new InputStreamReader(
                            p.getInputStream()));
                    // need to get seccond line for id
                    int lineNR = 1;
                    String id = null;
                    while ((line = input.readLine()) != null) {
                        // System.out.println(line);
                        if (lineNR == 2) {
                            // getID
                            StringTokenizer st = new StringTokenizer(line, ";");
                            id = st.nextToken();
                            break;
                        }
                        lineNR++;
                    }
                    input.close();
                    if (id != null) {
                        System.out.println("Found id: " + id);
                        param = "calibredb add_format " + id + " \""
                                + br.getFile().getAbsolutePath() + "\"";
                        System.out.println(param);
                        p = Runtime.getRuntime().exec(param);
                        input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        while ((line = input.readLine()) != null) {
                            System.out.println(line);
                        }
                        input.close();
                        param = "remove_format " + id + " zip";
                        p = Runtime.getRuntime().exec(
                                "C://Program Files (x86)//Calibre2//calibredb " + param);
                        input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        while ((line = input.readLine()) != null) {
                            System.out.println(line);
                        }
                        input.close();
                    }
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
            System.out.println(" ");
            // break;
        }

    }
}
