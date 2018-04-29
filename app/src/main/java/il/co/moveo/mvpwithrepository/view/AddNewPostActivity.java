package il.co.moveo.mvpwithrepository.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import il.co.moveo.mvpwithrepository.R;
import il.co.moveo.mvpwithrepository.model.Post;
import il.co.moveo.mvpwithrepository.presenter.PostPresenter;

public class AddNewPostActivity extends AppCompatActivity implements View.OnClickListener,PostView {
    private EditText mTitle;
    private EditText mDescription;
    private Button mButtonPublish;
    PostPresenter presenter=new PostPresenter(this);
    int mPostId;
    static final int POST_ID=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);
        initViews();
        presenter.onCreate();
    }


    public void initViews() {
        mTitle=findViewById(R.id.et_title);
        mDescription=findViewById(R.id.et_description);
        mButtonPublish=findViewById(R.id.btn_publish);
        mButtonPublish.setOnClickListener(this);
        if(mPostId!=POST_ID)
            mButtonPublish.setText(R.string.update);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_publish){
            presenter.publishPost(mTitle.getText().toString(),mDescription.getText().toString(),mPostId);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
        //todo ask eliran if this is the right way
        mPostId=presenter.getPostId();
        presenter.updatePostDataIfNeeded(mPostId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public int getPostId() {
        return getIntent().getIntExtra(Post.ID,POST_ID);
    }

    @Override
    public void notifyTheUser() {
        Toast.makeText(this,"Required fields",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updatePostDataIfNeeded(String title, String description) {
        mTitle.setText(title);
        mDescription.setText(description);
    }
}
