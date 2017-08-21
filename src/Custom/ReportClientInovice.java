/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Custom;

/**
 *
 * @author user
 */

import java.awt.Color;
import java.awt.*;
import java.awt.print.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.Vector;

/**
 *
 * @author a
 */
public class ReportClientInovice implements Pageable, Printable {

    private PageFormat format = null;
    private BufferedReader bufferedReader = null;
    private Font font = null;           // The font to print with
    private int linespacing;           // How much space between lines
    private float LINESPACEFACTOR = 1.1f;
    private Vector lines = null;
    private String line = null;
    private int linesPerPage;          // How many lines fit on a page
    private int numPages;             // How many pages required to print all lines
    private int baseline = -1;        // The baseline position of the font.
    ///////////////////////////////////////////////
    private static String FONTFAMILY = "Monospaced";
    private static int FONTSIZE = 10;
    private static int FONTSTYLE = Font.PLAIN;

  public  ReportClientInovice(String report, PageFormat pageFormat) throws IOException {
        this(new StringReader(report), pageFormat);
    }

    public ReportClientInovice(Reader stream, PageFormat pageFormat) throws IOException {
        format = pageFormat;
        bufferedReader = new BufferedReader(stream);
        lines = new Vector();
        while ((line = bufferedReader.readLine()) != null) {
            lines.addElement(line);
        }
        font = new Font(FONTFAMILY, FONTSIZE, FONTSTYLE);    //Font we will use
        linespacing = (int) (FONTSIZE * LINESPACEFACTOR);     // space between lines
        linesPerPage = (int) Math.floor(pageFormat.getImageableHeight() / linespacing); //lines per page
        numPages = (lines.size() - 1) / linesPerPage + 1;  // number of pages

    }

    public int getNumberOfPages() {
        return numPages;
    }

    public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException {
        return format;
    }

    public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException {
        return this;
    }

    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if ((pageIndex < 0) | (pageIndex >= numPages)) {
            return NO_SUCH_PAGE;
        }
        if (baseline == -1) {
            FontMetrics fm = g.getFontMetrics(font);
            baseline = fm.getAscent();
        }
        g.setColor(Color.WHITE);
        g.fillRect((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY(),
                (int) pageFormat.getImageableWidth(), (int) pageFormat.getImageableHeight());
        g.setFont(font);
        g.setColor(Color.black);
        int startLine = pageIndex * linesPerPage;
        int endLine = startLine + linesPerPage - 1;
        if (endLine >= lines.size()) {
            endLine = lines.size() - 1;
        }
        int x = (int) pageFormat.getImageableX();
        int y = (int) pageFormat.getImageableY();
        for (int i = startLine; i <= endLine; i++) {
            String myLine = (String) lines.elementAt(i);
            if (myLine.length() > 0) {
                g.drawString(myLine, x, y);
            }
            y += linespacing;
        }
        return PAGE_EXISTS;
    }
}
