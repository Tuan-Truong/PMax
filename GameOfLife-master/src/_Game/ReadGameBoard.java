package _Game;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Game Of Life program created for HIOA final project
 * The ReadGameBoard class opens RLE file and decoding it to pattern on board.
 *
 * @version 1.0
 * @since   2017-01-14
 */


public class ReadGameBoard {
    private Stage stage;
    File file;
    public int[][] pattern;
    private String line;
    private String patterName;
    private int wNumInt;
    private int hNumInt;
    private int cellSize;

    /**
     * Constructs  board with Height and Width and Initialize a pattern array, and openfile, readfile methods
     *
     * @param boardHeight canvas height
     * @param boardWidth  canvas width
     */


    public ReadGameBoard(int boardHeight, int boardWidth) {

        pattern = new int[boardWidth][boardHeight];


        try {
            openFile();
            readFile(getLine());

            // setText();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("arrayIndex" + e);
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 /*  public void setText(){


        Pattern hash = Pattern.compile(".*#.*");

        Matcher m = hash.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
        }else {
            System.out.println("NO MATCH");

        }


    }*/


    /**
     * Constructs and initializes the stage.
     *
     * @param primaryStage unused.
     */

    public void init(Stage primaryStage) {

        this.stage = stage;

    }

    /**
     * This method opens the file with FileChooser.
     *
     * @throws IOException On input error.
     * @see IOException
     */

    private void openFile() throws IOException {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open GOL Shape");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Run Length Encoded File", "*.RLE"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*")

        );

        file = fileChooser.showOpenDialog(stage);


        if (file != null) {
            System.out.println("Choosen file " + file);
        }


        Scanner scanner1 = new Scanner(file);
        String line2 = scanner1.nextLine();

        Pattern p = Pattern.compile(".*#.*");
        Matcher m = p.matcher(line2);
        boolean b = m.matches();

        setPatterName(m.group());


      /*  Scanner scanner1 = new Scanner(file);
        scanner1.nextLine();
        System.out.println("funker dette");
        System.out.printf(scanner1.nextLine());
       String line2 = scanner1.nextLine();

        if (Pattern.matches(".*#.*", line2)) {

            Pattern hash = Pattern.compile(".*#.*");

            Matcher m = hash.matcher(line2);
            if (m.find( )) {
                System.out.println("Found value: " + m.group(0) );
                System.out.println("Found value: " + m.group(1) );
                System.out.println("Found value: " + m.group(2) );
            }else {
                System.out.println("NO MATCH");
            }
        }*/


    }


    /**
     * This method decodes each line that has string 'b' , 'o' , '$'.
     * The it is putting it to the pattern array.
     *
     * @throws IOException On input error.
     * @see FileNotFoundException
     */
    public void readFile(String line) throws IOException {

        this.setLine(line);
        int rownumber = 5;
        int columnnumber = 0;
        if (file == null) {
            return;
        }

       /* String url2 = "test";
        URL url = new URL(url2);
        InputStream in = url.openStream();
        Scanner scan = new Scanner(in);



        while (scan.hasNext())
        {
            String str = scan.nextLine();
//            readGameBoard.readFile(str);
            System.out.println(str);
        }
        scan.close();*/

        System.out.println(pattern.length);

        // check if website or file

        try (Scanner scanner = new Scanner(file)) {
            //BufferedReader reader = new BufferedReader(new FileReader(file));

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();

                // checkin g line is empty or commented or with rule line
                if (line.isEmpty() || Pattern.matches(".*rule.*", line) || Pattern.matches(".*#.*", line)) {
                    Pattern hnumber = Pattern.compile(".*x = (?<cnumber>\\d*?),.*");
                    Matcher hmatcher = hnumber.matcher(line);

                    Pattern wnumber = Pattern.compile(".*y = (?<cnumber>\\d*?),.*");
                    Matcher wmatcher = wnumber.matcher(line);
                    hNumInt = 1;
                    wNumInt = 1;
                    //System.out.println("ready to get height");
                    if (hmatcher.find()) {
                        String bNumString = hmatcher.group("cnumber");
                        if (!bNumString.isEmpty()) {
                            System.out.println("he not empty");
                            hNumInt = Integer.parseInt(bNumString);
                        }

                        System.out.println("heigh" + bNumString);
                        System.out.println("test");
                        //columnnumber = columnnumber + bNumInt;
                    }

                    if (wmatcher.find()) {
                        String bNumString = wmatcher.group("cnumber");
                        if (!bNumString.isEmpty()) {

                            wNumInt = Integer.parseInt(bNumString);
                        }

                        pattern = new int[hNumInt][wNumInt];
                        //columnnumber = columnnumber + bNumInt;
                        System.out.println("wdth" + wNumInt);
                    }


                    continue;
                }


                // split the line with $
                Pattern p = Pattern.compile("(?<=\\$)");

                String[] items = p.split(line);
                for (String item : items) {
                    //x = 1714, y = 1647, rule = s23/b3
                    // itemTmp = 2b3o1b2o$
                    String itemTmp = item;
                    // get height and width'


                    // while itemTmp is a valid form
                    while ((!itemTmp.isEmpty()) && Pattern.matches(".*b.*|.*o.*", itemTmp)) {


                        // b pattern - eg. 34b --> cnumber will be 34
                        Pattern bnumber = Pattern.compile("^(?<cnumber>\\d*?)b");
                        Matcher bmatcher = bnumber.matcher(itemTmp);

                        // o pattern eg. 3o -> onumber will be 3
                        Pattern onumber = Pattern.compile("^(?<onumber>\\d*?)o");
                        Matcher omatcher = onumber.matcher(itemTmp);

                        if (bmatcher.find()) {
                            String bNumString = bmatcher.group("cnumber");
                            int bNumInt = 1;
                            if (!bNumString.isEmpty()) {

                                bNumInt = Integer.parseInt(bNumString);
                            }
                            columnnumber = columnnumber + bNumInt;
                            itemTmp = itemTmp.replaceFirst("^\\d*?b", "");
                        } else if (omatcher.find()) {
                            String oNumString = omatcher.group("onumber");

                            int oNumInt = 1;
                            if (!oNumString.isEmpty()) {

                                oNumInt = Integer.parseInt(oNumString);
                            }


                            for (int cnum = 1; cnum <= oNumInt; cnum++) {

                                pattern[rownumber + cellSize][columnnumber + cnum + cellSize] = 1;
                                //pattern[rownumber + 10][columnnumber + cnum + 10] = 1;


                                //columnnumber = columnnumber +1;
                            }
                            columnnumber = columnnumber + oNumInt;
                            itemTmp = itemTmp.replaceFirst("^\\d*?o", "");
                        }

                    }

                    //if $ ONLY move to next row (row = row + 1 and column =0)
                    if (Pattern.matches(".*\\$", item)) {
                        columnnumber = 0;
                        rownumber = rownumber + 1;
                    }


                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void readGameBoardFromURL(String text) throws IOException {

       /* URL oracle = new URL("http://www.conwaylife.com/patterns/gosperglidergungliderdestruction.rle");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        URLConnection conn = oracle.openConnection();
        openFile(new InputStreamReader(conn.getInputStream()));

        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();

        readFile(inputLine);
*/
        URL url = new URL(text);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        String in;

        while ((in = br.readLine()) != null) {
            System.out.println(in);
        }

        br.close();


    }

    public String getCreationDetails(File file) {
        try {
            Path p = Paths.get(file.getAbsolutePath());
            BasicFileAttributes view
                    = Files.getFileAttributeView(p, BasicFileAttributeView.class)
                    .readAttributes();
            FileTime fileTime = view.creationTime();

            return ("" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((fileTime.toMillis())));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public String getPatterName() {
        return patterName;
    }

    public void setPatterName(String patterName) {
        this.patterName = patterName;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getCellSize() {
        return cellSize;
    }
}

/*
    public int getwNumInt() {
        return wNumInt;
    }

    public void setwNumInt(int wNumInt) {
        this.wNumInt = wNumInt;
    }

    public int gethNumInt() {
        return hNumInt;
    }

    public void sethNumInt(int hNumInt) {
        this.hNumInt = hNumInt;
    }


    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }
}*/
