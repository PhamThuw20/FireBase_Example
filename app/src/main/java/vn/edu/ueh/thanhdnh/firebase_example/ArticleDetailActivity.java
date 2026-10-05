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
        TextView tvViews = findViewById(R.id.tvViewsDetail);
        TextView tvContent = findViewById(R.id.tvContentDetail);

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String imgCover = getIntent().getStringExtra("img_cover");
        long views = getIntent().getLongExtra("view_count", 0);

        tvTitle.setText(title);
        tvContent.setText(content);
        tvViews.setText("Lượt xem: " + views);

        if (imgCover != null && !imgCover.isEmpty()) {
            int resourceId = getResources().getIdentifier(imgCover, "drawable", getPackageName());
            if (resourceId != 0) {
                ivCover.setImageResource(resourceId);
            } else {
                ivCover.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        }
    }
}
