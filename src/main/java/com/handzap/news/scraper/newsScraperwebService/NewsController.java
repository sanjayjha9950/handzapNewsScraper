package com.handzap.news.scraper.newsScraperwebService;


import Model.ArticleDTO;
import org.springframework.web.bind.annotation.*;
import serviceIMPL.NewsService;

import java.util.List;

@RestController
public class NewsController {


    private NewsService newsService = new NewsService();

    @GetMapping("/authors")
    public List<String> getListOfAuthors(){
        return newsService.getListOfAuthors();
    }

    @GetMapping("/search/{queryString}")
    public List<ArticleDTO> getSearchResults(@PathVariable String queryString){
        return newsService.getArticleBySearchQuery(queryString);
    }
}
