package com.handzap.news.scraper.newsScraperwebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import serviceIMPL.NewsService;

import java.io.IOException;

@SpringBootApplication
public class NewsScraperWebServiceApplication {

	public static void main(String[] args)throws IOException {
		SpringApplication.run(NewsScraperWebServiceApplication.class, args);
		NewsService service = new NewsService();
		service.scrapNewsDataToDB();
	}

}

