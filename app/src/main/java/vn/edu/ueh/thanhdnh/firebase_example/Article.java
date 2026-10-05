package vn.edu.ueh.thanhdnh.firebase_example;

public class Article {
    private String id;
    private String title;
    private String content;
    private String img_cover;

    public Article() {
        // Default constructor required for calls to DataSnapshot.getValue(Article.class) in Firebase
    }

    public Article(String title, String content, String img_cover) {
        this.title = title;
        this.content = content;
        this.img_cover = img_cover;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg_cover() {
        return img_cover;
    }

    public void setImg_cover(String img_cover) {
        this.img_cover = img_cover;
    }
}
