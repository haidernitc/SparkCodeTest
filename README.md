Titanic Data
------------

File list: <br />
1.AggregatedReport.java <br />
2.ExtractExcelToCSV.java <br />
3.titanic3.xls <br />
4.newFile.txt <br />
5.pom.xml

Design Decisions & Project Issues:

  I have configured Spark in windows and placed spark code under D:/spark-1.5.2-bin-hadoop2.6 folder in my local machine. 
  
  Reading excel file as is not a great way of handling data since we might loose the data exact behavior(certain column are integers but  while reading they might appear as double) so parsed it and converted it to '|' delimited file without altering data. 
  

  
  First Report would appear as below.
  
  +-----+------------+---------------+     <br />
|Count|  CatDetails|            Cat|        <br />  
+-----+------------+---------------+        <br />
|  200|           1|Passenger Class|        <br />
|  181|           3|Passenger Class|        <br />
|  119|           2|Passenger Class|        <br /> 
|  339|      female|         Gender|        <br />
|  161|        male|         Gender|        <br />
|   45|     Teenage|        AgeBand|        <br />
|   89|  Middle Age|        AgeBand|        <br />
|  205|       Young|        AgeBand|        <br />
|  111|     Elderly|        AgeBand|        <br />
|   50|Less than 10|        AgeBand|        <br />
+-----+------------+---------------+

Second Report would appear as below.    

+-----+--------------------+               <br />
|Count|               State|               <br />
+-----+--------------------+               <br />
|    1|                  MA|               <br />
|    1|  Ireland Washington|               <br />
|    1|                null|               <br />
|    1| NY / Briarcliff ...|               <br />
|    1|                  PQ|               <br />
|    1| England / Cottag...|               <br />
|    4|    PA / Cooperstown|               <br />
|    1|                  IL|               <br />
|    2|                  AB|               <br />
|    3|                  PA|               <br />
|    1|                  NJ|               <br />
|    1|     NY / Washington|               <br />
|    1| England / New Du...|               <br />
|    1|             Co Cork|               <br />
|    2|                  NY|               <br />
|    2|                  MA|               <br />
|    1|                  NY|               <br />
|    1|             England|               <br />
|    2|                  VT|               <br />
|    1|                  OH|               <br />
+-----+--------------------+               <br />
only showing top 20 rows                   <br />

  
  
