package il.co.moveo.mvpwithrepository.view;

public interface PostView{
    void finishActivity();

    int getPostId();

    void notifyTheUser();

    void updatePostDataIfNeeded(String title, String description);
}
