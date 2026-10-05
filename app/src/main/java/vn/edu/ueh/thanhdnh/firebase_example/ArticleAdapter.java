package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private Context context;
    private List<Article> articles;

    public ArticleAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    public void update(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(context).inflate(R.layout.article_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvContent.setText(article.getContent());
        holder.tvViewCount.setText("Views: " + article.getView_count());
        
        // Load image resource by name if it's not empty, else set a placeholder
        if (article.getImg_cover() != null && !article.getImg_cover().isEmpty()) {
            int resourceId = context.getResources().getIdentifier(article.getImg_cover(), "drawable", context.getPackageName());
            if (resourceId != 0) {
                holder.imgCover.setImageResource(resourceId);
            } else {
                holder.imgCover.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        } else {
            holder.imgCover.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        // Add Click Listener to open detail and increase view count
        holder.itemView.setOnClickListener(v -> {
            // Update view count on Firebase
            if (article.getId() != null) {
                com.google.firebase.firestore.FirebaseFirestore.getInstance()
                        .collection("articles")
                        .document(article.getId())
                        .update("view_count", article.getView_count() + 1);
            }

            // Start Detail Activity
            android.content.Intent intent = new android.content.Intent(context, ArticleDetailActivity.class);
            intent.putExtra("title", article.getTitle());
            intent.putExtra("content", article.getContent());
            intent.putExtra("img_cover", article.getImg_cover());
            intent.putExtra("view_count", article.getView_count() + 1); // Pass the updated count
            intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
