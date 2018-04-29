package il.co.moveo.mvpwithrepository.presenter;

import il.co.moveo.mvpwithrepository.model.Post;
import il.co.moveo.mvpwithrepository.view.PostView;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;

public class PostPresenter implements Presenter {
    private final Realm realm;
    private PostView view;

    public PostPresenter(PostView view) {
        this.view = view;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        realm.close();

    }

    public void publishPost(String title, String description, final int id) {
        boolean isValid=isValid(title,description);
        if (!isValid) {
            view.notifyTheUser();
            return;
        }
        final Post post=new Post(title,description);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int postId;
                if(id!=-1)
                    postId=id;
                else {
                    // increment index
                    Number currentIdNum = realm.where(Post.class).max(Post.ID);
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    postId=nextId;
                }
                post.setId(postId);
                realm.insertOrUpdate(post);
                view.finishActivity();
            }
        });
    }

    private boolean isValid(String title, String description) {
        if(title.equals("") || description.equals(""))
            return false;
        return true;
    }

    public OrderedRealmCollection<Post> allPosts(){
        return realm.where(Post.class).findAllAsync();
    }

    public int getPostId() {
         return view.getPostId();
    }

    public void updatePostDataIfNeeded(final int mPostId) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Post post=realm.where(Post.class).equalTo(Post.ID,mPostId).findFirst();
                if(post!=null)
                    view.updatePostDataIfNeeded(post.getTitle(),post.getDescription());
            }
        });
    }
}
