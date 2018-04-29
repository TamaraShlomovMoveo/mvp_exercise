package il.co.moveo.mvpwithrepository.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import il.co.moveo.mvpwithrepository.R;
import il.co.moveo.mvpwithrepository.model.Post;
import il.co.moveo.mvpwithrepository.presenter.FeedPresenter;
import il.co.moveo.mvpwithrepository.presenter.PostPresenter;
import io.realm.Realm;

public class FeedActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;
    FeedPresenter presenter=new FeedPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        FloatingActionButton fab =findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),AddNewPostActivity.class));
            }
        });
        mRecyclerView=findViewById(R.id.recycler_view);
        mAdapter=new PostAdapter(this,presenter.allPosts(), true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

    }

}
