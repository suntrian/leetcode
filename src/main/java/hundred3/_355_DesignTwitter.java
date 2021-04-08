package hundred3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//355
/*Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
        postTweet(userId, tweetId): Compose a new tweet.
        getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
        follow(followerId, followeeId): Follower follows a followee.
        unfollow(followerId, followeeId): Follower unfollows a followee.
*/
public class _355_DesignTwitter {

    /** Initialize your data structure here. */
    private List<List<String>> twitter;
    private List<List<Integer>> follow;

    public _355_DesignTwitter() {
        twitter = new ArrayList<List<String>>();
        follow = new ArrayList<List<Integer>>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        this.twitter.add(Arrays.asList(String.valueOf(userId),String.valueOf(tweetId)));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        List<Integer> userfollow = new ArrayList<>();
        int count = 0;
        for(int i = 0; i < this.follow.size(); i++){
            if(this.follow.get(i).get(0) == userId) userfollow.add(this.follow.get(i).get(1));
        }
        for (int i = this.twitter.size() - 1; i >= 0 ; i--) {
            if(Integer.valueOf(this.twitter.get(i).get(0)) == userId || userfollow.contains(Integer.valueOf(this.twitter.get(i).get(0)))) {
                result.add(Integer.valueOf(this.twitter.get(i).get(1)));
                count++;
                if(count >=10) break;
            }
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        this.follow.add(Arrays.asList(followerId, followeeId));
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        for(int i = 0; i < this.follow.size(); i++) {
            if (this.follow.get(i).get(0) == followerId && this.follow.get(i).get(1) == followeeId) {
                this.follow.remove(i);
            }
        }
    }

    /**
     * Your Twitter object will be instantiated and called as such:
     * Twitter obj = new Twitter();
     * obj.postTweet(userId,tweetId);
     * List<Integer> param_2 = obj.getNewsFeed(userId);
     * obj.follow(followerId,followeeId);
     * obj.unfollow(followerId,followeeId);
     */


    public static void main(String[] args) {
        _355_DesignTwitter t = new _355_DesignTwitter();
        t.postTweet(1,5);
        t.getNewsFeed(1);
        t.follow(1,2);
        t.postTweet(2,6);
        t.getNewsFeed(1);
        t.unfollow(1,2);
        t.getNewsFeed(1);
    }

}
