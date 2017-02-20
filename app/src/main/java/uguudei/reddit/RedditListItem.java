package uguudei.reddit;

public class RedditListItem {
	private String title;
	private String score;
	private String subreddit;
    private String url;

	public RedditListItem() {
	}

	public RedditListItem(String title, String score, String subreddit, String url) {
		super();
		this.setTitle(title);
		this.setScore(score);
		this.setSubreddit(subreddit);
        this.url = url;
	}


	@Override
	public boolean equals(Object obj){
		if (obj == null) {
			return false;
		}
		if (!RedditListItem.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final RedditListItem other = (RedditListItem) obj;
		if (!this.getTitle().equals(other.getTitle()) || ! this.getScore().equals(other.getScore())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode(){
		int hash = 15;
		hash = 53 * this.getScore().hashCode() + this.getTitle().hashCode();
		return hash;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getSubreddit() {
		return subreddit;
	}

	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}

    public String getUrl() {
        return url;
    }

    public void setSeturl(String url) {
        this.url = url;
    }

}
