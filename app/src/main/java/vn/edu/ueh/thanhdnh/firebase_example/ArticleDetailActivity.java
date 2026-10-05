package vn.edu.ueh.thanhdnh.firebase_example;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ArticleDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        ImageView ivCover = findViewById(R.id.ivCoverDetail);
        TextView tvTitle = findViewById(R.id.tvTitleDetail);
        TextView tvContent = findViewById(R.id.tvContentDetail);

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String imgCover = getIntent().getStringExtra("img_cover");

        tvTitle.setText(title);
        tvContent.setText(content);

        if (imgCover != null && !imgCover.isEmpty()) {
            com.squareup.picasso.Picasso.get()
                    .load(imgCover)
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_gallery)
                    .into(ivCover);
        }
    }
}
