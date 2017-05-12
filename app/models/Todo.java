package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

//models a simple to do item
@Entity
public class Todo extends Model {
	public String title;
	
	public Todo(String title)
	{
		this.title = title;
	}

}
