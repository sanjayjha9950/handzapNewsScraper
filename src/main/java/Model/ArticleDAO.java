package Model;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    public void saveArticle(List<ArticleDTO> articleDTOList){
        //save article dto
    }



    public List<ArticleDTO> getSearchResults(String queryString){
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        //like query to fetch articles list
        return articleDTOList;

    }

    public List<String> getListOfAuthors(){
        List<String> authors = new ArrayList<>();
        //fetch list of authors using distinct query
        return authors;
    }

}
