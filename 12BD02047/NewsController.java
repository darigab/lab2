
public class NewsController {
	private News model;
	private NewsView view;
	
	public NewsController(News model, NewsView view){
		this.model=model;
		this.view=view;
	}
	
	public void setNewsTitle(String title){
		model.setTitle(title);
	}
	
	public String getNewsTitle(){
		return model.getTitle();
	}
	
	public void setNewsAuthor(String author){
		model.setAuthor(author);
	}
	
	public String getNewsAuthor(){
		return model.getAuthor();
	}
	
	public void updateView(){
		view.printCurrentNews(model.getTitle(), model.getAuthor());
		
	}
}
