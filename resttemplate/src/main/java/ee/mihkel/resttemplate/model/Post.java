package ee.mihkel.resttemplate.model;

import lombok.Data;

@Data
public class Post {
    private int id;
    private String slug;
    private String url;
    private String title;
    private String content;
    private String image;
    private String thumbnail;
    private String status;
    private String category;
    private String publishedAt;
    private String updatedAt;
    private int userId;
}
