package il.co.moveo.mvpwithrepository.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import il.co.moveo.mvpwithrepository.R;
import il.co.moveo.mvpwithrepository.model.Post;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class PostAdapter extends RealmRecyclerViewAdapter<Post,PostAdapter.PostViewHolder> {
    Context context;
    public PostAdapter(Context context,@Nullable OrderedRealmCollection<Post> data, boolean autoUpdate) {
        super(data, autoUpdate);
        this.context=context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { updatePost(v); }
        });
        return new PostViewHolder(itemView);
    }

    private void updatePost(View view) {
        Intent intent=new Intent(context,AddNewPostActivity.class);
        int position=(int)view.getTag();
        int postId=getItem(position).getId();
        intent.putExtra(Post.ID,postId);
        context.startActivity(intent);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.itemView.setTag(position);
        Post post=getData().get(position);
        holder.bind(post);

    }
    class PostViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle,mDescription;
        public PostViewHolder(View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.tv_title);
            mDescription=itemView.findViewById(R.id.tv_description);
        }

        void bind(Post post){
            mTitle.setText(post.getTitle());
            mDescription.setText(post.getDescription());
        }
    }
}
