package pl.edu.pjatk.lab7.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private int id;
    private String name;
    private String dateOfUpdate;
    private int numberAvailable;

    public Item(){
    }

    public Item(int id, String name, String dateOfUpdate, int numberAvailable){
        super();
        this.id = id;
        this.name = name;
        this.dateOfUpdate = dateOfUpdate;
        this.numberAvailable = numberAvailable;
    }
    
    public Item(int id, String name, int numberAvailable){
        super();
        this.id = id;
        this.name = name;
        this.dateOfUpdate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        this.numberAvailable = numberAvailable;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getDate() {
        return this.dateOfUpdate;
    }

    /** @param dateOfUpdate - String in formt dd.MM.yyyy*/
    public void setDate(String dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    public int getAvailable() {
        return numberAvailable;
    }

    public void setAvailable(int numberAvailable) {
        this.numberAvailable = numberAvailable;
    }

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", dateOfUpdate=" + dateOfUpdate + ", numberAvailable="
				+ numberAvailable + "]";
	}

    
}
