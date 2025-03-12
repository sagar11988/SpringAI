package com.dailycodebuffer.budget_ai;

import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BudgetAiApplicationTests {

	@Value("classpath:/Defects.xlsx")
	private Resource defects;
	@Test
	void contextLoads() {
//		try (Workbook workbook = WorkbookFactory.create(new FileInputStream(
//				           new File("C:\\Users\\Lenovo\\Downloads\\budget-ai-main\\src\\main\\resources\\Defects.xlsx") ))){
//
//			List<Document> documents = new ArrayList<>();
//			// FileInputStream file = new FileInputStream(
//			//           new File("gfgcontribute.xlsx"));
//			System.out.println("Create Vector File");
//			//  workbook = WorkbookFactory.create(defects.getInputStream());
//			Sheet sheet = workbook.getSheetAt(0);
//			for (Row row:sheet
//			) {
//				StringBuilder rowContent = new StringBuilder();
//				for (Cell cell: row
//				) {
//					rowContent.append(cell.toString()).append(" ");
//				}
//				System.out.println(rowContent);
//
//				//Document document = new Document(rowContent.toString().trim());
//				//document.getMetadata().put("filename","Defects.xlsx");
//				//documents.add(document);
//			}
//			//workbook.close();
//			//vectorStore.add(documents);
//			//vectorStore.save(vectorStoreFile);
//		} catch (Exception e){
//			e.printStackTrace();
//		}

	}

}
