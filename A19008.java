import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 @author Chelsea Dorich (Email: <a href="mailto:"robotqi@gmail.com>robotqi@gmail.com</a>)
 @version 1.1 04/04/2014
 @assignment.number A190-08
 @prgm.usage Called from the operating system
 @see "Gaddis, 2013, Starting out with Java, From Control Structures, 5th Edition"
 @see "<a href='http://docs.oracle.com/javase/7/docs/technotes/guides/javadoc/index.html'>JavaDoc Documentation</a>

 */

public class A19008
{
    public static void main(String[] args)throws Exception
    {
        //instantiate INET form
  INET net = new INET();
        //decare string containing input file address
  String strFilePath ="C:\\Users\\Chelsea\\A19008\\Data\\Worlds.txt";
        //decare string containing output file address
  String strOutFile ="C:\\Users\\Chelsea\\A19008\\Data\\UnitedStates.txt";
        //creat bln using inet function to determine whether file exists
  Boolean blnExists = net.fileExists(strFilePath);
  // if bln is fale
  if(!blnExists)
    {//create new file using data from given url
       String strURLData= net.getURLRaw("http://weather.noaa.gov/data/nsd_cccc.txt");
       //save data to file
       net.saveToFile(strFilePath, strURLData);
    }
        //when file exists, get data from file and add to this string
        String strFileData = net.getFromFile(strFilePath);
        // create new strtokeniser using return carriages as deliminator
        StringTokenizer strTokenizer = new StringTokenizer(strFileData, "\r\n", true);
        // create new string bbuilder
        StringBuilder stbContent = new StringBuilder("");
        //declare empty string
        String strFileLine= "";
        //while there are more tokens
        while(strTokenizer.hasMoreTokens())
    {// put next token into the string that was just created
        strFileLine = strTokenizer.nextToken();
        //create a second tokeniser using semicolens as the deliminators, also returining those to the origonal text
        StringTokenizer strTokenizer2 = new StringTokenizer(strFileLine, ";", true);
        //create a new array list to hole the peices being tokenised
        ArrayList<String> list = new ArrayList<String>();
        //while there are ore tokens in the second tokeniser
        while(strTokenizer2.hasMoreTokens())
        {//create a string to hold the next token
            String strSemiSorter = strTokenizer2.nextToken();
            //add each token to the arylist
            list.add(strSemiSorter);
        }
        //use an if statement to determine if the ary is fully populated
        if(list.size()> 11)
        {
         //second if to sore records: if the record does not have dashes in the fourth index and united states int the
            //tenth index, do nothing; otherwise,
        if(!list.get(4).startsWith("---")&&list.get(10).startsWith("United States"))
        { // create a string with select peices of the array and append it to the string builder
            String strOutputData= "Station 4 letter ID: "+list.get(0)+ "\t"+"Station Name: "+list.get(6)+"\t"+
                    "State: "+list.get(8)+"\t"+" Latitude: "+list.get(14)+"\t"+" Longitude: "+ list.get(16)+"\n";
            stbContent.append(strOutputData);
        }

        }
        //when the string builder is finished building, print the data to the output file
        net.saveToFile(strOutFile, stbContent.toString());

    }}}
