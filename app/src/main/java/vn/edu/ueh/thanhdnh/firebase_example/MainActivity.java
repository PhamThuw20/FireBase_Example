package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore db;
    Button btAdd, btShow;
    EditText etTitle, etContent, etImgCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
        
        btAdd = findViewById(R.id.btAdd);
        btShow = findViewById(R.id.btShow);
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        etImgCover = findViewById(R.id.etImgCover);
        
        btAdd.setOnClickListener(this);
        btShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btAdd) {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            String imgCover = etImgCover.getText().toString();
            
            // Lượt xem mặc định khi vừa tạo bài viết là 0
            long viewCount = 0;
            
            Article article = new Article(title, content, imgCover, viewCount);
            
            // Add to "articles" collection
            db.collection("articles").add(article)
                .addOnSuccessListener(documentReference -> Toast.makeText(MainActivity.this, "Added Article Successfully", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Failed to Add Article", Toast.LENGTH_SHORT).show());
                
            etTitle.setText("");
            etContent.setText("");
            etImgCover.setText("");
        } else if (view.getId() == R.id.btShow) {
            Intent intent = new Intent(getBaseContext(), ShowDataActivity.class);
            startActivity(intent);
        }
    }
}
