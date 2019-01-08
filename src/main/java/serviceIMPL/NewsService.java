package serviceIMPL;

import Model.ArticleDAO;
import Model.ArticleDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


public class NewsService {
    final Set<String> authors = new HashSet<>();
    ArticleDAO articleDAO = new ArticleDAO();

public void scrapNewsDataToDB()throws IOException {

    String address = "https://www.thehindu.com/archive/";
    String macro1 = "href=\"https://www.thehindu.com/archive/web/";
    String macro2 = ".ece";///article[\d]*.ece/
    List<String> monthsLink = getListOfURLs(address,macro1);
    List<String> daysLink = new ArrayList<>();

    for (String monthURL: monthsLink){
        daysLink = getListOfURLs(monthURL,macro1);
        for (String dayLink: daysLink){
            List<String> articlesLinks = getListOfURLs(dayLink,macro2);
            List<ArticleDTO> articleDTOList = new ArrayList<>();
            for(String article: articlesLinks) {
                System.out.println(article);
                final Document document = Jsoup.connect(article).userAgent("mozilla/17.0").get();
                Elements element = document.select("a.auth-nm");
                String author = element.text();
                String title = document.select("h1.title").text();
                System.out.println(element.text()+" "+document.select("h1.title").text());
                authors.add(element.text());
                ArticleDTO articleDTO = new ArticleDTO();
                articleDTO.setAuthor(author);
                articleDTO.setTitle(title);
                articleDTO.setUrl(article);
                articleDTOList.add(articleDTO);
            }
            articleDAO.saveArticle(articleDTOList);
        }
    }

}

public List<ArticleDTO> getArticleBySearchQuery(String query){
    return articleDAO.getSearchResults(query);
}

public List<String> getListOfAuthors(){
    return articleDAO.getListOfAuthors();
}


private List<String> getListOfURLs(String address,String macro) throws IOException {
    URL temp = new URL(address);
    Scanner in = new Scanner(temp.openStream());
    List<String> urlList=new ArrayList<>();
    try{
    while(in.hasNext()){
        String line = in.next();
        if (line.contains(macro) && !line.contains(".ece/")){
            int from = line.indexOf("\"");
            int to = line.lastIndexOf("\"");
            urlList.add(line.substring(from+1,to));
        }
    }
    }catch (Exception e){
        System.out.println(e.getStackTrace());
    }
    return urlList;
}
}
