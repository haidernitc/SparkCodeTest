I. File list
------------
1.AggregatedReport.java
2.ExtractExcelToCSV.java
3.titanic3.xls
4.newFile.txt
5.pom.xml

Design Decisions & Project Issues:

  I have configured Spark in windows and placed spark code under D:/spark-1.5.2-bin-hadoop2.6 folder in my local machine. 
  
  Reading excel file as is not a great way of handling data since we might loose the data exact behavior(certain column are integers but  while reading they might appear as double) so parsed it and converted it to '|' delimited file without altering data. 
  
  
  
  
