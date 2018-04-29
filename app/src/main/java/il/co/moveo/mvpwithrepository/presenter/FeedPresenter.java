package il.co.moveo.mvpwithrepository.presenter;

import il.co.moveo.mvpwithrepository.model.Post;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;

public class FeedPresenter implements Presenter {
    private final Realm realm;

    public FeedPresenter() {
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


    public OrderedRealmCollection<Post> allPosts(){
        return realm.where(Post.class).findAll();
    }
}
