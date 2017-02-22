package com.code.titanic;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class AggregatedReport {

	public static void main(String[] args) {
		System.setProperty("hadoop.home.dir", "D:/spark-1.5.2-bin-hadoop2.6");
		SparkConf conf = new SparkConf()
				.setAppName("org.sparkexample.WordCount")
				.setMaster("local")
				.set("spark.sql.warehouse.dir",
						"file:///C:/Users/Haider/git/SparkCodeTest/spark-basics/");
		SparkContext context = new SparkContext(conf);
		try {
			ExtractExcelToCSV.convertExcelToCsv();
		} catch (EncryptedDocumentException | InvalidFormatException
				| IOException e) {
			e.printStackTrace();
		}
		SparkSession sparkSession = new SparkSession(context);
		Dataset<Row> df = sparkSession.read()
				.format("com.databricks.spark.csv").option("header", true)
				.option("delimiter", "|").option("inferSchema", true)
				.load("newFile.txt");
		System.out.println("========== Print Schema ============");
		df.printSchema();
		System.out.println("========== Print Data ==============");
		// df.show();
		try {
			df.createTempView("titanicdata");
		} catch (AnalysisException e) {
			e.printStackTrace();
		}

		Dataset<Row> report1 = sparkSession
				.sql("select Count, CatDetails,Cat from (select sum(Count) as Count,CatDetails,'Passenger Class' as Cat from (select 1 as Count,pclass as catdetails from titanicdata where survived=1)A group by catdetails)A union all  select Count,CatDetails,Cat from (select sum(Count) as Count,CatDetails,'Gender' as Cat from (select 1 as Count,sex as catdetails from titanicdata where survived =1)A  group by catdetails)A union all select Count, CatDetails,Cat from  (select sum(Count) as Count,CatDetails,'AgeBand' as Cat from (select 1 as Count,case when age <=10 then 'Less than 10' when age >10 and age <=18 then 'Teenage' when age >18 and age<=35 then 'Young' when age>35 and age <=50 then 'Middle Age' else 'Elderly' end as catdetails from titanicdata where survived=1)A group by catdetails)A");
		report1.show();

		Dataset<Row> report2 = sparkSession
				.sql("select count(*) Count,case when split(split(homedest,'//')[0],',')[1] ='' or split(split(homedest,'//')[0],',')[1] = null then 'Unknown' else split(split(homedest,'//')[0],',')[1] end as State from titanicdata where survived=1 group by homedest, 'State' ");
		report2.show();
	}
}
